package ru.bozhov.decemberCloud.lk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.bozhov.decemberCloud.auth.model.DecemberUser;
import ru.bozhov.decemberCloud.auth.repository.UserRepository;

import java.util.Optional;

@Controller("/lk")
public class LKController {
    @Autowired
    UserRepository userRepository;
    @GetMapping("/lk")
    public String getLK(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            Optional<DecemberUser> user = userRepository.findByUsername(((UserDetails) authentication.getPrincipal()).getUsername());
            if(user.isPresent()) {
                model.addAttribute("user", user.get());
                return "lk/lk.html";
            }
        }
        return "redirect: auth/registration";
    }
    @GetMapping("/settings")
    public String getSettingsPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            Optional<DecemberUser> user = userRepository.findByUsername(((UserDetails) authentication.getPrincipal()).getUsername());
            if (user.isPresent()) {
                model.addAttribute("user", user.get());
                return "lk/settings";
            }
        }
        return "redirect:/lk";
    }

    @PostMapping("/updateUser/{id}")
    public String editDecemberUser(@PathVariable("id") Long id, @ModelAttribute DecemberUser decemberUser, RedirectAttributes redirectAttributes) {
        userRepository.updateDecemberUser(id,decemberUser.getUsername(),decemberUser.getEmail());
        redirectAttributes.addFlashAttribute("successMessage", "User details updated successfully.");
        return "redirect:/lk";
    }
}
