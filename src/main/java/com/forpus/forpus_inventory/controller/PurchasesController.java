package com.forpus.forpus_inventory.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class PurchasesController {
    public Button buttonWarePB;
    public Button buttonDashPB;
    public Button buttonAccountingPB;
    public Button buttonBuyPB;
    public Button buttonSalePB;
    public Button buttonSettingsPB;
    @FXML
    protected void buttonSlide(ActionEvent event) throws IOException {
        WareController.slide(event);

    }
    @FXML
    public void buttonCRUD(ActionEvent event) {
    }
    @FXML
    public void buttonsOptions(ActionEvent event) {
    }
}
