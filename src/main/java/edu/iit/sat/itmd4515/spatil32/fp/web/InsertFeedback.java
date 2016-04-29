/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.fp.web;

import edu.iit.sat.itmd4515.spatil32.fp.model.Customer;
import edu.iit.sat.itmd4515.spatil32.fp.model.Feedback;
import edu.iit.sat.itmd4515.spatil32.fp.model.Orders;
import edu.iit.sat.itmd4515.spatil32.fp.service.CustomerService;
import edu.iit.sat.itmd4515.spatil32.fp.service.FeedbackService;
import edu.iit.sat.itmd4515.spatil32.fp.service.OrderService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
@WebServlet(name = "InsertFeedback", urlPatterns = {"/insertFeedback"})
public class InsertFeedback extends HttpServlet
{
    @EJB
    FeedbackService feedbackService;
    
    @EJB
    CustomerService customerService;
    
    @EJB
    OrderService orderService;
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
            out.println("<title>Servlet InsertFeedback</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InsertFeedback at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("/WEB-INF/pages/Feedback.jsp").forward(request, response);
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
            throws ServletException, IOException 
    {
        Integer rating = null;
        if(!WebUtil.isEmpty(request.getParameter("rating")))
            rating = Integer.parseInt(WebUtil.trimParam(request.getParameter("rating")));
        String comment = WebUtil.trimParam(request.getParameter("comment"));
        Customer loggedInCustomer = customerService.findByCustomerId(LoginCustomer.CustomeID);
        Feedback newFeedback = new Feedback(loggedInCustomer, new Date(), comment, rating);
        feedbackService.create(newFeedback);
        Orders currentOrderDetails = (Orders)request.getSession().getAttribute("currentOrder");
        request.setAttribute("currentOrder", currentOrderDetails);
        request.getRequestDispatcher("/WEB-INF/pages/Orders.jsp").forward(request, response);
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
