package com.usefulNVersatileWeb.usefulWeb.main.service;

import com.usefulNVersatileWeb.usefulWeb.main.mapper.MainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class MainService {

    @Autowired
    MainMapper mainMapper;

    public List<HashMap<String, Object>> mainBoradList() throws Exception{
        List<HashMap<String, Object>> resultMap = mainMapper.mainBoradList();

        for (int i = 0; i < resultMap.size(); i++) {
            String contentText = resultMap.get(i).get("content").toString();
            //줄바꿈 text가 있다면
            if(contentText.contains("\r\n")) {
                List<String> text = Arrays.asList(contentText.split("\r\n"));
                resultMap.get(i).put("contentArr", text);
            }
        }
        return resultMap;
    }
}
