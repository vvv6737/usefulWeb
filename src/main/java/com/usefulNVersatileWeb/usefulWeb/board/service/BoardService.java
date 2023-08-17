package com.usefulNVersatileWeb.usefulWeb.board.service;

import com.usefulNVersatileWeb.usefulWeb.board.mapper.BoardMapper;
import com.usefulNVersatileWeb.usefulWeb.board.vo.BoardVo;
import com.usefulNVersatileWeb.usefulWeb.user.vo.UserVo;
import com.usefulNVersatileWeb.usefulWeb.util.IpUtil;
import com.usefulNVersatileWeb.usefulWeb.util.ResponseUtil;
import com.usefulNVersatileWeb.usefulWeb.util.SessionUtil;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    BoardMapper boardMapper;

    public List<HashMap<String, Object>> boardList(BoardVo boardVo) throws Exception {
        return boardMapper.boardList(boardVo);
    }

    public int[] boardListPaging(BoardVo boardVo) throws Exception {
        int[] paging;
        int itemsInAPage = boardVo.getPageSize();
        int totalCount = boardMapper.boardListCount(boardVo); //db 등에서 전체 게시물 카운트해서 값 가져오기.
        int totalPage = (int) Math.ceil(totalCount / (double) itemsInAPage);

        if(totalPage == 0) {
            paging = new int[0];
            paging[0] = 1;
        } else {
            paging = new int[totalPage];
            for (int i = 1; i <= totalPage; i++) {
                paging[i-1] = i;
            }
        }
        return paging;
    }

    public HashMap<String, Object> addBoard(MultipartFile imgFile, HttpServletRequest request) throws Exception {
        UserVo userVo = SessionUtil.getUser(request);
        if(userVo == null) {
            return ResponseUtil.failResponse(0, "세션이 종료되었습니다. 다시 로그인하여 작성해주세요.");
        }

        BoardVo boardVo = new BoardVo();
        // ip 저장, 유저 시퀀스 저장, 게시글 제목 저장, 내용 저장, 이미지 있을경우 이미지 저장
        String getIp = IpUtil.ipView(request);
        boardVo.setIp(getIp);
        boardVo.setUserSeq(userVo.getSeq());
        boardVo.setTitle(request.getParameter("title"));
        boardVo.setContent(request.getParameter("content"));

        //업로드할 파일이 있는경우
        if(!imgFile.isEmpty()) {
            // 현재 시간
            LocalTime now = LocalTime.now();
            // 포맷 정의하기
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH_mm_ss");
            // 포맷 적용하기
            String formatedNow = now.format(formatter);

            String ProductimageOriName = imgFile.getOriginalFilename();
            String fileNameExtension = FilenameUtils.getExtension(ProductimageOriName).toLowerCase();

            File destinationFile;
            String fileName;
            do {
                fileName = "boardImg_" + formatedNow + "." + fileNameExtension;
                destinationFile = new File(ResponseUtil.IsLoc() + fileName);
            } while (destinationFile.exists());
            destinationFile.getParentFile().mkdirs();
            imgFile.transferTo(destinationFile);

            // 파일정보, 경로 저장
            boardVo.setFileName(fileName);
            boardVo.setFilePath(ResponseUtil.IsLoc());
        }

        int resultInt = boardMapper.addBoard(boardVo);
        if (resultInt <= 0) {
            return ResponseUtil.failResponse(0, "게시글이 처리되지 않았습니다.");
        }
        return ResponseUtil.successResponse("게시글이 처리되었습니다.");
    }

    public HashMap<String, Object> boardDetail(int seq) {
        HashMap<String, Object> resMap = boardMapper.boardDetail(seq);

        if(resMap.get("CONTENT") != null) {
            String contentText = resMap.get("CONTENT").toString();
            if(contentText.contains("\r\n")) {
                List<String> text = Arrays.asList(contentText.split("\r\n"));
                resMap.put("contentArr", text);
            }
        }
        return resMap;
    }

    public HashMap<String, Object> updateBoard(BoardVo boardVo, HttpServletRequest request) throws Exception {
        UserVo userSession = SessionUtil.getUser(request);
        if(userSession == null) {
            return ResponseUtil.failResponse(0, "세션이 종료되었습니다. 다시 로그인하여 작성해주세요.");
        }

        //기존 게시판 작성자와 업데이트 할 유저 정보가 다르면 리턴
        if(boardVo.getUserSeq() != userSession.getSeq()) {
            return ResponseUtil.failResponse(0, "유저 정보가 다릅니다.");
        }

        // ip 저장
        boardVo.setIp(IpUtil.ipView(request));

        int resultInt = boardMapper.updateBoard(boardVo);
        if (resultInt <= 0) {
            return ResponseUtil.failResponse(0, "게시글이 처리되지 않았습니다.");
        }
        return ResponseUtil.successResponse("게시글이 처리되었습니다.");
    }
}
