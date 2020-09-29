package com.example.sandbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TilesController {


    @GetMapping("/welcome")
    public String getWelcomePage() {
        return "welcome";
    }

    @GetMapping("/")
    public String index() {
        return "home";
    }

    @GetMapping("/documentation")
    public String getDocumentationPage() {
        return "documentation";
    }
}
