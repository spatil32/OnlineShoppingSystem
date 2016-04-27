<%-- 
    Document   : CartProducts
    Created on : Apr 26, 2016, 10:44:27 PM
    Author     : Dell
--%>
<%@page import="edu.iit.sat.itmd4515.spatil32.fp.model.Products"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@include file="/WEB-INF/jspf/header.jspf" %>

<h1 style="color: crimson">All Cart Products!!</h1>

<%
    int i = (Integer)session.getAttribute("index");
    out.print("Page invoked at i = " + i);
    ArrayList<Products> cartProducts = (ArrayList<Products>)session.getAttribute("selectedProducts");
    out.print("in jsp page size : " + cartProducts.size());
%>
<form method="POST" style="text-align: center" action="<c:url value="/confirmedOrder"/>">
<table class="table">
    <thead>
        <tr class="info">
            <th>Serial No</th>
            <th>Product Name</th>
            <th>Manufacturing Date</th>
            <th>Price</th>
            <th>Discount</th>
        </tr>
        </thead>
        <tbody>
             <% i=0;
              out.print("Start zalay : " + i);
             %>
            <c:forEach items="${requestScope.basketProducts}" var="product">
                <tr class="danger">
                    <td>${product.basketId}</td>
                    <td><% out.print(cartProducts.get(i).getProductName()); %></td>
                    <td><% out.print(cartProducts.get(i).getMfgDate()); %></td>
                    <td>${product.pricePerUnit}</td>
                    <td><% out.print(cartProducts.get(i).getDiscount()); %></td>
                    <td>
                        <a href="<c:url value="${requestScope.servletPath}/removeFromCart"><c:param name="basketId" value="${product.basketId}"/></c:url>">Delete From Cart</a>
                    </td>
                </tr>
                <% i++; %>
        </c:forEach>
             <% 
              out.print("End zalay : " + i);
             %>   
        <br/>            
        <br/>
    </tbody>
</table>
<input class="btn-danger" type="submit" value="Confirm Order" name="confirmOrder" id="confirmOrder"/>
</form>
<% i = 0; 
out.print("Finish zala : " + i);
%>
<%@include file="/WEB-INF/jspf/footer.jspf" %>       