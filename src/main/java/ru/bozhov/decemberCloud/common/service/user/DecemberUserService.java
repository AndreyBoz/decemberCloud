package ru.bozhov.decemberCloud.common.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.bozhov.decemberCloud.auth.model.DecemberUser;


public interface DecemberUserService extends UserDetailsService {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    void createNewUser(DecemberUser person);
    DecemberUser findUserByUsername(String username);
    void updateUser(Long id, String username, String email);
}
