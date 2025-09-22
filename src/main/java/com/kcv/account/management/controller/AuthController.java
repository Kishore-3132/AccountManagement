package com.kcv.account.management.controller;

import com.kcv.account.management.auth.LoginRequest;
import com.kcv.account.management.auth.LoginResponse;
import com.kcv.account.management.dto.common.CommonResponse;
import com.kcv.account.management.jwt.AuthService;
import com.kcv.account.management.service.ICommonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/unsecure")
public class AuthController {
    private final AuthService authService;
    private final ICommonService commonService;

    public AuthController(AuthService authService,ICommonService commonService) {
        this.authService = authService;
        this.commonService = commonService;
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
            LoginResponse errorResponse = new LoginResponse();
            CommonResponse error = commonService.getErrorCodeDescription(response.getResponseCode());
            errorResponse.setResponseMessage(error.getResponseMessage());
            errorResponse.setResponseCode(error.getResponseCode());
            errorResponse.setSuccess(false);
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<LoginResponse> logout(@RequestHeader("Authorization") String authHeader) {
        LoginRequest source = new LoginRequest();
        String token = authHeader.replace("Bearer ", "");
        source.setToken((token != null && !"".equals(token)) ? token : "");
        LoginResponse response = authService.logout(source);
        if (response.getSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            LoginResponse errorResponse = new LoginResponse();
            CommonResponse error = commonService.getErrorCodeDescription(response.getResponseCode());
            errorResponse.setResponseMessage(error.getResponseMessage());
            errorResponse.setResponseCode(error.getResponseCode());
            errorResponse.setSuccess(false);
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        }
    }
}
