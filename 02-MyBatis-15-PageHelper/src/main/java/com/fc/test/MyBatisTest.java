package com.fc.test;

import com.fc.dao.StudentDao;
import com.fc.entity.Student;
import com.fc.util.MyBatisUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {
    @Test
    public void test(){
        StudentDao studentDao = MyBatisUtil.getMapper(StudentDao.class);

        PageHelper.startPage(1,2);

        List<Student> students = studentDao.findAll();



//        for (Student student : students) {
//            System.out.println(student);
//        }

        PageInfo<Student> PageInfo = new PageInfo<>(students);

        System.out.println(PageInfo);

        MyBatisUtil.commit();
    }
}
