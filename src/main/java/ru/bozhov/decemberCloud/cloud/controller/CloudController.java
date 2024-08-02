package ru.bozhov.decemberCloud.cloud.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.bozhov.decemberCloud.auth.model.DecemberUser;
import ru.bozhov.decemberCloud.cloud.model.Folder;
import ru.bozhov.decemberCloud.common.repository.UserRepository;
import ru.bozhov.decemberCloud.common.utils.HibernateUtil;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Controller
public class CloudController {

    private final UserRepository userRepository;

    @GetMapping("/cloud")
    public String getCloudPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            Optional<DecemberUser> user = userRepository.findByUsername(((UserDetails) authentication.getPrincipal()).getUsername());
            if (user.isPresent()) {
                try (Session s = HibernateUtil.getSession()) {
                    s.beginTransaction();
                    List<Folder> folders = s.createQuery("SELECT f FROM Folder f JOIN f.accessedUsers u WHERE u = :user", Folder.class)
                            .setParameter("user", user.get())
                            .getResultList();

                    for (Folder folder : folders) {
                        Hibernate.initialize(folder.getDecemberFiles());
                    }

                    model.addAttribute("user", user.get());
                    model.addAttribute("folders", folders);
                    s.getTransaction().commit();
                } catch (Exception e) {
                    log.error("", e);
                    return "redirect:/account";
                }

                return "cloud/cloud.html";
            }
        }
        return "redirect:/account";
    }
}
