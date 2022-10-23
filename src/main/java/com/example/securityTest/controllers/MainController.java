package com.example.securityTest.controllers;

import com.example.securityTest.entities.User;
import com.example.securityTest.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;

    @GetMapping("/")
    public String homePage() {

        return "home";
    }

    @GetMapping("/auth")
    public String pageForAuth(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return "Secured part of web services " + user.getUsername() + " " + user.getEmail();
    }

    @GetMapping("/read_profile")
    public String pageForReadProfile() {
        return "Read profile page";
    }
    @GetMapping("/only_for_admins")
    public String pageForAdmins() {
        return "For admins";
    }
}
