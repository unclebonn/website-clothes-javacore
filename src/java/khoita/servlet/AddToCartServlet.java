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
import khoita.product.ProductDAO;
import khoita.product.ProductDTO;
import khoita.registration.RegistrationDTO;

/**
 *
 * @author Fstore
 */
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends HttpServlet {

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
        ProductDAO dao = new ProductDAO();
        String txtProductID = request.getParameter("txtProductID");
        String txtProductName = request.getParameter("txtProductName");
        String txtProductDescription = request.getParameter("txtProductDescription");
        float txtProductPrice = Float.parseFloat(request.getParameter("txtProductPrice"));
        int txtProductQuantity = Integer.parseInt(request.getParameter("txtProductQuantity"));
        System.out.println(txtProductID + txtProductDescription + txtProductName + txtProductPrice + txtProductQuantity); // testing if data come through this page
        String url = "invalid.html";
        try {
            HttpSession session = request.getSession(false);
            if (session.getAttribute("USER_NAME") != null) {
                ProductDTO dto = new ProductDTO(txtProductID, txtProductName, txtProductDescription, txtProductPrice, txtProductQuantity);
                RegistrationDTO account = (RegistrationDTO) session.getAttribute("USER_NAME");
                if (dao.addToCart(dto, account.getAccount())) {
                    url = "shopping.jsp";
                }
            }
        } 
        catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (NamingException ex) {
            System.out.println(ex);
        }
        finally{
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
