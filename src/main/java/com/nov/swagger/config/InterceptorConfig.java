package com.nov.swagger.config;

import com.nov.swagger.interceptor.SwaggerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 请求拦截器
 * @author november
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //配置拦截的请求
        registry.addInterceptor(swaggerInterceptor()).addPathPatterns("/swagger-ui/index.html");
    }

    @Bean
    public SwaggerInterceptor swaggerInterceptor(){
        return new SwaggerInterceptor();
    }


}
