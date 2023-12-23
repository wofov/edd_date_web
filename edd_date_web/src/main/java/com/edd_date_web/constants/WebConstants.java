package com.edd.date.constants;

import java.util.Map;

public class WebConstants {

    //
    public static final int MAX_REQUESTS = 4;
    public static final long TIME_WINDOW_MILLISECONDS = 2000;
    public static final long BLOCKING_TIME_MILLISECONDS = 5000;

    public static final String API_NAME = "EDD API";
    public static final String API_DESCRIPTION = "EDD API 명세서";
    public static final String API_VERSION = "0.0.1";
    public static final String BASE_PACKAGE = "com.edd.date.controller";

    //Map
    public static final String STATUS = "Status";
    public static final String MESSAGE = "Message";
    public static final String FAIL = "Fail";
    public static final String OK = "Ok";

    //JWT
    public static final String CREDENTIALS = "";
    public static final String CLAIMS_ID = "id";
    public static final String AUTHORIZATION = "Authorization";
    public static final long tokenValidTime = 60 * 60 * 168000L;
    public static final String JWT_SPLIT = ".";


    //CipherConfig
    public static final String CHARSET = "UTF-8";
    public static final String ALGORITHM = "AES";
    public static final int BLOCK_SIZE = 16;
    public static final String CIPHER_TRANSFORMATION = "AES/CBC/PKCS5Padding";




    //----------
    public static final String OVERLAP = "Overlap";
    public static final String GAP = "";
    public static final String EXCEPTION_500 = "500-ERROR";
    public static final String NOT_FOUND = "404-ERROR";
    public static final String JWTDECODER = "\\.";
    public static final String UNKNOWN_ERROR = "Unknown Error";
    public static final String CUSTOM_ACTIVATED = "CustomException 발동";
    public static final String SIGN_DATE = "signDate";
    public static final String Like_IT = "likeIt";

    //======
    public static final Map<String,String> EXCEPTION_ERROR =Map.of(
            "Status","Fail","Message","Unknown Error"
    );



}
