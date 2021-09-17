package fr.eni.servlets;

import fr.eni.bll.*;
import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Categorie;
import fr.eni.bo.Retrait;
import fr.eni.bo.Utilisateur;
import fr.eni.dal.ArticleVenduDAO;
import fr.eni.dal.CategorieDAO;
import fr.eni.dal.DAOFactory;
import jdk.vm.ci.meta.Local;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@WebServlet(value = "/createAuction")
public class ServletCreateAuction extends HttpServlet {
    public static ArticleVenduManager articleVenduManager = new ArticleVenduManager();
    public static final CategorieManager categorieManager = new CategorieManager();
    public static EnchereManager enchereManager = new EnchereManager();
    public static UtilisateurManager utilisateurManager = new UtilisateurManager();
    final String NOT_STARTED = "A";
    final String IN_PROGRESS = "E";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession laSession = request.getSession();
        Utilisateur lUtilisateur = (Utilisateur) laSession.getAttribute("userInfo");
        try {
            request.setAttribute("lesCategories", categorieManager.getAllCategories());
        } catch (BLLException e) {
            e.printStackTrace();
            request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
        }
        Retrait lieuRetrait = new Retrait(
                lUtilisateur.getRue(),
                lUtilisateur.getCodePostal(),
                lUtilisateur.getVille()
        );
        request.setAttribute("lieuRetrait", lieuRetrait);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/createAuction.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Integer> listeCodesErreur = new ArrayList<>();
        HttpSession laSession = request.getSession();
        Utilisateur lUtilisateur = (Utilisateur) laSession.getAttribute("userInfo");
        switch (request.getParameter("btnPressed")) {
            // New Auction
            case "save":
                String articleName = checkArticleName(request, listeCodesErreur);
                String articleDescription = checkDescription(request, listeCodesErreur);
                Categorie category = checkCategory(request, listeCodesErreur);
                //Todo : la photo
                int startingPrice = checkStartingPrice(request, listeCodesErreur);
                LocalDate auctionStartDate = checkStartDate(request, listeCodesErreur);
                LocalDate auctionEndDate = checkEndDate(request, listeCodesErreur);
                checkDates(auctionStartDate, auctionEndDate, listeCodesErreur);
                String auctionStatus = setAuctionStatus(auctionStartDate, auctionEndDate);
                Retrait collectPoint = checkCollectPoint(request, listeCodesErreur);

                if (listeCodesErreur.size() > 0) {
                    Collections.sort(listeCodesErreur);
                    request.setAttribute("listeCodesErreur",listeCodesErreur);
                    doGet(request, response);
                } else {
                    try {
                        articleVenduManager.addNewArticle(
                            articleName,
                            articleDescription,
                            auctionStartDate,
                            auctionEndDate,
                            startingPrice,
                            auctionStatus,
                            lUtilisateur,
                            category,
                            collectPoint
                        );
                        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
                        rd.forward(request, response);
                    } catch (BLLException e) {
                        e.printStackTrace();
                        request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
                        doGet(request, response);
                    }
                }
                break;
            case "cancelCreation":
                break;
            // Auction Not Started - can still be edited
            case "edit":
                break;
            case "cancelEdit":
                break;
            case "cancelAuction":
                break;

        }
    }

    private String checkArticleName(HttpServletRequest request, List<Integer> listeCodesErreur) {
        String articleName;
        articleName = request.getParameter("articleName");
        if(articleName == null || articleName.trim().equals("")) {
            listeCodesErreur.add(CodesResultatServlets.ARTICLE_NAME_REQUIRED);
        }
        return articleName;
    }

    private String checkDescription(HttpServletRequest request, List<Integer> listeCodesErreur) {
        String description;
        description = request.getParameter("articleDescription");
        if(description == null || description.trim().equals("")) {
            listeCodesErreur.add(CodesResultatServlets.DESCRIPTION_REQUIRED);
        }
        return description;
    }

    private Categorie checkCategory(HttpServletRequest request, List<Integer> listeCodesErreur) {
        String label = request.getParameter("selectedCategory");
        int idCategory  = Integer.parseInt(request.getParameter("category"));
        Categorie category = new Categorie(
            idCategory,
            label
        );
        if(label == null || label.trim().equals("")) {
            listeCodesErreur.add(CodesResultatServlets.CATEGORY_REQUIRED);
        }
        if(idCategory == 0) {
            listeCodesErreur.add(CodesResultatServlets.CATEGORY_REQUIRED);
        }
        return category;
    }

    private int checkStartingPrice(HttpServletRequest request, List<Integer> listeCodesErreur) {
        int startingPrice = 0 ;
        startingPrice = Integer.parseInt(request.getParameter("startingPrice"));
        if (startingPrice <= 0) {
            listeCodesErreur.add(CodesResultatServlets.STARTING_PRICE_INVALID);
        }
        return startingPrice;
    }

    private LocalDate checkStartDate(HttpServletRequest request, List<Integer> listeCodesErreur) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        LocalDate startDate = null ;
        try {
            Date date = sdf.parse(request.getParameter("auctionStartDate"));
            startDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (ParseException e) {
            e.printStackTrace();
            listeCodesErreur.add(CodesResultatServlets.START_DATE_REQUIRED);
        }
        return startDate;
    }

    private LocalDate checkEndDate(HttpServletRequest request, List<Integer> listeCodesErreur) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        LocalDate endDate = null ;
        try {
            Date date = sdf.parse(request.getParameter("auctionEndDate"));
            endDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (ParseException e) {
            e.printStackTrace();
            listeCodesErreur.add(CodesResultatServlets.END_DATE_REQUIRED);
        }
        return endDate;
    }

    private void checkDates(LocalDate auctionStartDate, LocalDate auctionEndDate, List<Integer> listeCodesErreur) {
        if (auctionEndDate.isBefore(auctionStartDate)) {
            listeCodesErreur.add(CodesResultatServlets.ERROR_ENDATE_BEFORE_STARTDATE);
        }
        if (auctionEndDate.isAfter(LocalDate.now()) || LocalDate.now() == auctionEndDate) {
            listeCodesErreur.add(CodesResultatServlets.ERROR_ENDDATE_BEFORE_TODAY);
        }
    }

    private String setAuctionStatus(LocalDate auctionStartDate, LocalDate auctionEndDate) {
        String auctionStatus = "";
        if(LocalDate.now().isBefore(auctionStartDate)) {
            auctionStatus = NOT_STARTED;
        }
        if (LocalDate.now().isAfter(auctionStartDate) || LocalDate.now() == auctionStartDate) {
            auctionStatus = IN_PROGRESS;
        }
        return auctionStatus;
    }

    private Retrait checkCollectPoint(HttpServletRequest request, List<Integer> listeCodesErreur) {
        String streetName = checkStreetName(request, listeCodesErreur);
        String zipCode = checkzipCode(request, listeCodesErreur);
        String city = checkCity(request, listeCodesErreur);
        Retrait collectPoint = new Retrait(streetName, zipCode, city);
        return collectPoint;
    }

    private String checkCity(HttpServletRequest request, List<Integer> listeCodesErreur) {
        String city;
        city = request.getParameter("city");
        if(city == null || city.trim().equals("")) {
            listeCodesErreur.add(CodesResultatServlets.CITY_REQUIRED);
        }
        return city;
    }

    private String checkzipCode(HttpServletRequest request, List<Integer> listeCodesErreur) {
        String zipCode;
        zipCode = request.getParameter("zipCode");
        if(zipCode == null || zipCode.trim().equals("")) {
            listeCodesErreur.add(CodesResultatServlets.ZIPCODE_REQUIRED);
        }
        return zipCode;
    }

    private String checkStreetName(HttpServletRequest request, List<Integer> listeCodesErreur) {
        String streetName;
        streetName = request.getParameter("streetName");
        if(streetName == null || streetName.trim().equals("")) {
            listeCodesErreur.add(CodesResultatServlets.STREETNAME_REQUIRED);
        }
        return streetName;
    }

}
