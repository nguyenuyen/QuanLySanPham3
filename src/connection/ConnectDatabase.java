package connection;

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
            e.printStackTrace();
        }
        return cons;
    }

    public static void main(String[] args) {
       ConnectDatabase myconnect=new ConnectDatabase();
        Connection conn=getConnecttion();
        if(conn != null) {
            System.out.println("thành công");
        }
        else {
            System.out.println("không kết nối được ");
        }
    }
}
