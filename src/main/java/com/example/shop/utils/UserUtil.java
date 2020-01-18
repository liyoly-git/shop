package com.example.shop.utils;

import org.springframework.util.DigestUtils;

/**
 * 用户工具类
 *
 * @author ly
 * @date 2020/01/13
 */
public class UserUtil {

    /**
     * md5加密
     * @param password
     * @return
     */
    public static String md5DigestAsHex(String password){
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }
}
