package ru.bozhov.decemberCloud.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bozhov.decemberCloud.auth.model.DecemberUser;
import ru.bozhov.decemberCloud.common.service.user.DecemberUserService;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    DecemberUserService decemberUserService;
    @GetMapping("/panel")
    public String adminPanel(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            DecemberUser user = decemberUserService.findUserByUsername(((UserDetails) authentication.getPrincipal()).getUsername());
            model.addAttribute("user", user);
            return "admin/panel.html";
        }
        return "redirect:/account";
    }
}
