package com.fc.test;

import com.fc.entity.Student;
import com.fc.entity.Teacher;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Student student = context.getBean("student", Student.class);

        System.out.println(student);

        Teacher teacher = context.getBean("teacher", Teacher.class);

        System.out.println(teacher);
    }
}
