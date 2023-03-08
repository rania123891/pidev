/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khadamni.service;
import entity.Session ;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import khadamni.tools.MaConnection;
/**
 *
 * @author lordg
 */
public class SessionSercive  {
java.sql.Connection cnx;

    public SessionSercive() {
        cnx = MaConnection.getInstance().getCnx();
    }
    
    public void ajouter(Session t) {
        try {
            String sql = "insert into session(idu,nom,date_debut)"
                    + "values (?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId());
            ste.setString(2, t.getNom());
            
              Timestamp timestamp = Timestamp.valueOf(t.getDate());
              ste.setTimestamp(3, timestamp);
            
            ste.executeUpdate();
            System.out.println("conx ajouter ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
 
    /*@Override
    public List<Connection> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Connection findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(String nom, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    */
}
