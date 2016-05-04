<%-- 
    Document   : AllWishlists
    Created on : Apr 29, 2016, 10:29:02 PM
    Author     : Dell
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/header.jspf" %>

<div class="pricing-table">
    <div class="panel panel-primary" style="border: none;">
        <div class="panel-body panel-body-landing">

<table class="table">
        <thead>
            <tr class="info">
                <th>Wishlist Number</th>
                <th>Creation Date</th>
                <th>Customer Id</th>
                <th>Product</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.wishlists}" var="wishlist">
                <tr class="danger">
                    <td>${wishlist.wishlistId}</td>
                    <td>${wishlist.creationDate}</td>
                    <td>${wishlist.customer.customerId}</td>
                    <td>${wishlist.product.productName}</td>
                    <td>
                        <a href="<c:url value="${requestScope.servletPath}/customer/addToCartFromWishlist"><c:param name="productId" value="${wishlist.product.productId}"/></c:url>">Add To Cart</a>
                    </td>
                    <td>
                        <a href="<c:url value="${requestScope.servletPath}/customer/removeFromWishList"><c:param name="productId" value="${wishlist.product.productId}"/></c:url>">Remove from Wishlist</a>
                    </td>
                </tr>
        </c:forEach>
        <br/>
        <tr><td></td><td>                <a href="<c:url value="${requestScope.servletPath}/customer/pageFlow"></c:url>">Go Back</a>
</td></tr>
    </tbody>
</table>
</div></div></div>
<%@include file="/WEB-INF/jspf/footer.jspf" %>

