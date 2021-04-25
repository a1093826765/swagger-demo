package com.nov.swagger.controller;

import com.nov.swagger.pojo.Rep;
import com.nov.swagger.pojo.User;
import com.nov.swagger.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 *
 * @Description: swagger Controller模版
 * @Author: november
 * @CreateTime: 2021/4/21 12:28 下午
 * @UpdateTIme:
 */
@Api(tags = "用户模块")
@RestController
@RequestMapping("/controller")
public class TestController {

    @Autowired
    RedisUtil redisUtil;

    /**
     * hidden 隐藏接口 默认为false
     * @param request
     * @return
     */
    @ApiOperation(value = "测试接口",notes = "测详细信息",hidden = true)
    @GetMapping("/test")
    public String TestApi(HttpServletRequest request){
        return "{\"code\":200,\"data\":\""+request.getHeader("Authorization")+"\"}";
    }

    @ApiOperation(value = "更用户状态接口",notes = "更新用户状态详细信息")
    @GetMapping("/getUser")
    public Rep getUser(@ApiParam("用户状态") String state){
        return new Rep("200","成功",state);
    }


    @ApiOperation(value = "用户验证接口",notes = "用户验证接口")
    @PostMapping("/checkUser")
    public Rep getUser(User user){
        return new Rep("200","成功",null);
    }

    @ApiOperation(value = "用户登录接口",notes = "用户登录接口")
    @PostMapping("/login")
    public Rep login(User user){
        //验证账号密码后
        redisUtil.setBit(user.getUsername(),1,true);
        return new Rep("200","成功",user.getUsername());
    }
}
