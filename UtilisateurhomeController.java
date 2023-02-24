/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Category;
import entity.Utilisateur;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import khadamni.service.CategoryService;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import khadamni.service.UtilisateurService;
import khadamni.utilisateurfx;

/**
 * FXML Controller class
 *
 * @author lordg
 */
public class UtilisateurhomeController implements Initializable {

    @FXML
    private TableView<Category> category;
    @FXML
    private TableColumn<Category, ImageView> image;
    @FXML
    private TableColumn<Category, String> nom;
    @FXML
    private TableColumn<Category, String> description;
    @FXML
    private TableColumn<Category, Integer> nb;
    @FXML
    private Button logout;
    private int id;
    @FXML
    private ImageView pdp;
    @FXML
    private Label nomu;
    private String photo;
    private String name;

    /**
     * Initializes the controller class.
     */
    public void setid(int data){
        this.id=data;
    }
    public void initializeprem(Utilisateur f){
        
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UtilisateurService us =new UtilisateurService();
       Utilisateur u = us.findById(id);
       if(u!=null){
           System.err.println("hello world");
       }else {
           System.err.println("rien");
       }
        

        CategoryService cs = new CategoryService();
        List<Category> categorys = cs.afficher();
        ObservableList<Category> observableList = FXCollections.observableArrayList(categorys);

        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        nb.setCellValueFactory(new PropertyValueFactory<>("nb"));
        image.setCellValueFactory(cellData -> {
            String imagePath = cellData.getValue().getImage();

            if (imagePath != null) {
                File file = new File(imagePath);
                Image image = new Image(file.toURI().toString());
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);
                return new SimpleObjectProperty<>(imageView);
            } else {
                return new SimpleObjectProperty<>(null);
            }
        });

        category.setItems(observableList);
    }

    @FXML
    private void logout(ActionEvent event) {
        //date_fin_session
        UtilisateurService us = new UtilisateurService();
        us.logout(id);
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
