<%-- 
    Document   : Feedback
    Created on : Apr 29, 2016, 8:25:08 PM
    Author     : Dell
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/header.jspf" %>

<h1 style="color: crimson">Feedback Form</h1>
<form method="POST" style="text-align: center" action="<c:url value="/customer/insertFeedback"/>">
    <div>
        <label for="feedback">Provide rating : &nbsp;&nbsp;</label>
        <select name="rating">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
        </select>
    </div>
    <div>
        <label for="feedback">Comment : &nbsp;&nbsp;</label>
        <textarea required="true" name="comment"></textarea> 
    </div>
    <input class="btn-danger" type="submit" value="Submit" name="SubmitFeedback" id="SubmitFeedback"/>
</form>
           
           
<%@include file="/WEB-INF/jspf/footer.jspf" %> 
