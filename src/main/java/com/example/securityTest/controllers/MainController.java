package com.example.securityTest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MainController {

    @GetMapping("/")
    public String homePage() {

        return "home";
    }

    @GetMapping("/auth")
    public String pageForAuth(Principal principal) {

        return "Secured part of web services " + principal.getName();
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
