package com.cnki.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * JwtUtil:用来进行签名和效验Token
 *
 * @author li-lb
 */
@Slf4j
@Component
public class JwtUtil {

    /**
     * # 加密密钥
     */
    private static String secret;
    public static String getSecret() {
        return secret;
    }
    @Value("${jwt.secret}")
    public void setSecret(String secret) {
        JwtUtil.secret = secret;
    }

    /**
     * JWT验证过期时间 expire 分钟
     */
    private static String expire;

    public static String getExpire() {
        return expire;
    }
    @Value("${jwt.expire}")
    public void setExpire(String expire) {
        JwtUtil.expire = expire;
    }

    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @return 是否正确
     */
    public static boolean verify(String token, String username) {
        try {
            //根据密码生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            //效验TOKEN
            DecodedJWT jwt = verifier.verify(token);
            log.info("登录验证成功!");
            return true;
        } catch (Exception exception) {
            log.error("JwtUtil登录验证失败!");

            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成token签名expire 分钟后过期
     *
     * @param username 用户名(电话号码)
     * @return 加密的token
     */
    public static String sign(String username) {
        long l=Long.parseLong(System.currentTimeMillis() + expire);
        Date date = new Date(l);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 附带username信息
        return JWT.create()
                .withClaim("username", username)
                .withExpiresAt(date)
                .sign(algorithm);

    }
}
