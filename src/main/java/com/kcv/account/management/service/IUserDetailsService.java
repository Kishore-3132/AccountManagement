package com.kcv.account.management.service;


import com.kcv.account.management.dto.UserDetailsDTO;
import com.kcv.account.management.dto.UserDetailsRequest;
import com.kcv.account.management.dto.UserDetailsResponse;

import java.util.List;

public interface IUserDetailsService {

    public UserDetailsResponse addUser(UserDetailsRequest request);

    public List<UserDetailsResponse> getAllUsers();

    public UserDetailsResponse deleteUser(UserDetailsDTO request);

    public UserDetailsResponse editUser(UserDetailsRequest request);
}
