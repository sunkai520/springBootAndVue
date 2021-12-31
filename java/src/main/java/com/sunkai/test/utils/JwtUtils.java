package com.sunkai.test.utils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Calendar;
import java.util.Map;
/**
 *jwt工具类 使用java-jwt
 * token由三部分组成
 * 1:header头部可默认。
 * 2:荷载 用户信息相关的信息进行base64加密(切记不可将密码也写入)
 * 3:签名 是将secret签名+header+荷载再进行加密
 * **/
public class JwtUtils {
    //签名秘钥 不可随意泄露
    private static final String SECRET = "jwt_secret";
    public static String createJwt(Map<String,String> map){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,1);//签名过期时间 单位天
        JWTCreator.Builder builder = JWT.create();
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        builder.withExpiresAt(calendar.getTime());
       return builder.sign(Algorithm.HMAC256(SECRET));
    }
    //验证token
    public static void  verify(String token){
        JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
    }
    //获取令牌荷载(用户信息)
    public static DecodedJWT getToken(String token){
        return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
    }
}
