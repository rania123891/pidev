/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;
import entities.vehicule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author lenovo
 */
public class vehiculeService  implements IService<vehicule> {
    private final Connection cnx = MyDB.getInstance().getCnx();;
    private PreparedStatement ps;
    
  
    @Override
    public void ajouter(vehicule t) {
        String req = "INSERT INTO vehicule(immatriculation,marque,puissance,kilometrage ,nbrdeplace , prix, typevehicule_id ) "+"values (?,?,?,?,?,?,?)";
        
        try{
        ps = cnx.prepareStatement(req);
        ps.setString(1, t.getImmatriculation());
        ps.setString(2, t.getMarque());
        ps.setString(3, t.getPuissance());
        ps.setString(4, t.getKilometrage());
        ps.setInt(5, t.getNbrdeplace());
        ps.setFloat(6, t.getPrix());
        ps.setInt(7, t.getTypevehicule_id());
        ps.executeUpdate();
        
         System.out.println("vehicule ajoutée");
        }
      
            catch (SQLException ex) {
            
        }
    }
    
    @Override
    public void   modifier(vehicule t)  {
      try{
        String req = "UPDATE vehicule SET marque = ?,puissance = ?,kilometrage = ?,nbrdeplace = ?, prix= ? ,typevehicule_id = ?"+" WHERE `immatriculation`=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getMarque());
        ps.setString(2, t.getPuissance());
        ps.setString(3, t.getKilometrage());
        ps.setInt(4, t.getNbrdeplace());
        ps.setFloat(5, t.getPrix());
        ps.setInt(6, t.getTypevehicule_id());
        ps.setString(7, t.getImmatriculation());
     

        ps.executeUpdate();}
      catch(Exception l) {}        
    }
      @Override
    public void supprimer(vehicule t) {
        try{
            String req = "DELETE FROM vehicule WHERE immatriculation = ? ";
           PreparedStatement ps = cnx.prepareStatement(req);
           ps.setString(1, t.getImmatriculation());
           ps.executeUpdate();
        }catch(Exception ignored){
            
        }
    }
    
    @Override
    public List<vehicule> recuperer() {
        try{
        List<vehicule> vehicules = new ArrayList<>();
        String s = "select * from vehicule";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            vehicule v = new vehicule();
            v.setImmatriculation(rs.getString("immatriculation"));
            v.setMarque(rs.getString("marque"));
            v.setPuissance(rs.getString("puissance"));
            v.setKilometrage(rs.getString("kilometrage"));
            v.setNbrdeplace(rs.getInt("nbrdeplace"));
             v.setPrix(rs.getFloat("prix"));
             v.setTypevehicule_id(rs.getInt("typevehicule_id"));
           
            
            
            vehicules.add(v);
            
        }
        return vehicules;
        }
        catch (Exception ignored){
            System.out.println(ignored.getMessage());
            return null;
        }
    }



}
