package com.kcv.account.management.dto;

import com.kcv.account.management.dto.enums.AccountStatusEnum;
import com.kcv.account.management.dto.enums.GenderEnum;
import com.kcv.account.management.dto.enums.ROLEEnum;
import lombok.Data;

@Data
public class UserDetailsRequest {

    private Integer userId;
    private String userName;
    private GenderEnum gender;
    private String mobileNumber;
    private String userFullName;
    private AccountStatusEnum status;
    private ROLEEnum role;

}
