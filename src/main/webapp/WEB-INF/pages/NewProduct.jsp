<%-- 
    Document   : NewProduct
    Created on : Apr 29, 2016, 1:24:28 PM
    Author     : Dell
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/header.jspf" %>
<h1>New Product Registration</h1>

<c:if test="${not empty requestScope.violations}">
    <h2 style="color: red">Violations were found on registration page!</h2>
    <ul>
        <c:forEach items="${requestScope.violations}" var="violation">
            <li>
                <c:out value="${violation.propertyPath}"/>: ${violation.message}
            </li>
        </c:forEach>
    </ul>
</c:if>
    
<form method="POST" style="text-align: center" action="<c:url value="/newProduct"/>">
    <div>
        <label for="productName">Enter Product Name&nbsp;&nbsp;</label>
        <input required class="form-horizontal" type="text" name="productName" id="productName" value="${products.productName}"/>
    </div>
    <div>
        <label for="mfgDate">Enter Manufacturing/Publishing date&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
        <input required class="form-horizontal" type="date" name="mfgDate" id="mfgDate" value="${products.mfgDate}"/>
    </div>
    <div>
        <label for="category">Select Category&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
        <input required class="form-horizontal" type="text" name="category" id="category" value="${products.category}"/>
    </div>
    <div>
        <label for="price">Enter Price&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
        <input required class="form-horizontal" type="number" name="price" id="price" value="${products.price}"/>
    </div>
    <div>
        <label for="discount">Enter Discount&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
        <input required class="form-horizontal" type="number" name="discount" id="discount" value="${products.discount}"/>
    </div>
    <div>
        <label for="totalQty">Enter Total Quantity&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
        <input required class="form-horizontal" type="number" name="totalQty" id="totalQty" value="${products.totalQty}"/>
    </div>
    <div>
        <label for="availableQty">Enter Available quantity&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
        <input required class="form-horizontal" type="number" name="availableQty" id="availableQty" value="${products.availableQty}"/>
    </div>
    <div/>
        <input class="btn-danger" type="submit" name="submitProduct" id="submitProduct"/>
</form>


<%@include file="/WEB-INF/jspf/footer.jspf" %>