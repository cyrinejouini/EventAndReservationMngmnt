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
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.dateTime;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import static java.util.Date.parse;
import java.util.ResourceBundle;
import static java.util.logging.Level.parse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.derby.impl.sql.execute.CurrentDatetime;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class AjoutevFXMLController implements Initializable {

    @FXML
    private Label ver_nom;
    @FXML
    private Label ver_prenom;
    @FXML
    private Label ver_tel;
    @FXML
    private Label ver_mot;
    @FXML
    private Label ver_rep;
    @FXML
    private Label erreurMail;
    @FXML
    private Label ver_user;
    @FXML
    private Label path;
    @FXML
    private ImageView img_view;
    @FXML
    private JFXTextField tf_nom;
    @FXML
    private JFXTextField tf_description;
    @FXML
    private JFXTextField tf_nbplace;
    @FXML
    private JFXButton btn_image;
    @FXML
    private JFXButton btn_valider;
    @FXML
    private DatePicker dev;
     File imageFile;
String uploadPath ="C:\\wamp64\\www\\ProjetMobile\\";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void CheckNom(KeyEvent event) {
    }

    @FXML
    private void CheckPrenom(KeyEvent event) {
//        if (tf_description.getText().trim().length() > 0  ) {
//             try {
//                int i = Integer.parseInt(tf_nbplace.getText());
//            } catch (NumberFormatException e) {
//                 ver_prenom.setText("Correct ");
//            ver_prenom.setTextFill(Color.GREEN);
//            ver_prenom.setVisible(true);
//            } }
//            else 
//            {
//            ver_prenom.setText("nombre de place invalide !!");
//            ver_prenom.setTextFill(Color.RED);
//            ver_prenom.setVisible(true);
//        }
    }

    @FXML
    private void CheckUser(KeyEvent event) {
        if (tf_nbplace.getText().trim().length() > 0  ) {
              {
                 ver_user.setText("Correct ");
            ver_user.setTextFill(Color.GREEN);
            ver_user.setVisible(true);
             }}
            else 
            {
            ver_user.setText("nombre de place invalide !!");
            ver_user.setTextFill(Color.RED);
            ver_user.setVisible(true);
        }
    }
 private void ShowImage() {

        
        System.out.println("clicked");
       
        File newfile = new File(uploadPath+imageFile.getName());
        Image image = new Image(newfile.toURI().toString());
        img_view.setImage(image);
    }
   
    @FXML    
    private void onSelectImage(){
        SelectImage();
    }
    
     public void SelectImage(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("GIF", "*.gif"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "/Desktop"));
        Stage stage =new Stage();
        imageFile = fileChooser.showOpenDialog(stage);
        if(imageFile!=null){
            path.setText(imageFile.getName());
        }
        ShowImage();


    }
      public void upload(){

        System.out.println(uploadPath+imageFile.getName());
            File newfile = new File(uploadPath+imageFile.getName());
            FileChannel src = null;
            try {
                src = new FileInputStream(imageFile).getChannel();
                FileChannel dest = new FileOutputStream(newfile).getChannel();
                dest.transferFrom(src, 0, src.size());
                dest.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    
    

    @FXML
    private void createvntAc(ActionEvent event) throws SQLException {
       
        Eventtt e=new Eventtt();
    System.out.println(imageFile.getName());
       
     
     
        int i = Integer.parseInt(tf_nbplace.getText());
        EventtService es = new EventtService();
        
            e.setNom_event(tf_nom.getText());
            e.setDescription(tf_description.getText());
            e.setNb_place(i);
            e.setDate(java.sql.Date.valueOf(dev.getValue()));
            e.setImage(imageFile.getName());
            upload();
            es.createEvent(e);
            System.out.println("Created!");
            
        } 
    
}  
