package com.kcv.account.management.exception;

import com.kcv.account.management.dto.common.CommonResponse;
import com.kcv.account.management.service.CommonServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ErrorResponseMapper {

    private final CommonServiceImpl commonService;

    public ErrorResponseMapper(CommonServiceImpl commonService) {
        this.commonService = commonService;
    }

    public <T extends CommonResponse> ResponseEntity<T> buildErrorResponse(String errorCode, Class<T> responseType) {
        T response;
        try {
            response = responseType.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create response instance", e);
        }

        CommonResponse error = commonService.getErrorCodeDescription(errorCode);
        response.setResponseCode(error.getResponseCode());
        response.setResponseMessage(error.getResponseMessage());
        response.setSuccess(false);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
