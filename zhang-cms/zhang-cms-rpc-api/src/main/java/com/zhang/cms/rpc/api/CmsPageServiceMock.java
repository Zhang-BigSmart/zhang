package com.zhang.cms.rpc.api;

import com.zhang.common.base.BaseServiceMock;
import com.zhang.cms.dao.mapper.CmsPageMapper;
import com.zhang.cms.dao.model.CmsPage;
import com.zhang.cms.dao.model.CmsPageExample;

/**
* 降级实现CmsPageService接口
* Created by zihao on 2017/8/7.
*/
public class CmsPageServiceMock extends BaseServiceMock<CmsPageMapper, CmsPage, CmsPageExample> implements CmsPageService {

}
