package com.usefulNVersatileWeb.usefulWeb.main.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface MainMapper {

    //메인화면 리스트
    List<HashMap<String, Object>> mainBoradList() throws Exception;

}
