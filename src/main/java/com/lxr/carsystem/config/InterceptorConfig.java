package com.lxr.carsystem.config;

import com.lxr.carsystem.interceptor.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter{
    /**
     * 添加拦截器，指定拦截的路径和不拦截的路径
    * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInterceptor()).
                addPathPatterns("/user/sellCar/**").
                addPathPatterns("/user/personCenter/**").
                addPathPatterns("/user/buyCar/**").
                addPathPatterns("/user/carBaike/**").excludePathPatterns("/user/info/**");

    }
}
