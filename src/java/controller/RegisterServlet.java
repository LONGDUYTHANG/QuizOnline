/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class RegisterServlet extends HttpServlet {
   
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
            out.println("<title>Servlet RegisterServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterServlet at " + request.getContextPath () + "</h1>");
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
        processRequest(request, response);
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
        AccountDAO myAccountDAO = new AccountDAO();
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String confirmed_pass = request.getParameter("confirmedPass");
        //thong bao loi gui lai nguoi dung
        String register_error = "";
        //Neu mat khau xac nhan giong mat khau da nhap
        if (confirmed_pass.equals(pass)) {
            //neu email do to tai trong database
            if (myAccountDAO.getAccountByEmail(email) == null) {
                myAccountDAO.addAccount( email, pass);
                request.getRequestDispatcher("homepage_1.jsp").forward(request, response);
            } else {
                //gui lai cac thong tin nguoi dung da nhap sang page register de nguoi dung nhap tiep
                register_error = "Email existed!";
                request.setAttribute("email_error", register_error);
                request.setAttribute("email", email);
                request.setAttribute("pass", pass);
                request.getRequestDispatcher("homepage.jsp").forward(request, response);
            }
        } else {
            //gui lai cac thong tin nguoi dung da nhap sang page register de nguoi dung nhap tiep
            register_error = "Wrong confirmed password!";
            request.setAttribute("pass_error", register_error);
            request.setAttribute("email", email);
            request.getRequestDispatcher("homepage.jsp").forward(request, response);
        }
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
