package com.fc.service;

import com.fc.entity.MessageBoard;
import com.fc.entity.MessageBoardWithBLOBs;

import java.util.Map;

public interface MsgboardService {
    Map<String, Object> getListt(int pageNum, int pageSize);

    Map<String, Object> add(MessageBoardWithBLOBs messageBoard);

    Map<String, Object> update(MessageBoardWithBLOBs messageBoard);

    Map<String, Object> delete(int id);
}
