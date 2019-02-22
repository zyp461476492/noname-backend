package github.beginner.noname.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * @author zyp on 2019/2/18
 */
@Slf4j
public class JwtUtils {
    private static SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * jwt 有效时长 目前设置为1分钟
     */
    private static long Expiration = 1000 * 60;

    /**
     * 验证jwt
     * @param jwsString jwt经过加密签名后称作jws(JSON Web Signature)
     * @return true 如果验证通过 false 验证失败
     */
    public static boolean verifyJwt(String jwsString) {
        boolean res =  false;
        try {
            Jws<Claims> claims =  Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(jwsString);
            res = true;
        } catch (JwtException e) {
            log.info("jwt verify failed with jws: {}", jwsString);
        }
        return res;
    }

    /**
     * 解析jwt
     * @param jwsString 待解析的 jws
     * @return 解析后的结果
     */
    public static Jws<Claims> parseJwt(String jwsString) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwsString);
    }

    public static String buildJws(String loginName, Long id) {
        return Jwts.builder()
                .setHeaderParam("userId", id)
                .setSubject(loginName)
                .setExpiration(new Date(System.currentTimeMillis() + Expiration))
                .signWith(key)
                .compact();
    }

    public static void main(String[] args) {
        String jws = buildJws("test", 55L);
        System.out.println(jws);
        System.out.println(verifyJwt(jws));
    }
}
