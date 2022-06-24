package com.fc.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fc.dao.UserMapper;
import com.fc.entity.User;
import com.fc.entity.UserExample;
import com.fc.service.UserService;
import com.fc.vo.DateVo;
import com.fc.vo.ResultVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

                resultVo = new ResultVo(200,  "有这个人", true, userDateVo);
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

                resultVo = new ResultVo(200, "用户查询成功", true, userDateVo);
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
            vo = new ResultVo(200, "添加用户成功", true, user);
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

            vo = new ResultVo(200, "修改用户成功", true, user);
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
            vo = new ResultVo(200, "删除用户成功", true, null);
        } else {
            vo = new ResultVo(4400, "删除用户失败", false, null);
        }

        return vo;
    }

    @Override
    public ResultVo login(String username, String password) {
        ResultVo vo = new ResultVo();

        vo.setCode(-1);
        vo.setMessage("登录失败，当前用户名不存在");
        vo.setSuccess(false);
        vo.setData(null);

        UserExample example = new UserExample();

        UserExample.Criteria criteria = example.createCriteria();

        criteria.andUsernameEqualTo(username);

        List<User> users = userMapper.selectByExample(example);

        // 能查出来说明用户名是存在的
        if (users.size() > 0) {
            User user = users.get(0);

            // 如果密码相同
            if (user.getPassword().equals(password)) {
                vo.setSuccess(true);
                vo.setMessage("登录成功！");
                vo.setCode(200);

                // 密码不要传给前端
                user.setPassword(null);

                // 盐值
                String salt = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));

                // 头部
                Map<String, Object> headers = new HashMap<>();

                headers.put("alg", "HS256");
                headers.put("typ", "JWT");

                // 获取token
                String token = JWT.create()
                        .withHeader(headers)
                        // 主题
                        .withSubject("登录权限验证")
                        // 签发人
                        .withIssuer("admin")
                        // 签发日期
                        .withIssuedAt(new Date())
                        // 过期时间
                        // 设置稍微长一点，后面有机会讲token续签的时候再改
                        .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                        .withClaim("id", user.getId())
                        .withClaim("username", username)
                        .withClaim("role", user.getRole())
                        .withClaim("salt", salt)
                        // 使用盐值进行签发生成jwt
                        .sign(Algorithm.HMAC256(salt));

                Map<String, Object> result = new HashMap<>();

                result.put("user", user);
                result.put("token", token);

                vo.setData(result);
            } else {
                vo.setCode(-2);
                vo.setMessage("登录失败，请输入正确的密码");
            }
        }

        return vo;
    }
}
