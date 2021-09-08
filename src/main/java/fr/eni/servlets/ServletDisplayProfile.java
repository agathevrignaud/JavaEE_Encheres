package fr.eni.servlets;

import fr.eni.bll.UtilisateurManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletDisplayProfile", value = "/myProfile")
public class ServletDisplayProfile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UtilisateurManager utilisateurManager = new UtilisateurManager();
        utilisateurManager.getUserById(2);



        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/profileInformation.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
