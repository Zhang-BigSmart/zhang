package com.zhang.cms.rpc.service.impl;

import com.zhang.cms.dao.mapper.CmsUserMapper;
import com.zhang.cms.dao.model.CmsUser;
import com.zhang.cms.dao.model.CmsUserExample;
import com.zhang.cms.rpc.api.CmsApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2017/8/21
 * @history
 */
@Service
@Transactional
public class CmsApiServiceImpl implements CmsApiService{

    @Autowired
    CmsUserMapper cmsUserMapper;

    @Override
    public CmsUser selectCmsUserByUsername(String username) {
        CmsUserExample cmsUserExample = new CmsUserExample();
        cmsUserExample.createCriteria().andUsernameEqualTo(username);
        List<CmsUser> cmsUserList = cmsUserMapper.selectByExample(cmsUserExample);
        if (null != cmsUserList && cmsUserList.size() >0){
            return cmsUserList.get(0);
        }
        return null;
    }
}
