package com.seenroot.springboot.service;

import com.seenroot.springboot.domain.User;

import java.util.List;

public interface UserService {
    List<User> findByName(String name);

    User findById(Integer id);
}
