package com.example.superproject1.web.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class AuthInfo {
    private Long userId;
}
