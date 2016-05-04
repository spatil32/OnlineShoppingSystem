<%-- 
    Document   : CustomerOrders
    Created on : Apr 30, 2016, 6:54:21 PM
    Author     : Dell
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jspf/header.jspf" %>
<h1 style="color: crimson">All Products!!</h1>

<div class="pricing-table">
    <div class="panel panel-primary" style="border: none;">
        <div class="panel-body panel-body-landing">

<form method="POST" style="text-align: center" action="<c:url value="/admin/navigationServlet"/>">
<table class="table">
        <thead>
            <tr class="info">
                <th>Customer Name</th>
                <th>Order Id</th>
                <th>Delivery Date</th>
                <th>Total Bill Amount</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.allOrders}" var="order">
                <tr class="danger">
                    <td>${order.customer.firstName}</td>
                    <td>${order.orderId}</td>
                    <td>${order.deliveryDate}</td>
                    <td>${order.totalBillAmount}</td>
                </tr>
        </c:forEach>
        <br/>
    </tbody>
</table>
<input class="btn-danger" type="submit" value="Go Back" name="goBack" id="goBack"/>
</form>
        </div></div></div>
<%@include file="/WEB-INF/jspf/footer.jspf" %> 