package com.atlcd.hustcyclingclubbackend.core.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;


public class ActivityCodeParser {

    @Getter
    @AllArgsConstructor
    public enum CategoryEnum {
        DAILY_LEISURE_ACTIVITY(1,"日常休闲"),
        LONG_DISTANCE_ACTIVITY(2,"周末小长途"),
        HOLIDAY_LONG_DISTANCE_ACTIVITY(3,"假期长途");

        private final int code;
        private final String category;
    }

    @Getter
    @AllArgsConstructor
    public enum StateEnum {
        REGISTRATION_IN_PROGRESS(1,"正在报名中"),
        REGISTRATION_HAS_ENDED(2,"报名已截止"),
        ACTIVITY_IN_PROGRESS(3,"活动进行中"),
        ACTIVITY_HAS_ENDED(4,"活动已结束");

        private final int code;
        private final String state;
    }

    private static final Map<Integer,String> categoryMap = new HashMap<>();
    static {
        // 初始化映射
        for (CategoryEnum categoryEnum : CategoryEnum.values()) {
            categoryMap.put(categoryEnum.getCode(), categoryEnum.getCategory());
        }
    }

    public static String getCategory(int code) {
        return categoryMap.getOrDefault(code, "Unknown Category");
    }

    private static final Map<Integer,String> stateMap = new HashMap<>();
    static {
        for (StateEnum stateEnum : StateEnum.values()) {
            stateMap.put(stateEnum.getCode(), stateEnum.getState());
        }
    }

    public static String getState(int code) {
        return stateMap.getOrDefault(code, "Unknown Status");
    }
}
