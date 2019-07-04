package com.seenroot.springboot.service.impl;

import com.seenroot.springboot.domain.User;
import com.seenroot.springboot.mapper.UserMapper;
import com.seenroot.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    // 注入Mapper接口
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findByName(String name) {
        return userMapper.findByName(name);
    }
}
