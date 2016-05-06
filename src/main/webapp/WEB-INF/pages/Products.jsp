<%-- 
    Document   : Products
    Created on : Apr 25, 2016, 9:11:38 PM
    Author     : Dell
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jspf/header.jspf" %>
<h1 style="color: crimson">All Products!!</h1>


<div class="container">
    <marqee><h3>You are just 2 clicks away for shopping.</h3></marqee>
    <div class="progress">
        <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="30" aria-valuemin="0" aria-valuemax="100" style="width:30%">
            30%
        </div>
    </div>
</div>


<form method="GET" style="text-align: center" action="<c:url value="/previousOrders"/>">
    <input class="btn-danger" type="submit" value="Your Orders" name="previousOrders" id="previousOrders"/>
</form>

<form method="POST" style="text-align: center" action="<c:url value="/addToCart"/>">
    <div class="pricing-table">
        <div class="panel panel-primary" style="border: none;">
            <div class="panel-body panel-body-landing">
                <table class="table">
                    <thead>
                        <tr>
                            <th width="80px">Product Id</th>
                            <th width="50px">Product Name</th>
                            <th width="50px">Manufacturing Date</th>
                            <th width="50px">Category</th>
                            <th width="50px">Price</th>
                            <th width="50px">Discount</th>
                            <th width="50px">Total Quantity</th>
                            <th width="50px">Available Quantity</th>
                            <th width="50px"></th>
                            <th width="50px"></th>                
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.allProducts}" var="product">
                            <tr>
                                <td width="80px">${product.productId}</td>
                                <td width="80px">${product.productName}</td>
                                <td width="80px">${product.mfgDate}</td>
                                <td width="80px">${product.category}</td>
                                <td width="80px">${product.price}</td>
                                <td width="80px">${product.discount}</td>
                                <td width="80px">${product.totalQty}</td>
                                <td width="80px">${product.availableQty}</td>
                                <td width="80px">
                                    <a href="<c:url value="${requestScope.servletPath}/addToCart"><c:param name="productId" value="${product.productId}"/></c:url>">Add To Cart</a>
                                    </td>
                                    <td width="80px">
                                        <a href="<c:url value="${requestScope.servletPath}/addToWishList"><c:param name="productId" value="${product.productId}"/></c:url>">Add To Wishlist</a>
                                    </td>
                                </tr>
                        </c:forEach>
                    <br/>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <input class="btn-danger" type="submit" value="Go To Cart" name="submitCart" id="submitCart"/>
</form>
<%@include file="/WEB-INF/jspf/footer.jspf" %>                