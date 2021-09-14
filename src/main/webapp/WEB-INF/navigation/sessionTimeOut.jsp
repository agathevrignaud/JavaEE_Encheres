<html>
    <%
        int timeOut = session.getMaxInactiveInterval();
        response.setHeader("Refresh", timeOut  + "; URL=index.jsp");
    %>
</html>
