package com.indra.StaySmart.dto.response;

import lombok.Data;

@Data
public class AuthResponse {

    private String message;
    private String username;

    public AuthResponse(String message, String username) {
        this.message = message;
        this.username = username;
    }
}
