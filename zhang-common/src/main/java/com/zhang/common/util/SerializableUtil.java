package com.zhang.common.util;

import com.alibaba.dubbo.common.utils.StringUtils;
import org.apache.shiro.session.Session;

import java.io.*;

/**
 * @author Edison
 * @ClassName:
 * @Desc: session序列化工具
 * @date 2017/7/30
 * @history
 */
public class SerializableUtil {

    public static String serialize(Session session){
        if (null == session){
            return null;
        }
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(session);
            return org.apache.shiro.codec.Base64.encodeToString(bos.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("serialize session error", e);
        }
    }

    public static Session deserialize(String sessionStr) {
        if (StringUtils.isBlank(sessionStr)){
            return null;
        }
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(org.apache.shiro.codec.Base64.decode(sessionStr));
            ObjectInputStream ois = new ObjectInputStream(bis);
            return (Session) ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException("deserialize session error", e);
        }
    }
}
