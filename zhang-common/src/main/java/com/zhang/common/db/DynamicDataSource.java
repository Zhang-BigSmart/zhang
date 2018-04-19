package com.zhang.common.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author Edison
 * @ClassName:
 * @Desc: 动态数据源(数据源切换)
 * @date 2017/7/18
 * @history
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private final static Logger _log = LoggerFactory.getLogger(DynamicDataSource.class);

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    @Override
    protected Object determineCurrentLookupKey() {
        return null;
    }

    /**
     * 设置数据源
     * @param dataSource
     */
    public static void setDataSource(String dataSource){
        contextHolder.set(dataSource);
    }

    /**
     * 获取数据源
     * @return
     */
    public static String getDataSource(){
        String dataSource = contextHolder.get();
        //如果没有指定数据源，使用默认数据源
        if(null == dataSource){
            DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getDefault());
        }
        return contextHolder.get();
    }


    /**
     * 清楚数据源
     */
    public static void clearDataSource(){
        contextHolder.remove();
    }

}
