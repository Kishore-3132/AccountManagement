package com.kcv.account.management.service;


import com.kcv.account.management.dto.UserDetailsDTO;
import com.kcv.account.management.dto.common.UserDetailsRequest;
import com.kcv.account.management.dto.common.UserDetailsResponse;

import java.util.List;

public interface IUserDetailsService {

    public UserDetailsResponse addUser(UserDetailsRequest request);

    public List<UserDetailsResponse> getAllUsers();

    public UserDetailsResponse deleteUser(UserDetailsDTO request);

    public UserDetailsResponse editUser(UserDetailsRequest request);
}
