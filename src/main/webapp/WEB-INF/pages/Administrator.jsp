<%-- 
    Document   : Administrator
    Created on : Apr 29, 2016, 1:14:40 PM
    Author     : Dell
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/header.jspf" %>
<h1>Administrator Page</h1>

<form method="GET" style="text-align: center" action="<c:url value="${requestScope.servletPath}/newProduct"/>">
    <button class="btn-lg" type="submit" value="Submit">Add New Product</button>
    <label><b>This button lets admin add new product.</b></label>
</form>
<br/><br/>

<form method="POST" style="text-align: center" action="<c:url value="${requestScope.servletPath}/editProduct"/>">
    <button class="btn-lg" type="submit" value="Submit">Edit Products</button>
    <label><b>This button lets admin update or delete products.</b></label>    
</form>
<br/><br/>
