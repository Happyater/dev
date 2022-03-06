package com.fc.controller;

import com.fc.entity.Student;
import com.fc.vo.PageInfo;
import com.fc.service.StudentService;
import com.fc.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/page")
public class PageService extends HttpServlet {
    //表示层依赖了业务逻辑层
    StudentService studentService = new StudentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取前端请求参数
        String parameter = req.getParameter("pageNo");

        //设置pageNo的默认值
        int pageNo = 1;

        //判断pageNo使用默认值,还是前端请求值
        if (parameter != null){
            pageNo = Integer.parseInt(parameter);
        }

        //设置每页显示多少条数据
        int pageSize = 5;

        //获取一个对象pageInfo,里面包含了一些前端的参数
        //当前页 pageNo
        //每页显示多少条 pageSize
        //总数据量 totalCount
        //总页数 pageCount
        //每页显示的内容 list
        PageInfo<Student> pageInfo = studentService.getPageInfo(pageNo, pageSize);

        //设置域对象
        req.setAttribute("pageInfo", pageInfo);

        //发送到前端
        req.getRequestDispatcher("page.jsp").forward(req,resp);

//        //获取前端请求参数
//        String parameter = req.getParameter("pageNo");
//
//        //设置pageNo的默认值
//        int pageNo = 1;
//
//        //判断pageNo使用默认值，还是前端请求值
//        if(parameter != null){
//            pageNo = Integer.parseInt(parameter);
//        }
//
//        //设置每页显示多少条数据
//        int pageSize = 5;
//
//        //获取一个对象pageInfo,里面包含了一些前端的参数
//        //当前页 pageNo
//        //每页显示多少条 pageSize
//        //总数据量 totalCount
//        //总页数 pageCount
//        //每一页显示的内容 list
//        PageInfo<Student> pageInfo = studentService.getPageInfo(pageNo, pageSize);
//
//        //设置域对象
//        req.setAttribute("pageInfo", pageInfo);
//
//        //发送到前端
//        req.getRequestDispatcher("page.jsp").forward(req,resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
