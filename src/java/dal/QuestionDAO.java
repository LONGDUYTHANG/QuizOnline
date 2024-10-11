/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.List;
import model.Question;
import java.sql.*;
import model.Answer;
import model.Dimension;
import model.Lesson_Topic;
import model.Level;
import model.Question_Handle;
import model.Subject;

/**
 *
 * @author FPT SHOP
 */
public class QuestionDAO extends DBContext {

    public List<Question> getAllQuestion() {
        List<Question> list = new ArrayList<>();
        String sql = "SELECT * FROM Question";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
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

    public List<Dimension> getAllDimension() {
        List<Dimension> list = new ArrayList<>();
        String sql = "SELECT * FROM Dimension";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
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

    public List<Dimension> getAllDimensionBySubjectId(int subject_id_raw) {
        List<Dimension> list = new ArrayList<>();
        String sql = "SELECT * FROM Dimension WHERE subject_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, subject_id_raw);
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

    public void addQuestion(Question question) {
        String sql = "INSERT INTO [dbo].[Question] "
                + "([subject_id], [dimension_id], [lesson_topic_id], [level_id], [status], [question_content], [explanation], [media]) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, question.getSubject_id());
            st.setInt(2, question.getDimension_id());
            st.setInt(3, question.getLesson_topic_id());
            st.setInt(4, question.getLevel_id());
            st.setBoolean(5, question.isStatus());
            st.setString(6, question.getQuestion_content());
            st.setString(7, question.getExplanation());
            st.setString(8, question.getMedia());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void addAnswer(Answer answer) {
        String sql = "INSERT INTO [dbo].[Answer]\n"
                + "           ([answer_detail]\n"
                + "           ,[isCorrect]\n"
                + "           ,[question_id])\n"
                + "     VALUES(?, ?, ?)";

        try {
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, answer.getAnswer_detail());
            st.setBoolean(2, answer.isIsCorrect());
            st.setInt(3, answer.getQuestion_id());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public Question getLastQuestion() {
        String sql = "SELECT TOP 1 * FROM Question ORDER BY question_id DESC";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
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
                return question;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public void addMutipleAnswers(Answer[] answers) {
        for (Answer answer : answers) {
            this.addAnswer(answer);
        }
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

    public List<Question_Handle> getAllQuestionByQuizId(int quiz_id) {
        List<Question_Handle> list = new ArrayList<>();
        String sql = "SELECT \n"
                + "    qe.question_id, \n"
                + "    qe.subject_id, \n"
                + "    qe.dimension_id, \n"
                + "    qe.lesson_topic_id, \n"
                + "    qe.level_id, \n"
                + "    qe.status, \n"
                + "    qe.question_content, \n"
                + "    qe.explanation, \n"
                + "    qe.media \n"
                + "FROM \n"
                + "    Quiz qz\n"
                + "JOIN \n"
                + "    Lesson l ON qz.quiz_id = l.quiz_id\n"
                + "JOIN \n"
                + "    Lesson_Topic lt ON lt.lesson_topic_id = l.lesson_topic_id\n"
                + "JOIN \n"
                + "    Question qe ON qe.lesson_topic_id = lt.lesson_topic_id\n"
                + "WHERE \n"
                + "    qz.quiz_id = ?\n"
                + "ORDER BY \n"
                + "    qe.question_id -- Chọn cột sắp xếp phù hợp\n"
                + "OFFSET 100 ROWS \n"
                + "FETCH NEXT 20 ROWS ONLY;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, quiz_id);
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
                List<Answer> listAnswer = getAnswerOfQuestion(question_id);

                Question_Handle question = new Question_Handle(listAnswer, question_id, subject_id, dimension_id, lesson_topic_id, level_id, status, question_content, explanation, media);
                list.add(question);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }

    public List<Answer> getAnswerOfQuestion(int quest_id) {
        String sql = "select answer_id, answer_detail, isCorrect, a.question_id from Question qe\n"
                + "join Answer a on qe.question_id = a.question_id\n"
                + "where a.question_id = ?";
        List<Answer> listAnswer = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, quest_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int answer_id = rs.getInt("answer_id");
                String answer_detail = rs.getString("answer_detail");
                boolean isCorrect = rs.getBoolean("isCorrect");
                int question_id = rs.getInt("question_id");
                Answer a = new Answer(answer_id, answer_detail, isCorrect, question_id);
                listAnswer.add(a);
            }
        } catch (Exception e) {
        }
        return listAnswer;

    }

    public static void main(String[] args) {
        QuestionDAO dao = new QuestionDAO();
        System.out.println(dao.getLevelById(1));
        List<Question_Handle> la = dao.getAllQuestionByQuizId(5);
        for (Question_Handle question_Handle : la) {
            System.out.println(question_Handle.getList_answer());
        }
    }
}
