/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.AccountDAO;
import dal.PostDAO;
import dal.QuizDAO;
import dal.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.*;

/**
 *
 * @author DELL-PC
 */
public class Profile extends HttpServlet {
   
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
            out.println("<title>Servlet Profile</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Profile at " + request.getContextPath () + "</h1>");
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
        HttpSession session = request.getSession(true);
        AccountDAO ad = new AccountDAO();
        Account ac = (Account) session.getAttribute("user");
        if (ac == null) {
            ac = ad.getAccountById(2);
        }
        ac = ad.getAccountById(ac.getAccount_id());
        session.setAttribute("user", ac);
        SubjectDAO sd = new SubjectDAO();
        PostDAO pd = new PostDAO();
        QuizDAO qd = new QuizDAO();
        int quizDone = qd.countFinishedQuiz(ac);
        int enrolledSubject = sd.countEnrolledSubject(ac);
        int numberBlogs = pd.countCreatedBlogs(ac);
        session.setAttribute("enrolled_subject", enrolledSubject);
        session.setAttribute("created_blog", numberBlogs);
        session.setAttribute("finished_quiz", quizDone);
        List<RegisteredSubject> listSubject = sd.getEnrolledSubjectRecently(ac);
        //PrintWriter out = response.getWriter();
        //out.print(listSubject);
        session.setAttribute("recently_enrolled_subject", listSubject);
        request.getRequestDispatcher("customer/profile.jsp").forward(request, response);
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
        HttpSession session = request.getSession(true);
        AccountDAO ad = new AccountDAO();
        Account ac = (Account) session.getAttribute("user");
        String fullName = request.getParameter("fullname");
        String newPass = request.getParameter("newpass");
        if (fullName != null) {
            String gender = request.getParameter("gender");
            String mobile = request.getParameter("mobile");
            String email = request.getParameter("email");
            if (ad.getAccount(email) != null && !email.equalsIgnoreCase(ac.getEmail())) {
                request.setAttribute("erru", "Email is already exist");
                request.getRequestDispatcher("customer/profile.jsp").forward(request, response);
                return;
                
            }
            request.setAttribute("updatesc", "Update success");
            Account uAcc = new Account(ac.getAccount_id(), fullName, "Male".equalsIgnoreCase(gender), email, mobile);
            ad.updateProfile(uAcc);
        } else {
            ad.updatePassword(newPass, ac);
            ac = ad.getAccount(ac.getEmail());
            request.setAttribute("cpsuccess", "Update success");
        }
        ac = ad.getAccountById(ac.getAccount_id());
        session.setAttribute("user", ac);
        request.getRequestDispatcher("customer/profile.jsp").forward(request, response);
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
