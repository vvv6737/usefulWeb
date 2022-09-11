package com.usefulNVersatileWeb.usefulWeb.mapper;

import com.usefulNVersatileWeb.usefulWeb.vo.BoardVo;
import com.usefulNVersatileWeb.usefulWeb.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface BoradMapper {

    //게시글 리스트
    List<BoardVo> boardList(BoardVo boardVo) throws Exception;

    //게시글 추가
    int addBoard(BoardVo boardVo) throws Exception;

    HashMap<String, Object> boardDetail(int seq) throws Exception;

    //게시글 수정, 삭제
    int updateBoard(BoardVo boardVo) throws Exception;
}
