package com.example.shop.plugins.shiro;

import com.example.shop.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author ly
 * @date 2020/01/13
 */
@Slf4j
public class JwtRealm extends AuthorizingRealm {
    /**
     * 多重用一个support
     * 标识这个Realm是专门用来验证JwtToken
     * 不负责验证其它的token(UsernamePasswordToken)
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        // 这个token就是从过滤器中传入的jwtToken
        return token instanceof JwtToken;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //认证
    //这个token就是从过滤器中传入的JwtToken
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String jwt = (String) authenticationToken.getPrincipal();
        if(jwt == null){
            throw new NullPointerException("jwtToken 不允许为空");
        }
        //判断
        JwtUtil jwtUtil = new JwtUtil();
        if(!jwtUtil.isVerify(jwt)){
            throw new UnknownAccountException();
        }
        //下面是验证这个user是否是真是存在的
        String username = (String) jwtUtil.decode(jwt).get("sub");//判断数据库中username是否存在
        log.info("在使用token登录" + username);
        return new SimpleAuthenticationInfo(jwt,jwt,"JwtRealm");
        //这里返回的是类似账号密码的东西,但是JwtToken都是jwt字符串.还需要一个该Realm的类名
    }
}
