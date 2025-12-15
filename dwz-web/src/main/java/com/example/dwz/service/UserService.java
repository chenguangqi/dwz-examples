package com.example.dwz.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dwz.entity.User;
import com.example.dwz.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    // 继承ServiceImpl已经提供了常见的CRUD操作
    // 可以在这里添加自定义的业务逻辑方法
}