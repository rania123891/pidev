/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author DELL
 */
public class Mecanicien {
   private int  IdMecanicien      ; 
   private String NomMecanicien         ; 
   private  float SalaireMecanicien   ;
   private String Disponibilite;

    public Mecanicien(int IdMecanicien, String NomMecanicien, float SalaireMecanicien, String Disponibilite) {
        this.IdMecanicien = IdMecanicien;
        this.NomMecanicien = NomMecanicien;
        this.SalaireMecanicien = SalaireMecanicien;
        this.Disponibilite = Disponibilite;
    }

    public Mecanicien(String NomMecanicien, float SalaireMecanicien, String Disponibilite) {
        this.NomMecanicien = NomMecanicien;
        this.SalaireMecanicien = SalaireMecanicien;
        this.Disponibilite = Disponibilite;
    }
   
   

    public Mecanicien() {
    }

    public int getIdMecanicien() {
        return IdMecanicien;
    }

    public String getNomMecanicien() {
        return NomMecanicien;
    }

    public float getSalaireMecanicien() {
        return SalaireMecanicien;
    }

    public String getDisponibilite() {
        return Disponibilite;
    }

    public void setIdMecanicien(int IdMecanicien) {
        this.IdMecanicien = IdMecanicien;
    }

    public void setNomMecanicien(String NomMecanicien) {
        this.NomMecanicien = NomMecanicien;
    }

    public void setSalaireMecanicien(float SalaireMecanicien) {
        this.SalaireMecanicien = SalaireMecanicien;
    }

    public void setDisponibilite(String Disponibilite) {
        this.Disponibilite = Disponibilite;
    }

    @Override
    public String toString() {
        return "Mecanicien{" + "IdMecanicien=" + IdMecanicien + ", NomMecanicien=" + NomMecanicien + ", SalaireMecanicien=" + SalaireMecanicien + ", Disponibilite=" + Disponibilite + '}';
    }
    
    
   

   
    
}
