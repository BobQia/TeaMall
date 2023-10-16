package com.teamall.common.utils.sso;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;

/**
 * @author DengQiao
 * @date 2023-8-30 0030
 */
public class JwtUtils {
    private static String secret="AAABBBCCCDDDEEE";
    /**基于负载和算法创建token信息*/
    public static String generatorToken(Map<String,Object> map){
        return Jwts.builder()
                .setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis()+24*60*60*1000))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();//签约,创建token
    }
    /**解析token获取数据*/
    public static Claims getClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
    /**判定token是否失效*/
    public static boolean isTokenExpired(String token){
        Date expiration=getClaimsFromToken(token).getExpiration();
        return expiration.before(new Date());
    }

    //根据token字符串得到用户名称
    public static String getUserName(String token) {
        if(StringUtils.hasLength(token)) {
            return "";
        }

        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (String)claims.get("userName");
    }

}
