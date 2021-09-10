<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Mot de passe oublié</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/navigation/header.jsp" %>
        <h3>Veuillez entrez vos identifiants de connexion :</h3>
        <form method="post" action="${pageContext.request.contextPath}/forgottenPwd">
            <fieldset>
                <label for="username">Nom d'utilisateur :</label><input type="text" id="username" name="username">
                <label for="email">Email lié au compte :</label><input type="text" id="email" name="email">
            </fieldset>
            <button type="submit">Valider</button>
            <c:choose>
                <c:when test="${isUserInDb != null && isUserInDb}">
                    <c:out value="Un lien pour réinitialiser le mot de passe vous a été envoyé."></c:out>
                </c:when>
                <c:when test="${isUserInDb != null && !isUserInDb}">
                    <c:out value="Erreur lors de la saisie, veuillez recommencer."></c:out>
                </c:when>
            </c:choose>
        </form>
    </body>
</html>
