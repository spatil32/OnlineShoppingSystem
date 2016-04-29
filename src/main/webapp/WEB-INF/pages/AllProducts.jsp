<%-- 
    Document   : EditProducts
    Created on : Apr 29, 2016, 2:31:53 PM
    Author     : Dell
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jspf/header.jspf" %>
<h1 style="color: crimson">Edit Products!!</h1>

<form method="POST" style="text-align: center" action="<c:url value="/navigationServlet"/>">
<table class="table">
        <thead>
            <tr class="info">
                <th>Product Id</th>
                <th>Product Name</th>
                <th>Manufacturing Date</th>
                <th>Category</th>
                <th>Price</th>
                <th>Discount</th>
                <th>Total Quantity</th>
                <th>Available Quantity</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.allProducts}" var="product">
                <tr class="danger">
                    <td>${product.productId}</td>
                    <td>${product.productName}</td>
                    <td>${product.mfgDate}</td>
                    <td>${product.category}</td>
                    <td>${product.price}</td>
                    <td>${product.discount}</td>
                    <td>${product.totalQty}</td>
                    <td>${product.availableQty}</td>
                    <td>
                        <a href="<c:url value="${requestScope.servletPath}/editProduct"><c:param name="productId" value="${product.productId}"/></c:url>">Edit Product</a>
                    </td>
                    <td>
                        <a href="<c:url value="${requestScope.servletPath}/navigationServlet"><c:param name="productId" value="${product.productId}"/></c:url>">Delete Product</a>
                    </td>
                </tr>
        </c:forEach>
        <br/>
    </tbody>
</table>
<input class="btn-danger" type="submit" value="Go Back" name="GoBack" id="GoBack"/>
</form>
<%@include file="/WEB-INF/jspf/footer.jspf" %> 