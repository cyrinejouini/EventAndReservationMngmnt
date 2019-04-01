/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entite_ev.Ligne_Res;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import technique.DataSource;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amine
 */
public class ReservationService {
     Connection connexion1 ;
             

    public ReservationService() {
        connexion1= DataSource.getInsatance().getConnection();
    }
    EventtService evser=new EventtService();
    ;
    public List<Ligne_Res> getAllRes() throws SQLException {

        List<Ligne_Res> events = new ArrayList<>();
        String req = "select * from lignereservation";
        Statement stm = connexion1.prepareStatement(req);
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Ligne_Res e =  new Ligne_Res(rst.getInt("id"),rst.getInt("event_id"),rst.getInt("quantite"),rst.getInt("reservation_id"));
            events.add(e);
            System.out.println(e);
            System.out.println("salut");
        }
        return events;
    }
    
    public void participer(Ligne_Res ess) throws SQLException
    {List<Ligne_Res> events = new ArrayList<>();
        String req3 = "select * from lignereservation where reservation_id=2 AND event_id="+ess.getEvent_id();
       
        Statement stm1 =connexion1.prepareStatement(req3);
        
        
        ResultSet rst = stm1.executeQuery(req3);

        while (rst.next()) {
            Ligne_Res e =  new Ligne_Res(rst.getInt("id"),rst.getInt("event_id"),rst.getInt("quantite"),rst.getInt("reservation_id"));
        events.add(e);
        } 
        System.out.println(events);
    
        if(events.size()==0)
        {  if (connexion1 != null) {
            // requete
            String req1 =  "INSERT INTO `lignereservation`(`event_id`,"
                    + " `reservation_id`,"
                    
                    + " `quantite`) VALUES (?,?,?)";
 
            try ( // statement
                    PreparedStatement statment = connexion1.prepareStatement(req1)) {
                statment.setInt(1, ess.getEvent_id());
                statment.setInt(2, 2);
                statment.setInt(3,1);
               
                
                // execute statement
                statment.executeUpdate();
                // close statement
            }
            
        }}
        else if(events.size()!=0)
        {try {
            
            String req1 = "UPDATE lignereservation SET quantite=quantite +1 WHERE event_id="+ess.getEvent_id()+"AND reservation_id=2 " ;
            Statement st=connexion1.createStatement();
            st.executeUpdate(req1);
            } catch (Exception ex) {
             
         
        }
        
    
    
    
}evser.updateEvent5(ess.getEvent_id(),-1);
    }

public void annuler(Ligne_Res ess) throws SQLException
{
    List<Ligne_Res> events = new ArrayList<>();
        String req3 = "select * from lignereservation where reservation_id=2 AND event_id="+ess.getEvent_id();
       
        Statement stm1 =connexion1.prepareStatement(req3);
        
        
        ResultSet rst = stm1.executeQuery(req3);

        while (rst.next()) {
            Ligne_Res e =  new Ligne_Res(rst.getInt("id"),rst.getInt("event_id"),rst.getInt("quantite"),rst.getInt("reservation_id"));
        events.add(e);
        } 
        int a = events.get(0).getQuantite();
         if (connexion1 != null) {
            // requete
            String req ="DELETE FROM `lignereservation` WHERE reservation_id=2 and event_id="+ess.getEvent_id();

            // statement
            PreparedStatement statment = connexion1.prepareStatement(req);
            
           

            // execute statement
            statment.executeUpdate();

            // close statement
            statment.close();

            // show info
         }
         evser.updateEvent5(ess.getEvent_id(),a);
        
}
}
