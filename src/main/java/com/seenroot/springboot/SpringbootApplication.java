package com.seenroot.springboot;

import com.seenroot.springboot.dao.UserDOMapper;
import com.seenroot.springboot.dataobject.UserDO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
// mybatis mapper接口扫描
@MapperScan("com.seenroot.springboot.dao")
@RestController
public class SpringbootApplication {

    @Autowired
    private UserDOMapper userDOMapper;

    @RequestMapping("/user")
    public String home() {
        UserDO userDO = userDOMapper.selectByPrimaryKey(1);
        if (userDO == null) {
            return "用户对象不存在";
        }
        return userDO.getName();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

}
