package com.ceiec.webguide.formal.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * CreateDate：2017/11/1
 * Author：NieLixiang
 * Description: 该类主要用于保存http请求的响应信息
 **/
public class ResponseContent {

    /** 返回码 */
    private String code;

    /** 返回码描述 */
    private String error;

    /** 返回数据 */
    private Object mess;

    /**
     * 默认构造函数，用于反射，controller层接口别调
     */
    public ResponseContent() {

    }

    /**
     * 构造函数
     *
     * @param messageType 返回消息类型
     */
    public ResponseContent(MessageType messageType) {
        this.code = messageType.getCode();
        if (messageType.getCode() != MessageType.SUCCESS.getCode()) {
            this.error = messageType.getContent();
        }
    }

    /**
     * 构造函数
     *
     * @param messageType 返回消息类型
     * @param responseData 返回数据
     */
    public ResponseContent(MessageType messageType, Object responseData) {
        this.code = messageType.getCode();
        if (messageType.getCode() != MessageType.SUCCESS.getCode()) {
            this.error = messageType.getContent();
        }
        this.mess = responseData;
    }

    /**
     * 构造函数
     *
     * @param messageType 返回消息类型
     * @param responseData 返回数据
     * @param jsonDataName 返回数据对应的json key
     */
    public ResponseContent(MessageType messageType, Object responseData, String jsonDataName) {
        this.code = messageType.getCode();
        if (messageType.getCode() != MessageType.SUCCESS.getCode()) {
            this.error = messageType.getContent();
        }
        JSONObject json = new JSONObject();
        json.put(jsonDataName, responseData);
        this.mess = json;
    }

    /**
     * 获取返回码
     *
     * @return String 返回码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置返回码
     *
     * @param code 返回码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取返回码描述
     *
     * @return String 返回码描述
     */
    public String getError() {
        return error;
    }

    /**
     * 设置返回码描述
     *
     * @param error 返回码描述
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * 获取返回数据
     *
     * @return Object 返回数据
     */
    public Object getMess() {
        return mess;
    }

    /**
     * 设置返回数据
     *
     * @param mess 返回数据
     */
    public void setMess(Object mess) {
        this.mess = mess;
    }
}
