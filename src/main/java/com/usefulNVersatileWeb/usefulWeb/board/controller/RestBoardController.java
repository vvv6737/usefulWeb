package com.usefulNVersatileWeb.usefulWeb.board.controller;

import com.usefulNVersatileWeb.usefulWeb.board.service.BoardService;
import com.usefulNVersatileWeb.usefulWeb.board.vo.BoardVo;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/board")
public class RestBoardController {

    @Autowired
    BoardService boardService;

    @PostMapping(value = "/addBoard", name = "게시판 등록 API")
    public HashMap<String, Object> registerView(BoardVo boardVo, HttpServletRequest request) throws Exception {
        return boardService.addBoard(boardVo, request);
    }

    @PostMapping(value = "/updateBoard", name = "게시판 수정 API")
    public HashMap<String, Object> updateBoard(BoardVo boardVo, HttpServletRequest request) throws Exception {
        return boardService.updateBoard(boardVo, request);
    }

    @PostMapping(value = "/excel", name = "엑셀 테스트")
    public void excel(BoardVo boardVo, HttpServletResponse res) throws Exception {
        List<HashMap<String, Object>> list = boardService.boardList(boardVo);

        //엑셀 저장 로직
        if(list != null && list.size() > 0) {
            final String fileName = "boardList.xlsx";

            /* 엑셀 그리기 */
            final String[] colNames = {
                    "No", "제목", "날짜", "ID"
            };

            // 헤더 사이즈
            final int[] colWidths = {
                    3000, 5000, 5000, 3000
            };

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = null;
            XSSFCell cell = null;
            XSSFRow row = null;

            //Font
            Font fontHeader = workbook.createFont();
            fontHeader.setFontName("맑은 고딕");	//글씨체
            fontHeader.setFontHeight((short)(9 * 20));	//사이즈
            fontHeader.setBoldweight(Font.BOLDWEIGHT_BOLD);	//볼드(굵게)
            Font font9 = workbook.createFont();
            font9.setFontName("맑은 고딕");	//글씨체
            font9.setFontHeight((short)(9 * 20));	//사이즈

            // 엑셀 헤더 셋팅
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setAlignment(CellStyle.ALIGN_CENTER);
            headerStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            headerStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            headerStyle.setFont(fontHeader);

            // 엑셀 바디 셋팅
            CellStyle bodyStyle = workbook.createCellStyle();
            bodyStyle.setAlignment(CellStyle.ALIGN_CENTER);
            bodyStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            bodyStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            bodyStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            bodyStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            bodyStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            bodyStyle.setFont(font9);

            // 엑셀 왼쪽 설정
            CellStyle leftStyle = workbook.createCellStyle();
            leftStyle.setAlignment(CellStyle.ALIGN_LEFT);
            leftStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            leftStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            leftStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            leftStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            leftStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            leftStyle.setFont(font9);

            //rows
            int rowCnt = 0;
            int cellCnt = 0;
            int listCount = list.size();

            // 엑셀 시트명 설정
            sheet = workbook.createSheet("사용자현황");
            row = sheet.createRow(rowCnt++);

            //헤더 정보 구성
            for (int i = 0; i < colNames.length; i++) {
                cell = row.createCell(i);
                cell.setCellStyle(headerStyle);
                cell.setCellValue(colNames[i]);
                sheet.setColumnWidth(i, colWidths[i]);	//column width 지정
            }

            //데이터 부분 생성
            for(HashMap<String, Object> data : list) {
                cellCnt = 0;
                row = sheet.createRow(rowCnt++);

                // 넘버링
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(listCount--);

                // 제목
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(data.get("title").toString());

                // 날짜
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(data.get("uploadDate").toString());

                // ID
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(data.get("userId").toString());
            }
            res.setContentType("application/vnd.ms-excel");
            // 엑셀 파일명 설정
            res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            try {
                workbook.write(res.getOutputStream());
            } catch(IOException e) {
                System.out.println("IOException "+e);
            } catch(Exception e) {
                System.out.println("Exception "+e);
            } finally {
                System.out.println("완료~");
            }
        }

    }
}
