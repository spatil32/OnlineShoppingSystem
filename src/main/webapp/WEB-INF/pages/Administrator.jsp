<%-- 
    Document   : Administrator
    Created on : Apr 29, 2016, 1:14:40 PM
    Author     : Dell
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/header.jspf" %>
<h1>Administrator Page</h1>

<div class="pricing-table">
    <div class="panel panel-primary" style="border: none;">
        <div class="panel-body panel-body-landing">
            <table class="table">
<td>
<form method="GET" style="text-align: center" action="<c:url value="${requestScope.servletPath}/admin/newProduct"/>">
    <button class="btn-lg" type="submit" value="Submit">Add New Product</button>
    <label><b>This button lets admin add new product.</b></label>
</form>
</td>
<td>
<form method="POST" style="text-align: center" action="<c:url value="${requestScope.servletPath}/editProduct"/>">
    <button class="btn-lg" type="submit" value="Submit">Edit Products</button>
    <label><b>This button lets admin update or delete products.</b></label>    
</form>
</td>    
<td>
<form method="GET" style="text-align: center" action="<c:url value="${requestScope.servletPath}/admin/adminFunctionalities"/>">
    <button class="btn-lg" type="submit" value="Submit">See All Feedbacks</button>
    <label><b>This button lets admin view all customers feedback.</b></label>    
</form>
</td>
<td>
<form method="POST" style="text-align: center" action="<c:url value="${requestScope.servletPath}/admin/adminFunctionalities"/>">
    <button class="btn-lg" type="submit" value="Submit">View All Customers Orders</button>
    <label><b>This button lets admin see all customers orders.</b></label>    
</form>
</td>
            </table>
        </div>
    </div></div>

<a href="<c:url value="${requestScope.servletPath}/confirmedOrder"></c:url>">Logout</a>
    
<br/><br/>
