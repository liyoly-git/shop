package com.example.shop.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author ly
 * @date 2020/01/10
 */
@Data
public class VueLoginInfoVO implements Serializable {

    private static final long serialVersionUID = 3103554885603629445L;

    @NotNull(message = "用户名不允许为空")
    private String username;

    @NotNull(message = "密码不允许为空")
    private String password;
}
