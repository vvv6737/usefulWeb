package com.usefulNVersatileWeb.usefulWeb.reply.service;

import com.usefulNVersatileWeb.usefulWeb.reply.mapper.ReplyMapper;
import com.usefulNVersatileWeb.usefulWeb.reply.vo.ReplyVo;
import com.usefulNVersatileWeb.usefulWeb.user.vo.UserVo;
import com.usefulNVersatileWeb.usefulWeb.util.ResponseUtil;
import com.usefulNVersatileWeb.usefulWeb.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Service
public class ReplyService {

    @Autowired
    ReplyMapper replyMapper;

    public List<Map<String, Object>> replyList(Map<String, Object> param) {
        return replyMapper.replyList(param);
    }

    public Map<String, Object> replyInsert(HttpServletRequest request, ReplyVo replyVo) {
        UserVo userVo = SessionUtil.getUser(request);

        //로그인하지 않았을경우
        if(userVo == null) {
            return ResponseUtil.failResponse(100, "로그인후 댓글입력이 가능합니다.");
        }

        //유저시퀀스 입력
        replyVo.setUserSeq(userVo.getSeq());
        System.out.println("replyVo : " + replyVo);

        int result = replyMapper.replyInsert(replyVo);
        if (result <= 0) {
            return ResponseUtil.failResponse(200, "댓글입력이 처리되지 않았습니다.");
        }
        return ResponseUtil.successResponse("댓글입력이 처리되었습니다.");
    }
}
