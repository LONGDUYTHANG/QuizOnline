/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.QuizDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Subject;

/**
 *
 * @author FPT SHOP
 */
public class AddQuizServlet extends HttpServlet {
   
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
            out.println("<title>Servlet AddQuizServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddQuizServlet at " + request.getContextPath () + "</h1>");
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
        QuizDAO dao = new QuizDAO();
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String subject_id = request.getParameter("subject_id");
        String level_id = request.getParameter("level_id");
        String duration = request.getParameter("duration");
        String passrate = request.getParameter("passrate");
        String quiztype_id = request.getParameter("quiztype_id");
        String description = request.getParameter("description");
        String totalquestion = request.getParameter("totalquestion");
        String question_type = request.getParameter("question_type");
        request.setAttribute("name", name);
        request.setAttribute("subject_id", Integer.parseInt(subject_id));
        request.setAttribute("level_id", level_id);
        request.setAttribute("duration", duration);
        request.setAttribute("passrate", passrate);
        request.setAttribute("quiztype_id", quiztype_id);
        request.setAttribute("description", description);
        request.setAttribute("totalquestion", totalquestion);
        request.setAttribute("question_type", question_type);
        request.setAttribute("listSubject", dao.getAllSubject());
        request.setAttribute("listLevel", dao.getAllLevel());
        request.setAttribute("listQuiz_Type", dao.getAllQuizType());
        //Get activeTab
        String activeTab = request.getParameter("activeTab");
        request.setAttribute("activeTab", activeTab);
        if (question_type.equals("topic")) {
            request.setAttribute("questionTopic", dao.getAllLessonTopicBySubjectId(Integer.parseInt(subject_id)));
        }
        else if (question_type.equals("group")) {
            request.setAttribute("questionGroup", dao.getAllDimensionByType(1, Integer.parseInt(subject_id)));
        }
        else {
            request.setAttribute("questionDomain", dao.getAllDimensionByType(2, Integer.parseInt(subject_id)));
        }
        request.getRequestDispatcher("expert/add_quiz.jsp").forward(request, response);
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
        QuizDAO dao = new QuizDAO();
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String subject_id = request.getParameter("subject_id");
        String level_id = request.getParameter("level_id");
        String duration = request.getParameter("duration");
        String passrate = request.getParameter("passrate");
        String quiztype_id = request.getParameter("quiztype_id");
        String description = request.getParameter("description");
        String totalquestion = request.getParameter("totalquestion");
        String question_type = request.getParameter("question_type");
        String[] group_selection = request.getParameterValues("group_selection");
        String number_of_questions[] = request.getParameterValues("number_of_questions");
        
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
