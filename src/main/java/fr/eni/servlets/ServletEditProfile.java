package fr.eni.servlets;

import fr.eni.bll.UtilisateurManager;
import fr.eni.bo.Utilisateur;
import org.apache.tomcat.util.modeler.Util;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletEditProfile", value = "/editMyProfile")
public class ServletEditProfile extends HttpServlet {
    private static final UtilisateurManager utilisateurManager = new UtilisateurManager();

    /* TODO : Ajouter un doFilter pour que seul un utilisateur connecté dont c'est le profil accède ici

    public  void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        vérifier si l'utilisateur qui navigue est connecté (isAuthenticated)
        &&
        vérifier si l'utilisateur possède le compte qu'on veut modifier ici
        -> msg d'erreur "accès refusé : veuillez vous connecter"

        filterChain.doFilter(request, response) ;
    }

    */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idUser = Integer.parseInt(request.getParameter("idUser"));
        Utilisateur lUtilisateur = utilisateurManager.getUserById(idUser);
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
                    utilisateurManager.deleteUser(Integer.parseInt(request.getParameter("idUser")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // TODO : Redirection homepage en mode déconnecté + clean les infos de session
                RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
                break;
        }
    }
}
