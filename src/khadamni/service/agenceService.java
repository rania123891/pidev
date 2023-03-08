
package khadamni.service;

import entity.agence;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import khadamni.tools.MaConnection;

public class agenceService implements Interface<agence> {

    Connection cnx;

    public agenceService() {
        cnx = MaConnection.getInstance().getCnx();
    }

    @Override
    public void ajouter(agence f) {
        try {
            String sql = "insert into utilisateur(nom, prenom, num, adresse, photo, description, rate, type , verified, role,mdp) "
                    + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, f.getNom());
            ste.setString(2, f.getPrenom());
            ste.setInt(3, f.getNumtel());
            ste.setString(4, f.getAdresse());
            ste.setString(5, f.getPhoto());
            ste.setString(6, f.getDescription());
            ste.setInt(7, f.getRate());
            ste.setString(8, f.gettype());
            ste.setBoolean(9, f.isVerified());
            ste.setString(10, "agence");
            ste.setString(11, f.getMdp());
            ste.executeUpdate();
            System.out.println("agence ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<agence> afficher() {
        List<agence> agences = new ArrayList<>();
        try {
            String sql = "select * from utilisateur where role = 'agence' ";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
                agence f = new agence(s.getInt(1), s.getInt(4), s.getString("nom"), s.getString("prenom"), s.getString("adresse"), s.getString("role"), s.getString("photo"), s.getString("description"), s.getInt("rate"), s.getString("type"), s.getBoolean("verified"), s.getString("mdp"));
                agences.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return agences;
    }

    @Override
    public agence findById(int id) {
        agence f = null;
        try {
            String sql = "select * from utilisateur where id = ?";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);
            ResultSet s = ste.executeQuery();
            while (s.next()) {
                  f = new agence(s.getInt(1), s.getInt(4), s.getString("nom"), s.getString("prenom"), s.getString("adresse"), s.getString("role"), s.getString("photo"), s.getString("description"), s.getInt("rate"), s.getString("type"), s.getBoolean("verified"), s.getString("mdp"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return f;
    }

    @Override
    public void supprimer(int id) {
        String sql = "delete from utilisateur where id = ?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);
            ste.executeUpdate();
            System.out.println("agence supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(String nom ,int id) {
     String sql = "update utilisateur set nom=? where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, nom);
            ste.setInt(2,id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
     public agence session(String nom , String mdp) {
          
          agence f = null;
    
          try {
            String sql = "select * from utilisateur where nom = ? and mdp = ?  ";
            
              PreparedStatement ste = cnx.prepareStatement(sql);
             ste.setString(1, nom );
             ste.setString(2, mdp );
            ResultSet s = ste.executeQuery();
            while (s.next()) {

                 f = new agence(s.getInt(1), s.getInt(4), s.getString("nom"), s.getString("prenom"), s.getString("adresse"), s.getString("role"), s.getString("photo"), s.getString("description"), s.getInt("rate"), s.getString("type"), s.getBoolean("verified"), s.getString("mdp"));


            }
         
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return f;
    }
     public void logout(int id) {
        LocalDateTime now = LocalDateTime.now(); // current date and time
        ; // replace with the IDU you're searching for

// build the query to find the most recent session for the given IDU
        try {
            String query1 = "SELECT id, date_debut FROM session WHERE idu = ? AND date_debut <= ? ORDER BY date_debut DESC LIMIT 1";
            PreparedStatement ps = cnx.prepareStatement(query1);

            ps.setInt(1, id);
            Timestamp timestamp = Timestamp.valueOf(now);
            ps.setTimestamp(2, timestamp);

            // execute the query
            try (ResultSet rs = ps.executeQuery()) {

                // if we found a session, update its end date to the current date and time
                if (rs.next()) {
                    int sessionId = rs.getInt("id");
                    LocalDateTime sessionStart = rs.getTimestamp("date_debut").toLocalDateTime();

                    String query2 = "UPDATE session SET date_fin = ? WHERE id = ?";
                    try (PreparedStatement ps2 = cnx.prepareStatement(query2)) {
                        ps2.setTimestamp(1, Timestamp.valueOf(now));
                        ps2.setInt(2, sessionId);
                        ps2.executeUpdate();
                    }

                } else {
                    System.out.println("No session found for IDU ");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}



