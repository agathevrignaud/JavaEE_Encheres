<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fr.eni.messages.LecteurMessage" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${cookie['cookie_lang'].value}" />
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="${cookie['cookie_lang'].value}">
  <head>
    <title><fmt:message key="createauction.title"/></title>
  </head>
  <body>
    <%@include file="/WEB-INF/navigation/header.jsp"%>
    <h2><fmt:message key="createauction.title"/></h2>
    <!-- Print errors (if any) -->
    <c:if test="${!empty listeCodesErreur}">
      <fmt:message key="createauction.error"/>
      <ul>
        <c:forEach var="code" items="${listeCodesErreur}">
          <li>${LecteurMessage.getMessageErreur(code)}</li>
        </c:forEach>
      </ul>
    </c:if>
    <!-- Auction Details -->
    <form method="post" action="${pageContext.request.contextPath}/createAuction">
      <fieldset>
        <label for="articleName">
          <fmt:message key="createauction.article"/>
        </label>
        <input type="text" id="articleName" name="articleName"/><br/>
        <label for="articleDescription">
          <fmt:message key="createauction.description"/>
        </label>
        <input type="text" id="articleDescription" name="articleDescription"/><br/>
        <label for="idCategory">
          <fmt:message key="createauction.category"/>
        </label>
        <select id="category" name="category" onchange="getSelectedCategory(this);">
          <c:forEach var="c" items="${lesCategories}">
            <input type="hidden" name="selectedCategory" id="selectedCategory">
            <option id="idCategory" value="${c.numCategorie}">
                ${c.libelle}
            </option>
          </c:forEach>
        </select><br/>
        <label for="articleImg">
          <fmt:message key="createauction.articleImg"/>
        </label>
        <input type="file" accept="image/*" name="articleImg" id="articleImg" onchange="loadFile(event)"
               class="form-control">
        <br/>
        <label for="articleStartingPrice">
          <fmt:message key="createauction.startingPrice"/>
        </label>
        <input type="number" id="articleStartingPrice" min="10" step="10" name="articleStartingPrice"/><br/>
        <label for="auctionStartDate">
          <fmt:message key="createauction.auctionStartDate"/>
        </label>
        <input type="date" id="auctionStartDate" name="auctionStartDate"><br/>
        <label for="auctionEndDate">
          <fmt:message key="createauction.auctionEndDate"/>
        </label>
        <input type="date" id="auctionEndDate" name="auctionEndDate"><br/>
      </fieldset>
      <!-- Collect point -->
      <fieldset>
        <legend><fmt:message key="createauction.collectPoint"/></legend>
        <hr>
        <label for="streetName">
          <fmt:message key="createauction.streetName"/>
        </label>
        <input type="text" id="streetName" name="streetName" value="${lieuRetrait.rue}"/><br/>
        <label for="zipCode">
          <fmt:message key="createauction.zipCode"/>
        </label>
        <input type="text" id="zipCode" name="zipCode" minlength="5" maxlength="5" value="${lieuRetrait.codePostal}"/><br/>
        <label for="city">
          <fmt:message key="createauction.city"/>
        </label>
        <input type="text" id="city" name="city" value="${lieuRetrait.ville}"/><br/>
      </fieldset>

      <c:choose>
        <c:when test="${lArticle.etatVente.equals('P')}">
          <button type="submit" name="btnPressed" value="edit">
            <fmt:message key="createauction.save"/>
          </button>
          <button type="submit" name="btnPressed" value="cancelEdit">
            <fmt:message key="createauction.cancel"/>
          </button>
          <button type="submit" name="btnPressed" value="cancelAuction">
            <fmt:message key="createauction.cancel"/>
          </button>
        </c:when>
        <c:otherwise>
          <button type="submit" name="btnPressed" value="save">
            <fmt:message key="createauction.save"/>
          </button>
          <button type="submit" name="btnPressed" value="cancelCreation">
            <fmt:message key="createauction.cancel"/>
          </button>
        </c:otherwise>
      </c:choose>
    </form>
  </body>
</html>
<script>
function getSelectedCategory(sel) {
  document.getElementById("selectedLabel").value = sel.options[sel.selectedIndex].text;
}
</script>
