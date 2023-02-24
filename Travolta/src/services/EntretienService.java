/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import models.*;
import Utils.DataSource;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author DELL
 */
public class EntretienService implements Iservices<Entretient> {
           
    Connection cnx = DataSource.getInstance().getCnx();

    


    @Override
    public void Ajouter(Entretient t) {
  
try{
    
    String requete = "INSERT INTO entretient(PrixEntretient,DateRetour,TypeEntretien) VALUES (?,?,?)";
        PreparedStatement pst = cnx.prepareStatement(requete);
        java.sql.Date sqlDate = java.sql.Date.valueOf(t.getDateRetour());
        
        
        pst.setInt(1, t.getPrixEntretient());
        pst.setDate(2, sqlDate);
        pst.setString(3, t.getTypeEntretien());
        pst.executeUpdate();
        System.out.println("Entretient d'iedntifiant"+t.getIdEntretient()+"est ajoutée");
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }    }

     @Override
    public void Supprimer(int t) {
    
try{
    
        String requete = "DELETE FROM entretient WHERE IdEntretient=?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setInt(1, t);
        pst.executeUpdate();
        System.out.println("Entretient Supprimé !");
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }        }

    @Override
    public void Modifier(Entretient R) {}

    
    
    public void Update(int IdEntretient ,int PrixEntretient,LocalDate DateRetour,String TypeEntretien) {
        /*private int  IdReservation ; 
   private String IdClient ; 
   private Date DateReservation ; 
   private  float PrixTotal ;*/
         
try{    
        String requete = "UPDATE entretient SET PrixEntretient=?,DateRetour= ?,TypeEntretien=? WHERE IdEntretient="+IdEntretient;
        PreparedStatement pst = cnx.prepareStatement(requete);
        java.sql.Date sqlDate = java.sql.Date.valueOf(DateRetour);

        pst.setInt(1, PrixEntretient);
        pst.setDate(2, sqlDate);
        pst.setString(3, TypeEntretien);

        
        pst.executeUpdate();
        System.out.println("Entretient mofidiée !");
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }     }

    @Override
    public ObservableList<Entretient> Afficher() {
      
         
ObservableList<Entretient> listentretien = FXCollections.observableArrayList();
        try{
        String requete = "SELECT * FROM entretient";
        PreparedStatement pst = cnx.prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            java.sql.Date date = rs.getDate("DateRetour");
            LocalDate localDate = date.toLocalDate();
            listentretien.add(new Entretient(rs.getInt(1),rs.getInt(2),localDate,rs.getString(4)));
        }
        System.out.println("Liste Entretient!");
      System.out.println(listentretien);

        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
        return listentretien ;
           }

    @Override
    public Entretient chercherById(int id) {
         /*private int  IdReservation ; 
   private String IdClient ; 
   private Date DateReservation ; 
   private  float PrixTotal ;*/
List<Entretient> listentretien = new ArrayList<>();
        try{
            
        String requete = "SELECT * FROM entretient where IdEntretient="+id;
        PreparedStatement pst = cnx.prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            java.sql.Date date = rs.getDate("DateRetour");
            LocalDate localDate = date.toLocalDate();
            listentretien.add(new Entretient(rs.getInt(1),rs.getInt(2),localDate,rs.getString(4)));
        }
        System.out.println("Entretient avec identifiant :"+id+"est");
            System.out.println(listentretien);
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
        return listentretien.get(0);    }
    
}
