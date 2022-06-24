package com.fc.service.impl;

import com.fc.dao.TbNoteMapper;
import com.fc.entity.TbNote;
import com.fc.entity.TbUser;
import com.fc.service.IndexService;
import com.fc.vo.NoteVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    private TbNoteMapper noteMapper;

    @Autowired
    private HttpSession session;

    @Override
    public ModelAndView page(Integer id, String title, String date, int pageNum, int pageSize, HttpSession session) {
        TbUser user = (TbUser) session.getAttribute("user");

        int userId = user.getId();

        PageHelper.startPage(pageNum, pageSize);

        ModelAndView modelAndView = new ModelAndView();

        if (title != null && title != "") {
            title = "%" + title + "%";
        }

        List<TbNote> notes = noteMapper.selectNote(id, userId, title, date);

        PageInfo<TbNote> pageInfo = new PageInfo<>(notes);

        List<NoteVO> noteVOS = noteMapper.findCountByDate(userId);

        PageInfo<NoteVO> dateInfo = new PageInfo<>(noteVOS);

        List<NoteVO> noteVOList = noteMapper.findCountByType(userId);

        PageInfo<NoteVO> typeInfo = new PageInfo<>(noteVOList);

        session.setAttribute("page", pageInfo);

        session.setAttribute("dateInfo", dateInfo);

        session.setAttribute("typeInfo", typeInfo);

        modelAndView.addObject("menu_page", "index");
        modelAndView.addObject("changePage", "/note/list.jsp");
        modelAndView.setViewName("forward:/index.jsp");
        return modelAndView;
    }
}
