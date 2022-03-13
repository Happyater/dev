package com.fc.dao;

import com.fc.entity.Student;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentDao {
    @Select("select * from student")
    List<Student> findAll();
}
