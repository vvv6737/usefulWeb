package com.usefulNVersatileWeb.usefulWeb.util;


import com.usefulNVersatileWeb.usefulWeb.user.vo.UserVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {
    final static String SESSION_USER_KEY = "USER";

    public static boolean setUser(UserVo user, HttpServletRequest request){
        if (user == null) return false;
        HttpSession session = request.getSession();
        session.setAttribute(SESSION_USER_KEY, user);
        return true;
    }

    public static UserVo getUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (UserVo) session.getAttribute(SESSION_USER_KEY);
    }
}
