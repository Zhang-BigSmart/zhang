package com.zhang.cms.rpc.api;

import com.zhang.common.base.BaseServiceMock;
import com.zhang.cms.dao.mapper.CmsSettingMapper;
import com.zhang.cms.dao.model.CmsSetting;
import com.zhang.cms.dao.model.CmsSettingExample;

/**
* 降级实现CmsSettingService接口
* Created by zihao on 2017/8/7.
*/
public class CmsSettingServiceMock extends BaseServiceMock<CmsSettingMapper, CmsSetting, CmsSettingExample> implements CmsSettingService {

}
