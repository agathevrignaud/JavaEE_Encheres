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
                        ${u.nom} ${u.prenom}
                    </td>
                    <td>
                        <!-- Selon compteActif vrai ou faux-->
                        <c:choose>
                            <c:when test="${u.compteActif}">
                                <i class="fas fa-user-minus" title="Désactiver le compte"></i>
                            </c:when>
                            <c:when test="${!u.compteActif}">
                                <i class="fas fa-user-plus" title="Réactiver le compte"></i>
                            </c:when>
                        </c:choose>
                    </td>
                </tr>
            </tbody>
            </c:forEach>

        </table>


        <p>Liste des catégories</p>


    </body>
</html>
