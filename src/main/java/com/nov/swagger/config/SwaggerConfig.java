package com.nov.swagger.config;

import io.swagger.models.parameters.Parameter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Description: swagger配置类
 * @Author: november
 * @CreateTime: 2021/4/21 1:00 下午
 * @UpdateTIme:
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {

    /**
     *
     * Created by IntelliJ IDEA.
     * @Description: 配置localhost环境
     * @author november
     * @CreateTime: 2021/4/22 3:19 下午
     * @UpdateTIme:
     * @param
     * @return
     */
    @Bean
    public Docket localDocket(Environment environment){
        //设置swagger的版本，目前maven版本是3.0
        Docket docket = new Docket(DocumentationType.OAS_30)
                //配置文档信息
                .apiInfo(apiInfo())
                //配置是否启动swagger
                .enable(getFlag(environment))
                //配置环境/多个分组
                .groupName("本地环境")
                .select()
                //扫描全部
                .apis(RequestHandlerSelectors.any())
//                //都不扫描
//                .apis(RequestHandlerSelectors.none())
//                //只扫描类上对应的注解
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
//                //只扫描对应的注解
//                .apis(RequestHandlerSelectors.withMethodAnnotation(GetMapping.class))
//                //只扫描指定包
//                .apis(RequestHandlerSelectors.basePackage("com.nov.swagger.controller"))
//                //过滤路径
//                .paths(PathSelectors.ant("/controller/**"))
//                //过滤 正则匹配
//                .paths(PathSelectors.regex(""))
                .build()
                //添加头部（全局）
                .securitySchemes(securitySchemes())
                //添加头部（单独接口）
                .globalRequestParameters(globalRequestParameters())
                //添加字段
                .securityContexts(securityContexts())
                .host("123123")
                .host("123123")
                ;
        return docket;
    }

    /**
     *
     * Created by IntelliJ IDEA.
     * @Description: 配置text环境
     * @author november
     * @CreateTime: 2021/4/22 3:21 下午
     * @UpdateTIme:
     * @param
     * @return
     */
    @Bean
    public Docket TestDocket(Environment environment){
        //设置swagger的版本，目前maven版本是3.0
        Docket docket = new Docket(DocumentationType.OAS_30)
                //配置文档信息
                .apiInfo(apiInfo())
                //配置是否启动swagger
                .enable(getFlag(environment))
                //配置环境/多个分组
                .groupName("测试环境")
                .select()
                //扫描全部
                .apis(RequestHandlerSelectors.any())
//                //都不扫描
//                .apis(RequestHandlerSelectors.none())
//                //只扫描类上对应的注解
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
//                //只扫描对应的注解
//                .apis(RequestHandlerSelectors.withMethodAnnotation(GetMapping.class))
//                //只扫描指定包
//                .apis(RequestHandlerSelectors.basePackage("com.nov.swagger.controller"))
//                //过滤路径
//                .paths(PathSelectors.ant("/controller/**"))
//                //过滤 正则匹配
//                .paths(PathSelectors.regex(""))
                .build();
        return docket;
    }

    /**
     *
     * Created by IntelliJ IDEA.
     * @Description: 配置文档信息
     * @author november
     * @CreateTime: 2021/4/21 1:27 下午
     * @UpdateTIme:
     * @param
     * @return
     */
    private  ApiInfo apiInfo(){
        //设置作者信息
        Contact contact = new Contact("作者名/公司名", "访问地址", "联系邮箱");
        return new ApiInfoBuilder()
                //设置api文档标题
                .title("xxx系统 APIs")
                //设置文档描述
                .description("xxx描述")
                //网站地址
                .termsOfServiceUrl("http://localhost:8080/swagger-ui/index.html")
                //设置作者信息（可以不设置）
                .contact(contact)
                //api文档版本
                .version("1.0")
                //许可名（可以不设置）
                .license("许可名")
                //许可地址（可以不设置）
                .licenseUrl("http://localhost:8080/swagger-ui/index.html")
                .build();

    }

    /**
     *
     * Created by IntelliJ IDEA.
     * @Description: 判断当前是否处于正式环境
     * @author november
     * @CreateTime: 2021/4/22 3:24 下午
     * @UpdateTIme:
     * @param
     * @return
     */
    private boolean getFlag(Environment environment){
        //判断环境是否处于测试环境，如果测试环境则启用swagger
        return environment.acceptsProfiles(Profiles.of("dev","test"));
    }

    /**
     *
     * Created by IntelliJ IDEA.
     * @Description: 全局字段
     * @author november
     * @CreateTime: 2021/4/22 5:28 下午
     * @UpdateTIme:
     * @param
     * @return
     */
    private List<SecurityScheme> securitySchemes() {
        ApiKey apiKey = new ApiKey("Authorization", "Authorization", "header");
        return Collections.singletonList(apiKey);
    }



    /**
     *
     * Created by IntelliJ IDEA.
     * @Description: 单独接口字段
     * @author november
     * @CreateTime: 2021/4/22 5:29 下午
     * @UpdateTIme:
     * @param
     * @return
     */
    private List<RequestParameter> globalRequestParameters() {
        RequestParameterBuilder parameterBuilder = new RequestParameterBuilder()
                .in(ParameterType.HEADER)
                .name("sign")
                .required(false)
                .query(param -> param.model(model -> model.scalarModel(ScalarType.STRING)));
        return Collections.singletonList(parameterBuilder.build());
    }

    /**
     *
     * Created by IntelliJ IDEA.
     * @Description: 增加字段
     * @author november
     * @CreateTime: 2021/4/22 5:28 下午
     * @UpdateTIme:
     * @param
     * @return
     */
    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences=new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        securityReferences.add(new SecurityReference("sign", authorizationScopes));
        return securityReferences;
    }

    /**
     *
     * Created by IntelliJ IDEA.
     * @Description: 增加字段
     * @author november
     * @CreateTime: 2021/4/22 5:28 下午
     * @UpdateTIme:
     * @param
     * @return
     */
    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts=new ArrayList<>();
        securityContexts.add(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("^(?!auth).*$"))
                        .build());
        return securityContexts;
    }




}
