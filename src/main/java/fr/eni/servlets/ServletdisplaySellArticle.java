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

@WebServlet(value = "/articleDetails")
public class ServletdisplaySellArticle extends HttpServlet {
        public static ArticleVenduManager articleVenduManager = new ArticleVenduManager();

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            //  TODO : rentre l'id article dynamique (sur 2 en dur)
            // request.getParameter(idArticle)
            ArticleVendu lArticle = articleVenduManager.getArticleById(2);
            request.setAttribute("lArticle", lArticle);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/displaySellDetail.jsp");
            rd.forward(request, response);
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request, response);
        }

}
