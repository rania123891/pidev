/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.*;
import services.*;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class GestionMecanicienController implements Initializable {

    @FXML
    private TableView<Mecanicien> table_reservation;
    @FXML
    private TableColumn<Mecanicien, String> col_id;
    @FXML
    private Button ButtonAjouterActivite;
    @FXML
    private Button ButtonSupprimerActivite;
    @FXML
    private Button ButtonModifierActivite;
    @FXML
    private Button ButtonGestionExcursion;
    @FXML
    private Button ButtonImprimerActivite;
    @FXML
    private Button ButtonGestionReservation;
    @FXML
    private TableColumn<Mecanicien, String>col_nomAct;
    @FXML
    private TableColumn<Mecanicien, String>col_typeAct;
    @FXML
    private TableColumn<Mecanicien, String> col_PrixAct;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherMecaniciens();
        // TODO
    }    

    

    @FXML
    private void ToAjouterMecanicienPage(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("AjouterMecanicienPage.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GestionEntretienController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void SupprimerMecanicien(ActionEvent event) {
        MecanicienService as=new MecanicienService();
        as.Supprimer(IdMecanicien);
        AfficherMecaniciens();
  }

    @FXML
    private void ModifierMecanicien(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("ModifierMecanicienPage.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GestionMecanicienController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ToGestionExcursion(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("GestionMecanicien.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GestionMecanicienController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ImprimerMecanicien(ActionEvent event) {
    }

     public void AfficherMecaniciens(){
          
        
         
        MecanicienService as=new MecanicienService();
        col_id.setCellValueFactory(new PropertyValueFactory<>("IdMecanicien"));
        col_nomAct.setCellValueFactory(new PropertyValueFactory<>("NomMecanicien"));
        col_typeAct.setCellValueFactory(new PropertyValueFactory<>("SalaireMecanicien"));
        col_PrixAct.setCellValueFactory(new PropertyValueFactory<>("Disponibilite"));
        table_reservation.setItems(as.Afficher());
                    

    }
    

    @FXML
    private void ToGestionEntretien(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("GestionEntretien.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GestionMecanicienController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
public static int IdMecanicien=0;
    @FXML
    private void getSelected(MouseEvent event) {
                    Mecanicien r=table_reservation.getSelectionModel().getSelectedItem();
             int id=r.getIdMecanicien();
             IdMecanicien=id;

    }
        
    
        
    

    @FXML
    private void BPDF(ActionEvent event) {

    }
}