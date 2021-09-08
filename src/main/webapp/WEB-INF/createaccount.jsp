<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create account</title>
</head>
<body>
<form action="signup" method="post">
    <fieldset>
        <legend>Mon profil</legend>
        <label for="pseudo">Pseudo:</label><input type="text" name="pseudo" id="pseudo"><br/>
        <label for="surname">Nom: </label><input type="text" name="surname" id="surname"><br/>
        <label for="name">Prénom: </label><input type="text" name="name" id="name"><br/>
        <label for="mail">Email: </label><input type="email" name="mail" id="mail"><br/>
        <label for="phone">Telephone: </label><input type="text" name="phone" id="phone"><br/>
        <label for="street"></label>Rue:<input type="text" name="street" id="street"><br/>
        <label for="zip">Code postal: </label><input type="text" name="zip" id="zip" pattern="[0-9]{5}" minlength="5"
                                                     maxlength="5"><br/>
        <label for="city">Ville:</label><input type="text" name="city" id="city"><br/>
        <label for="password">Mot de passe:</label><input type="password" name="password" id="password"><br/>
        <label for="confirmPwd">Confirmation:</label><input type="password" name="confirmPwd" id="confirmPwd"><br/>
        <input type="submit" value="Créer">
        <button><a href="home">Annuler</a></button>
    </fieldset>
</form>
</body>
</html>