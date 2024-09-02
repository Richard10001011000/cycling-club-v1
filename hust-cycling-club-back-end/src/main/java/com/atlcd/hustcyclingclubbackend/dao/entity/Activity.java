package com.atlcd.hustcyclingclubbackend.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName activity
 */
@TableName(value ="activity")
@Data
public class Activity implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer activityId;

    private String title;

    private String introduce;

    private String imgAddress;

    private Integer speed;

    private Integer distance;

    private String gatheringPlace;

    private Integer category;

    private Date registrationTime;

    private Date startTime;

    private Date endTime;

    private Integer stateCode;

    private Integer isDeleted;

    private Integer revision;

    private Date createdTime;

    private Date updatedTime;

    private static final long serialVersionUID = 1L;
}