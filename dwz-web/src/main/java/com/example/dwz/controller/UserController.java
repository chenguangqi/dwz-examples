package com.example.dwz.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private List<Map<String, Object>> users = new ArrayList<>();
    
    public UserController() {
        // 初始化一些示例数据
        Map<String, Object> user1 = new HashMap<>();
        user1.put("id", 1);
        user1.put("name", "张三");
        user1.put("email", "zhangsan@example.com");
        user1.put("department", "技术部");
        users.add(user1);
        
        Map<String, Object> user2 = new HashMap<>();
        user2.put("id", 2);
        user2.put("name", "李四");
        user2.put("email", "lisi@example.com");
        user2.put("department", "市场部");
        users.add(user2);
        
        Map<String, Object> user3 = new HashMap<>();
        user3.put("id", 3);
        user3.put("name", "王五");
        user3.put("email", "wangwu@example.com");
        user3.put("department", "人事部");
        users.add(user3);
    }
    
    @GetMapping
    public Map<String, Object> getUsers(@RequestParam(defaultValue = "1") int pageNum,
                                       @RequestParam(defaultValue = "10") int pageSize) {
        Map<String, Object> result = new HashMap<>();
        result.put("statusCode", 200);
        result.put("message", "操作成功");
        result.put("data", users);
        result.put("pageNum", pageNum);
        result.put("pageSize", pageSize);
        result.put("total", users.size());
        return result;
    }
    
    @GetMapping("/{id}")
    public Map<String, Object> getUser(@PathVariable int id) {
        Map<String, Object> result = new HashMap<>();
        
        for (Map<String, Object> user : users) {
            if ((Integer) user.get("id") == id) {
                result.put("statusCode", 200);
                result.put("message", "操作成功");
                result.put("data", user);
                return result;
            }
        }
        
        result.put("statusCode", 404);
        result.put("message", "用户不存在");
        return result;
    }
    
    @PostMapping
    public Map<String, Object> createUser(@RequestBody Map<String, Object> user) {
        // 设置新用户的ID
        int maxId = 0;
        for (Map<String, Object> u : users) {
            int id = (Integer) u.get("id");
            if (id > maxId) {
                maxId = id;
            }
        }
        user.put("id", maxId + 1);
        users.add(user);
        
        Map<String, Object> result = new HashMap<>();
        result.put("statusCode", 200);
        result.put("message", "用户创建成功");
        result.put("data", user);
        return result;
    }
    
    @PutMapping("/{id}")
    public Map<String, Object> updateUser(@PathVariable int id, @RequestBody Map<String, Object> updatedUser) {
        Map<String, Object> result = new HashMap<>();
        
        for (int i = 0; i < users.size(); i++) {
            Map<String, Object> user = users.get(i);
            if ((Integer) user.get("id") == id) {
                updatedUser.put("id", id); // 确保ID不被修改
                users.set(i, updatedUser);
                
                result.put("statusCode", 200);
                result.put("message", "用户更新成功");
                result.put("data", updatedUser);
                return result;
            }
        }
        
        result.put("statusCode", 404);
        result.put("message", "用户不存在");
        return result;
    }
    
    @DeleteMapping("/{id}")
    public Map<String, Object> deleteUser(@PathVariable int id) {
        Map<String, Object> result = new HashMap<>();
        
        for (int i = 0; i < users.size(); i++) {
            Map<String, Object> user = users.get(i);
            if ((Integer) user.get("id") == id) {
                users.remove(i);
                
                result.put("statusCode", 200);
                result.put("message", "用户删除成功");
                return result;
            }
        }
        
        result.put("statusCode", 404);
        result.put("message", "用户不存在");
        return result;
    }
}