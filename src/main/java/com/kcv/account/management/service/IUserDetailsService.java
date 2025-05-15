package com.kcv.account.management.service;


import com.kcv.account.management.dto.entity.UserDetailsDTO;
import com.kcv.account.management.dto.users.UserDetailsRequest;
import com.kcv.account.management.dto.users.UserDetailsResponse;

import java.util.List;

public interface IUserDetailsService {

    public UserDetailsResponse addUser(UserDetailsRequest request);

    public UserDetailsResponse getAllUsers();

    public UserDetailsResponse deleteUser(UserDetailsDTO request);

    public UserDetailsResponse editUser(UserDetailsRequest request);
}
