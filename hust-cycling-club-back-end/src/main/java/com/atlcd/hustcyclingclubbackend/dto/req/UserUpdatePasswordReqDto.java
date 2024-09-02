package com.atlcd.hustcyclingclubbackend.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserUpdatePasswordReqDto {
    @Schema(description = "原始密码")
    @NotBlank(message = "原密码不能为空")
    private String oldPasswordMd5;

    @Schema(description = "新密码")
    @NotBlank(message = "新密码不能为空")
    private String newPasswordMd5;
}
