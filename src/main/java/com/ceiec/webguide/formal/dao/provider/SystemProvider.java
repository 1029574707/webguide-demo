package com.ceiec.webguide.formal.dao.provider;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.jdbc.SQL;

import static com.ceiec.webguide.formal.constant.MySqlConstant.USERACCOUNTTABLE;

/**
 * CreateDate: 2018/4/24 <br/>
 * Author: WangHao <br/>
 * Description: SystemDao的provider类，具体实现操作数据库
 **/
public class SystemProvider {

    public String getUsersWithCondition(JSONObject condition) {
        String sql = new SQL() {
            {
                SELECT("*");
                FROM(USERACCOUNTTABLE);
                String keywords = condition.getString("keywords");
                if (keywords != null && !"".equals(keywords)) {
                    WHERE(" user_name LIKE CONCAT ('%', #{keywords}, '%') ");
                    OR();
                    WHERE(" job_number LIKE CONCAT ('%', #{keywords}, '%') ");
                }
                Integer role = condition.getInteger("role");
                if (role != null){
                    WHERE(" role = #{role} ");
                }
            }
        }.toString();

        Integer start = condition.getInteger("start");
        Integer size = condition.getInteger("size");
        int startIndex = (start - 1) * size;

        sql += " limit #{startIndex}, #{size} ";
        return sql;
    }

    public String updateSysParam(JSONObject paramInfoJson) {
        String sql = new SQL() {

        }.toString();
        return sql;
    }

    public String getSysLogsWithCondition(JSONObject condition) {
        String sql = new SQL() {

        }.toString();
        return sql;
    }

    public String getTodaySysLogsWithCondition(JSONObject condition) {
        String sql = new SQL() {

        }.toString();
        return sql;
    }

    public String countUsersWithCondition(JSONObject condition){
        return new SQL(){
            {
                SELECT("count(*)");
                FROM(USERACCOUNTTABLE);
                String keywords = condition.getString("keywords");
                if (keywords != null && !"".equals(keywords)) {
                    WHERE(" user_name LIKE CONCAT ('%', #{keywords}, '%') ");
                    OR();
                    WHERE(" job_number LIKE CONCAT ('%', #{keywords}, '%') ");
                }
                Integer role = condition.getInteger("role");
                if (role != 0){
                    WHERE(" role = #{role} ");
                }
            }
        }.toString();
    }
}
