package dao;

import connection.ConnectDatabase;
import model.Product;
import model.UserAccount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import security.MD5Library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    //Them sua xoa (thao tac)
    public static final Logger logger = LogManager.getRootLogger();

    public UserAccount findUserByEmailandPass(String user, String pass) {

        UserAccount userAccount = null;
        ConnectDatabase myConnect = new ConnectDatabase();
        Connection conn = null;
        try {
            conn = ConnectDatabase.getConnecttion();

            if (conn == null) logger.error("loi ket noi database");

            String sql = " select u.email as email,u.password as password ,r.name as role from users as u inner join user_role as ur ON u.id = ur.user_id " +
                    " inner join role r on ur.role_id= r.id where u.email = ? and u.password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, user);
            ps.setString(2, MD5Library.md5(pass));
            logger.error(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                userAccount = new UserAccount(rs.getString("email"), rs.getString("password"), rs.getString("role"));
            }

        } catch (SQLException e) {
            logger.error("loi Exception: " + e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                logger.error("khong dong ket noi duoc");
            }
        }

        return userAccount;

    }

    public int addUser(UserAccount userAccount) {

        ConnectDatabase myConnect = new ConnectDatabase();
        Connection conn = null;
        try {
            conn = ConnectDatabase.getConnecttion();

            if (conn == null) logger.error("loi ket noi database");
            //B1 : Insert User
            String sql = "insert into users (name, phone, email, password) values(?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userAccount.getName());
            ps.setString(2, userAccount.getPhone());
            ps.setString(3, userAccount.getEmail());
            ps.setString(4, MD5Library.md5(userAccount.getPass()));

            ps.executeUpdate();
            logger.error(ps.toString());

            //B2:Insert into table User_role
            String sql2 = "insert into user_role (user_id, role_id) values((select id from users where email= ?), 2)";
            ps = conn.prepareStatement(sql2);

            ps.setString(1, userAccount.getEmail());

            int result = ps.executeUpdate();

            if (result > 0) {
                return 1;
            } else {
                throw new SQLException("khong ket noi duoc database loi Exception");
            }
        } catch (SQLException e) {
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

    public List<UserAccount> findAllUser() {
        ConnectDatabase myConnect = new ConnectDatabase();
        Connection conn = null;
        List<UserAccount> userAccounts = new ArrayList<>();
        try {
            conn = ConnectDatabase.getConnecttion();
            if (conn == null) logger.error("loi ket noi database");
            String sql = "select u.* from users as u inner join user_role as ur ON u.id = ur.user_id and ur.role_id=2";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userAccounts.add(new UserAccount(rs.getInt("id"), rs.getString("email"), rs.getString("password"), rs.getString("phone"), rs.getString("name")));
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

        return userAccounts;
    }


    //jkdafjdskjf
    public int editUser(int id, UserAccount userAccount) {
        Connection conn = null;
        try {
            conn = ConnectDatabase.getConnecttion();
            if (conn == null) logger.error("loi ket noi database");
            String sql = "update users set name = ?, phone = ? where id = " + id;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userAccount.getName());
            ps.setString(2, userAccount.getPhone());
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
                logger.error("khong dong ket noi duoc");
            }
        }
        return 0;
    }

    public UserAccount getInformationUserById(int id) {
        UserAccount userAccount = null;
        ConnectDatabase myConnect = new ConnectDatabase();
        Connection conn = null;
        try {
            conn = ConnectDatabase.getConnecttion();
            if (conn == null) logger.error("loi ket noi database");
            if (conn == null) return null;
            String sql = "select * from users where id = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            logger.error(ps.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                userAccount = new UserAccount(rs.getInt("id"), rs.getString("email"), rs.getString("password"), rs.getString("phone"), rs.getString("name"));
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
        return userAccount;
    }

    public int deleteUser(int id) {

        ConnectDatabase myConnect = new ConnectDatabase();
        Connection conn = null;
        try {
            conn = ConnectDatabase.getConnecttion();
            if (conn == null) logger.error("loi ket noi database");
            String sql1 = "delete from user_role as ur where ur.user_id =? ";
            PreparedStatement ps = conn.prepareStatement(sql1);
            ps.setInt(1, id);
            logger.error(ps.toString());
            ps.executeUpdate();

            String sql2 = "delete from product where user_id =?";
            ps = conn.prepareStatement(sql2);
            ps.setInt(1, id);
            logger.error(ps.toString());
            ps.executeUpdate();

            String sql = "delete from users where id =? ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
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

    public UserAccount findUser(String email) {
        UserAccount user = null;
        Connection conn = null;

        try {
            conn = ConnectDatabase.getConnecttion();
            if (conn == null) {
                logger.info("loi ket noi database");
            }

            String sql = "select * from users where email= ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            //logger.error(ps.toString());
            if (rs.next()) {
                user = new UserAccount(rs.getString("phone"),rs.getString("name"));
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
        return user;
    }

    public  int DeleteAllUser(String[] arr){
        Connection conn = null;
        Product product = null;
        int resultSet = 0 ;
        try {

            conn = ConnectDatabase.getConnecttion();
            if (conn == null) logger.error("loi ket noi database");
            for(int i= 0 ; i<arr.length ; i++)
            {
                conn = ConnectDatabase.getConnecttion();
                if (conn == null) logger.error("loi ket noi database");
                String sql1 = "delete from user_role as ur where ur.user_id =? ";
                PreparedStatement ps = conn.prepareStatement(sql1);
                ps.setInt(1, Integer.parseInt(arr[i]));
                logger.error(ps.toString());
                ps.executeUpdate();

                String sql2 = "delete from product where user_id =?";
                ps = conn.prepareStatement(sql2);
                ps.setInt(1, Integer.parseInt(arr[i]));
                logger.error(ps.toString());
                ps.executeUpdate();

                String sql = "delete from users where id =? ";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(arr[i]));
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

}
