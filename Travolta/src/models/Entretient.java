/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.time.LocalDate;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class Entretient {
    private int  IdEntretient ; 
   private int PrixEntretient ; 
   private LocalDate  DateRetour ; 
   private  String TypeEntretien ;

    public Entretient(int IdEntretient, int PrixEntretient, LocalDate DateRetour, String TypeEntretien) {
        this.IdEntretient = IdEntretient;
        this.PrixEntretient = PrixEntretient;
        this.DateRetour = DateRetour;
        this.TypeEntretien = TypeEntretien;
    }

    public Entretient(int PrixEntretient, LocalDate DateRetour, String TypeEntretien) {
        this.PrixEntretient = PrixEntretient;
        this.DateRetour = DateRetour;
        this.TypeEntretien = TypeEntretien;
    }

    public int getIdEntretient() {
        return IdEntretient;
    }

    public int getPrixEntretient() {
        return PrixEntretient;
    }

    public LocalDate getDateRetour() {
        return DateRetour;
    }

    public String getTypeEntretien() {
        return TypeEntretien;
    }

    public void setIdEntretient(int IdEntretient) {
        this.IdEntretient = IdEntretient;
    }

    public void setPrixEntretient(int PrixEntretient) {
        this.PrixEntretient = PrixEntretient;
    }

    public void setDateRetour(LocalDate DateRetour) {
        this.DateRetour = DateRetour;
    }

    public void setTypeEntretien(String TypeEntretien) {
        this.TypeEntretien = TypeEntretien;
    }

    @Override
    public String toString() {
        return "Entretient{" + "IdEntretient=" + IdEntretient + ", PrixEntretient=" + PrixEntretient + ", DateRetour=" + DateRetour + ", TypeEntretien=" + TypeEntretien + '}';
    }
   
   

   
    
   
}
