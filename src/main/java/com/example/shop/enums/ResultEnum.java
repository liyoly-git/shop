package com.example.shop.enums;

import lombok.Getter;

/**
 * 信息返回枚举类,统一异常管理
 *
 * @author ly
 * @date 2020/01/10
 */
@Getter
public enum ResultEnum {

    /**
     * 结果枚举集
     */
    PARAMS_ERR(1000, "参数错误"),
    NO_PERMISSION(9998,"无操作权限"),
    NO_USERNAME(401,"用户名不能为空"),
    NOT_SUPPORTED_ALGORITHM(600,"不支持此算法"),
    USERNAME_PASSWORD_ERR(700,"用户名或密码错误"),
    EXIST_USER(800,"用户已存在"),
    UNKNOWN_ERR(9999,"未知错误");

    private Integer code;
    private String message;

    ResultEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }
}
