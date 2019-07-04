package com.seenroot.springboot.mapper;

import com.seenroot.springboot.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    @Select("select * from user where name = #{name}")
    List<User> findByName(String name);
}
