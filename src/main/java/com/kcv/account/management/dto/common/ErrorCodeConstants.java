package com.kcv.account.management.dto.common;

public class ErrorCodeConstants {

    public static class CustomerErrorCode {
        public static final String CUSTOMER_CREATION_FAILED = "CUST_001";
        public static final String FETCH_CUSTOMER_FAILED = "CUST_002";
        public static final String CUSTOMER_EDITING_FAILED= "CUST_003";
        public static final String CUSTOMER_DELETION_FAILED = "CUST_004";
        public static final String CUSTOMER_NOT_FOUND = "CUST_005";
        public static final String NO_CUSTOMER_AVAILABLE = "CUST_006";
        public static final String GENERIC_ERROR = "GEN_9999";
    }

    public static class PackageErrorCode {
        public static final String PACKAGE_CREATION_FAILED = "PCKG_001";
        public static final String FETCH_PACKAGE_FAILED = "PCKG_002";
        public static final String PACKAGE_EDITING_FAILED= "PCKG_003";
        public static final String PACKAGE_DELETION_FAILED = "PCKG_004";
        public static final String PACKAGE_NOT_FOUND = "PCKG_005";
        public static final String NO_PACKAGE_AVAILABLE = "PCKG_006";
    }

    public static class UserErrorCode {
        public static final String USER_CREATION_FAILED = "USR_001";
        public static final String FETCH_USER_FAILED = "USR_002";
        public static final String USER_EDITING_FAILED= "USR_003";
        public static final String USER_DELETION_FAILED = "USR_004";
        public static final String USER_NOT_FOUND = "USR_005";
        public static final String NO_USER_AVAILABLE = "USR_006";
    }

    public static class PaymentErrorCode {
        public static final String PAYMENT_CREATION_FAILED = "CUST_001";
        public static final String FETCH_PAYMENT_FAILED = "CUST_002";
        public static final String PAYMENT_EDITING_FAILED= "CUST_003";
        public static final String PAYMENT_DELETION_FAILED = "CUST_004";
        public static final String PAYMENT_NOT_FOUND = "CUST_005";
        public static final String NO_PAYMENT_AVAILABLE = "CUST_006";
    }

    public static class CommonErrorCode {

        public static final String GENERIC_ERROR = "GEN_9999";
    }

}
