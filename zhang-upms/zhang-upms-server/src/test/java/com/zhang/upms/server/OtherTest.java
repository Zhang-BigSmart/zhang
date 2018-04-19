package com.zhang.upms.server;

import com.zhang.common.util.PropertiesFileUtil;
import org.junit.Test;

/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2017/8/1
 * @history
 */
public class OtherTest {

    @Test
    public void test(){
        System.out.println("----"+PropertiesFileUtil.getInstance().get("zhang-admin.version"));
    }

}
