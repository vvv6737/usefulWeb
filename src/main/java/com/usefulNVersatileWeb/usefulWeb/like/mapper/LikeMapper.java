package com.usefulNVersatileWeb.usefulWeb.like.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LikeMapper {

    int showBoardLikeCount(int boardSeq);

}
