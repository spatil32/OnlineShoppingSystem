<%-- 
    Document   : AllWishlists
    Created on : Apr 29, 2016, 10:29:02 PM
    Author     : Dell
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/header.jspf" %>

<a href="<c:url value="${requestScope.servletPath}/pageFlow"></c:url>">Go Back</a>
<table class="table">
        <thead>
            <tr class="info">
                <th>Wishlist Number</th>
                <th>Creation Date</th>
                <th>Customer Id</th>
                <th>Product</th>
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
                        <a href="<c:url value="${requestScope.servletPath}/addToCartFromWishlist"><c:param name="productId" value="${wishlist.product.productId}"/></c:url>">Add To Cart</a>
                    </td>
                    <td>
                        <a href="<c:url value="${requestScope.servletPath}/removeFromWishList"><c:param name="productId" value="${wishlist.product.productId}"/></c:url>">Remove from Wishlist</a>
                    </td>
                </tr>
        </c:forEach>
        <br/>
    </tbody>
</table>

<%@include file="/WEB-INF/jspf/footer.jspf" %>

