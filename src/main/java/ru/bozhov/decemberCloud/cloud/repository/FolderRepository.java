package ru.bozhov.decemberCloud.cloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bozhov.decemberCloud.auth.model.DecemberUser;
import ru.bozhov.decemberCloud.cloud.model.Folder;

import java.util.List;

@Repository
public interface FolderRepository extends JpaRepository<Folder,Long> {
    Folder findFolderById(Long id);
    List<Folder> findFoldersByAccessedUsers(List<DecemberUser> accessedUsers);
    List<Folder> findFoldersByAccessedUsersAndFolderName(List<DecemberUser> accessedUsers, String folderName);
}
