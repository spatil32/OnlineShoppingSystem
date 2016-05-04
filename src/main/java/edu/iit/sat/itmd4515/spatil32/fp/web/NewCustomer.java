/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.fp.web;

import edu.iit.sat.itmd4515.spatil32.fp.model.Customer;
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
@WebServlet(name = "NewCustomer", urlPatterns = {"/customer/newCustomer"})
public class NewCustomer extends HttpServlet
{

    private static final Logger LOG = Logger.getLogger(NewCustomer.class.getName());
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
            out.println("<title>Servlet NewCustomer</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewCustomer at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("/WEB-INF/pages/Registration.jsp").forward(request, response);
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
        Date birthdate = new GregorianCalendar(birthYear,birthMonth ,birthDay).getTime();
        String phoneNo = WebUtil.trimParam(request.getParameter("phoneNo"));
        String username = WebUtil.trimParam(request.getParameter("username"));
        String password = WebUtil.trimParam(request.getParameter("password"));
        
        Customer newCustomer = new Customer(firstName, lastName, age, gender.charAt(0), address, email, birthdate, phoneNo, username, password);
        Set<ConstraintViolation<Customer>> violations = validator.validate(newCustomer);
        
        if(violations.isEmpty())
        {
            customerService.create(newCustomer);
            request.getRequestDispatcher("/WEB-INF/pages/Login.jsp").forward(request, response);
        }
        else
        {
            LOG.info("There are " + violations.size() + " violations in registration form as below : \n");
            for (ConstraintViolation<Customer> violation : violations) 
            {
                LOG.info("#####" + violation.getRootBeanClass().getSimpleName()
                        + "." + violation.getPropertyPath() + " failed violation:\t"
                        + violation.getInvalidValue() + " failed with message " + violation.getMessage());
            }
            request.setAttribute("violations", violations);
            request.setAttribute("customer", newCustomer);
            request.getRequestDispatcher("WEB-INF/pages/Registration.jsp").forward(request, response);
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
