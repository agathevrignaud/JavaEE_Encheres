<%--
  Created by IntelliJ IDEA.
  User: rsaintalme2021
  Date: 07/09/2021
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>
        Vendre objet aux enchères
    </title>
</head>
    <header>
        <h2>ENI-Enchères</h2>
        <h2>Nouvelle vente</h2>
    </header>
<body>
    <form action="VendreObjet" method="post">
        <label for="nomArticle">Article : </label> <input type="text" id="nomArticle" name="nomArticle"/><br/>
        <label for="descArticle">Description : </label> <input type="text" id="descArticle" name="descArticle"/><br/>
        <label for="idCategory">Catégorie : </label>
        <select id="idCategory" name="categories" >
            <c:forEach items="${listeCategorie}" var="categorie" >
                <option id="${categorie.libelle} value="${categorie.no_categorie}">
                    ${categorie.libelle}
                </option>
            </c:forEach>
        </select>
        <br/>
        <label for="photoArticle">Photo de l'article : </label> <input type="button" id="photoArticle"
                                                                       value="UPLOADER"><br/>
        <label for="prixArticle">Mise à prix : </label><input type="number" id="prixArticle" min="10" step="10" name="prixArticle"/><br/>
        <label for="debutEnchere">Debut de l'enchère : </label><input type="date" id="debutEnchere" name="debutEnchere"><br/>
        <label for="finEnchere">Fin de l'enchère : </label><input type="date" id="finEnchere" name="finEnchere"><br/>
        <fieldset>
            <legend>Retrait</legend>
            <label for="rue">Rue : </label> <input type="text" id="rue" name="rue"/><br/>
            <label for="cp">Code postal : </label> <input type="text" id="cp" minlength="5" maxlength="5" name="cp"/><br/>
            <label for="ville">Ville : </label> <input type="text" id="ville" name="ville"/><br/>
        </fieldset>

        <input type="submit" value="Enregistrer">
        <button value="">Annuler</button>
    </form>
</body>
</html>
