package com.ceiec.webguide.formal.enums;

/**
 * CreateDate：2017/11/20 <br/>
 * Author：NieLixiang <br/>
 * Description: 该类主要用于保存Redis缓存的分组key值
 **/
public enum ERedisDomain {

    /** 登陆用户token缓存 */
    TOKEN_LOGIN_USER("token.login.user", "登陆用户token缓存"),
    /** 登录用户请求时间缓存 */
    USER_REQUEST_TIME("user.request.time", "登录用户请求时间缓存"),
    /** mybatis查询二级缓存 */
    MYBATIS_SECOND_CACHE("mybatis.second.cache", "mybatis二级缓存");

    /** 分组key值 */
    private String key;

    /** key值描述 */
    private String desc;

    /**
     * 构造函数
     *
     * @param key 分组key值
     * @param desc key值描述
     */
    private ERedisDomain(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    /**
     * 获取分组key值
     *
     * @return String 分组key值
     */
    public String getKey() {
        return key;
    }

    /**
     * 获取key值描述
     *
     * @return
     */
    public String getDesc() {
        return desc;
    }
}
