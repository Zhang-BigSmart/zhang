package com.zhang.cms.rpc.api;

import com.zhang.common.base.BaseServiceMock;
import com.zhang.cms.dao.mapper.CmsMenuMapper;
import com.zhang.cms.dao.model.CmsMenu;
import com.zhang.cms.dao.model.CmsMenuExample;

/**
* 降级实现CmsMenuService接口
* Created by zihao on 2017/8/7.
*/
public class CmsMenuServiceMock extends BaseServiceMock<CmsMenuMapper, CmsMenu, CmsMenuExample> implements CmsMenuService {

}
