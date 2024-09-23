/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import controller.PricePackage;
import dal.DBContext;
import dal.SubjectDAO;
import java.util.ArrayList;
import java.util.List;
import model.SubjectCategory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Dimension;
import model.DimensionType;
import model.Subject;
import model.Package;

/**
 *
 * @author trung
 */
public class PackageDAO extends DBContext {

    public List<Package> getAllPackage() {
        List<Package> packageList = new ArrayList<>();
        String sql = "SELECT [package_id], [package_name], [duration], [listPrice], [salePrice], [status], [subject_id] FROM [QuizOnline].[dbo].[Package]";

        try (
                PreparedStatement pstmt = connection.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                // Create a new Package object for each row in the ResultSet
                Package pkg = new Package();
                pkg.setPackage_id(rs.getInt("package_id"));
                pkg.setPackage_name(rs.getString("package_name"));
                pkg.setDuration(rs.getInt("duration"));
                pkg.setListPrice(rs.getDouble("listPrice"));
                pkg.setSalePrice(rs.getDouble("salePrice"));

                pkg.setStatus(rs.getString("status"));

                SubjectDAO sDao = new SubjectDAO();

                pkg.setSubject_id(sDao.getSubjectByID(rs.getInt("subject_id")));

                packageList.add(pkg);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
        }

        return packageList;
    }

    public boolean createPackage(String packageName, int duration, double listPrice, double salePrice, int status, int subjectId) {
        String sql = "INSERT INTO [dbo].[Package] (package_name, duration, listPrice, salePrice, status, subject_id) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, packageName);
            pstmt.setInt(2, duration);
            pstmt.setDouble(3, listPrice);
            pstmt.setDouble(4, salePrice);
            pstmt.setInt(5, status);
            pstmt.setInt(6, subjectId);

            int rowsAffected = pstmt.executeUpdate();

            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        // Create an instance of PackageDAO
        PackageDAO packageDAO = new PackageDAO();

        // Sample data for the new package
        String packageName = "Sample Package";
        int duration = 30; // Duration in days
        double listPrice = 100.0; // Original price
        double salePrice = 80.0; // Sale price
        int status = 0; // 0 for active, 1 for inactive
        int subjectId = 6; // Assuming there is a subject with ID 1

        // Call createPackage method
        boolean isCreated = packageDAO.createPackage(packageName, duration, listPrice, salePrice, status, subjectId);

        // Print the result
        if (isCreated) {
            System.out.println("Package created successfully!");
        } else {
            System.out.println("Failed to create package.");
        }

    }

    public Package getPricePackageById(int packageId) {
        Package pkg = null;
        String sql = "SELECT [package_id], [package_name], [duration], [listPrice], [salePrice], [status], [subject_id] "
                + "FROM [dbo].[Package] WHERE [package_id] = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, packageId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    pkg = new Package();
                    pkg.setPackage_id(rs.getInt("package_id"));
                    pkg.setPackage_name(rs.getString("package_name"));
                    pkg.setDuration(rs.getInt("duration"));
                    pkg.setListPrice(rs.getDouble("listPrice"));
                    pkg.setSalePrice(rs.getDouble("salePrice"));
                    pkg.setStatus(rs.getString("status"));

                    SubjectDAO sDao = new SubjectDAO();
                    pkg.setSubject_id(sDao.getSubjectByID(rs.getInt("subject_id")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pkg;
    }

    public boolean updatePackageById(int id, String packageName, int duration, double listPrice, double salePrice, int status, int subjectId) {
        String sql = "UPDATE [dbo].[Package] SET package_name = ?, duration = ?, listPrice = ?, salePrice = ?, status = ?, subject_id = ? WHERE package_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, packageName);
            pstmt.setInt(2, duration);
            pstmt.setDouble(3, listPrice);
            pstmt.setDouble(4, salePrice);
            pstmt.setInt(5, status);
            pstmt.setInt(6, subjectId);
            pstmt.setInt(7, id);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
public boolean deleteById(int id) {
    String sql = "DELETE FROM [dbo].[Package] WHERE [package_id] = ?";
    
    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
        pstmt.setInt(1, id);
        
        int rowsAffected = pstmt.executeUpdate();
        return rowsAffected > 0; 
    } catch (Exception e) {
        e.printStackTrace(); 
        return false; 
    }
}


}