package com.sunkai.test.jwtUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Data;
import java.util.Calendar;
import java.util.Map;
@Data
public class JwtUtils {
    //签名秘钥 不可随意泄露
    private static final String SECRET = "jwt_secret";
    public static String createJwt(Map<String,String> map){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND,20);//签名过期时间 单位秒
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
