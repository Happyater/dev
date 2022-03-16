package com.fc.test;

import com.fc.controller.UserController;
import com.fc.entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class SpringTest {
    public static void main(String[] args) {
//        UserController userController = new UserController();
//
//        List<User> users = userController.findAll();
//
//        System.out.println(users);

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        UserController userController = context.getBean("userController", UserController.class);

        List<User> users = userController.findAll();

        System.out.println(users);

    }
}
