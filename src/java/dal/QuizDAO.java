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
    
//    public List<Dimension> getAllDimensionByType(int dimension_type_id) {
//        List<Lesson_Topic> list = new ArrayList<>();
//        String sql = "SELECT * \n"
//                + "FROM Dimension_Type \n"
//                + "JOIN Dimension \n"
//                + "ON Dimension_Type.dimension_type_id = Dimension.dimension_type_id\n"
//                + "WHERE Dimension_Type.dimension_type_name = 'Group';";
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            st.setInt(1, subject_id_raw);
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                int lesson_topic_id = rs.getInt("lesson_topic_id");
//                String lesson_topic_name = rs.getString("lesson_topic_name");
//                int subject_id = rs.getInt("subject_id");
//                list.add(new Lesson_Topic(lesson_topic_id, lesson_topic_name, subject_id));
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }
//        return list;
//    }

    public static void main(String[] args) {
        QuizDAO dao = new QuizDAO();
        List<Lesson_Topic> list = dao.getAllLessonTopicBySubjectId(1);
        for (Lesson_Topic lesson_Topic : list) {
            System.out.println(lesson_Topic);
        }
    }
}
