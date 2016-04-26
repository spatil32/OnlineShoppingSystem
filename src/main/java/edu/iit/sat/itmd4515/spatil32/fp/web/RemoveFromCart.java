/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.fp.web;

import edu.iit.sat.itmd4515.spatil32.fp.model.Basket;
import edu.iit.sat.itmd4515.spatil32.fp.model.Customer;
import edu.iit.sat.itmd4515.spatil32.fp.model.Orders;
import edu.iit.sat.itmd4515.spatil32.fp.service.BasketService;
import edu.iit.sat.itmd4515.spatil32.fp.service.CustomerService;
import edu.iit.sat.itmd4515.spatil32.fp.service.OrderService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
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
@WebServlet(name = "RemoveFromCart", urlPatterns = {"/removeFromCart"})
public class RemoveFromCart extends HttpServlet 
{
    @EJB
    CustomerService customerService;
    
    @EJB
    BasketService basketService;
    
    @EJB
    OrderService orderService;
    
    private static final Logger LOG = Logger.getLogger(RemoveFromCart.class.getName());
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
            out.println("<title>Servlet RemoveFromCart</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RemoveFromCart at " + request.getContextPath() + "</h1>");
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
            throws ServletException, IOException
    {
        Long basketId = null;
        if(!WebUtil.isEmpty(request.getParameter("basketId")))
        {
            basketId = Long.parseLong(request.getParameter("basketId"));
            Customer loggedInCustomer = customerService.findByCustomerId(LoginCustomer.CustomeID);
            Basket deleteBasket = basketService.findBasketByBasketId(basketId);
            LOG.info("SIZE : " + deleteBasket.getProducts().size());
            //basketService.delete(deleteBasket);
            ////TO COMPLETE//////
        }
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
        Date shoppingDate = new Date();
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(shoppingDate);
        cal.add(Calendar.DATE, 7);
        Date deliveryDate = cal.getTime();
        Customer loggedInCustomer = customerService.findByCustomerId(LoginCustomer.CustomeID);
        int totalBillAmount = 0;
        List<Basket> allBasket = basketService.findAll();
        for (Basket basket : allBasket)
        {
            totalBillAmount = totalBillAmount + basket.getPricePerUnit();
        }
        Orders orders = new Orders(loggedInCustomer, totalBillAmount, deliveryDate);
        orderService.create(orders);
        LOG.info("Total bill amount is : " + totalBillAmount);
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
