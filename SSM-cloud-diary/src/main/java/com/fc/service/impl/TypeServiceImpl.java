package com.fc.service.impl;

import com.fc.dao.TbNoteTypeMapper;
import com.fc.entity.TbNote;
import com.fc.entity.TbNoteType;
import com.fc.entity.TbUser;
import com.fc.service.TypeService;
import com.fc.vo.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private HttpSession session;

    @Autowired
    private TbNoteTypeMapper tbNoteTypeMapper;

    @Override
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView();

        TbUser user = (TbUser) session.getAttribute("user");

        List<TbNoteType> list = tbNoteTypeMapper.findByUserId(user.getId());

        mv.addObject("list", list);
        mv.addObject("menu_page", "/type");
        mv.addObject("changePage", "/type/list.jsp");
        mv.setViewName("forward:/index.jsp");

        return mv;
    }

    @Override
    public ResultInfo addOrUpdate(int typeId, String typeName, int userId) {
        TbNoteType tbNoteType = new TbNoteType();

        tbNoteType.setTypeName(typeName);

        ResultInfo resultInfo = new ResultInfo();

        TbUser user = (TbUser) session.getAttribute("user");

        tbNoteType.setUserId(user.getId());

        int i = 0;

        if (typeId == 0) {
            i = tbNoteTypeMapper.insertSelective(tbNoteType);
            if (i > 0) {
                resultInfo.setMessage("添加成功");
                resultInfo.setData(tbNoteType.getId());
            } else {
                resultInfo.setMessage("添加失败");
            }
        } else {

            tbNoteType.setId(typeId);

            i = tbNoteTypeMapper.updateByPrimaryKeySelective(tbNoteType);

            if (i > 0) {
                resultInfo.setMessage("修改成功");
            } else {
                resultInfo.setMessage("修改失败");
            }
        }

        if (i > 0) {
            resultInfo.setCode(1);
        } else {
            resultInfo.setCode(0);
        }

        return resultInfo;
    }

    @Override
    public ResultInfo delete(int id) {
        List<TbNote> tbNotes = tbNoteTypeMapper.selectExist(id);

        ResultInfo resultInfo = new ResultInfo();

        if (tbNotes.size() != 0) {
            resultInfo.setCode(0);
            resultInfo.setMessage("删除失败");
            return resultInfo;
        }

        int i = tbNoteTypeMapper.deleteByPrimaryKey(id);

        if (i > 0) {
            resultInfo.setCode(1);
        } else {
            resultInfo.setCode(0);
            resultInfo.setMessage("删除失败");
        }

        return resultInfo;
    }
}
