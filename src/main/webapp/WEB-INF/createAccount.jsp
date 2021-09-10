<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Create account</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/navigation/header.jsp" %>
        <form action="signup" method="post">
            <fieldset>
                <legend>Mon profil</legend>
                <label for="username">Pseudo :</label>
                <input type="text" name="username" id="username" required="required"><br/>
                <label for="surname">Nom :</label>
                <input type="text" name="surname" id="surname" required="required"><br/>
                <label for="firstName">Prénom :</label>
                <input type="text" name="firstName" id="firstName" required="required"><br/>
                <label for="email">Email :</label>
                <input type="email" name="email" id="email" required="required"><br/>
                <label for="phoneNumber">Telephone :</label>
                <input type="text" name="phoneNumber" id="phoneNumber" required="required"><br/>
                <label for="streetName">Rue :</label>
                <input type="text" name="streetName" id="streetName" required="required"><br/>
                <label for="zipCode">Code postal :</label>
                <input type="text" name="zipCode" id="zipCode" pattern="[0-9]{5}" minlength="5"
                                                             maxlength="5" required="required"><br/>
                <label for="city">Ville :</label>
                <input type="text" name="city" id="city" required="required"><br/>
                <label for="password">Mot de passe :</label>
                <input type="password" name="password" id="password" required="required"><br/>
                <label for="confirmPwd">Confirmation :</label>
                <input type="password" name="confirmPwd" id="confirmPwd" required="required"><br/>

                <!-- Changer le bouton -->
                <input type="submit" value="Créer">
                <button><a href="home">Annuler</a></button>

                <br/>
                <c:if test="${not empty error}">
                    <p>Erreur: <c:out value="${error}"></c:out></p>
                </c:if>
            </fieldset>
        </form>
    </body>
</html>