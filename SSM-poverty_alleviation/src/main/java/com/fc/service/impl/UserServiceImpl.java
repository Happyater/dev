package com.fc.service.impl;

import com.fc.dao.UserMapper;
import com.fc.entity.User;
import com.fc.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, Object> getList(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);

        List<User> users = userMapper.selectByExample(null);

        PageInfo<User> pageInfo = new PageInfo<>(users);

        Map<String, Object> data = new HashMap<>();

        data.put("total", pageInfo.getTotal());
        data.put("list", pageInfo.getList());
        data.put("pageNum", pageNum);
        data.put("pageSize", pageSize);

        Map<String, Object> map = new HashMap<>();

        map.put("message", "OK");
        map.put("code", 200);
        map.put("success", true);
        map.put("data", data);

        return map;
    }

    @Override
    public Map<String, Object> add(User user) {

        int i = userMapper.insert(user);

        if(i > 0) {
            Map<String, Object> map = new HashMap<>();

            User data = userMapper.selectByPrimaryKey(user.getId());


            map.put("message", "OK");
            map.put("code", 200);
            map.put("success", true);
            map.put("data", data);

            return map;
        }
        return null;
    }

    @Override
    public Map<String, Object> update(User user) {
        int i = userMapper.updateByPrimaryKeySelective(user);

        Map<String, Object> map = new HashMap<>();

        if(i > 0) {

            User data = userMapper.selectByPrimaryKey(user.getId());

            map.put("message", "OK");
            map.put("code", 200);
            map.put("success", true);
            map.put("data", data);

            return map;
        }
        return null;
    }

    @Override
    public Map<String, Object> delete(int id) {

        int i = userMapper.deleteByPrimaryKey((long) id);

        Map<String, Object> map = new HashMap<>();

        if (i > 0) {
            map.put("message", "OK");
            map.put("code", 200);
            map.put("success", true);

            return map;
        }

        return null;
    }
}
