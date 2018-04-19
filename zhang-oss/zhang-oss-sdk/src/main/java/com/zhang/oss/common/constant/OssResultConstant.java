package com.zhang.oss.common.constant;

/**
 * @author Edison
 * @ClassName:
 * @Desc:oss系统接口结果常量枚举类
 * @date 2017/10/15
 * @history
 */
public enum OssResultConstant {

    FAILED(0,"failed"),
    SUCCESS(1,"success");

    public int code;

    public String message;

    OssResultConstant(int code, String message) {
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
