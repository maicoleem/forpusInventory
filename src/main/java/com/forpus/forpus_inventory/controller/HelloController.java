package com.forpus.forpus_inventory.controller;

import com.forpus.forpus_inventory.HelloApplication;
import com.forpus.forpus_inventory.persistence.crud.SingUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloController {
    @FXML
    private Label lblSingUp;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField password;

    @FXML
    protected void onButtonClick(ActionEvent event) throws IOException {
        SingUp.name = userName.getText();
        SingUp.password = password.getText();
        System.out.println(SingUp.name +" / "+ SingUp.password);
        SingUp.companySingUP();
        if(SingUp.companySingUP()){
            lblSingUp.setText("Success");

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("settings-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Forpus Inventory");
            stage.setScene(scene);
            stage.show();

        }else{
            lblSingUp.setText("Error in password or user name");
        }

    }

    public void setting(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SettingController.class.getResource("settings-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Forpus Inventory");
        stage.setScene(scene);
        stage.show();
    }
}