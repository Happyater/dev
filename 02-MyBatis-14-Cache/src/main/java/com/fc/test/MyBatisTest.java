package com.fc.test;

import com.fc.dao.StudentDao;
import com.fc.entity.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisTest {

    @Test
    public void test() {
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            SqlSession sqlSession = sqlSessionFactory.openSession();

            StudentDao studentDao = sqlSession.getMapper(StudentDao.class);

            System.out.println("第一次查询");

            studentDao.findById(1);

            sqlSession.commit();

            System.out.println("第二次查询");

            studentDao.findById(1);

            Student student = new Student();
            student.setId(1);
            student.setAge((byte) 51);
            studentDao.update(student);


            sqlSession.commit();

            System.out.println("第三次查询");

            studentDao.findById(1);


            sqlSession.commit();

            System.out.println("第四次查询");

            studentDao.findById(1);



            sqlSession.commit();

            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testClearCache() {
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            SqlSession sqlSession = sqlSessionFactory.openSession();

            StudentDao studentDao = sqlSession.getMapper(StudentDao.class);

            Student student = studentDao.findById(1);

            Student student2 = new Student();
            student2.setId(1);
            student2.setAge((byte) 52);
            int i = studentDao.update(student2);

            Student student1 = studentDao.findById(1);

            System.out.println(student == student1);

            sqlSession.commit();

            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void test0ne() {
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            SqlSession sqlSession = sqlSessionFactory.openSession();
            SqlSession sqlSession1 = sqlSessionFactory.openSession();

            StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
            StudentDao studentDao1 = sqlSession1.getMapper(StudentDao.class);

            Student student = studentDao.findById(1);

//            sqlSession.commit();

            Student student1 = studentDao1.findById(1);

            System.out.println(student == student1);

            sqlSession.commit();

            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
