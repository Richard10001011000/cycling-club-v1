package com.atlcd.hustcyclingclubbackend.dto.resp;

import lombok.Data;

import java.util.Date;

@Data
public class ActivityDetailRespDto {
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

    private Boolean isParticipant;
}
