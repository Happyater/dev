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
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MyBatisTest {

    @Test
    public void insertStudent(){
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            SqlSession sqlSession = sqlSessionFactory.openSession();

            StudentDao studentDao = sqlSession.getMapper(StudentDao.class);

            Student student = new Student();
            student.setName("烬");
            student.setAge((byte)30);
            student.setGender("男");
            student.setBirthday(new Date());
            student.setInfo("我在杀戮中绽放,亦如黎明中的花朵");

            int i = studentDao.insertStudent(student);

            System.out.println("受影响的行数:" + i);

            sqlSession.commit();

            sqlSession.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void insert(){
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            SqlSession sqlSession = sqlSessionFactory.openSession();

            StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
            HashMap<String, Object> map = new HashMap<>();
            map.put("name", "艾瑞利亚");
            map.put("age", 27);
            map.put("gender", "女");
            map.put("birthday", new Date());
            map.put("info", "为出生之土而战");

            int i = studentDao.insert(map);

            System.out.println("受影响的行数:" + i);

            sqlSession.commit();

            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void testFindByGenderAndName(){
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            SqlSession sqlSession = sqlSessionFactory.openSession();

            StudentDao studentDao = sqlSession.getMapper(StudentDao.class);

            Student student = studentDao.findByGenderAndName("男", "拉亚斯特");

            System.out.println(student);

            sqlSession.commit();

            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






    @Test
    public void testFindByAgeAndName(){
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            SqlSession sqlSession = sqlSessionFactory.openSession();

            StudentDao studentDao = sqlSession.getMapper(StudentDao.class);

            Student student = studentDao.findByAgeAndName((byte) 52, "亚托克斯");

            System.out.println(student);

            sqlSession.commit();

            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void testFindByIdAndName(){
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            SqlSession sqlSession = sqlSessionFactory.openSession();

            StudentDao studentDao = sqlSession.getMapper(StudentDao.class);

            Student student = studentDao.findByIdAndName(1, "亚托克斯");

            System.out.println(student);

            sqlSession.commit();

            sqlSession.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
