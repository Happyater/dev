package com.fc.service.impl;

import com.fc.dao.UserMapper;
import com.fc.entity.User;
import com.fc.service.UserService;
import com.fc.vo.DateVo;
import com.fc.vo.ResultVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public ResultVo getList(Integer pageNum, Integer pageSize, Long id) {
        ResultVo resultVo;

        DateVo<User> userDateVo;

        List<User> users;

        if(id != null) {
            users = new ArrayList<>();

            User user = userMapper.selectByPrimaryKey(id);

            if(user == null) {
                userDateVo = new DateVo<>(0L, users, pageNum, pageSize);

                resultVo = new ResultVo(4000, "查无此人", false, userDateVo);
            } else {
                users.add(user);

                userDateVo = new DateVo<>(1L, users, pageNum, pageSize);

                resultVo = new ResultVo(1000,  "有这个人", true, userDateVo);
            }
        } else {
            PageHelper.startPage(pageNum, pageSize);

            users = userMapper.selectByExample(null);

            if(users.size() == 0) {
                userDateVo = new DateVo<>(0L, users, pageNum, pageSize);

                resultVo = new ResultVo(4100, "没有用户", false, userDateVo);

            } else {
                PageInfo<User> pageInfo = new PageInfo<>(users);

                userDateVo = new DateVo<>(pageInfo.getTotal(), users, pageNum, pageSize);

                resultVo = new ResultVo(1100, "用户查询成功", true, userDateVo);
            }
        }
        return resultVo;
    }

    @Override
    public ResultVo add(User user) {
        ResultVo vo;

        if(user.getCreateTime() == null) {
            user.setCreateTime(new Date());
        }

        int i = userMapper.insertSelective(user);

        if (i > 0) {
            vo = new ResultVo(1200, "添加用户成功", true, user);
        } else {
            vo = new ResultVo(4200, "添加用户失败", false, null);
        }
        return vo;
    }

    @Override
    public ResultVo update(User user) {
        ResultVo vo;

        int i = userMapper.updateByPrimaryKeySelective(user);

        if(i > 0) {
            user = userMapper.selectByPrimaryKey(user.getId());

            vo = new ResultVo(1300, "修改用户成功", true, user);
        } else {
            vo = new ResultVo(4300, "修改用户失败", false, null);
        }

        return vo;
    }

    @Override
    public ResultVo delete(Long id) {
        ResultVo vo;

        int i = userMapper.deleteByPrimaryKey(id);

        if(id > 0) {
            vo = new ResultVo(1400, "删除用户成功", true, null);
        } else {
            vo = new ResultVo(4400, "删除用户失败", false, null);
        }

        return vo;
    }
}
