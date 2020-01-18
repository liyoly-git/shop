package com.example.shop.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.shop.enums.ResultEnum;
import com.example.shop.exception.domain.ApiException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 总的来说,工具类中有三个方法
 * 获取JwtToken,获取JwtToken中封装的信息,判断JWtToken是否存在
 * 1.encode(),参数是=签发人,存在时间,一些其他的信息=.返回值是JwtToken对应的字符串
 * 2.decode(),参数是=JwtToken=.返回值是荷载部分的键值对
 * 3.isVerify(),参数是=JwtToken.返回值是这个JwtToken是否存在
 * @author ly
 * @date 2020/01/13
 */
public class JwtUtil {

    /**
     * 创建默认的秘钥和算法,供无参的构造方法使用
     */
    private static final String DEFAULT_BASE64_ENCODED_SECRET_KEY = "badbabe";
    private static final SignatureAlgorithm DEFAULT_SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    public JwtUtil(){
        this(DEFAULT_BASE64_ENCODED_SECRET_KEY,DEFAULT_SIGNATURE_ALGORITHM);
    }

    private final String base64EncodedSecretKey;
    private final SignatureAlgorithm signatureAlgorithm;

    public JwtUtil(String secretKey, SignatureAlgorithm signatureAlgorithm) {
        this.base64EncodedSecretKey = Base64.encodeBase64String(secretKey.getBytes());
        this.signatureAlgorithm = signatureAlgorithm;
    }

    /**
     * 这里就是产生jwt字符串的地方
     * jwt字符串包括三个部分
     * 1.header
     *      -当前字符串的类型,一般都是"jwt"
     *      -哪种算法加密,"HS256或者其他的加密算法
     *      所以一般都是固定的,没有什么变化
     * 2.payload
     *      一般有四个是最常见的标准字段(下面有)
     *      iat:签发时间,也就是这个jwt什么时候生成的
     *      jti:JWT的唯一标识
     *      iss:签发人,一般都是username或者userId
     *      exp:过期时间
     */
    public String encode(String iss, long ttlMillis, Map<String,Object> claims){
        // iss签发人,ttlMillis生存时间,claims是指还想要在jwt中存储的一些非隐私信息
        if (claims == null){
            claims = new HashMap<>();
        }
        long nowMillis = System.currentTimeMillis();

        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setId(UUID.randomUUID().toString())//2.这个是JWT的唯一标识
                .setIssuedAt(new Date(nowMillis))//1.这个地方就是以毫秒为单位,换算当前系统时间生成的iat
                .setSubject(iss)//3.签发人,也就是JWT是谁给的(逻辑上一般都是username或者userId)
                .signWith(signatureAlgorithm,base64EncodedSecretKey);//这个地方是生成jwt使用的算法和秘钥

        if (ttlMillis >= 0){
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);//4.过期时间,这个也是使用毫秒生成的,使用当前时间+前面传入的持续时间生成
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     * 相当于encode的方向,传入jwtToken生成对应的username和password等字段.claims就是一个map
     * 也就是拿到荷载部分的所有键值对
     */
    public Claims decode(String jwtToken){

        // 得到 DefaultJwtParser
        return Jwts.parser()
                // 设置签名的秘钥
                .setSigningKey(base64EncodedSecretKey)
                // 设置需要解析的 jwt
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    /**
     * 判断jwtToken是否合法
     */
    public boolean isVerify(String jwtToken){
        //这个是官方的校检规则,这里只写了一个"效验算法",可以自己加
        Algorithm algorithm = null;
        switch (signatureAlgorithm){
            case HS256:
                algorithm = Algorithm.HMAC256(Base64.decodeBase64(base64EncodedSecretKey));
                break;
            default:
                throw new ApiException(ResultEnum.NOT_SUPPORTED_ALGORITHM);
        }
        JWTVerifier verifier = JWT.require(algorithm).build();
        verifier.verify(jwtToken); // 校检不通过会抛出异常
        // 判断合法的标准: 1.头部和荷载部分有没有篡改过.2.没有过期
        return true;
    }

    public static void main(String[] args){
        JwtUtil util = new JwtUtil("tom",SignatureAlgorithm.HS256);
        // 以tom作为秘钥,以HS256加密
        Map<String,Object> map = new HashMap<>();
        map.put("username","tom");
        map.put("password","123456");
        map.put("age",20);

        String jwtToken = util.encode("tom",30000,map);

        System.out.println(jwtToken);

        util.decode(jwtToken).forEach((key, value) -> System.out.println(key + ": " + value));
    }
}