package com.seenroot.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello springboot";
    }

    /**
     * 测试thymeleaf
     *
     * @return
     */
    @GetMapping("/testThymeleaf")
    public String testThymeleaf(Model model) {
        model.addAttribute("name", "springboot整合thymeleaf");
        return "test";
    }

    @GetMapping("/add")
    public String add() {
        return "/user/add";
    }

    @GetMapping("/update")
    public String update() {
        return "/user/update";
    }

    @GetMapping("/toLogin")
    public String toLogin() {
        return "/login";
    }
}
