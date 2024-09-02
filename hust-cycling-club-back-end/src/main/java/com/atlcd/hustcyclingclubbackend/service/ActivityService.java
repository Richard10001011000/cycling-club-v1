package com.atlcd.hustcyclingclubbackend.service;

import com.atlcd.hustcyclingclubbackend.core.resp.Result;
import com.atlcd.hustcyclingclubbackend.dao.entity.Activity;
import com.atlcd.hustcyclingclubbackend.dto.req.ActivivityAllReqDto;
import com.atlcd.hustcyclingclubbackend.dto.req.UserSaveActivityReqDto;
import com.atlcd.hustcyclingclubbackend.dto.req.UserUpdateActivityReqDto;
import com.atlcd.hustcyclingclubbackend.dto.resp.ActivityDetailRespDto;
import com.atlcd.hustcyclingclubbackend.dto.resp.ActivityInfoRespDto;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 12548
* @description 针对表【activity(车协活动)】的数据库操作Service
* @createDate 2024-08-13 15:55:38
*/
public interface ActivityService extends IService<Activity> {

    Result<ActivityDetailRespDto> getDetail(Integer activityId,String token);

    Result<List<ActivityInfoRespDto>> listAll(ActivivityAllReqDto activivityAllReqDto);

    Result<Void> saveActivity(UserSaveActivityReqDto userSaveActivityReqDto);

    Result<Void> updateActivity(UserUpdateActivityReqDto userUpdateActivityReqDto);

    Result<Void> deleteActivity(Integer activityId);
}
