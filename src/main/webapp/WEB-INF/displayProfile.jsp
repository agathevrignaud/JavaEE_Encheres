<%@ page import="fr.eni.bo.Utilisateur" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>Mon profil</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/utils/css/all.css">
    </head>
    <header>
        <h2>Eni Enchères</h2>
    </header>
    <body>
        <form>
            <legend>Mes informations</legend>
            <input type="hidden" name="idUser" value="${userInfo.no_utilisateur}"/>
            <label for="username"> Pseudo :</label><input type="text" name="username" id="username" value="${userInfo.pseudo}" ><br/>
            <label for="name">Nom :</label><input type="text" name="name" id="name" value="${userInfo.nom}"><br/>
            <label for="surname">Prénom :</label><input type="text" name="surname" id="surname" value="${userInfo.prenom}"><br/>
            <label for="mail"></label>Mail :<input type="email" name="mail" id="mail" value="${userInfo.email}"><br/>
            <label for="phoneNumber">Tel :</label><input type="tel" name="phoneNumber" id="phoneNumber" value="${userInfo.telephone}"><br/>
            <label for="streetName">Adresse :</label><input type="text"  name="streetName" id="streetName" value="${userInfo.rue}"><br/>
            <label for="zip"></label>Code postal :<input type="text" minlength="5" maxlength="5" name="zip" id="zip" value="${userInfo.codePostal}"><br/>
            <label for="city"></label>Ville :<input type="text" name="city" id="city" value="${userInfo.ville}"><br/>
        </form>
        <c:if test="${true}">
            <div>
                <a href="${pageContext.request.contextPath}/editMyProfile?idUser=${userInfo.no_utilisateur}">
                    <i class="fas fa-pen" title="Modifier le profil"></i>
                </a>
            </div>
        </c:if>
    </body>
</html>
