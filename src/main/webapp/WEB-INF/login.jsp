<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Se connecter</title>
    </head>
    <header>
        <h2>ENI-Enchères</h2>
    </header>
    <body>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <fieldset>
                <legend> Se connecter</legend>

                <label for="username">Identifiant :</label>
                <input type="text" name="username" id="username" value="${fn:length(cUsername) > 0 ? cUsername : ""}" required><br/>
                <label for="password">Mot de passe :</label>
                <input type="password" name="password" id="password" value="${fn:length(cPwd) > 0 ? cPwd : ""}" required><br/>

                <button type="submit">Connexion</button>

                <input type="checkbox" id="rememberMe" name="rememberMe"><label for="rememberMe">Se souvenir de moi</label><br/>
                <a href="${pageContext.request.contextPath}/forgottenPwd" id="forgotPassword">
                    Mot de passe oublié
                </a><br/>

                <button name="createAccount" type="submit" formaction="signup" formmethod="get">Créer un compte</button><br/>
                <c:if test="${sessionScope.authenticationError != null && sessionScope.authenticationError}">
                    <c:out value="ERREUR: Identifiants incorrects. Veuillez réessayer avec les bonnes informations."></c:out>
                </c:if>
            </fieldset>
        </form>
    </body>
</html>
