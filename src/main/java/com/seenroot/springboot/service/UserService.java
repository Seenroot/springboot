package com.seenroot.springboot.service;

import com.seenroot.springboot.domain.User;

public interface UserService {
    User findByName(String name);
}
