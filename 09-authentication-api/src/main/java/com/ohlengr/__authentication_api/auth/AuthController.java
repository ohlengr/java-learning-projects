package com.ohlengr.__authentication_api.auth;

import com.ohlengr.__authentication_api.jwt.JwtService;
import com.ohlengr.__authentication_api.user.User;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;
    public AuthController(AuthService authService, JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @GetMapping("")
    public String index(){
        return "Hello World!";
    }

    @PostMapping("/register")
    public RegisterResponse register(@Valid @RequestBody RegisterRequest registerRequest) {
        User user = authService.register(registerRequest);
        return new RegisterResponse(user.getId(), user.getName(), user.getEmail());
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        User user = authService.login(loginRequest);
        String token = jwtService.generateToken(user);
        return new LoginResponse(user.getId(), user.getName(), user.getEmail(), token);
    }
}
