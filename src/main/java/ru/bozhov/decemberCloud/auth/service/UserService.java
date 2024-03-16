package ru.bozhov.decemberCloud.auth.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bozhov.decemberCloud.auth.model.DecemberUser;
import ru.bozhov.decemberCloud.auth.repository.UserRepository;
import ru.bozhov.decemberCloud.cloud.service.DecemberFileService;
import ru.bozhov.decemberCloud.cloud.service.FolderService;

@Service
@AllArgsConstructor
public class UserService {
    UserRepository userRepository;
    FolderService folderService;
    DecemberFileService decemberFileService;
    @Transactional
    public void createNewUser(DecemberUser person) {

    }
}
