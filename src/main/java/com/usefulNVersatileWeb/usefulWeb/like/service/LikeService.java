package com.usefulNVersatileWeb.usefulWeb.like.service;

import com.usefulNVersatileWeb.usefulWeb.like.mapper.LikeMapper;
import com.usefulNVersatileWeb.usefulWeb.user.vo.UserVo;
import com.usefulNVersatileWeb.usefulWeb.util.ResponseUtil;
import com.usefulNVersatileWeb.usefulWeb.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class LikeService {

    @Autowired
    LikeMapper likeMapper;

    public int showBoardLikeCount(int boardSeq) {
        return likeMapper.showBoardLikeCount(boardSeq);
    }

    public Map<String, Object> boardLikeupdate(int boardSeq, HttpServletRequest request) {
        UserVo userVo = SessionUtil.getUser(request);
        if(userVo == null) {
            return ResponseUtil.failResponse(0, "로그인 후 좋아요 등록이 가능합니다.");
        }

        Map<String, Object> param = new HashMap<>();
        param.put("boardSeq", boardSeq);
        param.put("userSeq", userVo.getSeq());

        Boolean isLike = likeMapper.isBoardLike(param);
        System.out.println(isLike);

        if(isLike) {
            likeMapper.deleteBoardLike(param);
        } else {
            likeMapper.insertBoardLike(param);
        }
        return ResponseUtil.successResponse("좋아요 적용 완료했습니다.");
    }
}
