
package com.serviq;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Welcome to Serviq! Smart Repair and Maintenance Management System is running successfully.";
    }
}
