package dao;

import connection.ConnectDatabase;
import model.Product_log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Product_logDao {

    public static int AddProduct_log(Product_log product_log) {
        Logger logger = LogManager.getRootLogger();
        Connection conn = null;
        try {
            conn = ConnectDatabase.getConnecttion();
            if (conn == null) logger.error("khong ket noi duoc database");
            String sql = "insert into product_log ( username, time, type) values (?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, product_log.getUser());
            preparedStatement.setTimestamp(2, product_log.getTime());
            preparedStatement.setString(3, product_log.getType());
            logger.info(sql);
            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                return 1;
            }
        } catch (Exception e) {
            logger.error("khong ket noi duoc database loi Exception"+e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
               logger.warn("khong dong ket noi duoc");
            }
        }
        return 0;
    }

}
