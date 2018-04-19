package com.zhang.upms.common.constant;

import com.zhang.common.base.BaseResult;

/**
 * @author Edison
 * @ClassName:
 * @Desc: upms系统常量枚举类
 * @date 2017/7/23
 * @history
 */
public class UpmsResult extends BaseResult{

    public UpmsResult(UpmsResultConstant upmsResultConstant, Object data) {
        super(upmsResultConstant.getCode(), upmsResultConstant.getMessage(), data);
    }
}
