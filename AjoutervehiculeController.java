/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.typevehicule;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import entities.vehicule;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import services.typevehiculeService;
import services.vehiculeService;



/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class AjoutervehiculeController implements Initializable {

    @FXML
    private TextField marquetxt;
    @FXML
    private TextField immatriculationtxt;
    @FXML
    private TextField puissancetxt;
    @FXML
    private TextField kilometragetxt;
    @FXML
    private TextField nombredeplacetxt;
    @FXML
    private TextField prixtxt;
    @FXML
    private ComboBox<typevehicule> typevehiculetxt;
    @FXML
    private Button btnafficher;
    @FXML
    private Button btnajouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       typevehiculetxt.getItems().setAll(new typevehiculeService().recuperer());
    }    

    @FXML
    private void affichervehicule(ActionEvent event) {
         try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("Affichervehicule.fxml"));
            immatriculationtxt.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
     private void reset() {
        immatriculationtxt.setText("");
        marquetxt.setText("");
        puissancetxt.setText("");
        kilometragetxt.setText("");
        nombredeplacetxt.setText("");
        prixtxt.setText("");
    }

    @FXML
    private void ajoutervehicule(ActionEvent event) {
            vehicule v = new vehicule();
        v.setImmatriculation(immatriculationtxt.getText());
        v.setMarque(marquetxt.getText());
        v.setPuissance(puissancetxt.getText());
        v.setKilometrage(kilometragetxt.getText());
        v.setNbrdeplace(Integer.parseInt(nombredeplacetxt.getText()));
        v.setPrix(Float.parseFloat(prixtxt.getText()));
        v.setTypevehicule_id(typevehiculetxt.getSelectionModel().getSelectedItem().getId());
        new vehiculeService().ajouter(v);
        new Alert(Alert.AlertType.INFORMATION,"Ajout avec success!",ButtonType.OK).show();
        reset();
    }
    }
    

