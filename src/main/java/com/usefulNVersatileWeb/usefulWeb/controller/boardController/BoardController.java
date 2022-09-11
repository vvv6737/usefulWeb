package com.usefulNVersatileWeb.usefulWeb.controller.boardController;

import com.usefulNVersatileWeb.usefulWeb.service.BoardService;
import com.usefulNVersatileWeb.usefulWeb.util.IpUtil;
import com.usefulNVersatileWeb.usefulWeb.util.UrlUtil;
import com.usefulNVersatileWeb.usefulWeb.vo.BoardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @GetMapping(value = "/list", name = "게시판 목록")
    public String mainView(Model model, BoardVo boardVo, HttpServletRequest request) throws Exception {
        model.addAttribute("boardList", boardService.boardList(boardVo));
        return UrlUtil.url("BoardList", request);
    }

    @GetMapping(value = "/register", name = "게시판 등록, 수정 페이지")
    public String registerView(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes attributes) throws Exception {
        Object userInfo = session.getAttribute("USER");
        if(userInfo == null) {
            attributes.addFlashAttribute("noUserService", "로그인이 필요한 서비스입니다.");
            return "redirect:/user/login";
        }
        return UrlUtil.url("Register", request);
    }

    @ResponseBody
    @PostMapping(value = "/addBoard", name = "게시판 등록 API")
    public Object registerView(HttpSession session, BoardVo boardVo, HttpServletRequest request) throws Exception {
        Object userInfo = session.getAttribute("USER");
        if(userInfo == null) {
            return "세션이 종료되었습니다. 다시 로그인하여 작성해주세요.";
        }
        String getIp = IpUtil.ipView(request);
        boardVo.setIp(getIp);
        int resultInt = boardService.addBoard(boardVo);
        return resultInt;
    }

    @ResponseBody
    @PostMapping(value = "/register", name = "게시판 등록, 수정 프로세스")
    public List<BoardVo> registerProc(BoardVo boardVo) throws Exception {
        return null;
    }

    @GetMapping(value = "/detailView", name = "게시판 상세 페이지")
    public String detailView(Model model, HttpServletRequest request) throws Exception {
        return UrlUtil.url("DetailView", request);
    }
}
