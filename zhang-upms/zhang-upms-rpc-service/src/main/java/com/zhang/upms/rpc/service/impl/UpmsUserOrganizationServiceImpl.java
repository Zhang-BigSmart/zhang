package com.zhang.upms.rpc.service.impl;

import com.zhang.common.annotation.BaseService;
import com.zhang.common.base.BaseServiceImpl;
import com.zhang.upms.dao.mapper.UpmsUserOrganizationMapper;
import com.zhang.upms.dao.model.UpmsUserOrganization;
import com.zhang.upms.dao.model.UpmsUserOrganizationExample;
import com.zhang.upms.rpc.api.UpmsUserOrganizationService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UpmsUserOrganizationService实现
* Created by zihao on 2017/7/25.
*/
@Service
@Transactional
@BaseService
public class UpmsUserOrganizationServiceImpl extends BaseServiceImpl<UpmsUserOrganizationMapper, UpmsUserOrganization, UpmsUserOrganizationExample> implements UpmsUserOrganizationService {

    private static Logger _log = LoggerFactory.getLogger(UpmsUserOrganizationServiceImpl.class);

    @Autowired
    UpmsUserOrganizationMapper upmsUserOrganizationMapper;

    @Override
    public int organization(String[] organizationIds, int id) {
        int result = 0;
        // 删除旧记录
        UpmsUserOrganizationExample upmsUserOrganizationExample = new UpmsUserOrganizationExample();
        upmsUserOrganizationExample.createCriteria()
                .andUserIdEqualTo(id);
        upmsUserOrganizationMapper.deleteByExample(upmsUserOrganizationExample);
        // 增加新纪录
        if (null != organizationIds) {
            for (String organizationId : organizationIds) {
                if (StringUtils.isBlank(organizationId)) {
                    continue;
                }
                UpmsUserOrganization upmsUserOrganization = new UpmsUserOrganization();
                upmsUserOrganization.setUserId(id);;
                upmsUserOrganization.setOrganizationId(NumberUtils.toInt(organizationId));
                result = upmsUserOrganizationMapper.insertSelective(upmsUserOrganization);
            }
        }
        return result;
    }
}