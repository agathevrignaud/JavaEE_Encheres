package fr.eni.dal;

//10000-19999
public class CodesResultatDAL {
    //Codes for Utilisateur
    public static final int ERROR_SELECT_ALL_USERS=10000;
    public static final int ERROR_SELECT_USER_BY_ID=10001;
    public static final int ERROR_CREATE_USER=10002;
    public static final int ERROR_UPDATE_USER_DATA=10003;
    public static final int ERROR_UPDATE_USER_ACCOUNT_STATUS=10004;
    public static final int ERROR_UPDATE_USER_CREDIT=10005;
    public static final int ERROR_DELETE_USER=10006;
    public static final int ERROR_CHECK_USER_EXISTENCE=10007;
    public static final int ERROR_RESET_PWD=10008;

    //Codes for ArticleVendu
    public static final int ERROR_SELECT_ALL_ARTICLES=10101;
    public static final int ERROR_SELECT_ALL_ARTICLES_BY_USER_ID=10102;
    public static final int ERROR_SELECT_ARTICLE_BY_ID=10103;
    public static final int ERROR_UPDATE_ARTICLE_BID=10104;
    public static final int ERROR_UPDATE_AUCTION_STATUS=10105;
    public static final int ERROR_CREATE_ARTICLE=10106;

    //Codes for Enchere
    public static final int ERROR_SELECT_ALL_BIDS_BY_ARTICLE=10204;
    public static final int ERROR_SELECT_HIGHEST_BID_BY_ARTICLE=10205;
    public static final int ERROR_CREATE_BID=10201;
    public static final int ERROR_DELETE_BIDS_BY_USER=10202;
    public static final int ERROR_DELETE_BIDS_ON_ARTICLE=10203;


    //Codes for Categorie
    public static final int ERROR_SELECT_ALL_CATEGORIES=10305;
    public static final int ERROR_CREATE_CATEGORY=10301;
    public static final int ERROR_UPDATE_CATEGORY=10302;
    public static final int ERROR_DELETE_CATEGORY=10303;
    public static final int ERROR_CHECK_IF_CATEGORY_IS_USED=10304;

    //Codes for Retrait
    public static final int ERROR_SELECT_RETRAIT_BY_ID=10402;
    public static final int ERROR_CREATE_RETRAIT=10401;

}
