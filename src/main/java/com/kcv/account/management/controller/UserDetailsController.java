package com.kcv.account.management.controller;

import com.kcv.account.management.dto.users.UserDetailsRequest;
import com.kcv.account.management.dto.users.UserDetailsResponse;
import com.kcv.account.management.service.IUserDetailsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@Log4j2
@RequestMapping("/user")
public class UserDetailsController {
    @Autowired
    private IUserDetailsService userService;

    @PostMapping("/addUser")
    public ResponseEntity<UserDetailsResponse> addUser(@RequestBody UserDetailsRequest request) {
        UserDetailsResponse response = userService.addUser(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<UserDetailsResponse> getAllDetails() {
        UserDetailsResponse response = userService.getAllUsers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/editUser/{id}")
    public ResponseEntity<UserDetailsResponse> editUser(@RequestBody UserDetailsRequest request,@PathVariable Integer id) {
        request.setUserId(id);
        UserDetailsResponse response = userService.editUser(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
