/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxdashtest;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Leon
 */
public class FXMLDocumentController implements Initializable {
    
    Meters meters = new Meters();
    
    @FXML
    private TextField ean, postcode;
    public Label label;
    public Button btn_login;
    
      
    @FXML
    private void handleButtonAction(ActionEvent event) {  

        // test if button works
        // System.out.println("You clicked me!");
        try {
        int ean_nummer = Integer.parseInt(ean.getText());
        if (meters.check(ean_nummer, postcode.getText())){
            label.setText("");
             try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDashboard.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                
                FXMLDashboardController controller=fxmlLoader.<FXMLDashboardController>getController();
                controller.setIndex(ean.getText());
                
                
                Stage newstage = new Stage();
                newstage.setScene(new Scene(root1));  
                newstage.show();
                
                //close start stage
                nonBtnClick(event);
                
                
                } catch(Exception e) {
                    e.printStackTrace();
                }
        }else {
            label.setText("De inloggevens zijn incorrect!");
        }
        } catch(NumberFormatException e) {
            label.setText("Oops, u heeft niks ingevuld!");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    } 
    
    @FXML
    private void nonBtnClick(ActionEvent event) {
    ((Node) event.getSource()).getScene().getWindow().hide();//close currentstage
    }
    
}
