package fr.eni.servlets;

import fr.eni.bll.CategorieManager;
import fr.eni.bll.UtilisateurManager;
import fr.eni.bo.Categorie;
import fr.eni.bo.Utilisateur;
import org.apache.coyote.Request;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ServletTestPoolConnexion
 */
@WebServlet(
        urlPatterns= {
                "/adminTools",
                "/deactivateAccount",
                "/reactivateAccount",
                "/deleteAccount",
                "/editCategory",
                "/deleteCategory",
                "/createNewCategory"
        })
public class ServletAdminTools extends HttpServlet {
    private static final UtilisateurManager utilisateurManager = new UtilisateurManager();
    private static final CategorieManager categorieManager = new CategorieManager();


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
            case "/editCategory":
                //  TODO : Régler le souci du nom qui n'est pas pris en compte
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
                    System.out.println("Nope");
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








