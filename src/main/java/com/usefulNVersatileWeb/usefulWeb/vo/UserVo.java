package com.usefulNVersatileWeb.usefulWeb.vo;

import lombok.Data;

@Data
public class UserVo {
    private int seq;
    private String userId;
    private String userName;
    private String userPassword;
    private String userPNum1;
    private String userPNum2;
    private String userPNum3;
    private String zipCode;
    private String address;
    private String detailAddress;
    private String userEmail;
    private String userRegDate;
}