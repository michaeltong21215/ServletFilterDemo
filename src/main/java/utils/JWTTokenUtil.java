package utils;

import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTTokenUtil {
    private static Clock clock = DefaultClock.INSTANCE;
    public static String generateToken(){
        Map<String, Object> claims = new HashMap();
        Date currentDate = clock.now();
        return Jwts.builder().setClaims(claims).setSubject("admin").setAudience("web").setIssuedAt(currentDate).signWith(SignatureAlgorithm.HS512, "1234").compact();
    }

    public static void main(String[] args) {
        System.out.println("token: " + generateToken());
    }
}
