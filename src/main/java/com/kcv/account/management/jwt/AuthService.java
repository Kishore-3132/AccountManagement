package com.kcv.account.management.jwt;

import com.kcv.account.management.dto.auth.LoginRequest;
import com.kcv.account.management.dto.auth.LoginResponse;
import com.kcv.account.management.dto.common.ErrorCodeConstants;
import com.kcv.account.management.dto.entity.UserLoginProfileDTO;
import com.kcv.account.management.dto.entity.UserLoginActivity;
import com.kcv.account.management.repository.IUserLoginProfileRepository;
import com.kcv.account.management.repository.IUserLoginActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private IUserLoginProfileRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserLoginActivityRepository userLoginActivityRepository;


    public LoginResponse login(String username, String password) {
        LoginResponse response = new LoginResponse();
        Optional<UserLoginProfileDTO> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent() && passwordEncoder.matches(password, userOpt.get().getPassword())) {
            String token = jwtUtil.generateToken(username);
            if(token != null && !"".equals(token))
            {
                response.setResponseMessage("SUCCESS");
                response.setResponseCode("000");
                response.setSuccess(true);
                response.setToken(token);
                response.setUsername(userOpt.get().getUsername());
                response.setRole(userOpt.get().getRole());
                // Log login activity
                try {
                    UserLoginActivity userLoginActivity = new  UserLoginActivity();
                    userLoginActivity.setSessionId(token);
                    userLoginActivity.setSessionStatus("ACTIVE");
                    userLoginActivity.setUserLoginProfile(userOpt.get());
                    userLoginActivity.setUserName(userOpt.get().getUsername());
                    userLoginActivity.setStartDatetime(LocalDateTime.now());
                    userLoginActivityRepository.save(userLoginActivity);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else
            {
                response.setResponseMessage("Login Failed");
                response.setResponseCode(ErrorCodeConstants.LoginErrorCode.LOGIN_FAILED);
                response.setSuccess(false);
            }

        } else
        {
            response.setResponseMessage("Invalid credentials");
            response.setResponseCode(ErrorCodeConstants.LoginErrorCode.INVALID_CREDENTIALS);
            response.setSuccess(false);
        }
        return response;
    }

    public LoginResponse logout(LoginRequest source) {
        LoginResponse response = new LoginResponse();

//        UserLoginActivity activityOpt = userLoginActivityRepository.findBySessionIdAndSessionStatus(source.getToken(), "ACTIVE");
        UserLoginActivity userLogin = userLoginActivityRepository.findBySessionId(source.getToken());
        if (userLogin != null) {
            userLogin.setSessionStatus("INACTIVE");
            userLogin.setEndDatetime(LocalDateTime.now());
            userLoginActivityRepository.save(userLogin);
            response.setResponseMessage("Logged out successfully");
            response.setResponseCode("000");
            response.setSuccess(true);
            return response;
        } else {
            response.setResponseMessage("Sorry Unable to logout");
            response.setResponseCode(ErrorCodeConstants.CommonErrorCode.GENERIC_ERROR);
            response.setSuccess(false);
            return response;
        }
    }
    public UserLoginProfileDTO register(UserLoginProfileDTO user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
