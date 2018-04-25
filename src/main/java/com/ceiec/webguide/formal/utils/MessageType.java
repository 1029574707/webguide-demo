/******************************************************************
 * MessageType.java
 * Copyright 2017 by CEIEC Company. All Rights Reserved.
 * CreateDate：2017年9月4日
 * Author：NieLixiang
 * Version：1.0.0
 ******************************************************************/

package com.ceiec.webguide.formal.utils;

/**
 * CreateDate：2017/9/4
 * Author：NieLixiang
 * Description: 该类主要用于保存舆情监控系统的返回消息类型
 **/
public enum MessageType {

    /** 请求成功 */
    SUCCESS("0000", "请求成功"),

    /** 未知异常 */
    UNKNOWN("9999", "请求失败，未知异常");

    /** 消息码 */
    private String code;

    /** 消息内容 */
    private String content;

    /**
     * <b>构造方法</b>
     * <br/>
     *
     * @param code 消息码
     * @param content 消息内容
     */
    private MessageType(String code, String content) {
        this.code = code;
        this.content = content;
    }

    /**
     * <b>方法说明：</b>
     * <ul>
     * 获取消息码
     * </ul>
     *
     * @return String 消息码
     */
    public String getCode() {
        return code;
    }

    /**
     * <b>方法说明：</b>
     * <ul>
     * 获取消息内容
     * </ul>
     *
     * @return String 消息内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 将消息码转换为对应的消息类型枚举对象
     *
     * @param code 消息码
     * @return MessageType 消息类型枚举对象
     */
    public static MessageType getMessageTypeEnum(String code) {
        for (MessageType messageType : MessageType.values()) {
            if (messageType.code.equals(code)) {
                return messageType;
            }
        }
        throw new IllegalArgumentException("Illegal return code: " + code);
    }

    /**
     * <b>方法说明：</b>
     * <ul>
     * 获取当前消息类型对应的国际化日志配置文件的键值
     * </ul>
     *
     * @return String 国际化日志配置文件的键值
     */
    public String generateI18Nkey() {
        return this.getDeclaringClass().getSimpleName().toLowerCase() + "." + this.name().toLowerCase().replace("_", ".");
    }

    /**
     * <b>方法说明：</b>
     * <ul>
     * 工具方法，生成国际化日志配置文件的键值
     * </ul>
     */
    public static void main(String[] args) {
        for (MessageType messageType : MessageType.values()) {
            String key = messageType.generateI18Nkey();
            System.out.println(key);
        }
    }
}
