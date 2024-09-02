package com.atlcd.hustcyclingclubbackend.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRegisterReqDto {

    @Schema(description = "用户学号",required = true)
    @NotBlank(message = "学号不能为空")
    private String studentNum;

    @Schema(description = "未经过MD5加密的原始密码",required = true)
    @NotBlank(message = "密码不能为空")
    private String passwordMd5;

    @Schema(description = "真实姓名",required = true)
    @NotBlank(message = "真实姓名不能为空")
    private String realName;

    @Schema(description = "用户昵称",required = true)
    @NotBlank(message = "用户昵称不能为空")
    private String nickName;


}
