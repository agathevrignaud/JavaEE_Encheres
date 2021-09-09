package fr.eni.dal;

import fr.eni.bo.Enchere;
import fr.eni.bo.Utilisateur;

import java.lang.reflect.Array;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EnchereDAOJdbc implements EnchereDAO {
    private static final String SELECT_ALL_ENCHERES = "SELECT * FROM ENCHERES WHERE no_article=? ORDER BY montant_enchere DESC";
    public static final String INSERT_ENCHERE = "INSERT INTO ENCHERES VALUES(?,?,?,?)";

    @Override
    public List<Enchere> selectByIdArticle(int idArticle) {
        List<Enchere> lesEncheres = new ArrayList<>();
        Enchere lEnchere = new Enchere();

        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_ENCHERES);
            pstmt.setInt(1, idArticle);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                lEnchere.setNo_utilisateur(rs.getInt("no_utilisateur"));
                lEnchere.setNo_article(rs.getInt("no_article"));
                lEnchere.setDateEnchere(rs.getTimestamp("date_enchere").toLocalDateTime());
                lEnchere.setMontantEnchere(rs.getInt("montant_enchere"));
            }
            lesEncheres.add(lEnchere);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return lesEncheres;
    }

    @Override
    public void createEnchere(Enchere lEnchere) {
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(INSERT_ENCHERE);
            pstmt.setInt(1, lEnchere.getNo_utilisateur());
            pstmt.setInt(2, lEnchere.getNo_article());
            pstmt.setTimestamp(3, Timestamp.valueOf(lEnchere.getDateEnchere()));
            pstmt.setInt(4, lEnchere.getMontantEnchere());
            ResultSet rs = pstmt.executeQuery();
        }
        catch(Exception e) {
            System.out.println("Erreur lors de l'insertion de l'enchère en base");
            e.printStackTrace();
        }
    }
}
