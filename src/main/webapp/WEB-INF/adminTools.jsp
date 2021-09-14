<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages"/>

<html>
    <head>
        <title>Panneau d'Administration</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/utils/css/all.css">
    </head>
    <body>
        <%@include file="/WEB-INF/navigation/header.jsp" %>
        <table>
            <thead>
                <tr>
                    <th><fmt:message key="admin.userList"/></th>
                </tr>
            </thead>
            <c:forEach var="u" items="${lesUtilisateurs}">
            <tbody>
                <tr>
                    <!-- infos utilisateur -->
                    <td>
                        <a href="${pageContext.request.contextPath}/myProfile?idUser=${u.no_utilisateur}">
                            <i class="fas fa-user" title="<fmt:message key="admin.seeProfile"/>"></i>
                        </a>
                    </td>
                    <td>${u.nom}</td>
                    <td>${u.prenom}</td>
                    <td>${u.email}</td>
                    <td>
                    <c:choose>
                        <c:when test="${u.compteActif}">
                            <a href="${pageContext.request.contextPath}/deactivateAccount?idUser=${u.no_utilisateur}">
                                <i class="fas fa-user-minus" title="<fmt:message key="admin.deactivateAccount"/>"></i>
                            </a>
                        </c:when>
                        <c:when test="${!u.compteActif}">
                            <a href="${pageContext.request.contextPath}/reactivateAccount?idUser=${u.no_utilisateur}">
                                <i class="fas fa-user-plus" title="<fmt:message key="admin.reactivateAccount"/>"></i>
                            </a>
                        </c:when>
                    </c:choose>
                            <a href="${pageContext.request.contextPath}/deleteAccount?idUser=${u.no_utilisateur}">
                                <i class="fas fa-user-times" title="<fmt:message key="admin.deleteAccount"/>"></i>
                            </a>
                    </td>
                </tr>
            </tbody>
            </c:forEach>
        </table>

        <table>
            <thead>
            <tr>
                <th><fmt:message key="admin.categoryList"/></th>
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
                        <a href="${pageContext.request.contextPath}/displayEditCategory?btnPressed=${!editBtnPressed}">
                            <i class="fas fa-pen" title="<fmt:message key="admin.editCategoryName"/>"></i>
                        </a>
                        <c:if test="${editBtnPressed}">
                            <form method="get" action="${pageContext.request.contextPath}/editCategory">
                                <input type="hidden" name="idCategory" value="${c.no_categorie}">
                                <label for="newName">Nouveau nom</label>
                                <input type="text" name="newName" id="newName"/>
                                <button type="submit">
                                    <i class="fas fa-check" title="<fmt:message key="admin.confirm"/>"></i>
                                </button>
                            </form>
                            </a>
                        </c:if>
                            <a href="${pageContext.request.contextPath}/deleteCategory?idCategory=${c.no_categorie}">
                                <i class="fas fa-minus" title="<fmt:message key="admin.deleteCategory"/>"></i>
                            </a>
                    </td>
                </tr>
                </tbody>
            </c:forEach>
        </table>
        <c:if test="${deleteCategoryError}">
            <fmt:message key="admin.deleteCategoryError"/>
        </c:if>

        <form method="post" action="${pageContext.request.contextPath}/createNewCategory">
                <label for="newCategory"><fmt:message key="admin.addNewCategory"/></label>
                <input type="text" name="newCategory" id="newCategory"/>
                <button type="submit"><fmt:message key="admin.add"/></button>
        </form>
    </body>
</html>
