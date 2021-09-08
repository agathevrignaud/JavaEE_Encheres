<%--
  Created by IntelliJ IDEA.
  User: rsaintalme2021
  Date: 08/09/2021
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Mon profil</title>
</head>
<header>
    <h2>Eni Enchères</h2>
</header>
<body>
<form action="/ServletDisplayProfile" method="post">
    <fieldset>
        <legend>Mes informations</legend>
        <label for="username"> Pseudo :</label><input type="text" id="username" readonly><br/>
        <label for="name">Nom :</label><input type="text" id="name" readonly><br/>
        <label for="surname">Prénom :</label><input type="text" id="surname" readonly><br/>
        <label for="mail"></label>Mail :<input type="email" id="mail" readonly><br/>
        <label for="phoneNumber">Tel :</label><input type="tel" id="phoneNumber" readonly><br/>
        <label for="streetName">Adresse :</label><input type="text" id="streetName" readonly><br/>
        <label for="zip"></label>Code postal :<input type="text" minlength="5" maxlength="5" id="zip" readonly><br/>
        <label for="city"></label>Ville :<input type="text" id="city" readonly><br/>
    </fieldset>
</form>
<button value="" name="Modifier">Modifier</button>
</body>
</html>
