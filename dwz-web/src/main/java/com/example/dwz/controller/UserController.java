package com.example.dwz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dwz.entity.User;
import com.example.dwz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public Map<String, Object> getUsers(@RequestParam(defaultValue = "1") int pageNum,
                                       @RequestParam(defaultValue = "10") int pageSize) {
        Page<User> page = new Page<>(pageNum, pageSize);
        userService.page(page);
        
        Map<String, Object> result = new HashMap<>();
        result.put("statusCode", 200);
        result.put("message", "操作成功");
        result.put("data", page.getRecords());
        result.put("pageNum", page.getCurrent());
        result.put("pageSize", page.getSize());
        result.put("total", page.getTotal());
        return result;
    }
    
    @GetMapping("/{id}")
    public Map<String, Object> getUser(@PathVariable int id) {
        Map<String, Object> result = new HashMap<>();
        User user = userService.getById(id);
        
        if (user != null) {
            result.put("statusCode", 200);
            result.put("message", "操作成功");
            result.put("data", user);
        } else {
            result.put("statusCode", 404);
            result.put("message", "用户不存在");
        }
        return result;
    }
    
    @PostMapping
    public Map<String, Object> createUser(@RequestBody User user) {
        userService.save(user);
        
        Map<String, Object> result = new HashMap<>();
        result.put("statusCode", 200);
        result.put("message", "用户创建成功");
        result.put("data", user);
        return result;
    }
    
    @PutMapping("/{id}")
    public Map<String, Object> updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        Map<String, Object> result = new HashMap<>();
        
        User user = userService.getById(id);
        if (user != null) {
            updatedUser.setId(id); // 确保ID不被修改
            userService.updateById(updatedUser);
            
            result.put("statusCode", 200);
            result.put("message", "用户更新成功");
            result.put("data", updatedUser);
        } else {
            result.put("statusCode", 404);
            result.put("message", "用户不存在");
        }
        return result;
    }
    
    @DeleteMapping("/{id}")
    public Map<String, Object> deleteUser(@PathVariable int id) {
        Map<String, Object> result = new HashMap<>();
        
        User user = userService.getById(id);
        if (user != null) {
            userService.removeById(id);
            
            result.put("statusCode", 200);
            result.put("message", "用户删除成功");
        } else {
            result.put("statusCode", 404);
            result.put("message", "用户不存在");
        }
        return result;
    }
}