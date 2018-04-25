package com.ceiec.webguide.formal.utils;

import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * CreateDate: 2018/3/9 <br/>
 * Author: WangHao <br/>
 * Description:
 **/

public class Base64Util {

    /**
     * @Description: 将base64编码字符串转换为图片
     * @Author:
     * @CreateTime:
     * @param imgStr base64编码字符串
     * @param path 图片路径-具体到文件
     * @return
     */
    public static boolean generateImage(String imgStr, String path) throws IOException {
        if (imgStr == null) {
            return false;
        }

        try {
            File image = new File(path);
            File parentFile = new File(image.getParent());
            if(!parentFile.exists()){
                parentFile.mkdirs();
            }
            if(image.exists()){
                image.delete();
            }
            image.createNewFile();

            OutputStream out = new FileOutputStream(path);
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            out.write(b);
            out.flush();
            out.close();
        }  catch (IOException e) {
            throw new IOException(e);
        }
        return true;
    }
}
