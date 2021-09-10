package fr.eni.servlets;

import fr.eni.bll.CategorieManager;
import fr.eni.bll.UtilisateurManager;
import fr.eni.bo.Categorie;
import fr.eni.bo.Utilisateur;
import java.io.IOException;
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

    /* TODO : Ajouter un doFilter pour que seul un admin puisse accéder à cette page !

    public  void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        vérifier si l'utilisateur qui navigue est connecté (isAuthenticated)
        &&
        vérifier s'il est administrateur (isAdmin)
        -> msg d'erreur "accès refusé : vous n'avez pas les droits nécessaires"

        filterChain.doFilter(request, response) ;
    }

    */


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loadUserList(request, utilisateurManager);
        loadCategoryList(request, categorieManager);

        switch (request.getServletPath()) {
            //  User Account Management
            case "/deactivateAccount":
            case "/reactivateAccount":
                utilisateurManager.updateUserAccountStatus(Integer.parseInt(request.getParameter("idUser")));
                loadUserList(request, utilisateurManager);
                break;
            case "/deleteAccount":
                utilisateurManager.deleteUser(Integer.parseInt(request.getParameter("idUser")));
                loadUserList(request, utilisateurManager);
                break;
            //  Category Management
            case "/displayEditCategory" :
                if (request.getParameter("btnPressed") != null) {
                    request.setAttribute("editBtnPressed", Boolean.valueOf(request.getParameter("btnPressed")));
                }
                loadCategoryList(request, categorieManager);
                break;
            case "/editCategory":
                try {
                    categorieManager.updateCategory(Integer.parseInt(request.getParameter("idCategory")),request.getParameter("newName"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                loadCategoryList(request, categorieManager);
                break;
            case "/deleteCategory":
                int nbrOfUses = categorieManager.getAllUses(Integer.parseInt(request.getParameter("idCategory")));
                if (nbrOfUses > 0) {
                    request.setAttribute("deleteCategoryError", true);
                } else {
                    categorieManager.deleteCategory(Integer.parseInt(request.getParameter("idCategory")));
                }
                loadCategoryList(request, categorieManager);
                break;
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/adminTools.jsp") ;
        requestDispatcher.forward(request, response) ;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("newCategory") != null) {
            try {
                categorieManager.addNewCategory(request.getParameter("newCategory"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            doGet(request, response);
        }
    }

    private void loadUserList(HttpServletRequest request, UtilisateurManager utilisateurManager) {
        List<Utilisateur> lesUtilisateurs = utilisateurManager.getAllUsers();
        request.setAttribute("lesUtilisateurs", lesUtilisateurs);
    }

    private void loadCategoryList(HttpServletRequest request, CategorieManager categorieManager) {
        List<Categorie> lesCategories = categorieManager.getAllCategories();
        request.setAttribute("lesCategories", lesCategories);
    }

}








