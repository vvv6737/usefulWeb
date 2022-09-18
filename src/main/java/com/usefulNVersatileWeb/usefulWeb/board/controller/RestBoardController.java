package com.usefulNVersatileWeb.usefulWeb.board.controller;

import com.usefulNVersatileWeb.usefulWeb.board.service.BoardService;
import com.usefulNVersatileWeb.usefulWeb.board.vo.BoardVo;
import com.usefulNVersatileWeb.usefulWeb.user.vo.UserVo;
import com.usefulNVersatileWeb.usefulWeb.util.IpUtil;
import com.usefulNVersatileWeb.usefulWeb.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@RequestMapping("/board")
public class RestBoardController {

    @Autowired
    BoardService boardService;

    @PostMapping(value = "/addBoard", name = "게시판 등록 API")
    public HashMap<String, Object> registerView(HttpSession session, BoardVo boardVo, HttpServletRequest request) throws Exception {
        HashMap<String, Object> resMap = new HashMap<>();

        UserVo userVo = SessionUtil.getUser(request);
        if(userVo == null) {
            resMap.put("result", false);
            resMap.put("msg", "세션이 종료되었습니다. 다시 로그인하여 작성해주세요.");
            return resMap;
        }
        // ip 저장
        String getIp = IpUtil.ipView(request);
        boardVo.setIp(getIp);
        //유저 시퀀스 저장
        boardVo.setSeq(userVo.getSeq());

        int resultInt = boardService.addBoard(boardVo);
        System.out.println(resultInt);
        if (resultInt <= 0) {
            resMap.put("result", false);
            resMap.put("msg", "게시글이 처리되지 않았습니다.");
            return resMap;
        } else {
            resMap.put("result", true);
            resMap.put("msg", "게시글이 처리되었습니다.");
            return resMap;
        }
    }
}
