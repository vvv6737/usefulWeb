package com.usefulNVersatileWeb.usefulWeb.mapper;

import com.usefulNVersatileWeb.usefulWeb.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface UserMapper {

    int addUser(UserVo userVo) throws Exception;

    UserVo loginForm(UserVo userVO);

    List<UserVo> isUserIdCheck(UserVo userVO) throws Exception;

}
