package com.zhang.upms.rpc.api;

import com.zhang.common.base.BaseServiceMock;
import com.zhang.upms.dao.mapper.UpmsLogMapper;
import com.zhang.upms.dao.model.UpmsLog;
import com.zhang.upms.dao.model.UpmsLogExample;

/**
* 降级实现UpmsLogService接口
* Created by zihao on 2017/7/25.
*/
public class UpmsLogServiceMock extends BaseServiceMock<UpmsLogMapper, UpmsLog, UpmsLogExample> implements UpmsLogService {

}
