package ai.ecma.appauthservice.filter;

import ai.ecma.appauthservice.exception.RestException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    @Value("${app.jwt.access-secret-key}")
    private String accessJwtSecretKey;

    @Value("${app.jwt.refresh-secret-key}")
    private String refreshJwtSecretKey;

    @Value("${app.jwt.access-token-life-time}")
    private long accessLifeTime;

    @Value("${app.jwt.refresh-token-life-time}")
    private long refreshLifeTime;

    public String generateToken(String username, boolean forAccess) {
        Date expire = new Date(System.currentTimeMillis() + (forAccess ? accessLifeTime : refreshLifeTime));
        String secretKey = forAccess ? accessJwtSecretKey : refreshJwtSecretKey;

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expire)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        try {
            return Jwts
                    .parser()
                    .setSigningKey(accessJwtSecretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            throw RestException.forbidden();
        }
    }
}
