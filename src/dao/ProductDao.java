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
import java.text.DecimalFormat;
import java.text.NumberFormat;
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

    public String splitTime(String s)
    {
        String[] data = s.split("\\.");
        return data[0];
    }
    public List<Product> findAllProduct(String email) {
        Connection conn = null;
        List<Product> products = new ArrayList<>();

        int id =0 ;
        try {
            conn = ConnectDatabase.getConnecttion();
            String query1 ="select id from users where email=?";
            PreparedStatement ps = conn.prepareStatement(query1);
            ps.setString(1,email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
            }
            String sql = "select p.name,p.id, p.price ,p.user_id ,t.name as type,p.create_at ,pic.url from product as p  inner join type t on p.type_id = t.id inner join picture pic on pic.id = p.image_id where p.user_id = ? ";
             ps = conn.prepareStatement(sql);
             ps.setInt(1,id);
            logger.error(ps.toString());
             rs = ps.executeQuery();
             String time;
            while (rs.next()) {
                String s= rs.getString("create_at");
                logger.error("create_at : " + s);
                time = splitTime(s);
                products.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getInt("price"), rs.getString("type"),time,rs.getString("url")));
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
            int id =0;
            String query = "select id from type where type.name = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, product.getType());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                id= rs.getInt("id");
            }
            String sql = "update product set name = ?, price = ?, type_id = ? where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setInt(2,product.getPrice());
            ps.setInt(3, id);
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

    public int deleteProduct(int id) {
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

    public int deleteAllProduct(String[] arr)
    {
        Connection conn = null;
        Product product = null;
      /*  String id_checked = value.trim();
        String[] arr = id_checked.split(",");*/
        int resultSet = 0 ;
        try {

            conn = ConnectDatabase.getConnecttion();
            if (conn == null) logger.error("loi ket noi database");
            for(int i= 0 ; i<arr.length ; i++)
            {
                String sql = "DELETE FROM product WHERE id = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1,Integer.parseInt(arr[i]));
                logger.error(ps.toString());
                resultSet = ps.executeUpdate();
            }

            if (resultSet >0) {
                // logger.error(product);
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

    public List<Product> findAllProductToExport(String email) {
        Connection conn = null;
        List<Product> products = new ArrayList<>();
        NumberFormat formatter = new DecimalFormat("#,###,###");
        int id =0 ;
        try {
            conn = ConnectDatabase.getConnecttion();
            String query1 ="select id from users where email=?";
            PreparedStatement ps = conn.prepareStatement(query1);
            ps.setString(1,email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
            }
            String sql = "select p.name,p.id, p.price ,p.user_id,t.name as type,p.create_at from product as p  inner join type t on p.type_id = t.id where p.user_id =? order by price ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            logger.error(ps.toString());
            rs = ps.executeQuery();
            String time;
            while (rs.next()) {
                String s= rs.getString("create_at");
                logger.error("create_at : " + s);
                time = splitTime(s);
                products.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getInt("price"), rs.getString("type"),time));
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

    public int importProduct(String name,int price, String mail,String type,String url){
        Connection conn = null;
        try {
            conn = ConnectDatabase.getConnecttion();
            if (conn == null) logger.error("loi ket noi database");
            if (conn == null) return 0;
            String sql = "insert into product(name,price,user_id,type_id,image_id ) values (?,?,(select id from users where users.email = ?),(select id from type where type.name = ?),(select id from picture where picture.url = ?));";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            ps.setInt(2,price);
            ps.setString(3,mail);
            ps.setString(4,type);
            ps.setString(5,url);

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


}
