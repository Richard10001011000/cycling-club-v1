package com.atlcd.hustcyclingclubbackend.core.interceptor;


import com.atlcd.hustcyclingclubbackend.core.auth.UserHolder;
import com.atlcd.hustcyclingclubbackend.core.common.constants.ResultCodeEnum;
import com.atlcd.hustcyclingclubbackend.core.exception.BusinessException;
import com.atlcd.hustcyclingclubbackend.core.util.JwtUtil;
import com.atlcd.hustcyclingclubbackend.dao.mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Token 解析拦截器
 *
 * @author Richard
 * @date 2024/8/12
 */
@Component
@RequiredArgsConstructor
public class TokenParseInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    private final UserMapper userMapper;

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {
        // 获取登录 JWT
        String token = request.getHeader("Authorization");
        if (!StringUtils.hasText(token) || jwtUtil.isExpiration(token)) {
            BusinessException.fail(ResultCodeEnum.USER_TOKEN_ERROR);
        }
        Integer userId = jwtUtil.getUserId(token);
        if(userMapper.selectById(userId) == null){
            BusinessException.fail(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        // 解析 token 并保存
        UserHolder.setUserId(userId);
        return true;
    }

    /**
     * DispatcherServlet 完全处理完请求后调用，出现异常照常调用
     */
    @SuppressWarnings("NullableProblems")
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 清理当前线程保存的用户数据
        UserHolder.clear();
    }

}
