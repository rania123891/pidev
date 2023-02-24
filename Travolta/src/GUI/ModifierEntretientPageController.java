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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.*;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ModifierEntretientPageController implements Initializable {

    @FXML
    private TextField IdClientLabel;
    @FXML
    private TextField PrixTotal;
    @FXML
    private Label PrixTotalLabel;
    @FXML
    private DatePicker DateReservationLabel;
    @FXML
    private Button AnnulerAjoutReservationButton;
    @FXML
    private Button ButtonAjouterReservationManually;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    

    @FXML
    private void AjouterEntretienManually(ActionEvent event) {
        EntretienService rs=new EntretienService();
        rs.Update(GestionEntretienController.idEntretien,Integer.parseInt(IdClientLabel.getText()) , DateReservationLabel.getValue(),PrixTotal.getText());
        PrixTotal.getScene().getWindow().hide();
        Parent root;
         try {
            root = FXMLLoader.load(getClass().getResource("GestionReservation.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ModifierEntretientPageController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
