<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="fr.eni.messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${cookie['cookie_lang'].value}" />
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="${cookie['cookie_lang'].value}">
    <head>
        <title><fmt:message key="createacc.title"/></title>
    </head>
    <body>
        <%@include file="/WEB-INF/navigation/header.jsp"%>
        <!-- Print errors (if any) -->
        <c:if test="${!empty listeCodesErreur}">
            <fmt:message key="createacc.error"/>
            <ul>
                <c:forEach var="code" items="${listeCodesErreur}">
                    <li>${LecteurMessage.getMessageErreur(code)}</li>
                </c:forEach>
            </ul>
        </c:if>
        <!-- Account Details -->
        <form method="post" action="${pageContext.request.contextPath}/signUp">
            <fieldset>
                <legend><fmt:message key="createacc.myProfile"/></legend>
                <label for="username">
                    <fmt:message key="createacc.username"/>
                </label>
                <input type="text" name="username" id="username"><br/>
                <label for="surname">
                    <fmt:message key="createacc.surname"/>
                </label>
                <input type="text" name="surname" id="surname"><br/>
                <label for="firstName">
                    <fmt:message key="createacc.firstName"/>
                </label>
                <input type="text" name="firstName" id="firstName"><br/>
                <label for="email">
                    <fmt:message key="createacc.email"/>
                </label>
                <input type="email" name="email" id="email"><br/>
                <label for="phoneNumber">
                    <fmt:message key="createacc.phoneNumber"/>
                </label>
                <input type="text" name="phoneNumber" id="phoneNumber"><br/>
                <label for="streetName">
                    <fmt:message key="createacc.streetName"/>
                </label>
                <input type="text" name="streetName" id="streetName"><br/>
                <label for="zipCode">
                    <fmt:message key="createacc.zipCode"/>
                </label>
                <input type="text" name="zipCode" id="zipCode" pattern="[0-9]{5}" minlength="5"
                                                             maxlength="5"><br/>
                <label for="city">
                    <fmt:message key="createacc.city"/>
                </label>
                <input type="text" name="city" id="city" ><br/>
                <label for="pwd">
                    <fmt:message key="createacc.pwd"/>
                </label>
                <input type="password" name="pwd" id="pwd"><br/>
                <label for="confirmPwd">
                    <fmt:message key="createacc.confirmPwd"/>
                </label>
                <input type="password" name="confirmPwd" id="confirmPwd"><br/>

                <button type="submit" name="btnPressed" value="createAccount">
                    <fmt:message key="createacc.btnCreate"/>
                </button>
                <button type="submit" name="btnPressed" value="cancelCreateAccount">
                    <fmt:message key="createacc.btnCancel"/>
                </button>
            </fieldset>
        </form>
    </body>
</html>