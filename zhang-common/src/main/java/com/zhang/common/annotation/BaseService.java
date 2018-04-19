package com.zhang.common.annotation;

import java.lang.annotation.*;

/**
 * @author Edison
 * @ClassName:
 * @Desc:初始化继承BaseService的service
 * @date 2017/7/18
 * @history
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BaseService {
}
