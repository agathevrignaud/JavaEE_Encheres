<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit profile</title>
    </head>
        <body>
            <p>Mon Profil</p>
            <form action="" method="">
                pseudo : <input type="text">
                nom : <input type="text">
                prénom : <input type="text">
                email : <input type="text">
                telephone: <input type="text">
                rue: <input type="text">
                code postal: <input type="text">
                ville : <input type="text">
                mot de passe actuel : <input type="text">
                nouveau mot de passe : <input type="text">
                confirmation : <input type="text">
                crédit :
                <button type="submit" name="Save">Enregistrer</button>
                <button type="submit" name="Delete">Supprimer compte</button>
            </form>
    </body>
</html>