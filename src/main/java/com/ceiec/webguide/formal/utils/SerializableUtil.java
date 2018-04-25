package com.ceiec.webguide.formal.utils;

import java.io.*;

/**
 * CreateDate: 2018/4/10 <br/>
 * Author: WangHao <br/>
 * Description: 用于序列化与反序列化redis的二级缓存
 **/

public class SerializableUtil {

    /**
     * 序列化
     *
     * @param obj 需要序列化的对象
     * @return 序列化结果
     */
    public static byte[] serialize(Object obj) {
        ObjectOutputStream obi;
        ByteArrayOutputStream bai = null;
        byte[] bs = null;
        try {
            bai = new ByteArrayOutputStream();
            obi = new ObjectOutputStream(bai);
            obi.writeObject(obj);
            bs = bai.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bai != null) {
                try {
                    bai.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bs;
    }

    /**
     * 反序列化
     *
     * @param bs 需要反序列化的byte数组
     * @return 反序列化结果
     */
    public static Object unserialize(byte[] bs) {
        ByteArrayInputStream bis = null;
        Object obj = null;
        try {
            bis = new ByteArrayInputStream(bs);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }
}
