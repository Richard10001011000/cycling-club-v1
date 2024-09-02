package com.atlcd.hustcyclingclubbackend.controller;

import com.atlcd.hustcyclingclubbackend.core.auth.UserHolder;
import com.atlcd.hustcyclingclubbackend.dto.req.*;

import com.atlcd.hustcyclingclubbackend.dto.resp.ActivityInfoRespDto;
import com.atlcd.hustcyclingclubbackend.dto.resp.UserDetailRespDto;
import com.atlcd.hustcyclingclubbackend.dto.resp.UserInfoRespDto;
import com.atlcd.hustcyclingclubbackend.dto.resp.UserLoginRespDto;
import com.atlcd.hustcyclingclubbackend.service.ActivityService;
import com.atlcd.hustcyclingclubbackend.service.ParticipantService;
import com.atlcd.hustcyclingclubbackend.service.UserService;
import com.atlcd.hustcyclingclubbackend.core.resp.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "用户相关接口")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
public class UserController {

    private final UserService userService;

    private final ParticipantService participantService;

    private final ActivityService activityService;

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Result<UserLoginRespDto> login(@RequestBody @Valid UserLoginReqDto userLoginReqDto) {
        return userService.login(userLoginReqDto);
    }

    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public Result<Void> register(@RequestBody @Valid UserRegisterReqDto userRegisterReqDto) {
        return userService.register(userRegisterReqDto);
    }

    @Operation(summary = "用户修改密码")
    @PutMapping("/password")
    public Result<Void> updatePassword(@RequestBody @Valid UserUpdatePasswordReqDto userUpdatePasswordReqDto) {
        return userService.updatePassword(UserHolder.getUserId(),userUpdatePasswordReqDto);
    }

    @GetMapping("/info")
    @Operation(summary = "用户页面信息牌")
    public Result<UserInfoRespDto> getInfo() {
        return userService.getInfo(UserHolder.getUserId());
    }

    @GetMapping("/detail")
    @Operation(summary = "获得用户全部信息")
    public Result<UserDetailRespDto> getDetail() {
        return userService.getDetail(UserHolder.getUserId());
    }

    @PutMapping("/detail")
    @Operation(summary = "用户补充或修改信息")
    public Result<Void> updateDetail(@RequestBody @Valid UserUpdateDetailReqDto userUpdateDetailReqDto) {
        return userService.updateDetail(UserHolder.getUserId(),userUpdateDetailReqDto);
    }

    @GetMapping("/participant")
    @Operation(summary = "获得用户参加活动列表")
    public Result<List<ActivityInfoRespDto>> listParticipant() {
        return participantService.listParticipant(UserHolder.getUserId());
    }

    @PostMapping("/participant/{activityId}")
    @Operation(summary = "用户参加活动")
    public Result<Void> saveParticipant(@PathVariable("activityId") Integer activityId) {
        return participantService.saveParticipant(UserHolder.getUserId(),activityId);
    }

    @DeleteMapping("/participant/{activityId}")
    @Operation(summary = "用户退出活动")
    public Result<Void> deleteParticipant(@PathVariable("activityId") Integer activityId) {
        return participantService.deleteParticipant(UserHolder.getUserId(),activityId);
    }

    @PostMapping("/activity")
    @Operation(summary = "管理员创建活动——用户需为管理员")
    public Result<Void> saveActivity(@RequestBody @Valid UserSaveActivityReqDto userSaveActivityReqDto){
        return activityService.saveActivity(userSaveActivityReqDto);
    }

    @PutMapping("/activity")
    @Operation(summary = "管理员更新活动")
    public Result<Void> updateActivity(@RequestBody @Valid UserUpdateActivityReqDto userUpdateActivityReqDto){
        return activityService.updateActivity(userUpdateActivityReqDto);
    }

    @DeleteMapping("/activity")
    @Operation(summary = "管理员删除活动")
    public Result<Void> deleteActivity(@RequestParam Integer activityId){
        return activityService.deleteActivity(activityId);
    }

}
