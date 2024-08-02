package ru.bozhov.decemberCloud.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeRedirectController {

    @GetMapping("/")
    public String redirectToWelcome() {
        return "redirect:/welcome";
    }
}