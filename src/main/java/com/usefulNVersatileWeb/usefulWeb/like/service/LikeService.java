package com.usefulNVersatileWeb.usefulWeb.like.service;

import com.usefulNVersatileWeb.usefulWeb.like.mapper.LikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    @Autowired
    LikeMapper likeMapper;

    public int showBoardLikeCount(int boardSeq) {
        return likeMapper.showBoardLikeCount(boardSeq);
    }
}
