/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoita.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khoita.registration.RegistrationDAO;
import khoita.registration.RegistrationDTO;

/**
 *
 * @author Fstore
 */
@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
public class UpdateServlet extends HttpServlet {

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
        String url = "updatedfail.jsp";
        String txtAccount = request.getParameter("txtAccount");
        String txtPassword = request.getParameter("txtPassword");
        String txtFullname = request.getParameter("txtFullname");
        String lastSearchValue = request.getParameter("txtSearchValue");
        String lastAccount = request.getParameter("lastAccount");
        String isRole = request.getParameter("isRole");
        boolean flag = false;
        if (isRole != null) {
            flag = true;
        }

        try {
            RegistrationDAO dao = new RegistrationDAO();

            if (dao.updateUser(txtAccount, txtPassword, txtFullname, lastAccount, flag)) {
                url = "DispatchController"
                        + "?txtSearch=" + lastSearchValue
                        + "&btAction=search";
            }
        } catch (SQLException e) {
            String msg = e.getMessage();
            if (msg.contains("duplicate")) {
                request.setAttribute("DUPLICATE_ACCOUNT", "This account name already existed");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (NamingException ex) {
            System.out.println(ex);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
        processRequest(request, response);
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
