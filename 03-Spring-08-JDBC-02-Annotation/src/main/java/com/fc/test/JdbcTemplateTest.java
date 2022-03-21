package com.fc.test;

import com.fc.config.JdbcConfig;
import com.fc.dao.impl.JDBCTemplateDaoImpl;
import com.fc.entity.User;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class JdbcTemplateTest {
    @Test
    public void test(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfig.class);

        JDBCTemplateDaoImpl jdbcTemplateDao = context.getBean(JDBCTemplateDaoImpl.class);

        List<User> users = jdbcTemplateDao.findAll();

        for (User user : users) {
            System.out.println(user);
        }

        context.close();
    }
}
