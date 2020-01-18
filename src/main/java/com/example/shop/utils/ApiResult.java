package com.example.shop.utils;

import com.example.shop.enums.ResultEnum;
import lombok.Data;

/**
 * 通用结果返回
 *
 * @author ly
 * @date 2020/01/10
 */
@Data
public class ApiResult<T> {
    
    private Integer code;
    
    private T content;
    
    private String message;

    public static <T> ApiResult success(){
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setCode(200);
        apiResult.setContent(null);
        apiResult.setMessage("操作成功");
        return apiResult;
    }

    public static <T> ApiResult success(T content){
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setCode(200);
        apiResult.setContent(content);
        apiResult.setMessage("操作成功");
        return apiResult;
    }

    public static <T> ApiResult error(){
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setCode(300);
        apiResult.setContent(null);
        apiResult.setMessage("操作失败");
        return apiResult;
    }

    public static <T> ApiResult error(Integer code, String message) {
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setCode(code);
        apiResult.setContent(null);
        apiResult.setMessage(message);
        return apiResult;
    }

    public static <T> ApiResult error(ResultEnum resultEnum) {
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setCode(resultEnum.getCode());
        apiResult.setContent(null);
        apiResult.setMessage(resultEnum.getMessage());
        return apiResult;
    }
}
