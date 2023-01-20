package com.forpus.forpus_inventory.controller;


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

public class SettingController {
    @FXML
    private static Label label;

    public static void changeText(){
        label.setText("Hello");
    }
}
