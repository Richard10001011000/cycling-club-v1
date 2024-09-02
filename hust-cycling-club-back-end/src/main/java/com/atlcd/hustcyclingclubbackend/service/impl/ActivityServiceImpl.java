package com.atlcd.hustcyclingclubbackend.service.impl;

import com.atlcd.hustcyclingclubbackend.core.common.constants.ActivityCodeParser;
import com.atlcd.hustcyclingclubbackend.core.common.constants.ResultCodeEnum;
import com.atlcd.hustcyclingclubbackend.core.exception.BusinessException;
import com.atlcd.hustcyclingclubbackend.core.resp.Result;
import com.atlcd.hustcyclingclubbackend.core.util.BeanUtil;
import com.atlcd.hustcyclingclubbackend.core.util.JwtUtil;
import com.atlcd.hustcyclingclubbackend.dao.mapper.ParticipantMapper;
import com.atlcd.hustcyclingclubbackend.dto.req.ActivivityAllReqDto;
import com.atlcd.hustcyclingclubbackend.dto.req.UserSaveActivityReqDto;
import com.atlcd.hustcyclingclubbackend.dto.req.UserUpdateActivityReqDto;
import com.atlcd.hustcyclingclubbackend.dto.resp.ActivityDetailRespDto;
import com.atlcd.hustcyclingclubbackend.dto.resp.ActivityInfoRespDto;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atlcd.hustcyclingclubbackend.dao.entity.Activity;
import com.atlcd.hustcyclingclubbackend.service.ActivityService;
import com.atlcd.hustcyclingclubbackend.dao.mapper.ActivityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* @author 12548
* @description 针对表【activity(车协活动)】的数据库操作Service实现
* @createDate 2024-08-13 15:55:38
*/
@Service
@RequiredArgsConstructor
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity>
    implements ActivityService{

    private final ActivityMapper activityMapper;

    private final ParticipantMapper participantMapper;

    private final JwtUtil jwtUtil;

    @Override
    public Result<ActivityDetailRespDto> getDetail(Integer activityId,String token) {
        Activity activity = activityMapper.selectById(activityId);
        if(activity == null) {
            BusinessException.fail(ResultCodeEnum.ACTIVITY_NOT_EXIST);
        }
        ActivityDetailRespDto activityDetailRespDto = new ActivityDetailRespDto();
        BeanUtil.copyProperties(activity, activityDetailRespDto);
        activityDetailRespDto.setIsParticipant(false);
        if (!jwtUtil.isExpiration(token) && participantMapper.getParticipant(jwtUtil.getUserId(token), activityId) != null) {
            activityDetailRespDto.setIsParticipant(true);
        }
        return Result.ok(activityDetailRespDto);
    }

    @Override
    public Result<List<ActivityInfoRespDto>> listAll(ActivivityAllReqDto activivityAllReqDto) {
        List<Activity> activities = activityMapper.listAll(activivityAllReqDto);
        List<ActivityInfoRespDto> activityInfoRespDtos = new ArrayList<>();
        activityInfoRespDtos = BeanUtil.copyList(activities, ActivityInfoRespDto.class);
        return Result.ok(activityInfoRespDtos);
    }

    @Override
    public Result<Void> saveActivity(UserSaveActivityReqDto userSaveActivityReqDto) {
        Activity activity = new Activity();
        BeanUtil.copyProperties(userSaveActivityReqDto, activity);
        int result = activityMapper.insert(activity);
        if(result == 0) {
            BusinessException.fail(ResultCodeEnum.DB_ERROR);
        }
        return Result.ok();
    }

    @Override
    public Result<Void> updateActivity(UserUpdateActivityReqDto userUpdateActivityReqDto) {
        Activity activity = activityMapper.selectById(userUpdateActivityReqDto.getActivityId());
        if(activity == null) {
            BusinessException.fail(ResultCodeEnum.ACTIVITY_NOT_EXIST);
        }
        BeanUtil.copyProperties(userUpdateActivityReqDto, activity);
        int result = activityMapper.updateById(activity);
        if(result == 0) {
            BusinessException.fail(ResultCodeEnum.DB_ERROR);
        }
        return Result.ok();
    }

    @Override
    public Result<Void> deleteActivity(Integer activityId) {
        Activity activity = activityMapper.selectById(activityId);
        if(activity == null) {
            BusinessException.fail(ResultCodeEnum.ACTIVITY_NOT_EXIST);
        }
        int result = activityMapper.deleteById(activity);
        if(result == 0) {
            BusinessException.fail(ResultCodeEnum.DB_ERROR);
        }
        return Result.ok();
    }

    @Scheduled(cron = "0 0/1 * * * ?") // 每小时执行一次
    public void updateActivityStatus() {
        List<Activity> activities = activityMapper.selectList(new LambdaQueryWrapper<>());
        for (Activity activity : activities) {
            Date now = new Date();
            Date registrationTime = activity.getRegistrationTime();
            Date startTime = activity.getStartTime();
            Date endTime = activity.getEndTime();
            if (now.before(registrationTime)) {
                activity.setStateCode(ActivityCodeParser.StateEnum.REGISTRATION_IN_PROGRESS.getCode());
            } else if (now.before(startTime)) {
                activity.setStateCode(ActivityCodeParser.StateEnum.REGISTRATION_HAS_ENDED.getCode());
            } else if (now.before(endTime)) {
                activity.setStateCode(ActivityCodeParser.StateEnum.ACTIVITY_IN_PROGRESS.getCode());
            } else {
                activity.setStateCode(ActivityCodeParser.StateEnum.ACTIVITY_HAS_ENDED.getCode());
            }
            activityMapper.updateById(activity);
        }
    }
}




