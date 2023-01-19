package com.forpus.forpus_inventory;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label label;

    @FXML
    protected void onButtonClick() {
        label.setText("Welcome to JavaFX Application!");
    }
}