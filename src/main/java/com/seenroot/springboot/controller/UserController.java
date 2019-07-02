package com.seenroot.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {

    @PostMapping("/login/account")
    public Map<String, Object> login() {
        System.out.println("login");
        return null;
    }
}
