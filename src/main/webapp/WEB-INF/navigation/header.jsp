<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <body>
        <ul>
            <li>
                <!-- TODO : US 6003 + 9001
                <i class="fas fa-shopping-cart"></i>
                -->
                <a href="${pageContext.request.contextPath}/">ENI Enchères</a>
            </li>
            <c:choose>
                <c:when test="${isUserLoggedIn}">
                    <li>
                        <a href="${pageContext.request.contextPath}/myProfile?idUser=${userInfo.no_utilisateur}">Mon Profil</a>
                    </li>
                    <c:if test="${userInfo.administrateur}">
                        <li>
                            <a href="${pageContext.request.contextPath}/adminTools">Administration</a>
                        </li
                    </c:if>
                    <li>
                        <a href="${pageContext.request.contextPath}/">Déconnexion</a>
                    </li
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="${pageContext.request.contextPath}/signup">S'inscrire</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/login">Se connecter</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </body>
</html>
