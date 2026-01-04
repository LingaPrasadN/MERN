package com.t4.app_backend.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/dashboard")
public class Dashboard {

    @GetMapping
    public String getDashboard() {
        return new String("Welcome to the Dashboard!");
    }

}
