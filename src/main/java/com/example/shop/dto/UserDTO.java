package com.example.shop.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ly
 * @date 2020/01/13
 */
@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = -5858100387609993884L;

    private String username;

    private String password;

    private String nickname;

    private Integer age;

}
