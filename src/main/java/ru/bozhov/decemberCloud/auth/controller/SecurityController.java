package ru.bozhov.decemberCloud.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.bozhov.decemberCloud.auth.model.User;

@Controller
public class SecurityController {
    @GetMapping("/welcome")
    public String welcome(){
        return "/welcome";
    }
    @GetMapping("/login")
    public String login(){
        return "/login";
    }
    @GetMapping("/registration")
    public String registration(@ModelAttribute("user") User user) {
        return "/registration";
    }
    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("user") User user){
        registrationService.register(user);
        return "redirect:/login";
    }
}
