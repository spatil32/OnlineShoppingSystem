<%--
    Document   : CartProducts
    Created on : Apr 26, 2016, 10:44:27 PM
    Author     : Dell
--%>
<%@page import="java.util.Iterator"%>
<%@page import="edu.iit.sat.itmd4515.spatil32.fp.model.Products"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@include file="/WEB-INF/jspf/header.jspf" %>

<div class="container">
    <h3>You are just 1 click away for shopping.</h3>
    <div class="progress">
        <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width:70%">
            70%
        </div>
    </div>
</div>

<h1 style="color: crimson">All Cart Products!!</h1>

<%
    int i = 0;
    ArrayList<Products> cartProducts = (ArrayList<Products>) session.getAttribute("selectedProducts");
%>

<div class="pricing-table">
    <div class="panel panel-primary" style="border: none;">
        <div class="panel-body panel-body-landing">

            <form method="POST" style="text-align: center" action="<c:url value="/confirmedOrder"/>">
                <div class="pricing-table">
                    <div class="panel panel-primary" style="border: none;">
                        <table class="table">
                            <thead>
                                <tr class="info">
                                    <th>Serial No</th>
                                    <th>Product Name</th>
                                    <th>Manufacturing Date</th>
                                    <th>Price</th>
                                    <th>Discount</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <% i = 0;%>
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
                            <br/>            
                            <br/>
                            </tbody>
                        </table>
                    </div></div>
                <input class="btn-danger" type="submit" value="Confirm Order" name="confirmOrder" id="confirmOrder"/>
            </form>
        </div></div></div>
<%  i = 0;%>
<%@include file="/WEB-INF/jspf/footer.jspf" %>       