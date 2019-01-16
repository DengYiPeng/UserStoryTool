package edu.nju.stories.config;

import edu.nju.stories.interceptor.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfigure implements WebMvcConfigurer {

    @Autowired
    AuthenticationInterceptor authenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 可添加多个，这里选择拦截所有请求地址，进入后判断是否有加注解即可
        registry.addInterceptor(authenticationInterceptor).addPathPatterns("/user/**").addPathPatterns("/map/**")
        .addPathPatterns("/card/**");

    }
}
