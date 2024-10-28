/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dal.CategoryDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.RegisteredSubject;
import model.Role;
import model.Subject;
import model.Category;
import model.SubjectCategory;

/**
 *
 * @author Phuong Anh
 */
public class SubjectDAO extends DBContext {

    /**
     * Get a list of subjects
     *
     * @return an array list
     */
    public ArrayList<Subject> getSubject() {
        PreparedStatement stm;
        ResultSet rs;
        ArrayList<Subject> subject_list = new ArrayList<>();
        try {
            String strSelect = "SELECT * FROM Subject";
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
     *
     * @param subject_id
     * @return a subject object
     */
    public Subject getSubjectByID(int subject_id) {
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
                return subject;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //Error tum lum
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

                subject.setStatus(rs.getInt("status") == 1);
                subject.setIsFeatured(rs.getBoolean("isFeatured"));
                subject.setThumbnail(rs.getString("thumbnail"));
                subject.setTagline(rs.getString("tagline"));
                subject.setDescription(rs.getString("description"));

                AccountDAO aDao = new AccountDAO();
                Account acc = aDao.getAccountById(rs.getString("account_id"));
                subject.setAccountId(acc.getAccount_id());
//                subject.setCreatedDate(rs.g);

                subjects.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subjects;
    }

    //Error tum lum
    public List<Subject> getFeaturedSubject() {
        List<Subject> subjects = new ArrayList<>();
        String sql = "SELECT TOP 5 * FROM Subject;";

        try (
                PreparedStatement pstmt = connection.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setSubjectId(rs.getInt("subject_id"));
                subject.setSubjectName(rs.getString("subject_name"));

                CategoryDAO cDao = new CategoryDAO();
                SubjectCategory sc = cDao.getCategoryById(rs.getInt("category_id"));
                subject.setCategoryId(rs.getInt("category_id"));

                subject.setStatus(rs.getInt("status") == 1);
                subject.setIsFeatured(rs.getBoolean("isFeatured"));
                subject.setThumbnail(rs.getString("thumbnail"));
                subject.setTagline(rs.getString("tagline"));
                subject.setDescription(rs.getString("description"));

                AccountDAO aDao = new AccountDAO();
                Account acc = aDao.getAccountById(rs.getString("account_id"));
                subject.setAccountId(acc.getAccount_id());
//                subject.setCreatedDate(rs.g);

                subjects.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subjects;
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

    public int countEnrolledSubject(Account a) {
        String sql = "select COUNT(*) as countSubject from Registration\n"
                + "where account_id = ?";
        int count = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, a.getAccount_id());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("countSubject");
            }
        } catch (Exception e) {
        }
        return count;
    }

    public ArrayList<Subject> searchSubjects(String keyword) {
        ArrayList<Subject> subjects = new ArrayList<>();
        String sql = "SELECT * FROM Subject WHERE subject_name LIKE ? OR description LIKE ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            String searchKeyword = "%" + keyword + "%";
            pstmt.setString(1, searchKeyword);
            pstmt.setString(2, searchKeyword);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setSubjectId(rs.getInt("subject_id"));
                subject.setSubjectName(rs.getString("subject_name"));
                subject.setCategoryId(rs.getInt("category_id"));
                subject.setStatus(rs.getInt("status") == 1);
                subject.setIsFeatured(rs.getBoolean("isFeatured"));
                subject.setThumbnail(rs.getString("thumbnail"));
                subject.setTagline(rs.getString("tagline"));
                subject.setDescription(rs.getString("description"));
                subject.setAccountId(rs.getInt("account_id"));
                subject.setCreatedDate(rs.getTimestamp("created_date"));

                subjects.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subjects;
    }

    public ArrayList<Subject> searchSubjectsByCategory(String keyword) {
        ArrayList<Subject> subjects = new ArrayList<>();
        String sql = "SELECT * FROM Subject WHERE category_id in (select category_id from Category where category_name= ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            // Sử dụng % để tìm kiếm bất kỳ vị trí nào trong tên hoặc mô tả
            pstmt.setString(1, keyword);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setSubjectId(rs.getInt("subject_id"));
                subject.setSubjectName(rs.getString("subject_name"));
                subject.setCategoryId(rs.getInt("category_id"));
                subject.setStatus(rs.getInt("status") == 1);
                subject.setIsFeatured(rs.getBoolean("isFeatured"));
                subject.setThumbnail(rs.getString("thumbnail"));
                subject.setTagline(rs.getString("tagline"));
                subject.setDescription(rs.getString("description"));
                subject.setAccountId(rs.getInt("account_id"));
                subject.setCreatedDate(rs.getTimestamp("created_date"));

                subjects.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subjects;
    }

    public List<RegisteredSubject> getEnrolledSubjectRecently(Account a) {
        List<RegisteredSubject> subjects = new ArrayList<>();

        String sql = "select top 3 *, cast(r.registration_time as date) enrolled_date from Registration r\n"
                + "join Subject s on r.subject_id = s.subject_id\n"
                + "where r.account_id = ? and r.status_id = 3\n"
                + "order by registration_time desc";

        try {

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, a.getAccount_id());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                RegisteredSubject subject = new RegisteredSubject();
                subject.setSubjectId(rs.getInt("subject_id"));
                subject.setSubjectName(rs.getString("subject_name"));

                subject.setCategoryId(rs.getInt("category_id"));

                subject.setStatus(rs.getInt("status") == 1);
                subject.setIsFeatured(rs.getBoolean("isFeatured"));
                subject.setThumbnail(rs.getString("thumbnail"));
                subject.setTagline(rs.getString("tagline"));
                subject.setDescription(rs.getString("description"));

                subject.setAccountId(a.getAccount_id());
                subject.setCreatedDate(rs.getTimestamp("created_date"));
                subject.setCost(rs.getInt("cost"));
                subject.setList_price(rs.getDouble("list_price"));
                subject.setSale_price(rs.getDouble("sale_price"));
                subject.setRegistration_time(rs.getString("enrolled_date"));
                subject.setValid_to(rs.getString("valid_to"));
                subject.setNote(rs.getString("note"));
//                subject.setCreatedDate(rs.g);

                subjects.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subjects;
    }

    //Error tum lum
    public List<RegisteredSubject> getEnrolledSubject(Account a) {
        List<RegisteredSubject> subjects = new ArrayList<>();
        String sql = "select *,CAST(case when valid_to < GETDATE() then 0 else 1 end as bit) as is_expired, cast(r.registration_time as date) enrolled_date from Registration r\n"
                + "join Subject s on r.subject_id = s.subject_id\n"
                + "where r.account_id = ?\n"
                + "order by registration_time desc";

        try {

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, a.getAccount_id());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                RegisteredSubject subject = new RegisteredSubject();
                subject.setSubjectId(rs.getInt("subject_id"));
                subject.setSubjectName(rs.getString("subject_name"));

                subject.setCategoryId(rs.getInt("category_id"));

                subject.setStatus(rs.getInt("status") == 1);
                subject.setIsFeatured(rs.getBoolean("isFeatured"));
                subject.setThumbnail(rs.getString("thumbnail"));
                subject.setTagline(rs.getString("tagline"));
                subject.setDescription(rs.getString("description"));

                AccountDAO aDao = new AccountDAO();
                Account acc = aDao.getAccountById(rs.getString("account_id"));
                subject.setAccountId(acc.getAccount_id());
                subject.setCreatedDate(rs.getTimestamp("created_date"));
                subject.setCost(rs.getInt("cost"));
                subject.setList_price(rs.getDouble("list_price"));
                subject.setSale_price(rs.getDouble("sale_price"));
                subject.setRegistration_time(rs.getString("enrolled_date"));
                subject.setValid_to(rs.getString("valid_to"));
                subject.setIs_expired(rs.getInt("is_expired") == 1);
                subject.setNote(rs.getString("note"));
//                subject.setCreatedDate(rs.g);

                subjects.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subjects;
    }

    public int countSubjects() {
        String sql = "SELECT COUNT(*) AS totalSubjects FROM Subject";
        int count = 0;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("totalSubjects");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    public List<Subject> getFeaturedSubjects() {
        List<Subject> subjects = getSubject();
        List<Subject> featuredSubjects = new ArrayList<>();

        for (Subject subject : subjects) {
            if (subject.isIsFeatured()) {
                featuredSubjects.add(subject);
            }
        }

        return featuredSubjects;
    }

    public List<Subject> getLatestSubjects() {
        List<Subject> subjects = getSubject();
        subjects.sort((s1, s2) -> s2.getCreatedDate().compareTo(s1.getCreatedDate()));
        return subjects;
    }

    public List<Subject> getOldestSubjects() {
        List<Subject> subjects = getSubject();
        subjects.sort((s1, s2) -> s1.getCreatedDate().compareTo(s2.getCreatedDate()));
        return subjects;
    }

    public List<Subject> getListSubject() {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT * FROM Subject";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int subject_id = rs.getInt("subject_id");
                String subject_name = rs.getString("subject_name");
                int category_id = rs.getInt("category_id");
                boolean status = rs.getBoolean("status");
                boolean isFeatured = rs.getBoolean("isFeatured");
                String thumbnail = rs.getString("thumbnail");
                String tagline = rs.getString("tagline");
                String description = rs.getString("description");
                int account_id = rs.getInt("account_id");
                java.sql.Timestamp created_date = rs.getTimestamp("created_date");
                Subject subject = new Subject(subject_id, subject_name, category_id, status, isFeatured, thumbnail, tagline, description, account_id, created_date);
                list.add(subject);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }

    public boolean isSubjectRegistered(int accountId, int subjectId) {
        String sql = "SELECT 1 FROM Registration WHERE account_id = ? AND subject_id = ? and status_id = 3";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, accountId);
            pstmt.setInt(2, subjectId);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();  // Trả về true nếu có kết quả, tức là đã đăng ký
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Trả về false nếu có lỗi hoặc không tìm thấy bản ghi
    }

    public static void main(String[] args) {
        SubjectDAO subjectDAO = new SubjectDAO();
        int totalSubjects = subjectDAO.countSubjects();
        System.out.println("Tổng số lượng subjects: " + totalSubjects);
    }

}
