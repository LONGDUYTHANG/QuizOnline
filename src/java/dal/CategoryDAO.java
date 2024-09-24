/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.util.ArrayList;
import java.util.List;
import model.SubjectCategory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author trung
 */
public class CategoryDAO extends DBContext {

    public List<SubjectCategory> getAllCategory() {
        List<SubjectCategory> categories = new ArrayList<>();
        String sql = "SELECT * FROM Category"; 

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SubjectCategory category = new SubjectCategory(); 
                category.setCategory_id(rs.getInt("category_id")); 
                category.setCategory_name(rs.getString("category_name"));
                categories.add(category); 
            }

        } catch (Exception e) {
            e.printStackTrace(); 
        }

        return categories; 
    }
 public SubjectCategory getCategoryById(int categoryId) {
        String sql = "SELECT * FROM Category WHERE category_id = ?";
        SubjectCategory category = null;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, categoryId);
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                category = new SubjectCategory(); 
                category.setCategory_id(rs.getInt("category_id"));
                category.setCategory_name(rs.getString("category_name"));
            }

        } catch (Exception e) {
            e.printStackTrace(); //
        }

        return category; 
    }
    
   

}
