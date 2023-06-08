package com.usefulNVersatileWeb.usefulWeb.board.controller;

import com.usefulNVersatileWeb.usefulWeb.board.service.BoardService;
import com.usefulNVersatileWeb.usefulWeb.board.vo.BoardVo;
import com.usefulNVersatileWeb.usefulWeb.user.vo.UserVo;
import com.usefulNVersatileWeb.usefulWeb.util.IpUtil;
import com.usefulNVersatileWeb.usefulWeb.util.ResponseUtil;
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
    public HashMap<String, Object> registerView(BoardVo boardVo, HttpServletRequest request) throws Exception {
        return boardService.addBoard(boardVo, request);
    }

    @PostMapping(value = "/updateBoard", name = "게시판 수정 API")
    public HashMap<String, Object> updateBoard(BoardVo boardVo, HttpServletRequest request) throws Exception {
        return boardService.updateBoard(boardVo, request);
    }
}
