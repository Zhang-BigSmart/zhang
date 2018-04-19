package com.zhang.cms.common.constant;

/**
 * @author Edison
 * @ClassName:
 * @Desc: cms系统接口结果常量枚举类
 * @date 2017/8/7
 * @history
 */
public enum CmsResultConstant {

    FAILED(0, "failed"),
    SUCCESS(1, "success"),

    EMPTY_USERNAME(20101, "Username cannot be empty"),
    EMPTY_PASSWORD(20102, "Password cannot be empty"),
    INVALID_USERNAME(20103, "Account does not exist"),
    INVALID_PASSWORD(20104, "Password error"),
    INVALID_ACCOUNT(20105, "Invalid account"),

    FILE_TYPE_ERROR(20001, "File type not supported!"),
    INVALID_LENGTH(20002, "Invalid length"),
    INVALID_PARAMETER(20003, "Invalid parameter");

    public int code;

    public String message;

    CmsResultConstant(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
