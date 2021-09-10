<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <body>
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/">ENI Enchères</a>
            </li>
            <c:choose>
                <c:when test="${isUserLoggedIn}">
                    <li>
                        <a href="${pageContext.request.contextPath}/myProfile?idUser=${userInfo.no_utilisateur}">Mon Profil</a>
                    </li>
                    <li>
                        <a href="">Déconnexion</a>
                    </li
                        <c:if test="${userInfo.administrateur}">
                            <li>
                                <a href="${pageContext.request.contextPath}/adminTools">Administration</a>
                            </li
                        </c:if>
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
