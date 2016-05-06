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
import edu.iit.sat.itmd4515.spatil32.fp.service.WishlistService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
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
@WebServlet(name = "AddToCartFromWishlist", urlPatterns = {"/addToCartFromWishlist"})
public class AddToCartFromWishlist extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(AddToCartFromWishlist.class.getName());
    @EJB
    CustomerService customerService;

    @EJB
    ProductService productService;

    @EJB
    BasketService basketService;

    @EJB
    WishlistService wishlistService;

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
            out.println("<title>Servlet AddToCartFromWishlist</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddToCartFromWishlist at " + request.getContextPath() + "</h1>");
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
        Long productId = null;
        if (!WebUtil.isEmpty(request.getParameter("productId"))) {
            productId = Long.parseLong(request.getParameter("productId"));
        }

        Customer loggedInCustomer = customerService.findByCustomerId(LoginCustomer.CustomeID);
        Products cartProduct = productService.findByProductID(productId);
        cartProducts.add(cartProduct);
        LOG.log(Level.INFO, "in doget : {0}", cartProducts.size());
        Basket newBasket = new Basket(new Date(), 1, cartProduct.getPrice(), loggedInCustomer);
        newBasket.addProducts(cartProduct);
        basketService.create(newBasket);
        wishlistService.deleteWishlistProductByProductId(productId);
        request.setAttribute("basketProducts", basketService.findAllBasketByCustomerId(LoginCustomer.CustomeID));
        LOG.info("No Error in doGet");
        request.getRequestDispatcher("/WEB-INF/pages/CartProducts.jsp").forward(request, response);
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
