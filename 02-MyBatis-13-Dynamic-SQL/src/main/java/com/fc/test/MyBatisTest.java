package com.fc.test;

import com.fc.dao.StudentDao;
import com.fc.entity.Student;
import com.fc.util.MyBatisUtil;
import org.junit.Test;

import java.util.List;

public class MyBatisTest {
    @Test
    public void update(){
        StudentDao studentDao = MyBatisUtil.getMapper(StudentDao.class);

        Student student = new Student();

        student.setId(1);
        student.setAge((byte)51);

        int i = studentDao.update(student);

        System.out.println("受影响的行数:" + i);

        MyBatisUtil.commit();
    }


    @Test
    public void testFindByStudent(){
        StudentDao studentDao = MyBatisUtil.getMapper(StudentDao.class);

        Student student = new Student();
//        student.setId(1);
        student.setName("%亚%");


        List<Student> students = studentDao.findByStudent(student);

        for (Student student1 : students) {
            System.out.println(student1);
        }
        MyBatisUtil.commit();
    }


    @Test
    public void testFindAll(){
        StudentDao studentDao = MyBatisUtil.getMapper(StudentDao.class);

        List<Student> students = studentDao.findAll();

        for (Student student : students) {
            System.out.println(student);
        }
        MyBatisUtil.commit();
    }
}
