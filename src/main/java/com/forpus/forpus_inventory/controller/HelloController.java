package com.forpus.forpus_inventory.controller;

import com.forpus.forpus_inventory.HelloApplication;
import com.forpus.forpus_inventory.domain.services.Constant;
import com.forpus.forpus_inventory.persistence.Session.SessionDB;
import com.forpus.forpus_inventory.persistence.crud.DataBase;
import com.forpus.forpus_inventory.persistence.crud.SingUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;
import java.io.IOException;

/**<h1>HelloController</h1>
 * If the Class that controller the view, the user input the user and password for sing in.
 * */
public class HelloController {
    public Button buttonConnection;
    public Button buttonLogo;
    public Button buttonLoad;
    @FXML
    private Label lblSingUp;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField password;
    /**
     * This method captures the username and password to log in, sending the date to the Class
     * {@code SingUp} with the method companySingUp. return the view if the sing up was successful.
     * */
    @FXML
    protected void onButtonClick(ActionEvent event) throws IOException {

        if(SingUp.companySingUP(userName.getText(), password.getText())){
            lblSingUp.setText("Success");
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("settings-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(Constant.title());
            stage.setScene(scene);
            stage.show();

        }else{
            lblSingUp.setText("Error in password or user name");
        }

    }
    @FXML
    public void logo() {
        buttonConnection.setVisible(!buttonConnection.isVisible());
        buttonLoad.setVisible(!buttonLoad.isVisible());
    }
    @FXML
    public void connection() {

        try{
            Session session = SessionDB.session();
            if(session.isConnected()){
                buttonConnection.setStyle("-fx-background-color: #31AB00 ;");
                SessionDB.sessionClose();
            }else {
                buttonConnection.setStyle("-fx-background-color: #AB0D00 ;");
            }
        }catch (Exception i){
            buttonConnection.setStyle("-fx-background-color: #AB0D00 ;");
            WareController.alertSend(i.toString());
        }
    }
    @FXML
    public void load() {
        if(DataBase.install()){
            WareController.alertSend("BASE DE DATOS CREADA CORRECTAMENTE");
        }else {
            WareController.alertSend("ERROR AL CARGAR LA BASE DE DATOS");
        }
    }

}