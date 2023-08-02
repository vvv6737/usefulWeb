package com.usefulNVersatileWeb.usefulWeb.board.mapper;

import com.usefulNVersatileWeb.usefulWeb.board.vo.BoardVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface BoardMapper {

    //게시글 리스트
    List<HashMap<String, Object>> boardList(BoardVo boardVo) throws Exception;

    //게시글 전체수
    int boardListCount(BoardVo boardVo);

    //게시글 추가
    int addBoard(BoardVo boardVo) throws Exception;

    HashMap<String, Object> boardDetail(int seq);

    //게시글 수정, 삭제
    int updateBoard(BoardVo boardVo) throws Exception;
}
