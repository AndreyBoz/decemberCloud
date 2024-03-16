package ru.bozhov.decemberCloud.cloud.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.bozhov.decemberCloud.auth.model.DecemberUser;
import ru.bozhov.decemberCloud.auth.repository.UserRepository;
import ru.bozhov.decemberCloud.cloud.service.DecemberFileService;
import ru.bozhov.decemberCloud.cloud.service.FolderService;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class CloudController {

    UserRepository userRepository;
    FolderService folderService;
    DecemberFileService decemberFileService;
    @GetMapping("/cloud")
    public String getCloudPage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            Optional<DecemberUser> user = userRepository.findByUsername(((UserDetails) authentication.getPrincipal()).getUsername());
            if (user.isPresent()) {
                model.addAttribute("user", user.get());
                model.addAttribute("folder", folderService.getParentFolder(user.get()));
                return "cloud/cloud.html";
            }
        }
        return "redirect:/lk";
    }
}
