package com.atlcd.hustcyclingclubbackend.core.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

/**
 * OpenApi 配置类
 *
 * @author xiongxiaoyang
 * @date 2022/9/1
 */
@Configuration
@SecurityScheme(type = SecuritySchemeType.APIKEY, in = SecuritySchemeIn.HEADER, name = "Authorization", description = "登录 token")
public class OpenApiConfig {

}
