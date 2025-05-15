package com.kcv.account.management.service;


import com.kcv.account.management.dto.common.CommonResponse;

import java.util.List;

public interface ICommonService {

    public CommonResponse getErrorCodeDescription(String code);
}
