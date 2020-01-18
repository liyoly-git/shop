package com.example.shop.exception.domain;

import com.example.shop.enums.ResultEnum;
import lombok.Getter;

/**
 * 接口异常
 *
 * @author ly
 * @date 2020/01/10
 */
@Getter
public class ApiException extends RuntimeException{

    private static final long serialVersionUID = -3388607029108265844L;
    private Integer code;

    public ApiException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public ApiException(Integer code,String message){
        super(message);
        this.code = code;
    }

    @Override
    public String toString() {
        return "ApiException{" +
                "code=" + code +
                ", message=" + getMessage() +
                '}';
    }
}
