/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entite_ev.Eventtt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import technique.DataSource;

/**
 *
 * @author amine
 */
public class EventtService {
     Connection connection;
     
      public EventtService() {
        connection = DataSource.getInsatance().getConnection();
    }  
      private Logger logger = Logger.getLogger(EventtService.class.getName());
     
    public void createEvent(Eventtt e) throws SQLException {
   
        if (connection != null) {
            // requete
            String req =  "INSERT INTO `event`(`date`,"
                    + " `nom`,"
                    + " `description`,"
                    + " `nombreplace`, `image`, `visibi`) VALUES (?,?,?,?,?,?)";
 
            try ( // statement
                    PreparedStatement statment = connection.prepareStatement(req)) {
                statment.setDate(1, e.getDate());
                statment.setString(2, e.getNom_event());
                statment.setString(3, e.getDescription());
                statment.setInt(4, e.getNb_place());
                statment.setString(5, e.getImage());
                statment.setInt(6, 1);
                
                // execute statement
                statment.executeUpdate();
                // close statement
            }
       

            // show info
            logger.info("Evenement ajouté");
        } else {
            // log error
            logger.log(Level.SEVERE, "Erreur de connexion.");

            // throw exception
            throw new SQLException("Erreur de connexion.");
        }
    
    }
    public void deleteEvent(Eventtt e) throws SQLException {
         if (connection != null) {
            // requete
            String req ="DELETE FROM `event` WHERE id=?";

            // statement
            PreparedStatement statment = connection.prepareStatement(req);
            statment.setInt(1, e.getId_event());
             System.out.println("aaaaaa");
           

            // execute statement
            statment.executeUpdate();

            // close statement
            statment.close();

            // show info
            logger.info("evenement supprimé");
        } else {
            // log error
            logger.log(Level.SEVERE, "Erreur de connexion.");

            // throw exception
            throw new SQLException("Erreur de connexion.");
        }
    
        
    }
     public List<Eventtt> getAllEvent() throws SQLException {

        List<Eventtt> events = new ArrayList<>();
        String req = "select * from event";
        Statement stm = connection.prepareStatement(req);
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Eventtt e =  new Eventtt (rst.getInt("id"),rst.getDate("date"), rst.getString("nom") ,rst.getString("description"), 
                      rst.getInt("nombreplace"),  rst.getString("image"));
            events.add(e);
            System.out.println(e);
        }
        return events;
    }
     
     public void updateEvent(Eventtt e) {
         try {
            String req = "UPDATE event SET date='" + e.getDate() +"',nom='" + e.getNom_event() +"',description='" +e.getDescription() +"' ,nombreplace='" + e.getNb_place() +"',image= '" + e.getImage() + "' WHERE id=" +e.getId_event();
            Statement st=connection.createStatement();
            st.executeUpdate(req);
             System.out.println("modifié");
         } catch (Exception ex) {
             Logger.getLogger(EventtService.class.getName()).log(Level.SEVERE, null,ex);
         }
     }
    public void updateEvent5(int a,int x) {
         try {
            String req = "UPDATE event SET nombreplace=nombreplace +"+x+" WHERE id="+a;
            Statement st=connection.createStatement();
            st.executeUpdate(req);
             System.out.println("reservation effectuée");
         } catch (Exception ex) {
             Logger.getLogger(EventtService.class.getName()).log(Level.SEVERE, null,ex);
         }
     }
}
