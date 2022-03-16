package com.fc.dao.impl;

import com.fc.dao.UserDao;
import com.fc.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoMySQLImpl implements UserDao {
    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        list.add(new User(1, "123", "123456"));
        list.add(new User(2, "456", "123456"));
        list.add(new User(3, "789", "123456"));
        return list;
    }
}
