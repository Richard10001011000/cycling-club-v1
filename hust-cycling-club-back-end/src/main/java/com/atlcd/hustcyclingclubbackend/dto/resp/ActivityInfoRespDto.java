package com.atlcd.hustcyclingclubbackend.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class ActivityInfoRespDto {
    private Integer activityId;

    private String title;

    private String imgAddress;

    private Integer category;

    private Date startTime;

    private Integer stateCode;

}
