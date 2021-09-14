<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fr.eni.messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <title><fmt:message key="login.signIn"/></title>
    </head>
    <header>
        <h2><fmt:message key="appTitle"/></h2>
    </header>
    <body>
        <%@ include file="/WEB-INF/navigation/header.jsp" %>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <fieldset>
                <legend><fmt:message key="login.signIn"/></legend>
                <label for="username">
                    <i class="fas fa-user" title="<fmt:message key="login.username"/>"></i>
                </label>
                <input type="text" name="username" id="username" value="${fn:length(cUsername) > 0 ? cUsername : ""}" required><br/>
                <label for="pwd">
                    <i class="fas fa-key" title="<fmt:message key="login.password"/>"></i>
                </label>
                <input type="password" name="pwd" id="pwd" value="${fn:length(cPwd) > 0 ? cPwd : ""}" required><br/>
                <button type="submit" name="btnPressed" value="login">
                    <fmt:message key="login.signIn"/>
                </button>
                <label for="rememberMe">
                    <input type="checkbox" id="rememberMe" name="rememberMe" checked="${cRemember ? "checked" : "" }">
                    <fmt:message key="login.rememberMe"/>
                </label><br/>
                <a href="${pageContext.request.contextPath}/forgottenPwd" id="forgotPassword">
                    <fmt:message key="login.forgottenPassword"/>
                </a><br/>
                <button type="submit" name="btnPressed" value="createAccount">
                    <fmt:message key="login.signUp"/>
                </button><br/>
                <c:if test="${not empty listeCodesErreur}">
                    <c:forEach var="code" items="${listeCodesErreur}">
                        <li>${LecteurMessage.getMessageErreur(code)}</li>
                    </c:forEach>
                </c:if>
            </fieldset>
        </form>
    </body>
</html>
