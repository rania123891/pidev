/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import java.time.LocalDateTime ;
/**
 *
 * @author lordg
 */
public class Session {
    
    private int id;
    private String nom;
  private LocalDateTime date ;
 private LocalDateTime datef ;
 
    public Session(Utilisateur u) {
        this.id = u.getId();
        this.nom=u.getNom();
        this.date = LocalDateTime.now();
    }
     public Session(agence f) {
        this.id = f.getId();
        this.nom=f.getNom();
        this.date = LocalDateTime.now();
    }
      public Session(Admin a) {
        this.id = a.getId();
        this.nom=a.getNom();
        this.date = LocalDateTime.now();
    }
     

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getDatef() {
        return datef;
    }

    public void setDatef(LocalDateTime datef) {
        this.datef = datef;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    

    @Override
    public String toString() {
        return "Connection{" + "nom=" + nom + ", date=" + date + ", datef=" + datef + '}';
    }
    
    
}
