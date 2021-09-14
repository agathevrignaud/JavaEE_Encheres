<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title><fmt:message key="buycredit.title"/></title>
</head>
<body>
    <%@include file="/WEB-INF/navigation/header.jsp" %>
    <h3>
        <fmt:message key="buycredit.msg_info">
            <fmt:param value="${firstName}"></fmt:param>
        </fmt:message>
    </h3>
    <form method="post" action="${pageContext.request.contextPath}/creditShop">
        <input type="number" min="0" max="500" step="10" name="creditsBought">
        <input type="hidden" value="${idUser}" name="idUser">
        <button type="submit"><fmt:message key="buycredit.confirm"/></button>
    </form>
    <c:if test="${newBalance != null}">
        <fmt:message key='buycredit.msgNewBalance'/>
    </c:if>
</body>
</html>
