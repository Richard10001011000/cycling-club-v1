package com.atlcd.hustcyclingclubbackend.service;

import com.atlcd.hustcyclingclubbackend.core.resp.Result;
import com.atlcd.hustcyclingclubbackend.dao.entity.Participant;
import com.atlcd.hustcyclingclubbackend.dto.resp.ActivityInfoRespDto;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 12548
* @description 针对表【participant(活动参加人员)】的数据库操作Service
* @createDate 2024-08-12 17:54:31
*/
public interface ParticipantService extends IService<Participant> {

    Result<List<ActivityInfoRespDto>> listParticipant(Integer userId);

    Result<Void> saveParticipant(Integer userId,Integer activityId);

    Result<Void> deleteParticipant(Integer userId, Integer activityId);
}
