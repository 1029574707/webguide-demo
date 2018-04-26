package com.ceiec.webguide.formal.dao.provider;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.jdbc.SQL;

import static com.ceiec.webguide.formal.constant.MySqlConstant.SYSTEMLOGTABLE;
import static com.ceiec.webguide.formal.constant.MySqlConstant.USERACCOUNTTABLE;
import static com.ceiec.webguide.formal.constant.MySqlConstant.SYSTEMTODAYLOGTABLE;

/**
 * CreateDate: 2018/4/24 <br/>
 * Author: WangHao <br/>
 * Description: SystemDao的provider类，具体实现操作数据库
 **/
public class SystemProvider {

    public String getSysLogsWithCondition(JSONObject condition) {
        return generateSysLogSQL(condition, SYSTEMLOGTABLE);
    }


    public String getTodaySysLogsWithCondition(JSONObject condition) {
        return generateSysLogSQL(condition, SYSTEMTODAYLOGTABLE);
    }


    public String countUsersWithCondition(JSONObject condition) {
        return new SQL() {
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
                if (role != null && role != 0) {
                    WHERE(" role = #{role} ");
                }
            }
        }.toString();
    }


    private String generateSysLogSQL(JSONObject condition, String tableName) {
        String sql = new SQL() {
            {
                SELECT("l.*");
                Integer role = condition.getInteger("role");
                if (role != null) {
                    FROM(tableName + " l, " + USERACCOUNTTABLE + " u ");
                    WHERE(" u.role = #{role} ");
                    WHERE(" l.operator_id = u.user_id ");
                } else {
                    FROM(tableName + " l ");
                }
                String keywords = condition.getString("keywords");
                if (keywords != null && !"".equals(keywords)) {
                    WHERE(" (l.task_id LIKE CONCAT('%', #{keywords}, '%') OR l.log_content LIKE CONCAT('%', #{keywords}, '%')) ");
                }
                String startTime = condition.getString("startTime");
                if (startTime != null && !"".equals(startTime)) {
                    WHERE(" l.operate_time >= #{startTime} ");
                }
                String endTime = condition.getString("endTime");
                if (endTime != null && !"".equals(endTime)) {
                    WHERE(" l.operate_time <= #{endTime} ");
                }
            }
        }.toString();

        Integer start = condition.getInteger("start");
        Integer size = condition.getInteger("size");
        int startIndex = (start - 1) * size;

        sql += " LIMIT " + startIndex + ", " + size;
        return sql;
    }
}
