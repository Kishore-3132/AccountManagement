package com.kcv.account.management.controller;

import com.kcv.account.management.dto.auth.LoginRequest;
import com.kcv.account.management.dto.auth.LoginResponse;
import com.kcv.account.management.exception.ErrorResponseMapper;
import com.kcv.account.management.jwt.AuthService;
import com.kcv.account.management.service.ICommonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/unsecure")
public class AuthController {
    private final AuthService authService;
    private final ICommonService commonService;
    private final ErrorResponseMapper errorResponseMapper;

    public AuthController(AuthService authService,ICommonService commonService, ErrorResponseMapper errorResponseMapper) {
        this.authService = authService;
        this.commonService = commonService;
        this.errorResponseMapper = errorResponseMapper;
    }

//    @PostMapping("/register")
//    public User register(@RequestBody User user) {
//        return authService.register(user);
//    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest body) {
        LoginResponse response = authService.login(body.getUsername(),body.getPassword());
        if (response.getSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
          return errorResponseMapper.buildErrorResponse(response.getResponseCode(), LoginResponse.class);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<LoginResponse> logout(@RequestHeader("Authorization") String authHeader,@RequestBody LoginRequest request) {
        LoginRequest source = new LoginRequest();
        String token = authHeader.replace("Bearer ", "");
        source.setToken((token != null && !"".equals(token)) ? token : "");
        LoginResponse response = authService.logout(source);
        if (response.getSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return errorResponseMapper.buildErrorResponse(response.getResponseCode(), LoginResponse.class);
        }
    }
}
