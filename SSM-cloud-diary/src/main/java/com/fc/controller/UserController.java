package com.fc.controller;

import com.fc.service.UserService;
import com.fc.vo.UpdateVO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("login")
    public ModelAndView login(@RequestParam(value = "username") String username,
                              @RequestParam(value = "password") String password,
                              Integer remember,
                              HttpServletResponse response,
                              HttpServletRequest request) {
        return userService.login(username, password, remember, response, request);
    }

    @GetMapping("logout")
    public ModelAndView logout(HttpServletResponse response,
                               HttpServletRequest request) {
        return userService.logout(response, request);
    }

    @RequestMapping("userCenter")
    public ModelAndView userCenter() {
        return userService.userCenter();
    }

    @PostMapping("update")
    public ModelAndView update(UpdateVO vo, MultipartFile img) {
        return userService.update(vo, img);
    }

    @GetMapping("checkNick")
    public int checkNick(String nick) {
        return userService.checkNick(nick);
    }
}
