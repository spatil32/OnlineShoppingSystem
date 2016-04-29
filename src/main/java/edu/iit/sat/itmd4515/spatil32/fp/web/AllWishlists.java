/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.fp.web;

import edu.iit.sat.itmd4515.spatil32.fp.model.Basket;
import edu.iit.sat.itmd4515.spatil32.fp.model.Products;
import edu.iit.sat.itmd4515.spatil32.fp.service.BasketService;
import edu.iit.sat.itmd4515.spatil32.fp.service.Basket_ProductsService;
import edu.iit.sat.itmd4515.spatil32.fp.service.WishlistService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
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
@WebServlet(name = "AllWishlists", urlPatterns = {"/allWishlists"})
public class AllWishlists extends HttpServlet 
{

    private static final Logger LOG = Logger.getLogger(AllWishlists.class.getName());
    @EJB
    WishlistService wishlistService;
    
    @EJB
    BasketService basketService;
    
    @EJB
    Basket_ProductsService basketProductsService;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * 
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
            out.println("<title>Servlet AllWishlists</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AllWishlists at " + request.getContextPath() + "</h1>");
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
        ArrayList<Products> cartProducts = (ArrayList<Products>)request.getSession().getAttribute("selectedProducts");
        Iterator<Products> iterator = cartProducts.iterator();
        while(iterator.hasNext())
        {
            iterator.next();
            iterator.remove();
        }
        basketProductsService.removeAll();
        List<Basket> allBaskets = basketService.findAllBasketByCustomerId(LoginCustomer.CustomeID);
        LOG.info("In all wishlist size = " + allBaskets.size());
        basketService.deleteBasketByCustomerId(LoginCustomer.CustomeID);
        LOG.info("Deleted from Basket");
        request.setAttribute("wishlists", wishlistService.findWishlistByCustomerId(LoginCustomer.CustomeID));
        request.getRequestDispatcher("/WEB-INF/pages/AllWishlists.jsp").forward(request, response);
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
