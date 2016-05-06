/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.fp.web;

import edu.iit.sat.itmd4515.spatil32.fp.model.Customer;
import edu.iit.sat.itmd4515.spatil32.fp.model.CustomersLogin;
import edu.iit.sat.itmd4515.spatil32.fp.service.CustomerService;
import edu.iit.sat.itmd4515.spatil32.fp.service.CustomerServiceLogin;
import edu.iit.sat.itmd4515.spatil32.fp.service.ProductService;
import static edu.iit.sat.itmd4515.spatil32.fp.web.LoginCustomer.CustomeID;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dell
 */
@WebServlet(name = "NavigateCustomer", urlPatterns = {"/customer"})
public class NavigateCustomer extends HttpServlet 
{

    private static final Logger LOG = Logger.getLogger(NavigateCustomer.class.getName());
    @EJB
    CustomerServiceLogin customerLoginService;
    
    @EJB
    ProductService productService;
    
    @EJB
    CustomerService customerService;
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
            out.println("<title>Servlet NavigateCustomer</title>");            
            out.println("</head>");
            out.println("<body>");
            if(request.isUserInRole("CUSTOMER"))
            {
                CustomersLogin cust = customerLoginService.findByUsername(request.getRemoteUser());
                String username = cust.getUser().getUserName();
                Customer loggedInCustomer = customerService.findCustomerByUsername(username);
                CustomeID = loggedInCustomer.getCustomerId();
                out.println("<h1>Welcome Employee: " + username + "</hl>");
                request.setAttribute("allProducts", productService.findAll());
                request.getRequestDispatcher("/WEB-INF/pages/Products.jsp").forward(request, response);                
            }
            out.println("<h1>Servlet NavigateCustomer at " + request.getContextPath() + "</h1>");
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
