package entity;
public class Category {
 
    private int id,nb_service;
    private String nom,description,Image;

    public Category(int nb_service, String nom, String description, String Image) {
        this.nb_service = nb_service;
        this.nom = nom;
        this.description = description;
        this.Image = Image;
    }

    public Category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNb_service() {
        return nb_service;
    }

    public void setNb_service(int nb_service) {
        this.nb_service = nb_service;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", nb_service=" + nb_service + ", nom=" + nom + ", description=" + description + ", Image=" + Image + '}';
    }
    
    
}