package ru.bozhov.decemberCloud.common.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.bozhov.decemberCloud.auth.model.DecemberUser;
import ru.bozhov.decemberCloud.common.repository.UserRepository;
import ru.bozhov.decemberCloud.common.service.user.DecemberUserService;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DecemberUserImpl implements DecemberUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void createNewUser(DecemberUser person) {
            userRepository.save(person);
    }

    @Override
    public DecemberUser findUserByUsername(String username) {
        if(username.isBlank() || username.isEmpty()){
            log.error("Username is null");
            return null;
        }

        Optional<DecemberUser> userOptional = userRepository.findByUsername(username);

        if(userOptional.isPresent()){
            log.error("User didn't found");
        }

        return userOptional.get();
    }

    @Transactional
    @Override
    public void updateUser(Long id, String username, String email) {
        if(id==null){
            log.error("Id is null");
            return;
        }
        Optional<DecemberUser> user = userRepository.findById(id);
        if(user.isPresent()){
            DecemberUser decemberUser = user.get();
            decemberUser.setUsername(username);
            decemberUser.setEmail(email);
            return;
        }
        log.error("Error update user, id: {}, username: {}, email: {}", id,username,email);
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        DecemberUser user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not exists by Username or Email"));

        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());

        return new User(
                usernameOrEmail,
                user.getPassword(),
                authorities
        );
    }
}
