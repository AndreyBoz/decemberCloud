package ru.bozhov.decemberCloud.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.bozhov.decemberCloud.auth.model.DecemberUser;
import ru.bozhov.decemberCloud.common.service.user.DecemberUserService;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    DecemberUserService decemberUserService;
    @GetMapping
    public String account(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            DecemberUser user = decemberUserService.findUserByUsername(((UserDetails) authentication.getPrincipal()).getUsername());
            model.addAttribute("user", user);
            return "account/account.html";
        }
        return "redirect:/auth/registration";
    }

    @GetMapping("/settings")
    public String settings(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            DecemberUser user = decemberUserService.findUserByUsername(((UserDetails) authentication.getPrincipal()).getUsername());
            model.addAttribute("user", user);
            return "account/settings";

        }
        return "redirect:/account";
    }

    @PostMapping("/updateUser/{id}")
    public String edit(@PathVariable("id") Long id, @ModelAttribute DecemberUser decemberUser, RedirectAttributes redirectAttributes) {
        decemberUserService.updateUser(id, decemberUser.getUsername(), decemberUser.getEmail());
        redirectAttributes.addFlashAttribute("successMessage", "User details updated successfully.");
        return "redirect:/account";
    }
}
