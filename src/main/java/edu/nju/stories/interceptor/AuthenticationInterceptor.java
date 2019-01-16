package edu.nju.stories.interceptor;

import edu.nju.stories.annotation.LoginRequired;
import edu.nju.stories.constants.ErrorCode;
import edu.nju.stories.constants.Headers;
import edu.nju.stories.exception.LogicException;
import edu.nju.stories.models.UserModel;
import edu.nju.stories.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


public class AuthenticationInterceptor implements HandlerInterceptor {


    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 判断接口是否需要登录
        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);
        // 有 @LoginRequired 注解，需要认证
        if (methodAnnotation != null) {
            // 判断是否存在令牌信息，如果存在，则允许登录
            String accessToken = request.getHeader(Headers.ACCESS_TOKEN);
            String userId = request.getHeader(Headers.ACCESS_USER_ID);
            if (null == accessToken) {
                throw new LogicException(ErrorCode.NOT_LOGIN, "没有登录");
            }
            UserModel userModel = userService.checkToken(accessToken);
            if (userModel == null || !userModel.get_id().equals(userId)) {
                throw new LogicException(ErrorCode.NOT_LOGIN, "没有登录");
            }
        }
        return true;
    }

}
