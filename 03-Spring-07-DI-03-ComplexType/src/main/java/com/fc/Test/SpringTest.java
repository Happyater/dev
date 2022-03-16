package com.fc.Test;

import com.fc.entity.ComplexType;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        ComplexType complexType = context.getBean("complexType", ComplexType.class);

        System.out.println(complexType);

    }
}
