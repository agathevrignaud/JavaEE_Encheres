package fr.eni.servlets;

import fr.eni.bll.ArticleVenduManager;
import fr.eni.bll.EnchereManager;
import fr.eni.bll.UtilisateurManager;
import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Enchere;
import fr.eni.bo.Utilisateur;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet(
        urlPatterns= {
            "/articleDetails",
            "/makeABid"
        })
public class ServletDisplaySellArticle extends HttpServlet {
        public static ArticleVenduManager articleVenduManager = new ArticleVenduManager();
        public static EnchereManager enchereManager = new EnchereManager();
        public static UtilisateurManager utilisateurManager = new UtilisateurManager();

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            //  TODO : rentre l'id article dynamique (sur 2 en dur)
            // request.getParameter(idArticle)
            int idArticle = 2;
            ArticleVendu lArticle = articleVenduManager.getArticleById(idArticle);
            request.setAttribute("lArticle", lArticle);
            Enchere lEnchere = enchereManager.getHighestBidByIdArticle(idArticle);
            request.setAttribute("lEnchere", lEnchere);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/displaySellDetail.jsp");
            rd.forward(request, response);
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            List<Integer> listeCodesErreur = new ArrayList<>();
            HttpSession laSession = request.getSession();
            int bid = Integer.parseInt(request.getParameter("bid"));
            int highestBid = Integer.parseInt(request.getParameter("highestBid"));
            int idArticle = Integer.parseInt(request.getParameter("idArticle"));
            Utilisateur lUtilisateur = (Utilisateur) laSession.getAttribute("userInfo");

            checkBid(bid, highestBid, lUtilisateur.getCredit(), listeCodesErreur);

            Enchere lEnchere = enchereManager.getHighestBidByIdArticle(idArticle);
            int previousHighestBid = lEnchere.getMontantEnchere();
            int idPreviousHighestBidder = lEnchere.getNo_utilisateur();

            if (listeCodesErreur.size() > 0) {
                request.setAttribute("errorOnBid", true);
                request.setAttribute("listeCodesErreur", listeCodesErreur);
                doGet(request, response);
            } else {
                try {
                    enchereManager.addNewEnchere(
                            lUtilisateur.getNo_utilisateur(),
                            Integer.parseInt(request.getParameter("idArticle")),
                            LocalDateTime.now(),
                            Integer.parseInt(request.getParameter("bid"))
                    );
                    articleVenduManager.updateArticlePrice(bid, idArticle);
                    utilisateurManager.updateUserCredit(-bid, lUtilisateur.getNo_utilisateur());
                    utilisateurManager.updateUserCredit(previousHighestBid, idPreviousHighestBidder);
                    request.setAttribute("successfulBid", true);
                    doGet(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

    private int checkBid(int bid, int highestBid, int credit, List<Integer> listeCodesErreur) {
        if (!(bid > highestBid)) {
            listeCodesErreur.add(CodesResultatServlets.USER_BID_TOO_LOW);
        }
        if (!(credit - bid >= 0)) {
            listeCodesErreur.add(CodesResultatServlets.NOT_ENOUGH_TO_BID);
        }
        return bid ;
    }

}
