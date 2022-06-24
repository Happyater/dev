package com.fc.dao;

import com.fc.entity.TbNote;
import com.fc.vo.NoteVO;

import java.util.List;
import java.util.Map;

public interface ReportMapper {
    List<TbNote> location(Integer id);

    List<NoteVO> getNoteCountByMonth(Integer id);
}
