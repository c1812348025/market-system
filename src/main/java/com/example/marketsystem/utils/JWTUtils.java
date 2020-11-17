package com.example.marketsystem.utils;

import com.example.marketsystem.model.entity.Alluser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * jwt工具类
 * 1.生成的token可以被base64解密成明文信息
 * 2.base64解密的明文，修改后编码，会解密失败
 * 3.已颁布的token无法作废，除非修改密钥
 */
public class JWTUtils {

    /**
     *过期时间
     */
    private static final long EXPIRE = 60000 * 60 * 24 * 7;
    /**
     * 加密密钥
     */
    private static final String SECRET="wyclass.class";
    /**
     * 令牌前缀
     */
    private static final String TOKEN_PIRE = "wyclass";
    /**
     * 发布者
     */
    private static final String SUBJECT = "wyclass";

    /**
     * 生成令牌  ，加密
     * @param tuser
     * @return
     */
    public static String geneJosnWebToken(Alluser tuser){
        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("tId",tuser.getId())
                .claim("tPhone",tuser.getUserPhone())
                .claim("perMissOn",tuser.getUserSalt())//rediskey值
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE))
                .signWith(SignatureAlgorithm.HS256,SECRET).compact();
        token = TOKEN_PIRE + token;
        return token;
    }

    /**
     * 解密
     */
    public static Claims checkJWT(String token){
        try{
            final Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PIRE,"")).getBody();
            return claims;
        }catch (Exception e){
            return null;
        }
    }
}
