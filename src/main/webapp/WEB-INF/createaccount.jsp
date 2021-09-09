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
        <label for="pseudo">Pseudo:</label><input type="text" name="pseudo" id="pseudo" required=required><br/>
        <label for="surname">Nom: </label><input type="text" name="surname" id="surname" required=required><br/>
        <label for="name">Prénom: </label><input type="text" name="name" id="name" required=required<br/>
        <label for="mail">Email: </label><input type="email" name="mail" id="mail" required=required><br/>
        <label for="phone">Telephone: </label><input type="text" name="phone" id="phone" required=required><br/>
        <label for="street"></label>Rue:<input type="text" name="street" id="street" required=required><br/>
        <label for="zip">Code postal: </label><input type="text" name="zip" id="zip" pattern="[0-9]{5}" minlength="5"
                                                     maxlength="5" required=required><br/>
        <label for="city">Ville:</label><input type="text" name="city" id="city" required=required><br/>
        <label for="password">Mot de passe:</label><input type="password" name="password" id="password" required=required><br/>
        <label for="confirmPwd">Confirmation:</label><input type="password" name="confirmPwd" id="confirmPwd" required=required><br/>
        <input type="submit" value="Créer">
        <button><a href="home">Annuler</a></button>
    </fieldset>
</form>
</body>
</html>