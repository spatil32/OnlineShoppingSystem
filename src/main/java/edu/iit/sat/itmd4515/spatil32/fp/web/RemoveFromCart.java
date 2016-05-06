/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.fp.web;

import edu.iit.sat.itmd4515.spatil32.fp.model.BasketProducts;
import edu.iit.sat.itmd4515.spatil32.fp.model.Customer;
import edu.iit.sat.itmd4515.spatil32.fp.model.Products;
import edu.iit.sat.itmd4515.spatil32.fp.service.BasketService;
import edu.iit.sat.itmd4515.spatil32.fp.service.Basket_ProductsService;
import edu.iit.sat.itmd4515.spatil32.fp.service.CustomerService;
import edu.iit.sat.itmd4515.spatil32.fp.service.OrderService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author patils03
 */
@WebServlet(name = "RemoveFromCart", urlPatterns = {"/removeFromCart"})
public class RemoveFromCart extends HttpServlet {

    @EJB
    CustomerService customerService;

    @EJB
    BasketService basketService;

    @EJB
    OrderService orderService;

    @EJB
    Basket_ProductsService basketProductSrvice;

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
        Long basketId = null;
        if (!WebUtil.isEmpty(request.getParameter("basketId"))) {
            basketId = Long.parseLong(request.getParameter("basketId"));
            Customer loggedInCustomer = customerService.findByCustomerId(LoginCustomer.CustomeID);
            //find productid from basketproduct
            BasketProducts basketProduct = basketProductSrvice.findBasketProductByBasketId(basketId);
            //delete from basketproduct
            basketProductSrvice.deleteByBasketId(basketId);
            //delete from basket
            basketService.deleteBasketByBasketId(basketId);
            //delete from arraylist
            ArrayList<Products> cartProducts = (ArrayList<Products>) request.getSession().getAttribute("selectedProducts");
            LOG.info("Atta size ahe  = " + cartProducts.size());
            int productIdOfBasketProduct = basketProduct.getProductId();
            LOG.info("Delete karaycha id ahe = " + Integer.toString(productIdOfBasketProduct));
            for (Products cartProduct : cartProducts) {
                LOG.info("Product id he ahet..");
                LOG.info("***************");
                LOG.info(cartProduct.toString());
            }
            Iterator<Products> iterator = cartProducts.iterator();
            while (iterator.hasNext()) {
                Products toDelete = iterator.next();
                if (toDelete.getProductId() == productIdOfBasketProduct) {
                    iterator.remove();
                    break;
                }
            }
            request.setAttribute("basketProducts", basketService.findAllBasketByCustomerId(LoginCustomer.CustomeID));
            request.getRequestDispatcher("/WEB-INF/pages/CartProducts.jsp").forward(request, response);
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
            throws ServletException, IOException {
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
