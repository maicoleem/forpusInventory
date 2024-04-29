package com.forpus.forpusinventory.controller;

import com.forpus.forpusinventory.HelloApplication;
import com.forpus.forpusinventory.domain.repository.ReportGenerator;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Objects;
import static com.forpus.forpusinventory.domain.services.ConstantsPurchases.createNumericTextFormatter;

public class SalesController {

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

    public TableView<ServiceClass> tableService;
    public TableColumn<Object, Object> c21;
    public TableColumn<Object, Object> c22;
    public TableColumn<Object, Object> c23;
    public TableColumn<Object, Object> c24;
    public TableColumn<Object, Object> c25;
    public TableView<InvoiceClass> tableInvoice;
    public TableColumn<Object, Object> i1;
    public TableColumn<Object, Object> i2;
    public TableColumn<Object, Object> i3;
    public TableColumn<Object, Object> i4;
    public TableColumn<Object, Object> i5;
    public TableView<WareinvoiceClass> tableWareInv;
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
    public Button found;
    public Button cancel;
    public Button save;
    public Button search;
    public Button remove;
    public Button buttonWarePB;
    public Button buttonDashPB;
    public Button buttonAccountingPB;
    public Button buttonBuyPB;
    public Button buttonSalePB;
    public Button buttonSettingsPB;
    public Button buttonProduct0;
    public Button buttonService;
    public Button buttonCredit;
    public Label labelCliente;
    public TableView<ProductClass> tableMain;
    public Label labelNameCliente;
    public TextField tfCliente;
    public ComboBox<String> comboBoxPrice;
    public Button buttonCliente;
    public Label labelProduct;
    public TextField tfProduct;
    public Label labelNameProduct;
    public Button buttonProduct;
    public TextField tfOff;
    public Label labelOff;
    public Button buttonRegister;
    public Pane panelTotal;
    public Label labelBold;
    public Button buttonBold;
    public Label labelIVA;
    public Label labelSubTota2;
    public Label labelTotal;
    public Button buttonIVA;
    public Label labelTotal2;
    public Label labelIVA2;
    public Label labelBold2;
    public Label labelSubTotal;
    public TextField tfTaxes;
    public Pane panelPayment;
    public Label labelCash;
    public Label labelBank;
    public TextField tfCash;
    public TextField tfBank;
    public Label labelDebt;
    public Label labelPay;
    public Pane panelCheckIn;
    public Button buttonCheckIn;
    public Button buttonQuote;
    public Label labelPriceSale;
    public TextField tfPriceSale;
    public Label labelAmount;
    public TextField tfAmount;
    public ComboBox<String> comboBoxWare;
    public TextField tfProductName;
    public Button buttonSuppress;
    public ComboBox<String> comboBoxAmount;
    public Label labelUtilities;
    public Button buttonFactura;
    public TextField tfFactura;
    public Button bWare;
    public Button bDash;
    public Button bAccounting;
    public Button bBuy;
    public Button bSale;
    public Button bSettings;
    public TextField tfOffPer;
    public Pane paneProduct;
    public TableView<ProductClass> tableProductSearch;
    public TableColumn<Object, Object> columCode;
    public TableColumn<Object, Object> columProduct;
    public TableColumn<Object, Object> columPrice;
    public TextField tfCodeSeek;
    public TextField tfProductSeek;
    public TextField tfPriceSeek;
    public TextField tfCodeFound;

    public void initialize() {

        labelIVA.setText("0");
        labelIVA2.setText("0");
        labelBold.setText("0");
        labelBold2.setText("0");

        tfAmount.setTextFormatter(createNumericTextFormatter());
        tfPriceSale.setTextFormatter(createNumericTextFormatter());
        tfOff.setTextFormatter(createNumericTextFormatter());
        tfBank.setTextFormatter(createNumericTextFormatter());
        tfCash.setTextFormatter(createNumericTextFormatter());
        tfTaxes.setTextFormatter(createNumericTextFormatter());
        tfOffPer.setTextFormatter(createNumericTextFormatter());

        Constant.entity = "CustomerClass";
        ConstantsSales.salesOption = "Product";
        ConstantsPurchases.entity = "SaleProduct";

        taxesIVABOLD();
        loadTableProduct();

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
        c24.setCellValueFactory(new PropertyValueFactory<>("profit"));
        c25.setCellValueFactory(new PropertyValueFactory<>("idWare"));

        i1.setCellValueFactory(new PropertyValueFactory<>("idInvoice"));
        i2.setCellValueFactory(new PropertyValueFactory<>("date"));
        i3.setCellValueFactory(new PropertyValueFactory<>("idCustomer"));
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

    }
    //Carga las taxaqs de impuestos
    public void taxesIVABOLD(){
        try {
            Constant.entity = "TaxesClass";
            FoundHQL.workerFound();
            for (TaxesClass tx : ConstantsAccounting.taxesList) {
                if (tx.getIdTaxes().equals("IVA")) {
                    labelIVA.setText(tx.getTaxes());
                    ConstantsSales.iva = tx.getTaxes();

                }
                if (tx.getIdTaxes().equals("BOLD")) {
                    labelBold.setText(tx.getTaxes());
                    ConstantsSales.bold = tx.getTaxes();
                }
            }
        }catch (Exception i){
            i.printStackTrace();
        }
    }
    public void clean(){
        try{
        final boolean a = false;

        labelProduct.setVisible(a);
        labelNameProduct.setVisible(a);
        labelOff.setVisible(a);
        labelAmount.setVisible(a);
        labelUtilities.setVisible(a);

        tfProduct.setVisible(a);
        tfOff.setVisible(a);
        tfAmount.setVisible(a);
        tfPriceSale.setVisible(a);
        tfProductName.setVisible(a);
        tfTaxes.setVisible(a);

        buttonProduct.setVisible(a);
        buttonRegister.setVisible(a);
        buttonSuppress.setVisible(a);
        buttonFactura.setVisible(a);
        tfOffPer.setVisible(a);
        paneProduct.setVisible(a);

        search.setVisible(a);
        save.setVisible(a);
        found.setVisible(a);
        cancel.setVisible(a);
        remove.setVisible(a);

        comboBoxWare.setVisible(a);
        comboBoxPrice.setVisible(a);
        comboBoxAmount.setVisible(a);

        tableMain.setVisible(a);
        tableService.setVisible(a);
        tableInvoice.setVisible(a);
        tableMoveInv.setVisible(a);
        tableWareInv.setVisible(a);

        panelCheckIn.setVisible(a);
        buttonCheckIn.setVisible(!a);
        buttonIVA.setVisible(!a);
        buttonBold.setVisible(!a);
        labelIVA.setText("IVA");
        labelIVA2.setVisible(!a);
        labelBold.setVisible(!a);
        labelBold2.setVisible(!a);

        panelPayment.setVisible(a);
        panelTotal.setVisible(a);

        labelTotal2.setText("0");
        labelIVA2.setText("0");
        labelBold2.setText("0");
        tfCash.setText("0");
        tfBank.setText("0");
        labelPay.setText("0");
        labelDebt.setText("0");

        buttonProduct0.setStyle("-fx-background-color: #1BA1E2; ");
        buttonService.setStyle("-fx-background-color: #1BA1E2; ");
        buttonCredit.setStyle("-fx-background-color: #1BA1E2; ");
        try {
            if (!ConstantsPurchases.productTableList.isEmpty()) {
                ConstantsPurchases.productTableList.clear();
            }
            if (!ConstantsPurchases.serviceTableList.isEmpty()) {
                ConstantsPurchases.serviceTableList.clear();
            }
            if (!ConstantsPurchases.wareInvoiceList.isEmpty()) {
                ConstantsPurchases.wareInvoiceList.clear();
            }
            if (!ConstantsPurchases.invoiceList.isEmpty()) {
                ConstantsPurchases.invoiceList.clear();
            }
            if (!tableService.getItems().isEmpty()) {
                tableService.getItems().clear();
            }
            if (!tableMain.getItems().isEmpty()) {
                tableMain.getItems().clear();
            }
        }catch (Exception i){
            System.out.println(i + "ERROR EN CLEAN() SALECONTROLLER");
        }
        }catch (Exception i){
            i.printStackTrace();
        }
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
        try{
        Button button = (Button) event.getSource();
        clean();
        final boolean a = true;
        switch (button.getId()){
            case "buttonProduct0":
                buttonProduct0.setStyle("-fx-background-color: #F5F5F5; ");
                ConstantsSales.salesOption = "Product";
                Constant.entity = "CustomerClass";
                ConstantsPurchases.entity = "SaleProduct";

                panelPayment.setVisible(a);
                panelCheckIn.setVisible(a);
                panelTotal.setVisible(a);
                paneProduct.setVisible(a);

                labelProduct.setVisible(a);
                labelProduct.setText("Producto");
                labelNameProduct.setVisible(a);
                labelNameProduct.setText("Nombre Producto");
                labelNameCliente.setVisible(a);
                labelAmount.setVisible(a);
                labelUtilities.setVisible(a);

                tfOffPer.setVisible(a);

                labelPriceSale.setVisible(a);
                labelOff.setVisible(a);

                tfProduct.setVisible(a);
                tfOff.setVisible(a);
                tfAmount.setVisible(a);
                tfAmount.setText("1");
                tfPriceSale.setVisible(a);

                comboBoxAmount.setVisible(a);
                comboBoxWare.setVisible(a);
                comboBoxPrice.setVisible(a);

                tableMain.setVisible(a);

                buttonProduct.setVisible(a);
                buttonRegister.setVisible(a);
                search.setVisible(a);
                save.setVisible(a);
                found.setVisible(a);
                cancel.setVisible(a);
                remove.setVisible(a);
                buttonSuppress.setVisible(a);
                buttonFactura.setVisible(a);
                buttonQuote.setText("COTIZACIÓN");

                taxesIVABOLD();

                break;
            case "buttonService":
                buttonService.setStyle("-fx-background-color: #F5F5F5; ");
                ConstantsSales.salesOption = "Service";
                Constant.entity = "CustomerClass";
                ConstantsPurchases.entity = "Service";

                panelPayment.setVisible(a);
                panelCheckIn.setVisible(a);
                panelTotal.setVisible(a);

                tfOffPer.setVisible(a);

                labelProduct.setVisible(a);
                labelProduct.setText("Servicio");
                labelNameProduct.setVisible(a);
                labelNameProduct.setText("Nombre Servicio");
                labelNameCliente.setVisible(a);
                labelAmount.setVisible(a);

                labelPriceSale.setVisible(a);
                labelOff.setVisible(a);

                tfProduct.setVisible(a);
                tfOff.setVisible(a);
                tfAmount.setVisible(a);
                tfPriceSale.setVisible(a);

                comboBoxWare.setVisible(a);
                comboBoxPrice.setVisible(a);

                tableService.setVisible(a);

                buttonProduct.setVisible(a);
                buttonRegister.setVisible(a);
                search.setVisible(a);
                save.setVisible(a);
                found.setVisible(a);
                cancel.setVisible(a);
                remove.setVisible(a);
                buttonSuppress.setVisible(a);
                buttonFactura.setVisible(a);
                buttonQuote.setText("COTIZACIÓN");
                taxesIVABOLD();

                break;
            case "buttonCredit":
                buttonCredit.setStyle("-fx-background-color: #F5F5F5; ");
                ConstantsSales.salesOption = "Credit";
                Constant.entity = "CustomerClass";
                ConstantsPurchases.entity = "customersDebt";
                ConstantsPurchases.entityForInvoice = "CustomersClass";

                tableInvoice.setVisible(a);
                tableMoveInv.setVisible(a);
                tableWareInv.setVisible(a);
                labelNameCliente.setVisible(a);
                labelCliente.setVisible(a);
                buttonCliente.setVisible(a);

                search.setVisible(a);

                tfTaxes.setVisible(a);
                tfCliente.setText("");

                panelPayment.setVisible(a);
                buttonIVA.setVisible(!a);
                tfOffPer.setVisible(!a);
                buttonBold.setVisible(!a);
                labelIVA.setText("Mora");
                labelIVA2.setVisible(!a);
                labelBold.setVisible(!a);
                labelBold2.setVisible(!a);
                panelTotal.setVisible(a);
                panelCheckIn.setVisible(a);
                buttonCheckIn.setVisible(!a);
                buttonFactura.setVisible(a);
                buttonQuote.setText("REGISTRAR PAGO");
                tableLoad();

                break;
            default:
                break;
        }
        }catch (Exception i){
            i.printStackTrace();
        }
    }
    public void clientFound(ActionEvent event) {
        try {
        Constant.entity = "CustomerClass";
        Constant.tfCode = tfCliente.getText();
        if(FoundHQL.workerFound()) {
            labelNameCliente.setText(Constant.customer.getName());
        }
        if(Objects.equals(ConstantsSales.salesOption, "Credit")){
            ObservableList<InvoiceClass> listOne = FXCollections.observableArrayList(ConstantsPurchases.invoiceCredit);
            if(!tfCliente.getText().isBlank()){
                tableInvoice.getItems().clear();
                FilteredList<InvoiceClass> filteredCode = new FilteredList<InvoiceClass>(listOne, s -> s.getIdCustomer().contains(tfCliente.getText()));
                tableInvoice.setItems(filteredCode);
            }else{
                tableInvoice.setItems(listOne);
                labelNameCliente.setText("Nombre Cliente");
            }
        }
        }catch (Exception i){
            i.printStackTrace();
        }
    }
    public void productFound(ActionEvent event) {
        try {
            switch (ConstantsSales.salesOption) {
                case "Product":
                    Constant.entity = "ProductClass";
                    Constant.tfCode = tfProduct.getText();
                    comboBoxPrice.getItems().clear();
                    comboBoxWare.getItems().clear();
                    comboBoxAmount.getItems().clear();
                    FoundHQL.workerFound();
                    labelNameProduct.setText(ConstantsWare.product.getName());
                    /*Obtener la lista de precios del producto
                     *Crear array donde estaran los precios
                     * Hacer un for para buscar en todas las bodegas
                     * Hacer un for para buscar los precios
                     * Adicionar al combobox
                     * */
                    ArrayList<String> listProduct = new ArrayList<>();
                    ArrayList<String> listWarehouse = new ArrayList<>();
                    ArrayList<String> listAmount = new ArrayList<>();
                    for (WareProductClass w : ConstantsWare.product.getWareProductsByIdProduct()) {
                        String ware = w.getIdWare();
                        listWarehouse.add(ware);
                        for (ProductpriceClass p : w.getProductpricesByIdWareProduct()) {
                            String price = String.valueOf(p.getPrice());
                            listProduct.add(price);
                            String amount = String.valueOf(p.getAmount());
                            listAmount.add(amount);
                        }
                    }
                    comboBoxWare.getItems().addAll(listWarehouse);
                    comboBoxPrice.getItems().addAll(listProduct);
                    comboBoxAmount.getItems().addAll(listAmount);
                    tfOff.setText("0");
                    tfAmount.setText("1");
                    tfPriceSale.setText(ConstantsWare.product.getSalePrice());
                    labelUtilities.setText("0");
                    break;
                case "Service":
                    Constant.entity = "ServiceClass";
                    Constant.tfCode = tfProduct.getText();
                    comboBoxPrice.getItems().clear();
                    FoundHQL.workerFound();
                    labelNameProduct.setText(ConstantsWare.service.getName());
                    tfPriceSale.setText(ConstantsWare.service.getProfit());

                    comboBoxPrice.getItems().clear();
                    comboBoxWare.getItems().clear();
                    comboBoxPrice.getItems().add(ConstantsWare.service.getCost());
                    comboBoxWare.getItems().add(ConstantsWare.service.getIdWare());
                    comboBoxPrice.setValue(ConstantsWare.service.getCost());
                    comboBoxWare.setValue(ConstantsWare.service.getIdWare());

                    tfOff.setText("0");
                    tfAmount.setText("1");
                    labelUtilities.setText("0");
                    break;
                default:
                    break;
            }
        }catch (Exception i){
            i.printStackTrace();
            WareController.alertSend("ERROR AL ENCONTRAR PRODUCTO");
        }
    }
    public void changeAmount(ActionEvent event) {
        try{
        if(ConstantsSales.salesOption.equals("Product")){
            int max = Integer.parseInt(comboBoxAmount.getValue());
            int amount = Integer.parseInt(tfAmount.getText());
            if(amount>max){
                WareController.alertSend("Noy hay Stock en la bodega seleccionada");
                buttonCheckIn.setDisable(true);
            }
        }
        }catch (Exception i){
            i.printStackTrace();
        }
    }
    public void changeSale() {
        if(ConstantsSales.salesOption.equals("Product")) {
            int sale = Integer.parseInt(tfPriceSale.getText());
            int saleOld = Integer.parseInt(ConstantsWare.product.getSalePrice());
            int price = Integer.parseInt(comboBoxPrice.getValue());
            int off = ((saleOld - sale)*100)/saleOld;
            tfOff.setText(String.valueOf(off));
            labelUtilities.setText(String.valueOf(ConstantsSales.utilitiesPer(sale, price)));
        }
        if(ConstantsSales.salesOption.equals("Service")){
            int sale = Integer.parseInt(tfPriceSale.getText());
            int saleOld = Integer.parseInt(ConstantsWare.service.getProfit());
            int price = Integer.parseInt(ConstantsWare.service.getCost());
            int off = ((saleOld - sale)*100)/saleOld;
            tfOff.setText(String.valueOf(off));
            labelUtilities.setText(String.valueOf(ConstantsSales.utilitiesPer(sale, price)));
        }
    }
    public void changeOff() {
        try{
        if(ConstantsSales.salesOption.equals("Product")){
            int off = Integer.parseInt(tfOff.getText());
            int saleOld = Integer.parseInt(ConstantsWare.product.getSalePrice());
            int price = Integer.parseInt(comboBoxPrice.getValue());
            int sale = saleOld -off; //(saleOld * off / 100);
            tfPriceSale.setText(String.valueOf(sale));
            labelUtilities.setText(String.valueOf(ConstantsSales.utilitiesPer(sale, price)));
        }
        if(ConstantsSales.salesOption.equals("Service")){
            int off = Integer.parseInt(tfOff.getText());
            int saleOld = Integer.parseInt(ConstantsWare.service.getProfit());
            int price = Integer.parseInt(ConstantsWare.service.getCost());
            int sale = saleOld -off; //(saleOld * off / 100);
            tfPriceSale.setText(String.valueOf(sale));
            labelUtilities.setText(String.valueOf(ConstantsSales.utilitiesPer(sale, price)));
        }
        }catch (Exception i){
            i.printStackTrace();
        }
    }
    public void pxndx(ActionEvent event) {
        ComboBox cb = (ComboBox) event.getSource();
        try {
            if (ConstantsSales.salesOption.equals("Product")) {
                switch (cb.getId()) {
                    case "comboBoxWare":
                        ArrayList<String> listProduct = new ArrayList<>();
                        ArrayList<String> listAmount = new ArrayList<>();
                        String price = "";
                        String amount = "";
                        for (WareProductClass w : ConstantsWare.product.getWareProductsByIdProduct()) {
                            if (cb.getValue().equals(w.getIdWare())) {
                                ConstantsSales.wareProduct = w;
                                for (ProductpriceClass p : w.getProductpricesByIdWareProduct()) {
                                    price = String.valueOf(p.getPrice());
                                    listProduct.add(price);
                                    amount = String.valueOf(p.getAmount());
                                    listAmount.add(amount);
                                }
                            }
                        }
                        comboBoxPrice.getItems().clear();
                        comboBoxAmount.getItems().clear();
                        comboBoxPrice.getItems().addAll(listProduct);
                        comboBoxAmount.getItems().addAll(listAmount);
                        comboBoxPrice.setValue(price);
                        comboBoxAmount.setValue(amount);
                        changeSale();
                        break;
                    case "comboBoxAmount":
                        for (ProductpriceClass pp : ConstantsSales.wareProduct.getProductpricesByIdWareProduct()) {
                            if (cb.getValue().equals(pp.getAmount())) {
                                comboBoxPrice.setValue(String.valueOf(pp.getPrice()));
                            }
                        }
                        break;
                    case "comboBoxPrice":
                        for (ProductpriceClass pp : ConstantsSales.wareProduct.getProductpricesByIdWareProduct()) {
                            if (cb.getValue().equals(pp.getPrice())) {
                                comboBoxAmount.setValue(String.valueOf(pp.getAmount()));
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        }catch (Exception i){
            System.out.println(i + " ComboBox en blanco");
        }
    }
    public void register(ActionEvent event) {
        try{
        if(verify()){

            switch (ConstantsSales.salesOption){
                case "Product":
                        ConstantsWare.product.setPurchasePrice(comboBoxPrice.getValue());
                        ConstantsWare.product.setAmount(Integer.valueOf(tfAmount.getText()));
                        ConstantsWare.product.setSalePrice(tfPriceSale.getText());
                        ConstantsWare.product.setProfit(labelUtilities.getText());
                        ConstantsWare.product.setIdWage(comboBoxWare.getValue());
                        ConstantsWare.product.setOffSale(Integer.parseInt(tfOff.getText()));
                        ConstantsWare.product.setStock(Integer.parseInt(comboBoxAmount.getValue()));
                        ConstantsPurchases.productTableList.add(ConstantsWare.product);

                    break;
                case "Service":
                        ConstantsWare.service.setProfit(tfPriceSale.getText());
                        ConstantsWare.service.setHour(tfAmount.getText());
                        ConstantsPurchases.serviceTableList.add(ConstantsWare.service);
                    break;
                default:
                    break;
            }

            tableLoad();
            labelIVA.setText("0");
            labelIVA2.setText("0");
            labelBold.setText("0");
            labelBold2.setText("0");
        }
        }catch (Exception i){
            WareController.alertSend("ERROR AL REGISTRAR");
            i.printStackTrace();
        }
    }
    public Boolean verify(){
        String falseFor = "";
        try {
            if((tfProductName.getText().isEmpty() && labelNameProduct.getText().isEmpty()) || tfProduct.getText().isEmpty()){
                falseFor = "Name";
            }

            if(comboBoxPrice.getValue() == null || comboBoxPrice.getValue().equals("0")){
                    falseFor = "Price";
            }

            if(tfAmount.getText().isEmpty()){
                falseFor = "Amount";
            }else{
                int a = Integer.parseInt(tfAmount.getText());
                if(a <= 0){
                    falseFor = "Amount";
                }
            }

            if(tfPriceSale.getText().isEmpty()){
                falseFor = "Sale";
            }else{
                int v = Integer.parseInt(tfPriceSale.getText());
                if(v <= 0){
                    falseFor = "Sale";
                }
            }

            if(comboBoxWare.getValue() == null || comboBoxWare.getValue().isBlank()){
                falseFor = "Ware";
            }
            if(comboBoxAmount.getValue() == null || comboBoxAmount.getValue().isBlank()){
                if (ConstantsSales.salesOption.equals("Service")){

                }else {
                    falseFor = "amountCB";
                }

            } else {
                int amount = Integer.parseInt(tfAmount.getText());
                int stock = Integer.parseInt(comboBoxAmount.getValue());
                if(amount > stock){
                    falseFor = "amountCB";
                }
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
                case "Sale":
                    WareController.alertSend("Digitar el precio de venta del producto");
                    return false;
                case "Ware":
                    WareController.alertSend("Digitar la bodega del producto");
                    return false;
                case "amountCB":
                    WareController.alertSend("No se cuenta con suficiente stock");
                    return false;
                default:
                    return true;
            }
        }catch (Exception i){
            i.printStackTrace();
            WareController.alertSend(String.valueOf(i));
            return false;
        }
    }
    public void tableLoad(){
        try {
            switch (ConstantsSales.salesOption) {
                case "Product":
                    tableMain.getItems().clear();
                    ObservableList<ProductClass> datesTTT = FXCollections.observableArrayList(ConstantsPurchases.productTableList);
                    tableMain.setItems(datesTTT);
                    labelSubTota2.setText("0");
                    //Obtiene el total sin Taxes
                    for (ProductClass p : tableMain.getItems()) {
                        int subtotal = ConstantsPurchases.subtotalProduct(String.valueOf(p.getAmount()), p.getSalePrice(), "0");
                        int balance = Integer.valueOf(labelSubTota2.getText());
                        int total = subtotal + balance;
                        labelSubTota2.setText(String.valueOf(total));
                    }
                    break;
                case "Service":
                    tableService.getItems().clear();
                    ObservableList<ServiceClass> datServices = FXCollections.observableArrayList(ConstantsPurchases.serviceTableList);
                    tableService.setItems(datServices);
                    labelSubTota2.setText("0");

                    for (ServiceClass p : tableService.getItems()) {
                        int subtotal = ConstantsPurchases.subtotalProduct(String.valueOf(p.getHour()), p.getProfit(), "0");
                        int balance = Integer.valueOf(labelSubTota2.getText());
                        int total = subtotal + balance;

                        labelSubTota2.setText(String.valueOf(total));
                    }
                    break;
                case "Credit":
                    ConstantsPurchases.listInvoiceSearch();
                    tableInvoice.getItems().clear();
                    try {

                        ArrayList<InvoiceClass> invoiceFiltrate = new ArrayList<>();

                        for (InvoiceClass iv : ConstantsPurchases.invoiceList) {
                            if (iv.getIdCustomer() != null) {
                                invoiceFiltrate.add(iv);
                                ConstantsPurchases.invoiceCredit.add(iv);
                            }
                        }
                        ObservableList<InvoiceClass> invoiceTable =
                                FXCollections.observableArrayList(invoiceFiltrate);
                        ConstantsPurchases.invoiceList = invoiceFiltrate;
                        tableInvoice.setItems(invoiceTable);
                    } catch (Exception i) {
                        System.out.println(i);
                        WareController.alertSend("Sin Facturas de clientes");
                    }
                    break;
                default:
                    break;
            }

            if (!ConstantsSales.salesOption.equals("Credit")) {

                labelTotal2.setText(labelSubTota2.getText());

                costTaxes();

                ConstantsWare.product = null;
                tfProduct.setText("");
                tfProductName.setText("");
                labelNameProduct.setText("");

                comboBoxPrice.getItems().clear();
                comboBoxWare.getItems().clear();
                comboBoxAmount.getItems().clear();

                tfOff.setText("0");
                tfAmount.setText("0");
                tfPriceSale.setText("0");

                pay();
            }
        }catch (Exception i){
            i.printStackTrace();
        }
    }
    public void costTaxes(){
        try{
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
            int subtotal = Integer.valueOf(labelSubTota2.getText());
            int total = iva + subtotal;
            labelTotal2.setText(String.valueOf(total));
        }
        }catch (Exception i){
            i.printStackTrace();
        }
    }
    public void pay(){

        int total = Integer.parseInt(labelTotal2.getText());
        int cash = Integer.parseInt(tfCash.getText());
        int bank;
        try {
            bank = Integer.parseInt(tfBank.getText());
        }catch (NumberFormatException i){
            bank = 0;
        }
        double bold = Double.parseDouble(labelBold.getText());
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

        int boldCost;
        if(bank!= 0 && bold != 0){
            boldCost = (int) ((bold/100) * bank);
            labelBold2.setText(String.valueOf(boldCost));
        }
    }
    public void suppress(ActionEvent event) {
        if(ConstantsSales.salesOption.equals("Product")){
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
        if(ConstantsSales.salesOption.equals("Service")){
            int a = tableService.getSelectionModel().getSelectedIndex();
            String nameProduct = (String) c2.getCellData(a);
            if (nameProduct != null) {
                //elimina de la tabla
                tableService.getItems().remove(a);
                //elimina del array list
                ConstantsPurchases.serviceTableList.remove(a);
                //carga la tabla para obtener el total
                tableLoad();
            }
        }
    }
    public void taxes(ActionEvent event) {
        try{
        Button button = (Button) event.getSource();
        switch (button.getId()){
            case "buttonIVA":
                ConstantsPurchases.iva = !ConstantsPurchases.iva;
                if(ConstantsPurchases.iva){
                    labelIVA.setText(ConstantsSales.iva);
                }else {
                    labelIVA.setText("0");
                    labelIVA2.setText("0");
                }
                break;
            case "buttonBold":
                ConstantsPurchases.boldP = !ConstantsPurchases.boldP;
                if(ConstantsPurchases.boldP){
                    labelBold.setText(ConstantsSales.bold);
                }else {
                    labelBold.setText("0");
                    labelBold2.setText("0");
                }
                break;
            default:
                break;
        }
        tableLoad();
        }catch (Exception i){
            i.printStackTrace();
        }
    }
    public void payment(KeyEvent keyEvent) {
        pay();
        buttonCheckIn.setDisable(ConstantsPurchases.checkin);
    }
    public void quote(ActionEvent event) {
        try{
        Button button = (Button) event.getSource();
        if(button.getText().equals("REGISTRAR PAGO")) {

            MoveinvoiceClass mi = new MoveinvoiceClass();

            mi.setDate(String.valueOf(ConstantsPurchases.dateActuallyGet()));
            mi.setDebt(Integer.parseInt(labelTotal2.getText()));
            mi.setPayCash(Integer.parseInt(tfCash.getText()));
            mi.setPayBank(Integer.parseInt(tfBank.getText()));
            mi.setSubtotal(Integer.parseInt(labelDebt.getText()));
            mi.setInvoiceByIdInvoice(ConstantsAccounting.invoice);
            mi.setIdInvoice(ConstantsAccounting.invoice.getIdInvoice());

            ConstantsPurchases.moveInv = mi;

            Constant.entity = "MoveinvoiceClass";
            ConstantsPurchases.entityForInvoice = "CustomerClass";
            if(SaveHQL.insertWorker("save")){

                WareController.alertSend("REGISTRO GUARDADO");
                tfTaxes.setText("0");
                tfCash.setText("0");
                tfBank.setText("0");
                labelSubTota2.setText("0");
                labelTotal2.setText("0");
                labelPay.setText("0");
                labelDebt.setText("0");
                tableLoad();

            }
        }else {
            cotizar(button.getText());
        }
        }catch (Exception i){
            i.printStackTrace();
        }
    }
    public void checkinInvoice(ActionEvent event) {
        /*Crea un invoice solo con los datos necesarios para
         *obtener la id, es obligatorio ingresar
         *bak, cash, date y total
         * con el idBill se encuentra la factura
         * */
        if(!ConstantsPurchases.productTableList.isEmpty() ||
                !ConstantsPurchases.serviceTableList.isEmpty()) {

            Button button = (Button) event.getSource();

            int bold = Integer.parseInt(labelBold2.getText());
            int bank = Integer.parseInt(tfBank.getText());
            int bankWithOutBold = bank - bold;

            InvoiceClass invoice = new InvoiceClass();

            invoice.setBold(labelBold2.getText());
            invoice.setBank(String.valueOf(bankWithOutBold));
            invoice.setCash(tfCash.getText());
            invoice.setIndebtedness(labelDebt.getText());
            invoice.setTotal(labelTotal2.getText());
            invoice.setDate(ConstantsPurchases.dateActuallyGet());
            invoice.setIdBill(666);

            ConstantsAccounting.invoice = invoice;
            Constant.entity = "InvoiceClass";
            Constant.tfCode = "";
            ConstantsPurchases.invoiceType = "saleFromProduct";

            if (SaveHQL.workerInsertUpdate()) {
                //si se guarda la factura, ahora la recupera.
                Constant.tfCode = "666";
                FoundHQL.wareFound();

                generadorWareAndPrice(button.getId());

                //Busca la compañia
                ConstantsPurchases.invoiceType = "purchaseFromSupplier";
                ConstantsPurchases.entityForInvoice = "CustomerClass";
                Constant.entity = "CompanyClass";
                FoundHQL.workerFound();

                //Actualiza la invoice
                ConstantsAccounting.invoice.setIdCompany(Constant.company.getIdCompanyNIT());
                ConstantsAccounting.invoice.setIdCustomer(Constant.customer.getIdCustomer());
                //calculo de impuestos pagados
                ConstantsAccounting.invoice.setTaxes(labelIVA2.getText());
                //calculo de los precios de compra de todos los productos por sus cantidades
                if (ConstantsSales.salesOption.equals("Product")) {
                    ConstantsAccounting.invoice.setTotalBuy(String.valueOf(ConstantsPurchases.totalBuyProduct(ConstantsPurchases.productTableList)));
                }
                if (ConstantsSales.salesOption.equals("Service")) {
                    ConstantsAccounting.invoice.setTotalBuy(String.valueOf(ConstantsPurchases.totalCostService(ConstantsPurchases.serviceTableList)));
                }
                //en caso de deudas hace
                ConstantsAccounting.invoice.setUtilities(
                        ConstantsSales.utilities(
                                ConstantsPurchases.totalSale,
                                ConstantsAccounting.invoice.getTotalBuy(),
                                ConstantsAccounting.invoice.getTaxes(),
                                ConstantsAccounting.invoice.getIndebtedness(),
                                ConstantsAccounting.invoice.getTotal(),
                                ConstantsAccounting.invoice.getBold()
                        )
                );
                //lo que falta por pagar en utilidades
                ConstantsAccounting.invoice.setRUtilities(ConstantsSales.rUtilities);

                //idBIll se usa para guardar la deuda actual
                ConstantsAccounting.invoice.setIdBill(Integer.parseInt(ConstantsAccounting.invoice.getIndebtedness()));

                //Actualiza la cuenta de la compañia y el cliente
                ConstantsSales.saleCompany(
                        ConstantsAccounting.invoice.getBank(),
                        ConstantsAccounting.invoice.getCash(),
                        ConstantsAccounting.invoice.getIndebtedness(),
                        ConstantsAccounting.invoice.getRUtilities(),
                        ConstantsSales.resta(
                                ConstantsAccounting.invoice.getUtilities(), ConstantsAccounting.invoice.getRUtilities()
                        )
                );

                ConstantsSales.saleCustomer(ConstantsAccounting.invoice.getBank(),
                        ConstantsAccounting.invoice.getCash(),
                        ConstantsAccounting.invoice.getIndebtedness());

                //Aqui se genera el sql que manda a guardar y actualizar todos los datos
                //--Falta codigo para salvar los datos--
                if (SaveHQL.saveInvoice()) {
                    WareController.alertSend("Datos guardados con exito");
                    ConstantsPurchases.productTableList.clear();
                    ConstantsPurchases.serviceTableList.clear();
                } else {
                    WareController.alertSend("Error al guardar los datos");
                }
            }
        }else {
            WareController.alertSend("SIN DATOS REGISTRADOS");
        }
    }
    public void invoiceView() {
        try{
        //carga los productos de la factura
        InvoiceClass invoiceSelected = tableInvoice.getSelectionModel().getSelectedItem();
        ConstantsAccounting.invoice = invoiceSelected;
        ConstantsPurchases.listWareInvoiceSearch(invoiceSelected, "customer");

        tableWareInv.getItems().clear();
        ObservableList<WareinvoiceClass> wiTable = FXCollections.observableArrayList(ConstantsPurchases.wareInvoiceList);
        tableWareInv.setItems(wiTable);

        //Carga la tabla de deudas si tiene
            //debe de ser providerClass para poder encontrar los datos
        ConstantsPurchases.entityForInvoice = "ProvidersClass";
        ConstantsPurchases.listMoveSearch(invoiceSelected);
        if(!ConstantsPurchases.moveInvoiceList.isEmpty()){
            tableMoveInv.getItems().clear();
            ObservableList<MoveinvoiceClass> miTable = FXCollections.observableArrayList(ConstantsPurchases.moveInvoiceList);
            tableMoveInv.setItems(miTable);
        }else {
            tableMoveInv.getItems().clear();
            labelSubTota2.setText("0");
            labelTotal2.setText("0");
            tfTaxes.setText("0");
        }

        //Carga la deuda pendiente de pagar
        try {
            if(0 != invoiceSelected.getIdBill()){
                labelSubTota2.setText(String.valueOf(invoiceSelected.getIdBill()));
                labelTotal2.setText(labelSubTota2.getText());
                tfTaxes.setText("0");
            }
        }catch (Exception i){
            System.out.println("DEUDA ES NULLA");
        }
        }catch (Exception i){
            i.printStackTrace();
        }
    }
    public void sobreCost(KeyEvent keyEvent) {
        int subtotal = Integer.parseInt(labelSubTota2.getText());
        int sobreCosto = Integer.parseInt(tfTaxes.getText());
        int total = subtotal +sobreCosto;
        labelTotal2.setText(String.valueOf(total));
    }
    public void facturar(ActionEvent event) {
        String entity = Constant.entity;

        Constant.entity = "InvoiceClass";
        Constant.tfCode = tfFactura.getText();
        FoundHQL.workerFound();

        ReportGenerator.generateReport();
        Constant.entity = entity;

    }
    public void cotizar(String button){
        try{
        if(!ConstantsPurchases.productTableList.isEmpty() ||
                !ConstantsPurchases.serviceTableList.isEmpty()) {
        //buscar y guardar la compañia
        CompanyClass company = ReportGenerator.companyFound();
        //guardar el cliente
        CustomerClass customer = ReportGenerator.customerInvoice(tfCliente.getText());
        //Guardar los wareInvoice
        generadorWareAndPrice(button);
        //guardar el total (iva)
        int iva = Integer.parseInt(labelIVA2.getText());
        int subtotal = Integer.parseInt(labelSubTota2.getText());
        //generar la cotización con jasper Report
        ReportGenerator.generadorCotizar(company,customer,ConstantsPurchases.wareInvoiceList,subtotal, iva);
        }else {
            WareController.alertSend("SIN DATOS REGISTRADOS");
        }
        }catch (Exception i){
            WareController.alertSend("ERROR AL GENERAR LA COTIZACIÓN");
            i.printStackTrace();
        }
    }
    public void generadorWareAndPrice(String button){
        try{
        switch (ConstantsSales.salesOption){
            case "Product":
                //cada producto debe de crear un wareinvoice
                if(!ConstantsPurchases.wareInvoiceList.isEmpty()){
                    ConstantsPurchases.wareInvoiceList.clear();
                }
                if(!ConstantsPurchases.pPriceUpdateList.isEmpty()){
                    ConstantsPurchases.pPriceUpdateList.clear();
                }

                for(ProductClass p: ConstantsPurchases.productTableList){
                    WareinvoiceClass wi = new WareinvoiceClass();
                    if(button.equals("buttonCheckIn")){
                        wi.setIdInvoice(ConstantsAccounting.invoice.getIdInvoice());
                    }
                    wi.setIdProduct(p.getIdProduct());
                    wi.setProductName(p.getName());
                    wi.setPriceSale(p.getSalePrice());
                    wi.setPriceBuy(Integer.parseInt(p.getPurchasePrice()));
                    wi.setAmount(p.getAmount());
                    wi.setOffSale(p.getOffSale());
                    wi.setIndexWare(ConstantsPurchases.productTableList.indexOf(p) + 1);
                    //agrega el wareproduct a la lista
                    ConstantsPurchases.wareInvoiceList.add(wi);

                    //actualiza los productos en product price
                    try{
                        for(WareProductClass wp: p.getWareProductsByIdProduct()){
                            //Si la bodega corresponde a una registrada
                            if(Objects.equals(wp.getIdWare(), p.getIdWage())){
                                for(ProductpriceClass pp: wp.getProductpricesByIdWareProduct()){
                                    //si el precio es el registrado
                                    if(pp.getPrice() == Integer.parseInt(p.getPurchasePrice())){
                                        //agrega el objeto productprice a la lista para actualizar
                                        //actualiza la cantidad en inventario
                                        pp.setAmount( - p.getAmount() + p.getStock());
                                        //guarda la cantidad actualizada
                                        ConstantsPurchases.pPriceUpdateList.add(pp);
                                    }
                                }
                            }
                        }
                    }catch (Exception i){
                        //en caso de error es porque el producto es nuevo o tiene nuevo precio
                        WareController.alertSend("Error en la lista de venta");
                        ConstantsPurchases.productNewList.add(p);
                    }

                }
                break;
            case "Service":
                //cada servicio debe de crear un wareinvoice
                if(!ConstantsPurchases.wareInvoiceList.isEmpty()){
                    ConstantsPurchases.wareInvoiceList.clear();
                }
                for(ServiceClass s: ConstantsPurchases.serviceTableList){
                    WareinvoiceClass wiS = new WareinvoiceClass();

                    if(button.equals("buttonCheckIn")){
                        wiS.setIdInvoice(ConstantsAccounting.invoice.getIdInvoice());
                    }

                    wiS.setIdProduct(s.getIdService());
                    wiS.setProductName(s.getName());
                    wiS.setPriceSale(s.getProfit());
                    wiS.setPriceBuy(Integer.parseInt(s.getCost()));
                    wiS.setAmount(Integer.parseInt(s.getHour()));
                    wiS.setAmount(Integer.parseInt(s.getHour()));
                    //idProductPrice es inecesario
                    wiS.setIndexWare(ConstantsPurchases.serviceTableList.indexOf(s) + 1);
                    //agrega el wareproduct a la lista
                    ConstantsPurchases.wareInvoiceList.add(wiS);
                }
                break;
            default:
                System.out.println("Error en  SalesController.entity");
                break;
        }
        }catch (Exception i){
            WareController.alertSend("ERROR AL GENERAR LOS PRODUCTOS EN EL MOVIMIENTO");
            i.printStackTrace();
        }
    }
    public void offPercentage() {
        try {
            int offPer = Integer.parseInt(tfOffPer.getText());
            int saleOld = Integer.parseInt(ConstantsWare.product.getSalePrice());
            int off = (saleOld * offPer) / 100;
            tfOff.setText(String.valueOf(off));
            changeOff();
        }catch (Exception i) {
            System.out.println(i + " Porcentaje de descuento nulo");
        }
    }
    public void loadTableProduct(){
        columCode.setCellValueFactory(new PropertyValueFactory<>("idProduct"));
        columProduct.setCellValueFactory(new PropertyValueFactory<>("name"));
        columPrice.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));
        tableProductSearch.setItems(ConstantsPurchases.seekProducts());
        ConstantsSales.seekProductList = tableProductSearch.getItems();
    }
    @FXML
    public void filtrateTableProducts(){
        ObservableList<ProductClass> listProduct = ConstantsSales.seekProductList;
        FilteredList<ProductClass> filteredListProduct = new FilteredList<ProductClass>(listProduct, s -> s.getIdProduct().contains(tfCodeSeek.getText()) && s.getName().contains(tfProductSeek.getText()) && s.getPurchasePrice().contains(tfPriceSeek.getText()) );
        tableProductSearch.setItems(filteredListProduct);
    }
    @FXML
    public void tableSelectedProduct(){
        try {
            ProductClass tShow = tableProductSearch.getSelectionModel().getSelectedItem();
            String code = tShow.getIdProduct();
            tfCodeFound.setText(code);
        }catch (Exception i){
            System.out.println("Date not selected");
        }
    }
}
