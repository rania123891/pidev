/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import services.*;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class GestionEntretienController implements Initializable {

    @FXML
    private TableView<Entretient> table_reservation;
    @FXML
    private Button ButtonAjouterReservation;
    @FXML
    private Button ButtonSupprimerReservation;
    @FXML
    private Button ButtonModifierReservation;
    @FXML
    private Button ButtonGestionExcursion;
    @FXML
    private Button ButtonImprimerReservation;
    @FXML
    private Button ButtonGestionActivite;
    @FXML
    private Button Buttonretourfirstpage;
    @FXML
    private TableColumn<Entretient, String>col_id;
    @FXML
    private TableColumn<Entretient, String> col_idClient;
    @FXML
    private TableColumn<Entretient, String>col_dateRes;
    @FXML
    private TableColumn<Entretient, String> col_prixTot;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherEntretiens();
        // TODO 
    }    
    public static int idEntretien=0;

    

    @FXML
    private void ToAjouterEntretienPage(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AjouterEntretienPage.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GestionEntretienController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void AfficherEntretiens(){
     
        
        EntretienService rs=new EntretienService();
        col_id.setCellValueFactory(new PropertyValueFactory<>("IdEntretienService"));
        col_idClient.setCellValueFactory(new PropertyValueFactory<>("PrixEntretien"));
        col_dateRes.setCellValueFactory(new PropertyValueFactory<>("DateRetour"));
        col_prixTot.setCellValueFactory(new PropertyValueFactory<>("TypeEntretien"));
                    table_reservation.setItems(rs.Afficher());
                    

    }

    @FXML
    private void SupprimerEntretien(ActionEvent event) {
        if(idEntretien==0){
            
        }
        else{
                    EntretienService rs=new EntretienService();
                    rs.Supprimer(idEntretien);
                    AfficherEntretiens();

            
        }
    }

    @FXML
    private void ModifierEntretien(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ModifierEntretienPage.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GestionEntretienController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  

    @FXML
    private void ImprimerEntretien(ActionEvent event) {
    }

    @FXML
    private void ToGestionMecanicien(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("GestionMecanicien.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GestionEntretienController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    @FXML
    private void RetourFirstPage(ActionEvent event) {
         Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("FirstPage.fxml"));
       
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
             } catch (IOException ex) {
            Logger.getLogger(GestionEntretienController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void getSelected(MouseEvent event) {
        Entretient r=table_reservation.getSelectionModel().getSelectedItem();
        int id=r.getIdEntretient();
        idEntretien=id;
    }
    @FXML
    public void ToNothing(){}

    @FXML
    private void BPDF(ActionEvent event) {
    }
    
}
