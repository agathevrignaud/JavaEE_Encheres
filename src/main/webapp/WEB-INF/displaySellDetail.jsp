<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fr.eni.messages.LecteurMessage" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${cookie['cookie_lang'].value}" />
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="${cookie['cookie_lang'].value}">
    <head>
        <title><fmt:message key="displaysell.title"/></title>
    </head>
    <body>
        <%@include file="/WEB-INF/navigation/header.jsp" %>
        <h2><fmt:message key="displaysell.title"/></h2>

        <h3>${lArticle.nomArticle}</h3>
        <label>
            <fmt:message key="displaysell.description"/>
        </label>
        ${lArticle.description}<br/>
        <label>
            <fmt:message key="displaysell.category"/>
        </label>
        ${lArticle.laCategorie.libelle}<br/>
        <label>
            <fmt:message key="displaysell.highestBid"/>
        </label>
        ${lArticle.prixVente} <fmt:message key="displaysell.credits"/><br/>
        <label>
            <fmt:message key="displaysell.startingBid"/>
        </label>
        ${lArticle.miseAPrix} <fmt:message key="displaysell.credits"/><br/>
        <label>
            <fmt:message key="displaysell.endBidDate"/>
        </label>
        ${lArticle.dateFinEnchere}<br/>
        <label>
            <fmt:message key="displaysell.collectLocation"/>
        </label>
        ${lArticle.lieuRetrait}<br/>
        <label>
            <fmt:message key="displaysell.seller"/>
        </label>
        ${lArticle.no_utilisateur}<br/>
        Vendeur à venir (cf branche 2004 & modifs associées)<br/>
        <c:if test="${userInfo.no_utilisateur != lArticle.no_utilisateur}">
            <label>
                <fmt:message key="displaysell.myBid"/>
            </label><br/>
            <form method="post" action="${pageContext.request.contextPath}/makeABid">
                <input type="hidden" id="idArticle" name="idArticle" value="${lArticle.no_article}"/>
                <input type="hidden" id="highestBid" name="highestBid" value="${lArticle.prixVente}"/>
                <input type="number" id="bid" name="bid" min="${lArticle.prixVente}" step="10" value="${lArticle.prixVente}"/>
                <button type="submit" name="makeABid"><fmt:message key="displaysell.makeABid"/></button>
            </form>
            <c:if test="${!empty listeCodesErreur}">
                <ul>
                    <c:forEach var="code" items="${listeCodesErreur}">
                        <li>${LecteurMessage.getMessageErreur(code)}</li>
                    </c:forEach>
                </ul>
            </c:if>
            <c:if test="${not empty successfulBid}">
                <fmt:message key="displaysell.success">
                    <fmt:param value="${lArticle.nomArticle}"></fmt:param>
                    <fmt:param value="${userInfo.credit}"></fmt:param>
                </fmt:message>
            </c:if>
        </c:if>
    </body>
</html>
