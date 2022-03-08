package com.fc.dao;

import com.fc.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StudentDao {
    int insert(Student student);

    int delete(Integer id);

    int update(Student student);

    List<Student> findAll();

    Student findById(Integer id);

    List<Student> findByKeyword(String keyword);

    // 主键回填
    int getIncrement(Student student);
}
