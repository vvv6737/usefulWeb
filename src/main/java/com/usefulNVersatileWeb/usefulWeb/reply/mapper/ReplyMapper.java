package com.usefulNVersatileWeb.usefulWeb.reply.mapper;

import com.usefulNVersatileWeb.usefulWeb.reply.vo.ReplyVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReplyMapper {

    List<Map<String, Object>> replyList(Map<String, Object> param);

    int replyInsert(ReplyVo replyVo);
}
