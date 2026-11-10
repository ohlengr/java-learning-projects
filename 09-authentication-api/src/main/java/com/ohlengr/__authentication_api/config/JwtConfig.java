package com.ohlengr.__authentication_api.config;

import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import com.nimbusds.jose.jwk.gen.OctetSequenceKeyGenerator;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Configuration
public class JwtConfig {

    @Value("${JWT_SECRET}")
    private String jwtSecret;

    private SecretKey jwtSecretKey(){
        byte[] bytes = Base64.getDecoder().decode(jwtSecret);
        return new SecretKeySpec(bytes, "HmacSHA256");
    }

    @Bean
    public JwtEncoder jwtEncoder(){
        OctetSequenceKey jwk = new OctetSequenceKey.Builder(jwtSecretKey())
                .keyUse(KeyUse.SIGNATURE)
                .algorithm(JWSAlgorithm.HS256)
                .keyID("aith-api-key")
                .build();

        JWKSet jwkSet = new JWKSet(jwk);

        return new NimbusJwtEncoder(
                new ImmutableJWKSet<SecurityContext>(jwkSet)
        );
    }

    @Bean
    public JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withSecretKey(jwtSecretKey()).macAlgorithm(MacAlgorithm.HS256).build();
    }
}
