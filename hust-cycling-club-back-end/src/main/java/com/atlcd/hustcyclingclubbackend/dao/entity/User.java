package com.atlcd.hustcyclingclubbackend.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer userId;

    private String studentNum;

    private String passwordMd5;

    private String nickName;

    private String signature;

    private String realName;

    private String gender;

    private String qq;

    private String phoneNumber;

    private String college;

    private Integer isLocked;

    private Integer isAdmin;

    private Integer isDeleted;

    private Integer revision;

    private Date createdTime;

    private Date updatedTime;

    private static final long serialVersionUID = 1L;
}