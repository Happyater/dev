package com.fc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Data
@AllArgsConstructor
@Component
public class User {
    @Value("1")
    private Integer id;
    @Value("13642")
    private String username;
    @Value("123456")
    private String password;

    public User() {
        System.out.println("无参构造");
    }

    //和init-method相同,会在对象创建后执行方法
    @PostConstruct
    public void eat() {
        System.out.println("啊实打实");
    }
    //在容器销毁之前
    @PreDestroy
    public void sleep() {
        System.out.println("跟风模仿");
    }
}
