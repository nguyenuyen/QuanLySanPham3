package dao;

import connection.ConnectDatabase;
import model.Image;
import model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImageDao {
    public static final Logger logger = LogManager.getRootLogger();
    public int addImage( String url) {
        ConnectDatabase myConnect = new ConnectDatabase();
        Connection conn = null;
        try {
            conn = ConnectDatabase.getConnecttion();
            if (conn == null) logger.error("loi ket noi database");
            if (conn == null) return 0;
            String sql = "insert into picture (url )  values (?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,url);

            logger.error(ps.toString());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return 1;
            } else {
                throw new SQLException("khong ket noi duoc database loi Exception");
            }
        } catch (Exception e) {
            logger.error("loi Exception: " + e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                logger.error("khong dong ket noi duoc");
            }
        }
        return 0;
    }
    public String splitTime(String s)
    {
        String[] data = s.split("\\.");
        return data[0];
    }
    public List<Image> findAllImage() {
        Connection conn = null;
        List<Image> imageList = new ArrayList<>();

        int id =0 ;
        try {
            conn = ConnectDatabase.getConnecttion();
            String sql = "select * from picture";
            PreparedStatement ps = conn.prepareStatement(sql);
            logger.error(ps.toString());
            ResultSet rs = ps.executeQuery();
            String time;
            while (rs.next()) {
                String s= rs.getString("create_at");
                logger.error("create_at : " + s);
                time = splitTime(s);
                imageList.add(new Image(rs.getInt("id"),rs.getString("url"),rs.getString("create_at")));
            }
        } catch (Exception e) {
            logger.error("loi Exception: " + e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                logger.error("khong dong ket noi duoc");
            }
        }
        return imageList;
    }
}
