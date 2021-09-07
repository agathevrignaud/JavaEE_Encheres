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
    pseudo:<input type="text">
    nom: <input type="text">
    prenom: <input type="text">
    email: <input type="text">
    telephone: <input type="text">
    rue: <input type="text" name="" id="">
    code postal: <input type="text">
    ville: <input type="text">
    mot de passe: <input type="text">
    confirmation: <input type="text">
    <button type="submit">Créer</button>
    <button><a href="home">Annuler</a></button>
</form>
</body>
</html>