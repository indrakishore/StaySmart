package com.indra.StaySmart.dto.request;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
