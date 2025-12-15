package com.example.dwz.controller;

import com.example.dwz.entity.User;
import com.example.dwz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DatabaseTestController {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserService userService;

    @GetMapping("/test/db-connection")
    public Map<String, Object> testConnection() {
        Map<String, Object> result = new HashMap<>();

        try {
            Connection connection = dataSource.getConnection();
            boolean isValid = connection.isValid(5);
            result.put("connected", isValid);
            result.put("message", "数据库连接成功");
            connection.close();
        } catch (SQLException e) {
            result.put("connected", false);
            result.put("message", "数据库连接失败: " + e.getMessage());
        }

        return result;
    }

    @GetMapping("/test/users")
    public Map<String, Object> testUsers() {
        Map<String, Object> result = new HashMap<>();
        try {
            List<User> users = userService.list();
            result.put("success", true);
            result.put("users", users);
            result.put("count", users.size());
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "查询用户失败: " + e.getMessage());
        }
        return result;
    }
}