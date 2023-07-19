package com.usefulNVersatileWeb.usefulWeb.reply.controller;

import com.usefulNVersatileWeb.usefulWeb.reply.service.ReplyService;
import com.usefulNVersatileWeb.usefulWeb.reply.vo.ReplyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reply")
public class ReplyContoller {

    @Autowired
    ReplyService replyService;

    @GetMapping(value = "/list", name = "해당글의 댓글 조회")
    public List<Map<String, Object>> replyView(@RequestParam Map<String, Object> param) {
        return replyService.replyList(param);
    }

    @PostMapping(value = "/insert", name = "댓글 입력")
    public Map<String, Object> replyInsert(HttpServletRequest request, ReplyVo replyVo) {
        return replyService.replyInsert(request, replyVo);
    }

    @PostMapping(value = "/update", name = "댓글 수정")
    public Map<String, Object> replyUpdate(HttpServletRequest request, ReplyVo replyVo) {
        return replyService.replyUpdate(request, replyVo);
    }


    @PostMapping(value = "/delete", name = "댓글 삭제")
    public Map<String, Object> replyDelete(HttpServletRequest request, ReplyVo replyVo) {
        return replyService.replyDelete(request, replyVo);
    }
}
