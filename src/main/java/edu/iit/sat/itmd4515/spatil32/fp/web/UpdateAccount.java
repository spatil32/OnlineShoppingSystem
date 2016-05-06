/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.fp.web;

import edu.iit.sat.itmd4515.spatil32.fp.model.Customer;
import edu.iit.sat.itmd4515.spatil32.fp.model.Orders;
import edu.iit.sat.itmd4515.spatil32.fp.service.CustomerService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;
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
@WebServlet(name = "UpdateAccount", urlPatterns = {"/updateAccount"})
public class UpdateAccount extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(UpdateAccount.class.getName());
    @EJB
    CustomerService customerService;

    @Resource
    Validator validator;

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
            out.println("<title>Servlet UpdateAccount</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateAccount at " + request.getContextPath() + "</h1>");
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
        request.setAttribute("loggedInCustomer", customerService.findByCustomerId(LoginCustomer.CustomeID));
        request.getRequestDispatcher("/WEB-INF/pages/UpdateAccount.jsp").forward(request, response);
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
        Integer customerId = Integer.parseInt(WebUtil.trimParam(request.getParameter("customerId")));
        String firstName = WebUtil.trimParam(request.getParameter("firstName"));
        String lastName = WebUtil.trimParam(request.getParameter("lastName"));
        Integer age = Integer.parseInt(WebUtil.trimParam(request.getParameter("age")));
        String gender = WebUtil.trimParam(request.getParameter("gender"));
        String address = WebUtil.trimParam(request.getParameter("address"));
        String email = WebUtil.trimParam(request.getParameter("email"));
        String birthDate = WebUtil.trimParam(request.getParameter("birthDate"));
        String[] birth = birthDate.split("-");
        int birthDay = Integer.parseInt(birth[2]);
        int birthMonth = Integer.parseInt(birth[1]);
        int birthYear = Integer.parseInt(birth[0]);
        Date birthdate = new GregorianCalendar(birthYear, birthMonth, birthDay).getTime();
        String phoneNo = WebUtil.trimParam(request.getParameter("phoneNo"));
        Customer loggedInCustomer = customerService.findByCustomerId(LoginCustomer.CustomeID);
        Customer updateCustomer = new Customer(customerId, firstName, lastName, age, gender.charAt(0), address, email, birthdate, phoneNo, loggedInCustomer.getUsername(), loggedInCustomer.getPassword());
        Set<ConstraintViolation<Customer>> violations = validator.validate(updateCustomer);
        if (violations.isEmpty()) {
            Orders orders = (Orders) request.getSession().getAttribute("currentOrder");
            customerService.UpdateCustomer(updateCustomer);
            request.setAttribute("currentOrder", orders);
            request.getRequestDispatcher("/WEB-INF/pages/Orders.jsp").forward(request, response);
        } else {
            LOG.info("There are " + violations.size() + " violations in update form as below : \n");
            for (ConstraintViolation<Customer> violation : violations) {
                LOG.info("#####" + violation.getRootBeanClass().getSimpleName()
                        + "." + violation.getPropertyPath() + " failed violation:\t"
                        + violation.getInvalidValue() + " failed with message " + violation.getMessage());
            }
            request.setAttribute("violations", violations);
            request.setAttribute("loggedInCustomer", updateCustomer);
            request.getRequestDispatcher("WEB-INF/pages/UpdateAccount.jsp").forward(request, response);
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
    }

}
