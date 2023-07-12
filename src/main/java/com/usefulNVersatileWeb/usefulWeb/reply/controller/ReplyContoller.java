package com.usefulNVersatileWeb.usefulWeb.reply.controller;

import com.usefulNVersatileWeb.usefulWeb.reply.service.ReplyService;
import com.usefulNVersatileWeb.usefulWeb.reply.vo.ReplyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reply")
public class ReplyContoller {

    @Autowired
    ReplyService replyService;

    @GetMapping(value = "/list", name = "해당글의 댓글 조회")
    public List<Map<String, Object>> registerView(@RequestParam Map<String, Object> param) {
        return replyService.replyList(param);
    }

    @PostMapping(value = "/insert", name = "댓글 입력")
    public List<Map<String, Object>> registerInsert(ReplyVo replyVo) {
        return null;
    }

    @PostMapping(value = "/update", name = "댓글 수정")
    public List<Map<String, Object>> registerUpdate(ReplyVo replyVo) {
        return null;
    }
}
