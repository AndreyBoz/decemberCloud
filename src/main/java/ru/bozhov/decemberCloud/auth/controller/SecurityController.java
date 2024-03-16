package ru.bozhov.decemberCloud.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.bozhov.decemberCloud.auth.model.DecemberUser;
import ru.bozhov.decemberCloud.auth.model.Role;
import ru.bozhov.decemberCloud.auth.service.UserService;

import java.util.HashSet;
import java.util.Set;


@Controller
public class SecurityController {
    @Autowired
    UserService userService;
    @GetMapping("/welcome")
    public String welcome(){
        return "/welcome";
    }
    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }
    @GetMapping("/registration")
    public String registration(@ModelAttribute("user") DecemberUser user) {
        return "auth/registration";
    }
    @PostMapping("/registration")
    public String registrationUser(@ModelAttribute("user") DecemberUser user){
        DecemberUser person = new DecemberUser();
        person.setUsername(user.getUsername());
        person.setPassword(new BCryptPasswordEncoder(5).encode(user.getPassword()));
        person.setEmail(user.getEmail());

        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setName("ROLE_USER");
        roles.add(role);
        person.setRoles(roles);

        userService.createNewUser(person);

        return "redirect: auth/login";
    }
}
