package com.fc.dao;

import com.fc.entity.Student;

import java.util.List;

public interface StudentDao {
    List<Student> findAll();

    List<Student> findByStudent(Student student);

    List<Student> findByStudentWhereTrim(Student student);

    int update(Student student);

    int delete(Integer... id);
}
