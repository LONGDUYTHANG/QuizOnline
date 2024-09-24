/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dao.CategoryDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Subject;
import model.SubjectCategory;

/**
 *
 * @author Phuong Anh
 */
public class SubjectDAO extends DBContext{
    /**
     * Get a list of subjects
     * @return an array list
     */
    public ArrayList<Subject> getSubject() {
        PreparedStatement stm;
        ResultSet rs; 
        ArrayList<Subject> subject_list = new ArrayList<>();
        try {
            String strSelect = "SELECT * FROM Subject ";
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setAccountId(rs.getInt("account_id"));
                subject.setCategoryId(rs.getInt("category_id"));
                subject.setCreatedDate(rs.getTimestamp("created_date"));
                subject.setDescription(rs.getString("description"));
                subject.setIsFeatured(rs.getBoolean("isFeatured"));
                subject.setStatus(rs.getBoolean("status"));
                subject.setSubjectId(rs.getInt("subject_id"));
                subject.setSubjectName(rs.getString("subject_name"));
                subject.setTagline(rs.getString("tagline"));
                subject.setThumbnail(rs.getString("thumbnail"));
                
                subject_list.add(subject);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return subject_list;
    }
    
    /**
     * Get information of a subject given by the subject ID
     * @param subject_id
     * @return a subject object 
     */
    public Subject getSubjectByID(int subject_id){
        PreparedStatement stm;
        ResultSet rs;
        Subject mySubject = new Subject();
        try {
            String strSelect = "SELECT * FROM Subject where subject_id=?";
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, subject_id);
            rs = stm.executeQuery();
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setAccountId(rs.getInt("account_id"));
                subject.setCategoryId(rs.getInt("category_id"));
                subject.setCreatedDate(rs.getTimestamp("created_date"));
                subject.setDescription(rs.getString("description"));
                subject.setIsFeatured(rs.getBoolean("isFeatured"));
                subject.setStatus(rs.getBoolean("status"));
                subject.setSubjectId(rs.getInt("subject_id"));
                subject.setSubjectName(rs.getString("subject_name"));
                subject.setTagline(rs.getString("tagline"));
                subject.setThumbnail(rs.getString("thumbnail"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return mySubject;
    }
    public List<Subject> getAllSubject() {
        List<Subject> subjects = new ArrayList<>();
        String sql = "SELECT subject_id, subject_name, category_id, status, isFeatured, thumbnail, tagline, description, account_id, created_date FROM Subject";

        try (
                PreparedStatement pstmt = connection.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setSubjectId(rs.getInt("subject_id"));
                subject.setSubjectName(rs.getString("subject_name"));

                CategoryDAO cDao = new CategoryDAO();
                SubjectCategory sc = cDao.getCategoryById(rs.getInt("category_id"));
                subject.setCategoryId(rs.getInt("category_id"));

                subject.setStatus(rs.getInt("status")==1);
                subject.setIsFeatured(rs.getBoolean("isFeatured"));
                subject.setThumbnail(rs.getString("thumbnail"));
                subject.setTagline(rs.getString("tagline"));
                subject.setDescription(rs.getString("description"));

                AccountDAO aDao = new AccountDAO();
                 Account acc = aDao.getAccountById(rs.getString("account_id"));
//                subject.setAccountId(acc);
//                subject.setCreatedDate(rs.getDate("created_date"));

                subjects.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subjects; 
    }
    
    public static void main(String[] args) {
        SubjectDAO a =new SubjectDAO();
        ArrayList<Subject> h= a.getSubject();
        for(Subject s:h){
        System.out.println(s.getDescription());
        }
    }
    public void createNewSubject(String subjectName, String subjectCategory, int status, boolean featured, String thumbnail, String tagLine, String description, String accountId) {
        String sql = "INSERT INTO Subject (subject_name, category_id, status, isFeatured, thumbnail, tagline, description, account_id, created_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, GETDATE())";

        try (
                PreparedStatement pstmt = connection.prepareStatement(sql)) {

            // Set parameters for the prepared statement
            pstmt.setString(1, subjectName);
            pstmt.setString(2, subjectCategory); // Ensure this matches your category ID from the categories table
            pstmt.setInt(3, status);
            pstmt.setBoolean(4, featured);
            pstmt.setString(5, thumbnail);
            pstmt.setString(6, tagLine);
            pstmt.setString(7, description);
            pstmt.setString(8, accountId);

            // Execute the insert
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging
            // Handle exceptions (e.g., log, rethrow, or show an error message)
        }
    }
}
