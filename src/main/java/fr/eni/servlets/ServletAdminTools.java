package fr.eni.servlets;

import fr.eni.bll.BLLException;
import fr.eni.bll.CategorieManager;
import fr.eni.bll.UtilisateurManager;
import fr.eni.bo.Categorie;
import fr.eni.bo.Utilisateur;
import java.io.IOException;
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
    private static final CategorieManager categorieManager = new CategorieManager();

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
                case "/reactivateAccount":
                    try {
                        utilisateurManager.updateUserAccountStatus(Integer.parseInt(request.getParameter("idUser")));
                    } catch (BLLException e) {
                        e.printStackTrace();
                        listeCodesErreur.add(CodesResultatServlets.UPDATE_ACCOUNT_STATUS_ERROR);
                        request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
                    }
                    loadUserList(request, listeCodesErreur);
                    break;
                case "/deleteAccount":
                    try {
                        utilisateurManager.deleteUser(Integer.parseInt(request.getParameter("idUser")));
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
                        categorieManager.updateCategory(Integer.parseInt(request.getParameter("idCategory")),request.getParameter("newName"));
                    } catch (BLLException e) {
                        e.printStackTrace();
                        listeCodesErreur.add(CodesResultatServlets.EDIT_CATEGORY_ERROR);
                        request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
                    }
                    loadCategoryList(request, listeCodesErreur);
                    break;
                case "/deleteCategory":
                    int nbrOfUses = categorieManager.getAllUses(Integer.parseInt(request.getParameter("idCategory")));
                    if (nbrOfUses > 0) {
                        request.setAttribute("deleteCategoryError", true);
                    } else {
                        categorieManager.deleteCategory(Integer.parseInt(request.getParameter("idCategory")));
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
        List<Utilisateur> lesUtilisateurs = null;
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

}








