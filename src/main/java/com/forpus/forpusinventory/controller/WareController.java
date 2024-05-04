package com.forpus.forpusinventory.controller;

import com.forpus.forpusinventory.HelloApplication;
import com.forpus.forpusinventory.domain.services.Constant;
import com.forpus.forpusinventory.domain.services.ConstantsAccounting;
import com.forpus.forpusinventory.domain.services.ConstantsWare;
import com.forpus.forpusinventory.domain.services.TableShow;
import com.forpus.forpusinventory.persistence.Session.SessionDB;
import com.forpus.forpusinventory.persistence.crud.DeleteHQL;
import com.forpus.forpusinventory.persistence.crud.FoundHQL;
import com.forpus.forpusinventory.persistence.crud.SaveHQL;
import com.forpus.forpusinventory.persistence.crud.SearchHQL;
import com.forpus.forpusinventory.persistence.entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.hibernate.event.spi.SaveOrUpdateEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class WareController {
    @FXML
    public TableColumn<Object, Object> c1;
    @FXML
    public TableColumn<Object, Object> c2;
    @FXML
    public TableColumn<Object, Object> c3;
    @FXML
    public TableColumn<Object, Object> c4;
    @FXML
    public TableColumn<Object, Object> c5;
    @FXML
    public TableColumn<Object, Object> c6;
    @FXML
    public TableColumn<Object, Object> c7;
    @FXML
    public TableColumn<Object, Object> c8;
    @FXML
    public TableColumn<Object, Object> c9;
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
    public ComboBox<String> comboBoxThree;
    @FXML
    public ComboBox<String> comboBoxOne;
    @FXML
    public ComboBox<String> comboBoxTwo;
    @FXML
    public ComboBox<String> comboBoxWage;
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
    public TextField tfWare;
    @FXML
    public Button bOne;
    @FXML
    public Button bTwo;
    @FXML
    public Button bThree;
    @FXML
    public Button buttonUAM;
    @FXML
    public Button buttonMAU;
    @FXML
    public Button bThreeRemove;
    @FXML
    public Button bTwoRemove;
    @FXML
    public Button buttonSP;
    @FXML
    public TableView tableWare;
    @FXML
    public Label labelProfitSale;
    @FXML
    public Button buttonRTable;
    @FXML
    public ComboBox<String> comboBoxPrice;
    @FXML
    public ComboBox<String> comboBoxWare;
    @FXML
    public ComboBox<String> comboBoxProductTT;
    @FXML
    public Label labelTransmute;
    @FXML
    public Label labelCostTrans;
    @FXML
    public void initialize(){
        ConstantsAccounting.clearConstants();
        SessionDB.sessionClose();
        buttonCategory.setStyle("-fx-background-color: #F5F5F5;");
        options("buttonCategory");
    }
    @FXML //botones del CRUD
    public void buttonCRUD(ActionEvent event) {
        try {
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
        }catch (Exception i){
            i.printStackTrace();
        }
    }
    public void crudEjecuted(String idButton) {
        try{
        switch (idButton) {
            case "save":
                Constant.tfCode = tfCode.getText();

                if (!Objects.equals(Constant.tfCode, "")) {
                    saveDates();
                    if(Constant.entity.equals("Transmute")){
                        if(ConstantsWare.saveTransmute(ConstantsWare.productPriceTransmute, ConstantsWare.pPListArray)){
                            alertSend("Se han guardado los datos");
                            break;
                        }
                    }
                    Constant.tfCode = tfCode.getText();
                    if (SaveHQL.workerInsertUpdate()) {
                        if (Objects.equals(Constant.messageSave, "Creado")) {
                            alertSend("Se han guardado los datos");
                        } else {
                            alertSend("Se han actualizado los datos");
                        }
                    } else {
                        alertSend("Error en actualizar y/o insertar datos, por favor reiniciar");
                    }
                } else {
                    alertSend("Por favor digitar código");
                }

                break;
            case "search":
                if (SearchHQL.searchHQL()) {
                    SalesController.searchCrud();

                } else {
                    Alert alertSearch = new Alert(Alert.AlertType.INFORMATION);
                    alertSearch.setTitle("Freya Style--//--Forpus Company");
                    alertSearch.setContentText("Error al cargar los datos");
                    alertSearch.show();
                }
                break;
            case "remove":
                if (Constant.isEntityForeanKey(Constant.entity)) {

                    DeleteHQL.deleteForean();

                } else {
                    Constant.tfCode = tfCode.getText();
                    if (DeleteHQL.workerDelete()) {
                        alertSend("Datos Eliminados");
                    } else {
                        alertSend("Error al eliminar datos");
                    }
                }
                crudEjecuted("cancel");
                break;
            case "cancel":
                //textFields
                switch (Constant.entity) {
                    case "CategorioneClass":
                    case "CategoritwoClass":
                    case "CategorythreeClass":
                        options("buttonCategory");
                        break;
                    case "buttonWare":
                        options("buttonWare");
                        break;
                    case "buttonService":
                        options("buttonService");
                        break;
                    case "buttonProducts":
                        options("buttonProducts");
                        break;
                    case "buttonTransmute":
                        options("buttonTransmute");
                        break;
                    default:
                }
                break;
            case "found":
                Constant.tfCode = tfCode.getText();
                if (!Objects.equals(Constant.tfCode, "")) {
                    differentiateBetweenEntities(FoundHQL.workerFound());
                } else {
                    alertSend("Por favor digitar código");
                }
                break;
            default:
                break;
        }
        }catch (Exception i){
            i.printStackTrace();
        }
    }
    //fucnion para que el crud haga diferentes cosas dependiendo de la entity
    public void differentiateBetweenEntities(Boolean found) {
        try{
        switch (Constant.entity) {
            case "CategoryoneClass":
                bOne.setDisable(false);
                SearchHQL.searchHQL();
                c1.setText("Categoria Uno");
                c1.setCellValueFactory(new PropertyValueFactory<>("categoryOne"));
                ObservableList<CategoryoneClass> dates = FXCollections.observableArrayList(ConstantsWare.categoryOneList);
                tableWare.getItems().clear();
                tableWare.setItems(dates);
                break;

            case "WarehouseClass":
                //busca y pone datos en la tabla
                tableLoad();
                //busca el codigo de la warehouse y pone el nombre
                if (found) {
                    tfOneCategory.setText(ConstantsWare.ware.getName());
                    save.setDisable(false);
                    remove.setDisable(false);
                } else {
                    alertSend("La bodega no existe");
                    save.setDisable(false);
                }
                break;
            case "ServiceClass":
                if (found) {
                    tfProduct.setText(ConstantsWare.service.getName());
                    tfCost.setText(ConstantsWare.service.getCost());
                    tfConsumed.setText(ConstantsWare.service.getProfit());
                    tfProfit.setText(ConstantsWare.service.getHour());
                    tfBuy.setText(ConstantsWare.service.getPayForHour());

                    if (!ConstantsWare.service.getServiceProductsByIdService().isEmpty()) {
                        Constant.listTableShow.clear();
                        for (ServiceProductClass sp : ConstantsWare.service.getServiceProductsByIdService()) {
                            TableShow tableShow = new TableShow();
                            //producto {buscar el producto con el id}
                            Constant.entity = "ProductClass";
                            Constant.tfCode = sp.getIdProduct();
                            FoundHQL.workerFound();
                            tableShow.setC1(ConstantsWare.product.getName());
                            //precio {buscarlo en la tabla productPrice}
                            tableShow.setC2(sp.getPrice());
                            //contenido
                            tableShow.setC3(sp.getContents());
                            //consumo
                            tableShow.setC4(sp.getConsume());
                            //costo
                            int pC = Integer.parseInt(tableShow.getC2());
                            int cM = Integer.parseInt(tableShow.getC3());
                            int cT = Integer.parseInt(tableShow.getC4());

                            int cost = ((pC * cM) / cT);

                            System.out.println(cost);

                            tableShow.setC5(String.valueOf(cost));
                            Constant.listTableShow.add(tableShow);
                        }
                        System.out.println(Constant.listTableShow);

                        Constant.entity = "ServiceTableShow";
                        tableLoad();
                        Constant.entity = "ServiceClass";

                        Constant.tfCode = tfCode.getText();
                    }
                    save.setDisable(false);
                    remove.setDisable(false);

                } else {
                    alertSend("El servicio no existe");
                    //busca los datos de los servicios
                    Constant.entity = "ServiceClass";
                    //busca y pone datos en la tabla
                    tableLoad();
                    save.setDisable(false);
                }
                //carga los datos del combobox
                Constant.entity = "ServiceClass";
                comboBoxLoad();
                Constant.entity = "ServiceClass";
                break;

            case "ProductClass":
                //busca los datos del servicio
                if (found) {
                    tfProduct.setText(ConstantsWare.product.getName());

                    tfBuy.setText(ConstantsWare.product.getPurchasePrice());

                    tfSale.setText(ConstantsWare.product.getSalePrice());

                    tfProfit.setText(ConstantsWare.product.getProfit());

                    save.setDisable(false);
                    remove.setDisable(false);
                } else {
                    alertSend("El producto no existe");
                    save.setDisable(false);
                }
                Constant.entity = "ProductClass";

                //carga los datos del combobox
                comboBoxLoad();

                Constant.entity = "ProductClass";
                //busca y pone datos en la tabla
                tableLoad();

                Constant.entity = "ProductClass";
                break;
            default:
                break;
        }
        }catch (Exception i){
            System.out.println(i.getMessage());
        }
    }
    //funcion para salvar datos
    public void saveDates() {
        try{
        switch (Constant.entity) {
            case "ProductClass":
                idValorComboBox(comboBoxOne.getValue(), "CategoryoneClass");
                if (ConstantsWare.one != null) {
                    ConstantsWare.tfOne = String.valueOf(ConstantsWare.one.getIdOne());
                    idValorComboBox(comboBoxTwo.getValue(), "CategorytwoClass");

                    if (ConstantsWare.two != null) {
                        ConstantsWare.tfTwo = String.valueOf(ConstantsWare.two.getIdTwo());
                        idValorComboBox(comboBoxThree.getValue(), "CategorythreeClass");

                        if (ConstantsWare.three != null) {
                            ConstantsWare.tfThree = String.valueOf(ConstantsWare.three.getIdThree());
                            System.out.println(ConstantsWare.three);
                        }
                    }
                }
                idValorComboBox(comboBoxWage.getValue(), "WarehouseClass");
                if (ConstantsWare.ware != null) {
                    ConstantsWare.tfWare = ConstantsWare.ware.getIdWarehouse();
                }
                Constant.entity = "ProductClass";
                Constant.tfName = tfProduct.getText();
                Constant.tfCode = tfCode.getText();
                ConstantsWare.tfBuy = tfBuy.getText();
                ConstantsWare.tfSale = tfSale.getText();
                ConstantsWare.tfProfit = tfProfit.getText();
                ConstantsWare.tfConsumed = tfConsumed.getText();
                break;
            case "CategoryoneClass":
            case "WarehouseClass":
                Constant.tfName = tfOneCategory.getText();
                break;
            case "ServiceClass":
                Constant.tfName = tfProduct.getText();
                //añadir el id del producto a la clase service_product
                idValorComboBox(comboBoxOne.getValue(), "ProductClass");
                if (ConstantsWare.product != null) {
                    ConstantsWare.tfOne = String.valueOf(ConstantsWare.product.getIdProduct());
                    //precio
                    idValorComboBox(comboBoxTwo.getValue(), "ProductpriceClass");
                    if (ConstantsWare.productPrice != null) {
                        ConstantsWare.tfTwo = String.valueOf(ConstantsWare.productPrice.getPrice());
                    }
                }
                ServiceClass service = new ServiceClass();
                service.setIdService(tfCode.getText());
                service.setName(tfProduct.getText());
                service.setPayForHour(tfBuy.getText());
                service.setHour(tfProfit.getText());
                service.setCost(tfCost.getText());

                idValorComboBox(comboBoxWage.getValue(), "WarehouseClass");
                if (ConstantsWare.ware != null) {
                    service.setIdWare(ConstantsWare.ware.getIdWarehouse());
                    service.setWarehouseByIdWare(ConstantsWare.ware);
                }
                Constant.entity = "ServiceClass";
                service.setProfit(tfConsumed.getText());
                service.setOff("0");
                ConstantsWare.service = service;

                //Crea lista de objetos service_product
                ConstantsWare.sPListArray.clear();
                if (!Constant.listTableShow.isEmpty()) {

                    for (TableShow t : Constant.listTableShow) {
                        ServiceProductClass sP = new ServiceProductClass();
                        sP.setIdService(Constant.tfCode);
                        sP.setContents(t.getC4());
                        sP.setConsume(t.getC3());
                        sP.setPrice(t.getC2());

                        Constant.entity = "ProductClass";
                        Constant.tfCode = t.getC1();
                        FoundHQL.wareFound();
                        sP.setIdProduct(ConstantsWare.product.getIdProduct());
                        sP.setProductByIdProduct(ConstantsWare.product);
                        ConstantsWare.sPListArray.add(sP);
                    }
                    Constant.entity = "ServiceClass";
                }
                break;
            case "Transmute":
                //crear lista de productPrice que se deben actualizar
                ConstantsWare.pPListArray.clear();
                if (!Constant.listTableShow.isEmpty()) {
                    for (TableShow t : Constant.listTableShow) {
                        ProductpriceClass pP = new ProductpriceClass();
                        pP.setIdPrice(Integer.parseInt(t.getC1()));
                        pP.setIdProductWare(Integer.parseInt(t.getC4()));
                        pP.setAmount(Integer.parseInt(t.getC8()));
                        pP.setPrice(Integer.parseInt(t.getC5()));
                        Constant.entity = "ProductpriceClassID";
                        Constant.tfCode = t.getC1();
                        FoundHQL.wareFound();
                        pP.setWareProductByIdProductWare(ConstantsWare.productPrice.getWareProductByIdProductWare());
                        ConstantsWare.pPListArray.add(pP);
                    }
                    Constant.entity = "Transmute";
                }
                break;
            default:
                break;
        }
        }catch (Exception i){
            i.printStackTrace();
        }
    }
    //botones check para categorias pricnipalmente
    public void buttonCheck(ActionEvent event) {
        try{
        Button buttonCheck = (Button) event.getSource();
        switch (buttonCheck.getId()) {
            case "bOne":
                Constant.entity = "CategoryoneClass";
                Constant.tfName = tfCode.getText();
                ConstantsWare.two = null;
                ConstantsWare.three = null;

                if (ConstantsWare.one != null) {
                    Constant.tfCode = String.valueOf(ConstantsWare.one.getIdOne());
                    if (SaveHQL.saveUpdateCate("update")) {
                        bOne.setDisable(true);
                        bTwo.setDisable(false);
                        tfCode.setDisable(true);
                        tfOneCategory.setDisable(false);
                        remove.setDisable(false);
                    }
                } else {
                    if (SaveHQL.saveUpdateCate("save")) {
                        bOne.setDisable(true);
                        bTwo.setDisable(false);
                        tfCode.setDisable(true);
                        tfOneCategory.setDisable(false);
                        remove.setDisable(false);
                    }
                }
                Constant.entity = "CategorytwoClass";
                SearchHQL.searchHQL();
                c1.setText("Categoria Dos");
                c1.setCellValueFactory(new PropertyValueFactory<>("categoryTwo"));
                ObservableList<CategorytwoClass> datesTwo = FXCollections.observableArrayList(ConstantsWare.categoryTwoList);
                tableWare.getItems().clear();
                tableWare.setItems(datesTwo);
                Constant.entity = "CategoryoneClass";

                break;
            case "bTwo":
                Constant.entity = "CategorytwoClass";
                Constant.tfName = tfOneCategory.getText();

                if (FoundHQL.workerFound() || ConstantsWare.two != null) {

                    Constant.tfCode = String.valueOf(ConstantsWare.two.getIdTwo());

                    if (SaveHQL.saveUpdateCate("update")) {
                        System.out.println("update");
                        bOne.setDisable(true);
                        bTwo.setDisable(false);
                        bThree.setDisable(false);

                        tfCode.setDisable(true);
                        tfOneCategory.setDisable(false);
                        tfThreeCategory1.setDisable(false);
                        bTwoRemove.setDisable(false);

                    }
                } else {
                    if (SaveHQL.saveUpdateCate("save")) {
                        System.out.println("save");
                        bOne.setDisable(true);
                        bTwo.setDisable(false);
                        bThree.setDisable(false);

                        bThree.setDisable(false);
                        tfCode.setDisable(true);
                        tfOneCategory.setDisable(false);
                        tfThreeCategory1.setDisable(false);
                        bTwoRemove.setVisible(true);
                        bTwoRemove.setDisable(false);
                    }
                }
                Constant.entity = "CategorythreeClass";
                SearchHQL.searchHQL();
                c1.setText("Categoria Tres");
                c1.setCellValueFactory(new PropertyValueFactory<>("categoryThree"));
                ObservableList<CategorythreeClass> datesThree = FXCollections.observableArrayList(ConstantsWare.categoryThreeList);
                tableWare.getItems().clear();
                tableWare.setItems(datesThree);
                Constant.entity = "CategorytwoClass";
                break;
            case "bThree":
                Constant.entity = "CategorythreeClass";
                Constant.tfName = tfThreeCategory1.getText();
                if (FoundHQL.workerFound() || ConstantsWare.three != null) {
                    Constant.tfCode = String.valueOf(ConstantsWare.three.getIdThree());
                    if (SaveHQL.saveUpdateCate("update")) {
                        bThreeRemove.setVisible(true);
                        bThreeRemove.setDisable(false);
                    }
                } else {
                    if (SaveHQL.saveUpdateCate("save")) {

                        bThreeRemove.setDisable(false);
                    }
                }
                break;
            case "buttonSP":
                if (Objects.equals(Constant.entity, "ServiceClass")) {
                    TableShow tableShow = new TableShow();
                    //producto
                    tableShow.setC1(comboBoxOne.getValue());
                    //precio
                    tableShow.setC2(comboBoxTwo.getValue());
                    //contenido
                    tableShow.setC3(tfSale.getText());
                    //consumo
                    tableShow.setC4(tfThreeCategory1.getText());
                    //costo
                    int pC = Integer.parseInt(tableShow.getC2());
                    int cM = Integer.parseInt(tableShow.getC3());
                    int cT = Integer.parseInt(tableShow.getC4());
                    int cost = ((pC * cM) / cT);
                    tableShow.setC5(String.valueOf(cost));

                    System.out.println("Cargando");
                    Constant.listTableShow.add(tableShow);
                    System.out.println(tableShow);
                    System.out.println(Constant.listTableShow);
                    Constant.entity = "ServiceTableShow";
                    tableLoad();
                    Constant.entity = "ServiceClass";
                } else if (Objects.equals(Constant.entity, "Transmute")) {
                    try {
                        int amount = Integer.parseInt(tfWare.getText());
                        int amountI = ConstantsWare.productPrice.getAmount();
                        int amountF = amountI - amount;

                        if (amountI > amount) {
                            TableShow tableT = new TableShow();
                            //Id de productPrice
                            tableT.setC1(String.valueOf(ConstantsWare.productPrice.getIdPrice()));
                            //Name Product
                            tableT.setC2(ConstantsWare.product.getName());
                            //ware
                            tableT.setC3(comboBoxTwo.getValue());
                            //ID WareProduct
                            tableT.setC4(String.valueOf(ConstantsWare.wareProduct.getIdWareProduct()));
                            //Price
                            tableT.setC5(String.valueOf(ConstantsWare.productPrice.getPrice()));
                            //Amount (total)
                            tableT.setC6(String.valueOf(ConstantsWare.productPrice.getAmount()));
                            //Amount que se va a usar
                            tableT.setC7(tfWare.getText());
                            //Amount que se Final
                            tableT.setC8(String.valueOf(amountF));
                            //Cost
                            int price1 = ConstantsWare.productPrice.getPrice();
                            int cost = price1 * amount;
                            tableT.setC9(String.valueOf(cost));

                            Constant.listTableShow.add(tableT);
                            System.out.println(tableT);
                            System.out.println(Constant.listTableShow);
                            Constant.entity = "TransmuteTableShow";
                            tableLoad();
                            Constant.entity = "Transmute";
                        } else {
                            alertSend("La cantidad supera el stock");
                        }
                    }catch (Exception i){
                        WareController.alertSend("POR FAVOR PRIMERO LLENE LISTA Y ESCOGER PRODUCTO");
                    }

                    }
                break;
            default:
                break;
        }
        }catch (Exception i){
            i.printStackTrace();
        }
    }
    @FXML //boton remover para las categorias
    public void buttonRemoveCate(ActionEvent event) {
        try{
        Button buttonRemove = (Button) event.getSource();
        switch (buttonRemove.getId()) {
            case "bTwoRemove":
                Constant.entity = "CategorytwoClass";
                DeleteHQL.deleteForean();
                tfOneCategory.setText("");
                tfThreeCategory1.setText("");
                break;
            case "bThreeRemove":
                Constant.entity = "CategorythreeClass";
                tfThreeCategory1.setText("");
                DeleteHQL.deleteForean();
                break;
            default:
                break;
        }
        }catch (Exception i){
            i.printStackTrace();
        }
    }
    @FXML //botones del lado derecho
    private void buttonSlide(ActionEvent event) throws IOException {
        slide(event);
    }
    @FXML//botones derechos
    static void slide(ActionEvent event) throws IOException {
        try{
        Button buttonSlide = (Button) event.getSource();
        if (Constant.greyToBlueSlide == null) {
            Constant.greyToBlueSlide = buttonSlide;
            buttonSlide.setStyle("-fx-background-color: #F5F5F5; ");
        } else if (Constant.greyToBlueSlide == buttonSlide) {
            buttonSlide.setStyle("-fx-background-color: #F5F5F5; ");
        } else {
            Constant.greyToBlueSlide.setStyle("-fx-background-color: #C2C2C2; ");
            buttonSlide.setStyle("-fx-background-color: #F5F5F5; ");
            Constant.greyToBlueSlide = buttonSlide;
        }
        slideChange(buttonSlide.getId(), event);
        }catch (Exception i){
            i.printStackTrace();
        }
    }
    @FXML //botones de lado derecho
    static void slideChange(String blue, ActionEvent event) throws IOException {
        try {
        switch (blue) {
            case "bWare":
                FXMLLoader fxmlLoaderWare = new FXMLLoader(HelloApplication.class.getResource("ware-view.fxml"));
                Scene sceneWare = new Scene(fxmlLoaderWare.load());
                Stage stageWare = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stageWare.setTitle(Constant.title());
                stageWare.setScene(sceneWare);
                stageWare.show();
                break;
            case "bDash":
                FXMLLoader fxmlLoaderDash = new FXMLLoader(HelloApplication.class.getResource("finance-view.fxml"));
                Scene sceneDash = new Scene(fxmlLoaderDash.load());
                Stage stageDash = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stageDash.setTitle(Constant.title());
                stageDash.setScene(sceneDash);
                stageDash.show();
                break;
            case "bAccounting":
                Constant.entity = "TaxesClass";
                FXMLLoader fxmlLoaderAccounting = new FXMLLoader(HelloApplication.class.getResource("accounting-view.fxml"));
                Scene sceneAccounting = new Scene(fxmlLoaderAccounting.load());
                Stage stageAccounting = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stageAccounting.setTitle(Constant.title());
                stageAccounting.setScene(sceneAccounting);
                stageAccounting.show();
                break;
            case "bBuy":
                FXMLLoader fxmlLoaderBuy = new FXMLLoader(HelloApplication.class.getResource("purchases-view.fxml"));
                Scene sceneBuy = new Scene(fxmlLoaderBuy.load());
                Stage stageBuy = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stageBuy.setTitle(Constant.title());
                stageBuy.setScene(sceneBuy);
                stageBuy.show();
                break;
            case "bSale":
                FXMLLoader fxmlLoaderSales = new FXMLLoader(HelloApplication.class.getResource("sales-view.fxml"));
                Scene sceneSales = new Scene(fxmlLoaderSales.load());
                Stage stageSales = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stageSales.setTitle(Constant.title());
                stageSales.setScene(sceneSales);
                stageSales.show();
                break;
            case "bSettings":
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("settings-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle(Constant.title());
                stage.setScene(scene);
                stage.show();
                break;
            case "buttonSingOut":
                FXMLLoader fxmlLoaderSing = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
                Scene sceneSing = new Scene(fxmlLoaderSing.load());
                Stage stageSing = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stageSing.setTitle("Forpus Inventory");
                stageSing.setScene(sceneSing);
                stageSing.show();
                break;
            default:
                break;
        }
        }catch (Exception i){
            i.printStackTrace();
        }
    }
    @FXML //botones de arriba
    public void buttonsOptions(ActionEvent event) {
        try{
        Button buttonOption = (Button) event.getSource();
        if (ConstantsWare.blueToWhite == null) {
            ConstantsWare.blueToWhite = buttonOption;
            buttonOption.setStyle("-fx-background-color: #F5F5F5; ");
        } else if (ConstantsWare.blueToWhite == buttonOption) {
            buttonOption.setStyle("-fx-background-color: #F5F5F5; ");
        } else {
            ConstantsWare.blueToWhite.setStyle("-fx-background-color: #1BA1E2; ");
            buttonOption.setStyle("-fx-background-color: #F5F5F5; ");
            ConstantsWare.blueToWhite = buttonOption;
        }
        ConstantsWare.idOption = buttonOption.getId();
        options(ConstantsWare.idOption);
        }catch (Exception i){
            i.printStackTrace();
        }
    }
    //botones de arriba, para dar formato al view

    /**
     * Define que muestra en la view de Warehouse
     * @param option
     *          define el boton del menu que se cliquea en la view
     */
    protected void options(String option) {
        try{
        clean();
        switch (option) {
            case "buttonCategory":
                buttonCategory.setStyle("-fx-background-color: #F5F5F5;");

                Constant.entity = "CategoryoneClass";

                labelCode.setText("Categoria 1");
                labelOneCategory.setText("Categoria 2");
                labelThreeCategory.setText("Categoria 3");

                labelThreeCategory.setVisible(true);
                tfOneCategory.setVisible(true);
                tfThreeCategory1.setVisible(true);

                tfOneCategory.setDisable(true);
                tfThreeCategory1.setDisable(true);

                bOne.setVisible(true);
                bTwo.setVisible(true);
                bThree.setVisible(true);
                bTwoRemove.setVisible(true);
                bThreeRemove.setVisible(true);

                break;
            case "buttonWare":
                Constant.entity = "WarehouseClass";
                buttonWare.setStyle("-fx-background-color: #F5F5F5;");
                labelCode.setText(Constant.lblCode);
                labelOneCategory.setText("Bodega");
                tfOneCategory.setVisible(true);

                break;
            case "buttonService":
                buttonService.setStyle("-fx-background-color: #F5F5F5; ");
                Constant.entity = "ServiceClass";

                labelCode.setText(Constant.lblCode);
                labelOneCategory.setText("Producto");
                labelTwoCategory.setText("Precio");
                labelThreeCategory.setText("Contenido");
                labelBuy.setText("Salario");
                labelProfit.setText("Horas");
                labelProduct.setText("Servicio");
                labelSale.setText("Consumido");
                labelWage.setText("Bodega");
                labelConsumed.setText("P. Venta");


                comboBoxOne.setVisible(true);
                comboBoxWage.setVisible(true);
                comboBoxTwo.setVisible(true);

                comboBoxOne.setPromptText("Producto");
                comboBoxTwo.setPromptText("Precio");
                comboBoxWage.setPromptText("Bodega");

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
                buttonSP.setVisible(true);
                buttonRTable.setVisible(true);

                break;
            case "buttonProducts":
                buttonProducts.setStyle("-fx-background-color: #F5F5F5;");
                Constant.entity = "ProductClass";

                labelCode.setText(Constant.lblCode);
                labelOneCategory.setText("Categoria 1");
                labelThreeCategory.setText("Categoria 3");

                labelTwoCategory.setText("Categoria 2");
                labelBuy.setText("Precio Compra");
                labelProfit.setText("Profit");
                labelProduct.setText("Producto");
                labelSale.setText("Precio Venta");
                labelWage.setText("Bodega");
                labelConsumed.setText("Cantidad");
                labelProfitSale.setText("");

                labelBuy.setVisible(true);
                labelSale.setVisible(true);
                labelProfit.setVisible(true);
                labelProduct.setVisible(true);
                labelTwoCategory.setVisible(true);
                labelWage.setVisible(true);
                labelThreeCategory.setVisible(true);
                labelConsumed.setVisible(true);
                labelProfitSale.setVisible(true);

                comboBoxOne.setVisible(true);
                comboBoxTwo.setVisible(true);
                comboBoxThree.setVisible(true);
                comboBoxWage.setVisible(true);

                comboBoxOne.setPromptText("Categoria 1");
                comboBoxTwo.setPromptText("Categoria 2");
                comboBoxThree.setPromptText("Categoria 3");
                comboBoxWage.setPromptText("Bodega");

                tfBuy.setVisible(true);
                tfProduct.setVisible(true);
                tfConsumed.setVisible(true);
                tfSale.setVisible(true);
                tfProfit.setVisible(true);

                break;
            case "buttonTransmute":
                buttonTransmute.setStyle("-fx-background-color: #F5F5F5;");
                Constant.entity = "Transmute";

                labelOneCategory.setVisible(true);
                labelOneCategory.setText("Producto");
                comboBoxOne.setVisible(true);

                labelTwoCategory.setVisible(true);
                labelTwoCategory.setText("Bodega");
                comboBoxTwo.setVisible(true);

                labelThreeCategory.setVisible(true);
                labelThreeCategory.setText("Precio");
                comboBoxThree.setVisible(true);

                labelWage.setVisible(true);
                labelWage.setText("Cantidad");
                tfWare.setVisible(true);

                labelBuy.setVisible(true);
                labelBuy.setText("Producto");
                comboBoxProductTT.setVisible(true);

                labelSale.setVisible(true);
                labelSale.setText("Bodega");
                comboBoxWare.setVisible(true);

                labelProfit.setVisible(true);
                labelProfit.setText("Precio");
                comboBoxPrice.setVisible(true);

                labelConsumed.setVisible(true);
                labelConsumed.setText("Cantidad");
                tfConsumed.setVisible(true);

                labelCost.setVisible(true);
                labelCost.setText("");

                labelProfitSale.setVisible(true);
                labelProfitSale.setText("");

                labelCostTrans.setVisible(true);
                labelCostTrans.setText("");

                labelCode.setVisible(false);
                tfCode.setVisible(false);
                buttonMAU.setVisible(true);
                buttonUAM.setVisible(true);
                buttonSP.setVisible(true);
                buttonRTable.setVisible(true);
                labelTransmute.setVisible(true);
                labelTransmute.setText("");

                comboBoxLoad();
                break;
            default:
                break;
        }
        tableLoad();
        }catch (Exception i){
            i.printStackTrace();
        }
    }
    //botones de arriba para dar formato al view
    protected void clean() {
        try{
        if (!Constant.listTableShow.isEmpty()) {
            Constant.listTableShow.clear();
        }
        labelBuy.setVisible(false);
        labelSale.setVisible(false);
        labelProfit.setVisible(false);
        labelProduct.setVisible(false);
        labelTwoCategory.setVisible(false);
        labelWage.setVisible(false);
        labelThreeCategory.setVisible(false);
        labelCost.setVisible(false);
        labelConsumed.setVisible(false);
        labelProfitSale.setVisible(false);
        labelCode.setVisible(true);
        labelTransmute.setVisible(false);
        labelCostTrans.setVisible(false);

        comboBoxOne.setVisible(false);
        comboBoxTwo.setVisible(false);
        comboBoxThree.setVisible(false);
        comboBoxWage.setVisible(false);
        comboBoxPrice.setVisible(false);
        comboBoxProductTT.setVisible(false);
        comboBoxWare.setVisible(false);

        tfBuy.setVisible(false);
        tfProduct.setVisible(false);
        tfSale.setVisible(false);
        tfProfit.setVisible(false);
        tfOneCategory.setVisible(false);
        tfThreeCategory1.setVisible(false);
        tfCost.setVisible(false);
        tfConsumed.setVisible(false);
        tfWare.setVisible(false);
        tfCode.setVisible(true);

        tfCode.setDisable(false);
        tfOneCategory.setDisable(false);
        tfThreeCategory1.setDisable(false);

        tfCode.setText("");
        tfBuy.setText("");
        tfProduct.setText("");
        tfSale.setText("");
        tfProfit.setText("");
        tfWare.setText("");
        tfCost.setText("");
        tfConsumed.setText("");
        tfThreeCategory1.setText("");
        tfOneCategory.setText("");

        bOne.setVisible(false);
        bTwo.setVisible(false);
        bThree.setVisible(false);
        buttonUAM.setVisible(false);
        buttonMAU.setVisible(false);
        bTwoRemove.setVisible(false);
        buttonSP.setVisible(false);
        buttonRTable.setVisible(false);

        bTwo.setDisable(true);
        bThree.setDisable(true);
        bTwoRemove.setDisable(true);
        bThreeRemove.setVisible(false);
        bThreeRemove.setDisable(true);
        remove.setDisable(true);

        //botones del crud
        search.setStyle("-fx-background-color: #1BA1E2; ");
        save.setStyle("-fx-background-color: #1BA1E2; ");
        found.setStyle("-fx-background-color: #1BA1E2; ");
        cancel.setStyle("-fx-background-color: #1BA1E2; ");
        remove.setStyle("-fx-background-color: #1BA1E2; ");
        save.setDisable(true);
        remove.setDisable(true);
        //tabla
        tableWare.getItems().clear();
        c1.setText("C1");
        c2.setText("C2");
        c3.setText("C3");
        c4.setText("C4");
        c5.setText("C5");
        c6.setText("C6");
        c7.setText("C7");
        c8.setText("C8");
        c9.setText("C9");
        //combobox
        comboBoxOne.getItems().clear();
        comboBoxTwo.getItems().clear();
        comboBoxThree.getItems().clear();
        comboBoxWage.getItems().clear();

        buttonCategory.setStyle("-fx-background-color: #1BA1E2; ");
        buttonWare.setStyle("-fx-background-color: #1BA1E2;");
        buttonService.setStyle("-fx-background-color: #1BA1E2; ");
        buttonProducts.setStyle("-fx-background-color: #1BA1E2;");
        buttonTransmute.setStyle("-fx-background-color: #1BA1E2;");
        }catch (Exception i){
            i.printStackTrace();
        }
    }
    //carga datos a la tabla
    public void tableLoad() {
        tableWare.getItems().clear();
        c1.setText("C1");
        c2.setText("C2");
        c3.setText("C3");
        c4.setText("C4");
        c5.setText("C5");
        c6.setText("C6");
        c7.setText("C7");
        c8.setText("C8");
        c9.setText("C9");

        try {
            switch (Constant.entity) {
                case "CategoryoneClass":
                    SearchHQL.searchHQL();

                    c1.setText("Categoria Uno");
                    c1.setCellValueFactory(new PropertyValueFactory<>("categoryOne"));
                    ObservableList<CategoryoneClass> dates = FXCollections.observableArrayList(ConstantsWare.categoryOneList);
                    tableWare.setItems(dates);

                    c2.setCellValueFactory(new PropertyValueFactory<>(null));
                    c3.setCellValueFactory(new PropertyValueFactory<>(null));
                    c4.setCellValueFactory(new PropertyValueFactory<>(null));
                    c5.setCellValueFactory(new PropertyValueFactory<>(null));
                    c6.setCellValueFactory(new PropertyValueFactory<>(null));
                    c7.setCellValueFactory(new PropertyValueFactory<>(null));
                    c8.setCellValueFactory(new PropertyValueFactory<>(null));
                    c9.setCellValueFactory(new PropertyValueFactory<>(null));
                    break;

                case "WarehouseClass":
                    //busca y pone datos en la tabla
                    SearchHQL.searchHQL();
                    tableWare.getItems().clear();
                    c1.setText("Codigo");
                    c1.setCellValueFactory(new PropertyValueFactory<>("idWarehouse"));
                    c2.setText("Bodega");
                    c2.setCellValueFactory(new PropertyValueFactory<>("name"));

                    c3.setCellValueFactory(new PropertyValueFactory<>(null));
                    c4.setCellValueFactory(new PropertyValueFactory<>(null));
                    c5.setCellValueFactory(new PropertyValueFactory<>(null));
                    c6.setCellValueFactory(new PropertyValueFactory<>(null));
                    c7.setCellValueFactory(new PropertyValueFactory<>(null));
                    c8.setCellValueFactory(new PropertyValueFactory<>(null));
                    c9.setCellValueFactory(new PropertyValueFactory<>(null));
                    ObservableList<WarehouseClass> datesWare = FXCollections.observableArrayList(ConstantsWare.wareList);
                    tableWare.setItems(datesWare);
                    break;
                case "ServiceClass":
                    SearchHQL.searchHQL();
                    tableWare.getItems().clear();
                    c1.setText("Codigo");
                    c1.setCellValueFactory(new PropertyValueFactory<>("idService"));
                    c2.setText("Servicio");
                    c2.setCellValueFactory(new PropertyValueFactory<>("name"));
                    c3.setText("Pago por Hora");
                    c3.setCellValueFactory(new PropertyValueFactory<>("payForHour"));
                    c4.setText("Horas");
                    c4.setCellValueFactory(new PropertyValueFactory<>("hour"));
                    c5.setText("Costo");
                    c5.setCellValueFactory(new PropertyValueFactory<>("cost"));

                    c6.setCellValueFactory(new PropertyValueFactory<>(null));
                    c7.setCellValueFactory(new PropertyValueFactory<>(null));
                    c8.setCellValueFactory(new PropertyValueFactory<>(null));
                    c9.setCellValueFactory(new PropertyValueFactory<>(null));
                    ObservableList<ServiceClass> datesService = FXCollections.observableArrayList(ConstantsWare.serviceList);
                    tableWare.setItems(datesService);
                    break;
                case "ProductClass":
                    if (!FoundHQL.workerFound()) {

                        SearchHQL.searchHQL();
                        tableWare.getItems().clear();
                        c1.setText("Codigo");
                        c1.setCellValueFactory(new PropertyValueFactory<>("idProduct"));
                        c2.setText("Producto");
                        c2.setCellValueFactory(new PropertyValueFactory<>("name"));
                        c3.setText("Categoria 1");
                        c3.setCellValueFactory(new PropertyValueFactory<>("idOne"));
                        c4.setText("Categoria 2");
                        c4.setCellValueFactory(new PropertyValueFactory<>("idTwo"));
                        c5.setText("Categoria 3");
                        c5.setCellValueFactory(new PropertyValueFactory<>("idThree"));
                        c6.setText("Precio de compra");
                        c6.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));
                        c7.setText("Precio de venta");
                        c7.setCellValueFactory(new PropertyValueFactory<>("salePrice"));
                        c8.setText("Profit");
                        c8.setCellValueFactory(new PropertyValueFactory<>("profit"));
                        c9.setCellValueFactory(new PropertyValueFactory<>(null));
                        ObservableList<ProductClass> datesProduct = FXCollections.observableArrayList(ConstantsWare.productList);
                        tableWare.setItems(datesProduct);
                    } else {
                        //da formato
                        labelConsumed.setVisible(false);
                        tfConsumed.setVisible(false);
                        labelBuy.setVisible(false);
                        tfBuy.setVisible(false);
                        labelProfit.setVisible(false);
                        tfProfit.setVisible(false);
                        labelProfitSale.setVisible(false);

                        //como el producto existe se debe de poner las bodegas, los precios y las cantidades
                        //los precios y las cantidades estan en la tabla productPrice
                        //la bodega esta en la tabla WareProduct

                        //obtener el codigo del producto
                        Constant.tfCode = tfCode.getText();
                        //array list
                        ArrayList<TableShow> listProducts = new ArrayList<>();
                        //obtener la lista de bodegas con el producto ConstantsWare.product.getWareProductsByIdProduct();
                        for (WareProductClass a : ConstantsWare.product.getWareProductsByIdProduct()) {
                            System.out.println(a);
                            //evalua si la bodega corresponde a la selecionada
                            //--if(a.getIdWare() == ConstantsWare.ware.getIdWarehouse()){
                            //Lista de ProductPrices que tiene el producto y la bodega
                            a.getProductpricesByIdWareProduct();
                            //ciclo para convertirlos en objetos de tipo table show
                            for (ProductpriceClass b : a.getProductpricesByIdWareProduct()) {
                                TableShow objeto = new TableShow();
                                //codigo del producto
                                objeto.setC1(ConstantsWare.product.getIdProduct());
                                //nombre del producto
                                objeto.setC2(ConstantsWare.product.getName());
                                //Bodega del producto
                                objeto.setC3(a.getWarehouseByIdWare().getName());
                                //Cantidad
                                objeto.setC4(String.valueOf(b.getAmount()));
                                //Precio de compra
                                objeto.setC5(String.valueOf(b.getPrice()));
                                listProducts.add(objeto);
                            }
                        }
                        //pone los datos en la tabla
                        tableWare.getItems().clear();
                        c1.setText("Codigo");
                        c1.setCellValueFactory(new PropertyValueFactory<>("c1"));
                        c2.setText("Producto");
                        c2.setCellValueFactory(new PropertyValueFactory<>("c2"));
                        c3.setText("Bodega");
                        c3.setCellValueFactory(new PropertyValueFactory<>("c3"));
                        c4.setText("Cantidad");
                        c4.setCellValueFactory(new PropertyValueFactory<>("c4"));
                        c5.setText("Precio de compra");
                        c5.setCellValueFactory(new PropertyValueFactory<>("c5"));

                        c6.setCellValueFactory(new PropertyValueFactory<>(null));
                        c7.setCellValueFactory(new PropertyValueFactory<>(null));
                        c8.setCellValueFactory(new PropertyValueFactory<>(null));
                        c9.setCellValueFactory(new PropertyValueFactory<>(null));

                        ObservableList<TableShow> datesTableShow = FXCollections.observableArrayList(listProducts);
                        tableWare.setItems(datesTableShow);
                    }
                    break;
                case "ServiceTableShow":
                    tableWare.getItems().clear();
                    c1.setText("Producto");
                    c1.setCellValueFactory(new PropertyValueFactory<>("c1"));
                    c2.setText("Precio");
                    c2.setCellValueFactory(new PropertyValueFactory<>("c2"));
                    c3.setText("Consumo");
                    c3.setCellValueFactory(new PropertyValueFactory<>("c3"));
                    c4.setText("Contenido");
                    c4.setCellValueFactory(new PropertyValueFactory<>("c4"));
                    c5.setText("Costo");
                    c5.setCellValueFactory(new PropertyValueFactory<>("c5"));

                    c6.setCellValueFactory(new PropertyValueFactory<>(null));
                    c7.setCellValueFactory(new PropertyValueFactory<>(null));
                    c8.setCellValueFactory(new PropertyValueFactory<>(null));
                    c9.setCellValueFactory(new PropertyValueFactory<>(null));

                    ObservableList<TableShow> datesSTS = FXCollections.observableArrayList(Constant.listTableShow);
                    tableWare.setItems(datesSTS);
                    break;
                case "TransmuteTableShow":
                    tableWare.getItems().clear();
                    c1.setText("ID PP");
                    c1.setCellValueFactory(new PropertyValueFactory<>("c1"));
                    c2.setText("Producto");
                    c2.setCellValueFactory(new PropertyValueFactory<>("c2"));
                    c3.setText("Bodega");
                    c3.setCellValueFactory(new PropertyValueFactory<>("c3"));
                    c4.setText("ID BP");
                    c4.setCellValueFactory(new PropertyValueFactory<>("c4"));
                    c5.setText("Precio");
                    c5.setCellValueFactory(new PropertyValueFactory<>("c5"));
                    c6.setText("Cantidad I");
                    c6.setCellValueFactory(new PropertyValueFactory<>("c6"));
                    c7.setText("Cantidad U");
                    c7.setCellValueFactory(new PropertyValueFactory<>("c7"));
                    c8.setText("Cantidad F");
                    c8.setCellValueFactory(new PropertyValueFactory<>("c8"));
                    c9.setText("Costo");
                    c9.setCellValueFactory(new PropertyValueFactory<>("c9"));
                    ObservableList<TableShow> datesTTT = FXCollections.observableArrayList(Constant.listTableShow);
                    tableWare.setItems(datesTTT);
                    break;
                default:
                    break;
            }
        } catch (Exception i) {
            i.printStackTrace();
            WareController.alertSend("ERROR AL CARGAR LOS DATOS");
        }

    }
    //carga datos a lso combobox
    public void comboBoxLoad() {
        try{
        ArrayList<String> listProduct = new ArrayList<>();
        comboBoxOne.getItems().clear();
        comboBoxTwo.getItems().clear();
        comboBoxThree.getItems().clear();
        comboBoxWage.getItems().clear();
        if (Objects.equals(Constant.entity, "ServiceClass")) {

            Constant.entity = "ProductClass";
            SearchHQL.searchHQL();
            if (ConstantsWare.productList != null) {
                for (ProductClass a : ConstantsWare.productList) {
                    String product = a.getName();
                    if (product != null) {
                        listProduct.add(product);
                    }
                }
            }

            ObservableList<String> comboBoxProduct = FXCollections.observableArrayList(listProduct);
            comboBoxOne.getItems().addAll(comboBoxProduct);

            Constant.entity = "WarehouseClass";
            SearchHQL.searchHQL();
            listProduct.clear();

            for (int i = 0; i < ConstantsWare.wareList.length; i = i + 1) {
                String product = ConstantsWare.wareList[i].getName();
                if (product != null) {
                    listProduct.add(product);
                }
            }
            comboBoxWage.getItems().addAll(listProduct);

            Constant.entity = "ServiceClass";

        }
        else if (Objects.equals(Constant.entity, "ProductClass")) {

            comboBoxOne.getItems().addAll(categoryOne(listProduct));
            Constant.entity = "WarehouseClass";
            SearchHQL.searchHQL();
            listProduct.clear();

            if (ConstantsWare.wareList != null) {
                for (WarehouseClass a : ConstantsWare.wareList) {
                    String product = a.getName();
                    if (product != null) {
                        listProduct.add(product);
                    }
                }
            }
            comboBoxWage.getItems().addAll(listProduct);

            Constant.entity = "ProductClass";
        }
        else {
            System.out.println("transmutar falta el combobox");
            Constant.entity = "ProductClass";
            SearchHQL.searchHQL();
            if (ConstantsWare.productList != null) {
                for (ProductClass a : ConstantsWare.productList) {
                    String product = a.getName();
                    if (product != null) {
                        listProduct.add(product);
                    }
                }
            }
            ObservableList<String> comboBoxProducts = FXCollections.observableArrayList(listProduct);
            comboBoxOne.getItems().addAll(comboBoxProducts);
            comboBoxProductTT.getItems().addAll(comboBoxProducts);
            Constant.entity = "Transmute";

        }
        }catch (Exception i){
            i.printStackTrace();
        }
    }
    public void comboBoxClick(ActionEvent event) {
        try{
        ComboBox cBoxChange = (ComboBox) event.getSource();
        ArrayList<String> listProduct = new ArrayList<>();

        if (Objects.equals(Constant.entity, "ProductClass")) {
            switch (cBoxChange.getId()) {
                case "comboBoxOne":
                    //limpia el comboBOx
                    comboBoxTwo.getItems().clear();
                    //carga el combobox 2
                    comboBoxTwo.getItems().addAll(categoryTwo(listProduct, (String) cBoxChange.getValue()));
                    //devuelve las constantes a los valores iniciales
                    Constant.entity = "ProductClass";
                    Constant.tfCode = tfCode.getText();

                    break;
                case "comboBoxTwo":

                    comboBoxThree.getItems().clear();
                    comboBoxThree.getItems().addAll(categoryThree(listProduct, (String) cBoxChange.getValue()));

                    Constant.entity = "ProductClass";
                    Constant.tfCode = tfCode.getText();
                    break;
                case "comboBoxThree":
                    Constant.entity = "CategorythreeClass";
                    Constant.tfName = (String) cBoxChange.getValue();
                    FoundHQL.workerFound();

                    Constant.entity = "ProductClass";
                    Constant.tfCode = tfCode.getText();
                    break;
                case "comboBoxWage":
                    Constant.entity = "WarehouseClass";
                    Constant.tfName = (String) cBoxChange.getValue();
                    FoundHQL.workerFound();

                    Constant.entity = "ProductClass";
                    Constant.tfCode = tfCode.getText();
                    break;
                default:
                    break;
            }
            System.out.println(cBoxChange.getValue());
        }
        else if (Objects.equals(Constant.entity, "Transmute")) {
            switch (cBoxChange.getId()) {
                case "comboBoxOne":
                case "comboBoxProductTT":
                    if(Objects.equals(cBoxChange.getId(), "comboBoxOne")){
                        //limpia el comboBOx
                        comboBoxTwo.getItems().clear();
                        labelTransmute.setText("");

                    }else{
                        comboBoxWare.getItems().clear();
                    }
                    //Crea la lista de productos
                    Constant.entity = "ProductClass";
                    SearchHQL.searchHQL();

                    //Obtiene el id del item seleccionado en el combobox 1
                    Constant.entity = "ProductClass";
                    Constant.tfCode = (String) cBoxChange.getValue();
                    FoundHQL.wareFound();

                    //realiza esto si la lista de precios no es nulo
                    if (ConstantsWare.product.getWareProductsByIdProduct() != null) {

                        //for each para iterar la lista de bodegas del producto
                        for (WareProductClass a : ConstantsWare.product.getWareProductsByIdProduct()) {
                            //obtiene la bodega
                            String ware = a.getIdWare();
                            listProduct.add(ware);
                        }
                    }

                    if(Objects.equals(cBoxChange.getId(), "comboBoxProductTT")){
                        comboBoxWare.getItems().addAll(listProduct);

                    }else{
                        //carga el combobox 2
                        comboBoxTwo.getItems().addAll(listProduct);
                    }
                    //devuelve las constantes a los valores iniciales
                    Constant.entity = "Transmute";

                    break;
                case "comboBoxTwo":
                case "comboBoxWare":

                    if(Objects.equals(cBoxChange.getId(), "comboBoxTwo")){
                    //limpia el comboBOx
                    comboBoxThree.getItems().clear();
                    }else{
                    comboBoxPrice.getItems().clear();
                    }

                    //Crea la lista de precios-producto
                    Constant.entity = "ProductpriceClass";
                    SearchHQL.searchHQL();
                    //get of id del item seleccionado en el combobox 2
                    Constant.tfAddress = (String) cBoxChange.getValue();
                    for (WareProductClass w : ConstantsWare.product.getWareProductsByIdProduct()) {
                        if (Objects.equals(w.getIdWare(), Constant.tfAddress)){
                            if (ConstantsWare.productPriceList != null) {
                                Constant.entity ="WareProductClass";
                                Constant.tfCode = ConstantsWare.product.getIdProduct();
                                Constant.tfName = Constant.tfAddress;
                                FoundHQL.wareFound();
                                for (ProductpriceClass pp : ConstantsWare.productPriceList) {
                                    if(ConstantsWare.wareProduct != null && pp.getIdProductWare() == ConstantsWare.wareProduct.getIdWareProduct()){
                                        String price = String.valueOf(pp.getPrice());
                                        listProduct.add(price);
                                    }
                                }
                            }
                        }
                    }
                    if(Objects.equals(cBoxChange.getId(), "comboBoxTwo")){
                        //carga el combobox 2
                        comboBoxThree.getItems().addAll(listProduct);
                    }else{
                        comboBoxPrice.getItems().addAll(listProduct);
                    }
                    //devuelve las constantes a los valores iniciales
                    Constant.entity = "Transmute";
                    break;
                case "comboBoxThree":
                case "comboBoxPrice":
                    Constant.entity = "ProductpriceClass";
                    Constant.tfCode = String.valueOf(ConstantsWare.wareProduct.getIdWareProduct());
                    if(Objects.equals(cBoxChange.getId(), "comboBoxThree")){
                        Constant.tfName = comboBoxThree.getValue();
                    }else{
                        Constant.tfName = comboBoxPrice.getValue();
                        Constant.tfSalary = "transmute";
                    }
                    FoundHQL.wareFound();
                    Constant.tfSalary = "";

                    if(Objects.equals(cBoxChange.getId(), "comboBoxThree")){
                        labelTransmute.setText(String.valueOf(ConstantsWare.productPrice.getAmount()));
                    }else{
                        labelProfitSale.setText(String.valueOf(ConstantsWare.productPriceTransmute.getAmount()));
                    }
                    Constant.entity = "Transmute";
                    break;
                default:
                    break;
            }
        }else{
                switch (cBoxChange.getId()) {
                    case "comboBoxOne":
                        //limpia el comboBOx
                        comboBoxTwo.getItems().clear();
                        //Crea la lista de productos
                        Constant.entity = "ProductClass";
                        SearchHQL.searchHQL();

                        //Obtiene el id del item seleccionado en el combobox 1
                        Constant.entity = "ProductClass";
                        Constant.tfCode = (String) cBoxChange.getValue();
                        FoundHQL.wareFound();

                        System.out.println(ConstantsWare.product);
                        //realiza esto si la lista de precios no es nulo
                        if (ConstantsWare.product.getWareProductsByIdProduct() != null) {

                            //for each para iterar la lista de productos
                            for (WareProductClass a : ConstantsWare.product.getWareProductsByIdProduct()) {
                                //obtiene los nombres de la categoria dos

                                for (ProductpriceClass b : a.getProductpricesByIdWareProduct()) {
                                    Integer price = b.getPrice();
                                    listProduct.add(price.toString());
                                }
                            }
                        }
                        //carga el combobox 2
                        comboBoxTwo.getItems().addAll(listProduct);
                        //devuelve las constantes a los valores iniciales
                        Constant.entity = "ServiceClass";
                        Constant.tfCode = tfCode.getText();

                        break;
                    case "comboBoxTwo":
                        Constant.entity = "ServiceClass";
                        Constant.tfCode = tfCode.getText();
                        break;
                    case "comboBoxWage":
                        Constant.entity = "WarehouseClass";
                        Constant.tfName = (String) cBoxChange.getValue();
                        FoundHQL.workerFound();

                        Constant.entity = "ServiceClass";
                        Constant.tfCode = tfCode.getText();
                        break;
                    default:
                        break;
                }
            }
        }catch (Exception i){
            i.printStackTrace();
        }
    }
    //Obtiene id del valor los combobox
    public void idValorComboBox (String valueComboBox, String entity){
        Constant.entity = entity;
        Constant.tfCode = valueComboBox;
        if (Objects.equals(entity, "WarehouseClass")) {
            FoundHQL.wareFound();
        } else {
            FoundHQL.workerFound();
        }

    }
    public static void alertSend (String massage){
        Alert alertMassage = new Alert(Alert.AlertType.INFORMATION);
        alertMassage.setTitle("Freya Style--//--Forpus Company");
        alertMassage.setContentText(massage);
        alertMassage.show();
    }
    @FXML //solo numeros
    /**
     * Si Constant.entity no contiene "Categor", no permite escribir letras
     * Esto es para tfThreeCategory
     * **/
    private void isNumber (KeyEvent keyEvent){
        if(!Constant.entity.contains("Categor")){
            TextField tf = (TextField) keyEvent.getSource();
            int a = Character.getNumericValue(keyEvent.getCharacter().charAt(0));
            if (!Character.isDigit(keyEvent.getCharacter().charAt(0))) {
                keyEvent.consume();
                if (!tf.getText().isEmpty() && a > 9 || Character.isSpaceChar(keyEvent.getCharacter().charAt(0))) {
                    tf.deleteText(tf.getText().length() - 1, tf.getText().length());
                }
            }
            if (Objects.equals(Constant.entity, "ProductClass") && !tfProfit.getText().isEmpty() && tfBuy.getText().length() > 1) {
                Integer p = Integer.valueOf(tfProfit.getText());
                int b = Integer.parseInt(tfBuy.getText());
                int v = b + ((b * p) / 100);
                labelProfitSale.setText(String.valueOf(v));
            }
        }

    }
    public void removeTable (ActionEvent event){
        try{
            int a = tableWare.getSelectionModel().getSelectedIndex();
            String nameProduct = (String) c1.getCellData(a);

            if (nameProduct != null) {

                //elimina de la tabla
                tableWare.getItems().remove(a);

                //elimina del array list
                Constant.listTableShow.remove(a);

                //Busca y elimina de la tabla service product
                if (!ConstantsWare.service.getServiceProductsByIdService().isEmpty() && Objects.equals(Constant.entity, "ServiceClass")) {
                    Constant.entity = "ProductClass";
                    Constant.tfCode = nameProduct;
                    FoundHQL.wareFound();

                    Constant.entity = "ServiceProductClass";
                    Constant.tfCode = ConstantsWare.product.getIdProduct();
                    Constant.tfName = tfCode.getText();
                    if (FoundHQL.wareFound()) {
                        DeleteHQL.deleteForean();
                    }
                    Constant.entity = "ServiceClass";
                    Constant.tfCode = tfCode.getText();
                }
            }
        }catch (Exception i){
            i.printStackTrace();
        }
    }
    public void buttonMany(ActionEvent event) {
        Button button = (Button) event.getSource();
        try {
            //Cantidad inicial del producto
            final int inicial = Integer.parseInt(labelProfitSale.getText());

            final int cambio = Integer.parseInt(tfConsumed.getText());
            //cantidad final
            int f;
            int h;
            int g = 1;
            if (Objects.equals(buttonUAM.getId(), button.getId())) {
                f = inicial - cambio;
            } else {
                f = cambio + inicial;
                g = -1;
            }
            //for calculate the change of amount
            for (TableShow t : Constant.listTableShow) {
                //C6 amount start
                int init = Integer.parseInt(t.getC6());
                //C7 change
                int chang = Integer.parseInt(t.getC7());
                //C8 final
                int fi = init + (g * chang);
                t.setC8(String.valueOf(fi));
            }
            //for calculate the cost total of table
            h = 0;
            for (TableShow t : Constant.listTableShow) {
                int cost = Integer.parseInt(t.getC9());
                h = h + cost;
            }
            labelCostTrans.setText(String.valueOf(h));
            Constant.entity = "TransmuteTableShow";
            tableLoad();
            labelCost.setText(String.valueOf(f));
            ConstantsWare.productPriceTransmute.setAmount(f);
            Constant.entity = "Transmute";
            tfCode.setText("Transmute");
            int profit = ConstantsWare.productPriceTransmute.getPrice();
            save.setDisable(h != profit);
            if(h != profit){
                alertSend("Los costos deben de ser iguales");
            }
        }catch (Exception i){
            i.printStackTrace();
            WareController.alertSend("POR FAVOR SELECCIONAR PRODUCTOS");
        }
    }
    public static ArrayList<String> categoryOne(ArrayList<String> listProduct){
        Constant.entity = "CategoryoneClass";
        SearchHQL.searchHQL();
        if (ConstantsWare.categoryOneList != null) {
            for (CategoryoneClass a : ConstantsWare.categoryOneList) {
                String product = a.getCategoryOne();
                if (product != null) {
                    listProduct.add(product);
                }
            }
        }
        return listProduct;
    }
    public static ArrayList<String> categoryTwo(ArrayList<String> listProduct, String valueOne){
        //Crea la lista de categorias 2
        Constant.entity = "CategorytwoClass";
        SearchHQL.searchHQL();
        //limpia la lista de entrada
        listProduct.clear();
        //Obtiene el ID del item seleccionado en el combobox 1
        Constant.entity = "CategoryoneClass";
        Constant.tfCode = valueOne;
        FoundHQL.workerFound();
        //realiza esto si la lista de categoria 2 no es nulo
        if (ConstantsWare.categoryTwoList != null) {
            //for each para iterar la lista de categoria 2
            for (CategorytwoClass a : ConstantsWare.categoryTwoList) {
                //obtiene los nombres de la categoria dos
                String product = a.getCategoryTwo();
                //si el nombre no es nulo y el id corresponde al id de la categoria 1 lo agrega a la lista
                if (product != null && a.getIdCategoryOne() == ConstantsWare.one.getIdOne()) {
                    listProduct.add(product);
                }

            }
        }
        return listProduct;
    }
    public static ArrayList<String> categoryThree(ArrayList<String> listProduct, String valueComboBox){
        Constant.entity = "CategorythreeClass";
        SearchHQL.searchHQL();

        Constant.entity = "CategorytwoClass";
        Constant.tfName = valueComboBox;
        FoundHQL.workerFound();

        if (ConstantsWare.categoryThreeList != null) {
            for (CategorythreeClass a : ConstantsWare.categoryThreeList) {
                String product = a.getCategoryThree();
                if (product != null && a.getIdTwoThree() == ConstantsWare.two.getIdTwo()) {
                    listProduct.add(product);
                }
            }
        }
        return listProduct;
    }
}