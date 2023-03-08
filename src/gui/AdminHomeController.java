/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Admin;
import entity.Category;
import entity.agence;
import entity.Utilisateur;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import khadamni.service.AdminService;
import khadamni.service.CategoryService;
import khadamni.service.agenceService;
import khadamni.service.UtilisateurService;
import khadamni.utilisateurfx;

/**
 * FXML Controller class
 *
 * @author lordg
 */
public class AdminHomeController implements Initializable {

    @FXML
    private ListView<Utilisateur> utilisateur;
    @FXML
    private TextField recherche;
    private int id;
    private boolean visible = true;
    @FXML
    private Button supprimer;
    @FXML
    private Button PDF;
    @FXML
    private Button modifier;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField num;
    @FXML
    private TextField verified;
    @FXML
    private TextField description;
    @FXML
    private TextField proffesion;
    @FXML
    private TextField adresse;
    @FXML
    private TextField mdp;
    @FXML
    private VBox vbox;
    @FXML
    private Text nomt;
    @FXML
    private Text prenomt;
    @FXML
    private Text numt;
    @FXML
    private Text adresset;
    @FXML
    private Text mdpt;
    @FXML
    private Text descriptiont;
    @FXML
    private Text proffessiont;
    @FXML
    private Text verifiedt;
    int i=0;
    @FXML
    private Button ajouter;
    @FXML
    private RadioButton cf;
    @FXML
    private ImageView pdp;
    @FXML
    private Button log;
    @FXML
    private ChoiceBox<String> choix;
    @FXML
    private Button show;
   private String imagePath =null;
    private String c="";
    @FXML
    private ChoiceBox<String> verif;
    @FXML
    private ChoiceBox<String> prof;
    /**
     * Initializes the controller class.
     */
    public void initializeprem(Admin a){
        ObservableList<String> options = FXCollections.observableArrayList(
    "admin", "utilisateur", "agence");
choix.setItems(options);
 ObservableList<String> option = FXCollections.observableArrayList(
    "true", "false");
verif.setItems(option);
 ObservableList<String> o = FXCollections.observableArrayList(
    "true", "false");
 List<String> nomsCategories = new ArrayList<>();
 CategoryService cs = new CategoryService();
 List<Category> categories = cs.afficher();
for (Category category : categories) {
    nomsCategories.add(category.getNom());
}
prof.getItems().addAll(nomsCategories);

AdminService as = new AdminService();
       List<Utilisateur> list = new ArrayList<>();
       list = as.afficherall();
       utilisateur.getItems().clear();
        utilisateur.getItems().addAll(list);
        
        
    }
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML
    private void recherche(KeyEvent event) {
    recherche.textProperty().addListener((observable, oldValue, newValue) -> {
        // exécuter la recherche
        UtilisateurService us = new UtilisateurService();
        Utilisateur u = us.findById(Integer.parseInt(newValue));
        agenceService fs = new agenceService();
        agence f = fs.findById(Integer.parseInt(newValue));
        AdminService as = new AdminService();
        Admin a = as.findById(Integer.parseInt(newValue));
        List<Utilisateur> results = new ArrayList<>();
        if(u!=null){
           results.add(u); 
        }
        if(f!=null){
            results.add(f);
        }
        if(a!=null){
          results.add(a);  
        }
        

        // mettre à jour la liste de résultats
        utilisateur.getItems().clear();
        utilisateur.getItems().addAll(results);
    });
}
    public void setid(int data){
        this.id=data;
    }

    @FXML
    private Utilisateur getuser(MouseEvent event) {
       
    Utilisateur u = utilisateur.getSelectionModel().getSelectedItem();
    UtilisateurService us = new UtilisateurService();
     agence f =null;
       
      if(u.getRole()!="user"){
            System.err.println("hello");
          f = us.convert(u.getId());
          
          if(f.getPhoto()!=null){
            imagePath = f.getPhoto(); 
          }else{
             // imagePath ="C:\\Users\\lordg\\Desktop\anonyme.jpg";
          //imagePath ="C:/Users/lordg/Desktop/anonyme.jpg";
          imagePath ="C:\\Users\\lordg\\Desktop\\anonyme.jpg";
          }
           Image image = new Image(new File(imagePath).toURI().toString());
           pdp.setImage(image);



          description.setText(f.getDescription());
            prof.setValue(f.gettype());
            verif.setValue(Boolean.toString(f.isVerified()));
}
        nom.setText(u.getNom());
          prenom.setText(u.getPrenom());
          num.setText(String.valueOf(u.getNumtel()));
          mdp.setText(u.getAdresse());
          adresse.setText(u.getAdresse());
         
          

    
        return u;
            }

    @FXML
    private void supp(ActionEvent event) {
       Utilisateur u = utilisateur.getSelectionModel().getSelectedItem();
       UtilisateurService us = new UtilisateurService();
       agenceService fs =new agenceService();
    if (u == null) {
        // No user selected, do nothing
        return;
    }
    us.supprimer(u.getId());
     
      
    
   nom.setText("");
          prenom.setText("");
          num.setText("");
          mdp.setText("");
          adresse.setText(""); 
      description.setText("");
            proffesion.setText("");
            verified.setText("");
             utilisateur.refresh();
    utilisateur.refresh();
    }

    @FXML
    private void modif(ActionEvent event) {
         Utilisateur u = utilisateur.getSelectionModel().getSelectedItem();
    UtilisateurService us = new UtilisateurService();
    agenceService fs =new agenceService();
     if (u == null) {
        // No user selected, do nothing
        return;
    }
    agence f =null;
      if(u.getRole()!="user"){
            
          f = us.convert(u.getId());
          fs.modifier(nom.getText(), f.getId());
      }
      us.modifier(nom.getText(), u.getId());
      nom.setText("");
          prenom.setText("");
          num.setText("");
          mdp.setText("");
          adresse.setText(""); 
      description.setText("");
            proffesion.setText("");
            verified.setText("");
       utilisateur.refresh();
    }
    @FXML
    private void generePDFS(ActionEvent event){
        AdminService as = new AdminService();
        try {
            as.createPDF(utilisateur);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("PDF créé avec succès !");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Une erreur s'est produite");
            alert.setContentText("Impossible de créer le PDF : " + e.getMessage());
            alert.showAndWait();
        }
    }



    @FXML
    private void ajout(ActionEvent event) {

      Alert alert = new Alert(Alert.AlertType.INFORMATION);
    visible = true;
        CategoryService cs =new CategoryService();
        List<Category> categories = new ArrayList<>();
     categories = cs.afficher();
     
     
    try {
        if(num.getText() != null) {
            // Vérifier que le nom ne dépasse pas 20 caractères
            if (nom.getText().length() > 20) {
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText(null);
                alert.setContentText("Le nom ne doit pas dépasser 20 caractères.");
                alert.show();
                return;
            }
            if (prenom.getText().length() > 20) {
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText(null);
                alert.setContentText("Le nom ne doit pas dépasser 20 caractères.");
                alert.show();
                return;
            }
            // Vérifier que le numéro de téléphone est composé de 8 chiffres
            if (!num.getText().matches("\\d{8}")) {
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText(null);
                alert.setContentText("Le numéro de téléphone doit être composé de 8 chiffres.");
                alert.show();
                return;
            }
            
            // Vérifier que le mot de passe comporte au moins 8 caractères
            if (mdp.getText().length() < 8) {
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText(null);
                alert.setContentText("Le mot de passe doit comporter au moins 8 caractères.");
                alert.show();
                return;
            }
            
            
            Utilisateur u = new Utilisateur(Integer.parseInt(num.getText()), nom.getText(), prenom.getText(), adresse.getText(), "user", "00000");
            UtilisateurService us = new UtilisateurService();
            agenceService fs = new agenceService();
             
            
            agence f = null;
            if (cf.isSelected()) {
               
                int i=0;
               
            

                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Select Image File");
                File selectedFile = fileChooser.showOpenDialog(null);
                String imagePath = selectedFile.getAbsolutePath();
                f = new agence(nom.getText(), prenom.getText(), Integer.parseInt(num.getText()), adresse.getText(), imagePath, description.getText(), 0, prof.getValue().toString(), Boolean.parseBoolean(verif.getValue().toString()), mdp.getText());
                fs.ajouter(f);
      
                alert.setTitle("Ajout d'utilisateur");
                alert.setHeaderText(null);
                alert.setContentText("agence ajouté.");
                alert.show();
            }else {
                us.ajouter(u);
                
                alert.setTitle("Ajout d'utilisateur");
                alert.setHeaderText(null);
                alert.setContentText("Utilisateur ajouté.");
                alert.show();
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Une erreur est survenue lors de l'ajout de l'utilisateur.");
        alert.show();
    }
    
    nom.setText("");
    prenom.setText("");
    num.setText("");
    mdp.setText("");
    adresse.setText(""); 
    description.setText("");
    proffesion.setText("");
    verified.setText("");
    utilisateur.refresh();
    }

    @FXML
    private void logout(ActionEvent event) {
         AdminService us = new AdminService();
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
        
    

    @FXML
    private void show(ActionEvent event) {
        
        this.c=choix.getValue().toString();
        AdminService as = new AdminService();
        
        List<Utilisateur> l1 = new ArrayList<>();
         List<agence> l2 = new ArrayList<>();
          List<Admin> l3 = new ArrayList<>();
        if(c=="utilisateur"){
            l1 = as.getAlluser();
             utilisateur.getItems().clear();
        utilisateur.getItems().addAll(l1);
        }
        if(c=="agence"){
            l2 = as.getAllagence();
             utilisateur.getItems().clear();
        utilisateur.getItems().addAll(l2);
        }
        if(c=="admin"){
            l3 = as.afficher();
             utilisateur.getItems().clear();
        utilisateur.getItems().addAll(l3);
        }
        
       
    }
}
    
    
