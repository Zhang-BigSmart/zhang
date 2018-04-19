package com.zhang.cms.rpc.api;

import com.zhang.common.base.BaseServiceMock;
import com.zhang.cms.dao.mapper.CmsCategoryMapper;
import com.zhang.cms.dao.model.CmsCategory;
import com.zhang.cms.dao.model.CmsCategoryExample;

/**
* 降级实现CmsCategoryService接口
* Created by zihao on 2017/8/7.
*/
public class CmsCategoryServiceMock extends BaseServiceMock<CmsCategoryMapper, CmsCategory, CmsCategoryExample> implements CmsCategoryService {

}
