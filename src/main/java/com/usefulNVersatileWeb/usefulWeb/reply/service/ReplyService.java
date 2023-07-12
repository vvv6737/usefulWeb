package com.usefulNVersatileWeb.usefulWeb.reply.service;

import com.usefulNVersatileWeb.usefulWeb.reply.mapper.ReplyMapper;
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

    public int replyInsert(Map<String, Object> param) {
        return replyMapper.replyInsert(param);
    }
}
