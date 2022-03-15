package com.fc.test;

import com.fc.entity.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
    @Test
    public void testSingleton(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Student student1 = context.getBean("student", Student.class);
        Student student2 = context.getBean("student", Student.class);


        System.out.println(student1);
        System.out.println(student2);

        System.out.println(student1 == student2);
    }


    @Test
    public void testPrototype(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Student student1 = context.getBean("student2", Student.class);
        Student student2 = context.getBean("student2", Student.class);


        System.out.println(student1);
        System.out.println(student2);

        System.out.println(student1 == student2);
    }
}
