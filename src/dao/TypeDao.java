package dao;

import connection.ConnectDatabase;
import model.Product;
import model.Type;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeDao {

    public static final Logger logger = LogManager.getRootLogger();

    public List<Type> findAllType() {
        Connection conn = null;
        List<Type> types = new ArrayList<>();
        try {
            conn = ConnectDatabase.getConnecttion();
            if (conn == null) logger.error("loi ket noi database");
            String sql = "select * from type";
            PreparedStatement ps = conn.prepareStatement(sql);
            logger.error(ps.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                types.add(new Type(rs.getInt("id"), rs.getString("name")));
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
        return types;
    }

    public int addType(String type) {
        Connection conn = null;
        try {
            conn = ConnectDatabase.getConnecttion();
            if (conn == null) logger.error("loi ket noi database");
            if (conn == null) return 0;
            String sql = "insert into type (name)  values (?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, type);

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

    public int deleteType(int id) {

        Connection conn = null;
        try {
            conn = ConnectDatabase.getConnecttion();
            if (conn == null) logger.error("loi ket noi database");
            String sql ="delete from product as p where type_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            logger.error("xoa san pham lien quan the loai"+ps.toString());
            String sql1 = "delete from type as ur where id = ? ";
            ps = conn.prepareStatement(sql1);
            ps.setInt(1,id);
            int result = ps.executeUpdate();
            logger.error("xoa the loai"+ps.toString());
            if (result > 0) {
                return 1;
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

    public Type findTypeById(int id) {
        Connection conn = null;
        Type type = null;
        try {
            conn = ConnectDatabase.getConnecttion();
            if (conn == null) logger.error("loi ket noi database");
            String sql = "select * from type where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            logger.error(ps.toString());
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                type = new Type(resultSet.getInt("id"),resultSet.getString("name"));
                // logger.error(product);
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
        return type;
    }
    public int editType(String name , int id) {
        Connection conn = null;
        try {
            conn = ConnectDatabase.getConnecttion();
            if (conn == null) logger.error("loi ket noi database");
            String sql1 = "update type set name =? where id = ? ";
            PreparedStatement ps = conn.prepareStatement(sql1);
            ps.setString(1,name);
            ps.setInt(2, id);
            logger.error(ps.toString());
            int result = ps.executeUpdate();
            if (result > 0) {
                return 1;
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

}
