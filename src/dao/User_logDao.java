package dao;

import connection.ConnectDatabase;
import model.User_log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class User_logDao {
    public static int AddUser_log(User_log user_log) {
        Connection conn = null;
        try {
            conn = ConnectDatabase.getConnecttion();
            if(conn == null) return 0;
            String sql = "insert into user_log ( username, time, type) values (?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,user_log.getUser());
            preparedStatement.setTimestamp(2,user_log.getTime());
            preparedStatement.setString(3,user_log.getType());
            int result = preparedStatement.executeUpdate();
            if(result > 0)
            {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }


}
