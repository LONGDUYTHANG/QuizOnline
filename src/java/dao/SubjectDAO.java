package dao;

import dal.DBContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Subject;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.SubjectCategory;

public class SubjectDAO extends DBContext {

    public void createNewSubject(String subjectName, String subjectCategory, int status, boolean featured, String thumbnail, String tagLine, String description, String accountId) {
        String sql = "INSERT INTO Subject (subject_name, category_id, status, isFeatured, thumbnail, tagline, description, account_id, created_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, GETDATE())";

        try (
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

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

    public static void main(String[] args) {
        // Create an instance of SubjectDAO
        SubjectDAO subjectDAO = new SubjectDAO();

        // Test data for creating a new subject
        String subjectName = "Mathematics";
        String subjectCategory = "1"; // Assuming "1" is a valid category ID in your database
        int status = 1; // Assuming 1 means active
        boolean featured = true; // Indicates if the subject is featured
        String thumbnail = "path/to/thumbnail.jpg"; // Path to the thumbnail image
        String tagLine = "Learn the basics of Mathematics"; // Subject tagline
        String description = "This course covers fundamental concepts of Mathematics."; // Description of the subject
        String accountId = "2"; // Assuming "123" is a valid account ID

        // Call the method to create a new subject
        try {
            subjectDAO.createNewSubject(subjectName, subjectCategory, status, featured, thumbnail, tagLine, description, accountId);
            System.out.println("New  created successfully!");
        } catch (Exception e) {
            System.err.println("Error creating subject: " + e.getMessage());
        }
    }

    public Subject getSubjectById(int subjectId) {
        String sql = "SELECT subject_id, subject_name, category_id, status, isFeatured, thumbnail, tagline, description, account_id, created_date "
                + "FROM Subject WHERE subject_id = ?";

        try (
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set the subject ID parameter
            pstmt.setInt(1, subjectId);

            // Execute the query
            ResultSet rs = pstmt.executeQuery();

            // If a record is found, map it to the Subject object
            if (rs.next()) {
                Subject subject = new Subject();

                subject.setSubject_id(rs.getInt("subject_id"));
                subject.setSubject_name(rs.getString("subject_name"));

                CategoryDAO cDao = new CategoryDAO();
                SubjectCategory sc = cDao.getCategoryById(rs.getInt("category_id"));
                subject.setCategory(sc);

                subject.setStatus(rs.getInt("status"));
                subject.setIs_featured(rs.getBoolean("isFeatured"));
                subject.setThumbnail(rs.getString("thumbnail"));
                subject.setTag_line(rs.getString("tagline"));
                subject.setDescription(rs.getString("description"));

                AccountDAO aDao = new AccountDAO();
                Account acc = aDao.getAccountById(rs.getString("account_id"));
                subject.setAccount_id(acc);
                subject.setCreated_date(rs.getDate("created_date"));

                return subject;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Subject> getAllSubject() {
        List<Subject> subjects = new ArrayList<>();
        String sql = "SELECT subject_id, subject_name, category_id, status, isFeatured, thumbnail, tagline, description, account_id, created_date FROM Subject";

        try (
                PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setSubject_id(rs.getInt("subject_id"));
                subject.setSubject_name(rs.getString("subject_name"));

                CategoryDAO cDao = new CategoryDAO();
                SubjectCategory sc = cDao.getCategoryById(rs.getInt("category_id"));
                subject.setCategory(sc);

                subject.setStatus(rs.getInt("status"));
                subject.setIs_featured(rs.getBoolean("isFeatured"));
                subject.setThumbnail(rs.getString("thumbnail"));
                subject.setTag_line(rs.getString("tagline"));
                subject.setDescription(rs.getString("description"));

                AccountDAO aDao = new AccountDAO();
                Account acc = aDao.getAccountById(rs.getString("account_id"));
                subject.setAccount_id(acc);
                subject.setCreated_date(rs.getDate("created_date"));

                subjects.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subjects; 
    }

}
