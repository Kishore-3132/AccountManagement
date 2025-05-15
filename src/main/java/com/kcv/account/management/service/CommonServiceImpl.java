package com.kcv.account.management.service;

import com.kcv.account.management.dto.common.CommonResponse;
import com.kcv.account.management.dto.common.ErrorCodeConstants;
import com.kcv.account.management.dto.entity.ErrorMappingDTO;
import com.kcv.account.management.repository.IErrorMappingRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class CommonServiceImpl implements ICommonService {

    @Autowired
    private IErrorMappingRepository errorMappingRepository;

    @Override
    public CommonResponse getErrorCodeDescription(String code) {
        CommonResponse response = new CommonResponse();
        try {
            ErrorMappingDTO errorResponse = errorMappingRepository.findByCode(code);
            response.setResponseCode(errorResponse.getCode());
            response.setResponseMessage(errorResponse.getDescription());
        } catch (Exception e) {
            log.info("::: Error While Fetching Error Code and Description from DataBase :::");
            e.printStackTrace();
            response.setResponseCode(ErrorCodeConstants.CommonErrorCode.GENERIC_ERROR);
            response.setResponseMessage("Error While Fetching Error Code and Description from DataBase");
        }
        return response;
    }
}
