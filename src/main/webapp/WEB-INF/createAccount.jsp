<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="fr.eni.messages.LecteurMessage" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Create account</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/navigation/header.jsp" %>
        <c:if test="${!empty listeCodesErreur}">
            <p>Erreur lors de la création du compte !</p>
            <ul>
                <c:forEach var="code" items="${listeCodesErreur}">
                    <li>${LecteurMessage.getMessageErreur(code)}</li>
                </c:forEach>
            </ul>
        </c:if>
        <form action="${pageContext.request.contextPath}/signUp" method="post">
            <fieldset>
                <legend>Mon profil</legend>
                <label for="username">Pseudo :</label>
                <input type="text" name="username" id="username"><br/>
                <label for="surname">Nom :</label>
                <input type="text" name="surname" id="surname"><br/>
                <label for="firstName">Prénom :</label>
                <input type="text" name="firstName" id="firstName"><br/>
                <label for="email">Email :</label>
                <input type="email" name="email" id="email"><br/>
                <label for="phoneNumber">Telephone :</label>
                <input type="text" name="phoneNumber" id="phoneNumber"><br/>
                <label for="streetName">Rue :</label>
                <input type="text" name="streetName" id="streetName"><br/>
                <label for="zipCode">Code postal :</label>
                <input type="text" name="zipCode" id="zipCode" pattern="[0-9]{5}" minlength="5"
                                                             maxlength="5"><br/>
                <label for="city">Ville :</label>
                <input type="text" name="city" id="city" ><br/>
                <label for="pwd">Mot de passe :</label>
                <input type="password" name="pwd" id="pwd"><br/>
                <label for="confirmPwd">Confirmation :</label>
                <input type="password" name="confirmPwd" id="confirmPwd"><br/>

                <button type="submit" name="btnPressed" value="createAccount">Créer</button>
                <button type="submit" name="btnPressed" value="cancelCreateAccount">Annuler</button>

                <br/>
            </fieldset>
        </form>
    </body>
</html>