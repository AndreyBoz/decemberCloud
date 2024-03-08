package ru.bozhov.decemberCloud.auth.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.bozhov.decemberCloud.auth.model.User;
@Service
public interface UserService extends JpaRepository<User,Long>, UserDetailsService {
}
