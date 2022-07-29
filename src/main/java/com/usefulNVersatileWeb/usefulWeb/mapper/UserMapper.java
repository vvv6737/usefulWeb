package com.usefulNVersatileWeb.usefulWeb.mapper;

import com.usefulNVersatileWeb.usefulWeb.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserVo> userInfo() throws Exception;
}
