package dao;

import connection.ConnectDatabase;
import model.Product;
import model.UserAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    public int findUser_idByEmail(String email) {
        ConnectDatabase myConnect = new ConnectDatabase();
        Connection conn = null;
        int user_id = 0;
        try {
            conn = ConnectDatabase.getConnecttion();
            String sql = "select id from users where users.email='" + email + "'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user_id = rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user_id;
    }

    public int addProduct(Product product) {
        ConnectDatabase myConnect = new ConnectDatabase();
        Connection conn = null;
        try {
            conn = ConnectDatabase.getConnecttion();
            if (conn == null) return 0;
            String sql = "insert into product (name, user_id, price, type)  values (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setInt(2, product.getUser_id());
            ps.setInt(3, product.getPrice());
            ps.setString(4, product.getType());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Product> findAllProduct() {
        ConnectDatabase myConnect = new ConnectDatabase();
        Connection conn = null;
        List<Product> products = new ArrayList<>();
        try {
            conn = ConnectDatabase.getConnecttion();
            String sql = "select * from product";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                products.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getInt("price"), rs.getString("type"), rs.getInt("user_id")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public int editProduct(Product product) {
        Connection conn = null;
        try {
            conn = new ConnectDatabase().getConnecttion();
            if (conn == null) return 0;
            String sql = "update product set name = ?, price = ?, type = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setInt(2, product.getPrice());
            ps.setString(3, product.getType());
            ps.setInt(4, product.getId());
            int result = ps.executeUpdate();
            if (result > 0) {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
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


            String sql = "delete from product where id = " + id;
            PreparedStatement ps = conn.prepareStatement(sql);
            int result = ps.executeUpdate();
            if (result > 0) {
                return 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public  Product findProductById(int id)
    {
        ConnectDatabase myConnect = new ConnectDatabase();
        Connection conn = null;
        Product  product=null;
        try {
            conn = ConnectDatabase.getConnecttion();
            String sql = "select* from product where id = " + id;
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next())
            {
                product=new Product(resultSet.getString("name"),resultSet.getInt("price"),resultSet.getString("type"),resultSet.getInt("user_id"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  product;
    }

}
