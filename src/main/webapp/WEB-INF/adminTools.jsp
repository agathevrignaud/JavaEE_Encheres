<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>Panneau d'Administration</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/utils/css/all.css">
    </head>
    <body>
        <table>
            <thead>
                <tr>
                    <th>Liste des Utilisateurs</th>
                </tr>
            </thead>
            <c:forEach var="u" items="${lesUtilisateurs}">
            <tbody>
                <tr>
                    <!-- infos utilisateur -->
                    <td>
<<<<<<< HEAD
                        <a href="${pageContext.request.contextPath}/myProfile?idUser=${u.no_utilisateur}"><i class="fas fa-user" title="Voir profil"></i></a>
=======
                        <a href="${pageContext.request.contextPath}/displayProfile?idUser=${u.no_utilisateur}"><i class="fas fa-user" title="Voir profil"></i></a>
>>>>>>> 25203593837224b2dbe97f632ad8ec1a51ff5e27
                    </td>
                    <td>${u.nom}</td>
                    <td>${prenom}</td>
                    <td>${email}</td>
                    <!-- TODO : Ajouter d'autres infos ? -->
                    <td>
                    <c:choose>
                        <c:when test="${u.compteActif}">
                            <a href="${pageContext.request.contextPath}/deactivateAccount?idUser=${u.no_utilisateur}">
                                <i class="fas fa-user-minus" title="Désactiver le compte"></i>
                            </a>
                        </c:when>
                        <c:when test="${!u.compteActif}">
                            <a href="${pageContext.request.contextPath}/reactivateAccount?idUser=${u.no_utilisateur}">
                                <i class="fas fa-user-plus" title="Réactiver le compte"></i>
                            </a>
                        </c:when>
                    </c:choose>
                            <a href="${pageContext.request.contextPath}/deleteAccount?idUser=${u.no_utilisateur}">
                                <i class="fas fa-user-times" title="Supprimer le compte"></i>
                            </a>
                    </td>
                </tr>
            </tbody>
            </c:forEach>
        </table>

        <table>
            <thead>
            <tr>
                <th>Liste des Catégories</th>
            </tr>
            </thead>
            <c:forEach var="c" items="${lesCategories}">
                <tbody>
                <tr>
                    <!-- infos Catégorie -->
                    <td>
                            ${c.libelle}
                    </td>
                    <!-- Actions sur une Catégorie -->
                    <td>
                        <a>

                        </a>
                        <i class="fas fa-pen" title="Editer le Nom"></i>
                        <!-- TODO : à faire apparaître/disparaître au clic sur le crayon -->
                        <c:if test="${true}">
                            <label for="newName">Nouveau nom : </label><input type="text" name="newName" id="newName"/>
                            <a href="${pageContext.request.contextPath}/editCategory?idCategory=${c.no_categorie}">
                                <i class="fas fa-check"></i>
                            </a>
                        </c:if>
                            <a href="${pageContext.request.contextPath}/deleteCategory?idCategory=${c.no_categorie}">
                                <i class="fas fa-minus" title="Supprimer la catégorie"></i>
                            </a>
                    </td>
                </tr>
                </tbody>
            </c:forEach>
        </table>
        <c:if test="${deleteCategoryError}">
            Impossible de supprimer une catégorie en cours d'utilisation !
        </c:if>

        <form method="post" action="${pageContext.request.contextPath}/createNewCategory">
                <label for="newCategory">Ajouter une nouvelle catégorie :</label>
                <input type="text" name="newCategory" id="newCategory"/>
                <button type="submit">Ajouter</button>
        </form>
    </body>
</html>
