package com.nov.swagger.pojo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 *
 * @Description:
 * @Author: november
 * @CreateTime: 2021/4/22 5:48 下午
 * @UpdateTIme:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rep {
    @ApiModelProperty(value = "返回值",required=true)
    private String code;
    @ApiModelProperty(value = "返回说明",required=true)
    private String message;
    @ApiModelProperty(value = "数据包",required=true)
    private String data;
}
