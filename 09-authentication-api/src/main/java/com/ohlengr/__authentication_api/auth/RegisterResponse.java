package com.ohlengr.__authentication_api.auth;

public record RegisterResponse(
        Long id,
        String name,
        String email
) {
}