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
import model.Registration_Status;

/**
 *
 * @author ADMIN
 */
public class RegistrationDAO extends DBContext {

    /**
     * Get all registration list which contains (user_email, registration_time,
     * subject name, package name, cost, status name)
     *
     * @return an array list
     */
    public ArrayList<Registration> getRegistrationList() {
        PreparedStatement stm;
        PreparedStatement stm_view;
        ResultSet rs;
        ArrayList<Registration> registration_list = new ArrayList<Registration>();
        try {
            String strSelect = "SELECT registration_id, registration_time, account_id, subject_id ,package_id, cost, status_id FROM Registration";
            String strView = "ALTER VIEW [RegistrationView] AS SELECT registration_id, registration_time, account_id, subject_id ,package_id, cost, status_id FROM Registration";
            stm = connection.prepareStatement(strSelect);
            stm_view = connection.prepareStatement(strView);
            stm_view.executeUpdate();
            rs = stm.executeQuery();
            while (rs.next()) {
                Registration registration = new Registration();
                registration.setRegistration_id(rs.getInt("registration_id"));
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
     * Get an user's registration list using his/her email
     *
     * @param email
     * @return an array list
     */
    public ArrayList<Registration> getRegistrationListByEmail(String email) {
        PreparedStatement stm;
        PreparedStatement stm_view;
        ResultSet rs;
        ArrayList<Registration> registration_list = new ArrayList<Registration>();
        try {
            String strSelect = "SELECT registration_id, registration_time, account_id, subject_id ,package_id, cost, status_id FROM Registration"
                    + " WHERE account_id in(SELECT account_id from Account WHERE email LIKE ?)";
            String strView = "ALTER VIEW [RegistrationView] AS SELECT registration_id, registration_time, account_id, subject_id ,package_id, cost, status_id FROM Registration"
                    + " WHERE account_id in(SELECT account_id from Account WHERE email LIKE '" + email + "')";
            stm = connection.prepareStatement(strSelect);
            stm_view = connection.prepareStatement(strView);
            stm.setString(1, email);
            rs = stm.executeQuery();
            stm_view.executeUpdate();
            while (rs.next()) {
                Registration registration = new Registration();
                registration.setRegistration_id(rs.getInt("registration_id"));
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
     *
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

    /**
     * Sort registration list by cost
     *
     * @param type ascending or descending
     * @return a registration list in order
     */
    public ArrayList<Registration> sortRegistrationListByCost(String type) {
        PreparedStatement stm;
        ResultSet rs;
        ArrayList<Registration> registration_list = new ArrayList<Registration>();
        try {
            String strSelect = "SELECT registration_id, registration_time, account_id, subject_id ,package_id, cost, status_id FROM RegistrationView"
                    + " ORDER BY cost " + type + "";
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            while (rs.next()) {
                Registration registration = new Registration();
                registration.setAccount_id(rs.getInt("account_id"));
                registration.setRegistration_id(rs.getInt("registration_id"));
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
     * Sort registration list by date
     *
     * @param type ascending or descending
     * @return a registration list in order
     */
    public ArrayList<Registration> sortRegistrationListByDate(String type) {
        PreparedStatement stm;
        ResultSet rs;
        ArrayList<Registration> registration_list = new ArrayList<Registration>();
        try {
            String strSelect = "SELECT registration_id, registration_time, account_id, subject_id ,package_id, cost, status_id FROM RegistrationView"
                    + " ORDER BY registration_time " + type + "";
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            while (rs.next()) {
                Registration registration = new Registration();
                registration.setAccount_id(rs.getInt("account_id"));
                registration.setRegistration_id(rs.getInt("registration_id"));
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
     * Get registration status list
     *
     * @return an array list
     */
    public ArrayList<Registration_Status> getRegistrationStatus() {
        PreparedStatement stm;
        ResultSet rs;
        ArrayList<Registration_Status> registration_status_list = new ArrayList<Registration_Status>();
        try {
            String strSelect = "SELECT status_id, status_name FROM Registration_Status";
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            while (rs.next()) {
                Registration_Status registration_status = new Registration_Status();
                registration_status.setStatus_id(rs.getInt("status_id"));
                registration_status.setStatus_name(rs.getString("status_name"));
                registration_status_list.add(registration_status);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return registration_status_list;
    }

    /**
     * Get registration list based on subject name
     *
     * @param subject_name
     * @return an array list of registration list
     */
    public ArrayList<Registration> filterRegistrationListBySubject(String subject_name) {
        PreparedStatement stm;
        ResultSet rs;
        ArrayList<Registration> registration_list = new ArrayList<Registration>();
        try {
            String strSelect = "Select registration_id, registration_time, account_id, subject_id ,package_id, cost, status_id FROM Registration"
                    + " WHERE  subject_id in(SELECT subject_id FROM Subject where subject_name LIKE ?)";
            stm = connection.prepareStatement(strSelect);
            stm.setString(1, subject_name);
            rs = stm.executeQuery();
            while (rs.next()) {
                Registration registration = new Registration();
                registration.setAccount_id(rs.getInt("account_id"));
                registration.setRegistration_id(rs.getInt("registration_id"));
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
     * Get a registration list based on registration time
     *
     * @param registration_time
     * @return an array list of registrations
     */
    public ArrayList<Registration> filterRegistrationListByDate(String registration_time) {
        PreparedStatement stm;
        ResultSet rs;
        ArrayList<Registration> registration_list = new ArrayList<Registration>();
        try {
            String strSelect = "Select registration_id, registration_time, account_id, subject_id ,package_id, cost, status_id FROM Registration"
                    + " WHERE registration_time >= '" + registration_time + "' AND registration_time <= '" + registration_time + " 23:59:00.000' ";
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            while (rs.next()) {
                Registration registration = new Registration();
                registration.setAccount_id(rs.getInt("account_id"));
                registration.setRegistration_id(rs.getInt("registration_id"));
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
     * Get a registration list based on status
     *
     * @param status
     * @return an array list of registrations
     */
    public ArrayList<Registration> filterRegistrationListByStatus(String status) {
        PreparedStatement stm;
        ResultSet rs;
        ArrayList<Registration> registration_list = new ArrayList<Registration>();
        try {
            String strSelect = "Select registration_id, registration_time, account_id, subject_id ,package_id, cost, status_id FROM Registration"
                    + " WHERE status_id IN (SELECT status_id FROM Registration_Status WHERE status_name LIKE ?) ";
            stm = connection.prepareStatement(strSelect);
            stm.setString(1, status);
            rs = stm.executeQuery();
            while (rs.next()) {
                Registration registration = new Registration();
                registration.setAccount_id(rs.getInt("account_id"));
                registration.setRegistration_id(rs.getInt("registration_id"));
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

    public ArrayList<Registration> getView() {
        PreparedStatement stm;
        ResultSet rs;
        ArrayList<Registration> registration_list = new ArrayList<Registration>();
        try {
            String strSelect = "SELECT registration_id, registration_time, account_id, subject_id ,package_id, cost, status_id FROM RegistrationView";
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            while (rs.next()) {
                Registration registration = new Registration();
                registration.setAccount_id(rs.getInt("account_id"));
                registration.setRegistration_id(rs.getInt("registration_id"));
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

    public Registration getRegistrationById(int id) {
        PreparedStatement stm;
        ResultSet rs;
        Registration registration = new Registration();
        try {
            String strSelect = "SELECT list_price,sale_price, registration_time, account_id, subject_id ,package_id,valid_from, valid_to, status_id"
                    + " FROM Registration where registration_id=?";
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                registration.setList_price(rs.getDouble("list_price"));
                registration.setRegistration_time(rs.getTimestamp("registration_time").toLocalDateTime());
                registration.setSubject_id(rs.getInt("subject_id"));
                registration.setPackage_id(rs.getInt("package_id"));
                registration.setSale_price(rs.getDouble("sale_price"));
                registration.setStatus_id(rs.getInt("status_id"));
                registration.setValid_from(rs.getTimestamp("valid_from").toLocalDateTime());
                registration.setValid_to(rs.getTimestamp("valid_to").toLocalDateTime());
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return registration;
    }

    public static void main(String[] args) {
        RegistrationDAO a = new RegistrationDAO();
//        ArrayList<Registration> h = a.filterRegistrationListByDate("2024-10-08");
//        ArrayList<Registration> h1 = a.filterRegistrationListBySubject("python");
//        ArrayList<Registration> h2 = a.filterRegistrationListByStatus("Canceled");
//        ArrayList<Registration> common_registration = new ArrayList<>(h1);
//        common_registration.retainAll(h);
//        Registration huyen = a.getRegistrationById(0);
//        //common_registration.retainAll(h2);
//        System.out.println(h.size());
//        System.out.println(h1.size());
//        System.out.println(h2.size());
//        System.out.println(common_registration.size());
//        System.out.println(huyen.getSubject_id());

    }
}
