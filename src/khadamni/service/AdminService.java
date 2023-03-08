/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khadamni.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entity.Admin;
import entity.agence;
import javafx.collections.ObservableList;
import khadamni.tools.MaConnection;
import entity.Utilisateur;
import javafx.scene.control.ListView;


import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AdminService implements Interface <Admin>{
    Connection cnx;

    public AdminService() {
        cnx = MaConnection.getInstance().getCnx();
    }
     @Override
    public void ajouter(Admin a) {
        try {
            String sql = "insert into utilisateur(nom,prenom,num,adresse,mdp,role)"
                    + "values (?,?,?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, a.getNom());
            ste.setString(2, a.getPrenom());
             ste.setInt(3, a.getNumtel());
            ste.setString(4, a.getAdresse());
            ste.setString(5, a.getMdp());
            ste.setString(6, a.getRole());
            ste.executeUpdate();
            System.out.println("Admin ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List <Admin> afficher() {
        List<Admin> admins = new ArrayList<>();
        try {
            String sql = "select * from utilisateur where role = 'admin' ";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
                Admin a;
                a = new Admin(s.getInt(1), s.getInt(4),s.getString("nom"), s.getString("prenom"),s.getString("adresse"),s.getString("role"), s.getString("mdp"));
                admins.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if(admins.size()<1){
            System.out.println("la liste est vide");
        }
        return admins;
    }
     public List <Utilisateur> afficherall() {
        List<Utilisateur> users = new ArrayList<>();
        try {
            String sql = "select * from utilisateur ";
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
    public Admin findById(int id) {
        Admin a = null;
        try {
            String sql = "select * from utilisateur where id = ? ";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);
            ResultSet s = ste.executeQuery();
            while (s.next()) {
              a = new Admin(s.getInt(1), s.getInt(4),s.getString("nom"), s.getString("prenom"),s.getString("adresse"),s.getString("role"), s.getString("mdp"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return a;
    }
     @Override
    public void supprimer(int id) {
        String sql = "delete from utilisateur where id = ?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);
            ste.executeUpdate();
            System.out.println("Admin supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     @Override
    public void modifier(String nom, int id) {
        String sql = "update utilisateur set nom=? where id=? ";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, nom);
            ste.setInt(2, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   public void ajouterUser(Utilisateur u){
       UtilisateurService us = new UtilisateurService();
 
        us.ajouter(u);
           }
   public List getAlluser(){
        UtilisateurService us = new UtilisateurService();
       return us.afficher();
   } 
   public void suprimerUser(int id){
        UtilisateurService us = new UtilisateurService();
        us.supprimer(id);
   }
   public void modifierUser(String n ,int id){
        UtilisateurService us = new UtilisateurService();
        us.modifier(n, id);
   }
   public   Utilisateur findUserById(int id){
       UtilisateurService us = new UtilisateurService();
       return us.findById(id);
   }
   public void ajouteragence(agence f){
   agenceService fs = new agenceService();

    fs.ajouter(f);
       }
public List getAllagence(){
   agenceService fs = new agenceService();
   return fs.afficher();
} 
public void suprimeragence(int id){
   agenceService fs = new agenceService();
   fs.supprimer(id);
}
public void modifieragence(String n ,int id){
   agenceService fs = new agenceService();
   fs.modifier(n, id);
}
public agence findagenceById(int id){
   agenceService fs = new agenceService();
   return fs.findById(id);
}
public   Admin session(String nom ,String mdp) {
          
          Admin a = null;
    
          try {
           String sql = "SELECT * FROM utilisateur WHERE nom = ? and mdp = ? ";
              PreparedStatement ste = cnx.prepareStatement(sql);
             ste.setString(1, nom );
             ste.setString(2, mdp );
            ResultSet s = ste.executeQuery();
            while (s.next()) {

                 a = new Admin(s.getInt(1), s.getInt(4),s.getString("nom"), s.getString("prenom"),s.getString("adresse"),s.getString("role"), s.getString("mdp"));
                

            }
          
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return a;
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

    public static Document createPDF(ListView<Utilisateur> listView) throws DocumentException, FileNotFoundException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("output.pdf"));
        document.open();

        ObservableList<Utilisateur> items = listView.getItems();
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);

        for (Utilisateur item : items) {
            table.addCell(new Paragraph(item.toString()));
        }

        document.add(table);
        document.close();

        return document;
    }

}
