package com.sunkai.test.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunkai.test.bean.Result;
import com.sunkai.test.jwtUtils.JwtUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;

public class Interceptor  implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String token = request.getHeader("Authorization").replace("bearer ","");
        System.out.println(token+"=-=-=-=-拦截到了");
        Result result = new Result();
        try {
            JwtUtils.verify(token);
            return true;
        }catch (SignatureVerificationException e){
            e.printStackTrace();
            System.out.println("无效签名");
            result.setMsg("无效签名");
        }catch (TokenExpiredException e){
            e.printStackTrace();
            System.out.println("过期");
            result.setMsg("过期");
        }catch (AlgorithmMismatchException e){
            e.printStackTrace();
            System.out.println("算法不一致");
            result.setMsg("算法不一致");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("token无效");
            result.setMsg("token无效");
        }
        try {
            result.setCode(6001);
            //jackJson将对象转成json
            String json = new ObjectMapper().writeValueAsString(result);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().print(json);
        }catch (Exception e){
            System.out.println(e+"拦截器异常");
        }
        return false;
    }
}
