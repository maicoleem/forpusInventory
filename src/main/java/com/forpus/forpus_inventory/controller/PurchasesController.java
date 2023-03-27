package com.forpus.forpus_inventory.controller;

import com.forpus.forpus_inventory.domain.services.*;
import com.forpus.forpus_inventory.persistence.crud.DeleteHQL;
import com.forpus.forpus_inventory.persistence.crud.FoundHQL;
import com.forpus.forpus_inventory.persistence.crud.SearchHQL;
import com.forpus.forpus_inventory.persistence.entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

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

                panelCheckIn.setVisible(a);
                panelPayment.setVisible(a);
                panelTotal.setVisible(a);

                checkProduct.setVisible(a);
                checkSale.setVisible(a);
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
        tfProductName.setVisible(a);

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

        panelCheckIn.setVisible(a);
        panelPayment.setVisible(a);
        panelTotal.setVisible(a);
        checkSale.setVisible(a);
        checkProduct.setVisible(a);

        ConstantsPurchases.productTableList.clear();
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

        if(checkProduct.isSelected()){
            if(FoundHQL.workerFound()){
                tfProductName.setDisable(true);
               String message = "El codigo ya existe";
               WareController.alertSend(message);

            }else{
                tfProductName.setDisable(false);
            }

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
            comboBoxPrice.getItems().addAll(listProduct);

            tfOff.setText("0");
            tfAmount.setText("1");
            tfProfit.setText("30");
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
        //comboBoxPrice.setValue("");

        ArrayList<String> listProduct = new ArrayList<>();
        Constant.entity = "WarehouseClass";
        SearchHQL.searchHQL();

        for(WarehouseClass w: ConstantsWare.wareList){
            listProduct.add(w.getIdWarehouse());
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
    public void register(ActionEvent event) {
        if(verify()){
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
                System.out.println(ConstantsWare.product);
                ConstantsPurchases.productTableList.add(ConstantsWare.product);
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

        tfAmount.setTextFormatter(createNumericTextFormatter());
        tfProfit.setTextFormatter(createNumericTextFormatter());
        tfPrice.setTextFormatter(createNumericTextFormatter());
        tfPriceSale.setTextFormatter(createNumericTextFormatter());
        tfOff.setTextFormatter(createNumericTextFormatter());
        tfBank.setTextFormatter(createNumericTextFormatter());
        tfCash.setTextFormatter(createNumericTextFormatter());

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
    }
    // Crear un TextFormatter que solo permita números
    public static TextFormatter<Integer> createNumericTextFormatter() {
        return new TextFormatter<>(new IntegerStringConverter(), 0,
                change -> {
                    String newText = change.getControlNewText();
                    if (newText.matches("\\d*")) {
                        return change;
                    }
                    return null;
                });
    }
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
        tableMain.getItems().clear();

        ObservableList<ProductClass> datesTTT = FXCollections.observableArrayList(ConstantsPurchases.productTableList);
        tableMain.setItems(datesTTT);
        labelTotal2.setText("0");
        for(ProductClass p: tableMain.getItems()){
            int price = Integer.parseInt(p.getPurchasePrice());
            int amount = p.getAmount();
            int subtotal = price * amount;

            int balance = Integer.valueOf(labelTotal2.getText());

            int total = subtotal + balance;
            labelTotal2.setText(String.valueOf(total));
        }
        labelTotal3.setText(labelTotal2.getText());
        costTaxes();

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
        pay();

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
}
