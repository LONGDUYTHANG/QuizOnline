/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import dal.PackageDAO;
import dal.RegistrationDAO;
import dal.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Registration;

/**
 *
 * @author ADMIN
 */
public class FilterRegistrationServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
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
            out.println("<title>Servlet FilterRegistrationServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FilterRegistrationServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RegistrationDAO myRegistrationDAO = new RegistrationDAO();
        AccountDAO myAccountDAO =new AccountDAO();
        PackageDAO myPackageDAO=new PackageDAO();
        SubjectDAO mySubjectDAO =new SubjectDAO();
        //Create registration list for filtering by subject
        String subject_name = request.getParameter("subject_name");
        ArrayList<Registration> registration_list_by_subject = new ArrayList<>();
        //Create registration list for filtering by registration date
        String registration_date = request.getParameter("date");
        ArrayList<Registration> registration_list_by_date = new ArrayList<>();
        //Create registration list for filtering by status
        String status = request.getParameter("status");
        ArrayList<Registration> registration_list_by_status = new ArrayList<>();
        //Check registration list for filtering by subject
        if (subject_name == null) {
            registration_list_by_subject = myRegistrationDAO.getRegistrationList();
        } else {
            registration_list_by_subject = myRegistrationDAO.filterRegistrationListBySubject(subject_name);
        }
        //Check registration list for filtering by registration date
        if (registration_date == null) {
            registration_list_by_date = myRegistrationDAO.getRegistrationList();
        } else {
            registration_list_by_date = myRegistrationDAO.filterRegistrationListByDate(registration_date);
        }
        //Check registration list for filtering by status
        if (status == null) {
            registration_list_by_status = myRegistrationDAO.getRegistrationList();
        } else {
            registration_list_by_status = myRegistrationDAO.filterRegistrationListByStatus(status);
        }
        //List combines all three filters
        ArrayList<Registration> common_registration=new ArrayList<>(registration_list_by_subject);
        common_registration.retainAll(registration_list_by_date);
        common_registration.retainAll(registration_list_by_status);
        PrintWriter out =response.getWriter();
        out.print(registration_date);
        request.setAttribute("accountDAO", myAccountDAO);
        request.setAttribute("subjectDAO", mySubjectDAO);
        request.setAttribute("packageDAO", myPackageDAO);
        request.setAttribute("registrationDAO", myRegistrationDAO);
        request.setAttribute("registration_list", registration_list_by_date);
        request.setAttribute("subject_name", subject_name);
        request.setAttribute("registration_date", registration_date);
        request.setAttribute("status", status);
        request.getRequestDispatcher("saler/registration_list.jsp").forward(request, response);
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
