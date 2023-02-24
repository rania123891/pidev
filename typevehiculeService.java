/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;
import entities.typevehicule;
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
public class typevehiculeService implements IService<typevehicule> {
    
    private final Connection cnx;
    private PreparedStatement ps;
    
     public typevehiculeService() {
        cnx = MyDB.getInstance().getCnx();
    }
         @Override
    public void ajouter(typevehicule t) throws SQLException {
      
              

        String req = "INSERT INTO typevehicule(id,nom,description ) "+"values (?,?,?)";
        
        try{
        ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getId());
        ps.setString(2, t.getNom());
        ps.setString(3, t.getDescription());
        
        ps.executeUpdate();
        
         System.out.println("type vehicule ajoutée");
        }
      
            catch (SQLException ex) {
            
        }
    }
    
    @Override
    public void modifier(typevehicule t) throws SQLException {
        String req = "UPDATE typevehicule SET nom = ?,description = ?"+" WHERE `id`=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(3, t.getId());
        ps.setString(1, t.getNom());
        ps.setString(2, t.getDescription());
     

        ps.executeUpdate();
        
    }
      @Override
    public void supprimer(typevehicule t) throws SQLException {
         String req = "DELETE FROM typevehicule WHERE id = ? ";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1,t.getId());
        
        ps.executeUpdate();
    }
    
    @Override
    public List<typevehicule> recuperer()  {
        try{
        List<typevehicule> Tvehicules = new ArrayList<>();
        String s = "select * from typevehicule";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            typevehicule v1 = new typevehicule();
            v1.setId(rs.getInt("id"));
            v1.setNom(rs.getString("nom"));
            v1.setDescription(rs.getString("description"));
            
           
            
            
            Tvehicules.add(v1);
            
        }
        return Tvehicules;
        }
        catch(Exception ignored){
            System.out.println(ignored.getMessage()  );
                return null;
        }
    }

    public void supprimer(vehicule v) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }



}


