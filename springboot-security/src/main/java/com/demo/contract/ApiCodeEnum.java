package com.demo.contract;


public enum ApiCodeEnum {

    /**
     *
     */
    INCORRECT_USERNAME_PASSWORD(20001, "incorrect username or password"),

    INCORRECT_REQUEST_PARAMETERS(20002, "incorrect request parameters"),

    FILE_CANNOT_BE_EMPTY(20003, "Files cannot be empty"),

    NO_PERMISSION(21004, "no permission"),

    INCORRECT_UPLOAD_OF_FILES(21005, "Incorrect upload of files"),

    IMEI_IS_BOUND(21006, "IMEI is bound"),

    EMAIL_HAS_BEEN_REGISTERED(21007, "The email has been registered"),

    MISSING_TOKEN(21008, "Missing token"),

    EMAIL_VERFICATION_ERROR(21009, "email verification error"),

    INVITE_FAILURE(21010, "invite failure"),

    INVITE_VERFICATION_ERROR(21011, "invite verification error"),

    UNBIND_DEVICE_ERROR(21012, "unbind device error"),

    PASSWORD_ERROR(21013,"Inconsistent password input twice"),

    CAN_NOT_DELETE_ONESELF(21014,"Can't delete oneself"),

    USED_BY_ANOTHER_DEALERSHIP(21015,"Current device has been used by another Dealership"),

    USER_ID_DIFFERENT(21016,"This user is not allowed to operate"),

    UPDATE_ALERT_STATUS(21017,"The alert status has been modified"),

    USER_ROLE_NOT_MANAGER(21018,"The user's role is not manager"),


    OK(21000, "ok"),

    UNKNOW_ERROR(21111,"unknown error");

    private int code;
    private String msg;

    private ApiCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
