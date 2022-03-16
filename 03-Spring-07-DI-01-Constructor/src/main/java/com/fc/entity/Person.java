package com.fc.entity;

public class Person {
    private String name;
    private Byte age;
    private Car car;

    public Person(String name, Byte age, Car car) {
        this.name = name;
        this.age = age;
        this.car = car;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", car=" + car +
                '}';
    }
}
