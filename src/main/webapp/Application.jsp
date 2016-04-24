<%-- 
    Document   : Application
    Created on : Feb 13, 2016, 3:35:17 PM
    Author     : Dell
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jspf/header.jspf" %>


<form method="GET" style="text-align: center" action="<c:url value="${requestScope.servletPath}/newCustomer"/>">
    <button class="btn-lg" type="submit" value="Submit">Register New Customer</button>
    <label><b>This button lets user create new shopping account.</b></label>    
</form>
<br/><br/>

<%@include file="./WEB-INF/jspf/footer.jspf" %>