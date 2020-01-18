package com.example.shop.plugins.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author ly
 * @date 2020/01/13
 */
// 类似UsernamePasswordToken
public class JwtToken implements AuthenticationToken {

    private String jwt;

    public JwtToken(String jwt){
        this.jwt = jwt;
    }

    @Override // 类似用户名
    public Object getPrincipal() {
        return jwt;
    }

    @Override //类似密码
    public Object getCredentials() {
        return jwt;
    }
    //返回的都是jwt
}
