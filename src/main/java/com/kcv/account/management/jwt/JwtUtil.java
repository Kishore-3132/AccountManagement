package com.kcv.account.management.jwt;

import com.kcv.account.management.dto.entity.UserLoginActivity;
import com.kcv.account.management.repository.IUserLoginActivityRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Autowired
    private IUserLoginActivityRepository userLoginActivityRepository;

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuer("AccountManagement")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    // Check session status in DB and validate JWT
    public boolean validateToken(String token) {
        UserLoginActivity userLoginActivity = null;
        try {
            token = (token != null && !token.isEmpty()) ? token : "";
             userLoginActivity = userLoginActivityRepository.findBySessionIdAndSessionStatus(token, "ACTIVE");
            if(userLoginActivity != null) {
                Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
                return true;
            } else {
                return false;
            }

        }catch (ExpiredJwtException e) {
            if (userLoginActivity != null) {
                userLoginActivity.setSessionStatus("EXPIRED");
                userLoginActivityRepository.save(userLoginActivity);
            }
            return false;
        } catch (JwtException e) {
            if (userLoginActivity != null) {
                userLoginActivity.setSessionStatus("INVALID");
                userLoginActivityRepository.save(userLoginActivity);
            }
            return false;
        }
    }
}
