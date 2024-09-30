/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Account;
import model.Role;

/**
 *
 * @author ADMIN
 */
public class AccountDAO extends DBContext {

    /**
     * Tìm account
     *
     * @param email tên đăng nhập
     * @param passwword
     * @return 1 đối tượng account
     */
    public Account getAccount(String email, String passwword) {
        PreparedStatement stm;
        ResultSet rs;
        Account myAccount = new Account();
        try {
            String strSelect = "select * from Account where email like ? and password like ?  ";
            stm = connection.prepareStatement(strSelect);
            stm.setString(1, email);
            stm.setString(2, passwword);
            rs = stm.executeQuery();
            if (rs.next()) {
                myAccount.setAccount_id(rs.getInt("account_id"));
                myAccount.setFirst_name(rs.getString("first_name"));
                myAccount.setLast_name(rs.getString("last_name"));
                myAccount.setGender(rs.getBoolean("gender"));
                myAccount.setEmail(rs.getString("email"));
                myAccount.setMobile(rs.getString("mobile"));
                myAccount.setAvatar(rs.getString("avatar"));
                myAccount.setRole_id(rs.getInt("role_id"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return myAccount;
    }
    
    public Account getAccount(String email) {
        PreparedStatement stm;
        ResultSet rs;
        Account myAccount = new Account();
        try {
            String strSelect = "select * from Account where email like ?";
            stm = connection.prepareStatement(strSelect);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if (rs.next()) {
                myAccount.setAccount_id(rs.getInt("account_id"));
                myAccount.setFirst_name(rs.getString("first_name"));
                myAccount.setLast_name(rs.getString("last_name"));
                myAccount.setGender(rs.getBoolean("gender"));
                myAccount.setEmail(rs.getString("email"));
                myAccount.setMobile(rs.getString("mobile"));
                myAccount.setAvatar(rs.getString("avatar"));
                myAccount.setRole_id(rs.getInt("role_id"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return myAccount;
    }

    public int getRole_Id(String role) {
        PreparedStatement stm;
        ResultSet rs;
        int id = 0;
        try {
            String strSelect = "select role_id from [dbo].[Role] where role_name like ? ";
            stm = connection.prepareStatement(strSelect);
            stm.setString(1, role);
            rs = stm.executeQuery();
            if (rs.next()) {
                id = rs.getInt("role_id");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return id;
    }

    /**
     *
     * @param accountId
     * @return
     */
    public Account getAccountById(String accountId) {
        String sql = "SELECT account_id, full_name, gender, email, mobile, password, avatar, role_id "
                + "FROM Account WHERE account_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            // Set the account ID parameter
            pstmt.setString(1, accountId);

            // Execute the query
            ResultSet rs = pstmt.executeQuery();

            // If a record is found, map it to the Account object
            if (rs.next()) {
                Account account = new Account();
                account.setFirst_name(rs.getString("first_name"));
                account.setLast_name(rs.getString("last_name"));
                account.setGender(rs.getInt("gender") == 1); // Assuming gender is stored as a boolean
                account.setEmail(rs.getString("email"));
                account.setMobile(rs.getString("mobile"));
                account.setPassword(rs.getString("password"));
                account.setAvatar(rs.getString("avatar"));

                account.setRole_id(rs.getInt("role_id"));

                return account;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Account getAccountById(int accountId) {
        String sql = "SELECT * FROM Account WHERE account_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            // Set the account ID parameter
            pstmt.setInt(1, accountId);

            // Execute the query
            ResultSet rs = pstmt.executeQuery();

            /*
             [account_id]
      ,[first_name]
      ,[last_name]
      ,[gender]
      ,[email]
      ,[mobile]
      ,[password]
      ,[avatar]
      ,[role_id]
            */
            // If a record is found, map it to the Account object
            if (rs.next()) {
                Account account = new Account();
                account.setFirst_name(rs.getString("first_name"));
                account.setLast_name(rs.getString("last_name"));
                account.setGender(rs.getInt("gender") == 1); // Assuming gender is stored as a boolean
                account.setEmail(rs.getString("email"));
                account.setMobile(rs.getString("mobile"));
                account.setPassword(rs.getString("password"));
                account.setAvatar(rs.getString("avatar"));
                account.setRole_id(rs.getInt("role_id"));
                account.setAccount_id(rs.getInt("account_id"));
                return account;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     *
     * @param roleId
     * @return
     */
    public Role getRoleById(int roleId) {
        String sql = "SELECT role_id, role_name FROM Role WHERE role_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, roleId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Role role = new Role();
                role.setRole_id(rs.getInt("role_id"));
                role.setRole_name(rs.getString("role_name"));

                return role;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updateProfile(Account a) {
        String sql = "UPDATE [dbo].[Account]\n"
                + "   SET [first_name] = ?\n"
                + "      ,[last_name] = ?\n"
                + "      ,[gender] = ?\n"
                + "      ,[mobile] = ?\n"
                + " WHERE account_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, a.getFirst_name());
             pstmt.setString(2, a.getLast_name());
            pstmt.setInt(3, a.isGender() ? 1 : 0);
            pstmt.setString(4, a.getMobile());
            pstmt.setInt(5, a.getAccount_id());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updatePassword(String newPass, Account a) {
        String sql = """
                     UPDATE [dbo].[Account]
                        SET [password] = ?
                      WHERE account_id = ?""";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, newPass);
            pstmt.setInt(2, a.getAccount_id());
            pstmt.executeUpdate();

        } catch (SQLException e) {
        }
    }

    /**
     * Find an account base on email
     *
     * @param email
     * @return 1 account
     */
    public Account getAccountByEmail(String email) {
        PreparedStatement stm;
        ResultSet rs;
        Account myAccount = new Account();
        try {
            String strSelect = "select * from Account where email like ? ";
            stm = connection.prepareStatement(strSelect);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if (rs.next()) {
                myAccount.setAccount_id(rs.getInt("account_id"));
                myAccount.setFirst_name(rs.getString("first_name"));
                myAccount.setLast_name(rs.getString("last_name"));
                myAccount.setGender(rs.getBoolean("gender"));
                myAccount.setEmail(rs.getString("email"));
                myAccount.setMobile(rs.getString("mobile"));
                myAccount.setAvatar(rs.getString("avatar"));
                myAccount.setRole_id(rs.getInt("role_id"));
                return myAccount;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Add an account(when guest register) (required email and password role_id
     * is 1)
     *
     * @param email
     * @param password
     */
    public void addAccount(String email, String password) {
        PreparedStatement stm;
        try {
            String strSelect = "insert into [dbo].[Account](first_name, last_name, gender, email,password,role_id) VALUES(?,?,?,?,?,?) ";
            stm = connection.prepareStatement(strSelect);
            stm.setString(1, "default");
            stm.setString(2, "name");
            stm.setBoolean(3, true);
            stm.setString(4, email);
            stm.setString(5, password);
            stm.setInt(6, 1);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void changeAvatar(String newAvatar, Account a) {
        String sql = "update Account \n"
                + "set avatar = ?\n"
                + "where account_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, newAvatar);
            ps.setInt(2, a.getAccount_id());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public void updateEmail(String newEmail, Account a) {
        String sql = """
                     UPDATE [dbo].[Account]
                        SET [email] = ?
                      WHERE account_id = ?""";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, newEmail);
            pstmt.setInt(2, a.getAccount_id());
            pstmt.executeUpdate();

        } catch (SQLException e) {
        }
    }
    public Account getAccountById1(String accountId) {
        String sql = "SELECT account_id, full_name, gender, email, mobile, password, avatar, role_id "
                + "FROM Account WHERE account_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            // Set the account ID parameter
            pstmt.setString(1, accountId);

            // Execute the query
            ResultSet rs = pstmt.executeQuery();

            // If a record is found, map it to the Account object
            if (rs.next()) {
                Account account = new Account();
                account.setAccount_id(rs.getInt("account_id"));
                account.setFirst_name(rs.getString("first_name"));
                account.setLast_name(rs.getString("last_name"));
                account.setGender(rs.getBoolean("gender")); // Assuming gender is stored as a boolean
                account.setEmail(rs.getString("email"));
                account.setMobile(rs.getString("mobile"));
                account.setPassword(rs.getString("password"));
                account.setAvatar(rs.getString("avatar"));

                AccountDAO adao = new AccountDAO();
                Role role = adao.getRoleById(rs.getInt("role_id"));

                account.setRole_id(role.getRole_id());

                return account; 
            }

        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        return null;
    }
    public static void main(String[] args) {
        AccountDAO ad = new AccountDAO();
        Account a = ad.getAccountById("4");
        System.out.println(a);
    }
}
