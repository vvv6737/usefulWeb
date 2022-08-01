package com.usefulNVersatileWeb.usefulWeb.vo;

import lombok.Data;

@Data
public class BoardVo {
    private int seq;
    private int userSeq;
    private String title;
    private String content;
    private String lastUpdate;
    private String ip;
}
