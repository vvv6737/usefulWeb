package com.usefulNVersatileWeb.usefulWeb.main.service;

import com.usefulNVersatileWeb.usefulWeb.main.mapper.MainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class MainService {

    @Autowired
    MainMapper mainMapper;

    public List<HashMap<String, Object>> mainBoradList() throws Exception{
        return mainMapper.mainBoradList();
    }
}
