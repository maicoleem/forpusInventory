package com.forpus.forpus_inventory.controller;

import com.forpus.forpus_inventory.domain.services.Constant;
import com.forpus.forpus_inventory.domain.services.ConstantsPurchases;
import com.forpus.forpus_inventory.domain.services.ConstantsWare;
import com.forpus.forpus_inventory.persistence.crud.FoundHQL;
import com.forpus.forpus_inventory.persistence.crud.SearchHQL;
import com.forpus.forpus_inventory.persistence.entity.ProductpriceClass;
import com.forpus.forpus_inventory.persistence.entity.WareProductClass;
import com.forpus.forpus_inventory.persistence.entity.WarehouseClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class PurchasesController {
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
    public Button buttonProduct0;
    public Button buttonService;
    public Button buttonCredit;
    public Label labelProvider;
    public TableView tableMain;
    public Label labelNameProvider;
    public TextField tfProvider;
    public ComboBox<String> comboBoxPrice;
    public Button buttonProvider;
    public Label labelProduct;
    public TextField tfProduct;
    public Label labelNameProduct;
    public Button buttonProduct;
    public Label labelPrice;
    public TextField tfOff;
    public Label labelOff;
    public Button buttonRegister;
    public Pane panelTotal;
    public Label labelBold;
    public Button buttonBold;
    public Label labelIVA;
    public Label labelTotal2;
    public Label labelTotal;
    public Button buttonIVA;
    public Pane panelPayment;
    public Label labelBank;
    public Label labelCash;
    public TextField tfCash;
    public TextField tfBank;
    public Label labelSubTotal;
    public Label labelPay;
    public Pane panelCheckIn;
    public Button buttonCheckIn;
    public Button buttonQuote;
    public TextField tfPrice;
    public Label labelPriceSale;
    public TextField tfPriceSale;
    public Label labelAmount;
    public TextField tfAmount;
    public Label labelProfit;
    public TextField tfProfit;
    public ComboBox comboBoxOne;
    public ComboBox comboBoxTwo;
    public ComboBox comboBoxThree;
    public ComboBox<String> comboBoxWare;
    public CheckBox checkProduct;
    public CheckBox checkSale;

    @FXML
    protected void buttonSlide(ActionEvent event) throws IOException {
        WareController.slide(event);
    }
    @FXML
    public void buttonCRUD(ActionEvent event) {
        SalesController.searchCrud();
    }
    @FXML
    public void buttonsOptions(ActionEvent event) {
        Button button = (Button) event.getSource();
        clear();
        switch (button.getId()){
            case "buttonProduct0":
                ConstantsPurchases.entity = "Purchases";
                final boolean a = true;
                labelProvider.setVisible(a);
                labelNameProvider.setVisible(a);
                labelProduct.setVisible(a);
                labelNameProduct.setVisible(a);
                labelPrice.setVisible(a);
                labelOff.setVisible(a);
                labelAmount.setVisible(a);
                labelProfit.setVisible(a);
                labelPriceSale.setVisible(a);

                tfProvider.setVisible(a);
                tfProduct.setVisible(a);
                tfPrice.setVisible(a);
                tfOff.setVisible(a);
                tfAmount.setVisible(a);
                tfProfit.setVisible(a);
                tfPriceSale.setVisible(a);

                buttonProvider.setVisible(a);
                buttonProduct.setVisible(a);
                buttonRegister.setVisible(a);

                search.setVisible(a);
                save.setVisible(a);
                found.setVisible(a);
                cancel.setVisible(a);
                remove.setVisible(a);

                comboBoxOne.setVisible(a);
                comboBoxTwo.setVisible(a);
                comboBoxThree.setVisible(a);
                comboBoxWare.setVisible(a);
                comboBoxPrice.setVisible(a);

                tableMain.setVisible(a);

                panelCheckIn.setVisible(a);
                panelPayment.setVisible(a);
                panelTotal.setVisible(a);
                break;
            case "buttonService":
                clear();
                break;
            case "buttonCredit":
                clear();
                break;
            default:
                break;
        }
    }

    public void clear(){
        final boolean a = false;
        labelProvider.setVisible(a);
        labelNameProvider.setVisible(a);
        labelProduct.setVisible(a);
        labelNameProduct.setVisible(a);
        labelPrice.setVisible(a);
        labelOff.setVisible(a);
        labelAmount.setVisible(a);
        labelProfit.setVisible(a);
        labelPriceSale.setVisible(a);

        tfProvider.setVisible(a);
        tfProduct.setVisible(a);
        tfPrice.setVisible(a);
        tfOff.setVisible(a);
        tfAmount.setVisible(a);
        tfProfit.setVisible(a);
        tfPriceSale.setVisible(a);

        buttonProvider.setVisible(a);
        buttonProduct.setVisible(a);
        buttonRegister.setVisible(a);

        search.setVisible(a);
        save.setVisible(a);
        found.setVisible(a);
        cancel.setVisible(a);
        remove.setVisible(a);

        comboBoxOne.setVisible(a);
        comboBoxTwo.setVisible(a);
        comboBoxThree.setVisible(a);
        comboBoxWare.setVisible(a);
        comboBoxPrice.setVisible(a);

        tableMain.setVisible(a);

        panelCheckIn.setVisible(a);
        panelPayment.setVisible(a);
        panelTotal.setVisible(a);


    }

    public void providerFound(ActionEvent event) {
        Constant.entity = "ProvidersClass";
        Constant.tfCode = tfProvider.getText();
        FoundHQL.workerFound();
        labelNameProvider.setText(Constant.provider.getName());
        checkSale();
    }
    public void productFound(ActionEvent event) {
        Constant.entity = "ProductClass";
        Constant.tfCode = tfProduct.getText();
        FoundHQL.workerFound();
        labelNameProduct.setText(ConstantsWare.product.getName());
        /*Obtener la lista de precios del producto
        *Crear array donde estaran los precios
        * Hacer un for para buscar en todas las bodegas
        * Hacer un for para buscar los precios
        * Adicionar al combobox
        * */
        ArrayList<String> listProduct = new ArrayList<>();
        for(WareProductClass w: ConstantsWare.product.getWareProductsByIdProduct()){
            for(ProductpriceClass p: w.getProductpricesByIdWareProduct()){
                String price = String.valueOf(p.getPrice());
                listProduct.add(price);
            }
        }
        comboBoxPrice.getItems().addAll(listProduct);

        tfOff.setText("0");
        tfAmount.setText("1");
        tfProfit.setText("30");
    }

    public void checkProduct(ActionEvent event) {
    }

    public void cbPrice(ActionEvent event) {
        ArrayList<String> listProduct = new ArrayList<>();
        for(WareProductClass w: ConstantsWare.product.getWareProductsByIdProduct()){
            String house = w.getIdWare();
            for(ProductpriceClass p: w.getProductpricesByIdWareProduct()){
                String price = String.valueOf(p.getPrice());
                if(price.equals(comboBoxPrice.getValue())){
                    listProduct.add(house);   
                }
            }
        }
        comboBoxWare.getItems().addAll(listProduct);
        tfPrice.setText("0");
        checkSale();
    }

    public void priceNew(KeyEvent keyEvent) {
        //Cuando se agrega un valor al tfPrice da la opcion para agregar a cualquier bodega
        comboBoxPrice.setPromptText("Precio");
        //comboBoxPrice.setValue("");

        ArrayList<String> listProduct = new ArrayList<>();
        Constant.entity = "WarehouseClass";
        SearchHQL.searchHQL();

        for(WarehouseClass w: ConstantsWare.wareList){
            listProduct.add(w.getName());
        }
        comboBoxWare.getItems().clear();
        comboBoxWare.getItems().addAll(listProduct);
        checkSale();
    }

    public void priceSale(){
        int price;
        if(!tfPrice.getText().equals("") && !tfPrice.getText().equals("0")){
            price = Integer.valueOf(tfPrice.getText());
        }else{
            price = Integer.valueOf(comboBoxPrice.getValue());
        }

        int profit = Integer.valueOf(tfProfit.getText());
        int sale = price + (price * profit / 100);
        tfPriceSale.setText(String.valueOf(sale));
    }

    public void checkSale() {
        if(checkSale.isSelected()){
            //precio nuevo
            tfPriceSale.setDisable(false);
            priceSale();
        }else{
            //precio actual
            tfPriceSale.setDisable(true);
            tfPriceSale.setText(ConstantsWare.product.getSalePrice());
        }
    }
}
