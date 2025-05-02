package com.kcv.account.management.service;

import com.kcv.account.management.dto.*;
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
        UserDetailsDTO user = new UserDetailsDTO();
        user.setUserName(request.getUserName());
        user.setGender(request.getGender().name());
        user.setMobileNumber(request.getMobileNumber());
        user.setStatus(request.getStatus().name());
        user.setId(request.getUserId());
        user.setRole(request.getRole().name());
        user = userRepository.save(user);

        UserDetailsResponse userResponse = new UserDetailsResponse();

        BeanUtils.copyProperties(user, userResponse);
        userResponse.setUserid(user.getId());
        userResponse.setGender(GenderEnum.valueOf(user.getGender()));
        userResponse.setStatus(AccountStatusEnum.valueOf(user.getStatus()));
        userResponse.setRole(ROLEEnum.valueOf(user.getRole()));

        return userResponse;
    }

    @Override
    public List<UserDetailsResponse> getAllUsers() {
        log.info("Fetching all the UserDetailss");
        List<UserDetailsResponse> userList = new ArrayList<>();

        List<UserDetailsDTO> listOfUserDetails = userRepository.findAll();

        listOfUserDetails.forEach(user -> {
            UserDetailsResponse userResponse = new UserDetailsResponse();

            BeanUtils.copyProperties(user, userResponse);
            userResponse.setUserid(user.getId());
            userResponse.setGender(GenderEnum.valueOf(user.getGender()));
            userResponse.setStatus(AccountStatusEnum.valueOf(user.getStatus()));
            userResponse.setRole(ROLEEnum.valueOf(user.getRole()));

            userList.add(userResponse);
        });


        return userList;
    }

    @Override
    public UserDetailsResponse deleteUser(UserDetailsDTO request) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserDetailsResponse editUser(UserDetailsRequest request) {
        UserDetailsDTO user = new UserDetailsDTO();
        user.setId(request.getUserId());
        user.setUserName(request.getUserName());
        user.setGender(request.getGender().name());
        user.setMobileNumber(request.getMobileNumber());
        user.setStatus(request.getStatus().name());
        user.setId(request.getUserId());
        user.setRole(request.getRole().name());
        user = userRepository.save(user);

        UserDetailsResponse userResponse = new UserDetailsResponse();

        BeanUtils.copyProperties(user, userResponse);
        userResponse.setUserid(user.getId());
        userResponse.setGender(GenderEnum.valueOf(user.getGender()));
        userResponse.setStatus(AccountStatusEnum.valueOf(user.getStatus()));
        userResponse.setRole(ROLEEnum.valueOf(user.getRole()));

        return userResponse;
    }


}
