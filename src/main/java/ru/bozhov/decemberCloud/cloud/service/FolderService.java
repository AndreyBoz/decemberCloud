package ru.bozhov.decemberCloud.cloud.service;

import ru.bozhov.decemberCloud.auth.model.DecemberUser;
import ru.bozhov.decemberCloud.cloud.model.Folder;

import java.util.List;

public interface FolderService{
    List<Folder> getFolderByUser(DecemberUser user);
    Folder getRootFolder(DecemberUser user);

    void saveFolder(Folder folder);
    Folder getFolderById(Long id);
}
