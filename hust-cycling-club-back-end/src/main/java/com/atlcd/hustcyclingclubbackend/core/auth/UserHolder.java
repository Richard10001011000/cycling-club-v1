package com.atlcd.hustcyclingclubbackend.core.auth;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserHolder {

    /**
     * 当前线程的用户ID
     */
    private static final ThreadLocal<Integer> userIdTL = new ThreadLocal<>();

    public void setUserId(Integer userId) {
        userIdTL.set(userId);
    }

    public Integer getUserId() {
        return userIdTL.get();
    }

    public void clear() {
        userIdTL.remove();
    }
}
