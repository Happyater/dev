package com.fc.controller;


import com.fc.entity.User;
import com.fc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("getList")
    public Map<String, Object> getList(int pageNum, int pageSize) {
        return userService.getList(pageNum, pageSize);
    }

    @RequestMapping("add")
    public Map<String, Object> add(User user) {
        return userService.add(user);
    }

    @RequestMapping("update")
    public Map<String, Object> update(User user) {
        return userService.update(user);
    }

    @RequestMapping("delete")
    public Map<String, Object> delete(int id) {
        return userService.delete(id);
    }
}
