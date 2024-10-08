/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.LessonDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Lesson_Topic;
import model.Quiz;
import model.Quiz_Type;

/**
 *
 * @author FPT SHOP
 */
public class AddLessonServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddLessonServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddLessonServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String subjectId = request.getParameter("subjectId");
        LessonDAO dao = new LessonDAO();
        List<Lesson_Topic> listLesson_Topic = dao.getAllLessonTopicBySubjectId(Integer.parseInt(subjectId));
        List<Quiz_Type> listQuiz_Type = dao.getAllQuizType();
        List<Quiz> listQuiz = dao.getAllQuizBySubjectId(Integer.parseInt(subjectId));
        request.setAttribute("listLesson_Topic", listLesson_Topic);
        request.setAttribute("listQuiz_Type", listQuiz_Type);
        request.setAttribute("listQuiz", listQuiz);
        request.getRequestDispatcher("expert/add_lesson.jsp").forward(request, response);
        
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String type = request.getParameter("type");
        if (type.equals("lesson")) {
            String name = request.getParameter("name");
            String summary = request.getParameter("summary");
            String topic = request.getParameter("topic");
            String order = request.getParameter("order");
            String status = request.getParameter("status");
            
            out.println(type);
            out.println(name);
            out.println(summary);
            out.println(topic);
            out.println(order);
            out.println(status);
            out.println();
        }
        else if (type.equals("video")) {
            String name = request.getParameter("name");
            String topic = request.getParameter("topic");
            String summary = request.getParameter("summary");
            String order = request.getParameter("order");
            String status = request.getParameter("status");
            String url = request.getParameter("url");
            String quill = request.getParameter("quillContent");
            out.println(name);
            out.println(topic);
            out.println(summary);
            out.println(order);
            out.println(status);
            out.println(url);
            out.println(quill);
        }
        else {
            String name = request.getParameter("name");
            String topic = request.getParameter("topic");
            String quiz_type = request.getParameter("quiz_type");
            String summary = request.getParameter("summary");
            String order = request.getParameter("order");
            String status = request.getParameter("status");
            String quiz = request.getParameter("quiz");
            out.println(name);
            out.println(topic);
            out.println(quiz_type);
            out.println(summary);
            out.println(order);
            out.println(status);
            out.println(quiz);
        }
    //request.getRequestDispatcher("expert/add_lesson.jsp").forward(request, response);

    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
