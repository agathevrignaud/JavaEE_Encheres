<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<head>
    <title>Create account</title>
</head>
<body>
<p>Mon profil</p>
<form action="ServletCreateAccount">
    pseudo:<input type="text" id="pseudo">
    nom: <input type="text" id="surname">
    prenom: <input type="text" id="name">
    email: <input type="text" id="email">
    telephone: <input type="text" id="phone">
    rue: <input type="text" name="" id="street">
    code postal: <input type="text" id="cp">
    ville: <input type="text" id="city">
    mot de passe: <input type="text" id="password">
    confirmation: <input type="text" id="confirm">
    <button type="submit">Créer</button>
    <button><a href="home">Annuler</a></button>
</form>
</body>
</html>