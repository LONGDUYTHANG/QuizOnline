/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Registration;


/**
 *
 * @author ADMIN
 */
public class RegistrationDAO extends DBContext {

    /**
     * Get all registration list which contains (user_email,
     * registration_time, subject name, package name, cost, status name)
     *
     * @return an array list
     */
    public ArrayList<Registration> getRegistrationList() {
        PreparedStatement stm;
        ResultSet rs;
        ArrayList<Registration> registration_list = new ArrayList<Registration>();
        try {
            String strSelect = "SELECT registration_id, registration_time, account_id, subject_id ,package_id, cost, status_id FROM Registration";
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            if (rs.next()) {
                Registration registration = new Registration();
                registration.setAccount_id(rs.getInt("account_id"));
                registration.setRegistration_time(rs.getTimestamp("registration_time").toLocalDateTime());
                registration.setSubject_id(rs.getInt("subject_id"));
                registration.setPackage_id(rs.getInt("package_id"));
                registration.setCost(rs.getDouble("cost"));
                registration.setStatus_id(rs.getInt("status_id"));
                registration_list.add(registration);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return registration_list;
    }
    
    /**
     * Get status of a registration by status_id
     * @param status_id
     * @return 
     */
       public String getRegistrationStatus(int status_id) {
        PreparedStatement stm;
        ResultSet rs;
        try {
            String strSelect = "SELECT  status_name FROM Registration_Status WHERE status_id = ?";
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, status_id);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString("status_name");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    public static void main(String[] args) {
        RegistrationDAO a= new RegistrationDAO();
        ArrayList<Registration> h= a.getRegistrationList();
        System.out.println(h.get(0).getRegistration_time());
      
    }
}
