package com.zhang.cms.rpc.api;

import com.zhang.cms.dao.model.CmsUser;


/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2017/8/21
 * @history
 */
public interface CmsApiService {

    /**
     * 根据username获取cmsuser
     */
    CmsUser selectCmsUserByUsername(String username);

}
