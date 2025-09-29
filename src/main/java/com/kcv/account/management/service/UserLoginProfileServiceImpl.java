package com.kcv.account.management.service;

import com.kcv.account.management.dto.common.ErrorCodeConstants;
import com.kcv.account.management.dto.entity.UserLoginProfileDTO;
import com.kcv.account.management.dto.users.UserDetail;
import com.kcv.account.management.dto.users.UserDetailsRequest;
import com.kcv.account.management.dto.users.UserDetailsResponse;
import com.kcv.account.management.dto.enums.AccountStatusEnum;
import com.kcv.account.management.dto.enums.GenderEnum;
import com.kcv.account.management.dto.enums.ROLEEnum;
import com.kcv.account.management.repository.IUserLoginProfileRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class UserLoginProfileServiceImpl implements IUserLoginProfileService {
    @Autowired
    private IUserLoginProfileRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetailsResponse addUser(UserDetailsRequest request) {
        log.info("::: User Creation Start :::");
        UserDetailsResponse userResponse = new UserDetailsResponse();
        try {
            UserLoginProfileDTO user = new UserLoginProfileDTO();
            user.setUsername(request.getUsername());
            user.setGender(request.getGender().name());
            user.setMobileNumber(request.getMobileNumber());
            user.setStatus(request.getStatus().name());
            user.setId(request.getUserId());
            user.setRole(request.getRole().name());
            user.setFullName(request.getFullName());
            user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
            user = userRepository.save(user);

            BeanUtils.copyProperties(user, userResponse);
            userResponse.setUserid(user.getId());
            userResponse.setGender(GenderEnum.valueOf(user.getGender()));
            userResponse.setStatus(AccountStatusEnum.valueOf(user.getStatus()));
            userResponse.setRole(ROLEEnum.valueOf(user.getRole()));
            userResponse.setResponseMessage("SUCCESS");
            userResponse.setResponseCode("000");
            userResponse.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            userResponse.setResponseMessage(e.getMessage());
            userResponse.setResponseCode(ErrorCodeConstants.UserErrorCode.USER_CREATION_FAILED);
            userResponse.setSuccess(false);
        }
        log.info("::: User Creation End :::");
        return userResponse;
    }

    @Override
    public UserDetailsResponse getAllUsers() {
        log.info("::: Fetching Users Start :::");
        UserDetailsResponse response = new UserDetailsResponse();
        response.setUsers(new ArrayList<>());
        try {

            List<UserLoginProfileDTO> listOfUserDetails = userRepository.findAll();
            if(listOfUserDetails != null && listOfUserDetails.size() > 0 )
            {
                listOfUserDetails.forEach(user -> {
                    UserDetail userResponse = new UserDetail();
                    BeanUtils.copyProperties(user, userResponse);
                    userResponse.setUserid(user.getId());
                    userResponse.setGender(GenderEnum.valueOf(user.getGender()));
                    userResponse.setStatus(AccountStatusEnum.valueOf(user.getStatus()));
                    userResponse.setRole(ROLEEnum.valueOf(user.getRole()));
                    response.getUsers().add(userResponse);
                    response.setResponseMessage("SUCCESS");
                    response.setResponseCode("000");
                    response.setSuccess(true);
                });
            }
            else {
                response.setResponseMessage("No Users Available at the moment");
                response.setResponseCode(ErrorCodeConstants.UserErrorCode.NO_USER_AVAILABLE);
                response.setSuccess(false);
            }
        } catch (Exception e) {
            log.info("::: Error Occurred while Fetching the Users :::");
            e.printStackTrace();
            response.setResponseMessage(e.getMessage());
            response.setResponseCode(ErrorCodeConstants.UserErrorCode.USER_EDITING_FAILED);
            response.setSuccess(false);
        }

        log.info("::: Fetching Users End :::");
        return response;
    }

    @Override
    public UserDetailsResponse deleteUser(UserDetailsRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserDetailsResponse editUser(UserDetailsRequest request) {
        log.info("::: User Editing Start :::");
        UserDetailsResponse userResponse = new UserDetailsResponse();
        try {
            Optional<UserLoginProfileDTO> userDetail = userRepository.findById(request.getUserId());
            if(!userDetail.isPresent()) {
                userResponse.setResponseMessage("User Not Found with the ID : " + request.getUserId());
                userResponse.setResponseCode(ErrorCodeConstants.UserErrorCode.USER_NOT_FOUND);
                userResponse.setSuccess(false);
                return userResponse;
            }
            UserLoginProfileDTO user = new UserLoginProfileDTO();
            user.setId(request.getUserId());
            user.setUsername(request.getUsername() != null ? request.getUsername() : userDetail.get().getUsername());
            user.setFullName(request.getFullName() != null ? request.getFullName() : userDetail.get().getFullName());
            user.setGender(request.getGender() != null ? request.getGender().name() : userDetail.get().getGender());
            user.setMobileNumber(request.getMobileNumber() != null ? request.getMobileNumber() : userDetail.get().getMobileNumber());
            user.setStatus(request.getStatus() != null ? request.getStatus().name() : userDetail.get().getStatus());
            user.setRole(request.getRole() != null ? request.getRole().name() : userDetail.get().getRole());
            user.setPassword(request.getPassword() != null ? bCryptPasswordEncoder.encode(request.getPassword()) : userDetail.get().getPassword());
            user = userRepository.save(user);

            BeanUtils.copyProperties(user, userResponse);
            userResponse.setUserid(user.getId());
            userResponse.setGender(GenderEnum.valueOf(user.getGender()));
            userResponse.setStatus(AccountStatusEnum.valueOf(user.getStatus()));
            userResponse.setRole(ROLEEnum.valueOf(user.getRole()));
            userResponse.setResponseMessage("SUCCESS");
            userResponse.setResponseCode("000");
            userResponse.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            userResponse.setResponseMessage(e.getMessage());
            userResponse.setResponseCode(ErrorCodeConstants.UserErrorCode.USER_EDITING_FAILED);
            userResponse.setSuccess(false);
        }
        log.info("::: User Editing End :::");
        return userResponse;
    }

    @Override
    public UserDetailsResponse findByUsername(String username) {
        UserDetailsResponse response = new UserDetailsResponse();
        Optional<UserLoginProfileDTO> userOpt = userRepository.findByUsername(username);
        if(userOpt.isPresent()) {
            UserLoginProfileDTO user = userOpt.get();

            BeanUtils.copyProperties(user, response);
            response.setUserid(user.getId());
            response.setGender(GenderEnum.valueOf(user.getGender()));
            response.setStatus(AccountStatusEnum.valueOf(user.getStatus()));
            response.setRole(ROLEEnum.valueOf(user.getRole()));
            response.setResponseMessage("SUCCESS");
            response.setResponseCode("000");
            response.setSuccess(true);
        } else {
            response.setResponseMessage("User Not Found with the Username : " + username);
            response.setResponseCode(ErrorCodeConstants.UserErrorCode.USER_NOT_FOUND);
            response.setSuccess(false);
        }
        return response;
    }


}
