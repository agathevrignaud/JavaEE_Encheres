<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Acheter des crédits</title>
</head>
<body>
    <%@include file="/WEB-INF/navigation/header.jsp" %>
    <h3>Achetez des crédits pour votre compte, ${firstName} !</h3>
    <form method="post" action="${pageContext.request.contextPath}/creditShop">
        <label></label><input type="number" min="0" max="500" step="10" name="creditsBought">
        <input type="hidden" value="${idUser}" name="idUser">
        <button type="submit">Valider</button>
    </form>
    <c:if test="${newBalance != null}">
        <c:out value="Votre nouveau solde de crédits est ${newBalance}"/>
    </c:if>
</body>
</html>
