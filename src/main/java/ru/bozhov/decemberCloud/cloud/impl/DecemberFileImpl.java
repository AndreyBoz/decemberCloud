package ru.bozhov.decemberCloud.cloud.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.bozhov.decemberCloud.cloud.model.DecemberFile;
import ru.bozhov.decemberCloud.cloud.model.Folder;
import ru.bozhov.decemberCloud.cloud.repository.DecemberFileRepository;
import ru.bozhov.decemberCloud.cloud.repository.FolderRepository;
import ru.bozhov.decemberCloud.cloud.service.DecemberFileService;

@AllArgsConstructor
@Slf4j
@Service
public class DecemberFileImpl implements DecemberFileService {
    DecemberFileRepository decemberFileRepository;
    FolderRepository folderRepository;
    @Transactional
    public void saveFile(DecemberFile file, Folder folder){
        decemberFileRepository.save(file);
        folderRepository.save(folder);
    }

    @Override
    @Transactional
    public void renameFile(Long fileId, String newFileName) {
        DecemberFile file = decemberFileRepository.findDecemberFileById(fileId);

        if(file==null) {
            log.debug("File was not found");
            throw new RuntimeException("File was not found");
        }

        file.setFilename(newFileName);
    }

    @Override
    public void deleteFile(Long fileId) {
        decemberFileRepository.deleteById(fileId);
    }
}
