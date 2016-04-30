<%-- 
    Document   : UpdateAccount
    Created on : Apr 30, 2016, 11:52:39 PM
    Author     : Dell
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jspf/header.jspf" %>
<h1>Update Product.!!</h1>

<c:if test="${not empty requestScope.violations}">
    <h2 style="color: red">Violations were found in Update customer!</h2>
    <ul>
        <c:forEach items="${requestScope.violations}" var="violation">
            <li>
                <c:out value="${violation.propertyPath}"/>: ${violation.message}
            </li>
        </c:forEach>
    </ul>
</c:if>
    
<a href="<c:url value="${requestScope.servletPath}/deleteAccount"></c:url>">Delete Account</a>    
<form method="POST" style="text-align: center" action="<c:url value="/updateAccount"/>">
    <div>
        <label for="customerId">Customer ID&nbsp;&nbsp;</label>
        <input readonly required class="form-horizontal" type="text" name="customerId" id="customerId" value="${loggedInCustomer.customerId}"/>
    </div>
    <div>
        <label for="firstName">Enter First Name&nbsp;&nbsp;</label>
        <input required class="form-horizontal" type="text" name="firstName" id="firstName" value="${loggedInCustomer.firstName}"/>
    </div>
    <div>
        <label for="lastName">Enter Last Name&nbsp;&nbsp;</label>
        <input required class="form-horizontal" type="text" name="lastName" id="lastName" value="${loggedInCustomer.lastName}"/>
    </div>
    <div>
        <label for="age">Enter Age&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
        <input required class="form-horizontal" type="number" name="age" id="age" value="${loggedInCustomer.age}"/>
    </div>
    <div>
        <label for="gender">Enter Gender&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
        <input required class="form-horizontal" type="text" name="gender" id="gender" value="${loggedInCustomer.gender}"/>
    </div>
    <div>
        <label for="address">Enter Address&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
        <input required class="form-horizontal" type="text" name="address" id="address" value="${loggedInCustomer.address}"/>
    </div>    
    <div>
        <label for="email">Enter Email&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
        <input required class="form-horizontal" type="email" name="email" id="email" value="${loggedInCustomer.email}"/>
    </div>
    <div>
        <label for="birthDate">Enter Birth date&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
        <input required class="form-horizontal" type="date" name="birthDate" id="birthDate" value="${loggedInCustomer.birthDate}"/>
    </div>
    <div>
        <label for="phoneNo">Enter Phone&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
        <input required class="form-horizontal" type="text" name="phoneNo" id="phoneNo" value="${loggedInCustomer.phoneNo}"/>
    </div>
    <div/>
<input class="btn-danger" type="submit" name="Update Account" id="submitAccount"/>
</form>
        
<%@include file="/WEB-INF/jspf/footer.jspf" %>

