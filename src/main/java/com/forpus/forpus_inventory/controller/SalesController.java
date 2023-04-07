package com.forpus.forpus_inventory.controller;

import com.forpus.forpus_inventory.HelloApplication;
import com.forpus.forpus_inventory.domain.services.*;
import com.forpus.forpus_inventory.persistence.crud.FoundHQL;
import com.forpus.forpus_inventory.persistence.crud.SaveHQL;
import com.forpus.forpus_inventory.persistence.crud.SearchHQL;
import com.forpus.forpus_inventory.persistence.entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

import static com.forpus.forpus_inventory.domain.services.ConstantsPurchases.createNumericTextFormatter;

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
    public TableView<ProductClass> tableMain;
    public Label labelNameClient;
    public TextField tfClient;
    public ComboBox<String> comboBoxAmount;
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
    public Button buttonProduct0;
    public TableColumn<Object, Object> c1;
    public TableColumn<Object, Object> c2;
    public TableColumn c3;
    public TableColumn c4;
    public TableColumn c5;
    public TableColumn c6;
    public TableColumn c7;
    public TableColumn c8;
    public TableColumn c9;
    public TableColumn c10;
    public ComboBox<String> comboBoxPrice;
    public Label labelNameCliente;
    public Label labelCliente;
    public TextField tfCliente;
    public Button buttonCliente;
    public Label labelSubTota2;
    public Label labelIVA2;
    public Label labelBold2;
    public TextField tfTaxes;
    public Label labelDebt;
    public TextField tfPriceSale;
    public TextField tfAmount;
    public ComboBox<String> comboBoxWare;
    public Button buttonSuppress;
    public TextField tfProductName;
    public TableView<ServiceClass> tableService;
    public TableColumn c21;
    public TableColumn c22;
    public TableColumn c23;
    public TableColumn c24;
    public TableColumn c25;
    public TableView<InvoiceClass> tableInvoice;
    public TableColumn i1;
    public TableColumn i2;
    public TableColumn i3;
    public TableColumn i4;
    public TableColumn i5;
    public TableView tableWareInv;
    public TableColumn wi1;
    public TableColumn wi2;
    public TableColumn wi3;
    public TableColumn wi4;
    public TableView tableMoveInv;
    public TableColumn m1;
    public TableColumn m2;
    public TableColumn m3;
    public TableColumn m4;
    public TableColumn m5;
    public TableColumn m6;
    public TableColumn m7;
    public Label labelUtilities;
    public void initialize() {

        tfAmount.setTextFormatter(createNumericTextFormatter());
        tfPriceSale.setTextFormatter(createNumericTextFormatter());
        tfOff.setTextFormatter(createNumericTextFormatter());
        tfBank.setTextFormatter(createNumericTextFormatter());
        tfCash.setTextFormatter(createNumericTextFormatter());
        tfTaxes.setTextFormatter(createNumericTextFormatter());

        Constant.entity = "CustomerClass";
        ConstantsSales.salesOption = "Product";

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

    }
    //Carga las taxaqs de impuestos
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
    public void clean(){
        final boolean a = false;
        labelProduct.setVisible(a);
        labelNameProduct.setVisible(a);
        labelOff.setVisible(a);
        labelAmount.setVisible(a);
        labelPriceSale.setVisible(a);

        tfProduct.setVisible(a);
        tfOff.setVisible(a);
        tfAmount.setVisible(a);
        tfPriceSale.setVisible(a);
        tfProductName.setVisible(a);
        tfTaxes.setVisible(a);

        buttonProduct.setVisible(a);
        buttonRegister.setVisible(a);
        buttonSuppress.setVisible(a);

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
        panelPayment.setVisible(a);
        panelTotal.setVisible(a);

        labelTotal2.setText("0");
        labelIVA2.setText("0");
        labelBold2.setText("0");
        tfCash.setText("0");
        tfBank.setText("0");
        labelPay.setText("0");
        labelDebt.setText("0");

        ConstantsPurchases.productTableList.clear();
        ConstantsPurchases.serviceTableList.clear();
        ConstantsPurchases.wareInvoiceList.clear();
        ConstantsPurchases.invoiceList.clear();

        tableService.getItems().clear();
        tableMain.getItems().clear();


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
        clean();
        final boolean a = true;
        switch (button.getId()){
            case "buttonProduct0":
                ConstantsSales.salesOption = "Product";
                Constant.entity = "CustomerClass";
                ConstantsPurchases.entity = "SaleProduct";

                panelPayment.setVisible(a);
                panelCheckIn.setVisible(a);
                panelTotal.setVisible(a);
                labelClient.setVisible(a);
                labelNameClient.setVisible(a);
                labelProduct.setVisible(a);
                labelProduct.setText("Producto");
                labelNameProduct.setVisible(a);
                labelAmount.setVisible(a);
                labelPriceBuy.setVisible(a);
                labelPriceSale.setVisible(a);
                labelOff2.setVisible(a);
                labelOff.setVisible(a);
                labelNote.setVisible(a);

                tfClient.setVisible(a);
                tfProduct.setVisible(a);
                tfOff.setVisible(a);
                tfNote.setVisible(a);

                comboBoxAmount.setVisible(a);
                comboBoxWare.setVisible(a);
                comboBoxPrice.setVisible(a);

                tableMain.setVisible(a);

                buttonProduct.setVisible(a);
                buttonClient.setVisible(a);
                buttonRegister.setVisible(a);

                break;
            case "buttonService":
                ConstantsSales.salesOption = "Service";
                Constant.entity = "CustomerClass";
                ConstantsPurchases.entity = "Service";

                break;
            case "buttonCredit":
                clean();
                break;
            default:
                break;
        }
    }
    public void clientFound(ActionEvent event) {
        Constant.entity = "CustomerClass";
        Constant.tfCode = tfClient.getText();
        FoundHQL.workerFound();
        labelNameClient.setText(Constant.customer.getName());
    }
    public void productFound(ActionEvent event) {
        switch (ConstantsSales.salesOption){
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
                for(WareProductClass w: ConstantsWare.product.getWareProductsByIdProduct()){
                    String ware = w.getIdWare();
                    listWarehouse.add(ware);
                    for(ProductpriceClass p: w.getProductpricesByIdWareProduct()){
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
                tfAmount.setText("0");
                tfPriceSale.setText(ConstantsWare.product.getSalePrice());
                break;
            case "Service":
                Constant.entity = "ServiceClass";
                Constant.tfCode = tfProduct.getText();
                comboBoxPrice.getItems().clear();
                FoundHQL.workerFound();
                labelNameProduct.setText(ConstantsWare.service.getName());
                tfPriceSale.setText(ConstantsWare.service.getProfit());
                comboBoxPrice.setValue(ConstantsWare.service.getCost());
                comboBoxWare.setValue(ConstantsWare.service.getIdWare());
                tfOff.setText("0");
                tfAmount.setText("1");
                break;
            default:
                break;
        }
    }
    public void changeAmount(ActionEvent event) {
        if(ConstantsSales.salesOption.equals("Product")){
            int max = Integer.parseInt(comboBoxAmount.getValue());
            int amount = Integer.parseInt(tfAmount.getText());
            if(amount>max){
                WareController.alertSend("Noy hay Stock en la bodega seleccionada");
                buttonCheckIn.setDisable(true);
            }
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
        if(ConstantsSales.salesOption.equals("Product")){
            int off = Integer.parseInt(tfOff.getText());
            int saleOld = Integer.parseInt(ConstantsWare.product.getSalePrice());
            int price = Integer.parseInt(comboBoxPrice.getValue());
            int sale = saleOld - (saleOld * off / 100);
            tfPriceSale.setText(String.valueOf(sale));
            labelUtilities.setText(String.valueOf(ConstantsSales.utilitiesPer(sale, price)));
        }
        if(ConstantsSales.salesOption.equals("Service")){
            int off = Integer.parseInt(tfOff.getText());
            int saleOld = Integer.parseInt(ConstantsWare.service.getProfit());
            int price = Integer.parseInt(ConstantsWare.service.getCost());
            int sale = saleOld - (saleOld * off / 100);
            tfPriceSale.setText(String.valueOf(sale));
            labelUtilities.setText(String.valueOf(ConstantsSales.utilitiesPer(sale, price)));
        }
    }
    public void pxndx(ActionEvent event) {
        ComboBox cb = (ComboBox) event.getSource();
        switch (cb.getId()){
            case "comboBoxWare":
                ArrayList<String> listProduct = new ArrayList<>();
                ArrayList<String> listAmount = new ArrayList<>();
                for(WareProductClass w: ConstantsWare.product.getWareProductsByIdProduct()){
                    if(cb.getValue().equals(w.getIdWare())){
                        ConstantsSales.wareProduct = w;
                        for(ProductpriceClass p: w.getProductpricesByIdWareProduct()){
                            String price = String.valueOf(p.getPrice());
                            listProduct.add(price);
                            comboBoxPrice.setValue(price);
                            String amount = String.valueOf(p.getAmount());
                            listAmount.add(amount);
                            comboBoxAmount.setValue(amount);
                        }
                    }
                }
                comboBoxPrice.getItems().addAll(listProduct);
                comboBoxAmount.getItems().addAll(listAmount);
                break;
            case "comboBoxAmount":
                for(ProductpriceClass pp: ConstantsSales.wareProduct.getProductpricesByIdWareProduct()){
                    if(cb.getValue().equals(pp.getAmount())){
                        comboBoxPrice.setValue(String.valueOf(pp.getPrice()));
                    }
                }
                break;
            case "comboBoxPrice":
                for(ProductpriceClass pp: ConstantsSales.wareProduct.getProductpricesByIdWareProduct()){
                    if(cb.getValue().equals(pp.getPrice())){
                        comboBoxAmount.setValue(String.valueOf(pp.getAmount()));
                    }
                }
                break;
            default:
                break;
        }
    }
    public void register(ActionEvent event) {
        if(verify()){

            switch (ConstantsSales.salesOption){
                case "Product":
                    int utilities = Integer.parseInt(labelUtilities.getText());
                    int price = Integer.parseInt(comboBoxPrice.getValue());
                    int profit = utilities * 100 / price;

                        ConstantsWare.product.setPurchasePrice(comboBoxPrice.getValue());
                        ConstantsWare.product.setAmount(Integer.valueOf(tfAmount.getText()));
                        ConstantsWare.product.setSalePrice(tfPriceSale.getText());
                        ConstantsWare.product.setProfit(String.valueOf(profit));
                        ConstantsWare.product.setIdWage(comboBoxWare.getValue());

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
                int a = Integer.valueOf(tfAmount.getText());
                if(a <= 0){
                    falseFor = "Amount";
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

        switch (ConstantsSales.salesOption){
            case "Product":
                tableMain.getItems().clear();
                ObservableList<ProductClass> datesTTT = FXCollections.observableArrayList(ConstantsPurchases.productTableList);
                tableMain.setItems(datesTTT);
                labelSubTota2.setText("0");
                //Obtiene el total sin Taxes
                for(ProductClass p: tableMain.getItems()){
                    int subtotal = ConstantsPurchases.subtotalProduct(String.valueOf(p.getAmount()), p.getSalePrice());
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

                for(ServiceClass p: tableService.getItems()){
                    int subtotal = ConstantsPurchases.subtotalProduct(String.valueOf(p.getHour()), p.getProfit());
                    int balance = Integer.valueOf(labelSubTota2.getText());
                    int total = subtotal + balance;

                    labelSubTota2.setText(String.valueOf(total));
                }
                break;
            case "Credit":
                ConstantsPurchases.listInvoiceSearch();
                tableInvoice.getItems().clear();
                ConstantsPurchases.invoiceList.removeIf(s -> s.getIdCustomer().isBlank());
                ObservableList<InvoiceClass> invoiceTable =
                        FXCollections.observableArrayList(ConstantsPurchases.invoiceList);
                tableInvoice.setItems(invoiceTable);
                break;
            default:
                break;
        }

        if(!ConstantsPurchases.entity.equals("Credit")){

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
            int subtotal = Integer.valueOf(labelSubTota2.getText());
            int total = iva + subtotal;
            labelTotal2.setText(String.valueOf(total));
        }

        if(ConstantsPurchases.boldP){
            int bold = Integer.valueOf(labelBold2.getText());
            int subtotal = Integer.valueOf(labelSubTota2.getText());
            int total = bold + subtotal;
            labelTotal2.setText(String.valueOf(total));
        }
    }
    public void pay(){

        int total = Integer.valueOf(labelTotal2.getText());
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
    public void payment(KeyEvent keyEvent) {
        pay();
        buttonCheckIn.setDisable(ConstantsPurchases.checkin);
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
        invoice.setTotal(labelTotal2.getText());
        invoice.setDate(ConstantsPurchases.dateActually());
        invoice.setIdBill(666);
        ConstantsAccounting.invoice = invoice;
        Constant.entity = "InvoiceClass";
        ConstantsPurchases.invoiceType = "saleFromProduct";

        if(SaveHQL.workerInsertUpdate()){
            //si se guarda la factura, ahora la recupera.
            Constant.tfCode = "666";
            FoundHQL.wareFound();


            switch (ConstantsSales.salesOption){
                case "Product":
                    //cada producto debe de crear un wareinvoice
                    for(ProductClass p: ConstantsPurchases.productTableList){
                        WareinvoiceClass wi = new WareinvoiceClass();

                        wi.setIdInvoice(ConstantsAccounting.invoice.getIdInvoice());
                        wi.setIdProduct(p.getIdProduct());
                        wi.setProductName(p.getName());
                        wi.setPriceSale(p.getSalePrice());
                        wi.setPriceBuy(Integer.parseInt(p.getPurchasePrice()));
                        wi.setAmount(p.getAmount());
                        //idProductPrice es inecesario
                        wi.setIdProductPrice(6);
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
                                            pp.setAmount( - p.getAmount() + pp.getAmount());
                                            //guarda la cantidad actualizada
                                            ConstantsPurchases.pPriceUpdateList.add(pp);
                                        }
                                    }
                                }
                            }
                        }catch (Exception i){
                            //en caso de error es porque el producto es nuevo o tiene nuevo precio
                            System.out.println("Error en la lista de venta");
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
                        wiS.setPriceSale(s.getProfit());
                        wiS.setPriceBuy(Integer.parseInt(s.getCost()));
                        wiS.setAmount(Integer.parseInt(s.getHour()));
                        //idProductPrice es inecesario
                        wiS.setIdProductPrice(6);
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
            ConstantsPurchases.entityForInvoice = "CustomerClass";
            Constant.entity = "CompanyClass";
            Constant.tfCode = "1";
            FoundHQL.workerFound();

            //Actualiza la invoice
            ConstantsAccounting.invoice.setIdCompany(Constant.company.getIdCompanyNIT());
            ConstantsAccounting.invoice.setIdCustomer(Constant.customer.getIdCustomer());
            //calculo de impuestos pagados
            ConstantsAccounting.invoice.setTaxes(ConstantsSales.taxes(labelIVA2.getText(), labelBold2.getText()));
            //calculo de los precios de compra de todos los productos por sus cantidades
            if(ConstantsSales.salesOption.equals("Product")) {
                ConstantsAccounting.invoice.setTotalBuy(String.valueOf(ConstantsPurchases.totalBuyProduct(ConstantsPurchases.productTableList)));
            }
            if(ConstantsSales.salesOption.equals("Service")) {
                ConstantsAccounting.invoice.setTotalBuy(String.valueOf(ConstantsPurchases.totalCostService(ConstantsPurchases.serviceTableList)));
            }

            //en caso de deudas hace
            ConstantsAccounting.invoice.setUtilities( ConstantsSales.utilities(ConstantsPurchases.totalSale,
             ConstantsAccounting.invoice.getTotalBuy(), ConstantsAccounting.invoice.getTaxes(),
             ConstantsAccounting.invoice.getIndebtedness(), ConstantsAccounting.invoice.getTotal() ));


            ConstantsAccounting.invoice.setRUtilities(ConstantsSales.rUtilities);

            //idBIll se usa para guardar la deuda actual
            ConstantsAccounting.invoice.setIdBill(Integer.parseInt(ConstantsAccounting.invoice.getIndebtedness()));

            //Actualiza la cuenta de la compañia y el proveedor
            ConstantsSales.saleCompany(ConstantsAccounting.invoice.getBank(),
                    ConstantsAccounting.invoice.getCash(), ConstantsAccounting.invoice.getIndebtedness(),
                    ConstantsAccounting.invoice.getRUtilities(), ConstantsAccounting.invoice.getUtilities());
            ConstantsSales.saleCustomer(ConstantsAccounting.invoice.getBank(), ConstantsAccounting.invoice.getCash(),
                    ConstantsAccounting.invoice.getIndebtedness());

            //Aqui se genera el sql que manda a guardar y actualizar todos los datos
            //--Falta codigo para salvar los datos--
            if(SaveHQL.saveInvoice()){
                WareController.alertSend("Datos guardados con exito");
            }else {
                WareController.alertSend("Error al guardar los datos");
            }

        }

    }
    public void invoiceView() {
        //carga los productos de la factura
        InvoiceClass invoiceSelected = tableInvoice.getSelectionModel().getSelectedItem();
        ConstantsAccounting.invoice = invoiceSelected;
        ConstantsPurchases.listWareInvoiceSearch(invoiceSelected);

        tableWareInv.getItems().clear();
        ObservableList<WareinvoiceClass> wiTable = FXCollections.observableArrayList(ConstantsPurchases.wareInvoiceList);
        tableWareInv.setItems(wiTable);

        //Carga la tabla de deudas si tiene
        ConstantsPurchases.listMoveSearch(invoiceSelected);
        if(!ConstantsPurchases.moveInvoiceList.isEmpty()){
            tableMoveInv.getItems().clear();
            ObservableList<MoveinvoiceClass> miTable = FXCollections.observableArrayList(ConstantsPurchases.moveInvoiceList);
            tableMoveInv.setItems(miTable);
        }else {
            tableMoveInv.getItems().clear();
        }

        //Carga la deuda pendiente de pagar
        if(0 != invoiceSelected.getIdBill()){
            labelSubTota2.setText(String.valueOf(invoiceSelected.getIdBill()));
            labelTotal2.setText(labelTotal2.getText());
            tfTaxes.setText("0");
        }
    }
    public void sobreCost(KeyEvent keyEvent) {
        int subtotal = Integer.parseInt(labelSubTota2.getText());
        int sobreCosto = Integer.parseInt(tfTaxes.getText());
        int total = subtotal +sobreCosto;
        labelTotal2.setText(String.valueOf(total));
    }
}
