/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.fp.web;

import edu.iit.sat.itmd4515.spatil32.fp.model.Basket;
import edu.iit.sat.itmd4515.spatil32.fp.model.Customer;
import edu.iit.sat.itmd4515.spatil32.fp.model.Orders;
import edu.iit.sat.itmd4515.spatil32.fp.model.Products;
import edu.iit.sat.itmd4515.spatil32.fp.service.BasketService;
import edu.iit.sat.itmd4515.spatil32.fp.service.Basket_ProductsService;
import edu.iit.sat.itmd4515.spatil32.fp.service.CustomerService;
import edu.iit.sat.itmd4515.spatil32.fp.service.EmailService;
import edu.iit.sat.itmd4515.spatil32.fp.service.OrderService;
import edu.iit.sat.itmd4515.spatil32.fp.service.ProductService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dell
 */
@WebServlet(name = "ConfirmedOrder", urlPatterns = {"/confirmedOrder"})
public class ConfirmedOrder extends HttpServlet 
{
    @EJB
    OrderService orderService;
    
    @EJB
    CustomerService customerService;
    
    @EJB
    BasketService basketService;
    
    @EJB
    ProductService productService;
    
    @EJB
    Basket_ProductsService basketProductsService;
    
    @EJB
    EmailService emailService;

    
    private static final Logger LOG = Logger.getLogger(ConfirmedOrder.class.getName());

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
            out.println("<title>Servlet ConfirmedOrder</title>");            
            out.println("</head>");
            out.println("<body>");
           
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
        if(request.isUserInRole("ADMIN"))
        {
            request.logout();
            request.getRequestDispatcher("login.html").forward(request, response);
        }
        else
        {
            Customer loggedInCustomer = customerService.findByCustomerId(LoginCustomer.CustomeID);
            Orders currentOrder = (Orders)request.getSession().getAttribute("currentOrder");
            emailService.doSendMail(loggedInCustomer, currentOrder);

            ArrayList<Products> cartProducts = (ArrayList<Products>)request.getSession().getAttribute("selectedProducts");
            Iterator<Products> iterator = cartProducts.iterator();
            while(iterator.hasNext())
            {
                iterator.next();
                iterator.remove();
            }
            basketProductsService.removeAll();
            List<Basket> allBaskets = basketService.findAllBasketByCustomerId(LoginCustomer.CustomeID);
            LOG.info("In logout size = " + allBaskets.size());
            basketService.deleteBasketByCustomerId(LoginCustomer.CustomeID);
            LOG.info("Deleted from Basket");
            LoginCustomer.CustomeID = null;
            request.getSession().removeAttribute("selectedProducts");
            request.getSession().removeAttribute("currentOrder");
            request.logout();
            request.getRequestDispatcher("login.html").forward(request, response);
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
        cal.add(Calendar.DATE, 5);
        Date deliveryDate = cal.getTime();
        Customer loggedInCustomer = customerService.findByCustomerId(LoginCustomer.CustomeID);
        int totalBillAmount = 0;
        List<Basket> allBasket = basketService.findAllBasketByCustomerId(LoginCustomer.CustomeID);
        for (Basket basket : allBasket)
        {
            totalBillAmount = totalBillAmount + basket.getPricePerUnit();
        }
        Orders orders = new Orders(loggedInCustomer, totalBillAmount, deliveryDate);
        HttpSession session = request.getSession();
        session.setAttribute("currentOrder", orders);
        orderService.create(orders);
        LOG.info("Total bill amount is : " + totalBillAmount);
        request.setAttribute("currentOrder", orders);
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
    }
}
