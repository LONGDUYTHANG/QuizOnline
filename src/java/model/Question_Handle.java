/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author DELL-PC
 */
public class Question_Handle extends Question{
    private List<Answer> list_answer;
    

    public Question_Handle(List<Answer> list_answer) {
        this.list_answer = list_answer;
    }

    public Question_Handle(List<Answer> list_answer, int question_id, int subject_id, int dimension_id, int lesson_topic_id, int level_id, boolean status, String question_content, String explanation, String media) {
        super(question_id, subject_id, dimension_id, lesson_topic_id, level_id, status, question_content, explanation, media);
        this.list_answer = list_answer;
    }

    public Question_Handle(List<Answer> list_answer, int subject_id, int dimension_id, int lesson_topic_id, int level_id, boolean status, String question_content, String explanation, String media) {
        super(subject_id, dimension_id, lesson_topic_id, level_id, status, question_content, explanation, media);
        this.list_answer = list_answer;
    }

    public List<Answer> getList_answer() {
        return list_answer;
    }

    public void setList_answer(List<Answer> list_answer) {
        this.list_answer = list_answer;
    }

    @Override
    public String toString() {
        return "Question_Handle{" + "list_answer=" + list_answer + '}';
    }
    
    
    
}
