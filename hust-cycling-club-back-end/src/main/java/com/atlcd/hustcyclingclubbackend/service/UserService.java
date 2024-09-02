package com.atlcd.hustcyclingclubbackend.service;

import com.atlcd.hustcyclingclubbackend.core.resp.Result;
import com.atlcd.hustcyclingclubbackend.dao.entity.User;
import com.atlcd.hustcyclingclubbackend.dto.req.UserLoginReqDto;
import com.atlcd.hustcyclingclubbackend.dto.req.UserRegisterReqDto;
import com.atlcd.hustcyclingclubbackend.dto.req.UserUpdateDetailReqDto;
import com.atlcd.hustcyclingclubbackend.dto.req.UserUpdatePasswordReqDto;
import com.atlcd.hustcyclingclubbackend.dto.resp.UserDetailRespDto;
import com.atlcd.hustcyclingclubbackend.dto.resp.UserInfoRespDto;
import com.atlcd.hustcyclingclubbackend.dto.resp.UserLoginRespDto;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 12548
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2024-08-12 13:17:13
*/
public interface UserService extends IService<User> {

    Result<UserLoginRespDto> login(UserLoginReqDto userLoginReqDto);

    Result<Void> register(UserRegisterReqDto userRegisterReqDto);

    Result<Void> updatePassword(Integer userId, UserUpdatePasswordReqDto userUpdatePasswordReqDto);

    Result<UserInfoRespDto> getInfo(Integer userId);

    Result<UserDetailRespDto> getDetail(Integer userId);

    Result<Void> updateDetail(Integer userId, UserUpdateDetailReqDto userUpdateDetailReqDto);
}
