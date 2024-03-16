package ru.bozhov.decemberCloud.cloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bozhov.decemberCloud.cloud.model.DecemberFile;
@Repository
public interface DecemberFileRepository extends JpaRepository<DecemberFile,Long> {
}
