package com.usefulNVersatileWeb.usefulWeb.board.controller;

import com.usefulNVersatileWeb.usefulWeb.board.service.BoardService;
import com.usefulNVersatileWeb.usefulWeb.util.IpUtil;
import com.usefulNVersatileWeb.usefulWeb.util.UrlUtil;
import com.usefulNVersatileWeb.usefulWeb.vo.BoardVo;
import com.usefulNVersatileWeb.usefulWeb.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@RequestMapping("/board")
public class ViewBoardController {

    @Autowired
    BoardService boardService;

    @GetMapping(value = "/list", name = "게시판 목록")
    public String mainView(Model model, BoardVo boardVo, HttpServletRequest request) throws Exception {
        model.addAttribute("boardList", boardService.boardList(boardVo));
        return UrlUtil.url("board/BoardList", request);
    }

    @GetMapping(value = "/register", name = "게시판 등록, 수정 페이지")
    public String registerView(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes attributes) throws Exception {
        Object userInfo = session.getAttribute("USER");
        HashMap<String, Object> resultMap = new HashMap<>();
        model.addAttribute("result", resultMap);
        if(userInfo == null) {
            attributes.addFlashAttribute("noUserService", "로그인이 필요한 서비스입니다.");
            return "redirect:/user/login?url=/board/register";
        }
        return UrlUtil.url("board/BoardRegister", request);
    }

    @GetMapping(value = "/datail/{seq}", name = "게시판 상세 페이지")
    public String boardDetail(@PathVariable int seq, Model model) throws Exception {
        HashMap<String, Object> resultMap = boardService.boardDetail(seq);
        model.addAttribute("result", resultMap);
        return "/view/board/BoardRegister";
    }

    @GetMapping(value = "/detailView", name = "게시판 상세 페이지")
    public String detailView(Model model, HttpServletRequest request) throws Exception {
        return UrlUtil.url("board/BoardDetailView", request);
    }
}
