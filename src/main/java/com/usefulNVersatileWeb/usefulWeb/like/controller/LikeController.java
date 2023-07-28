package com.usefulNVersatileWeb.usefulWeb.like.controller;

import com.usefulNVersatileWeb.usefulWeb.like.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/like")
public class LikeController {

    @Autowired
    LikeService likeService;

    @GetMapping(value = "/board/{boardSeq}", name = "게시판 좋아요 개수 조회")
    public int boardLikeView(@PathVariable int boardSeq) {
        return likeService.showBoardLikeCount(boardSeq);
    }

    @PostMapping(value = "/boardUpdate/{boardSeq}", name = "게시판 좋아요 등록, 취소")
    public Map<String, Object> boardLikeupdate(@PathVariable int boardSeq, HttpServletRequest request) {
        return likeService.boardLikeupdate(boardSeq, request);
    }

}
