package com.atlcd.hustcyclingclubbackend.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ActivivityAllReqDto {
    @Schema(description = "类型")
    private Integer category;

    @Schema(description = "状态码")
    private Integer stateCode;

    @Schema(description = "查询关键词")
    private String keyWord;
}
