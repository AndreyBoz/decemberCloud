package ru.bozhov.decemberCloud.cloud.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bozhov.decemberCloud.auth.model.DecemberUser;
import ru.bozhov.decemberCloud.cloud.model.Folder;
import ru.bozhov.decemberCloud.cloud.repository.FolderRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class FolderService {
    private FolderRepository folderRepository;

    public List<Folder> getParentFolder(DecemberUser decemberUser) {
        return null;
    }
}
