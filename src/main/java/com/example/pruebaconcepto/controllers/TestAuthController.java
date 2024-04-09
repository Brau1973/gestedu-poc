package com.example.pruebaconcepto.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@PreAuthorize("denyAll()")
public class TestAuthController {

    @RequestMapping("/hello")
    @PreAuthorize("permitAll()")
    public String hello() {
        return "Hello World!";
    }

    @RequestMapping("/helloSecure")
    @PreAuthorize("hasAuthority('Leer')")
    public String helloSecure() {
        return "Hello World secure!";
    }

    @RequestMapping("/helloSecureProfe")
    @PreAuthorize("hasRole('PROFESOR')")
    public String helloSecureProf() {
        return "Hello World PROFE!";
    }

    @RequestMapping("/helloSecureAdmin")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR')")
    public String helloSecureAdmin() {
        return "Hello World Admin!";
    }
}
