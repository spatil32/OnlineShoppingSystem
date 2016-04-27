<%-- 
    Document   : Orders
    Created on : Apr 27, 2016, 9:15:16 PM
    Author     : Dell
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/header.jspf" %>

<h1 style="color: crimson">Order Confirmation</h1>
<form method="GET" style="text-align: center" action="<c:url value="/confirmedOrder"/>">
    <label>
       <h2>Dear customer,<br/>
           Your order has been placed successfully.<br/>
           The total charges of your purchase are $ ${currentOrder.totalBillAmount}.<br/>
           Your products will be delivered till ${currentOrder.deliveryDate}. <br/>
           Thank you for shopping with us. <br/>
       </h1>
    </label>
<input class="btn-danger" type="submit" value="Logout" name="Logout" id="Logout"/>
</form>
           
           
<%@include file="/WEB-INF/jspf/footer.jspf" %> 
