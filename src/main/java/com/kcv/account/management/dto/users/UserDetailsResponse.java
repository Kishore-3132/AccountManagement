package com.kcv.account.management.dto.users;

import com.kcv.account.management.dto.common.CommonRequest;
import com.kcv.account.management.dto.common.CommonResponse;
import com.kcv.account.management.dto.enums.AccountStatusEnum;
import com.kcv.account.management.dto.enums.GenderEnum;
import com.kcv.account.management.dto.enums.ROLEEnum;
import lombok.Data;

import java.util.List;

@Data
public class UserDetailsResponse extends CommonResponse {

    private Long userid;
    private String userName;
    private GenderEnum gender;
    private String mobileNumber;
    private String fullName;
    private AccountStatusEnum status;
    private ROLEEnum role;
    private List<UserDetail> users;
}
