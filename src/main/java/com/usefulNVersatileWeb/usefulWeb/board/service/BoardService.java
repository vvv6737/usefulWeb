package com.usefulNVersatileWeb.usefulWeb.board.service;

import com.usefulNVersatileWeb.usefulWeb.board.mapper.BoradMapper;
import com.usefulNVersatileWeb.usefulWeb.vo.BoardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    BoradMapper boradMapper;

    public List<BoardVo> boardList(BoardVo boardVo) throws Exception {
        return boradMapper.boardList(boardVo);
    }

    public int addBoard(BoardVo boardVo) throws Exception {
        return boradMapper.addBoard(boardVo);
    }

    public HashMap<String, Object> boardDetail(int seq) throws Exception {
        return boradMapper.boardDetail(seq);
    }

    public int updateBoard(BoardVo boardVo) throws Exception {
        return boradMapper.updateBoard(boardVo);
    }
}
