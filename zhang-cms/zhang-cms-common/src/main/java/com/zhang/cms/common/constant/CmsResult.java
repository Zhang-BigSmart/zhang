package com.zhang.cms.common.constant;

import com.zhang.common.base.BaseResult;

/**
 * @author Edison
 * @ClassName:
 * @Desc: cms系统常量枚举类
 * @date 2017/8/7
 * @history
 */
public class CmsResult extends BaseResult {

    public CmsResult(CmsResultConstant cmsResultConstant, Object data) {
        super(cmsResultConstant.getCode(), cmsResultConstant.getMessage(), data);
    }

}
