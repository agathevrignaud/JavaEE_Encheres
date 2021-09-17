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

        <p>Filtres :</p>
        <form method="post" action="${pageContext.request.contextPath}/filterAuctions">
            <input type="text" placeholder="Le nom de l'article contient" />
            <label>Catégories</label>
            <select>
                <c:forEach var="c" items="${lesCategories}">
                    <option value="${c.numCategorie}">${c.libelle}</option>
                </c:forEach>
            </select>
            <!-- Different research filters if logged in or not -->
            <c:if test="${isUserLoggedIn}">
                <input type="radio" name="optionRdButton" id="optionRdButton" value="buy" onclick="onRdBtnBuy()"/><label>Achats</label>
                <input type="checkbox" name="groupBuy" id="openAuctions" value="openAuctions" /><label>Enchères ouvertes</label>
                <input type="checkbox" name="groupBuy" id="participated" value="participated"/><label>mes enchères en cours</label>
                <input type="checkbox" name="groupBuy" id="won" value="won"/><label>mes enchères remportées</label>
                <input type="radio" name="optionRdButton" id="optionRdButton" value="sell" onclick="onRdBtnSell()"><label>Mes Ventes</label>
                <input type="checkbox" name="groupSell" id="myAuctionsInProgress" value="myAuctionsInProgress"/><label>mes ventes en cours</label>
                <input type="checkbox" name="groupSell" id="myAuctionsNotStarted" value="myAuctionsNotStarted"/><label>ventes non débutées</label>
                <input type="checkbox" name="groupSell" id="myAuctionsFinished" value="myAuctionsFinished"/><label>ventes terminées</label>
            </c:if>
            <button type="submit">Rechercher</button>
        </form>

        <!-- Display all articles -->
        <c:forEach var="a" items="${lesArticles}">
            <div>
                <!-- TODO ajouter l'image-->
                <a href="${pageContext.request.contextPath}/auctionDetails?idArticle=${a.numArticle}">
                        ${a.nomArticle}
                </a>
                <p>Prix : ${a.prixVente}</p>
                <p>Fin de l'enchère : ${a.dateFinEnchere}</p>
                <p>Vendeur :
                    <a href="${pageContext.request.contextPath}/myProfile?idUser=${a.lUtilisateur.numUtilisateur}">
                            ${a.lUtilisateur.pseudo}
                    </a>
                </p>
            </div>
        </c:forEach>
    </body>
</html>
<script>
    function onRdBtnSell() {
        document.getElementById("openAuctions").disabled = true ;
        document.getElementById("participated").disabled = true ;
        document.getElementById("won").disabled = true ;
        document.getElementById("myAuctionsInProgress").disabled = false ;
        document.getElementById("myAuctionsNotStarted").disabled = false ;
        document.getElementById("myAuctionsFinished").disabled = false ;
    }
    function onRdBtnBuy() {
        document.getElementById("openAuctions").disabled = false ;
        document.getElementById("participated").disabled = false ;
        document.getElementById("won").disabled = false ;
        document.getElementById("myAuctionsInProgress").disabled = true ;
        document.getElementById("myAuctionsNotStarted").disabled = true ;
        document.getElementById("myAuctionsFinished").disabled = true ;
    }
</script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>