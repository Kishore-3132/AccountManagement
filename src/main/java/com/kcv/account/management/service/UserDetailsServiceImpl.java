package com.kcv.account.management.service;

import com.kcv.account.management.dto.common.ErrorCodeConstants;
import com.kcv.account.management.dto.entity.UserDetailsDTO;
import com.kcv.account.management.dto.users.UserDetail;
import com.kcv.account.management.dto.users.UserDetailsRequest;
import com.kcv.account.management.dto.users.UserDetailsResponse;
import com.kcv.account.management.dto.enums.AccountStatusEnum;
import com.kcv.account.management.dto.enums.GenderEnum;
import com.kcv.account.management.dto.enums.ROLEEnum;
import com.kcv.account.management.repository.IUserDetailsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class UserDetailsServiceImpl implements IUserDetailsService {
    @Autowired
    private IUserDetailsRepository userRepository;

    @Override
    public UserDetailsResponse addUser(UserDetailsRequest request) {
        log.info("::: User Creation Start :::");
        UserDetailsResponse userResponse = new UserDetailsResponse();
        try {
            UserDetailsDTO user = new UserDetailsDTO();
            user.setUserName(request.getUserName());
            user.setGender(request.getGender().name());
            user.setMobileNumber(request.getMobileNumber());
            user.setStatus(request.getStatus().name());
            user.setId(request.getUserId());
            user.setRole(request.getRole().name());
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

            List<UserDetailsDTO> listOfUserDetails = userRepository.findAll();
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
    public UserDetailsResponse deleteUser(UserDetailsDTO request) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserDetailsResponse editUser(UserDetailsRequest request) {
        log.info("::: User Editing Start :::");
        UserDetailsResponse userResponse = new UserDetailsResponse();
        try {
            UserDetailsDTO user = new UserDetailsDTO();
            user.setId(request.getUserId());
            user.setUserName(request.getUserName());
            user.setGender(request.getGender().name());
            user.setMobileNumber(request.getMobileNumber());
            user.setStatus(request.getStatus().name());
            user.setId(request.getUserId());
            user.setRole(request.getRole().name());
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


}
