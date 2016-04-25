<%-- 
    Document   : Login
    Created on : Apr 25, 2016, 9:10:51 PM
    Author     : Dell
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/header.jspf" %>
<h1>Customer Login</h1>

<form method="POST" style="text-align: center" action="<c:url value="/loginCustomer"/>">
    <div>
        <label for="username">User Name&nbsp;&nbsp;</label>
        <input required class="form-horizontal" type="text" name="username" id="username" value="${customer.username}"/>
    </div>
    <div>
        <label for="password">Password&nbsp;&nbsp;</label>
        <input required class="form-horizontal" type="password" name="password" id="password" value="${customer.password}"/>
    </div>
    <div/>
    <input class="btn-danger" type="submit" name="submitCustomer" id="submitCustomer"/>
</form>

<%@include file="/WEB-INF/jspf/footer.jspf" %>
