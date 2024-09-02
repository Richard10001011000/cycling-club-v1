package com.atlcd.hustcyclingclubbackend.dao.mapper;

import com.atlcd.hustcyclingclubbackend.dao.entity.Activity;
import com.atlcd.hustcyclingclubbackend.dto.req.ActivivityAllReqDto;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.util.StringUtils;

import java.util.List;

/**
* @author 12548
* @description 针对表【activity(车协活动)】的数据库操作Mapper
* @createDate 2024-08-13 15:55:38
* @Entity com.atlcd.hustcyclingclubbackend.dao.entity.Activity
*/
public interface ActivityMapper extends BaseMapper<Activity> {

    default List<Activity> listAll(ActivivityAllReqDto activivityAllReqDto){
        LambdaQueryWrapper<Activity> queryWrapper = new LambdaQueryWrapper<>();
        if (activivityAllReqDto.getCategory() !=null && activivityAllReqDto.getCategory() != 0) {
            queryWrapper.eq(Activity::getCategory, activivityAllReqDto.getCategory());
        }
        if (activivityAllReqDto.getStateCode() != null && activivityAllReqDto.getStateCode() != 0){
            queryWrapper.eq(Activity::getStateCode, activivityAllReqDto.getStateCode());
        }
        if (StringUtils.hasText(activivityAllReqDto.getKeyWord())) {
            queryWrapper.like(Activity::getTitle, activivityAllReqDto.getKeyWord());
        }
        return selectList(queryWrapper);
    }
}




