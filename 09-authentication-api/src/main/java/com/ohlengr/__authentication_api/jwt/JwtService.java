package com.ohlengr.__authentication_api.jwt;

import com.ohlengr.__authentication_api.user.User;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class JwtService {
    private final JwtEncoder jwtEncoder;

    public JwtService(JwtEncoder jwtEncoder){
        this.jwtEncoder = jwtEncoder;
    }

    public String generateToken(User user){
        Instant now = Instant.now();
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder().issuedAt(now).subject(user.getEmail()).claim("userId", user.getId()).expiresAt(now.plus(15, ChronoUnit.MINUTES)).build();
        JwsHeader jwsHeader = JwsHeader.with(MacAlgorithm.HS256).build();
        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(jwsHeader, jwtClaimsSet);
        Jwt jwt = jwtEncoder.encode(jwtEncoderParameters);
        return jwt.getTokenValue();
    }
}
