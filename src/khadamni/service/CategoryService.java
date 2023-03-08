/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khadamni.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.Category;
import khadamni.tools.MaConnection;

public class CategoryService implements Interface<Category> {

    Connection cnx;
    String sql = "";

    public CategoryService() {
        this.cnx = MaConnection.getInstance().getCnx();

    }

    @Override
    public void ajouter(Category c) {
        int id = 0;
        try {
            sql = "insert into services (service_nom,service_description,service_image,nb_sous_services) values (?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ste.setString(1, c.getNom());
            ste.setString(2, c.getDescription());
            ste.setString(3, c.getImage());
            ste.setInt(4, c.getNb_service());
            ste.executeUpdate();
            // Récupération de l'ID généré automatiquement

            ResultSet generatedKeys = ste.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
                c.setId(id);

            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
   public List<Category> afficher()  {
        List<Category> categorys = new ArrayList<>();
        try {
            String sql = "select * from services";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Category p = new Category(s.getInt(1), s.getString("service_nom"), s.getString("service_Description"), s.getString("service_image"));

                categorys.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return categorys;

    }

    @Override
    public void supprimer(int id) {
        String sql = "delete from Category where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(String nom, int id) {
        String sql = "update Category set nom=? where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, nom);
            ste.setInt(2, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    

    @Override
    public Category findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}