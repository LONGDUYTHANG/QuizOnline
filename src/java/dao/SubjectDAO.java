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
import model.DimensionType;
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

    public List<SubjectCategory> getAllCategory() {
        List<SubjectCategory> categories = new ArrayList<>();
        String sql = "SELECT * FROM Category";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
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

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
    public List<Dimension> getAllDimension() {
        List<Dimension> dimensions = new ArrayList<>();
        String sql = "SELECT  [dimension_id]\n"
                + "      ,[dimension_name]\n"
                + "      ,[dimension_type_id]\n"
                + "      ,[subject_id]\n"
                + "  FROM [dbo].[Dimension]";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Dimension dimension = new Dimension();
                dimension.setDimension_id(rs.getInt("dimension_id"));
                dimension.setDimension_name(rs.getString("dimension_name"));

                SubjectDAO sDao = new SubjectDAO();
                Subject subject = sDao.getSubjectById(rs.getInt("subject_id"));

                dimension.setSubject_id(subject);

                DimensionDAO dDap = new DimensionDAO();
                DimensionType dt = dDap.getType(rs.getInt("dimension_type_id"));
                dimension.setDimension_type_id(dt);

                dimensions.add(dimension);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dimensions;
    }

    public DimensionType getType(int dimensionTypeId) {
        String sql = "SELECT * FROM Dimension_Type WHERE dimension_type_id = ?";
        DimensionType dimensionType = null;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, dimensionTypeId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                dimensionType = new DimensionType();
                dimensionType.setDimension_type_id(rs.getInt("dimension_type_id"));
                dimensionType.setDimension_type_name(rs.getString("dimension_type_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dimensionType;
    }

    public Dimension getDimensionById(int dimensionId) {
        Dimension dimension = null; // Initialize the dimension object
        String sql = "SELECT [dimension_id], [dimension_name], [dimension_type_id], [subject_id] FROM [dbo].[Dimension] WHERE [dimension_id] = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, dimensionId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                dimension = new Dimension();
                dimension.setDimension_id(rs.getInt("dimension_id"));
                dimension.setDimension_name(rs.getString("dimension_name"));

                SubjectDAO sDao = new SubjectDAO();
                Subject subject = sDao.getSubjectById(rs.getInt("subject_id"));
                dimension.setSubject_id(subject);

                DimensionType dt = getType(rs.getInt("dimension_type_id"));
                dimension.setDimension_type_id(dt);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dimension;
    }

    public List<DimensionType> getAllType() {
        List<DimensionType> types = new ArrayList<>();
        String sql = "SELECT dimension_type_id, dimension_type_name FROM Dimension_Type";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                DimensionType dimensionType = new DimensionType();
                dimensionType.setDimension_type_id(rs.getInt("dimension_type_id"));
                dimensionType.setDimension_type_name(rs.getString("dimension_type_name"));

                types.add(dimensionType);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return types;
    }

    public boolean updateDimension(int id, String dimension_name, int dimension_type_id) {
        String sql = "UPDATE Dimension SET dimension_name = ?, dimension_type_id = ? WHERE dimension_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dimension_name);
            pstmt.setInt(2, dimension_type_id);
            pstmt.setInt(3, id);

            int rowsAffected = pstmt.executeUpdate();

            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean createNewDimension(String dimension_name, int dimension_type_id, int subject_id) {
        String sql = "INSERT INTO Dimension (dimension_name, dimension_type_id, subject_id) VALUES (?, ?, ?)";

        try (
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dimension_name);
            pstmt.setInt(2, dimension_type_id);
            pstmt.setInt(3, subject_id);

            int rowsAffected = pstmt.executeUpdate();

            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteDimension(int id) {
        String sql = "DELETE FROM Dimension WHERE dimension_id = ?"; // SQL query to delete by id
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();

            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
