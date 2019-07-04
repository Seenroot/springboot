package com.seenroot.springboot.controller;

import com.seenroot.springboot.domain.User;
import com.seenroot.springboot.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
// @RequestMapping("/api")
public class UserController {

    @PostMapping("/login/account")
    @ResponseBody
    public Map<String, Object> login() {
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("status", "ok");
        userInfo.put("currentAuthority", "admin");
        return userInfo;
    }

    /**
     * 登录逻辑处理
     */
    @PostMapping("/login")
    public String userLogin(String name, String password, Model model) {

        /**
         * 使用Shiro编写认证操作
         */
        // 1. 获取Subject
        Subject subject = SecurityUtils.getSubject();

        // 2. 封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);

        // 3. 执行登录方法
        try {
            // 无异常，表示 登录成功
            subject.login(token);
            // 跳转到test.html
            return "redirect:/testThymeleaf";
        } catch (UnknownAccountException e) {
            // 异常，表示 登录失败
            // e.printStackTrace();
            model.addAttribute("msg", "用户名不存在");
            // return "redirect:/toLogin"; // 表示请求这个路径，但是重定向model带不过去，直接使用login
            return "login";
        } catch (IncorrectCredentialsException e) {
            // 异常，表示 登录失败
            // e.printStackTrace();
            model.addAttribute("msg", "密码错误");
            return "login";
        }
    }
}
