package com.seenroot.springboot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    @PostMapping("/login/account")
    public Map<String, Object> login() {
        System.out.println("login");
        return null;
    }
}
