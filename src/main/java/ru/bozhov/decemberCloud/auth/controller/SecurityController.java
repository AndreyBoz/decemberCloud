package ru.bozhov.decemberCloud.auth.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.bozhov.decemberCloud.auth.model.DecemberUser;
import ru.bozhov.decemberCloud.auth.model.Role;
import ru.bozhov.decemberCloud.common.service.user.DecemberUserService;

import java.util.HashSet;
import java.util.Set;


@Controller
public class SecurityController {
    @Autowired
    DecemberUserService decemberUserService;
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
    public String registerUser(@Valid @ModelAttribute("user") DecemberUser user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        if (decemberUserService.existsByUsername(user.getUsername())) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "auth/registration";
        }

        if (decemberUserService.existsByEmail(user.getEmail())) {
            model.addAttribute("emailError", "Пользователь с таким email уже существует");
            return "auth/registration";
        }

        DecemberUser person = new DecemberUser();
        person.setUsername(user.getUsername());
        person.setPassword(new BCryptPasswordEncoder(5).encode(user.getPassword()));
        person.setEmail(user.getEmail());

        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setName("ROLE_USER");
        roles.add(role);
        person.setRoles(roles);

        decemberUserService.createNewUser(person);

        return "redirect:/auth/login";
    }
}
