package com.ohlengr.__authentication_api.auth;

import com.ohlengr.__authentication_api.exception.EmailAlreadyExistsException;
import com.ohlengr.__authentication_api.exception.InvalidCredentialsException;
import com.ohlengr.__authentication_api.user.User;
import com.ohlengr.__authentication_api.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    private String normalizeEmail(String email) {
        return email.trim().toLowerCase(Locale.ROOT);
    }

    public User register(RegisterRequest request) {
        String name = request.name();
        String email = normalizeEmail(request.email());
        boolean emailExists = emailExists(email);
        if (emailExists) {
            throw new EmailAlreadyExistsException("Email Already Exists");
        }
        String password = passwordEncoder.encode(request.password());
        User user = new User(email, name, password);
        return userRepository.save(user);
    }

    public User login(LoginRequest loginRequest){
        String email = normalizeEmail(loginRequest.email());
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new InvalidCredentialsException("Invalid Email or Password")
        );
        if(!passwordEncoder.matches(loginRequest.password(), user.getPassword())){
            throw new InvalidCredentialsException("Invalid email or password");
        }
        return user;
    }
}
