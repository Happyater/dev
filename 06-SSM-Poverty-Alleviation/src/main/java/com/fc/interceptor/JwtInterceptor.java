package com.fc.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("Authorization");

        response.setContentType("application/json; charset=UTF-8");

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> map = new HashMap<>();
        map.put("code", -1);
        map.put("success", false);
        map.put("data", null);

        if (token == null || token.equals("")) {
            map.put("message", "token不存在，访问被拒绝，请重新登录");

            String json = objectMapper.writeValueAsString(map);

            response.getWriter().write(json);

            return false;
        } else {
            try {
                DecodedJWT decode = JWT.decode(token);

                Claim saltClaim = decode.getClaim("salt");

                String salt = saltClaim.asString();

                JWT.require(Algorithm.HMAC256(salt))
                        .build().verify(token);

                return true;
            } catch (AlgorithmMismatchException e) {
                map.put("message", "算法不匹配");
            } catch (InvalidClaimException e) {
                map.put("message", "非法载荷");
            } catch (TokenExpiredException e) {
                map.put("message", "token已过期");
            } catch (SignatureVerificationException e) {
                map.put("message", "签名验证失败");
            } catch (Exception e) {
                map.put("message", "token有问题");
            }
        }

        String json = objectMapper.writeValueAsString(map);

        response.getWriter().write(json);

        return false;
    }
}
