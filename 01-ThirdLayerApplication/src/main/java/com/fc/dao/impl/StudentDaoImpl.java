package com.fc.dao.impl;

import com.fc.dao.StudentDao;
import com.fc.entity.Student;
import com.fc.util.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    QueryRunner queryRunner = new QueryRunner();

    Connection connection = DruidUtil.getConnection();

    //获取数据总量
    @Override
    public int getTotalCount() {

        String sql = "select * from student";

        List<Student> list = null;

        try {
             list = queryRunner.query(connection, sql, new BeanListHandler<>(Student.class));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        if(list != null){
            return list.size();
        }

        return 0;

//        String sql = "select * from student";
//
//        List<Student> list = null;
//
//        try {
//            list = queryRunner.query(connection, sql, new BeanListHandler<>(Student.class));
//        } catch (SQLException throwable) {
//            throwable.printStackTrace();
//        }
//
//        if(list != null){
//            return list.size();
//        }
//
//        return 0;


//        String sql = "select * from student";
//
//        List<Student> list = null;
//
//        try {
//            list = queryRunner.query(connection, sql, new BeanListHandler<>(Student.class));
//        } catch (SQLException throwable) {
//            throwable.printStackTrace();
//        }
//
//        if(list != null){
//            return list.size();
//        }
//
//        return 0;
    }

    //获取每页显示内容
    @Override
    public List<Student> getList(int pageNo, int pageSize) {

        String sql = "select * from student limit ?,?";

        List<Student> list = null;

        //计算开始行数
        int start = (pageNo - 1) * pageSize;


        try {
            list = queryRunner.query(connection, sql, new BeanListHandler<>(Student.class), start, pageSize);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return list;

//        String sql = "select * from student limit ?,?";
//
//        List<Student> list = null;
//
//        //计算开始行数
//        int start = (pageNo - 1) * pageSize;
//
//        try {
//            list = queryRunner.query(connection, sql, new BeanListHandler<>(Student.class), start, pageSize);
//        } catch (SQLException throwable) {
//            throwable.printStackTrace();
//        }
//
//        return list;

//        String sql = "select * from student limit ?,?";
//
//        List<Student> list = null;
//
//        //计算开始行数
//        int start = (pageNo - 1) * pageSize;
//
//        try {
//            list = queryRunner.query(connection, sql, new BeanListHandler<>(Student.class), start, pageSize);
//        } catch (SQLException throwable) {
//            throwable.printStackTrace();
//        }
//
//        return list;
    }

}
