package com.fc.dao;

import com.fc.entity.Student;

import java.util.List;

public interface StudentDao {
    List<Student> findAll();
    Student findById(Integer id);
    List<Student> findByName();
    List<Student> findByAge();
    List<Student> findByGender();
}
