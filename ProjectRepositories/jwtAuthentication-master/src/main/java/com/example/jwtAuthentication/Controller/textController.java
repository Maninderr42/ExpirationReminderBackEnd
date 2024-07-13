package com.example.jwtAuthentication.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class textController {

    @GetMapping("/api")
    public String getHTMLPage() {
        return "index"; // This will resolve to index.html in the resources/templates directory
    }
}
