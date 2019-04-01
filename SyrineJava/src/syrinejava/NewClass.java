/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syrinejava;

import Entite_ev.Eventtt;
import Service.EventtService;
import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author amine
 */
public class NewClass {
     public static void main(String[] args) throws SQLException {
         Eventtt ev = new Eventtt(new Date(2018,05,03), "event", "event parfait cyrine",2, "image");
        
        EventtService es = new EventtService (); 
       es.deleteEvent(ev);
     }
    
}
