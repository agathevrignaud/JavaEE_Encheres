package fr.eni.servlets;

import fr.eni.bll.ArticleVenduManager;
import fr.eni.bll.BLLException;
import fr.eni.bll.EnchereManager;
import fr.eni.bll.UtilisateurManager;
import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Enchere;
import fr.eni.bo.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/editMyProfile")
public class ServletEditProfile extends HttpServlet {
    private static final UtilisateurManager utilisateurManager = new UtilisateurManager();
    private static final ArticleVenduManager articleVenduManager = new ArticleVenduManager();
    private static final EnchereManager enchereManager = new EnchereManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idUser = Integer.parseInt(request.getParameter("idUser"));
        Utilisateur lUtilisateur = null;
        try {
            lUtilisateur = utilisateurManager.getUserById(idUser);
        } catch (BLLException e) {
            e.printStackTrace();
        }
        request.setAttribute("userInfo", lUtilisateur);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/editProfile.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        switch (request.getParameter("btnPressed")) {
            case "save":
                String pwd = "" ;
                if (request.getParameter("newPwd").length() == 0 || request.getParameter("newPwdConfirmed").length() == 0) {
                    pwd = request.getParameter("currentPwd");
                }
                try {
                    utilisateurManager.updateUserData(
                            Integer.parseInt(request.getParameter("idUser")),
                            request.getParameter("username"),
                            request.getParameter("surname"),
                            request.getParameter("firstName"),
                            request.getParameter("email"),
                            request.getParameter("phoneNumber"),
                            request.getParameter("streetName"),
                            request.getParameter("zipCode"),
                            request.getParameter("city"),
                            pwd,
                            request.getParameter("newPwdConfirmed")
                    );
                } catch (Exception e) {
                    System.out.println("Erreur lors de la màj des données utilisateur");
                    e.printStackTrace();
                }
                doGet(request, response);
                break;
            case "delete":
                // TODO : Ajouter une validation "êtes-vous sûr" etc etc
                try {
                    int idUser = Integer.parseInt(request.getParameter("idUser"));
                    reimburseAllBidders(idUser);
                    utilisateurManager.deleteUser(idUser);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                HttpSession laSession = request.getSession();
                laSession.setAttribute("isUserLoggedIn", false );
                laSession.setAttribute("userInfo", null);
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
                rd.forward(request, response);
                break;
        }
    }

    private void reimburseAllBidders(int idUser) {
        List<ArticleVendu> lesArticles = articleVenduManager.getAllArticlesByUser(idUser);
        for (ArticleVendu unArticle : lesArticles) {
            Enchere lEnchere = enchereManager.getHighestBidByIdArticle(unArticle.getNumArticle());
            try {
                utilisateurManager.updateUserCredit(unArticle.getPrixVente(), lEnchere.getlUtilisateur().getNumUtilisateur());
            } catch (BLLException e) {
                e.printStackTrace();
            }
        }
    }
}
