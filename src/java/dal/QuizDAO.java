/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Dimension;
import model.Lesson_Topic;
import model.Level;
import model.Quiz;
import model.Quiz_Type;
import model.Subject;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import model.Question;
import model.Quiz_Question;

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
                account.setFirst_name(rs.getString("first_name"));
                account.setLast_name(rs.getString("last_name"));
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
    
    public void addQuiz(Quiz quiz) {
        String sql = "INSERT INTO [dbo].[Quiz]\n"
                + "           ([quiz_name]\n"
                + "           ,[subject_id]\n"
                + "           ,[level_id]\n"
                + "           ,[number_of_questions]\n"
                + "           ,[duration]\n"
                + "           ,[passrate]\n"
                + "           ,[quiz_type_id]\n"
                + "           ,[quiz_description]\n"
                + "           ,[created_date]\n"
                + "           ,[updated_date]\n"
                + "           ,[account_id])\n"
                + "     VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, quiz.getQuiz_name());
            st.setInt(2, quiz.getSubject_id());
            st.setInt(3, quiz.getLevel_id());
            st.setInt(4, quiz.getNumber_of_questions());
            Duration duration = quiz.getDuration();
            long minutes = duration.toMinutes();
            st.setFloat(5, (float)minutes);
            st.setDouble(6, quiz.getPassrate());
            st.setInt(7, quiz.getQuiz_type_id());
            st.setString(8, quiz.getQuiz_description());
            st.setTimestamp(9, quiz.getCreated_date());
            st.setTimestamp(10, quiz.getUpdated_date());
            st.setInt(11, quiz.getAccount_id());
            
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void addQuizQuestion(Quiz_Question quiz_question) {
        String sql = "INSERT INTO [dbo].[Quiz_Question]\n"
                + "           ([quiz_id]\n"
                + "           ,[question_id])\n"
                + "     VALUES(?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, quiz_question.getQuiz_id());
            st.setInt(2, quiz_question.getQuestion_id());
            
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public int getNumberOfQuestionBySubjectAndLessonTopic(int subject_id, int lesson_topic_id, int level_id) {
        String sql = "SELECT COUNT(question_id) AS result FROM Question WHERE subject_id = ? AND lesson_topic_id = ? AND level_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, subject_id);
            st.setInt(2, lesson_topic_id);
            st.setInt(3, level_id);
            ResultSet rs = st.executeQuery();
            int result = 0;
            if (rs.next()) {
                result = rs.getInt("result");
            }
            return result;
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }
    
    public int getNumberOfQuestionBySubjectAndDimensionId(int subject_id, int dimension_id, int level_id) {
        String sql = "SELECT COUNT(question_id) AS result FROM Question WHERE subject_id = ? AND dimension_id = ? AND level_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, subject_id);
            st.setInt(2, dimension_id);
            st.setInt(3, level_id);
            ResultSet rs = st.executeQuery();
            int result = 0;
            if (rs.next()) {
                result = rs.getInt("result");
            }
            return result;
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }
    
    public Lesson_Topic getLessonTopicById(int lesson_topic_id_raw) {
        String sql = "SELECT * FROM Lesson_Topic WHERE lesson_topic_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, lesson_topic_id_raw);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int lesson_topic_id = rs.getInt("lesson_topic_id");
                String lesson_topic_name = rs.getString("lesson_topic_name");
                int subject_id = rs.getInt("subject_id");
                return new Lesson_Topic(lesson_topic_id, lesson_topic_name, subject_id);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    public Dimension getDimensionById(int dimension_id_raw) {
        String sql = "SELECT * FROM Dimension WHERE dimension_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, dimension_id_raw);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int dimension_id = rs.getInt("dimension_id");
                String dimension_name = rs.getString("dimension_name");
                int dimension_type_id = rs.getInt("dimension_type_id");
                int subject_id = rs.getInt("subject_id");
                return new Dimension(dimension_id, dimension_name, dimension_type_id, subject_id);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    
    public List<Question> getAllSelectQuestionByTopic(int subject_id_raw, int lesson_topic_id_raw, int number_of_questions_raw, int level_id_raw) {
        List<Question> list = new ArrayList<>();
        String sql = "SELECT TOP " + number_of_questions_raw + " * from Question where subject_id = ? and lesson_topic_id = ? and level_id = ?\n"
            + "ORDER BY NEWID()";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, subject_id_raw);
            st.setInt(2, lesson_topic_id_raw);
            st.setInt(3, level_id_raw);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int question_id = rs.getInt("question_id");
                int subject_id = rs.getInt("subject_id");
                int dimension_id = rs.getInt("dimension_id");
                int lesson_topic_id = rs.getInt("lesson_topic_id");
                int level_id = rs.getInt("level_id");
                boolean status = rs.getBoolean("status");
                String question_content = rs.getString("question_content");
                String explanation = rs.getString("explanation");
                String media = rs.getString("media");

                Question question = new Question(question_id, subject_id, dimension_id, lesson_topic_id, level_id, status, question_content, explanation, media);
                list.add(question);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
    
    public List<Question> getAllSelectQuestionByDimension(int subject_id_raw, int dimension_id_raw, int number_of_questions_raw, int level_id_raw) {
        List<Question> list = new ArrayList<>();
        String sql = "SELECT TOP " + number_of_questions_raw + " * from Question where subject_id = ? and dimension_id = ? AND level_id = ?\n"
            + "ORDER BY NEWID()";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, subject_id_raw);
            st.setInt(2, dimension_id_raw);
            st.setInt(3, level_id_raw);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int question_id = rs.getInt("question_id");
                int subject_id = rs.getInt("subject_id");
                int dimension_id = rs.getInt("dimension_id");
                int lesson_topic_id = rs.getInt("lesson_topic_id");
                int level_id = rs.getInt("level_id");
                boolean status = rs.getBoolean("status");
                String question_content = rs.getString("question_content");
                String explanation = rs.getString("explanation");
                String media = rs.getString("media");

                Question question = new Question(question_id, subject_id, dimension_id, lesson_topic_id, level_id, status, question_content, explanation, media);
                list.add(question);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
    
    public List<Quiz> getAllQuiz() {
        List<Quiz> list = new ArrayList<>();
        String sql = "SELECT * FROM Quiz";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
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
    
    public Quiz getQuiz(int quizId) {
        String sql = "SELECT * FROM Quiz where quiz_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, quizId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
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

                return new Quiz(quiz_id, quiz_name, subject_id, level_id, number_of_questions, Duration.ofMillis((long) (duration * 60 * 1000)), passrate, quiz_type_id, quiz_description, created_date, updated_date, account_id);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    
    public Quiz getNewlyAddedQuiz() {
        String sql = "SELECT top 1 * FROM Quiz ORDER BY quiz_id DESC";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
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
                
                return new Quiz(quiz_id, quiz_name, subject_id, level_id, number_of_questions, Duration.ZERO, passrate, quiz_type_id, quiz_description, created_date, updated_date, account_id);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    
    public static void main(String[] args) {
        QuizDAO dao = new QuizDAO();
        List<Quiz> quiz = dao.getAllQuiz();
        //Quiz quiz = dao.getNewlyAddedQuiz();
        System.out.println(quiz);
    }
}
