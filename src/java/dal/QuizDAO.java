/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Dimension;
import model.Lesson_Topic;
import model.Level;
import model.Quiz_Type;
import model.Subject;

/**
 *
 * @author FPT SHOP
 */
public class QuizDAO extends DBContext {
    public List<Subject> getAllSubject() {
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
    public List<Level> getAllLevel() {
        List<Level> list = new ArrayList<>();
        String sql = "SELECT * FROM Level";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int level_id = rs.getInt("level_id");
                String level_name = rs.getString("level_name");
                list.add(new Level(level_id, level_name));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
    
    public Subject getSubjectById(int id) {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT * FROM Subject WHERE subject_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
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
                return subject;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    
    public Level getLevelById(int level_id_raw) {
        String sql = "SELECT * FROM Level WHERE level_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, level_id_raw);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int level_id = rs.getInt("level_id");
                String level_name = rs.getString("level_name");
                return new Level(level_id, level_name);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    
    public Account getAccountById(String accountId) {
        String sql = "SELECT account_id, full_name, gender, email, mobile, password, avatar, role_id "
                + "FROM Account WHERE account_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            // Set the account ID parameter
            pstmt.setString(1, accountId);

            // Execute the query
            ResultSet rs = pstmt.executeQuery();

            // If a record is found, map it to the Account object
            if (rs.next()) {
                Account account = new Account();
                account.setFull_name(rs.getString("full_name"));
                account.setGender(rs.getInt("gender") == 1); // Assuming gender is stored as a boolean
                account.setEmail(rs.getString("email"));
                account.setMobile(rs.getString("mobile"));
                account.setPassword(rs.getString("password"));
                account.setAvatar(rs.getString("avatar"));

                account.setRole_id(rs.getInt("role_id"));

                return account;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public List<Quiz_Type> getAllQuizType() {
        List<Quiz_Type> list = new ArrayList<>();
        String sql = "SELECT * FROM Quiz_Type";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int quiz_type_id = rs.getInt("quiz_type_id");
                String quiz_type_name = rs.getString("quiz_type_name");
                list.add(new Quiz_Type(quiz_type_id, quiz_type_name));;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
    
    public List<Lesson_Topic> getAllLessonTopicBySubjectId(int subject_id_raw) {
        List<Lesson_Topic> list = new ArrayList<>();
        String sql = "SELECT * FROM Lesson_Topic WHERE subject_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, subject_id_raw);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int lesson_topic_id = rs.getInt("lesson_topic_id");
                String lesson_topic_name = rs.getString("lesson_topic_name");
                int subject_id = rs.getInt("subject_id");
                list.add(new Lesson_Topic(lesson_topic_id, lesson_topic_name, subject_id));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
    
    public List<Dimension> getAllDimensionByType(int dimension_type_id_raw, int subject_id_raw) {
        List<Dimension> list = new ArrayList<>();
        String sql = "SELECT dimension_id, dimension_name, Dimension.dimension_type_id, subject_id\n"
                + "                FROM Dimension\n"
                + "                JOIN Dimension_Type\n"
                + "                ON Dimension_Type.dimension_type_id = Dimension.dimension_type_id\n"
                + "                WHERE Dimension_Type.dimension_type_id = ? AND subject_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, dimension_type_id_raw);
            st.setInt(2, subject_id_raw);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int dimension_id = rs.getInt("dimension_id");
                String dimension_name = rs.getString("dimension_name");
                int dimension_type_id = rs.getInt("dimension_type_id");
                int subject_id = rs.getInt("subject_id");
                list.add(new Dimension(dimension_id, dimension_name, dimension_type_id, subject_id));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }

    public static void main(String[] args) {
        QuizDAO dao = new QuizDAO();
        List<Dimension> list = dao.getAllDimensionByType(2, 1);
        for (Dimension dimension : list) {
            System.out.println(dimension);
        }
    }
    
    public int countFinishedQuiz(Account a) {
        String sql = "select COUNT(*) as countQuiz from Quiz\n"
                + "where account_id = ?";
        int count = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, a.getAccount_id());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("countQuiz");
            }
        } catch (Exception e) {
        }
        return count;
    }
}
