package com.forpus.forpus_inventory.controller;

import com.forpus.forpus_inventory.HelloApplication;
import com.forpus.forpus_inventory.domain.services.Constant;
import com.forpus.forpus_inventory.persistence.crud.SearchHQL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.naming.spi.ObjectFactory;
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
    public Button buttonCredit;
    public void clean(){

        panelPayment.setVisible(false);
        panelCheckIn.setVisible(false);
        panelTotal.setVisible(false);
        labelClient.setVisible(false);
        labelNameClient.setVisible(false);
        labelProduct.setVisible(false);
        labelNameProduct.setVisible(false);
        labelAmount.setVisible(false);
        labelPriceBuy.setVisible(false);
        labelPriceSale.setVisible(false);
        labelOff2.setVisible(false);
        labelOff.setVisible(false);
        labelNote.setVisible(false);

        tfClient.setVisible(false);
        tfProduct.setVisible(false);
        tfOff.setVisible(false);
        tfNote.setVisible(false);

        comboBoxAmount.setVisible(false);

        tableMain.setVisible(false);

        buttonProduct.setVisible(false);
        buttonClient.setVisible(false);
        buttonRegister.setVisible(false);



    }
    @FXML
    protected void buttonSlide(ActionEvent event) throws IOException {
        WareController.slide(event);

    }
    @FXML
    public void buttonCRUD(ActionEvent event) {
        Button button = (Button) event.getSource();
        switch (button.getId()){
            case "search":
                Constant.entity = "CustomerClass";
                if(SearchHQL.searchHQL()) {
                    searchCrud();
                }
                break;
            case "cancel":
                clean();
                break;
            default:
                break;
        }
    }

    static void searchCrud() {
        try {
            FXMLLoader search = new FXMLLoader(HelloApplication.class.getResource("search-view.fxml"));
            Parent root = search.load();
            Scene sceneSearch = new Scene(root);
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(sceneSearch);

            stage.showAndWait();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void buttonsOptions(ActionEvent event){
        Button button = (Button) event.getSource();
        switch (button.getId()){
            case "buttonProduct0":
                clean();
                panelPayment.setVisible(true);
                panelCheckIn.setVisible(true);
                panelTotal.setVisible(true);
                labelClient.setVisible(true);
                labelNameClient.setVisible(true);
                labelProduct.setVisible(true);
                labelProduct.setText("Producto");
                labelNameProduct.setVisible(true);
                labelAmount.setVisible(true);
                labelPriceBuy.setVisible(true);
                labelPriceSale.setVisible(true);
                labelOff2.setVisible(true);
                labelOff.setVisible(true);
                labelNote.setVisible(true);

                tfClient.setVisible(true);
                tfProduct.setVisible(true);
                tfOff.setVisible(true);
                tfNote.setVisible(true);

                comboBoxAmount.setVisible(true);

                tableMain.setVisible(true);

                buttonProduct.setVisible(true);
                buttonClient.setVisible(true);
                buttonRegister.setVisible(true);

                Constant.entity = "CustomerClass";

                break;
            case "buttonService":
                clean();
                break;
            case "buttonCredit":
                clean();
                break;
            default:
                break;
        }
    }
}
