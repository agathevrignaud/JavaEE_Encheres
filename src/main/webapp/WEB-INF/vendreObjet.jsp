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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <h2>ENI-Enchères</h2>
    </nav>
</header>
<body>
<div class="col-md-12 row">
    <div class="col-md-6">
        <card>
            <div class="card-header d-flex justify-content-center" style="background-color: #ffff">
                <h2>Nouvelle vente</h2>
            </div>
            <div class="card-body d-flex justify-content-center">
                <form action="VendreObjet" method="post">
                    <div class="form-group">
                        <label for="nomArticle">Article : </label>
                        <input type="text" id="nomArticle" name="nomArticle" class="form-control"/>
                        <br/>
                        <label for="descArticle">Description : </label>
                        <input type="text" id="descArticle" name="descArticle" class="form-control"/>
                        <br/>
                        <label for="idCategory">Catégorie : </label>
                        <select id="idCategory" name="categories" class="form-control">
                            <c:forEach items="${listeCategorie}" var="categorie">
                                <option id="${categorie.libelle}" value="${categorie.no_categorie}">
                                        ${categorie.libelle}
                                </option>
                            </c:forEach>
                        </select>
                        <br/>
                        <label for="file">Photo de l'article : </label>
                        <input type="file" accept="image/*" name="image" id="file" onchange="loadFile(event)"
                               class="form-control">
                        <br/>
                        <label for="prixArticle">Mise à prix : </label>
                        <input type="number" id="prixArticle" min="10" step="10" name="prixArticle"
                               class="form-control"/>
                        <br/>
                        <label for="debutEnchere">Debut de l'enchère : </label>
                        <input type="date" id="debutEnchere" name="debutEnchere" class="form-control">
                        <br/>
                        <label for="finEnchere">Fin de l'enchère : </label>
                        <input type="date" id="finEnchere" name="finEnchere" class="form-control">
                        <br/>
                        <fieldset>
                            <legend>Retrait</legend>
                            <hr>
                            <label for="rue">Rue : </label>
                            <input type="text" id="rue" name="rue" class="form-control"/>
                            <br/>
                            <label for="cp">Code postal : </label>
                            <input type="text" id="cp" minlength="5" maxlength="5" name="cp" class="form-control"/>
                            <br/>
                            <label for="ville">Ville : </label>
                            <input type="text" id="ville" name="ville" class="form-control"/>
                            <br/>
                        </fieldset>

                        <input type="submit" value="Enregistrer" class="btn btn-outline-primary">
                        <a href="home.jsp">
                            <button value="" class="btn btn-outline-danger">Annuler</button>
                        </a>
                    </div>
                </form>
            </div>
        </card>
    </div>
    <div class="col-md-6 d-flex justify-content-center">
        <card>
            <div class="card-body">
                <p><img id="output" width="400"/></p>
            </div>
        </card>
    </div>
</div>
</body>
<script>
    var loadFile = function (event) {
        var image = document.getElementById('output');
        image.src = URL.createObjectURL(event.target.files[0]);
    };
</script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</html>
