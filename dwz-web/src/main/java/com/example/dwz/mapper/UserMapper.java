package com.example.dwz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dwz.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    // MyBatis-Plus已经提供了常见的CRUD操作，无需额外编写
}