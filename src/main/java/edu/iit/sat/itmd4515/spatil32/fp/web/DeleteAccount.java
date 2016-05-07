/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.fp.web;

import edu.iit.sat.itmd4515.spatil32.fp.model.Products;
import edu.iit.sat.itmd4515.spatil32.fp.service.BasketService;
import edu.iit.sat.itmd4515.spatil32.fp.service.Basket_ProductsService;
import edu.iit.sat.itmd4515.spatil32.fp.service.CustomerService;
import edu.iit.sat.itmd4515.spatil32.fp.service.FeedbackService;
import edu.iit.sat.itmd4515.spatil32.fp.service.OrderService;
import edu.iit.sat.itmd4515.spatil32.fp.service.WishlistService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
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
@WebServlet(name = "DeleteAccount", urlPatterns = {"/deleteAccount"})
public class DeleteAccount extends HttpServlet {

    @EJB
    CustomerService customerSerivce;

    @EJB
    BasketService basketService;

    @EJB
    Basket_ProductsService basketProductsService;

    @EJB
    OrderService orderService;

    @EJB
    WishlistService wishlistService;

    @EJB
    FeedbackService feedbackService;

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
            out.println("<title>Servlet DeleteAccount</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteAccount at " + request.getContextPath() + "</h1>");
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
            throws ServletException, IOException {
        ArrayList<Products> cartProducts = (ArrayList<Products>) request.getSession().getAttribute("selectedProducts");
        Iterator<Products> iterator = cartProducts.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        basketProductsService.removeAll();
        basketService.deleteBasketByCustomerId(LoginCustomer.CustomeID);
        orderService.deleteOrdersByCustomerId(LoginCustomer.CustomeID);
        wishlistService.deleteWishlistByCustomerId(LoginCustomer.CustomeID);
        feedbackService.deleteFeedbackByCustomerId(LoginCustomer.CustomeID);
        request.getSession().removeAttribute("selectedProducts");
        request.getSession().removeAttribute("currentOrder");
        request.logout();
        customerSerivce.deleteCustomerById(LoginCustomer.CustomeID);
        LoginCustomer.CustomeID = null;
        request.getRequestDispatcher("login.html").forward(request, response);
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
    }
}
