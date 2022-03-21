package com.fc.test;

import com.fc.dao.impl.JDBCTemplateDaoImpl;
import com.fc.entity.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class JdbcTemplateTest {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        JDBCTemplateDaoImpl jdbcTemplate = context.getBean(JDBCTemplateDaoImpl.class);

        List<User> users = jdbcTemplate.findAll();

        System.out.println(users);
    }
}
