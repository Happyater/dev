package com.fc.service.impl;

import com.fc.dao.ReportMapper;
import com.fc.entity.TbNote;
import com.fc.entity.TbUser;
import com.fc.service.ReportService;
import com.fc.vo.NoteVO;
import com.fc.vo.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportMapper reportMapper;

    @Autowired
    private HttpSession session;

    @Override
    public ResultInfo location() {
        TbUser user = (TbUser) session.getAttribute("user");

        List<TbNote> notes = reportMapper.location(user.getId());

        ResultInfo resultInfo = new ResultInfo();

        if (notes != null && notes.size() > 0) {
            resultInfo.setCode(1);
            resultInfo.setData(notes);
        }

        return resultInfo;
    }

    @Override
    public ResultInfo month() {
        TbUser user = (TbUser) session.getAttribute("user");

        Map<String, Object> data = null;

        List<NoteVO> notes = reportMapper.getNoteCountByMonth(user.getId());

        if (notes != null && notes.size() > 0) {
            List<String> months = new ArrayList<>();
            List<Integer> noteCounts = new ArrayList<>();

            for (NoteVO note : notes) {
                months.add(note.getGroupName());
                noteCounts.add(note.getNoteCount());
            }

            data = new HashMap<>();

            data.put("monthArray", months);
            data.put("dataArray", noteCounts);
        }

        ResultInfo resultInfo = new ResultInfo();

        if (data != null) {
            resultInfo.setData(data);
            resultInfo.setCode(1);
        }

        return resultInfo;
    }
}
