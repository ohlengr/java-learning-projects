package com.ohlengr.__authentication_api.auth;

import com.ohlengr.__authentication_api.user.User;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
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
}
