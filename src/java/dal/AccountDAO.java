/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Account;

/**
 *
 * @author ADMIN
 */
public class AccountDAO extends DBContext{

   /**
    * Tìm account 
    * @param username tên đăng nhập
    * @param passwword 
    * @return 1 đối tượng account
    */
    public Account getAccount(String username, String passwword) {
        PreparedStatement stm;
        ResultSet rs;
        Account myAccount=new Account();
        try {
            String strSelect = "select * from Account where user_name like ? and password like ?  ";
            stm = connection.prepareStatement(strSelect);
            stm.setString(1, username);
            stm.setString(2, passwword);
            rs = stm.executeQuery();
            if (rs.next()) {
                myAccount.setAccount_id(rs.getInt("account_id"));
                myAccount.setFull_name(rs.getString("full_name"));
                myAccount.setGender(rs.getBoolean("gender"));
                myAccount.setEmail(rs.getString("email"));
                myAccount.setMobile(rs.getString("mobile"));
                myAccount.setUsername(rs.getString("user_name"));
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
        int id=0;
        try {
            String strSelect = "select role_id from [Quiz Online].[dbo].[Role] where role_name like ? ";
            stm = connection.prepareStatement(strSelect);
            stm.setString(1, role);
            rs = stm.executeQuery();
            if (rs.next()) {
                id=rs.getInt("role_id");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return id;
    }
    public static void main(String[] args) {
        AccountDAO a=new AccountDAO();
        Account h=a.getAccount("a", "a");
        System.out.println(h.getRole_id());
    }
}
