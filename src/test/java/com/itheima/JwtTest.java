package com.itheima;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {


    /**
     * 生成JWT令牌 - Jwts.builder()
     */
    @Test
    public void testGenerateJwt(){
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", 1);
        dataMap.put("username", "admin");

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "aXRoZWltYQ==") //指定加密算法, 秘钥
                .addClaims(dataMap) //添加自定义信息
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 1000)) //设置过期时间
                .compact();//生成令牌
        System.out.println(jwt);
    }

    /**
     * 解析JWT令牌
     */
    @Test
    public void testParseJWT(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTczMjQzOTc3OH0.6VO9nIGo7UaqzEfQ3F9CT7M1JvSTD1r3Gfp_Lj41a3Q";
        Claims claims = Jwts.parser()
                .setSigningKey("aXRoZWltYQ==") //指定秘钥
                .parseClaimsJws(token) //解析令牌
                .getBody(); //获取自定义信息
        System.out.println(claims);
    }

}
