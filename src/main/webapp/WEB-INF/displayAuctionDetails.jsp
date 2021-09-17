<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fr.eni.messages.LecteurMessage" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${cookie['cookie_lang'].value}" />
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="${cookie['cookie_lang'].value}">
    <head>
        <title><fmt:message key="displayauction.title"/></title>
    </head>
    <body>
        <%@include file="/WEB-INF/navigation/header.jsp" %>
        <c:choose>
            <c:when test="${auctionInProgress && !auctionEditable}">
                <h2><fmt:message key="displayauction.title"/></h2>
            </c:when>
            <c:when test="${!auctionInProgress && !auctionEditable}">
                <c:choose>
                    <c:when test="${userInfo.numUtilisateur == lEnchere.no_utilisateur}">
                        <h2><fmt:message key="displayauction.youWon"/></h2>
                    </c:when>
                    <c:when test="${userInfo.numUtilisateur != lEnchere.no_utilisateur}">
                        <h2>
                            <fmt:message key='displayauction.someoneElseWon'>
                                <fmt:param value="${highestBidder.nom}"></fmt:param>
                            </fmt:message>
                        </h2>
                    </c:when>
                </c:choose>
            </c:when>
        </c:choose>

        <h3>${lArticle.nomArticle}</h3>
        <c:if test="${userInfo.numUtilisateur == lArticle.numUtilisateur}">
            <fmt:message key="displayauction.youAreTheSeller"/>
        </c:if>
        <br/>
        <label>
            <fmt:message key="displayauction.description"/>
        </label>
        ${lArticle.description}<br/>
        <label>
            <fmt:message key="displayauction.category"/>
        </label>
        ${lArticle.laCategorie.libelle}<br/>
        <label>
            <fmt:message key="displayauction.highestBid"/>
        </label>
        ${lArticle.prixVente} <fmt:message key="displayauction.credits"/><br/>
        <label>
            <fmt:message key="displayauction.startingBid"/>
        </label>
        ${lArticle.miseAPrix} <fmt:message key="displayauction.credits"/><br/>
        <label>
            <fmt:message key="displayauction.endBidDate"/>
        </label>
        ${lArticle.dateFinEnchere}<br/>
        <label>
            <fmt:message key="displayauction.collectLocation"/>
        </label>
        ${lArticle.lieuRetrait}<br/>
        <label>
            <fmt:message key="displayauction.seller"/>
        </label>
        ${lArticle.lUtilisateur.pseudo}<br/>

        <c:if test="${!auctionInProgress}">
            <c:if test="${userInfo.numUtilisateur == lEnchere.numUtilisateur}">
                <label>
                    <fmt:message key="displayauction.sellerTel"/>
                </label>
                ${highestBidder.telephone}<br/>
            </c:if>
        </c:if>

        <!-- The user logged in is not the seller -->
        <c:if test="${(userInfo.numUtilisateur != lArticle.numUtilisateur) && (auctionInProgress)}">
            <label>
                <fmt:message key="displayauction.myBid"/>
            </label><br/>
            <form method="post" action="${pageContext.request.contextPath}/makeABid">
                <input type="hidden" id="idArticle" name="idArticle" value="${lArticle.numUtilisateur}"/>
                <input type="hidden" id="highestBid" name="highestBid" value="${lArticle.prixVente}"/>
                <input type="number" id="bid" name="bid" min="${lArticle.prixVente}" step="10" value="${lArticle.prixVente}"/>
                <button type="submit" name="makeABid"><fmt:message key="displayauction.makeABid"/></button>
            </form>
            <c:if test="${!empty listeCodesErreur}">
                <ul>
                    <c:forEach var="code" items="${listeCodesErreur}">
                        <li>${LecteurMessage.getMessageErreur(code)}</li>
                    </c:forEach>
                </ul>
            </c:if>
            <c:if test="${successfulBid}">
                <fmt:message key='displayauction.success'>
                    <fmt:param value="${lArticle.nomArticle}"></fmt:param>
                    <fmt:param value="${highestBidder.credit}"></fmt:param>
                </fmt:message>
            </c:if>
        </c:if>
        <c:if test="${!lArticle.lUtilisateur.compteActif}">
            <p>Le compte de ce vendeur n'est plus actif. La vente a été annulée.</p>
        </c:if>

        <!-- The user logged in is the seller -->
        <c:if test="${(userInfo.numUtilisateur == lArticle.numUtilisateur)}">
            <c:if test="${auctionEditable}">
                <a href="${pageContext.request.contextPath}/editMyAuction">Modifier la vente</a>
            </c:if>
            <br/>
            <c:if test="${!auctionEditable}">
                <table>
                    <tr>
                        <th><fmt:message key="displayauction.allBidders"/></th>
                        <th><fmt:message key="displayauction.bidValue"/></th>
                        <th><fmt:message key="displayauction.bidDate"/></th>
                    </tr>
                    <tr>
                        <c:forEach var="e" items="${lesEncherisseurs}">
                            <td>
                                <a href="${pageContext.request.contextPath}/myProfile?idUser=${u.numUtilisateur}">
                                    <i class="fas fa-user" title="<fmt:message key="admin.seeProfile"/>"></i>
                                </a>
                            </td>
                            <td>${e.nom}</td>
                            <td>${e.montant_enchere}</td>
                            <td>${e.date_enchere}</td>
                        </c:forEach>
                    </tr>
                </table>
            </c:if>
        </c:if>
        <c:if test="${!empty listeCodesErreur}">
            <fmt:message key="displayauction.bidError"/>
            <ul>
                <c:forEach var="code" items="${listeCodesErreur}">
                    <li>${LecteurMessage.getMessageErreur(code)}</li>
                </c:forEach>
            </ul>
        </c:if>
    </body>
</html>
