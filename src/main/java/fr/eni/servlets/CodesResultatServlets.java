package fr.eni.servlets;

    // 30000 et 39999
    public abstract class CodesResultatServlets {
    // Error Codes for Accounts/Users
        //  Error Codes for Account Creation
        public static final int CREATE_ACCOUNT_ERROR=30000;
        public static final int USERNAME_REQUIRED=30003;
        public static final int SURNAME_REQUIRED=30004;
        public static final int FIRSTNAME_REQUIRED=30005;
        public static final int EMAIL_REQUIRED=30006;
        public static final int STREETNAME_REQUIRED=30007;
        public static final int ZIPCODE_REQUIRED=30008;
        public static final int CITY_REQUIRED=30009;
        public static final int PWD_REQUIRED=30010;
        public static final int PWD_CONFIRMED_REQUIRED=30010;
        public static final int USERNAME_ALREADY_USED=30011;
        public static final int EMAIL_ALREADY_USED=30012;
        public static final int PWD_NOT_VALID=30013;
        public static final int PWD_PWD_CONFIRMED_NOT_IDENTICAL=30014;

        // Error Codes for Login
        // Error Codes for Profile Edition

    // Error Codes for Auctions
        // Error Codes for Auction Creation
        // Error Codes for Auction Edition

    // Error Codes for Administration Pannel
        // Error Codes for User Management
        // Error Codes for Category Management

    }
