package com.forpus.forpus_inventory.controller;

import com.forpus.forpus_inventory.domain.services.Constant;
import com.forpus.forpus_inventory.domain.services.ConstantsAccounting;
import com.forpus.forpus_inventory.domain.services.ConstantsWare;
import com.forpus.forpus_inventory.domain.services.TableShow;
import com.forpus.forpus_inventory.persistence.crud.FoundHQL;
import com.forpus.forpus_inventory.persistence.crud.SaveHQL;
import com.forpus.forpus_inventory.persistence.crud.SearchHQL;
import com.forpus.forpus_inventory.persistence.entity.ProductpriceClass;
import com.forpus.forpus_inventory.persistence.entity.TaxesClass;
import com.forpus.forpus_inventory.persistence.entity.WareProductClass;
import com.forpus.forpus_inventory.persistence.entity.WarehouseClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class AccountingController {
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
    @FXML
    public TextField tfBold;
    @FXML
    public TextField tfIva;
    @FXML
    public Button buttonTaxes;
    public Button buttonStock;
    public Button buttonAccounting;
    public Button buttonInput;
    public Button buttonReceivable;
    public Label labelIVA;
    public Label labelBold;
    public ComboBox<String> comboBoxWare;
    public ComboBox comboBoxProduct;
    public TableView<TableShow> tableMain;
    public TableColumn<Object, Object> c1;
    public TableColumn<Object, Object> c2;
    public TableColumn<Object, Object> c3;
    public TableColumn<Object, Object> c4;

    public TableColumn<Object, Object> c5;

    @FXML
    protected void buttonSlide(ActionEvent event) throws IOException {
        WareController.slide(event);

    }
    @FXML //botones del CRUD
    public void buttonCRUD(ActionEvent event) {

        Button buttonCRUD = (Button) event.getSource();

        if (Constant.blueToWhite == null) {
            Constant.blueToWhite = buttonCRUD;
            buttonCRUD.setStyle("-fx-background-color: #F5F5F5; ");
        } else if (Constant.blueToWhite == buttonCRUD) {
            buttonCRUD.setStyle("-fx-background-color: #F5F5F5; ");
        } else {
            Constant.blueToWhite.setStyle("-fx-background-color: #1BA1E2; ");
            buttonCRUD.setStyle("-fx-background-color: #F5F5F5; ");
            Constant.blueToWhite = buttonCRUD;
        }
        crudEjecuted(buttonCRUD.getId());
    }

    public void crudEjecuted(String idButton) {

        switch (idButton) {
            case "save":

                saveDates();
                if (SaveHQL.workerInsertUpdate()) {
                    if (Objects.equals(Constant.messageSave, "Creado")) {
                       WareController.alertSend("Se han guardado los datos");
                    } else {
                        WareController.alertSend("Se han actualizado los datos");
                    }
                } else {
                    WareController.alertSend("Error en actualizar y/o insertar datos, por favor reiniciar");
                }

                break;
            case "search":

                break;
            case "remove":
                crudEjecuted("cancel");
                break;
            case "cancel":
                //textFields
                clean();
                break;
            case "found":
                    differentiateBetweenEntities(SearchHQL.searchHQL());
                break;
            default:
                break;
        }
    }

    private void saveDates() {

        switch (Constant.entity){
            case "TaxesClass":
                for(TaxesClass tx: ConstantsAccounting.taxesList){
                    if(Objects.equals(tx.getIdTaxes(), "IVA")){
                        tx.setTaxes(tfIva.getText());
                    }
                    if(Objects.equals(tx.getIdTaxes(), "BOLD")){
                        tx.setTaxes(tfBold.getText());
                    }
                }
            default:
                break;
        }
    }

    private void differentiateBetweenEntities(boolean workerFound) {
        switch (Constant.entity){
            case "TaxesClass":
                for(TaxesClass tx: ConstantsAccounting.taxesList){
                    if(Objects.equals(tx.getIdTaxes(), "IVA")){
                        tfIva.setText(tx.getTaxes());
                    }
                    if(Objects.equals(tx.getIdTaxes(), "BOLD")){
                        tfBold.setText(tx.getTaxes());
                    }
                }
                save.setDisable(false);
                break;
            default:
                break;
        }
    }

    @FXML
    public void buttonsOptions(ActionEvent event) {
        Button button = (Button) event.getSource();
        switch (button.getId()){
            case "buttonTaxes":
                Constant.entity = "TaxesClass";
                clean();
                labelBold.setVisible(true);
                labelIVA.setVisible(true);

                labelIVA.setText("IVA");
                labelBold.setText("BOLD");

                tfBold.setVisible(true);
                tfIva.setVisible(true);
                break;
            case "buttonStock":
                clean();
                Constant.entity = "WarehouseClass";
                labelIVA.setVisible(true);
                labelIVA.setText("Bodega");
                labelBold.setVisible(true);
                labelBold.setText("Producto");
                comboBoxProduct.setVisible(true);
                comboBoxWare.setVisible(true);
                comboBoxLoad();
                tableMain.setVisible(true);

                /*
                Debe de mostrar todos los poductos, filtro por bodega y producto
                Debe de mostar el dinero que se tiene en el inventatio, precio de compra
                ¿Debe de mostrar las utilidades esperadas?
                * */
                break;
            case "buttonAccounting":
                /*Debe de mostrar el registro de los clientes, la empresa,
                 los trabajadores, los socios y los proveedores
                * */
                Constant.entity = "Accounting";
                break;
            case "buttonInput":
                //Se debe de porder hacer ingresos de dinero a la cuenta de la compañia
                Constant.entity = "PartnersClass";
                break;
            case "buttonReceivable":
                //Se debe de poder visualizar las deudas que tiene la empresa
                // y las deudas de los cleintes de la empresa
                Constant.entity = "facture";
                break;
            default:
                break;
        }


    }
    public void comboBoxLoad(){
     comboBoxWare.getItems().clear();
     comboBoxProduct.getItems().clear();
        ArrayList<String> listComboBox = new ArrayList<>();
        switch (Constant.entity){
            case "WarehouseClass":
                //Carga las Bodegas en el comboBox
                SearchHQL.searchHQL();
                for (int i = 0; i < ConstantsWare.wareList.length; i = i + 1) {
                    String product = ConstantsWare.wareList[i].getIdWarehouse();
                    if (product != null) {
                        listComboBox.add(product);
                    }
                }
                comboBoxWare.getItems().addAll(listComboBox);
                //Carga los productos
                Constant.entity = "ProductClass";
                listComboBox.clear();
                SearchHQL.searchHQL();
                for (int i = 0; i < ConstantsWare.productList.length; i = i + 1) {
                    String product = ConstantsWare.productList[i].getName();
                    if (product != null) {
                        listComboBox.add(product);
                    }
                }
                //regresa constante
                Constant.entity = "WarehouseClass";
                break;
            default:
                break;
        }
    }
    public void comboBoxClick(ActionEvent event) {
        ComboBox comboBox = (ComboBox) event.getSource();
        tableLoad(Constant.entity, comboBox.getId());
    }

    public void tableLoad(String entity, String idComboBox){
        tableMain.getItems().clear();
        Constant.listTableShow.clear();
        c1.setText("C1");
        c2.setText("C2");
        c3.setText("C3");
        c4.setText("C4");
        c5.setText("C5");

        try{
            switch (entity){
                case "WarehouseClass":
                    //carga todos los productos que hay en esa bodega
                    /*producto, cantidad, precio
                    * */
                    if(Objects.equals(idComboBox, "comboBoxWare")){
                        //obtiene la lista de productos en la bodega
                        ConstantsWare.wareProductList = null;
                        Constant.tfCode = comboBoxWare.getValue();
                        FoundHQL.workerFound();
                        ConstantsWare.wareProductList = ConstantsWare.ware.getWareProductsByIdWarehouse().toArray(new WareProductClass[0]);
                        //Obtiene lista de productPrice
                        Constant.entity = "ProductpriceClass";
                        SearchHQL.searchHQL();
                        Constant.entity = "WarehouseClass";
                        for(WareProductClass wP: ConstantsWare.wareProductList){
                            for(ProductpriceClass pP: ConstantsWare.productPriceList){
                                if(wP.getIdWareProduct() == pP.getIdProductWare()){
                                    TableShow tableShow = new TableShow();
                                    Constant.entity = "ProductClass";
                                    Constant.tfCode = wP.getIdProduct();
                                    FoundHQL.workerFound();
                                    Constant.entity = "WarehouseClass";
                                    tableShow.setC1(ConstantsWare.product.getName());
                                    tableShow.setC2(String.valueOf(pP.getAmount()));
                                    tableShow.setC3(String.valueOf(pP.getPrice()));
                                    tableShow.setC4(ConstantsWare.product.getSalePrice());
                                    Constant.listTableShow.add(tableShow);
                                }
                            }
                        }
                    }

                    c1.setText("Produto");
                    c1.setCellValueFactory(new PropertyValueFactory<>("c1"));
                    c2.setText("Cantidad");
                    c2.setCellValueFactory(new PropertyValueFactory<>("c2"));
                    c3.setText("Precio");
                    c3.setCellValueFactory(new PropertyValueFactory<>("c3"));
                    c4.setText("Precio Venta");
                    c4.setCellValueFactory(new PropertyValueFactory<>("c4"));
                    ObservableList<TableShow> datesTTT = FXCollections.observableArrayList(Constant.listTableShow);
                    tableMain.setItems(datesTTT);

                    break;
                default:
                    break;
            }
        }catch (Exception i){
            System.out.println(i + " Error al cargar la tabla (tableLoad)");
            i.printStackTrace();
        }

    }

    public void numeric(KeyEvent keyEvent) {

        switch (Constant.entity){
            case "TaxesClass":
                TextField tf = (TextField) keyEvent.getSource();

                int b = Character.getNumericValue(keyEvent.getCharacter().charAt(0));
                String a = String.valueOf(keyEvent.getCharacter().charAt(0));

                if (Character.isSpaceChar(keyEvent.getCharacter().charAt(0)) || b > 9 || a.equals(",")) {
                    tf.deleteText(tf.getText().length() - 1, tf.getText().length());
                }
                if(tf.getText().length() > 4){
                    tf.deleteText(4 , tf.getText().length());
                }

                if(!tf.getText().isEmpty()){
                    for (int i=0;i<tf.getText().length()-1;i++){
                        String s = String.valueOf(tf.getText().charAt(i));
                        System.out.println(s);
                        if(s.equals(".") && s.equals(a)){
                            tf.deleteText(tf.getText().length() - 1, tf.getText().length());
                        }
                    }

                    String texto = tf.getText();
                    double db = Double.valueOf(texto);
                    if(db>100.0){
                        tf.setText("100");
                    }
                }
            default:
                break;
        }
    }
    public void clean(){
        labelBold.setVisible(false);
        labelIVA.setVisible(false);

        tfBold.setVisible(false);
        tfIva.setVisible(false);

        tfBold.setText("");
        tfIva.setText("");
        save.setDisable(true);

        comboBoxProduct.setVisible(false);
        comboBoxWare.setVisible(false);

        tableMain.setVisible(false);

    }


}
