package com.usefulNVersatileWeb.usefulWeb.board.vo;

import lombok.Data;

import java.io.File;

@Data
public class BoardVo {
    private int seq;
    private int userSeq;
    private String title;
    private String content;
    private String firstUpdate;
    private String lastUpdate;
    private String ip;

    private int pageNum;
    private int pageSize;

    private File imgFile;
    private String fileName;
    private String filePath;
}
