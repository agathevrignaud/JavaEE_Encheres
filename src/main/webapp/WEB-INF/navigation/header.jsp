<%--
  Created by IntelliJ IDEA.
  User: rsaintalme2021
  Date: 13/09/2021
  Time: 09:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
    <ul class="nav ">
        <li class="nav-item">
            <a class="nav-link disabled">ENI Enchères</a>
        </li>

        <c:choose>
            <c:when test="${isUserLoggedIn}">
                <li class="nav-item justify-content-end">
                    <a class="nav-link" href="#">Enchères</a>
                </li>
                <li class="nav-item justify-content-end">
                    <a class="nav-link" href="#" tabindex="-1" aria-disabled="true">Vendre un article</a>
                </li>
                <li class="nav-item justify-content-end">
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/myProfile?idUser=${userInfo.no_utilisateur}"
                       tabindex="-1" aria-disabled="true">Mon profil</a>
                </li>
                <li class="nav-item justify-content-end">
                    <a class="nav-link" href="${pageContext.request.contextPath}/" tabindex="-1" aria-disabled="true">Deconnexion</a>
                </li>
                <c:if test="${userInfo.administrateur}">
                    <li>
                        <a class="nav-link" href="adminTools" tabindex="-1" aria-disabled="true">Administration</a>
                    </li>
                </c:if>
            </c:when>
            <c:otherwise>
                <li class="nav-item justify-content-end">
                    <a class="nav-link" href="signup">S'inscrire</a>
                </li>
                <li class="nav-item justify-content-end">
                    <a class="nav-link" href="login" tabindex="-1" aria-disabled="true">Se connecter</a>
                </li>
            </c:otherwise>
        </c:choose>

    </ul>
</header>