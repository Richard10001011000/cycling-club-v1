package com.atlcd.hustcyclingclubbackend.controller;

import com.atlcd.hustcyclingclubbackend.core.resp.Result;
import com.atlcd.hustcyclingclubbackend.dto.req.ActivivityAllReqDto;
import com.atlcd.hustcyclingclubbackend.dto.resp.ActivityDetailRespDto;
import com.atlcd.hustcyclingclubbackend.dto.resp.ActivityInfoRespDto;
import com.atlcd.hustcyclingclubbackend.service.ActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activity")
@Tag(name = "活动相关接口")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @GetMapping("/detail/{activityId}")
    @Operation(summary = "获得活动详情")
    public Result<ActivityDetailRespDto> getDetail(@PathVariable Integer activityId,@RequestHeader(value = "Authorization",required = false ) String token) {
        return activityService.getDetail(activityId,token);
    }

    @PostMapping("/all")
    @Operation(summary = "查询指定规则下的全部活动")
    public Result<List<ActivityInfoRespDto>> listAll(@RequestBody ActivivityAllReqDto activivityAllReqDto) {
        System.out.println(activivityAllReqDto);
        return activityService.listAll(activivityAllReqDto);
    }

}
