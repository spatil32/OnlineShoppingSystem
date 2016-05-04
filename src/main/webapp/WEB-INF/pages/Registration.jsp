<%-- 
    Document   : Registration
    Created on : Apr 24, 2016, 9:19:06 PM
    Author     : Dell
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/header.jspf" %>
<h1>New Customer Registration</h1>

<c:if test="${not empty requestScope[customer]}">
    <h2>${requestScope.customer.firstName}
        ${requestScope.customer.lastName}</h2>
    </c:if>

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

<form method="POST" style="text-align: center" action="<c:url value="/customer/newCustomer"/>">
    <div>
        <label for="firstName">Enter First Name&nbsp;&nbsp;</label>
        <input required class="form-horizontal" type="text" name="firstName" id="firstName" value="${customer.firstName}"/>
    </div>
    <div>
        <label for="lastName">Enter Last Name&nbsp;&nbsp;</label>
        <input required class="form-horizontal" type="text" name="lastName" id="lastName" value="${customer.lastName}"/>
    </div>
    <div>
        <label for="age">Enter Age&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
        <input required class="form-horizontal" type="number" name="age" id="age" value="${customer.age}"/>
    </div>
    <div>
        <label for="gender">Enter Gender&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
        <input required class="form-horizontal" type="text" name="gender" id="gender" value="${customer.gender}"/>
    </div>
    <div>
        <label for="address">Enter Address&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
        <input required class="form-horizontal" type="text" name="address" id="address" value="${customer.address}"/>
    </div>    
    <div>
        <label for="email">Enter Email&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
        <input required class="form-horizontal" type="email" name="email" id="email" value="${customer.email}"/>
    </div>
    <div>
        <label for="birthDate">Enter Birth date&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
        <input required class="form-horizontal" type="date" name="birthDate" id="birthDate" value="${customer.birthDate}"/>
    </div>
    <div>
        <label for="phoneNo">Enter Phone&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
        <input required class="form-horizontal" type="text" name="phoneNo" id="phoneNo" value="${customer.phoneNo}"/>
    </div>
    <div>
        <label for="username">Enter Username&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
        <input required class="form-horizontal" type="text" name="username" id="username" value="${customer.username}"/>
    </div>
    <div>
        <label for="password">Enter Password&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
        <input required class="form-horizontal" type="password" name="password" id="password" value="${customer.password}"/>
    </div>
    <div/>
    <input class="btn-danger" type="submit" name="submitCustomer" id="submitCustomer"/>
</form>


<%@include file="/WEB-INF/jspf/footer.jspf" %>