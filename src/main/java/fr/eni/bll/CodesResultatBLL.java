package fr.eni.bll;


// Les codes disponibles sont entre 20000 et 29999

public abstract class CodesResultatBLL {

    //Codes for Account Creation
    public static final int CREATE_ACCOUNT_ERROR=20000;
    public static final int CREATE_ACCOUNT_SUCCESS=20001;
    public static final int USERNAME_REQUIRED=20003;
    public static final int USERNAME_INVALID=20004;
    public static final int USERNAME_ALREADY_USED=20005;
    public static final int EMAIL_REQUIRED=20006;
    public static final int EMAIL_ALREADY_USED=20007;
    public static final int PWD_REQUIRED=20008;
    public static final int PWD_CONFIRMED_REQUIRED=20009;
    public static final int PWD_NOT_VALID=20010;
    public static final int PWD_PWD_CONFIRMED_NOT_IDENTICAL=20011;

}
