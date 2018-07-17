package connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDatabase {

    public static Connection getConnecttion() {
        Connection cons = null;
        try {
            Class.forName("org.postgresql.Driver");
            cons = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/JspServlet", "postgres", "admin");

        } catch (ClassNotFoundException | SQLException e) {

        }
        return cons;
    }

    public static void main(String[] args) {
        Logger logger = LogManager.getRootLogger();
       ConnectDatabase myconnect=new ConnectDatabase();
        Connection conn=getConnecttion();
        if(conn != null) {
           logger.error("ket noi database thanh cong ");
        }
        else {
            logger.error("ket noi database that bai");
        }
    }
}
///