package com.usefulNVersatileWeb.usefulWeb.user.mapper;

import com.usefulNVersatileWeb.usefulWeb.user.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    int addUser(UserVo userVo) throws Exception;

    UserVo loginForm(UserVo userVO);

    List<UserVo> isUserIdCheck(UserVo userVO) throws Exception;

}
