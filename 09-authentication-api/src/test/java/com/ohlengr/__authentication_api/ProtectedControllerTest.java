package com.ohlengr.__authentication_api;

import com.ohlengr.__authentication_api.auth.RegisterResponse;
import com.ohlengr.__authentication_api.jwt.JwtService;
import com.ohlengr.__authentication_api.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest(properties = {
        "JWT_SECRET=VGhpc0lzQVRlc3RTZWNyZXRLZXlGb3JKV1QxMjM0NTY3ODkw"
})
@AutoConfigureMockMvc
public class ProtectedControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    JwtEncoder jwtEncoder;

    @Test
    public void shouldReturn401WhenTokenIsMissing() throws Exception{
        mockMvc.perform(
                get("/api/protected")
        ).andExpect(status().isUnauthorized());
    }

    @Test
    public void shouldReturn401WhenTokenIsInvalid() throws Exception{
        mockMvc.perform(
                get("/api/protected")
                        .header("Authorization", "Bearer invalid-token")
        ).andExpect(status().isUnauthorized());
    }

    @Test
    public void shouldReturn200WhenTokenIsValid() throws Exception{
        mockMvc.perform(
                get("/api/protected")
                        .header("Authorization", "Bearer " + generateToken())
        ).andExpect(status().isOk());
    }

    private String generateToken(){
        Instant now = Instant.now();
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder().issuedAt(now).subject("user@gmail.com").claim("userId", 10L).expiresAt(now.plus(15, ChronoUnit.MINUTES)).build();
        JwsHeader jwsHeader = JwsHeader.with(MacAlgorithm.HS256).build();
        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(jwsHeader, jwtClaimsSet);
        Jwt jwt = jwtEncoder.encode(jwtEncoderParameters);
        return jwt.getTokenValue();
    }
}
