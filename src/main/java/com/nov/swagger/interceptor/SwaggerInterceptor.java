package com.nov.swagger.interceptor;

import com.nov.swagger.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @Description:
 * @Author: november
 * @CreateTime: 2021/4/22 6:05 下午
 * @UpdateTIme:
 */
public class SwaggerInterceptor  implements HandlerInterceptor {

    @Autowired
    RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        try {
//            return redisUtil.getBit(request.getParameter("data"),1 );
//        }catch (Exception e){
//            return false;
//        }
        return true;
    }
}
