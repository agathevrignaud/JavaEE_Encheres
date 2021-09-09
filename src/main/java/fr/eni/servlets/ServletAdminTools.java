package fr.eni.servlets;

import fr.eni.bll.UtilisateurManager;
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


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Utilisateur> lesUtilisateurs = utilisateurManager.getAllUsers();

        for (Utilisateur u : lesUtilisateurs) {
            System.out.println(u.getPseudo());
        }

        request.setAttribute("lesUtilisateurs", lesUtilisateurs);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/adminTools.jsp") ;
        requestDispatcher.forward(request, response) ;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}








