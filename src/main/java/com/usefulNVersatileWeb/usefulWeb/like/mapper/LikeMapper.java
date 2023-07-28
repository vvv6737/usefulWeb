package com.usefulNVersatileWeb.usefulWeb.like.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface LikeMapper {

    int showBoardLikeCount(int boardSeq);

    int boardLikeupdate(int boardSeq, int userSeq);

    Boolean isBoardLike(Map<String, Object> param);

    int deleteBoardLike(Map<String, Object> param);

    int insertBoardLike(Map<String, Object> param);

}
