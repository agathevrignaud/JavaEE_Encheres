<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Se connecter</title>
</head>
<header>
    <h2>ENI-Enchères</h2>
</header>
<body>
<form action="login" method="post">
    <fieldset>
        <legend> Se connecter</legend>
        <label for="identifiant">Identifiant :</label><input type="text" name="pseudo" id="identifiant" required><br/>
        <label for="password">Mot de passe :</label><input type="password" name="password" id="password" required><br/>
        <input type="submit" value="Connexion">
        <input type="checkbox" id="rememberMe"><label for="rememberMe">Se souvenir de moi</label><br/>
        <a href="" id="forgotPassword">Mot de passe oublié</a><br/>
        <button name="createAccount" type="submit" formaction="signup" formmethod="get">Créer un compte</button><br/>
        <c:if test="${sessionScope.authenticationError != null && sessionScope.authenticationError}">
            <c:out value="ERREUR: Identifiants incorrects. Veuillez réessayer avec les bonnes informations."></c:out>
        </c:if>
    </fieldset>
</form>


</body>
</html>
