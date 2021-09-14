<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Edit profile</title>
    </head>
        <body>
            <%@include file="/WEB-INF/navigation/header.jsp" %>
            <p>Mon Profil</p>
            <form method="post" action="${pageContext.request.contextPath}/editMyProfile" >
                <fieldset>
                    <legend>Mon Profil</legend>
                        <input type="hidden" name="idUser" id="idUser" value="${userInfo.no_utilisateur}">
                        <label for="username"> Pseudo :</label><input type="text" name="username" id="username" value="${userInfo.pseudo}" ><br/>
                        <label for="surname">Nom :</label><input type="text" name="surname" id="surname" value="${userInfo.nom}"><br/>
                        <label for="firstName">Prénom :</label><input type="text" name="firstName" id="firstName" value="${userInfo.prenom}"><br/>
                        <label for="email"></label>Mail :<input type="email" name="email" id="email" value="${userInfo.email}"><br/>
                        <label for="phoneNumber">Téléphone :</label><input type="tel" name="phoneNumber" id="phoneNumber" value="${userInfo.telephone}"><br/>
                        <label for="streetName">Adresse :</label><input type="text"  name="streetName" id="streetName" value="${userInfo.rue}"><br/>
                        <label for="zipCode"></label>Code postal :<input type="text" minlength="5" maxlength="5" name="zipCode" id="zipCode" value="${userInfo.codePostal}"><br/>
                        <label for="city"></label>Ville :<input type="text" name="city" id="city" value="${userInfo.ville}"><br/>
                        <label for="currentPwd"></label>Mot de passe actuel :<input type="password" name="currentPwd" id="currentPwd" value="${userInfo.motDePasse}"><br/>
                        <label for="newPwd"></label>Nouveau mot de passe :<input type="password" name="newPwd" id="newPwd" value=""><br/>
                        <label for="newPwdConfirmed"></label>Confirmation :<input type="password" name="newPwdConfirmed" id="newPwdConfirmed" value=""><br/>
                        <label for="credit"></label>Credit :<input type="text" name="credit" id="credit" value="${userInfo.credit}" readonly><br/>
                    <a href="${pageContext.request.contextPath}/creditShop?idUser=${userInfo.no_utilisateur}&firstName=${userInfo.prenom}">
                        Acheter des crédits
                    </a>
                </fieldset>
                <button type="submit" name="btnPressed" value="save">Enregistrer</button>
                <button type="submit" name="btnPressed" value="delete">Supprimer mon Compte</button>
            </form>
    </body>
</html>