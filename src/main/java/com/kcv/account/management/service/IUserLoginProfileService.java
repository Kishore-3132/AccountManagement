package com.kcv.account.management.service;

import com.kcv.account.management.dto.users.UserDetailsRequest;
import com.kcv.account.management.dto.users.UserDetailsResponse;

import java.util.Optional;

public interface IUserLoginProfileService {

    public UserDetailsResponse addUser(UserDetailsRequest request);

    public UserDetailsResponse getAllUsers();

    public UserDetailsResponse deleteUser(UserDetailsRequest request);

    public UserDetailsResponse editUser(UserDetailsRequest request);

    UserDetailsResponse findByUsername(String username);
}
