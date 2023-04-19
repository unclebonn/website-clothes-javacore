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
import khoita.registration.RegistrationDAO;
import khoita.registration.RegistrationDTO;
import khoita.registration.RegistrationErrors;

/**
 *
 * @author Fstore
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

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
        String url = "register.jsp";
        String account = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmpassword = request.getParameter("confirmPpassword");
        String fullname = request.getParameter("fullname");
        RegistrationErrors errors = new RegistrationErrors();
        boolean role = false;
        boolean foundErrors = false;

        if (account.trim().isEmpty() || account.trim().length() > 25 || account.trim().length() < 6) {
            foundErrors = true;
            errors.setUsernameLengthError("The account is empty or it is over 25 characters or it less than 6 characters");
           
        }
        if (password.trim().isEmpty() || password.trim().length() > 25 || password.trim().length() < 6) {
            foundErrors = true;
            errors.setPasswordLengthError("The password is empty or it is over 25 characters or it less than 6 characters");
          
        }else if (!password.trim().equals(confirmpassword.trim()) ){
            foundErrors = true;
            errors.setConfirmPassword("The password is not match!");
        }
        if (fullname.trim().isEmpty() || fullname.trim().length() > 25 || fullname.trim().length() < 6) {
            foundErrors = true;
            errors.setFullnameLengthError("The fullname is empty or it is over 25 characters or it less than 6 characters");
           
        }

        try {
            if (foundErrors) {
                request.setAttribute("RegisterErros", errors);
            } else {
                RegistrationDTO user = new RegistrationDTO(account, password, fullname, role);
                RegistrationDAO rd = new RegistrationDAO();
                if (rd.register(user)) {
                    url = "index.html";
                }
            }

        } catch (SQLException e) {
            String errorMsg = e.getMessage();
            if (errorMsg.contains("duplicate")) {
                errors.setUsernameExisted("The username is existed");
                request.setAttribute("RegisterErros", errors);

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
