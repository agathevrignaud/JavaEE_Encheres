<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="fr.eni.messages.LecteurMessage" %>

<html>
    <head>
        <title><fmt:message key="login.signIn"/></title>
    </head>
    <header>
        <h2>ENI-Enchères</h2>
    </header>
    <body>
        <%@ include file="/WEB-INF/navigation/header.jsp" %>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <fieldset>
                <legend> Se connecter</legend>
                <label for="username">Identifiant :</label>
                <input type="text" name="username" id="username" value="${fn:length(cUsername) > 0 ? cUsername : ""}" required><br/>
                <label for="pwd">Mot de passe :</label>
                <input type="password" name="pwd" id="pwd" value="${fn:length(cPwd) > 0 ? cPwd : ""}" required><br/>
                <button type="submit" name="btnPressed" value="login">Connexion</button>
                <label for="rememberMe">
                    <input type="checkbox" id="rememberMe" name="rememberMe" checked="${cRemember ? "checked" : "" }">
                        Se souvenir de moi
                </label><br/>
                <a href="${pageContext.request.contextPath}/forgottenPwd" id="forgotPassword">
                    Mot de passe oublié
                </a><br/>

                <button type="submit" name="btnPressed" value="createAccount"> Créer un compte</button><br/>
                <c:if test="${not empty listeCodesErreur}">
                    <c:forEach var="code" items="${listeCodesErreur}">
                        <li>${LecteurMessage.getMessageErreur(code)}</li>
                    </c:forEach>
                </c:if>
            </fieldset>
        </form>
    </body>
</html>
