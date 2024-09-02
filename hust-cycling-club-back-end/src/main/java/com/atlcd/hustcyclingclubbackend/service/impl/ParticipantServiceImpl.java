package com.atlcd.hustcyclingclubbackend.service.impl;

import com.atlcd.hustcyclingclubbackend.core.common.constants.ResultCodeEnum;
import com.atlcd.hustcyclingclubbackend.core.exception.BusinessException;
import com.atlcd.hustcyclingclubbackend.core.resp.Result;
import com.atlcd.hustcyclingclubbackend.core.util.BeanUtil;
import com.atlcd.hustcyclingclubbackend.dao.entity.Activity;
import com.atlcd.hustcyclingclubbackend.dao.mapper.ActivityMapper;
import com.atlcd.hustcyclingclubbackend.dto.resp.ActivityInfoRespDto;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atlcd.hustcyclingclubbackend.dao.entity.Participant;
import com.atlcd.hustcyclingclubbackend.service.ParticipantService;
import com.atlcd.hustcyclingclubbackend.dao.mapper.ParticipantMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* @author 12548
* @description 针对表【participant(活动参加人员)】的数据库操作Service实现
* @createDate 2024-08-12 17:54:31
*/
@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl extends ServiceImpl<ParticipantMapper, Participant>
    implements ParticipantService{

    private final ParticipantMapper participantMapper;

    private final ActivityMapper activityMapper;

    @Override
    public Result<List<ActivityInfoRespDto>> listParticipant(Integer userId) {
        List<Integer> activityIds = participantMapper.listActivityIdByUserId(userId);
        List<Activity> activities = activityMapper.selectBatchIds(activityIds);
        List<ActivityInfoRespDto> activityInfoRespDtos = new ArrayList<>();
        activityInfoRespDtos = BeanUtil.copyList(activities,ActivityInfoRespDto.class);
        return Result.ok(activityInfoRespDtos);
    }

    @Override
    public Result<Void> saveParticipant(Integer userId,Integer activityId) {
        Activity activity = activityMapper.selectById(activityId);
        if(activity == null) {
            BusinessException.fail(ResultCodeEnum.ACTIVITY_NOT_EXIST);
        }
        Date now = new Date();
        if(now.after(activity.getRegistrationTime())){
            BusinessException.fail(ResultCodeEnum.ACTIVITY_REGISTRATION_HAS_ENDED);
        }
        LambdaQueryWrapper<Participant> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Participant::getUserId, userId).eq(Participant::getActivityId, activityId);
        System.out.println(participantMapper.selectCount(queryWrapper));
        if (participantMapper.getParticipant(userId, activityId) != null) {
            BusinessException.fail(ResultCodeEnum.PARTICIPANT_HAS_EXIST);
        }
        Participant participant = new Participant();
        participant.setUserId(userId);
        participant.setActivityId(activityId);
        int result = participantMapper.insert(participant);
        if(result == 0){
            BusinessException.fail(ResultCodeEnum.DB_ERROR);
        }
        return Result.ok();
    }

    @Override
    public Result<Void> deleteParticipant(Integer userId, Integer activityId) {
        Participant participant = participantMapper.getParticipant(userId, activityId);
        if(participant == null) {
            BusinessException.fail(ResultCodeEnum.PARTICIPANT_NOT_EXIST);
        }
        participantMapper.deleteById(participant);
        return Result.ok();
    }
}




