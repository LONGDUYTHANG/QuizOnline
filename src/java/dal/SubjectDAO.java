/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Subject;

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
    public Subject getSubjectBySubjectID(int subject_id){
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
    
    public static void main(String[] args) {
        SubjectDAO a =new SubjectDAO();
        ArrayList<Subject> h= a.getSubject();
        for(Subject s:h){
        System.out.println(s.getDescription());
        }
    }
}
