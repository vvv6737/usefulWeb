package com.usefulNVersatileWeb.usefulWeb.reply.vo;

import lombok.Data;

@Data
public class ReplyVo {
    private int replySeq;
    private int userSeq;
    private String boardSeq;
    private String replyContent;
    private String firstUpdate;
    private String lastUpadte;
}
