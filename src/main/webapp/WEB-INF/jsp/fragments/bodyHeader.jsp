<%--
  Created by IntelliJ IDEA.
  User: LeTRy
  Date: 4/13/2021
  Time: 3:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<header>
<%--    <a href="https://letry.ru"><input type="button" value="Home"></a>--%>
    <a href="https://letry.ru" class="button-main">Home</a>
    <a href="${pageContext.request.contextPath}/" class="button-main">Restaurants</a>
    <a href="${pageContext.request.contextPath}/logout" class="button-main">Logout</a>
    <hr>
</header>