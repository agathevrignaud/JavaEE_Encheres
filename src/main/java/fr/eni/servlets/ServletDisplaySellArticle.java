package fr.eni.servlets;

import fr.eni.bll.ArticleVenduManager;
import fr.eni.bll.BLLException;
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
import java.time.LocalDate;
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
            final String NOT_STARTED = "A";
            final String IN_PROGRESS = "E";

            //  TODO : rentre l'id article dynamique (sur 2 en dur)
            // request.getParameter(idArticle)
            int idArticle = Integer.parseInt(request.getParameter("idArticle"));
            ArticleVendu lArticle = articleVenduManager.getArticleById(idArticle);
            request.setAttribute("lArticle", lArticle);

            Enchere lEnchere = enchereManager.getHighestBidByIdArticle(idArticle);
            request.setAttribute("lEnchere", lEnchere);

            List<Enchere> lesEncherisseurs = enchereManager.getAllBidsByIdArticle(idArticle);
            request.setAttribute("lesEncherisseurs", lesEncherisseurs);

            if (IN_PROGRESS.equals(lArticle.getEtatVente())) {
                request.setAttribute("highestBidder", lEnchere.getlUtilisateur());
                request.setAttribute("auctionInProgress", true);
                request.setAttribute("auctionEditable", false);
            } else {
                if (NOT_STARTED.equals(lArticle.getEtatVente())) {
                    request.setAttribute("auctionInProgress", false);
                    request.setAttribute("auctionEditable", true);
                } else {
                    request.setAttribute("auctionInProgress", false);
                    request.setAttribute("auctionEditable", false);
                }
            }
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/displaySellDetail.jsp");
            rd.forward(request, response);
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            List<Integer> listeCodesErreur = new ArrayList<>();
            HttpSession laSession = request.getSession();
            int newBid = Integer.parseInt(request.getParameter("newBid"));
            int idArticle = Integer.parseInt(request.getParameter("idArticle"));
            Enchere lEnchere = enchereManager.getHighestBidByIdArticle(idArticle);
            int previousHighestBid = lEnchere.getMontantEnchere();
            Utilisateur newBidder = (Utilisateur) laSession.getAttribute("userInfo");

            checkBid(newBid, previousHighestBid, newBidder.getCredit(), listeCodesErreur);

            if (listeCodesErreur.size() > 0) {
                request.setAttribute("errorOnBid", true);
                request.setAttribute("listeCodesErreur", listeCodesErreur);
                doGet(request, response);
            } else {
                try {
                    enchereManager.addNewEnchere(
                            newBidder,
                            lEnchere.getlArticle(),
                            LocalDateTime.now(),
                            Integer.parseInt(request.getParameter("bid"))
                    );
                    articleVenduManager.updateArticlePrice(newBid, idArticle);
                    utilisateurManager.updateUserCredit(-newBid, newBidder.getNumUtilisateur());
                    Utilisateur previousHighestBidder = lEnchere.getlUtilisateur();
                    utilisateurManager.updateUserCredit(previousHighestBid, previousHighestBidder.getNumUtilisateur());
                    request.setAttribute("successfulBid", true);
                    doGet(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    private int checkBid(int newBid, int previousHighestBid, int credit, List<Integer> listeCodesErreur) {
        if (!(newBid > previousHighestBid)) {
            listeCodesErreur.add(CodesResultatServlets.USER_BID_TOO_LOW);
        }
        if (!(credit - previousHighestBid >= 0)) {
            listeCodesErreur.add(CodesResultatServlets.NOT_ENOUGH_TO_BID);
        }
        return newBid ;
    }

}
