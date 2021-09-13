<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/utils/css/all.css">
        <!-- https://pro.fontawesome.com/releases/v5.10.0/css/all.css -->
    </head>
    <body>
        <ul>
            <c:choose>
                <c:when test="${isUserLoggedIn}">
                    <li>
                        <a href="${pageContext.request.contextPath}/home">
                            <i class="fas fa-shopping-cart" title="Accueil"></i>
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/myProfile?idUser=${userInfo.no_utilisateur}">Mon Profil</a>
                    </li>
                    <c:if test="${userInfo.administrateur}">
                        <li>
                            <a href="${pageContext.request.contextPath}/adminTools">Administration</a>
                        </li>
                    </c:if>
                    <li>
                        <a href="${pageContext.request.contextPath}/logout">Déconnexion</a>
                    </li
                </c:when>
                <c:otherwise>
                    <li>
                            <i class="fas fa-shopping-cart" title="Accueil"></i>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/signUp">S'inscrire</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/login">Se connecter</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </body>
</html>
