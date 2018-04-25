//package com.ceiec.webguide.formal;
//
//import com.alibaba.fastjson.JSONObject;
//import com.ceiec.webguide.formal.utils.PropertyReaderUtils;
//import org.apache.tomcat.util.codec.binary.Base64;
//import org.springframework.util.CollectionUtils;
//
//import java.util.ArrayList;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * CreateDate: 2018/4/24 <br/>
// * Author: WangHao <br/>
// * Description:
// **/
//public class Test {
//
//    public static void main(String[] args) {
//        //初始化待返回结果
//        List<PostForwardVO> contentList = new ArrayList<>();
//        byte[] lock = new byte[0];
//
////参数合法性判断
//        if (CollectionUtils.isEmpty(urls)) {
//            return contentList;
//        }
//
////对每个url执行http请求并获取解析结果
//        Map<String, PostForwardVO> urlMap = new ConcurrentHashMap<>(); //原短链接与其对应的长链接内容映射
//        for (String url : urls) {
//            //并发执行解析
//            exec.execute(new Runnable() {
//                @Override
//                public void run() {
//                    long start = System.currentTimeMillis();
//                    PostForwardVO forward = new PostForwardVO();
//                    forward.setUrl(url);
//                    forward.setParseSuccess(false); //默认解析失败
//                    String requestUrl = PropertyReaderUtils.getProValue("inner.post.content.parse");
//                    try {
//                        String postContent = HttpUtils.doGet(requestUrl + Base64.encodeBase64String(url.getBytes()));
//                        JSONObject json = JSONObject.parseObject(postContent);
//                        if (json.getBoolean("success") == Boolean.TRUE) { //解析成功
//                            forward.setParseSuccess(true);
//                            forward.setTitle(json.getString("title"));
//                            forward.setImg(json.getString("img"));
//                            forward.setDesc(json.getString("text"));
//                            forward.setDomain(json.getString("domain"));
//                        }
//                    } catch (Exception e) {
//                        logger.error("unable to parse url:" + url, e);
//                    }
//                    urlMap.put(url, forward);
//
//                    //全部解析完成后唤醒方法返回
//                    synchronized (lock) {
//                        if (urlMap.size() == urls.size()) {
//                            lock.notifyAll();
//                        }
//                    }
//                    logger.debug("url---" + url + ",time---" + (System.currentTimeMillis() - start) + " ms");
//                }
//            });
//        }
//
////等待所有url均被解析完
//        synchronized (lock) {
//            try {
//                long timeout = Long.parseLong(PropertyReaderUtils.getProValue("post.parse.timeout"));
//                lock.wait(timeout); //只要大于上面最慢一个http请求时间即可，到点还未解析完则直接返回已解析部分
//            } catch (InterruptedException e) {
//                //ignore
//            }
//            //按传入顺序返回
//            for (String url : urls) {
//
//            }
//}
