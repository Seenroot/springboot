package com.seenroot.springboot.mapper;

import com.seenroot.springboot.domain.User;

public interface UserMapper {
    User findByName(String name);
}
