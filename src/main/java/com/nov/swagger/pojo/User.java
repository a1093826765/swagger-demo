package com.nov.swagger.pojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Created by IntelliJ IDEA.
 *
 * @Description:
 * @Author: november
 * @CreateTime: 2021/4/22 3:26 下午
 * @UpdateTIme:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户接口返回参数")
public class User {

    /**
     * required设置是否必参
     */
    @ApiModelProperty(value = "用户名",required=true)
    private String username;

    @ApiModelProperty(value = "密码",required=true)
    private String password;

    @ApiModelProperty(value = "用户类型",required=false)
    private String type;

    /**
     *   配置额外属性，在swagger不显示
     */
    @ApiModelProperty(value = "额外属性",hidden = true)
    private String info;
}
