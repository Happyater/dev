package com.fc.service.impl;

import com.fc.dao.TbUserMapper;
import com.fc.entity.TbUser;
import com.fc.service.UserService;
import com.fc.vo.ResultInfo;
import com.fc.vo.UpdateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private HttpSession session;

    @Override
    public ModelAndView login(String username, String password, Integer remember,
                              HttpServletResponse response, HttpServletRequest request) {


        List<TbUser> users = tbUserMapper.selectByExample(null);

        ModelAndView modelAndView = new ModelAndView();

        Cookie[] cookies = request.getCookies();

        HttpSession session = request.getSession(true);
        for (TbUser user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {

                session.setAttribute("user", user);

//                System.out.println("----------------");
                modelAndView.setViewName("forward:/index/page");
            }
        }

        if(session.getAttribute("user") == null) {
            ResultInfo resultInfo = new ResultInfo(0, "登录失败，用户名或密码错误", null);

            modelAndView.addObject("resultInfo", resultInfo);

            modelAndView.setViewName("forward:/login.jsp");
        }

        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("JSESSIONID")) {
                if(remember == null) {
                    cookie.setMaxAge(0);
                }
                if(remember != null) {
                    cookie.setMaxAge(60 * 30);
                }
                response.addCookie(cookie);
            }
        }

        return modelAndView;
    }

    @Override
    public ModelAndView logout(HttpServletResponse response, HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        session.invalidate();

        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies) {
            cookie.setMaxAge(0);
        }

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("redirect:/login.jsp");

        return modelAndView;
    }

    @Override
    public ModelAndView userCenter() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("menu_page", "user");
        modelAndView.addObject("changePage", "/user/info.jsp");

        modelAndView.setViewName("forward:/index.jsp");

        return modelAndView;
    }

    @Override
    public ModelAndView update(UpdateVO vo, MultipartFile img) {

        TbUser user = (TbUser) session.getAttribute("user");

        if (img != null) {
            String path = "D:/apache-tomcat-8.5.37-windows-x64/新建文件夹/apache-tomcat-8.5.37/webapps/upload/cloud_diary/head_portrait";

            File pathFile = new File(path);

            if (pathFile.exists()) {
                pathFile.mkdirs();
            }

            String originalFilename = img.getOriginalFilename();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

            String format = simpleDateFormat.format(new Date());

            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

            originalFilename = format + suffix;

            user.setHead("http://localhost:8081/upload/cloud_diary/head_portrait/"+originalFilename);

            try {
                img.transferTo(new File(pathFile, originalFilename));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        System.out.println(vo);

        user.setNick(vo.getNick());

        user.setMood(vo.getMood());

        int i = tbUserMapper.updateByPrimaryKeySelective(user);

        if (i > 0) {
            TbUser user1 = tbUserMapper.selectByPrimaryKey(vo.getId());
            session.setAttribute("user", user1);
        }

        ModelAndView mv = new ModelAndView();

        mv.setViewName("forward:userCenter");
        return mv;
    }

    @Override
    public int checkNick(String nick) {
        String[] nicks = tbUserMapper.selectNick();

        for (String s : nicks) {
            if (s.equals(nick)) {
                return 0;
            }
        }

        return 1;
    }
}
