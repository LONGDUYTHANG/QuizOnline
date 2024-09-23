/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Subject;
import java.sql.ResultSet;
import model.Account;
import model.Role;

/**
 *
 * @author trung
 */
public class AccountDAO extends DBContext {

    public Account getAccountById(String accountId) {
        String sql = "SELECT account_id, full_name, gender, email, mobile, password, avatar, role_id "
                + "FROM Account WHERE account_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Set the account ID parameter
            pstmt.setString(1, accountId);

            // Execute the query
            ResultSet rs = pstmt.executeQuery();

            // If a record is found, map it to the Account object
            if (rs.next()) {
                Account account = new Account();
                account.setUser_id(rs.getInt("account_id"));
                account.setFull_name(rs.getString("full_name"));
                account.setGender(rs.getInt("gender")); // Assuming gender is stored as a boolean
                account.setEmail(rs.getString("email"));
                account.setMobile(rs.getString("mobile"));
                account.setPassword(rs.getString("password"));
                account.setAvatar(rs.getString("avatar"));

                AccountDAO adao = new AccountDAO();
                Role role = adao.getRoleById(rs.getInt("role_id"));

                account.setRole_id(role);

                return account; 
            }

        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        return null;
    }

    public Role getRoleById(int roleId) {
        String sql = "SELECT role_id, role_name FROM Role WHERE role_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
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

}
