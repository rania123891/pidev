/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.agence;

import java.io.File;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import khadamni.service.agenceService;
import khadamni.utilisateurfx;

/**
 * FXML Controller class
 *
 * @author lordg
 */
public class agencehomeController implements Initializable {

    @FXML
    private ImageView pdp;
    private int id;
    @FXML
    private  Text desc;
    @FXML
    private Button logout;
    @FXML
    private Text nom;
    @FXML
    private Text num;
    @FXML
    private Text adr;

    /**
     * Initializes the controller class.
     */
    public void initializeprem(agence f) {
        String imagePath = "";
        if (f != null) {
            imagePath = f.getPhoto();
        } else {
            imagePath = "C:\\Users\\lordg\\Desktop\\anonyme.jpg";
        }
        
        Image image = new Image(new File(imagePath).toURI().toString());
        pdp.setImage(image);
        pdp.setFitHeight(250);
        pdp.setFitWidth(200);
        nom.setText(f.getNom());
        nom.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        desc.setText(f.getDescription());
        desc.setFont(Font.font("Serif", FontWeight.BOLD, 16));
        adr.setText(f.getAdresse());
        adr.setFont(Font.font("Serif", FontWeight.BOLD, 16));
        num.setText(String.valueOf(f.getNumtel()));
        num.setFont(Font.font("Dialog", FontWeight.BOLD, 16));
       /* Text nameText = new Text(f.getNom());
        nameText.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        Text signText = new Text("J");
        signText.setFont(Font.font("Verdana", FontWeight.BOLD, 28));
         HBox hbox = new HBox(10, pdp, nameText, signText);
        hbox.setPadding(new Insets(10));
        StackPane root = new StackPane(hbox);*/
        
        // Créer une scène avec le panneau de pile et l'afficher
       
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setid(int i) {
        this.id = i;
    }

    @FXML
    private void logout(ActionEvent event) {
        agenceService fs = new agenceService();
        fs.logout(id);
        //rja3t lil login   
        try {

            Parent page1
                    = FXMLLoader.load(getClass().getResource("/gui/session.fxml"
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
