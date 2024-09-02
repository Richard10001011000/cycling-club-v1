package com.atlcd.hustcyclingclubbackend.core.exception;

import com.atlcd.hustcyclingclubbackend.core.common.constants.ResultCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=true)
public class BusinessException extends RuntimeException {
    private final ResultCodeEnum resultCodeEnum;

    public BusinessException(ResultCodeEnum resultCodeEnum) {
        // 不调用父类 Throwable的fillInStackTrace() 方法生成栈追踪信息，提高应用性能
        // 构造器之间的调用必须在第一行
        super(resultCodeEnum.getMessage(), null, false, false);
        this.resultCodeEnum = resultCodeEnum;
    }

    public static BusinessException fail(ResultCodeEnum resultCodeEnum){
        throw  new BusinessException(resultCodeEnum);
    }
}
