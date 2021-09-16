package fr.eni.dal;

//10000-19999
public class CodesResultatDAL {
    //Codes for Utilisateur
    public static final int ERROR_SELECT_ALL=10000;
    public static final int ERROR_SELECT_BY_ID=10001;
    public static final int ERROR_CREATE_USER=10002;
    public static final int ERROR_UPDATE_USER_DATA=10003;
    public static final int ERROR_UPDATE_USER_ACCOUNT_STATUS=10004;
    public static final int ERROR_UPDATE_USER_CREDIT=10005;
    public static final int ERROR_DELETE_USER=10006;
    public static final int ERROR_CHECK_USER_EXISTENCE=10007;
    public static final int ERROR_RESET_PWD=10008;

    //Codes for ArticleVendu
    public static final int ERROR_UPDATE_ARTICLE_BID=10110;
    public static final int ERROR_UPDATE_AUCTION_STATUS=10111;

    //Codes for Enchere
    public static final int ERROR_CREATE_BID=10201;
    public static final int ERROR_DELETE_BID_BY_USER=10202;
    public static final int ERROR_DELETE_BIDS_ON_ARTICLE=10203;

    //Codes for Categorie
    public static final int ERROR_CREATE_CATEGORY=10301;
    public static final int ERROR_UPDATE_CATEGORY=10302;
    public static final int ERROR_DELETE_CATEGORY=10303;
    public static final int ERROR_CHECK_IF_CATEGORY_IS_USED=10304;

    //Codes for Retrait
    public static final int ERROR_CREATE_RETRAIT=10401;

}
