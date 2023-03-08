/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khadamni;

import entity.Admin;

import entity.agence;
import khadamni.service.AdminService;
import khadamni.service.CategoryService;

import khadamni.service.agenceService;
import khadamni.service.UtilisateurService;


/**
 *
 * @author lordg
 */
public class vromvrom {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        //usertest_______###########################
        UtilisateurService us = new UtilisateurService();
       //Utilisateur  u1 = new Utilisateur(29856147,"aziz","bouharb","salakta","titi","lavie123");
        //us.ajouter(u1);
        //Session s = new Session(u1);
       // SessionSercive ss = new SessionSercive();
        //ss.ajouter(s);
     
      //System.out.println(us.afficher());  
      //System.out.println(us.findById(7));
      // us.modifier("karim", 24)
      
       //us.supprimer(24);
       // System.out.println(us.afficher());
       
       //#############################################################
       //#############################################################
       
      
     
     //test agence____#############################################
       
    agence f1 = new agence(1,10, "karim", "karawli",  "gafsa", "agence", "aa", "b+4", 3, "docteur", true,"abdc");
     agenceService fs = new agenceService();
        CategoryService cs = new CategoryService();

     
      AdminService as = new AdminService();
      Admin a = new Admin(2 ,11111111,"1","1","gg","admin","12");
      //as.ajouter(a);
     // as.modifier("moncef", 28);
     //as.supprimer(19);
      System.out.println(as.findById(20));
       
      
       
    }
    
}
