package com.kcv.account.management.exception;

import com.kcv.account.management.dto.common.CommonResponse;
import com.kcv.account.management.dto.common.ErrorCodeConstants;
import com.kcv.account.management.service.CommonServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final CommonServiceImpl commonService;

    public GlobalExceptionHandler(CommonServiceImpl commonService) {
        this.commonService = commonService;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse> handleAllExceptions(Exception ex) {
        ex.printStackTrace();
        String errorCode = ErrorCodeConstants.CommonErrorCode.GENERIC_ERROR; // default internal error
        CommonResponse dbError = commonService.getErrorCodeDescription(errorCode);
        dbError.setResponseCode(errorCode);
        dbError.setResponseMessage(dbError.getResponseMessage());
        dbError.setErrorMessage("Exception occurred: " + ex.getMessage() + " at " + ex.getStackTrace()[0]);
        dbError.setSuccess(false);
        return ResponseEntity.ok(dbError); // always 200
    }
}
