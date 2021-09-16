<%@ page import="fr.eni.bo.Utilisateur" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${cookie['cookie_lang'].value}" />
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="${cookie['cookie_lang'].value}">
    <head>
        <title><fmt:message key="displayprofile.title"/></title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/utils/css/all.css">
    </head>
    <header>
        <h2><fmt:message key="appTitle"/></h2>
    </header>
    <body>
        <%@include file="/WEB-INF/navigation/header.jsp" %>
        <form>
            <legend><fmt:message key="displayprofile.myProfile"/></legend>
            <input type="hidden" name="idUser" value="${userInfo.numUtilisateur}"/>
            <label for="username">
                <fmt:message key="displayprofile.username"/>
            </label>
            <input type="text" name="username" id="username" value="${userInfo.pseudo}" ><br/>
            <label for="surname">
                <fmt:message key="displayprofile.surname"/>
            </label>
            <input type="text" name="surname" id="surname" value="${userInfo.nom}"><br/>
            <label for="firstName">
                <fmt:message key="displayprofile.firstName"/>
            </label>
            <input type="text" name="firstName" id="firstName" value="${userInfo.prenom}"><br/>
            <label for="email">
                <fmt:message key="displayprofile.email"/>
            </label>
            <input type="email" name="email" id="email" value="${userInfo.email}"><br/>
            <label for="phoneNumber">
                <fmt:message key="displayprofile.phoneNumber"/>
            </label>
            <input type="tel" name="phoneNumber" id="phoneNumber" value="${userInfo.telephone}"><br/>
            <label for="streetName">
                <fmt:message key="displayprofile.streetName"/>
            </label><input type="text"  name="streetName" id="streetName" value="${userInfo.rue}"><br/>
            <label for="zipCode">
                <fmt:message key="displayprofile.zipCode"/>
            </label>
            <input type="text" minlength="5" maxlength="5" name="zipCode" id="zipCode" value="${userInfo.codePostal}"><br/>
            <label for="city">
                <fmt:message key="displayprofile.city"/>
            </label>
            <input type="text" name="city" id="city" value="${userInfo.ville}"><br/>
        </form>
        <c:if test="${isItYou}">
            <div>
                <a href="${pageContext.request.contextPath}/editMyProfile?idUser=${userInfo.numUtilisateur}">
                    <i class="fas fa-pen" title="<fmt:message key="displayprofile.msg_edit"/>"></i>
                </a>
            </div>
        </c:if>
    </body>
</html>
