package com.atlcd.hustcyclingclubbackend.service.impl;

import com.atlcd.hustcyclingclubbackend.core.common.constants.ResultCodeEnum;
import com.atlcd.hustcyclingclubbackend.core.exception.BusinessException;
import com.atlcd.hustcyclingclubbackend.core.resp.Result;
import com.atlcd.hustcyclingclubbackend.core.util.BeanUtil;
import com.atlcd.hustcyclingclubbackend.core.util.JwtUtil;
import com.atlcd.hustcyclingclubbackend.dto.req.UserLoginReqDto;
import com.atlcd.hustcyclingclubbackend.dto.req.UserRegisterReqDto;
import com.atlcd.hustcyclingclubbackend.dto.req.UserUpdateDetailReqDto;
import com.atlcd.hustcyclingclubbackend.dto.req.UserUpdatePasswordReqDto;
import com.atlcd.hustcyclingclubbackend.dto.resp.UserDetailRespDto;
import com.atlcd.hustcyclingclubbackend.dto.resp.UserInfoRespDto;
import com.atlcd.hustcyclingclubbackend.dto.resp.UserLoginRespDto;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atlcd.hustcyclingclubbackend.dao.entity.User;
import com.atlcd.hustcyclingclubbackend.service.UserService;
import com.atlcd.hustcyclingclubbackend.dao.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
* @author 12548
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2024-08-12 13:17:13
*/
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    private final UserMapper userMapper;

    private final JwtUtil jwtUtil;

    @Override
    public Result<UserLoginRespDto> login(UserLoginReqDto userLoginReqDto) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getStudentNum, userLoginReqDto.getStudentNum())
                .eq(User::getPasswordMd5,userLoginReqDto.getPasswordMd5());
        User loginUser = userMapper.selectOne(queryWrapper);
        if(loginUser == null){
            BusinessException.fail(ResultCodeEnum.USER_LOGIN_ERROR);
        }
        return Result.ok(UserLoginRespDto.builder().nickName(loginUser.getNickName())
                .token(jwtUtil.createToken(loginUser.getUserId())).build());
    }

    @Override
    public Result<Void> register(UserRegisterReqDto userRegisterReqDto) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getStudentNum, userRegisterReqDto.getStudentNum());
        User user = userMapper.selectOne(queryWrapper);
        if (user != null){
            BusinessException.fail(ResultCodeEnum.STUDENT_NUM_EXIST_ERROR);
        }
        User resgisterUser = new User();
        BeanUtil.copyProperties(userRegisterReqDto, resgisterUser);
        resgisterUser.setCreatedTime(new Date());
        resgisterUser.setUpdatedTime(new Date());
        int result = userMapper.insert(resgisterUser);
        if (result == 0){
            BusinessException.fail(ResultCodeEnum.DB_ERROR);
        }
        return Result.ok();
    }

    @Override
    public Result<Void> updatePassword(Integer userId, UserUpdatePasswordReqDto userUpdatePasswordReqDto) {
        User user = userMapper.selectById(userId);
        if(!user.getPasswordMd5().equals(userUpdatePasswordReqDto.getOldPasswordMd5())){
            BusinessException.fail(ResultCodeEnum.USER_OLD_PASSWORD_ERROR);
        }
        user.setPasswordMd5(userUpdatePasswordReqDto.getNewPasswordMd5());
        user.setUpdatedTime(new Date());
        int result = userMapper.updateById(user);
        if (result == 0){
            BusinessException.fail(ResultCodeEnum.DB_ERROR);
        }
        return Result.ok();
    }

    @Override
    public Result<UserInfoRespDto> getInfo(Integer userId) {
        User user = userMapper.selectById(userId);
        UserInfoRespDto userInfoRespDto = new UserInfoRespDto();
        BeanUtil.copyProperties(user, userInfoRespDto);
        return Result.ok(userInfoRespDto);
    }

    @Override
    public Result<UserDetailRespDto> getDetail(Integer userId) {
        User user = userMapper.selectById(userId);
        UserDetailRespDto userDetailRespDto = new UserDetailRespDto();
        BeanUtil.copyProperties(user, userDetailRespDto);
        return Result.ok(userDetailRespDto);
    }

    @Override
    public Result<Void> updateDetail(Integer userId, UserUpdateDetailReqDto userUpdateDetailReqDto) {
        User user = userMapper.selectById(userId);
        if (user == null){
            BusinessException.fail(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        BeanUtil.copyProperties(userUpdateDetailReqDto, user);
        int result = userMapper.updateById(user);
        if(result == 0){
            BusinessException.fail(ResultCodeEnum.DB_ERROR);
        }
        return Result.ok();
    }
}




