package com.forpus.forpus_inventory.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class SalesController {
    public Button buttonWarePB;
    public Button buttonDashPB;
    public Button buttonAccountingPB;
    public Button buttonBuyPB;
    public Button buttonSalePB;
    public Button buttonSettingsPB;
    public Button found;
    public Button cancel;
    public Button save;
    public Button search;
    public Button remove;
    public Button buttonCategory;
    public Button buttonWare;
    public Button buttonService;
    public Label labelClient;
    public TableView tableMain;
    public Label labelNameClient;
    public TextField tfClient;
    public ComboBox comboBoxAmount;
    public Button buttonClient;
    public Label labelProduct;
    public TextField tfProduct;
    public Label labelNameProduct;
    public Button buttonProduct;
    public Label labelAmount;
    public Label labelPriceBuy;
    public Label labelPriceSale;
    public TextField tfOff;
    public Label labelOff;
    public Label labelNote;
    public TextField tfNote;
    public Button buttonRegister;
    public Pane panelTotal;
    public Label labelBold;
    public Button buttonBold;
    public Label labelIVA;
    public Label labelTotal2;
    public Label labelTotal;
    public Button buttonIVA;
    public Pane panelPayment;
    public Label labelCash;
    public Label labelBank;
    public Label labelSubTotal;
    public TextField tfBank;
    public TextField tfCash;
    public Label labelPay;
    public Pane panelCheckIn;
    public Button buttonCheckIn;
    public Button buttonQuote;
    public Label labelOff2;

    public void clean(){
        
    }
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
