package com.atlcd.hustcyclingclubbackend.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserUpdateDetailReqDto {
    @Schema(description = "用户昵称")
    @NotBlank(message = "用户昵称不能为空")
    private String nickName;

    @Schema(description = "个性签名")
    private String signature;

    @Schema(description = "真实姓名")
    @NotBlank(message = "真实姓名不能为空")
    private String realName;

    @Schema(description = "性别")
    private String gender;

    @Schema(description = "QQ号")
    private String qq;

    @Schema(description = "电话号码")
    private String phoneNumber;

    @Schema(description = "学院")
    private String college;
}
