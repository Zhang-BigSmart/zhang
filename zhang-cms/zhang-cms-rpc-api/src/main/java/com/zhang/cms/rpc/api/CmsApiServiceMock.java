package com.zhang.cms.rpc.api;

import com.zhang.cms.dao.model.CmsUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2017/8/21
 * @history
 */
public class CmsApiServiceMock implements CmsApiService{

    private static Logger _log = LoggerFactory.getLogger(CmsApiServiceMock.class);

    public CmsUser selectCmsUserByUsername(String username) {
        _log.info("CmsApiServiceMock => selectCmsUserByUsername");
        return null;
    }
}
