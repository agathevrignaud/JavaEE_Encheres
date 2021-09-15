<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html>
<head>
    <title>Accueil</title>
</head>
<body>
<%@include file="/WEB-INF/navigation/header.jsp" %>
<div class="d-flex justify-content-center">
    <h2>Liste des enchères</h2>
</div>
<form action="home" method="post">
    <p>Filtres:</p>
    <input type="search" class="form-control rounded" placeholder="Search" aria-label="Search"
           aria-describedby="search-addon" name="nomArticle"/>
    <label for="category-select">Catégories:
        <select name="categories" id="category-select">
            <option id="cat" value="toutes">Toutes</option>
            <c:forEach var="laCategorie" items="${lesCategories}">
                <option value="${laCategorie.getLibelle()}">${laCategorie.getLibelle()}</option>
            </c:forEach>
        </select>
    </label>
<c:if test="${isUserLoggedIn}">
    <card>
        <div class="form-check">
            <input class="form-check-input position-static" type="radio" name="choix" value="achat"
                   aria-label="...">Achats
        </div>
        <div class="form-check">
            <input class="form-check-input position-static" type="checkbox" name="enchereOuverte" value="true"
                   aria-label="...">Enchères ouvertes
        </div>
        <div class="form-check">
            <input class="form-check-input position-static" type="checkbox" name="enchereEnCours" value="true"

                   aria-label="...">Enchères en cours
        </div>
        <div class="form-check">
            <input class="form-check-input position-static" type="checkbox" name="enchereRemportees" value="true"
                   aria-label="...">Enchères remportées
        </div>
    </card>
    <card>
        <div class="form-check">
            <input class="form-check-input position-static" type="radio" name="choix" value="vente"
                   aria-label="...">Mes ventes
        </div>
        <div class="form-check">
            <input class="form-check-input position-static" type="checkbox" id="inprogresssell"
                   value="optioninprogresssell"
                   aria-label="...">mes ventes en cours
        </div>
        <div class="form-check">
            <input class="form-check-input position-static" type="checkbox" id="notstart"
                   value="optionnotstart"
                   aria-label="...">ventes non débuté
        </div>
        <div class="form-check">
            <input class="form-check-input position-static" type="checkbox" id="close" value="optionclose"
                   aria-label="...">ventes terminées
        </div>
    </card>
</c:if>
    <div>
        <button class="btn btn-primary" type="submit">Rechercher</button>
    </div>
</form>
<br>
<div class=" d-flex justify-content-around">
    <c:forEach var="lArticle" items="${lesArticles}">
    <div class="card">
        <div class="card-header">
            <img src="https://www.rueducommerce.fr/medias/3c7295be321035dea924efd54375a286/p_1000x1000/f3-b-blue-lgpu-nc-mid-sideoff.jpg"
                 alt="" width="50px;">
        </div>
        <div class="card-body">
            <p>${lArticle.getNomArticle()}</p>
            <p>Prix: ${lArticle.getPrixVente()}</p>
            <p>Fin de l'enchère: ${lArticle.getDateFinEnchere()}</p>
            <p>Vendeur: <a
                    href="${pageContext.request.contextPath}/myProfile?idUser=${lArticle.getNo_utilisateur()}">${lArticle.getPseudoUtilisateur()}</a>
            </p>


        </div>
    </div>
    </c:forEach>
</body>
</html>