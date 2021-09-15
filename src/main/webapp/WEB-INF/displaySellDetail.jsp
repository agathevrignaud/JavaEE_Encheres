<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <fmt:message key="displaysell.bestBid"/>
        </label>
        ${lArticle.prixVente}<br/>
        <label>
            <fmt:message key="displaysell.startingBid"/>
        </label>
        ${lArticle.miseAPrix}<br/>
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
        R so far<br/>
        <label>
            <fmt:message key="displaysell.myBid"/>
        </label><br/>
        <form method="post" action="${pageContext.request.contextPath}/makeABid">
            <input type="number" id="bid" name="bid" min="${lArticle.prixVente}" step="10" value="${lArticle.prixVente}"/>
            <button type="submit" name="makeABid"><fmt:message key="displaysell.makeABid"/></button>
        </form>
    </body>
</html>
