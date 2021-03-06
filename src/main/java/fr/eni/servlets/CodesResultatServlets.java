package fr.eni.servlets;

// 30000-39999
public abstract class CodesResultatServlets {
        // Codes for Accounts/Users
    //  Codes for Account Creation
    public static final int CREATE_ACCOUNT_ERROR=30000;
    public static final int CREATE_ACCOUNT_SUCCESS=30001;
    public static final int USERNAME_REQUIRED=30003;
    public static final int SURNAME_REQUIRED=30004;
    public static final int FIRSTNAME_REQUIRED=30005;
    public static final int EMAIL_REQUIRED=30006;
    public static final int STREETNAME_REQUIRED=30007;
    public static final int ZIPCODE_REQUIRED=30008;
    public static final int CITY_REQUIRED=30009;
    public static final int PWD_REQUIRED=30010;
    public static final int PWD_CONFIRMED_REQUIRED=30011;
    public static final int PWD_PWD_CONFIRMED_NOT_IDENTICAL=30012;

    // Codes for Login
    public static final int LOGIN_ERROR = 30100;
    public static final int LOGIN_SUCCESS = 30101;
    public static final int USER_NOT_AUTHENTICATED=30102;

    // Codes for Forgotten Password
    public static final int F_PWD_ERROR = 30200;
    public static final int F_PWD_SUCCESS = 30201;
    public static final int USER_NOT_FOUND = 30202;

    // Codes for Profile Edition (re-using some account creation codes)
    public static final int PROFILE_EDIT_ERROR = 30300;
    public static final int PROFILE_EDIT_SUCCESS = 30301;

        // Codes for Auctions
    // Codes for Auction Creation
    public static final int AUCTION_CREATION_SUCCESS = 30400;
    public static final int USER_BID_TOO_LOW=30401;
    public static final int NOT_ENOUGH_TO_BID=30402;
    public static final int ARTICLE_NAME_REQUIRED=30403;
    public static final int DESCRIPTION_REQUIRED=30404;
    public static final int CATEGORY_REQUIRED=30405;
    public static final int STARTING_PRICE_INVALID=30406;
    public static final int START_DATE_REQUIRED=30407;
    public static final int END_DATE_REQUIRED=30408;
    public static final int ERROR_ENDATE_BEFORE_STARTDATE=30409;
    public static final int ERROR_ENDDATE_BEFORE_TODAY=30410;

    // Codes for Auction Edition

    // Codes for Display Auction Details
    public static final int EMPTY_ID_ARTICLE=30600;
    public static final int EMPTY_ARTICLE=30601;
    public static final int EMPTY_BIDDERS_LIST=30602;
    public static final int EMPTY_NEW_BID=30603;


        // Codes for Administration Pannel
    // Codes for User Management
    public static final int LOAD_USERS_ERROR = 30500;
    public static final int LOAD_CATEGORIES_ERROR = 30501;
    public static final int DEACTIVATE_ACCOUNT_ERROR=30506;
    public static final int REACTIVATE_ACCOUNT_ERROR=30507;
    public static final int DELETE_ACCOUNT_ERROR = 30503;
    // Codes for Category Management
    public static final int EDIT_CATEGORY_ERROR=30504;
    public static final int CREATE_CATEGORY_ERROR=30505;
    public static final int CATEGORY_NAME_REQUIRED=30508;
    public static final int GET_CATEGORY_USES_ERROR=30509;
    public static final int DELETE_CATEGORY_ERROR=30510;

}
