package ru.bozhov.decemberCloud.cloud.service;

import ru.bozhov.decemberCloud.cloud.model.DecemberFile;
import ru.bozhov.decemberCloud.cloud.model.Folder;


public interface DecemberFileService {
    public void saveFile(DecemberFile file, Folder folder);

    void renameFile(Long fileId, String newFileName);

    void deleteFile(Long fileId);
}
