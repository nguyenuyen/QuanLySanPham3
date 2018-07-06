package dao;

import connection.ConnectDatabase;
import model.UserAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    //Them sua xoa (thao tac)
    public UserAccount findUserByEmailandPass(String user, String pass) {

        UserAccount userAccount = null;
        ConnectDatabase myConnect = new ConnectDatabase();
        Connection conn = null;
        try {
            conn = ConnectDatabase.getConnecttion();

            if (conn == null) return null;

            String sql = " select u.email as email,u.password as password ,r.name as role from users as u inner join user_role as ur ON u.id = ur.user_id " +
                    " inner join role r on ur.role_id= r.id where u.email = ? and u.password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                userAccount = new UserAccount(rs.getString("email"), rs.getString("password"), rs.getString("role"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userAccount;

    }

    public int addUser(UserAccount userAccount) {

        ConnectDatabase myConnect = new ConnectDatabase();
        Connection conn = null;
        try {
            conn = ConnectDatabase.getConnecttion();

            if (conn == null) return 0;
            //B1 : Insert User
            String sql = "insert into users (name, phone, email, password) values(?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userAccount.getName());
            ps.setString(2, userAccount.getPhone());
            ps.setString(3, userAccount.getEmail());
            ps.setString(4, userAccount.getPass());

            ps.executeUpdate();

            //B2:Insert into table User_role
            String sql2 = "insert into user_role (user_id, role_id) values((select id from users where email= ?), 2)";
            ps = conn.prepareStatement(sql2);

            ps.setString(1, userAccount.getEmail());

            int result = ps.executeUpdate();

            if (result > 0) {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<UserAccount> findAllUser() {
        ConnectDatabase myConnect = new ConnectDatabase();
        Connection conn = null;
        List<UserAccount> userAccounts = new ArrayList<>();
        try {
            conn = ConnectDatabase.getConnecttion();
            String sql = "select * from users";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userAccounts.add(new UserAccount(rs.getInt("id"), rs.getString("email"), rs.getString("password"), rs.getString("phone"), rs.getString("name")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return userAccounts;
    }

    public int editUser(int id,UserAccount userAccount) {

        ConnectDatabase myConnect = new ConnectDatabase();
        Connection conn = null;
        try {
            conn = ConnectDatabase.getConnecttion();
            String sql = "update users set name = ?, phone = ? where id = "+id;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userAccount.getName());
            ps.setString(2, userAccount.getPhone());

            int result = ps.executeUpdate();
            if (result > 0) {
                return 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public UserAccount getInformationUserById(int id) {
        UserAccount userAccount = null;
        ConnectDatabase myConnect = new ConnectDatabase();
        Connection conn = null;
        try {
            conn = ConnectDatabase.getConnecttion();
            if(conn == null)  return null;
            String sql = "select * from users where id = " + id;
            PreparedStatement ps= conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                userAccount=new UserAccount(rs.getInt("id"),rs.getString("email"),rs.getString("password"),rs.getString("phone"),rs.getString("name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return userAccount;
    }

    public int deleteUser(int id) {

        ConnectDatabase myConnect = new ConnectDatabase();
        Connection conn = null;
        try {
            conn = ConnectDatabase.getConnecttion();

            String sql1="delete from user_role as ur where ur.user_id = "+id;
            PreparedStatement ps = conn.prepareStatement(sql1);
            ps.executeUpdate();
            String sql = "delete from users where id = "+id;
            ps = conn.prepareStatement(sql);
            int result = ps.executeUpdate();
            if (result > 0) {
                return 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
