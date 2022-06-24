package com.fc.service;

import com.fc.vo.UpdateVO;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {

    ModelAndView login(String username,
                       String password,
                       Integer remember,
                       HttpServletResponse response,
                       HttpServletRequest request);

    ModelAndView logout(HttpServletResponse response, HttpServletRequest request);

    ModelAndView userCenter();

    ModelAndView update(UpdateVO vo, MultipartFile img);

    int checkNick(String nick);
}
