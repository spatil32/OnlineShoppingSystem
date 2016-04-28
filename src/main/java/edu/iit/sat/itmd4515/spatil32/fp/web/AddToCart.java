/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.fp.web;

import edu.iit.sat.itmd4515.spatil32.fp.model.Basket;
import edu.iit.sat.itmd4515.spatil32.fp.model.Customer;
import edu.iit.sat.itmd4515.spatil32.fp.model.Products;
import edu.iit.sat.itmd4515.spatil32.fp.service.BasketService;
import edu.iit.sat.itmd4515.spatil32.fp.service.CustomerService;
import edu.iit.sat.itmd4515.spatil32.fp.service.ProductService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
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
@WebServlet(name = "AddToCart", urlPatterns = {"/addToCart"})
public class AddToCart extends HttpServlet
{
    @EJB
    ProductService productService;

    @EJB
    BasketService basketService;
    
    @EJB
    CustomerService customerService;
    
    public List<Products> cartProducts = new ArrayList<>();
    private static final Logger LOG = Logger.getLogger(AddToCart.class.getName());
    
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
            out.println("<title>Servlet AddToCart</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddToCart at " + request.getContextPath() + "</h1>");
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
        Long productId = null;
        if(!WebUtil.isEmpty(request.getParameter("productId")))
        {
            Customer loggedInCustomer = customerService.findByCustomerId(LoginCustomer.CustomeID);
            productId = Long.parseLong(request.getParameter("productId"));
            Products cartProduct = productService.findByProductID(productId);
            cartProducts.add(cartProduct);
            LOG.info("in doget : " + cartProducts.size());
            Basket newBasket = new Basket(new Date(), 1, cartProduct.getPrice(), loggedInCustomer);
            newBasket.addProducts(cartProduct);
            basketService.create(newBasket);
        }
        request.setAttribute("allProducts", productService.findAll());
        LOG.info("No Error in doGet");
        request.getRequestDispatcher("/WEB-INF/pages/Products.jsp").forward(request, response);
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
        LOG.info("came to doPost");
        LOG.info(basketService.findAll().toString());
        request.setAttribute("basketProducts", basketService.findAllBasketByCustomerId(LoginCustomer.CustomeID));
        
        HttpSession session = request.getSession();
        
        LOG.info("in dopost : " + cartProducts.size());
        session.setAttribute("selectedProducts", cartProducts);
        LOG.info("Printed in JSP");
        List<Basket> b = basketService.findAllBasketByCustomerId(LoginCustomer.CustomeID);
        int i = 0;
        for (Basket basket : b)
        {
            LOG.info(Integer.toString(basket.getBasketId()));
            
            LOG.info(cartProducts.get(i).getProductName());
            LOG.info(Integer.toString(cartProducts.get(i).getPrice()));
            i++;
        }
        
        LOG.info("#############################");

        
        LOG.info("*****************");
        request.getRequestDispatcher("/WEB-INF/pages/CartProducts.jsp").forward(request, response);
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
