<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${cookie['cookie_lang'].value}" />
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html>
<script>
    function verifyChoix(choix) {
        if (choix === 'achat') {
            document.getElementById('enchereOuverte').disabled = false;
            document.getElementById('enchereEnCours').disabled = false;
            document.getElementById('enchereRemportees').disabled = false;
            document.getElementById('ventesNonCommence').disabled = true;
            document.getElementById('ventesEnCours').disabled = true;
            document.getElementById('ventesTermine').disabled = true;
        } else {
            document.getElementById('enchereOuverte').disabled = true;
            document.getElementById('enchereEnCours').disabled = true;
            document.getElementById('enchereRemportees').disabled = true;
            document.getElementById('ventesNonCommence').disabled = false;
            document.getElementById('ventesEnCours').disabled = false;
            document.getElementById('ventesTermine').disabled = false;
        }
    }
</script>
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
        <card id="filtresAchat">
            <div class="form-check">
                <input class="form-check-input position-static" type="radio" name="choix" value="achat"
                       aria-label="..." onclick="verifyChoix('achat')">Achats
            </div>
            <div class="form-check">
                <input class="form-check-input position-static" type="checkbox" id="enchereOuverte"
                       name="enchereOuverte" value="true"
                       aria-label="..." disabled>Enchères ouvertes
            </div>
            <div class="form-check">
                <input class="form-check-input position-static" type="checkbox" id="enchereEnCours"
                       name="enchereEnCours" value="true"

                       aria-label="..." disabled>Enchères en cours
            </div>
            <div class="form-check">
                <input class="form-check-input position-static" type="checkbox" id="enchereRemportees"
                       name="enchereRemportees" value="true"
                       aria-label="..." disabled>Enchères remportées
            </div>
        </card>
        <card id="filtresVente">
            <div class="form-check">
                <input class="form-check-input position-static" type="radio" name="choix" value="vente"
                       aria-label="..." onclick="verifyChoix('vente')">Mes ventes
            </div>
            <div class="form-check">
                <input class="form-check-input position-static" type="checkbox" id="ventesEnCours" name="ventesEnCours"
                       value="true"
                       aria-label="..." disabled>mes ventes en cours
            </div>
            <div class="form-check">
                <input class="form-check-input position-static" type="checkbox" id="ventesNonCommence"
                       name="ventesNonCommence" value="true"
                       aria-label="..." disabled>ventes non débuté
            </div>
            <div class="form-check">
                <input class="form-check-input position-static" type="checkbox" id="ventesTermine" name="ventesTermine"
                       value="true"
                       aria-label="..." disabled>ventes terminées
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
            <c:if test="${isUserLoggedIn}">
                <p>
                    <a href="${pageContext.request.contextPath}/articleDetails?idArticle=${lArticle.getNumArticle()}"> ${lArticle.getNomArticle()}</a>
                </p>
            </c:if>
            <c:if test="${not isUserLoggedIn}">
                <p>
                        ${lArticle.getNomArticle()}
                </p>
            </c:if>
            <p>Prix: ${lArticle.getPrixVente()}</p>
            <p>Fin de l'enchère: ${lArticle.getDateFinEnchere()}</p>
            <p>Vendeur: <a
                    href="${pageContext.request.contextPath}/myProfile?idUser=${lArticle.getlUtilisateur().getNumUtilisateur()}">${lArticle.getlUtilisateur().getPseudo()}</a>
            </p>


        </div>
    </div>
    </c:forEach>
</body>
</html>