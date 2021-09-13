package fr.eni.servlets;

import fr.eni.bll.BLLException;
import fr.eni.bll.UtilisateurManager;
import fr.eni.bo.Utilisateur;
import fr.eni.dal.DALException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet(value = "/signup")
public class ServletCreateAccount extends HttpServlet {
    private static final UtilisateurManager utilisateurManager = new UtilisateurManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/createAccount.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Integer> listeCodesErreur = new ArrayList<>();

        switch (request.getParameter("btnPressed")) {
            case "createAccount":
                String pseudo = checkUsername(request, listeCodesErreur);
                String nom = checkSurname(request, listeCodesErreur);
                String prenom = checkFirstName(request, listeCodesErreur);
                String email = checkEmail(request, listeCodesErreur);
                String telephone = request.getParameter("phoneNumber");
                String rue = checkStreetName(request, listeCodesErreur);
                String codePostal = checkZipCode(request, listeCodesErreur);
                String ville = checkCity(request, listeCodesErreur);
                String motDePasse = checkPassword(request, listeCodesErreur);
                String motDePasseConfirmation = request.getParameter("confirmPwd");

                if (listeCodesErreur.size()>0) {
                    Collections.sort(listeCodesErreur);
                    request.setAttribute("listeCodesErreur",listeCodesErreur);
                    doGet(request, response);
                } else {
                    try {
                        Utilisateur lUtilisateur = utilisateurManager.addNewUser(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, motDePasseConfirmation);
                        HttpSession laSession = request.getSession();
                        laSession.setAttribute("isUserLoggedIn", true );
                        laSession.setAttribute("userInfo", lUtilisateur);
                        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
                        rd.forward(request, response);
                    } catch (BLLException e) {
                        e.printStackTrace();
                        request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
                    }
                }
                break;
            case "cancelCreateAccount":
                //  TODO Rediriger vers la page d'accueil
                break;
        }
    }

    private String checkUsername(HttpServletRequest request, List<Integer> listeCodesErreur) {
        String username;
        username = request.getParameter("username");
        if(username == null || username.trim().equals("")) {
            listeCodesErreur.add(CodesResultatServlets.USERNAME_REQUIRED);
        }
        return username;
    }

    private String checkSurname(HttpServletRequest request, List<Integer> listeCodesErreur) {
        String surname;
        surname = request.getParameter("surname");
        if(surname == null || surname.trim().equals("")) {
            listeCodesErreur.add(CodesResultatServlets.SURNAME_REQUIRED);
        }
        return surname;
    }

    private String checkFirstName(HttpServletRequest request, List<Integer> listeCodesErreur) {
        String firstName;
        firstName = request.getParameter("firstName");
        if(firstName == null || firstName.trim().equals("")) {
            listeCodesErreur.add(CodesResultatServlets.FIRSTNAME_REQUIRED);
        }
        return firstName;
    }

    private String checkEmail(HttpServletRequest request, List<Integer> listeCodesErreur) {
        String email;
        email = request.getParameter("email");
        if(email == null || email.trim().equals("")) {
            listeCodesErreur.add(CodesResultatServlets.EMAIL_REQUIRED);
        }
        return email;
    }

    private String checkStreetName(HttpServletRequest request, List<Integer> listeCodesErreur) {
        String streetName;
        streetName = request.getParameter("streetName");
        if(streetName == null || streetName.trim().equals("")) {
            listeCodesErreur.add(CodesResultatServlets.STREETNAME_REQUIRED);
        }
        return streetName;
    }

    private String checkZipCode(HttpServletRequest request, List<Integer> listeCodesErreur) {
        String zipCode;
        zipCode = request.getParameter("zipCode");
        if(zipCode == null || zipCode.trim().equals("")) {
            listeCodesErreur.add(CodesResultatServlets.ZIPCODE_REQUIRED);
        }
        return zipCode;
    }

    private String checkCity(HttpServletRequest request, List<Integer> listeCodesErreur) {
        String city;
        city = request.getParameter("city");
        if(city == null || city.trim().equals("")) {
            listeCodesErreur.add(CodesResultatServlets.CITY_REQUIRED);
        }
        return city;
    }

    private String checkPassword(HttpServletRequest request, List<Integer> listeCodesErreur) {
        String pwd = request.getParameter("pwd");
        String confirmPwd = request.getParameter("confirmPwd");
        if(pwd == null || pwd.trim().equals("")) {
            listeCodesErreur.add(CodesResultatServlets.PWD_REQUIRED);
        }
        if(confirmPwd == null || confirmPwd.trim().equals("")) {
            listeCodesErreur.add(CodesResultatServlets.PWD_CONFIRMED_REQUIRED);
        }
        return pwd;
    }

}
