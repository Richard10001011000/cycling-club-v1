package com.atlcd.hustcyclingclubbackend.core.common.constants;

import com.atlcd.hustcyclingclubbackend.dao.entity.Participant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCodeEnum {
    OK("00000","OK"),
    USER_TOKEN_ERROR("A0001","用户未登录或登录认证失效"),
    USER_NOT_EXIST_ERROR("A0002","用户不存在"),
    STUDENT_NUM_EXIST_ERROR("A0003","该学号已被注册"),
    USER_OLD_PASSWORD_ERROR("A0004","原密码错误"),
    USER_LOGIN_ERROR("A0005", "学号或密码错误"),
    USER_REQUEST_PARAM_ERROR("A0401","用户请求参数异常"),
    SYSTEM_ERROR("B0001", "系统执行出错"),
    DB_ERROR("B0002","数据库错误"),
    ACTIVITY_NOT_EXIST("A1001","活动不存在"),
    ACTIVITY_REGISTRATION_HAS_ENDED("A1002","活动报名已截止"),
    PARTICIPANT_HAS_EXIST("A2001","您已报名该活动"),
    PARTICIPANT_NOT_EXIST("A2002","活动未报名");

    @Schema(description = "错误码，00000-没有错误")
    private final String code;

    @Schema(description = "响应消息")
    private final String message;
}
