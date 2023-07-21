package com.usefulNVersatileWeb.usefulWeb.reply.service;

import com.usefulNVersatileWeb.usefulWeb.reply.mapper.ReplyMapper;
import com.usefulNVersatileWeb.usefulWeb.reply.vo.ReplyVo;
import com.usefulNVersatileWeb.usefulWeb.user.vo.UserVo;
import com.usefulNVersatileWeb.usefulWeb.util.ResponseUtil;
import com.usefulNVersatileWeb.usefulWeb.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ReplyService {

    @Autowired
    ReplyMapper replyMapper;

    public List<Map<String, Object>> replyList(Map<String, Object> param) {
        List<Map<String, Object>> result = replyMapper.replyList(param);
        if(result.size() != 0) {
            for (int i = 0; i < result.size(); i++) {
                String contentText = result.get(i).get("REPLAY_CONTENT").toString();
                if(contentText.contains("\n")){
                    List<String> contentTextToArr = Arrays.asList(contentText.split("\n"));
                    result.get(i).put("REPLAY_CONTENT", contentTextToArr);
                }
            }
        }
        return result;
    }

    public Map<String, Object> replyInsert(HttpServletRequest request, ReplyVo replyVo) {
        UserVo userVo = SessionUtil.getUser(request);
        //로그인하지 않았을경우
        if(userVo == null) {
            return ResponseUtil.failResponse(100, "로그인 후 댓글입력이 가능합니다.");
        }

        //유저시퀀스 셋팅
        replyVo.setUserSeq(userVo.getSeq());
        int result = replyMapper.replyInsert(replyVo);
        if (result <= 0) {
            return ResponseUtil.failResponse(200, "댓글입력이 처리되지 않았습니다.");
        }
        return ResponseUtil.successResponse("댓글입력이 처리되었습니다.");
    }

    public Map<String, Object> replyUpdate(HttpServletRequest request, ReplyVo replyVo) {
        UserVo userVo = SessionUtil.getUser(request);
        //로그인하지 않았을경우
        if(userVo == null) {
            return ResponseUtil.failResponse(100, "로그인 후 댓글수정이 가능합니다.");
        }

        int result = replyMapper.replyUpdate(replyVo);
        if (result <= 0) {
            return ResponseUtil.failResponse(200, "댓글입력이 처리되지 않았습니다.");
        }
        return ResponseUtil.successResponse("댓글이 수정되었습니다.");
    }

    public Map<String, Object> replyDelete(HttpServletRequest request, ReplyVo replyVo) {
        UserVo userVo = SessionUtil.getUser(request);
        //로그인하지 않았을경우
        if(userVo == null) {
            return ResponseUtil.failResponse(100, "로그인 후 댓글삭제가 가능합니다.");
        }

        int result = replyMapper.replyDelete(replyVo);
        if (result <= 0) {
            return ResponseUtil.failResponse(200, "댓글입력이 처리되지 않았습니다.");
        }
        return ResponseUtil.successResponse("댓글이 삭제처리되었습니다.");
    }
}
