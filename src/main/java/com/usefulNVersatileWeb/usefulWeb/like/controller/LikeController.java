package com.usefulNVersatileWeb.usefulWeb.like.controller;

import com.usefulNVersatileWeb.usefulWeb.like.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/like")
public class LikeController {

    @Autowired
    LikeService likeService;

    @GetMapping(value = "/board/{boardSeq}", name = "게시판 좋아요 개수 조회")
    public int mainView(@PathVariable int boardSeq) {
        return likeService.showBoardLikeCount(boardSeq);
    }

}
