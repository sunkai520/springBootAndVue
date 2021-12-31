package com.sunkai.test.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunkai.test.bean.Result;
import com.sunkai.test.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//登录拦截器
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("Authorization");
        String requsetUrl = request.getRequestURI();
        log.info("拦截的请求是{}",requsetUrl);
        Result result = new Result();
        try {
            JwtUtils.verify(token.replace("bearer ",""));
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
