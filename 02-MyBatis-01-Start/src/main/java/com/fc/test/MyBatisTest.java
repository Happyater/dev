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
import java.util.List;

public class MyBatisTest {

    @Test
    public void testFindStudentById(){

        try {
            //读取配置文件中的内容到流中
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");


            //创建会话工厂
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            //获取连接
            SqlSession sqlSession = sqlSessionFactory.openSession();

            //通过反射获取接口的实现类
            StudentDao studentDao = sqlSession.getMapper(StudentDao.class);

            Student student = studentDao.findStudentById(2);

            System.out.println(student);

            sqlSession.commit();

            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



//        try {
//            //读取配置文件中的内容到流中
//            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
//
//
//            //创建会话工厂
//            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//
//            //获取连接
//            SqlSession sqlSession = sqlSessionFactory.openSession();
//
//            //通过反射获取接口的实现类
//            StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
//
//            Student student = studentDao.findStudentById(2);
//
//            System.out.println(student);
//
//            sqlSession.commit();
//
//            sqlSession.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        try {
//            //读取配置文件中的内容到流中
//            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
//
//
//            //创建会话工厂
//            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//
//            //获取连接
//            SqlSession sqlSession = sqlSessionFactory.openSession();
//
//            //通过反射获取接口的实现类
//            StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
//
//            Student student = studentDao.findStudentById(2);
//
//            System.out.println(student);
//
//            sqlSession.commit();
//
//            sqlSession.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void testFindAll(){


        try {
            //读取配置文件中的内容到流中
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

            //获取MyBatis核心对象sqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            //通过会话工厂获取连接
            SqlSession sqlSession = sqlSessionFactory.openSession();

            //通过反射获取接口的实现类
            StudentDao studentDao = sqlSession.getMapper(StudentDao.class);

            List<Student> students = studentDao.findAll();

            for (Student student : students) {
                System.out.println(student);
            }

            sqlSession.commit();

            sqlSession.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

//        try {
//            //读取配置文件中的内容到流中
//            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
//
//            //获取MyBatis核心对象sqlSessionFactory
//            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//
//            //通过会话工厂获取连接
//            SqlSession sqlSession = sqlSessionFactory.openSession();
//
//            //通过反射获取接口的实现类
//            StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
//
//            List<Student> students = studentDao.findAll();
//
//            for (Student student : students) {
//                System.out.println(student);
//            }
//
//            sqlSession.commit();
//
//            sqlSession.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        try {
//            //读取配置文件中的内容到流中
//            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
//
//            //获取MyBatis核心对象sqlSessionFactory
//            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//
//            //通过会话工厂获取连接
//            SqlSession sqlSession = sqlSessionFactory.openSession();
//
//            //通过反射获取接口的实现类
//            StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
//
//            List<Student> students = studentDao.findAll();
//
//            for (Student student : students) {
//                System.out.println(student);
//            }
//
//            sqlSession.commit();
//
//            sqlSession.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }


    @Test
    public void testInsertFace(){

        try {
            //读取配置文件中的内容到流中
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

            //获取MyBatis核心对象SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            //通过会话工厂获取连接
            SqlSession sqlSession = sqlSessionFactory.openSession();

            //通过反射获取接口的实现类
            StudentDao studentDao = sqlSession.getMapper(StudentDao.class);

            Student student = studentDao.findById();

            System.out.println(student);

            sqlSession.commit();

            sqlSession.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


//        try {
//            //读取配置文件中的内容到流中
//            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
//
//            //获取MyBatis核心对象SqlSessionFactory
//            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//
//            //通过会话工厂获取连接
//            SqlSession sqlSession = sqlSessionFactory.openSession();
//
//            //通过反射获取接口的实现类
//            StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
//
//            Student student = studentDao.findById();
//
//            System.out.println(student);
//
//            sqlSession.commit();
//
//            sqlSession.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        try {
//            //读取配置文件中的内容到流中
//            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
//
//            //获取MyBatis核心对象SqlSessionFactory
//            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//
//            //通过会话工厂获取连接
//            SqlSession sqlSession = sqlSessionFactory.openSession();
//
//            //通过反射获取接口的实现类
//            StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
//
//            Student student = studentDao.findById();
//
//            System.out.println(student);
//
//            sqlSession.commit();
//
//            sqlSession.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    @Test
    public void testInsert(){

        try {
            //读取配置文件中的内容到流中
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

            //获取MyBatis核心对象sqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            //通过会话工厂获取连接
            SqlSession sqlSession = sqlSessionFactory.openSession();

            //执行sql语句,获得受影响的行数
            int i = sqlSession.insert("StudentMapper.insert");

            //提交事务
            sqlSession.commit();

            //关闭资源
            sqlSession.close();

            System.out.println("受影响的行数:" + i);

        } catch (IOException e) {
            e.printStackTrace();
        }

//        try {
//            //读取配置文件中的内容到流中
//            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
//
//            //获取MyBatis核心对象sqlSessionFactory
//            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//
//            //通过会话工厂获取连接
//            SqlSession sqlSession = sqlSessionFactory.openSession();
//
//            //执行sql语句,获得受影响的行数
//            int i = sqlSession.insert("StudentMapper.insert");
//
//            //提交事务
//            sqlSession.commit();
//
//            //关闭资源
//            sqlSession.close();
//
//            System.out.println("受影响的行数:" + i);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        try {
//            //读取配置文件中的内容到流中
//            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
//
//            //获取MyBatis核心对象sqlSessionFactory
//            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//
//            //通过会话工厂获取连接
//            SqlSession sqlSession = sqlSessionFactory.openSession();
//
//            //执行sql语句,获得受影响的行数
//            int i = sqlSession.insert("StudentMapper.insert");
//
//            //提交事务
//            sqlSession.commit();
//
//            //关闭资源
//            sqlSession.close();
//
//            System.out.println("受影响的行数:" + i);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }



    @Test
    public void test(){

        try {
            //读取配置文件中的内容到流中
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

            //获取MyBatis核心对象sqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            //通过会话工厂获取连接
            SqlSession sqlSession = sqlSessionFactory.openSession();

            Object o = sqlSession.selectOne("StudentMapper.select");

            System.out.println(o);

        } catch (IOException e) {
            e.printStackTrace();
        }


//        try {
//            //读取配置文件中的内容到流中
//            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
//
//            //获取MyBatis核心对象sqlSessionFactory
//            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//            //通过会话工厂获取连接
//            SqlSession sqlSession = sqlSessionFactory.openSession();
//
//            Object o = sqlSession.selectOne("StudentMapper.select");
//
//            System.out.println(o);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        try {
//            //读取配置文件中的内容到流中
//            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
//
//            //获取MyBatis核心类对象sqlSessionFactory
//            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//
//            //通过会话工厂获取连接
//            SqlSession sqlSession = sqlSessionFactory.openSession();
//
//            Object o = sqlSession.selectOne("StudentMapper.select");
//
//            System.out.println(o);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
