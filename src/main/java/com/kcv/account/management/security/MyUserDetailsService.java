package com.kcv.account.management.security;

import com.kcv.account.management.dto.entity.UserLoginProfileDTO;
import com.kcv.account.management.repository.IUserLoginProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserLoginProfileRepository userLoginProfileRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = null;
        Optional<UserLoginProfileDTO> userDetail = userLoginProfileRepository.findByUsername(username);
        if(userDetail.isPresent()) {
            UserLoginProfileDTO userLoginProfileDTO = userDetail.get();
            user = new MyUser(
                    userLoginProfileDTO.getId(),
                    userLoginProfileDTO.getUsername(),
                    userLoginProfileDTO.getPassword(),
                    userLoginProfileDTO.getFullName(),
                    userLoginProfileDTO.getGender(),
                    userLoginProfileDTO.getMobileNumber(),
                    userLoginProfileDTO.getStatus(),
                    userLoginProfileDTO.getRole(),
                    Collections.singletonList(new SimpleGrantedAuthority(userLoginProfileDTO.getRole()))
            );
        }

        return user;
    }
}
