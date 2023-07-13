package com.usefulNVersatileWeb.usefulWeb.reply.service;

import com.usefulNVersatileWeb.usefulWeb.reply.mapper.ReplyMapper;
import com.usefulNVersatileWeb.usefulWeb.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReplyService {

    @Autowired
    ReplyMapper replyMapper;

    public List<Map<String, Object>> replyList(Map<String, Object> param) {
        return replyMapper.replyList(param);
    }

    public Map<String, Object> replyInsert(Map<String, Object> param) {
        int result = replyMapper.replyInsert(param);
        if (result <= 0) {
            return ResponseUtil.failResponse(0, "댓글입력이 처리되지 않았습니다.");
        }
        return ResponseUtil.successResponse("댓글입력이 처리되었습니다.");
    }
}
