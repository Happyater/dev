package com.fc.service;

import com.fc.entity.User;

import java.util.Map;

public interface UserService {
    Map<String, Object> getList(int pageNum, int pageSize);

    Map<String, Object> add(User user);

    Map<String, Object> update(User user);

    Map<String, Object> delete(int id);
}
