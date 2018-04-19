package com.zhang.upms.rpc.api;

import com.zhang.common.base.BaseServiceMock;
import com.zhang.upms.dao.mapper.UpmsUserOrganizationMapper;
import com.zhang.upms.dao.model.UpmsUserOrganization;
import com.zhang.upms.dao.model.UpmsUserOrganizationExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* 降级实现UpmsUserOrganizationService接口
* Created by zihao on 2017/7/25.
*/
public class UpmsUserOrganizationServiceMock extends BaseServiceMock<UpmsUserOrganizationMapper, UpmsUserOrganization, UpmsUserOrganizationExample> implements UpmsUserOrganizationService {

    private static Logger _log = LoggerFactory.getLogger(UpmsUserOrganizationServiceMock.class);

    @Override
    public int organization(String[] organizationIds, int id) {
        _log.info("UpmsUserOrganizationServiceMock => organization");
        return 0;
    }

}
