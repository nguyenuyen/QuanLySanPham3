package dao;

import connection.ConnectDatabase;
import model.Product;
import model.Type;
import model.UserAccount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    public static final Logger logger = LogManager.getRootLogger();

    public int findUser_idByEmail(String email) {
        ConnectDatabase myConnect = new ConnectDatabase();
        Connection conn = null;
        int user_id = 0;
        try {
            conn = ConnectDatabase.getConnecttion();
            if (conn == null) logger.error("loi ket noi database");
            String sql = "select id from users where users.email= ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            logger.error(ps.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user_id = rs.getInt("id");
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
        return user_id;
    }

    public int addProduct(Product product, String name) {
        ConnectDatabase myConnect = new ConnectDatabase();
        Connection conn = null;
        try {
            conn = ConnectDatabase.getConnecttion();
            if (conn == null) logger.error("loi ket noi database");
            if (conn == null) return 0;
            String sql = "insert into product (name, user_id, price,type_id )  values (?,?,?,(select id from type where type.name = ?))";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setInt(2, product.getUser_id());
            ps.setInt(3, product.getPrice());
            ps.setString(4, name);
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

    public List<Product> findAllProduct() {
        Connection conn = null;
        List<Product> products = new ArrayList<>();
        try {
            conn = ConnectDatabase.getConnecttion();
            if (conn == null) logger.error("loi ket noi database");
            String sql = "select p.name,p.id, p.price ,p.user_id,t.name as type from product as p  inner join type t on p.type_id = t.id";
            PreparedStatement ps = conn.prepareStatement(sql);
            logger.error(ps.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                products.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getInt("price"), rs.getString("type"), rs.getInt("user_id")));
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
        return products;
    }

    public int editProduct(Product product) {
        Connection conn = null;
        try {
            conn = new ConnectDatabase().getConnecttion();
            if (conn == null) logger.error("loi ket noi database");
            String sql = "update product set name = ?, price = ?, type_id = (select id from type where type.name = ?) where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setInt(2, product.getPrice());
            ps.setString(3, product.getType());
            ps.setInt(4, product.getId());
            logger.error(ps.toString());
            int result = ps.executeUpdate();
            if (result > 0) {
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
                e.printStackTrace();
            }
        }
        return 0;

    }

    public int deleteProduct(int id) throws SQLException {
        ConnectDatabase myConnect = new ConnectDatabase();
        Connection conn = null;
        try {
            conn = ConnectDatabase.getConnecttion();
            if (conn == null) logger.error("loi ket noi database");
            String sql = "delete from product where id = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            logger.error(ps.toString());
            int result = ps.executeUpdate();
            if (result > 0) {
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
                logger.error("loi Exception khong dong ket noi duoc : ");
            }
        }
        return 0;
    }

    public Product findProductById(int id) {
        ConnectDatabase myConnect = new ConnectDatabase();
        Connection conn = null;
        Product product = null;
        try {
            conn = ConnectDatabase.getConnecttion();
            if (conn == null) logger.error("loi ket noi database");
            String sql = "select p.* ,type.name as type  from product as p , type  where p.type_id = type.id and p.id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            logger.error(ps.toString());
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                product = new Product(resultSet.getString("name"), resultSet.getInt("price"), resultSet.getString("type"), resultSet.getInt("user_id"));
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
        return product;
    }

    public List<Type> findAllTypeProduct() {
        ConnectDatabase myConnect = new ConnectDatabase();
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

    public int addTypeProduct(String type) {
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

    public int deleteTypeProduct(String name) {

        Connection conn = null;
        try {
            conn = ConnectDatabase.getConnecttion();
            if (conn == null) logger.error("loi ket noi database");
            String sql1 = "delete from type as ur where ur.name =? ";
            PreparedStatement ps = conn.prepareStatement(sql1);
            ps.setString(1, name);
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

    public int editTypeProduct(String name, String type) {
        Connection conn = null;
        try {
            conn = ConnectDatabase.getConnecttion();
            if (conn == null) logger.error("loi ket noi database");
            String sql1 = "update type set name =? where id = (select id from type where type = ?)  ";
            PreparedStatement ps = conn.prepareStatement(sql1);
            ps.setString(1, name);
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
