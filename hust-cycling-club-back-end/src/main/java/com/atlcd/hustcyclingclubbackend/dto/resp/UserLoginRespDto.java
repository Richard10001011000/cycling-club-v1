package com.atlcd.hustcyclingclubbackend.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginRespDto {

    private String token;

    private String nickName;
}
