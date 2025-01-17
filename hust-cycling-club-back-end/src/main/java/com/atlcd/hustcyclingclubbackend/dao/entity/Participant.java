package com.atlcd.hustcyclingclubbackend.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName participant
 */
@TableName(value ="participant")
@Data
public class Participant implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer participantId;

    private Integer activityId;

    private Integer userId;

    private Integer isDeleted;

    private Integer revision;

    private Date createdTime;

    private Date updatedTime;

    private static final long serialVersionUID = 1L;
}