package com.example.shop.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ly
 * @date 2020/01/10
 */
@Data
@TableName("shop_user")
public class UserDO implements Serializable {

    private static final long serialVersionUID = -8863724987236602493L;
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private String nickname;

}
