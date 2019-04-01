/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syrinejava;

import Entite_ev.Eventtt;
import Entite_ev.Ligne_Res;
import Service.EventtService;
import Service.ReservationService;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author amine
 */
public class SyrineJava extends Application {
    
   
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws SQLException {
        launch(args);
        
         
//        
//Eventtt ev = new Eventtt(12,new Date(2018,05,03), "event123", "event parfait cyrine",2, "image");
//        
//        ReservationService a= new ReservationService();
//       
//        Ligne_Res b=new Ligne_Res(12,2,0);
//        
//        a.annuler(b);
//                
     
        
     
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Interface_ev/ListeEvFXML.fxml"));
        Scene scene = new Scene(root);
     EventtService es=new EventtService();
    
        
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

 

   
    
}
