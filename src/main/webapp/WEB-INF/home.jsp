<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<head>
    <title>JSP - Hello World</title>
</head>
<body>

<ul class="nav ">
    <li class="nav-item">
        <a class="nav-link disabled">ENI Enchères</a>
    </li>
    <li class="nav-item justify-content-end">
        <a class="nav-link" href="#">S'inscrire</a>
    </li>
    <li class="nav-item justify-content-end">
        <a class="nav-link" href="#" tabindex="-1" aria-disabled="true">Se connecter</a>
    </li>
</ul>
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
<div class=" d-flex justify-content-around">
    <div class="card">
        <div class="card-header">
            <img src="https://www.rueducommerce.fr/medias/3c7295be321035dea924efd54375a286/p_1000x1000/f3-b-blue-lgpu-nc-mid-sideoff.jpg"
                 alt="" width="50px;">
        </div>
        <div class="card-body">
            <p>PC Gamer pour travailler</p>
            <p>Prix: 210 points</p>
            <p>Fin de l'enchère: 10/08/22</p>
            <p>Vendeur: jojo44</p>

        </div>
    </div>

    <div class="card">
        <div class="card-header">
            <img src="https://boulanger.scene7.com/is/image/Boulanger/bfr_overlay?$product_id=8713016009906_h_f_l_0&bfr_overlay?layer=comp&$t1=&$product_id=Boulanger/8713016009906_h_f_l_0&qlt=85,0&resMode=sharp2&op_usm=1.75,0.3,2,0&id=Q7OKO3&fmt=jpg&fit=constrain,1&wid=350&hei=350&qlt=85,0&resMode=sharp2&op_usm=1.75,0.3,2,0"
                 alt="" width="50px;">
        </div>
        <div class="card-body">
            <p>Cuiseur à riz</p>
            <p>Prix: 185 points</p>
            <p>Fin de l'enchère: 10/04/22</p>
            <p>Vendeur: ninja29</p>
        </div>
    </div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
        crossorigin="anonymous"></script>
</html>