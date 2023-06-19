package com.forpus.forpusinventory.controller;

import com.forpus.forpusinventory.domain.services.*;
import com.forpus.forpusinventory.persistence.crud.FoundHQL;
import com.forpus.forpusinventory.persistence.crud.SaveHQL;
import com.forpus.forpusinventory.persistence.crud.SearchHQL;
import com.forpus.forpusinventory.persistence.entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static com.forpus.forpusinventory.domain.services.ConstantsPurchases.createNumericTextFormatter;

public class PurchasesController {
    public Button found;
    public Button cancel;
    public Button save;
    public Button search;
    public Button remove;
    public Button buttonProduct0;
    public Button buttonService;
    public Button buttonCredit;
    public Label labelProvider;
    public TableView<ProductClass> tableMain;
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
    public ComboBox<String> comboBoxOne;
    public ComboBox<String> comboBoxTwo;
    public ComboBox<String> comboBoxThree;
    public ComboBox<String> comboBoxWare;
    public CheckBox checkProduct;
    public CheckBox checkSale;
    public TextField tfProductName;
    public TableColumn<Object, Object> c1;
    public TableColumn<Object, Object> c2;
    public TableColumn<Object, Object> c3;
    public TableColumn<Object, Object> c4;
    public TableColumn<Object, Object> c5;
    public TableColumn<Object, Object> c6;
    public TableColumn<Object, Object> c7;
    public TableColumn<Object, Object> c8;
    public TableColumn<Object, Object> c9;
    public TableColumn<Object, Object> c10;
    public Button buttonSuppress;
    public Label labelTotal3;
    public Label labelIVA2;
    public Label labelBold2;
    public Label labelTotal1;
    public Label labelDebt;
    public Label labelSubTotal1;
    public TableView<ServiceClass> tableService;
    public TableColumn<Object, Object> c21;
    public TableColumn<Object, Object> c22;
    public TableColumn<Object, Object> c23;
    public TableColumn<Object, Object> c24;
    public TableColumn<Object, Object> c25;
    public TableView<InvoiceClass> tableInvoice;
    public TableView<WareinvoiceClass> tableWareInv;
    public TableColumn<Object, Object> i1;
    public TableColumn<Object, Object> i2;
    public TableColumn<Object, Object> i3;
    public TableColumn<Object, Object> i4;
    public TableColumn<Object, Object> i5;
    public TableColumn<Object, Object> wi1;
    public TableColumn<Object, Object> wi2;
    public TableColumn<Object, Object> wi3;
    public TableColumn<Object, Object> wi4;
    public TableView<MoveinvoiceClass> tableMoveInv;
    public TableColumn<Object, Object> m1;
    public TableColumn<Object, Object> m2;
    public TableColumn<Object, Object> m3;
    public TableColumn<Object, Object> m4;
    public TableColumn<Object, Object> m5;
    public TableColumn<Object, Object> m6;
    public TableColumn<Object, Object> m7;
    public TextField tfTaxes;

    public Button bDash;
    public Button bAccounting;
    public Button bBuy;
    public Button bSale;
    public Button bSettings;
    public Button bWare;

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
        option(button.getId());
    }

    public void option(String idButton){
        clear();
        final boolean a = true;
        switch (idButton){
            case "buttonProduct0":
                buttonProduct0.setStyle("-fx-background-color: #F5F5F5; ");
                ConstantsPurchases.entity = "Purchases";
                ConstantsPurchases.entityForInvoice = "ProvidersClass";
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
                buttonSuppress.setVisible(a);
                buttonCheckIn.setVisible(a);

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
                buttonIVA.setVisible(a);
                buttonBold.setVisible(a);
                labelIVA.setText("IVA");
                labelIVA2.setVisible(a);
                labelBold.setVisible(a);
                labelBold2.setVisible(a);

                panelTotal.setVisible(a);

                checkProduct.setVisible(a);
                checkSale.setVisible(a);

                labelProduct.setText("Producto");
                labelNameProduct.setText("Nombre Producto");
                taxesIVABOLD();

                break;
            case "buttonService":
                buttonService.setStyle("-fx-background-color: #F5F5F5; ");
                ConstantsPurchases.entity = "Service";
                ConstantsPurchases.entityForInvoice = "ProvidersClass";
                labelProvider.setVisible(a);
                labelNameProvider.setVisible(a);
                labelProduct.setVisible(a);
                labelNameProduct.setVisible(a);
                labelPrice.setVisible(a);
                labelOff.setVisible(a);
                labelAmount.setVisible(a);

                tfProvider.setVisible(a);
                tfProduct.setVisible(a);
                tfPrice.setVisible(a);
                tfOff.setVisible(a);
                tfAmount.setVisible(a);

                buttonProvider.setVisible(a);
                buttonProduct.setVisible(a);
                buttonRegister.setVisible(a);
                buttonSuppress.setVisible(a);

                search.setVisible(a);
                save.setVisible(a);
                found.setVisible(a);
                cancel.setVisible(a);
                remove.setVisible(a);

                comboBoxWare.setVisible(a);

                tableService.setVisible(a);

                panelCheckIn.setVisible(a);
                buttonCheckIn.setVisible(a);
                panelPayment.setVisible(a);
                buttonIVA.setVisible(a);
                buttonBold.setVisible(a);
                labelIVA.setText("IVA");
                labelIVA2.setVisible(a);
                labelBold.setVisible(a);
                labelBold2.setVisible(a);

                panelTotal.setVisible(a);

                checkProduct.setVisible(a);

                labelProduct.setText("Servicio");
                labelNameProduct.setText("Nombre Servicio");
                taxesIVABOLD();
                break;
            case "buttonCredit":
                buttonCredit.setStyle("-fx-background-color: #F5F5F5; ");
                ConstantsPurchases.entity = "Credit";
                ConstantsPurchases.entityForInvoice = "ProvidersClass";
                labelProvider.setVisible(a);
                tfProvider.setVisible(a);

                tableInvoice.setVisible(a);
                tableMoveInv.setVisible(a);
                tableWareInv.setVisible(a);

                tfTaxes.setVisible(a);

                buttonProvider.setVisible(a);
                search.setVisible(a);

                panelCheckIn.setVisible(a);
                panelPayment.setVisible(a);
                buttonIVA.setVisible(!a);
                buttonBold.setVisible(!a);
                labelIVA.setText("Mora");
                labelIVA2.setVisible(!a);
                labelBold.setVisible(!a);
                labelBold2.setVisible(!a);
                buttonQuote.setVisible(a);
                buttonCheckIn.setVisible(!a);

                panelTotal.setVisible(a);
                labelNameProvider.setVisible(a);
                tableLoad();
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
        tfProductName.setVisible(a);
        tfTaxes.setVisible(a);

        buttonProvider.setVisible(a);
        buttonProduct.setVisible(a);
        buttonRegister.setVisible(a);
        buttonSuppress.setVisible(a);

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
        tableService.setVisible(a);
        tableInvoice.setVisible(a);
        tableMoveInv.setVisible(a);
        tableWareInv.setVisible(a);

        panelCheckIn.setVisible(a);
        panelPayment.setVisible(a);
        panelTotal.setVisible(a);
        checkSale.setVisible(a);
        checkProduct.setVisible(a);

        buttonQuote.setVisible(a);

        labelTotal2.setText("0");
        labelTotal3.setText("0");
        labelIVA2.setText("0");
        labelBold2.setText("0");
        tfCash.setText("0");
        tfBank.setText("0");
        labelPay.setText("0");
        labelDebt.setText("0");
        tfProvider.setText("");
        labelProvider.setText("Proveedor");
        try {
            if(!ConstantsPurchases.productTableList.isEmpty()) {
                ConstantsPurchases.productTableList.clear();
            }
            if(!ConstantsPurchases.serviceTableList.isEmpty()) {
                ConstantsPurchases.serviceTableList.clear();
            }
            if(!ConstantsPurchases.wareInvoiceList.isEmpty()) {
                ConstantsPurchases.wareInvoiceList.clear();
            }
            if(ConstantsPurchases.invoiceList != null) {
                ConstantsPurchases.invoiceList.clear();
            }
        }catch (Exception i){
            i.printStackTrace();
        }

        tableService.getItems().clear();
        tableMain.getItems().clear();

        buttonProduct0.setStyle("-fx-background-color: #1BA1E2; ");
        buttonService.setStyle("-fx-background-color: #1BA1E2; ");
        buttonCredit.setStyle("-fx-background-color: #1BA1E2; ");
    }
    public void providerFound(ActionEvent event) {
        Constant.entity = "ProvidersClass";
        Constant.tfCode = tfProvider.getText();
        if(FoundHQL.workerFound()){
            labelNameProvider.setText(Constant.provider.getName());
            //checkSale();
        }

        if(Objects.equals(ConstantsPurchases.entity, "Credit")){
            ObservableList<InvoiceClass> listOne = FXCollections.observableArrayList(ConstantsPurchases.invoiceCredit);
            if(!tfProvider.getText().isBlank()) {
                FilteredList<InvoiceClass> filteredCode = new FilteredList<InvoiceClass>(listOne, s -> s.getIdProviders().contains(tfProvider.getText()));
                tableInvoice.setItems(filteredCode);
            }else {
                tableInvoice.setItems(listOne);
                labelNameProvider.setText("Nombre Proveedor");
            }
        }
    }
    public void productFound(ActionEvent event) {
        switch (ConstantsPurchases.entity){
            case "Purchases":
                Constant.entity = "ProductClass";
                Constant.tfCode = tfProduct.getText();
                if(checkProduct.isSelected()){
                    checkProductIsTrue();
                }else{
                    comboBoxPrice.getItems().clear();
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
                    comboBoxPrice.setValue(listProduct.get(0));
                    comboBoxPrice.getItems().addAll(listProduct);
                    tfOff.setText("0");
                    tfAmount.setText("1");
                    tfProfit.setText(ConstantsWare.product.getProfit());
                }
                break;
            case "Service":
                Constant.entity = "ServiceClass";
                Constant.tfCode = tfProduct.getText();
                if(checkProduct.isSelected()){
                    checkProductIsTrue();
                }else{
                    comboBoxPrice.getItems().clear();
                    FoundHQL.workerFound();
                    labelNameProduct.setText(ConstantsWare.service.getName());
                    tfPrice.setText(ConstantsWare.service.getCost());

                    comboBoxWare.getItems().clear();
                    comboBoxWare.getItems().add(ConstantsWare.service.getIdWare());
                    comboBoxWare.setValue(ConstantsWare.service.getIdWare());

                    tfOff.setText("0");
                    tfAmount.setText("1");
                    tfPriceSale.setText(ConstantsWare.service.getProfit());
                    tfProfit.setText("30");
                }
                break;
            default:
                break;
        }
    }
    public void checkProductIsTrue(){
        if(FoundHQL.workerFound()){
            tfProductName.setDisable(true);
            String message = "El codigo ya existe";
            WareController.alertSend(message);

        }else{
            tfProductName.setDisable(false);
        }
        if(ConstantsPurchases.entity.equals("Service")){
            Constant.entity = "WarehouseClass";
            SearchHQL.searchHQL();
            comboBoxWare.getItems().clear();
            ArrayList<String> wares = new ArrayList<>();
            for(WarehouseClass wh: ConstantsWare.wareList) {
                wares.add(wh.getIdWarehouse());
            }
            comboBoxWare.getItems().addAll(wares);
            comboBoxWare.setValue(ConstantsWare.service.getIdWare());
        }
    }
    public void checkProduct(ActionEvent event) {
        ArrayList<String> listProduct = new ArrayList<>();
        tfProduct.setText("");
        labelNameProduct.setText("");
        if(checkProduct.isSelected()){
            tfProductName.setVisible(true);
            labelNameProduct.setVisible(false);
            comboBoxOne.getItems().clear();
            comboBoxOne.getItems().addAll(WareController.categoryOne(listProduct));

        }else{
            tfProductName.setVisible(false);
            labelNameProduct.setVisible(true);
            comboBoxOne.getItems().clear();
        }


    }
    public void cbPrice(ActionEvent event) {
        ArrayList<String> listProduct = new ArrayList<>();
        if(checkProduct.isSelected()){
            //carga todas las comboBox
            Constant.entity = "WarehouseClass";
            comboBoxWare.getItems().clear();
            for(WarehouseClass w: ConstantsWare.wareList){
                listProduct.add(w.getIdWarehouse());
            }
            comboBoxWare.getItems().addAll(listProduct);
            listProduct.clear();
        }else{
        for(WareProductClass w: ConstantsWare.product.getWareProductsByIdProduct()){
            String house = w.getIdWare();
            for(ProductpriceClass p: w.getProductpricesByIdWareProduct()){
                String price = String.valueOf(p.getPrice());
                if(price.equals(comboBoxPrice.getValue())){
                    listProduct.add(house);
                }
            }
        }
        tfPrice.setText(comboBoxPrice.getValue());
        comboBoxWare.getItems().clear();
        comboBoxWare.getItems().addAll(listProduct);
        checkSale();
        listProduct.clear();
        }
    }
    public void priceNew(KeyEvent keyEvent) {
        //Cuando se agrega un valor al tfPrice da la opcion para agregar a cualquier bodega
        comboBoxPrice.setValue("0");

        ArrayList<String> listProduct = new ArrayList<>();
        Constant.entity = "WarehouseClass";
        SearchHQL.searchHQL();

        for(WarehouseClass w: ConstantsWare.wareList){
            listProduct.add(w.getIdWarehouse());
        }
        comboBoxWare.getItems().clear();
        comboBoxWare.setValue(listProduct.get(0));
        comboBoxWare.getItems().addAll(listProduct);
        checkSale();

        if(!tfPrice.getText().equals("0") && !tfPrice.getText().equals("")) {
            int newPriceBuy = Integer.parseInt(tfPrice.getText());
            int priceSale = Integer.parseInt(tfPriceSale.getText());
            int profit = ((priceSale - newPriceBuy) / newPriceBuy) * 100;
            tfProfit.setText(String.valueOf(profit));
        }
    }
    public void priceSale(){
        int price;
        if(!tfPrice.getText().equals("") && !tfPrice.getText().equals("0")){
            price = Integer.valueOf(tfPrice.getText());
        }else{
            price = Integer.valueOf(comboBoxPrice.getValue());
        }
        if(!Objects.equals(tfProfit.getText(), "")) {
            int profit = Integer.valueOf(tfProfit.getText());
            int sale = price + (price * profit / 100);
            tfPriceSale.setText(String.valueOf(sale));
        }
    }
    public void checkSale() {
        if(checkSale.isSelected()){
            //precio nuevo
            tfPriceSale.setDisable(false);
            priceSale();
        }else{
            //precio actual
            tfPriceSale.setDisable(true);
            switch (ConstantsPurchases.entity){
                case "Purchases":
                    tfPriceSale.setText(ConstantsWare.product.getSalePrice());
                    break;
                case "Service":
                    tfPriceSale.setText(tfPrice.getText());
                    tfProfit.setText("30");
                    break;
                default:
                    break;
            }
        }
    }
    public void register(ActionEvent event) {
        if(verify()){

            switch (ConstantsPurchases.entity){
                case "Purchases":
                    if(checkProduct.isSelected()){

                        ProductClass product = new ProductClass();
                        product.setIdProduct(tfProduct.getText());
                        product.setName(tfProductName.getText());
                        product.setPurchasePrice(tfPrice.getText());
                        product.setAmount(Integer.valueOf(tfAmount.getText()));
                        product.setSalePrice(tfPriceSale.getText());
                        product.setProfit(tfProfit.getText());
                        product.setIdOne(ConstantsWare.one.getIdOne());

                        if(ConstantsWare.two != null){
                            product.setIdTwo(ConstantsWare.two.getIdTwo());
                        }

                        if(ConstantsWare.three != null){
                            product.setIdThree(ConstantsWare.three.getIdThree());
                        }

                        product.setIdWage(comboBoxWare.getValue());
                        ConstantsPurchases.productTableList.add(product);

                    }else{
                        ConstantsWare.product.setPurchasePrice(tfPrice.getText());
                        ConstantsWare.product.setAmount(Integer.valueOf(tfAmount.getText()));
                        ConstantsWare.product.setSalePrice(tfPriceSale.getText());
                        ConstantsWare.product.setProfit(tfProfit.getText());
                        ConstantsWare.product.setIdWage(comboBoxWare.getValue());
                        if(tfOff.getText().equals("")){
                            tfOff.setText("0");
                        }
                        ConstantsWare.product.setOffSale(Integer.valueOf(tfOff.getText())); //porcentaje

                        ConstantsPurchases.productTableList.add(ConstantsWare.product);
                    }
                    break;
                case "Service":
                    tfPriceSale.setText(tfPrice.getText());
                    if(checkProduct.isSelected()){
                        ServiceClass service = new ServiceClass();
                        service.setIdService(tfProduct.getText());
                        service.setName(tfProductName.getText());
                        service.setCost(tfPrice.getText());
                        service.setProfit(tfProfit.getText());
                        service.setIdWare(comboBoxWare.getValue());
                        service.setHour(tfAmount.getText());
                        service.setPayForHour("1");
                        ConstantsPurchases.serviceTableList.add(service);

                    }else{
                        ConstantsWare.service.setCost(tfPrice.getText());
                        ConstantsWare.service.setHour(tfAmount.getText());
                        ConstantsPurchases.serviceTableList.add(ConstantsWare.service);
                    }
                    break;
                default:
                    break;
            }
            tableLoad();
        }
    }
    public void categorySelected(ActionEvent event) {
        ComboBox<String> comboBox = (ComboBox<String>) event.getSource();

        switch (comboBox.getId()){
            case "comboBoxOne":
                //guarda el codigo de la categoria 1
                Constant.entity = "CategoryoneClass";
                Constant.tfCode = comboBoxOne.getValue();
                FoundHQL.workerFound();

                //carga el comboBox de la categoria 2
                comboBoxTwo.getItems().clear();
                comboBoxTwo.getItems().addAll(WareController.categoryTwo(new ArrayList<>(), comboBox.getValue()));

                break;
            case "comboBoxTwo":
                //guarda el codigo de la categoria 2
                Constant.entity = "CategorytwoClass";
                Constant.tfCode = comboBoxTwo.getValue();
                FoundHQL.workerFound();

                //carga el comboBox de la categoria 2
                comboBoxThree.getItems().clear();
                comboBoxThree.getItems().addAll(WareController.categoryThree(new ArrayList<>(), comboBox.getValue()));
                break;
            case "comboBoxThree":
                //guarda el codigo de la categoria 2
                Constant.entity = "CategorythreeClass";
                Constant.tfCode = comboBoxThree.getValue();
                FoundHQL.workerFound();

                break;
            default:
                break;
        }
    }
    @FXML
    public void initialize() {

        ConstantsWare.one = null;
        ConstantsWare.two = null;
        ConstantsWare.three = null;

        tfAmount.setTextFormatter(createNumericTextFormatter());
        tfProfit.setTextFormatter(createNumericTextFormatter());
        tfPrice.setTextFormatter(createNumericTextFormatter());
        tfPriceSale.setTextFormatter(createNumericTextFormatter());
        tfOff.setTextFormatter(createNumericTextFormatter());
        tfBank.setTextFormatter(createNumericTextFormatter());
        tfCash.setTextFormatter(createNumericTextFormatter());
        tfTaxes.setTextFormatter(createNumericTextFormatter());

        ConstantsPurchases.entity = "Purchases";
        ConstantsPurchases.entityForInvoice = "ProvidersClass";

        taxesIVABOLD();

        c1.setCellValueFactory(new PropertyValueFactory<>("idProduct"));
        c2.setCellValueFactory(new PropertyValueFactory<>("name"));
        c3.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));
        c4.setCellValueFactory(new PropertyValueFactory<>("amount"));
        c5.setCellValueFactory(new PropertyValueFactory<>("profit"));
        c6.setCellValueFactory(new PropertyValueFactory<>("salePrice"));
        c7.setCellValueFactory(new PropertyValueFactory<>("idOne"));
        c8.setCellValueFactory(new PropertyValueFactory<>("idTwo"));
        c9.setCellValueFactory(new PropertyValueFactory<>("idThree"));
        c10.setCellValueFactory(new PropertyValueFactory<>("idWage"));

        c21.setCellValueFactory(new PropertyValueFactory<>("idService"));
        c22.setCellValueFactory(new PropertyValueFactory<>("name"));
        c23.setCellValueFactory(new PropertyValueFactory<>("hour"));
        c24.setCellValueFactory(new PropertyValueFactory<>("cost"));
        c25.setCellValueFactory(new PropertyValueFactory<>("idWare"));

        i1.setCellValueFactory(new PropertyValueFactory<>("idInvoice"));
        i2.setCellValueFactory(new PropertyValueFactory<>("date"));
        i3.setCellValueFactory(new PropertyValueFactory<>("idProviders"));
        i4.setCellValueFactory(new PropertyValueFactory<>("idBill"));
        i5.setCellValueFactory(new PropertyValueFactory<>("total"));

        wi1.setCellValueFactory(new PropertyValueFactory<>("idWareInvoice"));
        wi2.setCellValueFactory(new PropertyValueFactory<>("productName"));
        wi3.setCellValueFactory(new PropertyValueFactory<>("amount"));
        wi4.setCellValueFactory(new PropertyValueFactory<>("priceBuy"));

        m1.setCellValueFactory(new PropertyValueFactory<>("id"));
        m2.setCellValueFactory(new PropertyValueFactory<>("date"));
        m3.setCellValueFactory(new PropertyValueFactory<>("idInvoice"));
        m4.setCellValueFactory(new PropertyValueFactory<>("debt"));
        m5.setCellValueFactory(new PropertyValueFactory<>("payCash"));
        m6.setCellValueFactory(new PropertyValueFactory<>("payBank"));
        m7.setCellValueFactory(new PropertyValueFactory<>("subtotal"));

        bSettings.setDisable(false);
        bSale.setDisable(false);
        bBuy.setDisable(false);
        bAccounting.setDisable(!Constant.isAdmin);
        bDash.setDisable(!Constant.isAdmin);
        bWare.setDisable(!Constant.isAdmin);

        buttonProduct0.setStyle("-fx-background-color: #F5F5F5; ");
        option("buttonProduct0");
    }
    // Crear un TextFormatter que solo permita números
    public Boolean verify(){
        String falseFor = "";
        try {
            if((tfProductName.getText().isEmpty() && labelNameProduct.getText().isEmpty()) || tfProduct.getText().isEmpty()){
                falseFor = "Name";
            }

            if(comboBoxPrice.getValue() == null || comboBoxPrice.getValue().equals("0")){
                if(tfPrice.getText().isEmpty() || tfPrice.getText().equals("0")){
                    falseFor = "Price";
                }
            }

            if(tfAmount.getText().isEmpty()){
                falseFor = "Amount";
            }else{
                int a = Integer.valueOf(tfAmount.getText());
                if(a <= 0){
                    falseFor = "Amount";
                }
            }

            if(tfProfit.getText().isEmpty()){
                falseFor = "Profit";
            }else{
                int  p = Integer.valueOf(tfProfit.getText());
                if(p <= 0){
                    falseFor = "Profit";
                }
            }

            if(tfPriceSale.getText().isEmpty()){
                falseFor = "Sale";
            }else{
                int v = Integer.valueOf(tfPriceSale.getText());
                if(v <= 0){
                    falseFor = "Sale";
                }
            }
            if(comboBoxWare.getValue() == null || comboBoxWare.getValue().isBlank()){
                falseFor = "Ware";
            }

            switch (falseFor){
                case "Name":
                    WareController.alertSend("Digitar nombre del producto");
                    return false;
                case "Price":
                    WareController.alertSend("Digitar el precio del producto");
                    return false;
                case "Amount":
                    WareController.alertSend("Digitar la cantidad del producto");
                    return false;
                case "Profit":
                    WareController.alertSend("Digitar el profit del producto");
                    return false;
                case "Sale":
                    WareController.alertSend("Digitar el precio de venta del producto");
                    return false;
                case "Ware":
                    WareController.alertSend("Digitar la bodega del producto");
                    return false;
                default:
                    return true;
            }
        }catch (Exception i){
            i.printStackTrace();
            WareController.alertSend(String.valueOf(i));
            System.out.println(falseFor);
            return false;
        }

    }
    public void tableLoad(){

        switch (ConstantsPurchases.entity){
            case "Purchases":
                tableMain.getItems().clear();
                ObservableList<ProductClass> datesTTT = FXCollections.observableArrayList(ConstantsPurchases.productTableList);
                tableMain.setItems(datesTTT);
                labelTotal2.setText("0");
                //Obtiene el total sin Taxes
                for(ProductClass p: tableMain.getItems()){

                    int subtotal = ConstantsPurchases.subtotalProduct(String.valueOf(p.getAmount()), p.getPurchasePrice(), String.valueOf(p.getOffSale()));
                    int balance = Integer.valueOf(labelTotal2.getText());
                    int total = subtotal + balance;
                    labelTotal2.setText(String.valueOf(total));
                }

                break;
            case "Service":
                tableService.getItems().clear();
                ObservableList<ServiceClass> datServices = FXCollections.observableArrayList(ConstantsPurchases.serviceTableList);
                tableService.setItems(datServices);
                labelTotal2.setText("0");
                for(ServiceClass p: tableService.getItems()){

                    int subtotal = ConstantsPurchases.subtotalProduct(String.valueOf(p.getHour()), p.getCost(), "0");

                    int balance = Integer.valueOf(labelTotal2.getText());

                    int total = subtotal + balance;

                    labelTotal2.setText(String.valueOf(total));
                }
                break;
            case "Credit":
                ConstantsPurchases.listInvoiceSearch();
                tableInvoice.getItems().clear();
                try{
                    ArrayList<InvoiceClass> invoiceFiltrate = new ArrayList<>();

                    for(InvoiceClass iv: ConstantsPurchases.invoiceList){
                        if(iv.getIdProviders() != null){
                            invoiceFiltrate.add(iv);
                            ConstantsPurchases.invoiceCredit.add(iv);
                        }
                    }
                    ObservableList<InvoiceClass> invoiceTable =
                            FXCollections.observableArrayList(invoiceFiltrate);
                    tableInvoice.setItems(invoiceTable);
                }catch (Exception i){
                    System.out.println(i);
                    WareController.alertSend("Sin Facturas de clientes");
                }
                break;
            default:
                break;
        }

        if(!ConstantsPurchases.entity.equals("Credit")){
            labelTotal3.setText(labelTotal2.getText());
            costTaxes();
            tfProduct.setText("");
            tfProductName.setText("");
            labelNameProduct.setText("");
            comboBoxPrice.getItems().clear();
            tfPrice.setText("0");
            tfOff.setText("0");
            tfAmount.setText("0");
            tfProfit.setText("0");
            tfPriceSale.setText("0");
            comboBoxWare.getItems().clear();
            ConstantsWare.one = null;
            ConstantsWare.two = null;
            ConstantsWare.three = null;
            pay();
        }
    }
    public void suppress(ActionEvent event) {

        int a = tableMain.getSelectionModel().getSelectedIndex();

        String nameProduct = (String) c2.getCellData(a);

        if (nameProduct != null) {
            //elimina de la tabla
            tableMain.getItems().remove(a);
            //elimina del array list
            ConstantsPurchases.productTableList.remove(a);
            //carga la tabla para obtener el total
            tableLoad();
        }
    }
    public void taxes(ActionEvent event) {
        Button button = (Button) event.getSource();
        switch (button.getId()){
            case "buttonIVA":
                ConstantsPurchases.iva = !ConstantsPurchases.iva;
                break;
            case "buttonBold":
                ConstantsPurchases.boldP = !ConstantsPurchases.boldP;
                break;
            default:
                break;
        }
        tableLoad();
    }
    public void costTaxes(){
        if(!labelIVA.getText().equals("IVA")){
            double ivaTaxes = Double.valueOf(labelIVA.getText());
            int subtotal = Integer.valueOf(labelTotal2.getText());
            int ivaSubtotal = (int) (ivaTaxes * subtotal / 100);
            labelIVA2.setText(String.valueOf(ivaSubtotal));
        }
        if(!labelBold.getText().equals("BOLD")){
            double boldTaxes = Double.valueOf(labelBold.getText());
            int subtotal = Integer.valueOf(labelTotal2.getText());
            int boldSubtotal = (int) (boldTaxes * subtotal / 100);
            labelBold2.setText(String.valueOf(boldSubtotal));
        }
        if(ConstantsPurchases.iva){
            int iva = Integer.valueOf(labelIVA2.getText());
            int subtotal = Integer.valueOf(labelTotal3.getText());
            int total = iva + subtotal;
            labelTotal3.setText(String.valueOf(total));
        }
        if(ConstantsPurchases.boldP){
            int bold = Integer.valueOf(labelBold2.getText());
            int subtotal = Integer.valueOf(labelTotal3.getText());
            int total = bold + subtotal;
            labelTotal3.setText(String.valueOf(total));
        }
    }
    public void taxesIVABOLD(){
        Constant.entity = "TaxesClass";
        FoundHQL.workerFound();
        for(TaxesClass tx: ConstantsAccounting.taxesList){
            if(tx.getIdTaxes().equals("IVA")){
                labelIVA.setText(tx.getTaxes());

            }
            if(tx.getIdTaxes().equals("BOLD")){
                labelBold.setText(tx.getTaxes());
            }
        }
    }
    public void pay(){
        int total = Integer.valueOf(labelTotal3.getText());
        int cash = Integer.valueOf(tfCash.getText());
        int bank = Integer.parseInt(tfBank.getText());
        int pay = cash + bank;
        int debt = total - pay;
        if(debt < 0){
            WareController.alertSend("Error de pago: la cantidad de pago es mayor a la cuenta por pagar, por favor verifique el monto");
            ConstantsPurchases.checkin = true;
        } else {
            labelDebt.setText(String.valueOf(debt));
            labelPay.setText(String.valueOf(pay));
            ConstantsPurchases.checkin = false;
        }
    }
    public void payment(KeyEvent keyEvent) {
        pay();
        buttonCheckIn.setDisable(ConstantsPurchases.checkin);

    }
    public void checkinInvoice(ActionEvent event) {
        /*Crea un invoice solo con los datos necesarios para
        *obtener la id, es obligatorio ingresar
        *bak, cash, date y total
        * con el idBill se encuentra la factura
        * */
        InvoiceClass invoice = new InvoiceClass();
        invoice.setBank(tfBank.getText());
        invoice.setCash(tfCash.getText());
        invoice.setIndebtedness(labelDebt.getText());
        invoice.setTotal(labelTotal3.getText());
        invoice.setDate(ConstantsPurchases.dateActually());
        invoice.setIdBill(666);
        ConstantsAccounting.invoice = invoice;
        Constant.entity = "InvoiceClass";
        ConstantsPurchases.invoiceType = "purchaseFromSupplier";
        if(SaveHQL.workerInsertUpdate()){
        //si se guarda la factura, ahora la recupera.
            Constant.tfCode = "666";
            FoundHQL.wareFound();


            switch (ConstantsPurchases.entity){
                case "Purchases":
                    //cada producto debe de crear un wareinvoice
                    for(ProductClass p: ConstantsPurchases.productTableList){
                        WareinvoiceClass wi = new WareinvoiceClass();

                        wi.setIdInvoice(ConstantsAccounting.invoice.getIdInvoice());
                        wi.setIdProduct(p.getIdProduct());
                        wi.setProductName(p.getName());
                        wi.setPriceSale(p.getSalePrice());
                        wi.setPriceBuy(Integer.parseInt(p.getPurchasePrice()));
                        wi.setAmount(p.getAmount());
                        wi.setIndexWare(ConstantsPurchases.productTableList.indexOf(p));
                        //agrega el wareproduct a la lista
                        ConstantsPurchases.wareInvoiceList.add(wi);

                        //actualiza los productos
                        try{
                            for(WareProductClass wp: p.getWareProductsByIdProduct()){
                                //Si la bodega corresponde a una registrada
                                if(Objects.equals(wp.getIdWare(), p.getIdWage())){
                                    for(ProductpriceClass pp: wp.getProductpricesByIdWareProduct()){
                                        //si el precio es el registrado
                                        if(pp.getPrice() == Integer.parseInt(p.getPurchasePrice())){
                                            //agrega el objeto productprice a la lista para actualizar
                                            //actualiza la cantidad en inventario
                                            pp.setAmount(p.getAmount() + pp.getAmount());
                                            //guarda la cantidad actualizada
                                            ConstantsPurchases.pPriceUpdateList.add(pp);
                                        }
                                    }
                                }
                            }
                        }catch (Exception i){
                            //en caso de error es porque el producto es nuevo o tiene nuevo precio
                            ConstantsPurchases.productNewList.add(p);
                        }

                    }
                    break;
                case "Service":
                    //cada servicio debe de crear un wareinvoice
                    for(ServiceClass s: ConstantsPurchases.serviceTableList){
                        WareinvoiceClass wiS = new WareinvoiceClass();

                        wiS.setIdInvoice(ConstantsAccounting.invoice.getIdInvoice());
                        wiS.setIdProduct(s.getIdService());
                        wiS.setProductName(s.getName());
                        wiS.setPriceSale(s.getCost());
                        wiS.setPriceBuy(Integer.parseInt(s.getCost()));
                        wiS.setAmount(Integer.parseInt(s.getHour()));
                        wiS.setIndexWare(ConstantsPurchases.productTableList.indexOf(s));
                        //agrega el wareproduct a la lista
                        ConstantsPurchases.wareInvoiceList.add(wiS);
                    }
                    break;
                default:
                    System.out.println("Error en  ConstantsPurchases.entity");
                    break;
            }
            //Busca la compañia
            ConstantsPurchases.invoiceType = "purchaseFromSupplier";
            Constant.entity = "CompanyClass";
            Constant.tfCode = "1";
            FoundHQL.workerFound();

            //Actualiza la invoice
            ConstantsAccounting.invoice.setIdCompany(Constant.company.getIdCompanyNIT());
            ConstantsAccounting.invoice.setIdProviders(Constant.provider.getNit());
            ConstantsAccounting.invoice.setTaxes(labelIVA2.getText());
            ConstantsAccounting.invoice.setTotalBuy(labelTotal3.getText());
            ConstantsAccounting.invoice.setUtilities("0");
            ConstantsAccounting.invoice.setRUtilities("0");
            //idBIll se usa para guardar la deuda actual
            ConstantsAccounting.invoice.setIdBill(Integer.parseInt(ConstantsAccounting.invoice.getIndebtedness()));

            //Actualiza la cuenta de la compañia y el proveedor
            ConstantsPurchases.purchaseCompany(ConstantsAccounting.invoice.getBank(), ConstantsAccounting.invoice.getCash(), ConstantsAccounting.invoice.getIndebtedness());
            ConstantsPurchases.purchaseProvider(ConstantsAccounting.invoice.getBank(), ConstantsAccounting.invoice.getCash(), ConstantsAccounting.invoice.getIndebtedness());

            //Aqui se genera el sql que manda a guardar y actualizar todos los datos
            if(SaveHQL.saveInvoice()){

                WareController.alertSend("Datos guardados con exito");
                if(ConstantsPurchases.entity.equals("Purchases")){
                    option("buttonProduct0");
                } else if (ConstantsPurchases.entity.equals("Service")) {
                    option("buttonService");
                }
            }else {
                WareController.alertSend("Error al guardar los datos");
            }

        }
    }
    public void invoiceView() {
        try {
            //carga los productos de la factura
            InvoiceClass invoiceSelected = tableInvoice.getSelectionModel().getSelectedItem();
            ConstantsAccounting.invoice = invoiceSelected;
            ConstantsPurchases.listWareInvoiceSearch(invoiceSelected);

            tableWareInv.getItems().clear();
            ObservableList<WareinvoiceClass> wiTable = FXCollections.observableArrayList(ConstantsPurchases.wareInvoiceList);
            tableWareInv.setItems(wiTable);

            //Carga la tabla de deudas si tiene
            ConstantsPurchases.listMoveSearch(invoiceSelected);
            if (!ConstantsPurchases.moveInvoiceList.isEmpty()) {
                tableMoveInv.getItems().clear();
                ObservableList<MoveinvoiceClass> miTable = FXCollections.observableArrayList(ConstantsPurchases.moveInvoiceList);
                tableMoveInv.setItems(miTable);
            } else {
                tableMoveInv.getItems().clear();
                labelTotal2.setText("0");
                labelTotal3.setText("0");
                tfTaxes.setText("0");
            }

            //Carga la deuda pendiente de pagar
            if (0 != invoiceSelected.getIdBill()) {
                labelTotal2.setText(String.valueOf(invoiceSelected.getIdBill()));
                labelTotal3.setText(labelTotal2.getText());
                tfTaxes.setText("0");
            }
        }catch (Exception i){
            System.out.println(i+" Ningun invoice seleccionado");
        }
    }
    public void sobreCost(KeyEvent keyEvent) {
        int subtotal = Integer.parseInt(labelTotal2.getText());
        int sobreCosto = Integer.parseInt(tfTaxes.getText());
        int total = subtotal +sobreCosto;
        labelTotal3.setText(String.valueOf(total));
    }
    public void quote(ActionEvent event) {

        MoveinvoiceClass mi = new MoveinvoiceClass();

        mi.setDate(ConstantsPurchases.dateActually());
        mi.setDebt(Integer.parseInt(labelTotal2.getText()));
        mi.setPayCash(Integer.parseInt(tfCash.getText()));
        mi.setPayBank(Integer.parseInt(tfBank.getText()));
        mi.setSubtotal(Integer.parseInt(labelDebt.getText()));
        mi.setInvoiceByIdInvoice(ConstantsAccounting.invoice);
        mi.setIdInvoice(ConstantsAccounting.invoice.getIdInvoice());

        ConstantsPurchases.moveInv = mi;

        Constant.entity = "MoveinvoiceClass";
        SaveHQL.insertWorker("save");

        tableLoad();

    }
}
