package com.edd.date.config.security;

import com.edd.date.constants.WebConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import org.thymeleaf.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtTokenProvider {
    @Value("${token.secretKey}")
    private String secretKey;
    private final UserDetailsService userDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(long userSeq,String adminId) {

        Claims claims = Jwts.claims().setSubject(adminId);
        claims.put(WebConstants.CLAIMS_ID,userSeq);

        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + WebConstants.tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPK(token));
        return new UsernamePasswordAuthenticationToken(userDetails, WebConstants.CREDENTIALS, userDetails.getAuthorities());
    }

    public String getUserPK(String token) {
        return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        return req.getHeader(WebConstants.AUTHORIZATION);
    }

    public boolean validateToken(String jwtToken) {

        try {

            if(StringUtils.isEmpty(jwtToken)
                    || !jwtToken.contains(WebConstants.JWT_SPLIT)){

                return false;

            }else {

                Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(jwtToken);
                return !claims.getBody().getExpiration().before(new Date());

            }

        } catch (ExpiredJwtException e) {

            return false;

        }
    }


}
