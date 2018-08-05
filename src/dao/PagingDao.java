package dao;

import connection.ConnectDatabase;
import model.Product;
import model.UserAccount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PagingDao {
    public static final Logger logger = LogManager.getRootLogger();
    private int noOfRecords;
    public List<Product> viewAllProduct(int offset, int noOfRecords,String email)
    {
        //logger.error("pagingdao : "+query);
        List<Product> list = new ArrayList<Product>();
        Product product = null;
        Connection connection = null;
        int id = 0;
        try {
            connection = ConnectDatabase.getConnecttion();
            String query1 ="select id from users where email=?";
            PreparedStatement ps = connection.prepareStatement(query1);
            ps.setString(1,email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
            }
            String query = "select p.name,p.id, p.price ,p.user_id,t.name as type from product as p  inner join type t on p.type_id = t.id  where p.user_id =?  limit " +noOfRecords +" OFFSET "+ offset;
            ps = connection.prepareStatement(query);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getInt("price"), rs.getString("type"), rs.getInt("user_id")));

            }
            String sql ="SELECT COUNT(*) as tong from product where user_id =?";
            ps=connection.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            if(rs.next())
                this.noOfRecords = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally
        {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }


    public List<UserAccount> viewAllUser(int offset, int noOfRecords)
    {
        String query = "select u.* from users as u inner join user_role as ur ON u.id = ur.user_id and ur.role_id=2  limit " +noOfRecords +" OFFSET "+ offset;
        logger.error("pagingdao : "+query);
        List<UserAccount> list = new ArrayList<UserAccount>();
        UserAccount userAccount = null;
        Connection connection = null;
        try {
            connection = ConnectDatabase.getConnecttion();
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add( new UserAccount(rs.getInt("id"), rs.getString("email"), rs.getString("password"), rs.getString("phone"), rs.getString("name")));
            }
            String sql ="SELECT COUNT(*) as tong from users";
            ps=connection.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next())
                this.noOfRecords = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally
        {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }


    public List<Product> viewAllProductByWord(int offset, int noOfRecords,String email, String s)
    {
        //logger.error("pagingdao : "+query);
        List<Product> list = new ArrayList<Product>();
        Product product = null;
        Connection connection = null;
        int id = 0;
        try {
            connection = ConnectDatabase.getConnecttion();
            String query1 ="select id from users where email=?";
            PreparedStatement ps = connection.prepareStatement(query1);
            ps.setString(1,email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
            }
            String query = "select p.name,p.id, p.price ,p.user_id,t.name as type from product as p  inner join type t on p.type_id = t.id  where p.user_id =? and  ( p.name like '%"+s+"%' or t.name like '%"+s+"%' or p.price like '%"+s+"%') limit " +noOfRecords +" OFFSET "+ offset;
            ps = connection.prepareStatement(query);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getInt("price"), rs.getString("type"), rs.getInt("user_id")));

            }
            String sql ="SELECT COUNT(*) as tong from product ,type where type_id =type.id and ( product.name like '%"+s+"%' or type.name like  '%"+s+"%') ";
            ps=connection.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next())
                this.noOfRecords = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally
        {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }
}
