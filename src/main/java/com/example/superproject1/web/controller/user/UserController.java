package com.example.superproject1.web.controller.user;

import com.example.superproject1.repository.user.User;
import com.example.superproject1.service.user.UserService;
import com.example.superproject1.config.JwtService;
import com.example.superproject1.web.dto.user.LoginRequest;
import com.example.superproject1.web.dto.user.LoginResponse;
import com.example.superproject1.web.dto.user.SignupRequest;
import com.example.superproject1.web.dto.user.SignupResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {
    JwtService jwtService;
    UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody SignupRequest signupRequest){
        Boolean result = userService.signup(signupRequest);
        SignupResponse resultResponse;

        if(result){
            resultResponse = SignupResponse.builder()
                    .message("회원가입이 완료되었습니다.")
                    .build();

            return ResponseEntity.ok().body(resultResponse);
        }

        resultResponse = SignupResponse.builder()
                .message("회원가입이 잘 되지 않았습니다.")
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        Optional<User> user = userService.login(loginRequest);

        LoginResponse loginResponse;

        if(user.isEmpty()){
            loginResponse = LoginResponse.builder()
                    .message("로그인에 실패 했습니다.").build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(loginResponse);
        }

        String token = jwtService.encode(Long.valueOf(user.get().getId()));

        HttpHeaders headers =new HttpHeaders();
        headers.set("Authorization", token);

        loginResponse = LoginResponse.builder()
                .email(user.get().getEmail())
                .message("로그인이 성공적으로 완료되었습니다.").build();

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(loginResponse);
    }
}
