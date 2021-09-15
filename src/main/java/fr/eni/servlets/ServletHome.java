package fr.eni.servlets;

import fr.eni.bll.ArticleVenduManager;
import fr.eni.bo.ArticleVendu;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/home")
public class ServletHome extends HttpServlet {
    public final ArticleVenduManager articleVenduManager = new ArticleVenduManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ArticleVendu> lesArticles = articleVenduManager.getAllArticles();
        request.setAttribute("lesArticles", lesArticles);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
