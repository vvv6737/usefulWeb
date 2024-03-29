package com.usefulNVersatileWeb.usefulWeb.board.controller;

import com.usefulNVersatileWeb.usefulWeb.board.service.BoardService;
import com.usefulNVersatileWeb.usefulWeb.board.vo.BoardVo;
import com.usefulNVersatileWeb.usefulWeb.user.vo.UserVo;
import com.usefulNVersatileWeb.usefulWeb.util.SessionUtil;
import com.usefulNVersatileWeb.usefulWeb.util.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.exceptions.TemplateInputException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@RequestMapping("/board")
public class ViewBoardController {

    @Autowired
    BoardService boardService;

    public static final String ERROR_PAGE = "/view/error/ErrorPage";

    @GetMapping(value = "/list", name = "게시판 목록")
    public String mainView(Model model, BoardVo boardVo, HttpServletRequest request) throws Exception {

        if (boardVo.getPageSize() == 0) {
            boardVo.setPageSize(10);
        }
        int pageNum = boardVo.getPageNum();
        pageNum = pageNum == 0 ? 1 : pageNum;
        boardVo.setPageNum((pageNum - 1) * boardVo.getPageSize());

        model.addAttribute("boardList", boardService.boardList(boardVo));
        model.addAttribute("paging", boardService.boardListPaging(boardVo));
        return UrlUtil.url("board/BoardList", request);
    }

    @GetMapping(value = "/register", name = "게시판 등록, 수정 페이지")
    public String registerView(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes attributes) throws Exception {
        UserVo userInfo = SessionUtil.getUser(request);
        HashMap<String, Object> resultMap = new HashMap<>();
        model.addAttribute("result", resultMap);
        if(userInfo == null) {
            attributes.addFlashAttribute("noUserService", "로그인이 필요한 서비스입니다.");
            return "redirect:/user/login?url=/board/register";
        }
        return UrlUtil.url("board/BoardRegister", request);
    }

    @GetMapping(value = "/detail/{seq}", name = "게시판 상세 페이지")
    public String boardDetail(@PathVariable int seq, Model model) {
        try {
            HashMap<String, Object> resultMap = boardService.boardDetail(seq);
            model.addAttribute("result", resultMap);

        } catch (TemplateInputException e) {
            String errorMsg = "템플릿에러! : " + e;
            model.addAttribute("errorMsg", errorMsg);
            return ERROR_PAGE;

        } catch (Exception e) {
            String errorMsg = "알수없는 에러! : " + e;
            model.addAttribute("errorMsg", errorMsg);
            return ERROR_PAGE;
        }
        return "/view/board/BoardRegister";
    }

    @GetMapping(value = "/detailView", name = "게시판 상세 페이지")
    public String detailView(Model model, HttpServletRequest request) throws Exception {
        return UrlUtil.url("board/BoardDetailView", request);
    }
}
