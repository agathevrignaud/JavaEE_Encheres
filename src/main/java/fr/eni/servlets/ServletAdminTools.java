package fr.eni.servlets;

import fr.eni.bll.*;
import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Categorie;
import fr.eni.bo.Enchere;
import fr.eni.bo.Utilisateur;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        urlPatterns= {
                "/adminTools",
                "/deactivateAccount",
                "/reactivateAccount",
                "/deleteAccount",
                "/displayEditCategory",
                "/editCategory",
                "/deleteCategory",
                "/createNewCategory"
        })
public class ServletAdminTools extends HttpServlet {
    private static final UtilisateurManager utilisateurManager = new UtilisateurManager();
    private static final ArticleVenduManager articleVenduManager = new ArticleVenduManager();
    private static final EnchereManager enchereManager = new EnchereManager();
    private static final CategorieManager categorieManager = new CategorieManager();
    final String CANCELLED = "A";
    final String NOT_STARTED = "P";
    final String IN_PROGRESS = "E";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Integer> listeCodesErreur = new ArrayList<>();
        loadUserList(request, listeCodesErreur);
        loadCategoryList(request, listeCodesErreur);

        if (listeCodesErreur.size() > 0) {
            Collections.sort(listeCodesErreur);
            request.setAttribute("listeCodesErreur",listeCodesErreur);
            doGet(request, response);
        } else {
            switch (request.getServletPath()) {
                //  User Account Management
                case "/deactivateAccount":
                    try {
                        int idUser = Integer.parseInt(request.getParameter("idUser"));
                        // Cancel all sales & bids by user, not reimboursed of his credit (?)
                        utilisateurManager.updateUserAccountStatus(idUser);
                        enchereManager.cancelAllBidsByUser(idUser);
                        // Bidders reimboursed on cancelled auctions
                        for(ArticleVendu unArticle : articleVenduManager.getAllArticlesByUser(idUser)) {
                            unArticle.setEtatVente(CANCELLED);
                            Enchere lEnchere = enchereManager.getHighestBidByIdArticle(unArticle.getNumArticle());
                            utilisateurManager.updateUserCredit(unArticle.getPrixVente(), lEnchere.getlUtilisateur().getNumUtilisateur());
                            enchereManager.cancelAllBidsForAuction(unArticle.getNumArticle());
                            unArticle.setPrixVente(unArticle.getMiseAPrix());
                        }
                    } catch (BLLException e) {
                        e.printStackTrace();
                        listeCodesErreur.add(CodesResultatServlets.DEACTIVATE_ACCOUNT_ERROR);
                        request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
                    }
                    loadUserList(request, listeCodesErreur);
                    break;
                case "/reactivateAccount":
                    try {
                        int idUser = Integer.parseInt(request.getParameter("idUser"));
                        utilisateurManager.updateUserAccountStatus(idUser);
                        for(ArticleVendu unArticle : articleVenduManager.getAllArticlesByUser(idUser)) {
                            if (LocalDate.now().isAfter(unArticle.getDateFinEnchere())) {
                                articleVenduManager.updateAuctionStatus(NOT_STARTED, unArticle.getNumArticle());
                            }
                            if (LocalDate.now().isAfter(unArticle.getDateDebutEnchere())) {
                                articleVenduManager.updateAuctionStatus(IN_PROGRESS, unArticle.getNumArticle());
                            }
                        }
                    } catch (BLLException e) {
                        e.printStackTrace();
                        listeCodesErreur.add(CodesResultatServlets.REACTIVATE_ACCOUNT_ERROR);
                        request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
                    }
                    loadUserList(request, listeCodesErreur);
                    break;
                case "/deleteAccount":
                    try {
                        int idUser = Integer.parseInt(request.getParameter("idUser"));
                        reimburseAllBidders(idUser);
                        utilisateurManager.deleteUser(idUser);
                    } catch (BLLException e) {
                        e.printStackTrace();
                        listeCodesErreur.add(CodesResultatServlets.DELETE_ACCOUNT_ERROR);
                        request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
                    }
                    loadUserList(request, listeCodesErreur);
                    break;
                //  Category Management
                case "/displayEditCategory" :
                    if (request.getParameter("btnPressed") != null) {
                        request.setAttribute("editBtnPressed", Boolean.valueOf(request.getParameter("btnPressed")));
                    }
                    loadCategoryList(request, listeCodesErreur);
                    break;
                case "/editCategory":
                    try {
                        int idCategory = Integer.parseInt(request.getParameter("idCategory"));
                        String newCategoryName = checkCategoryName(request, listeCodesErreur);
                        if (listeCodesErreur.size() > 0) {
                            request.setAttribute("listeCodesErreur",listeCodesErreur);
                            doGet(request, response);
                        }
                        categorieManager.updateCategory(idCategory,newCategoryName);
                    } catch (BLLException e) {
                        e.printStackTrace();
                        listeCodesErreur.add(CodesResultatServlets.EDIT_CATEGORY_ERROR);
                        request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
                    }
                    loadCategoryList(request, listeCodesErreur);
                    break;
                case "/deleteCategory":
                    int nbrOfUses = 0;
                    try {
                        nbrOfUses = categorieManager.getAllUses(Integer.parseInt(request.getParameter("idCategory")));
                    } catch (BLLException e) {
                        e.printStackTrace();
                        listeCodesErreur.add(CodesResultatServlets.GET_CATEGORY_USES_ERROR);
                        request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
                    }
                    if (nbrOfUses > 0) {
                        request.setAttribute("deleteCategoryError", true);
                    } else {
                        try {
                            categorieManager.deleteCategory(Integer.parseInt(request.getParameter("idCategory")));
                        } catch (BLLException e) {
                            e.printStackTrace();
                            listeCodesErreur.add(CodesResultatServlets.DELETE_CATEGORY_ERROR);
                            request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
                        }
                    }
                    loadCategoryList(request, listeCodesErreur);
                    break;
            }
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/adminTools.jsp") ;
        requestDispatcher.forward(request, response) ;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Integer> listeCodesErreur = new ArrayList<>();
        if (request.getParameter("newCategory") != null) {
            try {
                categorieManager.addNewCategory(request.getParameter("newCategory"));
            } catch (BLLException e) {
                e.printStackTrace();
                listeCodesErreur.add(CodesResultatServlets.CREATE_CATEGORY_ERROR);
                request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
            }
            doGet(request, response);
        }
    }

    private void loadUserList(HttpServletRequest request, List<Integer> listeCodesErreur) {
        List<Utilisateur> lesUtilisateurs;
        try {
            lesUtilisateurs = utilisateurManager.getAllUsers();
            request.setAttribute("lesUtilisateurs", lesUtilisateurs);
        } catch (BLLException bllException) {
            listeCodesErreur.add(CodesResultatServlets.LOAD_USERS_ERROR);
        }
    }

    private void loadCategoryList(HttpServletRequest request, List<Integer> listeCodesErreur) {
        try {
            List<Categorie> lesCategories = categorieManager.getAllCategories();
            request.setAttribute("lesCategories", lesCategories);
        } catch (BLLException bllException) {
            listeCodesErreur.add(CodesResultatServlets.LOAD_CATEGORIES_ERROR);
        }
    }

    private String checkCategoryName(HttpServletRequest request, List<Integer> listeCodesErreur) {
        String categoryName;
        categoryName = request.getParameter("newName");
        if(categoryName == null || categoryName.trim().equals("")) {
            listeCodesErreur.add(CodesResultatServlets.CATEGORY_NAME_REQUIRED);
        }
        return categoryName;
    }

    private void reimburseAllBidders(int idUser) {
        List<ArticleVendu> lesArticles = null;
        try {
            lesArticles = articleVenduManager.getAllArticlesByUser(idUser);
        } catch (BLLException e) {
            e.printStackTrace();
            //  TODO : à gérer mieux
        }
        for (ArticleVendu unArticle : lesArticles) {
            try {
                Enchere lEnchere = enchereManager.getHighestBidByIdArticle(unArticle.getNumArticle());
                utilisateurManager.updateUserCredit(unArticle.getPrixVente(), lEnchere.getlUtilisateur().getNumUtilisateur());
            } catch (BLLException e) {
                e.printStackTrace();
            }
        }
    }


}








