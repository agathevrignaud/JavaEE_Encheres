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
        <form action="">
            <p>Filtres:</p>
            <input type="search" class="form-control rounded" placeholder="Search" aria-label="Search"
                   aria-describedby="search-addon"/>
            <label for="category-select">Catégories:</label>

            <select name="category" id="category-select">
                <option id="cat1" value="toutes">Toutes</option>
                <option id="cat2" value="informatique">Informatique</option>
                <option id="cat3" value="vetement">Vêtement</option>
                <option id="cat4" value="ameublement">Ameublement</option>
                <option value="sport">Sports&Loisirs</option>
            </select>
            <div>
                <button class="btn btn-primary" type="submit">Rechercher</button>
            </div>
        </form>
        <br>
        <c:if test="${isUserLoggedIn}">
            <card>
                <form action="">
                    <div class="form-check">
                        <input class="form-check-input position-static" type="radio" name="buy" id="buy" value="optionbuy"
                               aria-label="...">Achats
                    </div>
                    <div class="form-check">
                        <input class="form-check-input position-static" type="checkbox" id="open" value="optionopen"
                               aria-label="...">Enchères ouvertes
                    </div>
                    <div class="form-check">
                        <input class="form-check-input position-static" type="checkbox" id="inprogressbuy"
                               value="optioninprogressbuy"
                               aria-label="...">Enchères en cours
                    </div>
                    <div class="form-check">
                        <input class="form-check-input position-static" type="checkbox" id="gain" value="optiongain"
                               aria-label="...">Enchères remportées
                    </div>
                    <button type="submit">Valider</button>
                </form>
            </card>
            <card>
                <form action="">
                    <div class="form-check">
                        <input class="form-check-input position-static" type="radio" name="sell" id="sell"
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
                    <button type="submit">Valider</button>
                </form>
            </card>
        </c:if>
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
                        <p>Vendeur: ${lArticle.getPseudoUtilisateur()}</p>

                    </div>
                </div>
            </c:forEach>
    </body>
</html>