package com.usefulNVersatileWeb.usefulWeb.board.vo;

import lombok.Data;

import java.io.File;

@Data
public class BoardVo {
    private int seq;
    private int userSeq;
    private String title;
    private String imgFileName;
    private String content;
    private String firstUpdate;
    private String lastUpdate;
    private String ip;

    private File imgFile;
}
