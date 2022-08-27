package com.usefulNVersatileWeb.usefulWeb.mapper;

import com.usefulNVersatileWeb.usefulWeb.vo.BoardVo;
import com.usefulNVersatileWeb.usefulWeb.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoradMapper {

    int addBoard(BoardVo boardVo) throws Exception;

    List<BoardVo> boardList(BoardVo boardVo) throws Exception;

}
