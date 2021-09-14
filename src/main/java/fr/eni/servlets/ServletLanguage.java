package fr.eni.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/language")
public class ServletLanguage extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletLanguage() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("lang")!=null) {
            Cookie cLang = new Cookie("cookie_lang", request.getParameter("lang").trim());
            cLang.setMaxAge(60 * 60 * 24 * 15); // 15 jours
            response.addCookie(cLang);
        }
        response.sendRedirect(request.getHeader("referer"));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
