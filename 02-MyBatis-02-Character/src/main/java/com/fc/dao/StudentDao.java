package com.fc.dao;

import com.fc.entity.Student;

import java.util.List;

public interface StudentDao {
    List<Student> findByLessThanAge(Integer i);

    List<Student> findGreaterThanAge(Integer i);
}
