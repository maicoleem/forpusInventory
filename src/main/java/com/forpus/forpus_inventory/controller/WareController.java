package com.forpus.forpus_inventory.controller;

import com.forpus.forpus_inventory.HelloApplication;
import com.forpus.forpus_inventory.domain.services.Constant;
import com.forpus.forpus_inventory.domain.services.ConstantsWare;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class WareController {
    @FXML
    public TableView tabla;
    @FXML
    public TableColumn c1;
    @FXML
    public TableColumn c2;
    @FXML
    public TableColumn c3;
    @FXML
    public TableColumn c4;
    @FXML
    public TableColumn c5;
    @FXML
    public TableColumn c6;
    @FXML
    public TableColumn c7;
    @FXML
    public TableColumn c8;
    @FXML
    public TableColumn c9;
    @FXML
    public Button found;
    @FXML
    public Button cancel;
    @FXML
    public Button save;
    @FXML
    public Button search;
    @FXML
    public Button remove;
    @FXML
    public Button buttonWarePB;
    @FXML
    public Button buttonDashPB;
    @FXML
    public Button buttonAccountingPB;
    @FXML
    public Button buttonBuyPB;
    @FXML
    public Button buttonSalePB;
    @FXML
    public Button buttonSettingsPB;
    @FXML
    public Button buttonCategory;
    @FXML
    public Button buttonWare;
    @FXML
    public Button buttonService;
    @FXML
    public Button buttonTransmute;
    @FXML
    public Label labelCode;
    @FXML
    public Label labelProduct;
    @FXML
    public Label labelOneCategory;
    @FXML
    public Label labelTwoCategory;
    @FXML
    public Label labelThreeCategory;
    @FXML
    public Label labelWage;
    @FXML
    public Label labelBuy;
    @FXML
    public Label labelSale;
    @FXML
    public Label labelProfit;
    @FXML
    public TextField tfCode;
    @FXML
    public TextField tfProduct;
    @FXML
    public TextField tfBuy;
    @FXML
    public TextField tfProfit;
    @FXML
    public TextField tfSale;
    @FXML
    public ComboBox comboBoxThree;
    @FXML
    public ComboBox comboBoxOne;
    @FXML
    public ComboBox comboBoxTwo;
    @FXML
    public ComboBox comboBoxWage;
    @FXML
    public Button buttonProducts;
    @FXML
    public TextField tfOneCategory;
    @FXML
    public TextField tfThreeCategory1;
    @FXML
    public Label labelCost;
    @FXML
    public TextField tfCost;
    @FXML
    public Label labelConsumed;
    @FXML
    public TextField tfConsumed;

    @FXML

    public void buttonCRUD(ActionEvent event) {
    }

    @FXML
    private void buttonSlide(ActionEvent event) throws IOException {
        slide(event);
    }

    @FXML
    static void slide(ActionEvent event) throws IOException {
        Button buttonSlide = (Button) event.getSource();

        if(Constant.greyToBlueSlide == null){
            Constant.greyToBlueSlide = buttonSlide;
            buttonSlide.setStyle("-fx-background-color: #F5F5F5; ");
        } else if (Constant.greyToBlueSlide == buttonSlide) {
            buttonSlide.setStyle("-fx-background-color: #F5F5F5; ");
        }else{
            Constant.greyToBlueSlide.setStyle("-fx-background-color: #C2C2C2; ");
            buttonSlide.setStyle("-fx-background-color: #F5F5F5; ");
            Constant.greyToBlueSlide = buttonSlide;
        }
        slideChange(buttonSlide.getId(), event);
    }

    public void alertSend(String massage){
        Alert alertMassage = new Alert(Alert.AlertType.INFORMATION);
        alertMassage.setTitle("Freya Style--//--Forpus Company");
        alertMassage.setContentText(massage);
        alertMassage.show();
    }

    static void slideChange(String blue, ActionEvent event) throws IOException {
        switch (blue){
            case "bWare":
                FXMLLoader fxmlLoaderWare = new FXMLLoader(HelloApplication.class.getResource("ware-view.fxml"));
                Scene sceneWare = new Scene(fxmlLoaderWare.load());
                Stage stageWare = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stageWare.setTitle("Forpus Inventory");
                stageWare.setScene(sceneWare);
                stageWare.show();
                break;
            case "bDash":
                break;
            case "bAccounting":
                break;
            case "bBuy":
            case "bSale":
                break;
            case "bSettings":
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("settings-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Forpus Inventory");
                stage.setScene(scene);
                stage.show();
                break;
            default:
                break;
        }

    }

    @FXML
    public void buttonsOptions(ActionEvent event) {
        Button buttonOption = (Button) event.getSource();

        if(ConstantsWare.blueToWhite == null){
            ConstantsWare.blueToWhite = buttonOption;
            buttonOption.setStyle("-fx-background-color: #F5F5F5; ");
        } else if (ConstantsWare.blueToWhite == buttonOption) {
            buttonOption.setStyle("-fx-background-color: #F5F5F5; ");
        }else{
            ConstantsWare.blueToWhite.setStyle("-fx-background-color: #1BA1E2; ");
            buttonOption.setStyle("-fx-background-color: #F5F5F5; ");
            ConstantsWare.blueToWhite = buttonOption;
        }

        ConstantsWare.idOption = buttonOption.getId();
        options(ConstantsWare.idOption);

    }
    protected void options(String option){
        clean();
        switch (option){
            case "buttonCategory":
                labelCode.setText("Categoria 1");
                labelOneCategory.setText("Categoria 2");
                labelThreeCategory.setText("Categoria 3");

                labelThreeCategory.setVisible(true);
                tfOneCategory.setVisible(true);
                tfThreeCategory1.setVisible(true);

                break;
            case "buttonWare":
                labelCode.setText(Constant.lblCode);
                labelOneCategory.setText("Bodega");

                tfOneCategory.setVisible(true);

                break;
            case "buttonService":
                labelCode.setText(Constant.lblCode);
                labelOneCategory.setText("Producto");
                labelTwoCategory.setText("Precio");
                labelThreeCategory.setText("Profit");
                labelBuy.setText("Salario");
                labelProfit.setText("Horas");
                labelProduct.setText("Servicio");
                labelSale.setText("Contenido");


                comboBoxOne.setVisible(true);
                comboBoxWage.setVisible(true);
                comboBoxTwo.setVisible(true);

                tfThreeCategory1.setVisible(true);
                tfBuy.setVisible(true);
                tfProfit.setVisible(true);
                tfProduct.setVisible(true);
                tfSale.setVisible(true);
                tfCost.setVisible(true);
                tfConsumed.setVisible(true);

                labelBuy.setVisible(true);
                labelSale.setVisible(true);
                labelProfit.setVisible(true);
                labelProduct.setVisible(true);
                labelTwoCategory.setVisible(true);
                labelWage.setVisible(true);
                labelThreeCategory.setVisible(true);
                labelCost.setVisible(true);
                labelConsumed.setVisible(true);

                break;
            case "buttonProducts":

                labelCode.setText(Constant.lblCode);
                labelOneCategory.setText("Categoria 1");
                labelThreeCategory.setText("Categoria 3");

                labelTwoCategory.setText("Categoria 2");
                labelBuy.setText("Precio Compra");
                labelProfit.setText("Profit");
                labelProduct.setText("Producto");
                labelSale.setText("Precio Venta");

                labelBuy.setVisible(true);
                labelSale.setVisible(true);
                labelProfit.setVisible(true);
                labelProduct.setVisible(true);
                labelTwoCategory.setVisible(true);
                labelWage.setVisible(true);
                labelThreeCategory.setVisible(true);

                comboBoxOne.setVisible(true);
                comboBoxTwo.setVisible(true);
                comboBoxThree.setVisible(true);
                comboBoxWage.setVisible(true);

                tfBuy.setVisible(true);
                tfProduct.setVisible(true);
                tfSale.setVisible(true);
                tfProfit.setVisible(true);


                break;
            case "buttonTransmute":
                break;
            default:
                break;
        };

    }

    protected void clean(){

        labelBuy.setVisible(false);
        labelSale.setVisible(false);
        labelProfit.setVisible(false);
        labelProduct.setVisible(false);
        labelTwoCategory.setVisible(false);
        labelWage.setVisible(false);
        labelThreeCategory.setVisible(false);
        labelCost.setVisible(false);
        labelConsumed.setVisible(false);

        comboBoxOne.setVisible(false);
        comboBoxTwo.setVisible(false);
        comboBoxThree.setVisible(false);
        comboBoxWage.setVisible(false);

        tfBuy.setVisible(false);
        tfProduct.setVisible(false);
        tfSale.setVisible(false);
        tfProfit.setVisible(false);
        tfOneCategory.setVisible(false);
        tfThreeCategory1.setVisible(false);
        tfCost.setVisible(false);
        tfConsumed.setVisible(false);

        tfCode.setText("");
        tfBuy.setText("");
        tfProduct.setText("");
        tfSale.setText("");
        tfProfit.setText("");

        tabla.getItems().clear();

    }
}
