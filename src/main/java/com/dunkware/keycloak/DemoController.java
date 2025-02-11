package com.dunkware.keycloak;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {

    @GetMapping
    @PreAuthorize("hasRole('client_user')")
    public String hello() {
        return "Hello from Spring boot & Keycloak";
    }

    @GetMapping("/hello-2")
    @PreAuthorize("hasRole('client admin')")
    public String hello2() {
        return "Hello from Spring boot & Keycloak - ADMIN";
    }


    @PostMapping()
    @PreAuthorize("hasRole('client admin')")
    public String login() {
        return "Hello from Spring boot & Keycloak - ADMIN";
    }
}
