package com.example.superproject1.web.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class LoginResponse {
    private String email;
    private String message;
}
