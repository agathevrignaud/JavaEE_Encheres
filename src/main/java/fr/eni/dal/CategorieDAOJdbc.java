package fr.eni.dal;

import fr.eni.bo.Categorie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategorieDAOJdbc implements CategorieDAO {
    private static final String SELECT_ALL_CATEGORIES = "SELECT * FROM CATEGOERIES";

    @Override
    public List<Categorie> selectAll() {
        List<Categorie> lesCategories = new ArrayList<Categorie>();
        Categorie laCategorie = new Categorie();

        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_CATEGORIES);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
                laCategorie.setNo_categorie(rs.getInt("no_categorie"));
                laCategorie.setLibelle(rs.getString("libelle"));
            }
            lesCategories.add(laCategorie);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return lesCategories;
    }
}
