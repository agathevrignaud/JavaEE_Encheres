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
@WebServlet("/AdminTools")
public class ServletAdminTools extends HttpServlet {
    private static final UtilisateurManager utilisateurManager = new UtilisateurManager();
    private static final CategorieManager categorieManager = new CategorieManager();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Utilisateur> lesUtilisateurs = utilisateurManager.getAllUsers();
        request.setAttribute("lesUtilisateurs", lesUtilisateurs);

        List<Categorie> lesCategories = categorieManager.getAllCategories();
        request.setAttribute("lesCategories", lesCategories);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/adminTools.jsp") ;
        requestDispatcher.forward(request, response) ;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}








