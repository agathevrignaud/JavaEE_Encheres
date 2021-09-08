<%--
  Created by IntelliJ IDEA.
  User: rsaintalme2021
  Date: 07/09/2021
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Se connecter</title>
</head>
<header>
    <h2>ENI-Enchères</h2>
</header>
<body>
<form>
    <fieldset>
        <legend> Se connecter </legend>
        <label for="identifiant">Identifiant :</label><input type="text" id="identifiant"><br/>
        <label for="password">Mot de passe :</label><input type="password" id="password"><br/>
        <input type="submit" value="Connexion">
        <input type="checkbox" id="rememberMe" ><label for="rememberMe">Se souvenir de moi</label><br/>
        <a href="" id="forgotPassword">Mot de passe oublié</a><br/>
        <button name="createAccount" value="">Créer un compte</button>
    </fieldset>
</form>

</body>
</html>
