<%--
  Created by IntelliJ IDEA.
  User: rsaintalme2021
  Date: 10/09/2021
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%
    if (request.getSession(false) != null && request.getSession(false).getAttribute("userIsAuthenticated") == null) {
        response.sendRedirect(request.getContextPath() + "/");
    }
%>