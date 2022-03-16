package com.fc.test;

import com.fc.controller.UserController;
import com.fc.entity.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class SpringTest {
    @Test
    public void test() {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        UserController userController = context.getBean("userController", UserController.class);

        System.out.println(userController.findAll());

    }

    @Test
    public void testUser(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        User user = context.getBean(User.class);

        System.out.println(user);
    }

    @Test
    public void testPostConstruct(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void testPreDestroy(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        context.close();
    }
}
