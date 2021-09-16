<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fr.eni.messages.LecteurMessage" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${cookie['cookie_lang'].value}" />
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="${cookie['cookie_lang'].value}">
    <head>
        <title>Accueil</title>
    </head>
    <body>
        <%@include file="/WEB-INF/navigation/header.jsp" %>

        <!-- Different research filters if logged in or not -->
        <c:choose>
            <c:when test="${isUserLoggedIn}">

            </c:when>
            <c:when test="${!isUserLoggedIn}">

            </c:when>
        </c:choose>

        <!-- display all articles -->
        <c:forEach var="a" items="${lesArticles}">
            <div>
                <!-- TODO ajouter l'image-->
                <a href="${pageContext.request.contextPath}/auctionDetails?idArticle=${a.no_article}">
                        ${a.nomArticle}
                </a>
                <p>Prix : ${a.prixVente}</p>
                <p>Vendeur :
                    <a href="${pageContext.request.contextPath}/profile?idUser=${a.lUtilisateur.no_utilisateur}">
                            ${a.lUtilisateur.nom}
                    </a>
                </p>
            </div>
        </c:forEach>
    </body>
</html>