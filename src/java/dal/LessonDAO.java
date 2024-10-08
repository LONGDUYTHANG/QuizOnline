/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import model.Lesson_Topic;
import model.Quiz;
import model.Quiz_Type;

/**
 *
 * @author FPT SHOP
 */
public class LessonDAO extends DBContext {
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
    public List<Quiz_Type> getAllQuizType() {
        List<Quiz_Type> list = new ArrayList<>();
        String sql = "SELECT * FROM Quiz_Type";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int quiz_type_id = rs.getInt("quiz_type_id");
                String quiz_type_name = rs.getString("quiz_type_name");
                list.add(new Quiz_Type(quiz_type_id, quiz_type_name));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
    
    public List<Quiz> getAllQuizBySubjectId(int subject_id_raw) {
        List<Quiz> list = new ArrayList<>();
        String sql = "SELECT * FROM Quiz WHERE subject_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, subject_id_raw);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int quiz_id = rs.getInt("quiz_id");
                String quiz_name = rs.getString("quiz_name");
                int subject_id = rs.getInt("subject_id");
                int level_id = rs.getInt("level_id");
                int number_of_questions = rs.getInt("number_of_questions");
                float duration = rs.getFloat("duration");
                float passrate = rs.getFloat("passrate");
                int quiz_type_id = rs.getInt("quiz_type_id");
                String quiz_description = rs.getString("quiz_description");
                Timestamp created_date = rs.getTimestamp("created_date");
                Timestamp updated_date = rs.getTimestamp("updated_date");
                int account_id = rs.getInt("account_id");

                list.add(new Quiz(quiz_id, quiz_name, subject_id, level_id, number_of_questions, Duration.ofMillis((long) (duration * 60 * 1000)), passrate, quiz_type_id, quiz_description, created_date, updated_date, account_id));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
    
    public static void main(String[] args) {
        LessonDAO dao = new LessonDAO();
        List<Quiz> list = dao.getAllQuizBySubjectId(9);
        for (Quiz quiz : list) {
            System.out.println(quiz);
        }
    }
    
    
}
