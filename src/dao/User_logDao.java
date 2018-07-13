package dao;

import connection.ConnectDatabase;
import model.User_log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class User_logDao {
    public static int AddUser_log(User_log user_log) {
        Logger logger = LogManager.getRootLogger();
        Connection conn = null;
        try {
            conn = ConnectDatabase.getConnecttion();
            if(conn == null) logger.error("khong ket noi duoc databse");
            String sql = "insert into user_log ( username, time, type) values (?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,user_log.getUser());
            preparedStatement.setTimestamp(2,user_log.getTime());
            preparedStatement.setString(3,user_log.getType());
            logger.info(sql);
            int result = preparedStatement.executeUpdate();
            if(result > 0)
            {
                return 1;
            }
        } catch (Exception e) {
            logger.error("khong ket noi duoc database Exception"+e.getMessage());
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                logger.warn("khong dong ket noi databse duoc ");
            }
        }
        return 0;
    }


}
