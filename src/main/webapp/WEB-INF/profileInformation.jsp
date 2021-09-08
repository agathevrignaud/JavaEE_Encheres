<%@ page import="fr.eni.bo.Utilisateur" %><%--
  Created by IntelliJ IDEA.
  User: rsaintalme2021
  Date: 08/09/2021
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>Mon profil</title>
    </head>
    <header>
        <h2>Eni Enchères</h2>
    </header>
    <body>

        <form method="post" action="${pageContext.request.contextPath}/myProfile" >
            <fieldset>
                <legend>Mes informations</legend>
                <label for="username"> Pseudo :</label><input type="text" name="username" id="username" value="${userInfo.pseudo}" ><br/>
                <label for="name">Nom :</label><input type="text" name="name" id="name" value="${userInfo.nom}"><br/>
                <label for="surname">Prénom :</label><input type="text" name="surname" id="surname" value="${userInfo.prenom}"><br/>
                <label for="mail"></label>Mail :<input type="email" name="mail" id="mail" value="${userInfo.email}"><br/>
                <label for="phoneNumber">Tel :</label><input type="tel" name="phoneNumber" id="phoneNumber" value="${userInfo.telephone}"><br/>
                <label for="streetName">Adresse :</label><input type="text"  name="streetName" id="streetName" value="${userInfo.rue}"><br/>
                <label for="zip"></label>Code postal :<input type="text" minlength="5" maxlength="5" name="zip" id="zip" value="${userInfo.codePostal}"><br/>
                <label for="city"></label>Ville :<input type="text" name="city" id="city" value="${userInfo.ville}"><br/>
            </fieldset>
            <button name="Modifier">Modifier</button>
        </form>

    </body>
</html>
