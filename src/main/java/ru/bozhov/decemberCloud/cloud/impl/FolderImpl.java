package ru.bozhov.decemberCloud.cloud.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.bozhov.decemberCloud.auth.model.DecemberUser;
import ru.bozhov.decemberCloud.cloud.model.Folder;
import ru.bozhov.decemberCloud.cloud.repository.FolderRepository;
import ru.bozhov.decemberCloud.cloud.service.FolderService;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class FolderImpl implements FolderService {

    FolderRepository folderRepository;

    @Override
    public List<Folder> getFolderByUser(DecemberUser user) {
        List<Folder> folders =  folderRepository.findFoldersByAccessedUsers(Collections.singletonList(user));
        if(folders.isEmpty())
            log.debug("Folders are not found");

        return folders;
    }

    @Override
    public Folder getRootFolder(DecemberUser user) {
        List<Folder> folders = folderRepository.findFoldersByAccessedUsersAndFolderName(Collections.singletonList(user), "main");

        if(folders.isEmpty())
            log.debug("Folders are not found");

        return folders.get(0);
    }

    @Override
    public void saveFolder(Folder folder) {
        folderRepository.save(folder);
    }

    @Override
    public Folder getFolderById(Long id) {
        Folder folder = folderRepository.findFolderById(id);

        if(folder == null)
            log.debug("Folder was not found");

        return folder;
    }
}
