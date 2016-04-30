<%-- 
    Document   : CustomerFeedbacks
    Created on : Apr 30, 2016, 6:46:04 PM
    Author     : Dell
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jspf/header.jspf" %>
<h1 style="color: crimson">All Feedbacks!!</h1>


<form method="POST" style="text-align: center" action="<c:url value="${requestScope.servletPath}/navigationServlet"/>">
<table class="table">
        <thead>
            <tr class="info">
                <th>Feedback Id</th>
                <th>Customer Name</th>
                <th>Feedback Date</th>
                <th>Rating</th>
                <th>Description</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.allFeedbacks}" var="feedback">
                <tr class="danger">
                    <td>${feedback.feedbackId}</td>
                    <td>${feedback.customer.firstName}</td>
                    <td>${feedback.feedbackDate}</td>
                    <td>${feedback.rating}</td>
                    <td>${feedback.description}</td>
                </tr>
        </c:forEach>
        <br/>
    </tbody>
</table>
<button class="btn-lg" type="submit" value="Go Back">Go Back</button>
</form>
<%@include file="/WEB-INF/jspf/footer.jspf" %> 