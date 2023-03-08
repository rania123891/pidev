/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khadamni.service;

import entity.agence;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Utilisateur;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import khadamni.tools.MaConnection;

/**
 *
 * @author lordg
 */
public class UtilisateurService implements Interface<Utilisateur> {

    Connection cnx;

    public UtilisateurService() {
        cnx = MaConnection.getInstance().getCnx();
    }

    @Override
    public void ajouter(Utilisateur t) {
        try {
            String sql = "insert into utilisateur(nom,prenom,num,adresse,role,mdp)"
                    + "values (?,?,?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getNom());
            ste.setString(2, t.getPrenom());
            ste.setInt(3, t.getNumtel());
            ste.setString(4, t.getAdresse());
            ste.setString(5, "user");
            ste.setString(6, t.getMdp());
            ste.executeUpdate();
            System.out.println("Personne ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

    @Override
    public List<Utilisateur> afficher() {
        List<Utilisateur> users = new ArrayList<>();
        try {
            String sql = "select * from utilisateur where role = 'user' ";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Utilisateur u = new Utilisateur(s.getInt(1), s.getInt(4),
                        s.getString("nom"), s.getString("prenom"), s.getString("adresse"), s.getString("role"), s.getString("mdp"));
                users.add(u);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return users;
    }

    @Override
    public Utilisateur findById(int id) {

        Utilisateur u = null;

        try {
            String sql = "select * from utilisateur where id = ? and role = 'user' ";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);

            ResultSet s = ste.executeQuery();
            while (s.next()) {

                u = new Utilisateur(s.getInt(1), s.getInt(4),
                        s.getString("nom"), s.getString("prenom"), s.getString("adresse"), s.getString("role"), s.getString("mdp"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return u;
    }

    public Utilisateur session(String nom, String mdp) {

        Utilisateur u = null;

        try {
            String sql = "select * from utilisateur where nom = ? and mdp = ?  ";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, nom);
            ste.setString(2, mdp);
            ResultSet s = ste.executeQuery();
            while (s.next()) {

                u = new Utilisateur(s.getInt(1), s.getInt(4),
                        s.getString("nom"), s.getString("prenom"), s.getString("adresse"), s.getString("role"), s.getString("mdp"));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return u;
    }

    @Override
    public void supprimer(int id) {
        String sql = "delete from utilisateur where id = ?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);
            ste.executeUpdate();
            System.out.println("Personne supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void modifier(String nom, int id) {

        String sql = "update utilisateur set nom=? where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, nom);
            ste.setInt(2, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void logout(int id) {
        LocalDateTime now = LocalDateTime.now(); // current date and time
        ; // replace with the IDU you're searching for

// build the query to find the most recent session for the given IDU
        try {
            String query1 = "SELECT id, date_debut FROM session WHERE idu = ? AND date_debut <= ? ORDER BY date_debut DESC LIMIT 1";
            PreparedStatement ps = cnx.prepareStatement(query1);

            ps.setInt(1, id);
            Timestamp timestamp = Timestamp.valueOf(now);
            ps.setTimestamp(2, timestamp);

            // execute the query
            try (ResultSet rs = ps.executeQuery()) {

                // if we found a session, update its end date to the current date and time
                if (rs.next()) {
                    int sessionId = rs.getInt("id");
                    LocalDateTime sessionStart = rs.getTimestamp("date_debut").toLocalDateTime();

                    String query2 = "UPDATE session SET date_fin = ? WHERE id = ?";
                    try (PreparedStatement ps2 = cnx.prepareStatement(query2)) {
                        ps2.setTimestamp(1, Timestamp.valueOf(now));
                        ps2.setInt(2, sessionId);
                        ps2.executeUpdate();
                    }

                } else {
                    System.out.println("No session found for IDU ");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

 
        public agence convert(int id) {

        agence f = null;
        
        try {
            String sql = "select * from utilisateur where id = ?  ";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);

            ResultSet s = ste.executeQuery();
            while (s.next()) {

               f = new agence(s.getInt(1), s.getInt(4), s.getString("nom"), s.getString("prenom"), s.getString("adresse"), s.getString("role"), s.getString("photo"), s.getString("description"), s.getInt("rate"), s.getString("type"), s.getBoolean("verified"), s.getString("mdp"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return f;
    }
    
}
