/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.vehicule;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import services.vehiculeService;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class AffichervehiculeController implements Initializable {

    @FXML
    private VBox _vehicule_list_;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refresh();
    } 
    private void refresh(){
        _vehicule_list_.getChildren().clear();
        List<vehicule> vehicules = new vehiculeService().recuperer();
        vehicules.forEach( v -> {
          Label im = new Label(v.getImmatriculation());
          im.setStyle("-fx-text-fill:white;-fx-font-family:\"Javanese Text\";");
           Label mar = new Label(v.getMarque());
          mar.setStyle("-fx-text-fill:white;-fx-font-family:\"Javanese Text\";");
          Label puis = new Label(v.getPuissance());
          puis.setStyle("-fx-text-fill:white;-fx-font-family:\"Javanese Text\";");
          Label kilo = new Label(v.getKilometrage());
          kilo.setStyle("-fx-text-fill:white;-fx-font-family:\"Javanese Text\";");
          Label nbr = new Label(String.valueOf(v.getNbrdeplace()));
          nbr.setStyle("-fx-text-fill:white;-fx-font-family:\"Javanese Text\";");
          Label pr = new Label(String.valueOf(v.getPrix()));
          pr.setStyle("-fx-text-fill:white;-fx-font-family:\"Javanese Text\";");
          Label ty = new Label(String.valueOf(v.getTypevehicule_id()));
          ty.setStyle("-fx-text-fill:white;-fx-font-family:\"Javanese Text\";");
          HBox immatriculation = new HBox();
          immatriculation.setMinWidth(90);
          immatriculation.setPrefWidth(90);
          immatriculation.setMaxWidth(90);
          immatriculation.getChildren().add(im);
          immatriculation.setAlignment(Pos.CENTER);
          HBox marque = new HBox();
          marque.setMinWidth(90);
          marque.setPrefWidth(90);
          marque.setMaxWidth(90);
          marque.getChildren().add(mar);
          marque.setAlignment(Pos.CENTER);
          HBox puissance = new HBox();
          puissance.setMinWidth(90);
          puissance.setPrefWidth(90);
          puissance.setMaxWidth(90);
          puissance.getChildren().add(puis);
          puissance.setAlignment(Pos.CENTER);
          HBox kilometrage = new HBox();
          kilometrage.setMinWidth(90);
          kilometrage.setPrefWidth(90);
          kilometrage.setMaxWidth(90);
          kilometrage.getChildren().add(kilo);
          kilometrage.setAlignment(Pos.CENTER);
          HBox nbrdeplace = new HBox();
          nbrdeplace.setMinWidth(90);
          nbrdeplace.setPrefWidth(90);
          nbrdeplace.setMaxWidth(90);
          nbrdeplace.getChildren().add(nbr);
          nbrdeplace.setAlignment(Pos.CENTER);
          HBox prix = new HBox();
          prix.setMinWidth(90);
          prix.setPrefWidth(90);
          prix.setMaxWidth(90);
          prix.getChildren().add(pr);
          prix.setAlignment(Pos.CENTER);
          HBox typevehicule_id = new HBox();
          typevehicule_id.setMinWidth(90);
          typevehicule_id.setPrefWidth(90);
          typevehicule_id.setMaxWidth(90);
          typevehicule_id.getChildren().add(ty);
          typevehicule_id.setAlignment(Pos.CENTER);
          VBox choix = new VBox();
          choix.setSpacing(10);
          choix.setMinWidth(90);
          choix.setPrefWidth(90);
          choix.setMaxWidth(90);
          choix.setAlignment(Pos.CENTER);
          Button supprimer = new Button();
          supprimer.setStyle("-fx-background-color: #9ACD32;-fx-cursor:hand;-fx-text-fill:white;");
          supprimer.setMinHeight(35);
          supprimer.setMinWidth(75);
          supprimer.setText("Supprimer");
          supprimer.setOnAction(e -> supprimer(v));
           Button modifier = new Button();
          modifier.setText("Modifier");
          modifier.setStyle("-fx-background-color: #9ACD32;-fx-cursor:hand;-fx-text-fill:white;");
          modifier.setMinHeight(35);
          modifier.setMinWidth(75);
          modifier.setOnAction(e -> modifier(v));
          
          choix.getChildren().setAll(supprimer,modifier);
         HBox row = new HBox();
         row.setMinHeight(100);
         row.setStyle("-fx-background-color:black;-fx-background-radius:10;");
         row.setAlignment(Pos.CENTER);
         row.setSpacing(10);
         HBox.setHgrow(row, Priority.ALWAYS);
         row.getChildren().setAll(immatriculation,marque,puissance,kilometrage,nbrdeplace,prix,typevehicule_id,choix);
          _vehicule_list_.getChildren().add(row);
        });
    }

      private void supprimer(vehicule v) {
        new vehiculeService().supprimer(v);
        new Alert(Alert.AlertType.INFORMATION,"Suppression avec success!",ButtonType.OK).show();
        refresh();
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("Ajoutervehicule.fxml"));
        Parent root = Loader.load();
        _vehicule_list_.getScene().setRoot(root);
        
    }

    private void modifier(vehicule v) {
        try{
          FXMLLoader Loader = new FXMLLoader(getClass().getResource("Modifiervehicule.fxml"));
        Parent root = Loader.load();
        ModifiervehiculeController controller = Loader.getController();
        controller.passData(v);
        _vehicule_list_.getScene().setRoot(root);
        }catch(Exception ignored){
            
        }
    }
    
}
