/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.QuestionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Dimension;
import model.Lesson_Topic;
import model.Level;
import model.Subject;

/**
 *
 * @author FPT SHOP
 */
public class Question_Detail_Validation_Controller extends HttpServlet {
   
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
            out.println("<title>Servlet QuestionDetailController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet QuestionDetailController at " + request.getContextPath () + "</h1>");
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
        QuestionDAO dao = new QuestionDAO();
        List<Subject> list = dao.getAllSubject();
        request.setAttribute("listSubject", list);
        request.getRequestDispatcher("question_detail.jsp").forward(request, response);
        
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
        QuestionDAO dao = new QuestionDAO();
        String subject_id = request.getParameter("subject_id");
        Subject s = dao.getSubjectById((Integer.parseInt(subject_id)));
        List<Subject> list = dao.getAllSubject();
        List<Dimension> listDimension = dao.getAllDimensionBySubjectId(Integer.parseInt(subject_id));
        List<Lesson_Topic> listLesson_Topic = dao.getAllLessonTopicBySubjectId(Integer.parseInt(subject_id));
        List<Level> listLevel = dao.getAllLevel();
        request.setAttribute("subject", s);
        request.setAttribute("listSubject", list);
        request.setAttribute("listDimension", listDimension);
        request.setAttribute("listLevel", listLevel);
        request.setAttribute("listLesson_Topic", listLesson_Topic);
        request.getRequestDispatcher("question_detail.jsp").forward(request, response);
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
