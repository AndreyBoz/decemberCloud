package ru.bozhov.decemberCloud.cloud.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.bozhov.decemberCloud.auth.model.DecemberUser;
import ru.bozhov.decemberCloud.cloud.model.DecemberFile;
import ru.bozhov.decemberCloud.cloud.model.Folder;
import ru.bozhov.decemberCloud.cloud.service.DecemberFileService;
import ru.bozhov.decemberCloud.cloud.service.FolderService;
import ru.bozhov.decemberCloud.common.service.user.DecemberUserService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

@AllArgsConstructor
@RestController
@RequestMapping("/api/file")
@CrossOrigin(origins = "*")
public class FileController {

    private static final String UPLOAD_DIR = "F:/"; // Укажите ваш путь
    private final DecemberFileService decemberFileService;
    private final DecemberUserService decemberUserService;
    private final FolderService folderService;

    @PostMapping("/upload")
    public ResponseEntity<String> postFile(@RequestParam("file") MultipartFile file, @RequestParam("folderId") Long folderId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Вы не авторизированы для загрузки файлов.");
        }

        DecemberUser user = new DecemberUser();

        try {
            DecemberFile decemberFile = new DecemberFile();
            decemberFile.setInsDate(Calendar.getInstance());
            decemberFile.setData(file.getBytes());
            String[] filenameParts = file.getOriginalFilename().split("\\.");
            decemberFile.setFileType(filenameParts[filenameParts.length - 1]);
            decemberFile.setFilename(file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf('.')));

            Folder folder = folderService.getFolderById(folderId);
            if (folder == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Папка не найдена.");
            }
            folder.getDecemberFiles().add(decemberFile);
            decemberFile.setFolder(folder);

            decemberFileService.saveFile(decemberFile, folder);

            File destFile = new File(UPLOAD_DIR + File.separator + file.getOriginalFilename());
            file.transferTo(destFile);
            return ResponseEntity.ok("Файл успешно загружен: " + destFile.getAbsolutePath());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Не удалось загрузить файл: " + e.getMessage());
        }
    }

    @PostMapping("/createFolder")
    public ResponseEntity<String> createFolder(@RequestBody FolderRequest folderRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Вы не авторизированы для создания папок.");
        }

        DecemberUser user = decemberUserService.findUserByUsername(((UserDetails) authentication.getPrincipal()).getUsername());

        try {
            Folder folder = new Folder();
            folder.setFolderName(folderRequest.getFolderName());
            folder.setAccessedUsers(new ArrayList<>(Collections.singletonList(user)));

            folderService.saveFolder(folder);
            return ResponseEntity.ok("Папка успешно создана.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Не удалось создать папку: " + e.getMessage());
        }
    }

    @PostMapping("/rename")
    public ResponseEntity<String> renameFile(@RequestBody RenameFileRequest renameFileRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Вы не авторизированы для переименования файлов.");
        }

        try {
            decemberFileService.renameFile(renameFileRequest.getFileId(), renameFileRequest.getNewFileName());
            return ResponseEntity.ok("Файл успешно переименован.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Не удалось переименовать файл: " + e.getMessage());
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteFile(@RequestBody DeleteFileRequest deleteFileRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Вы не авторизированы для удаления файлов.");
        }

        try {
            decemberFileService.deleteFile(deleteFileRequest.getFileId());
            return ResponseEntity.ok("Файл успешно удален.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Не удалось удалить файл: " + e.getMessage());
        }
    }

    static class FolderRequest {
        private String folderName;

        // Getters and Setters
        public String getFolderName() {
            return folderName;
        }

        public void setFolderName(String folderName) {
            this.folderName = folderName;
        }
    }

    static class RenameFileRequest {
        private Long fileId;
        private String newFileName;

        // Getters and Setters
        public Long getFileId() {
            return fileId;
        }

        public void setFileId(Long fileId) {
            this.fileId = fileId;
        }

        public String getNewFileName() {
            return newFileName;
        }

        public void setNewFileName(String newFileName) {
            this.newFileName = newFileName;
        }
    }

    static class DeleteFileRequest {
        private Long fileId;

        // Getters and Setters
        public Long getFileId() {
            return fileId;
        }

        public void setFileId(Long fileId) {
            this.fileId = fileId;
        }
    }
}
