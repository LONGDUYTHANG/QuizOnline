/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CategoryDAO;
import dal.PackageDAO;
import dal.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.*;

/**
 *
 * @author Phuong Anh
 */
public class SubjectDetailServlet extends HttpServlet {

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
            out.println("<title>Servlet SubjectDetailServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SubjectDetailServlet at " + request.getContextPath() + "</h1>");
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
        String raw_subject_id = request.getParameter("subject_id");
        int subject_id = 0;
        try {
            subject_id = Integer.parseInt(raw_subject_id);
        } catch (NumberFormatException e) {
        }

        PackageDAO packageDAO = new PackageDAO();
        List<model.Package> packageList = packageDAO.getAllPackage();

        SubjectDAO mySubjectDAO = new SubjectDAO();
        CategoryDAO categoryDAO = new CategoryDAO();

        Subject mySubject = mySubjectDAO.getSubjectByID(subject_id);
        String categoryName = categoryDAO.getCategoryNameById(mySubject.getCategoryId());

        String selectedDuration = request.getParameter("courseDuration");
        model.Package selectedPackageModel = packageList.get(0);
        if (selectedDuration != null) {
            for (model.Package pkg : packageList) {
                if (pkg.getDuration() == Integer.parseInt(selectedDuration)) {
                    selectedPackageModel = pkg;
                    break;
                }
            }
        }

        request.setAttribute("selectedDuration", selectedDuration);
        request.setAttribute("packageList", packageList);
        request.setAttribute("selectedPackageModel", selectedPackageModel);
        request.setAttribute("mySubject", mySubject);
        request.setAttribute("categoryName", categoryName);
        request.getRequestDispatcher("common/subject_details.jsp").forward(request, response);
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
        processRequest(request, response);

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
