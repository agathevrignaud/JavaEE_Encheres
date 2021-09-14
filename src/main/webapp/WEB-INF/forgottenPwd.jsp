<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages"/>
<html>
    <head>
        <title><fmt:message key="forgotpwd.title"/></title>
    </head>
    <body>
        <%@ include file="/WEB-INF/navigation/header.jsp" %>
        <h3><fmt:message key="forgotpwd.msg_input"/></h3>
        <form method="post" action="${pageContext.request.contextPath}/forgottenPwd">
            <fieldset>
                <label for="username">
                    <fmt:message key="forgotpwd.username"/>
                </label>
                <input type="text" id="username" name="username">
                <label for="email">
                    <fmt:message key="forgotpwd.email"/>
                </label><input type="text" id="email" name="email">
            </fieldset>
            <button type="submit">
                <fmt:message key="forgotpwd.reinitializePwd"/>
            </button>
            <c:choose>
                <c:when test="${isUserInDb != null && isUserInDb}">
                    <fmt:message key='forgotpwd.success'/>"
                </c:when>
                <c:when test="${isUserInDb != null && !isUserInDb}">
                    <fmt:message key='forgotpwd.error'/>"
                    <c:if test="${not empty listeCodesErreurs}">
                        <c:forEach var="code" items="${listeCodesErreur}">
                            <li>${LecteurMessage.getMessageErreur(code)}</li>
                        </c:forEach>
                    </c:if>
                </c:when>
            </c:choose>
        </form>
    </body>
</html>
