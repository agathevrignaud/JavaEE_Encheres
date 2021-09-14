<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html>
    <head>
        <title><fmt:message key="editprofile.title"/></title>
    </head>
    <body>
        <%@include file="/WEB-INF/navigation/header.jsp" %>
        <p><fmt:message key="editprofile.title"/></p>
        <form method="post" action="${pageContext.request.contextPath}/editMyProfile" >
            <fieldset>
                <legend><fmt:message key="editprofile.myProfile"/></legend>
                <input type="hidden" name="idUser" id="idUser" value="${userInfo.no_utilisateur}">
                <label for="username">
                    <fmt:message key="editprofile.username"/>
                </label>
                <input type="text" name="username" id="username" value="${userInfo.pseudo}" ><br/>
                <label for="surname">
                    <fmt:message key="editprofile.surname"/>
                </label>
                <input type="text" name="surname" id="surname" value="${userInfo.nom}"><br/>
                <label for="firstName">
                    <fmt:message key="editprofile.firstName"/>
                </label>
                <input type="text" name="firstName" id="firstName" value="${userInfo.prenom}"><br/>
                <label for="email">
                    <fmt:message key="editprofile.email"/>
                </label>
                <input type="email" name="email" id="email" value="${userInfo.email}"><br/>
                <label for="phoneNumber">
                    <fmt:message key="editprofile.phoneNumber"/>
                </label>
                <input type="tel" name="phoneNumber" id="phoneNumber" value="${userInfo.telephone}"><br/>
                <label for="streetName">
                    <fmt:message key="editprofile.streetName"/>
                </label>
                <input type="text"  name="streetName" id="streetName" value="${userInfo.rue}"><br/>
                <label for="zipCode">
                    <fmt:message key="editprofile.zipCode"/>
                </label>
                <input type="text" minlength="5" maxlength="5" name="zipCode" id="zipCode" value="${userInfo.codePostal}"><br/>
                <label for="city">
                    <fmt:message key="editprofile.city"/>
                </label>
                <input type="text" name="city" id="city" value="${userInfo.ville}"><br/>
                <label for="currentPwd">
                    <fmt:message key="editprofile.pwd"/>
                </label>
                <input type="password" name="currentPwd" id="currentPwd" value="${userInfo.motDePasse}"><br/>
                <label for="newPwd">
                    <fmt:message key="editprofile.newPwd"/>
                </label>
                <input type="password" name="newPwd" id="newPwd" value=""><br/>
                <label for="newPwdConfirmed">
                    <fmt:message key="editprofile.confirmPwd"/>
                </label>
                <input type="password" name="newPwdConfirmed" id="newPwdConfirmed" value=""><br/>
                <label for="credit">
                    <fmt:message key="editprofile.credit"/>
                </label>
                <input type="text" name="credit" id="credit" value="${userInfo.credit}" readonly><br/>
                <a href="${pageContext.request.contextPath}/creditShop?idUser=${userInfo.no_utilisateur}&firstName=${userInfo.prenom}">
                    <fmt:message key="editprofile.creditShop"/>
                </a>
            </fieldset>
            <button type="submit" name="btnPressed" value="save">
                <fmt:message key="editprofile.btnSave"/>
            </button>
            <button type="submit" name="btnPressed" value="delete">
                <fmt:message key="editprofile.btnDelete"/>
            </button>
        </form>
    </body>
</html>