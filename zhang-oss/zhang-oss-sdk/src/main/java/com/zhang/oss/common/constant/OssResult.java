package com.zhang.oss.common.constant;

import com.zhang.common.base.BaseResult;

/**
 * @author Edison
 * @ClassName:
 * @Desc:oss系统常量枚举类
 * @date 2017/10/15
 * @history
 */
public class OssResult extends BaseResult{

    public OssResult(OssResultConstant ossResultConstant, Object data) {
        super(ossResultConstant.getCode(), ossResultConstant.getMessage(), data);
    }
}
