package com.atlcd.hustcyclingclubbackend.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class UserSaveActivityReqDto {

    @Schema(description = "活动标题",required = true)
    @NotBlank(message = "活动标题不能为空")
    private String title;

    @Schema(description = "活动介绍",required = true)
    private String introduce;

    @Schema(description = "活动封面图片",required = true)
    private String imgAddress;

    @Schema(description = "活动集合地点",required = true)
    private String gatheringPlace;

    @Schema(description = "活动类型",required = true)
    private String category;

    @Schema(description = "活动报名截止时间",required = true)
    private Date registrationTime;

    @Schema(description = "活动开始时间",required = true)
    @NotNull(message = "活动开始时间不能为空")
    private Date startTime;

    @Schema(description = "活动结束时间",required = true)
    @NotNull(message = "活动结束时间不能为空")
    private Date endTime;
}
