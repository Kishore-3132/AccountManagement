package com.kcv.account.management.controller;

import com.kcv.account.management.auth.LoginRequest;
import com.kcv.account.management.auth.LoginResponse;
import com.kcv.account.management.dto.common.CommonResponse;
import com.kcv.account.management.service.ICommonService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@Log4j2
@RequestMapping("/auth")
public class AuthController {

    private ICommonService commonService;

//    // Login
//    @PostMapping("/login")
//    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
//        LoginResponse response = authService.login(loginRequest);
//
//        if (response.getSuccess()) {
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        }
//        else {
//            LoginResponse errorResponse = new LoginResponse();
//            CommonResponse error = commonService.getErrorCodeDescription(response.getResponseCode());
//            errorResponse.setResponseMessage(error.getResponseMessage());
//            errorResponse.setResponseCode(error.getResponseCode());
//            errorResponse.setSuccess(false);
//            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
//        }
//    }
//
//    // Logout
//    @PostMapping("/logout")
//    public ResponseEntity<String> logout(HttpServletRequest request) {
//        authService.logout(request);
//        return ResponseEntity.ok("Logged out successfully");
//    }

}
