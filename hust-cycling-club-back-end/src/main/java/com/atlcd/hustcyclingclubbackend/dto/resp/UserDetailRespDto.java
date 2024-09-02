package com.atlcd.hustcyclingclubbackend.dto.resp;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
public class UserDetailRespDto {
    private String nickName;

    private String signature;

    private String realName;

    private String gender;

    private String qq;

    private String phoneNumber;

    private String college;
}
