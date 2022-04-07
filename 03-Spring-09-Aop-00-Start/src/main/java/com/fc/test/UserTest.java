package com.fc.test;

import com.fc.dao.impl.UserDaoImpl;

public class UserTest {
    public static void main(String[] args) {
        UserDaoImpl userDao = new UserDaoImpl();

        userDao.findAll();
    }
}
