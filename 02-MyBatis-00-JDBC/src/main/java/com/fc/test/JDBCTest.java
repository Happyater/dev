package com.fc.test;

import com.fc.entity.Student;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;

public class JDBCTest {

    @Test
    public void testSelect(){

        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");

            //准备参数
            String url = "jdbc:mysql://localhost:3306/rx2002?useSSL=false&characterEncoding=UTF8";
            String username = "root";
            String password = "15239235191";
            String sql = "select * from student";

            //获取链接
            Connection connection = DriverManager.getConnection(url, username, password);

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //执行sql语句获取结果集
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Student> students = new ArrayList<>();
            while (resultSet.next()){
                Student student = new Student();
                student.setId(resultSet.getInt(1));
                student.setName(resultSet.getString(2));
                student.setAge(resultSet.getByte(3));
                student.setGender(resultSet.getString(4));
                student.setInfo(resultSet.getString(5));
                students.add(student);
            }

            for (Student student : students) {
                System.out.println(student);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }





//        try {
//            //加载驱动
//            Class.forName("com.mysql.jdbc.Driver");
//
//            //准备参数
//            String url = "jdbc:mysql://localhost:3306/rx2002?useSSL=false&characterEncoding=UTF8";
//            String username = "root";
//            String password = "15239235191";
//            String sql = "select * from student";
//
//            //获取链接
//            Connection connection = DriverManager.getConnection(url, username, password);
//
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//
//            //执行sql语句获取结果集
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            ArrayList<Student> students = new ArrayList<>();
//            while (resultSet.next()){
//                Student student = new Student();
//                student.setId(resultSet.getInt(1));
//                student.setName(resultSet.getString(2));
//                student.setAge(resultSet.getByte(3));
//                student.setGender(resultSet.getString(4));
//                student.setInfo(resultSet.getString(5));
//                students.add(student);
//            }
//
//            for (Student student : students) {
//                System.out.println(student);
//            }
//
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }






//        try {
//            //加载驱动
//            Class.forName("com.mysql.jdbc.Driver");
//
//            //准备参数
//            String url = "jdbc:mysql://localhost:3306/rx2002?useSSL=false&characterEncoding=UTF8";
//            String username = "root";
//            String password = "15239235191";
//            String sql = "select * from student";
//
//            //获取链接
//            Connection connection = DriverManager.getConnection(url, username, password);
//
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//
//            //执行sql语句获取结果集
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            ArrayList<Student> students = new ArrayList<>();
//            while (resultSet.next()){
//                Student student = new Student();
//                student.setId(resultSet.getInt(1));
//                student.setName(resultSet.getString(2));
//                student.setAge(resultSet.getByte(3));
//                student.setGender(resultSet.getString(4));
//                student.setInfo(resultSet.getString(5));
//                students.add(student);
//            }
//
//            for (Student student : students) {
//                System.out.println(student);
//            }
//
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
    }








    @Test
    public void testAdd(){

        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");

            //准备参数
            String url = "jdbc:mysql://localhost:3306/rx2002?useSSL=false&characterEncoding=UTF8";
            String username = "root";
            String password = "15239235191";
            String sql = "insert into student(name, age, gender, info) value (?,?,?,?)";

            //获取链接
            Connection connection = DriverManager.getConnection(url, username , password);

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,"房价高");
            preparedStatement.setInt(2, 6);
            preparedStatement.setString(3, "男");
            preparedStatement.setString(4,"多方面考虑的三分纪录时刻");

            //执行sql语句获取受影响的行数
            int i = preparedStatement.executeUpdate();

            System.out.println("受影响的行数:" + i);


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


//        try {
//
//            //加载驱动
//            Class.forName("com.mysql.jdbc.Driver");
//
//            //准备参数
//            String url = "jdbc:mysql://localhost:3306/rx2002?useSSL=false&characterEncoding=UTF8";
//            String username = "root";
//            String password = "15239235191";
//            String sql = "insert into student(name, age, gender, info) value (?,?,?,?)";
//
//            Connection connection = DriverManager.getConnection(url);
//
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//
//            preparedStatement.setString(1,"啊发发");
//            preparedStatement.setInt(2,6);
//            preparedStatement.setString(3,"男");
//            preparedStatement.setString(4,"啊大哥给个");
//
//            //执行sql语句获取受影响的行数
//            int i = preparedStatement.executeUpdate();
//
//            System.out.println("受影响的行数:" + i);
//
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }


//        try {
//            //加载驱动
//            Class.forName("com.mysql.jdbc.Driver");
//
//            //准备参数
//            String url = "jdbc:mysql://localhost:3306/rx2002?useSSL=false&characterEncoding=UTF8";
//            String username = "root";
//            String password = "15239235191";
//            String sql = "insert into student(name, age, gender, info) value (?,?,?,?)";
//
//            //获取链接
//            Connection connection = DriverManager.getConnection(url, username, password);
//
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//
//            preparedStatement.setString(1,"了空间的");
//            preparedStatement.setInt(2,5);
//            preparedStatement.setString(3,"男");
//            preparedStatement.setString(4,"阿发");
//
//            //执行sql语句获取受影响的行数
//            int i = preparedStatement.executeUpdate();
//
//
//            System.out.println("受影响的行数:" + i);
//
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
    }

}
