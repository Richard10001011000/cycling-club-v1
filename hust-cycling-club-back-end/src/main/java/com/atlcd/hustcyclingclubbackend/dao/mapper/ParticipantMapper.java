package com.atlcd.hustcyclingclubbackend.dao.mapper;

import com.atlcd.hustcyclingclubbackend.dao.entity.Participant;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author 12548
* @description 针对表【participant(活动参加人员)】的数据库操作Mapper
* @createDate 2024-08-12 17:54:31
* @Entity com.atlcd.hustcyclingclubbackend.dao.entity.Participant
*/
public interface ParticipantMapper extends BaseMapper<Participant> {
    default List<Integer> listActivityIdByUserId(Integer userId) {
        LambdaQueryWrapper<Participant> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Participant::getUserId, userId);
        return selectList(queryWrapper)
                .stream()
                .map(Participant::getActivityId).collect(Collectors.toList());
    }

    default List<Integer> listUserIdByActivityId(Integer activityId) {
        LambdaQueryWrapper<Participant> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Participant::getActivityId, activityId);
        return selectList(queryWrapper)
                .stream()
                .map(Participant::getUserId).collect(Collectors.toList());
    }

    default Participant getParticipant(Integer userId, Integer activityId) {
        LambdaQueryWrapper<Participant> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Participant::getUserId, userId).eq(Participant::getActivityId, activityId);
        return selectOne(queryWrapper);
    }
}




