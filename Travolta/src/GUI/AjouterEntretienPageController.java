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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.*;
import services.*;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AjouterEntretienPageController implements Initializable {

    @FXML
    private Button ButtonAjouterReservationManually;
    @FXML
    private TextField IdClientLabel;
    @FXML
    private Label PrixTotalLabel;
    @FXML
    private Button AnnulerAjoutReservationButton;
    @FXML
    private TextField PrixTotal_field;
    @FXML
    private DatePicker date_field;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterEntretientManually(ActionEvent event) {
        EntretienService rs=new EntretienService();
        Entretient entretient=new Entretient(Integer.parseInt(IdClientLabel.getText()) ,date_field.getValue(),PrixTotal_field.getText()  );
        entretient.setDateRetour(date_field.getValue());
        rs.Ajouter(entretient);
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("GestionEntretien.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterEntretienPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @FXML
    private void ToGestionEntretien(ActionEvent event) {
        Parent root;
         try {
            root = FXMLLoader.load(getClass().getResource("GestionEntretien.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterEntretienPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
