package ru.bozhov.decemberCloud.cloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bozhov.decemberCloud.cloud.model.Folder;
@Repository
public interface FolderRepository extends JpaRepository<Folder,Long> {
}
