package com.kenny.smart.type.handler;

import com.kenny.smart.type.UserStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: UserStatusTypeHandler
 * Function:  用户状态枚举类型处理器
 * Date:      2019/9/20 10:02
 * @author Kenny
 * version    V1.0
 */
public class UserStatusTypeHandler implements TypeHandler<UserStatus> {
    private final Map<Integer, UserStatus> userStatusMap = new HashMap<>();

    public UserStatusTypeHandler() {
        for (UserStatus userStatus : UserStatus.values()) {
            userStatusMap.put(userStatus.getValue(), userStatus);
        }
    }

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, UserStatus userStatus, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i,userStatus.getValue());
    }

    @Override
    public UserStatus getResult(ResultSet resultSet, String s) throws SQLException {
        Integer value = resultSet.getInt(s);
        return userStatusMap.get(value);
    }

    @Override
    public UserStatus getResult(ResultSet resultSet, int i) throws SQLException {
        Integer value = resultSet.getInt(i);
        return userStatusMap.get(value);
    }

    @Override
    public UserStatus getResult(CallableStatement callableStatement, int i) throws SQLException {
        Integer value = callableStatement.getInt(i);
        return userStatusMap.get(value);
    }
}
