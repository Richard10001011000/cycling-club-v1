package com.atlcd.hustcyclingclubbackend.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 功能描述: 跨域配置
 *
 * @author Richard
 * @date 2024/8/12
 */
@ConfigurationProperties(prefix = "hust-cycling-club.cors")
public record CorsProperties(List<String> allowOrigins) {

}
