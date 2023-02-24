/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author lenovo
 */
public class vehicule {
    private String immatriculation,marque,puissance,kilometrage;
    private int nbrdeplace;
    private float prix;
    private int typevehicule_id;

    

    public vehicule() {
    }

    public vehicule(String immatriculation, String marque, String puissance, String kilometrage, int nbrdeplace , float prix , int typevehicule_id ) {
        this.immatriculation = immatriculation;
        this.marque = marque;
        this.puissance = puissance;
        this.kilometrage = kilometrage;
        this.nbrdeplace = nbrdeplace;
        this.prix = prix;
        this.typevehicule_id = typevehicule_id;
    }

    public vehicule(String marque, String puissance, String kilometrage, int nbrdeplace, float prix , int typevehicule_id) {
        this.marque = marque;
        this.puissance = puissance;
        this.kilometrage = kilometrage;
        this.nbrdeplace = nbrdeplace;
        this.prix = prix;
        this.typevehicule_id = typevehicule_id;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getPuissance() {
        return puissance;
    }

    public void setPuissance(String puissance) {
        this.puissance = puissance;
    }

    public String getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(String kilometrage) {
        this.kilometrage = kilometrage;
    }

    public int getNbrdeplace() {
        return nbrdeplace;
    }

    public void setNbrdeplace(int nbrdeplace) {
        this.nbrdeplace = nbrdeplace;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getTypevehicule_id() {
        return typevehicule_id;
    }

    public void setTypevehicule_id(int typevehicule_id) {
        this.typevehicule_id = typevehicule_id;
    }

    @Override
    public String toString() {
        return "vehicule{" + "immatriculation=" + immatriculation + ", marque=" + marque + ", puissance=" + puissance + ", kilometrage=" + kilometrage + ", nbrdeplace=" + nbrdeplace + ", prix=" + prix + ", typevehicule_id=" + typevehicule_id + '}';
    }




    
}
