package com.mandatesystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        // Returns the name of a view (e.g., index.html) located in src/main/resources/templates
        return "index";
    }
}
