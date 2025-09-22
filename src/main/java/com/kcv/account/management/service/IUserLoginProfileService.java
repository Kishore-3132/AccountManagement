package com.kcv.account.management.service;


import com.kcv.account.management.dto.entity.UserLoginProfileDTO;
import com.kcv.account.management.dto.users.UserDetailsRequest;
import com.kcv.account.management.dto.users.UserDetailsResponse;

public interface IUserLoginProfileService {

    public UserDetailsResponse addUser(UserDetailsRequest request);

    public UserDetailsResponse getAllUsers();

    public UserDetailsResponse deleteUser(UserLoginProfileDTO request);

    public UserDetailsResponse editUser(UserDetailsRequest request);
}
