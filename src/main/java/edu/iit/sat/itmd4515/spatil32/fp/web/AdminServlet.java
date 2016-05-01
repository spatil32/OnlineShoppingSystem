/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.fp.web;

import edu.iit.sat.itmd4515.spatil32.fp.model.Admin;
import edu.iit.sat.itmd4515.spatil32.fp.model.Customer;
import edu.iit.sat.itmd4515.spatil32.fp.model.CustomersLogin;
import edu.iit.sat.itmd4515.spatil32.fp.service.AdminService;
import edu.iit.sat.itmd4515.spatil32.fp.service.CustomerService;
import edu.iit.sat.itmd4515.spatil32.fp.service.CustomerServiceLogin;
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
@WebServlet(name = "AdminServlet", urlPatterns = {"/admin"})
public class AdminServlet extends HttpServlet
{

    private static final Logger LOG = Logger.getLogger(AdminServlet.class.getName());
    @EJB
    CustomerServiceLogin customerServiceLogin;
    
    @EJB
    AdminService adminService;
    
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
            out.println("<title>Servlet AdminServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            LOG.info("servlet madhe ala");
            if(request.isUserInRole("ADMIN"))
            {
                LOG.info("admin role madhe ahe");
                Admin admin = adminService.findByUsername(request.getRemoteUser());
                String username = admin.getUser().getUserName();
                out.println("<h1>Welcome Employee: " + username + "</hl>");
                Customer loggedInCustomer = customerService.findCustomerByUsername(username);
                CustomeID = loggedInCustomer.getCustomerId();
                request.getRequestDispatcher("/WEB-INF/pages/Administrator.jsp").forward(request, response);
            }
            else
            {
                LOG.info("Customer role madhe ahe");
                CustomersLogin cust = customerServiceLogin.findByUsername(request.getRemoteUser());
                String username = cust.getUser().getUserName();
                out.println("<h1>Welcome Employee: " + username + "</hl>");
            }
            out.println("<h1>Servlet AdminServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
            throws ServletException, IOException 
    {
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
