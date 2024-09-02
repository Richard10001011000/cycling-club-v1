package com.atlcd.hustcyclingclubbackend.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
public class UserInfoRespDto {
    private String studentNum;

    private String nickName;

    private String signature;

}
