package com.usefulNVersatileWeb.usefulWeb.board.service;

import com.usefulNVersatileWeb.usefulWeb.board.mapper.BoardMapper;
import com.usefulNVersatileWeb.usefulWeb.vo.BoardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    BoardMapper boardMapper;

    public List<HashMap<String, Object>> boardList(BoardVo boardVo) throws Exception {
        return boardMapper.boardList(boardVo);
    }

    public int addBoard(BoardVo boardVo) throws Exception {
        return boardMapper.addBoard(boardVo);
    }

    public HashMap<String, Object> boardDetail(int seq) throws Exception {
        return boardMapper.boardDetail(seq);
    }

    public int updateBoard(BoardVo boardVo) throws Exception {
        return boardMapper.updateBoard(boardVo);
    }
}
