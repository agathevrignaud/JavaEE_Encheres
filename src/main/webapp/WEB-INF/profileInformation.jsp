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
    <title>Mon profile</title>
</head>
<header>
    <h2>Eni Enchères</h2>
</header>
<body>
<form action="/ServletDisplayProfile" method="post">
    <fieldset>
        <legend>Mes informations</legend>
        <label for="username">Pseudo :</label><input type="text" id="username"><br/>
        <label for="name">Nom :</label><input type="text" id="name"><br/>
        <label for="surname">Prénom :</label><input type="text" id="surname"><br/>
        <label for="mail"></label>Mail :<input type="email" id="mail"><br/>
        <label for="phoneNumber">Tel :</label><input type="tel" id="phoneNumber"><br/>
        <label for="streetName">Adresse :</label><input type="text" id="streetName"><br/>
        <label for="zip"></label>Code postal :<input type="text" minlength="5" maxlength="5" id="zip"><br/>
        <label for="city"></label>Ville :<input type="text" id="city"><br/>

    </fieldset>

</form>
</body>
</html>
