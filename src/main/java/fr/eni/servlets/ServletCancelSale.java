package fr.eni.servlets;

import fr.eni.bll.ArticleVenduManager;
import fr.eni.bll.RetraitManager;
import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Retrait;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;


@WebServlet(name = "ServletCancelSale", value = "/cancelsale")
public class ServletCancelSale extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/cancelSale.jsp");

        ArticleVenduManager articleVenduManager = new ArticleVenduManager();
        ArticleVendu articleVendu = articleVenduManager.selectArticleVendu(2);
        request.setAttribute("articleVendu", articleVendu);

        RetraitManager retraitManager = new RetraitManager();
        Retrait retrait = retraitManager.getRetraitById(2);
        request.setAttribute("retrait", retrait);

        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
