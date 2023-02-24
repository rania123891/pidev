/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Admin;
import entity.agence;
import entity.Session;
import entity.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import khadamni.service.AdminService;
import khadamni.service.FreelancerService;
import khadamni.service.SessionSercive;
import khadamni.service.UtilisateurService;
import khadamni.utilisateurfx;

/**
 * FXML Controller class
 *
 * @author lordg
 */
public class SessionController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField mdp;
    @FXML
    private Button connect;
    @FXML
    private Button inscrire;
    private int dataToPass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setDataToPass(int dataToPass) {
        this.dataToPass = dataToPass;
    }
    
    @FXML
    private void connect(ActionEvent event) {

 //NB NB NB
 //ceci le test 2 le plus efficace
 try {
            UtilisateurService us = new UtilisateurService();
            AdminService as = new AdminService();
            FreelancerService fs = new FreelancerService();
            Utilisateur u = us.session(nom.getText(), mdp.getText());
            agence f =null;
            Admin a =null;
            String role = "";
            String path = "";
             int id =0;
             String t ="";
            role = u.getRole();
            switch (role) {
                case "freelancer": {
                     f = fs.session(nom.getText(), mdp.getText());
                    t="f";
                    if (f == null) {
                        System.out.println("Freelancer non trouvé");
                        id =f.getId();
                    } else {
                        System.out.println("Freelancer trouvé");
                        path = "/gui/agencehome.fxml";
                        Session s = new Session(f);
                        SessionSercive sc2 = new SessionSercive();
                        sc2.ajouter(s);
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("connexion ");
                        alert.setHeaderText(null);
                        alert.setContentText("vous êtes connecté ");
                        alert.show();
                    }
                    break;
                }
                case "admin": {
                    path = "/gui/AdminHome.fxml";
                     a = as.session(nom.getText(), mdp.getText());
                    t="a";
                    id=a.getId();
                    if (a == null) {
                        System.out.println("admin non trouvé");
                    } else {
                        System.out.println("admin trouvé");
                        Session s = new Session(a);
                        SessionSercive sc3 = new SessionSercive();
                        sc3.ajouter(s);
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("connexion ");
                        alert.setHeaderText(null);
                        alert.setContentText("vous êtes connecté ");
                        alert.show();
                    }
                    break;
                }
                case "user": {
                    t="u";
                       id = u.getId();
                    Session s = new Session(u);
                    SessionSercive sc = new SessionSercive();
                    sc.ajouter(s);
                    path = "/gui/utilisateurhome.fxml";

                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("connexion ");
                    alert.setHeaderText(null);
                    alert.setContentText("vous êtes connecté ");
                    alert.show();
                    break;
                }

                
            }

          
             FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);
        if(t=="u"){
            UtilisateurhomeController c = loader.getController();
           c.initializeprem(u);
           c.setid(id);
        }
        if(t=="f"){
            agencehomeController fc = loader.getController();
            fc.initializeprem(f);
           fc.setid(id);
        }
        if(t=="a"){
            AdminHomeController ac = loader.getController();
            ac.initializeprem(a);
            ac.setid(id);
        }
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("warning ");
            alert.setHeaderText(null);
            alert.setContentText("Personne invalide ");
            alert.show();
        }

    }

    @FXML
    private void inscrire(ActionEvent event) {
        try {

            Parent page1
                    = FXMLLoader.load(getClass().getResource("/gui/inscrire.fxml"
                    ));

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {

            Logger.getLogger(utilisateurfx.class.getName()).log(Level.SEVERE,
                    null, ex);

        }
    }

}
