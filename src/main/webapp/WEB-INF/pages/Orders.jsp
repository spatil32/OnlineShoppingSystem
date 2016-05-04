<%-- 
    Document   : Orders
    Created on : Apr 27, 2016, 9:15:16 PM
    Author     : Dell
--%>
<%@page import="edu.iit.sat.itmd4515.spatil32.fp.model.Orders"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/header.jspf" %>

<div class="container">
    <h3>Your order is placed.</h3>
  <div class="progress">
    <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:100%">
      100%
    </div>
  </div>
</div>

<h1 style="color: crimson">Order Confirmation</h1>
<div class="pricing-table">
    <div class="panel panel-primary" style="border: none;">
        <div class="panel-body panel-body-landing">
            <table class="table">
                <td>
<form method="GET" style="text-align: center" action="<c:url value="${requestScope.servletPath}/customer/insertFeedback"/>">
        <button class="btn-lg" type="submit" value="Submit">Provide Feedback</button>
</form>           
        </td>
        <td>
<form method="GET" style="text-align: center" action="<c:url value="${requestScope.servletPath}/customer/allWishlists"/>">
        <button class="btn-lg" type="submit" value="Submit">Go To Wishlists</button>
</form>     
        </td>
            </table>
<form method="GET" style="text-align: center" action="<c:url value="/confirmedOrder"/>">
    <%
        Orders currentOrder = (Orders)session.getAttribute("currentOrder");
        if(currentOrder.getTotalBillAmount() == 0 || String.valueOf(currentOrder.getTotalBillAmount()).equals(""))
        {
            out.println("<h3>Dear customer,</h3><br/>");
            out.println("<h3>Thank you for visiting our shopping portal.</h3><br/>");
            out.println("<h3>We hope to see you again.</h3><br/>");
        }
        else
        {
            out.println("<h3>Dear customer,</h3><br/>");
            out.println("<h3>Your order has been placed successfully.</h3><br/>");
            out.println("<h3>The total charges of your purchase are $" + currentOrder.getTotalBillAmount() + "</h3><br/>");
            out.println("<h3>Your products will be delivered till " + currentOrder.getDeliveryDate()+ "</h3><br/>");
            out.println("<h3>Thank you for shopping with us.</h3>");
        }
    %>
 <br/><br/>
    <input class="btn-danger" type="submit" value="Logout" name="Logout" id="Logout"/>
</form>
 
    </div></div></div>
 <table class="table">
     <td>
         
<form method="GET" style="text-align: center" action="<c:url value="${requestScope.servletPath}/customer/updateAccount"/>">
    <button class="btn-lg" type="submit" value="Edit Account">Edit Account</button>
</form>
     </td>
</table>
<%@include file="/WEB-INF/jspf/footer.jspf" %> 
