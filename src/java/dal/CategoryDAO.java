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
import java.sql.SQLException;
import model.Category;
import model.Subject;

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
 
 public ArrayList<Category> getCategory() {
        PreparedStatement stm;
        ResultSet rs;
        ArrayList<Category> category_list = new ArrayList<>();
        try {
            String strSelect = "SELECT * FROM Category ";
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCategory_id(rs.getInt("category_id"));
                category.setCategory_name(rs.getString("category_name"));
                

                category_list.add(category);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return category_list;
    }
    
   

}
