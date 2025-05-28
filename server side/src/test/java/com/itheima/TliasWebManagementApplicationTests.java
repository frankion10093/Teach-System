package com.itheima;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import static io.jsonwebtoken.SignatureAlgorithm.HS256;

//@SpringBootTest
class TliasWebManagementApplicationTests {

    //这是测试jwt令牌的生成
    @Test
    public void testCreatJwt(){


                Map<String, Object> clamis = new HashMap<>();
                clamis.put("id",1);
                clamis.put("name","tom");

        String jwt = Jwts.builder()
                .signWith(HS256,"itheima")//设置签名算法
                .setClaims(clamis)//设置自定义内容
                .setExpiration(new Date(System.currentTimeMillis()))
                .compact();

        System.out.println(jwt);
    }
    @Test
    public void testParseJwt(){
        Claims paraseJwt = Jwts.parser()
                .setSigningKey("itheima")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOjEsImV4cCI6MTc0NzczMzYzMH0.goQ490ITCODEHE_TkIRfr-YIGzO18XFBYVE91vzG8E8")
                .getBody();

        System.out.println(paraseJwt);

    }


}
