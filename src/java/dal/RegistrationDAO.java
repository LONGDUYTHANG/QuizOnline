/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Account;
import model.Registration;
import model.RegistrationList;

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
    public ArrayList<RegistrationList> getRegistrationList() {
        PreparedStatement stm;
        ResultSet rs;
        ArrayList<RegistrationList> registration_list = new ArrayList<RegistrationList>();
        try {
            String strSelect = "SELECT A.email, B.registration_time, C.subject_name , D.package_name, B.cost, E.status_name FROM\n"
                    + "Account A, Registration B, Subject C, Package D, Registration_Status E  \n"
                    + "WHERE A.account_id=B.account_id \n"
                    + "and B.subject_id=C.subject_id\n"
                    + "and B.package_id=D.package_id\n"
                    + "and B.status_id=E.status_id ";
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            if (rs.next()) {
                RegistrationList registration = new RegistrationList();
                registration.setEmail(rs.getString("email"));
                registration.setRegistration_time(rs.getTimestamp("registration_time").toLocalDateTime());
                registration.setSubject_name(rs.getString("subject_name"));
                registration.setPackage_name(rs.getString("package_name"));
                registration.setCost(rs.getDouble("cost"));
                registration.setStatus(rs.getString("status_name"));
                registration_list.add(registration);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return registration_list;
    }
    public static void main(String[] args) {
        
        RegistrationDAO h= new RegistrationDAO();
        ArrayList<RegistrationList> a= h.getRegistrationList();
        System.out.println(a.get(0).getEmail());
    }
}
