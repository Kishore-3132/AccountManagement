package com.kcv.account.management.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class MyUser extends User {

    private Long ulpId;
    private String gender;
    private String mobileNumber;
    private String fullName;
    private String status;
    private String role;

    public MyUser(Long id, String username, String password, String fullName,
                  String gender, String mobileNumber, String status, String role,
                  Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.ulpId = id;
        this.fullName = fullName;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.status = status;
        this.role = role;
    }

    public Long getUlpId() { return ulpId; }
    public String getFullName() { return fullName; }
    public String getGender() { return gender; }
    public String getMobileNumber() { return mobileNumber; }
    public String getStatus() { return status; }
    public String getRole() { return role; }

    // Hide password from JSON responses
    @Override
    @JsonIgnore
    public String getPassword() {
        return super.getPassword();
    }
}
