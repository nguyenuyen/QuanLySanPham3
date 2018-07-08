package dao;

import connection.ConnectDatabase;
import model.Product_log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Product_logDao {
    public  static int AddProduct_log(Product_log product_log)
    {
        Connection conn = null;
        try {
            conn = ConnectDatabase.getConnecttion();
            if(conn == null) return 0;
            String sql = "insert into product_log ( username, time, type) values (?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, product_log.getUser());
            preparedStatement.setTimestamp(2,product_log.getTime());
            preparedStatement.setString(3,product_log.getType());
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
