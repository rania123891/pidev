/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import models.Mecanicien;
import Utils.DataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author DELL
 */
public class MecanicienService implements Iservices<Mecanicien>  {
        Connection cnx = DataSource.getInstance().getCnx();


    @Override
    public void Ajouter(Mecanicien t) {

            try {
                /* private int  IdActivite ;
                private String NomActivite ;
                private String ImageActivite ;
                private  float PrixActivite ;
                private String TypeActivite; */
                
                
                String requete = "INSERT INTO mecanicien (NomMecanicien,SalaireMecanicien,Disponibilite) VALUES (?,?,?)" ;
                
                PreparedStatement pst = cnx.prepareStatement(requete);
                
                pst.setString(1, t.getNomMecanicien());
                
                pst.setFloat(2, t.getSalaireMecanicien());
                pst.setString(3, t.getDisponibilite());
                
                
                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(MecanicienService.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            }

    @Override
    public void Supprimer(int t) {
        /* private int  IdActivite ; 
   private String NomActivite ; 
   private String ImageActivite ; 
   private  float PrixActivite ;
   private String TypeActivite; */
try{
        String requete = "DELETE FROM mecanicien WHERE IdMecanicien=?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setInt(1, t);
        pst.executeUpdate();
        System.out.println("Mecanicien Supprimé !");
        }

catch(SQLException ex){
        System.err.println(ex.getMessage());
        }        }

    @Override
    public void Modifier(Mecanicien t) {
    try {
       
        String requete = "UPDATE mecanicien SET NomMecanicien=?,SalaireMecanicien=?,Disponibilite=? WHERE IdMecanicien=?";
        PreparedStatement pst = cnx.prepareStatement(requete);

        pst.setString(1, t.getNomMecanicien());


        pst.setFloat(2, t.getSalaireMecanicien());
        pst.setString(3, t.getDisponibilite());
        pst.setInt(4, t.getIdMecanicien());

        pst.executeUpdate();

        System.out.println("Mecanicien qui a l'identifiant " + t.getIdMecanicien() + " est modifiée !");
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    } catch (IllegalArgumentException ex) {
        System.err.println(ex.getMessage());
    }
}
    
            
    public void update(int IdMecanicien,String NomMecanicien,float SalaireMecanicien,String Disponibilite){
            try {
                String requete = "UPDATE mecanicien SET NomMecanicien=?,SalaireMecanicien=?,Disponibilite=? WHERE IdMecanicien=?"+IdMecanicien;
                PreparedStatement pst = cnx.prepareStatement(requete);
                
                pst.setString(1, NomMecanicien);
                pst.setFloat(2, SalaireMecanicien);
                pst.setString(3, Disponibilite);
                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(MecanicienService.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    @Override
    public ObservableList<Mecanicien> Afficher() {
                        ObservableList<Mecanicien> listamecanicien = FXCollections.observableArrayList();
                        String requete = "SELECT IdMecanicien,NomMecanicien,SalaireMecanicien,Disponibilite FROM mecanicien ";
                        PreparedStatement pst ;


            try {
                
                
                    pst=new DataSource().getCnx().prepareStatement(requete);

                ResultSet rs = pst.executeQuery();
                while(rs.next()){
                    
                    Mecanicien a=new Mecanicien(rs.getInt(1),rs.getString(2),rs.getFloat(3),rs.getString(4));
                    
                    listamecanicien.add( a);
                    
                    
                }           } catch (SQLException ex) {
                Logger.getLogger(MecanicienService.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    
        
        

  return listamecanicien;

        }

    @Override
    public Mecanicien chercherById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
  

  }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    
   
    

