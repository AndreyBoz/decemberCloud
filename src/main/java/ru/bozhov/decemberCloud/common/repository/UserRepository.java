package ru.bozhov.decemberCloud.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.bozhov.decemberCloud.auth.model.DecemberUser;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<DecemberUser,Long> {
    Optional<DecemberUser> findByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<DecemberUser> findByUsernameOrEmail(String username, String email);

    boolean existsByUsername(String username);

    @Modifying
    @Query("UPDATE DecemberUser u SET u.username = :username, u.email = :email WHERE u.id = :id")
    void updateDecemberUser(Long id, String username, String email);
}
