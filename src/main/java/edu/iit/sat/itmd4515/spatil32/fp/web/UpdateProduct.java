/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.fp.web;

import edu.iit.sat.itmd4515.spatil32.fp.model.Products;
import edu.iit.sat.itmd4515.spatil32.fp.service.ProductService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

/**
 *
 * @author Dell
 */
@WebServlet(name = "UpdateProduct", urlPatterns = {"/UpdateProduct"})
public class UpdateProduct extends HttpServlet
{

    private static final Logger LOG = Logger.getLogger(UpdateProduct.class.getName());
    @Resource
    Validator validator;
    
    @EJB
    ProductService productService;
            
            
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
            out.println("<title>Servlet UpdateProduct</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProduct at " + request.getContextPath() + "</h1>");
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
            throws ServletException, IOException 
    {
        Integer productId = null;
        if (!WebUtil.isEmpty(request.getParameter("productId"))) 
        {
            productId = Integer.parseInt(WebUtil.trimParam(request.getParameter("productId")));
        }
        String productName = WebUtil.trimParam(request.getParameter("productName"));
        String mfgDate = WebUtil.trimParam(request.getParameter("mfgDate"));
        String[] date = mfgDate.split("-");
        int mfgDay = Integer.parseInt(date[2]);
        int mfgMonth = Integer.parseInt(date[1]);
        int mfgYear = Integer.parseInt(date[0]);
        Date mfgdate = new GregorianCalendar(mfgYear,mfgMonth ,mfgDay).getTime();
        String category = WebUtil.trimParam(request.getParameter("category"));
        Integer price = Integer.parseInt(WebUtil.trimParam(request.getParameter("price")));
        Integer discount = Integer.parseInt(WebUtil.trimParam(request.getParameter("discount")));
        Integer totalQty = Integer.parseInt(WebUtil.trimParam(request.getParameter("totalQty")));
        Integer availableQty = Integer.parseInt(WebUtil.trimParam(request.getParameter("availableQty")));
        
        Products editProduct = new Products(productId, productName, mfgdate, category.charAt(0), price, discount, totalQty, availableQty);
        Set<ConstraintViolation<Products>> violations = validator.validate(editProduct);
        
        if (violations.isEmpty()) 
        {
            productService.updateProducByProductId(editProduct);
            request.setAttribute("allProducts", productService.findAll());
            request.getRequestDispatcher("/WEB-INF/pages/AllProducts.jsp").forward(request, response);
        } else {
            LOG.log(Level.INFO, "There are {0} violations in customer update form as below : \n", violations.size());
            for (ConstraintViolation<Products> violation : violations) {
                LOG.info("#####" + violation.getRootBeanClass().getSimpleName()
                        + "." + violation.getPropertyPath() + " failed violation:\t"
                        + violation.getInvalidValue() + " failed with message " + violation.getMessage());
            }

            request.setAttribute("violations", violations);
            request.setAttribute("products", editProduct);
            request.getRequestDispatcher("WEB-INF/pages/UpdateProduct.jsp").forward(request, response);
        }
        
        
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
