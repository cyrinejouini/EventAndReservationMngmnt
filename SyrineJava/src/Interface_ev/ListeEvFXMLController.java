/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface_ev;

import Entite_ev.Eventtt;
import Service.EventtService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import static sun.font.FontManagerNativeLibrary.load;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class ListeEvFXMLController implements Initializable {

    @FXML
    private TableView<Eventtt> table_user;
    @FXML
    private TableColumn<Eventtt, String> col_user;
    @FXML
    private TableColumn<Eventtt, String> col_nom;
    @FXML
    private TableColumn<Eventtt, Date> col_prenom;
    @FXML
    private TableColumn<Eventtt, Integer> col_role;
   
    @FXML
    private JFXButton btn_supprimer;
    @FXML
    private JFXButton btn_modif;
    @FXML
    private JFXTextField tx_pre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_supprimer.setDisable(true);
        btn_modif.setDisable(true);
        try {
            load();
        } catch (SQLException ex) {
            Logger.getLogger(ListeEvFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } }
        private void load() 
                throws SQLException{
         EventtService es=new EventtService();
         Eventtt e = new Eventtt();
        ArrayList eventList= (ArrayList) es.getAllEvent();
        ObservableList eventObservable= FXCollections.observableArrayList(eventList); //declaration filtre
        
   FilteredList<Eventtt> filteredData;
        filteredData = new FilteredList<>(eventObservable,f->true);
        //wfé declaration
        table_user.setItems(eventObservable);
        
        col_user.setCellValueFactory(new PropertyValueFactory<>("nom_event"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_role.setCellValueFactory(new PropertyValueFactory<>("nb_place"));
       
       //nsovbb l be9i
         tx_pre.textProperty().addListener((observableValue,oldValue,newValue)->{
			filteredData.setPredicate((Predicate<? super Eventtt>)f->{
                            String t=String.valueOf(e.getNb_place());//bech tconverti el int l string
				if(newValue==null||newValue.isEmpty()){
					return true;
				}
				String lowerCaseFilter=newValue.toLowerCase();
				if(f.getNom_event().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
				
                                else if(f.getDescription().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
                                
                                else if(t.toLowerCase().contains(lowerCaseFilter)){
					return true;}
                                
				return false;
			});
		});
		SortedList<Eventtt> sortedData=new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind( table_user.comparatorProperty());
		 table_user.setItems(sortedData);
        // TODO
    }
        // TODO
   

    @FXML
    private void DeletUser(ActionEvent event) throws SQLException {
         EventtService es=new EventtService();
        Eventtt e=table_user.getSelectionModel().getSelectedItem();
        es.deleteEvent(e);
        load();
        
       
    }

    @FXML
    private void UpdateInfo(ActionEvent event) {
    }
        @FXML
    private void mouse_tabl(MouseEvent event) {
       
              int i =table_user.getSelectionModel().getSelectedIndex();
               if (i>=0) {
                   btn_supprimer.setDisable(false);
                  btn_modif.setDisable(false);
                   
               }
    }

    @FXML
    private void DeletUser(MouseEvent event) {
    }

    @FXML
    private void UpdateInfo(MouseEvent event) {
    }
    
}
