package com.forpus.forpus_inventory.controller;

import com.forpus.forpus_inventory.domain.services.*;
import com.forpus.forpus_inventory.persistence.crud.FoundHQL;
import com.forpus.forpus_inventory.persistence.crud.SaveHQL;
import com.forpus.forpus_inventory.persistence.crud.SearchHQL;
import com.forpus.forpus_inventory.persistence.entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
    public ComboBox<String> comboBoxProduct;
    public TableView<TableShow> tableMain;
    public TableColumn<Object, Object> c1;
    public TableColumn<Object, Object> c2;
    public TableColumn<Object, Object> c3;
    public TableColumn<Object, Object> c4;

    public TableColumn<Object, Object> c5;
    public TextField tfCode;
    public Label labelCode;
    public Button bSearchCode;
    public Button bCancelCode;
    public TableView<TableShow> tableTwo;
    public TableColumn<Object, Object> c21;
    public TableColumn<Object, Object> c22;
    public TableColumn<Object, Object> c23;
    public TableColumn<Object, Object> c24;
    public TableColumn<Object, Object> c25;
    public Label labelNameShow;
    public Label labelPhone;
    public Label labelAddress;
    public Label labelCash;
    public TextField tfCash;
    public CheckBox checkBoxDebt;
    public CheckBox checkBoxPay;

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

        switch (ConstantsAccounting.entity){
            case "TaxesClass":
                for(TaxesClass tx: ConstantsAccounting.taxesList){
                    if(Objects.equals(tx.getIdTaxes(), "IVA")){
                        tx.setTaxes(tfIva.getText());
                    }
                    if(Objects.equals(tx.getIdTaxes(), "BOLD")){
                        tx.setTaxes(tfBold.getText());
                    }
                }
            case "PartnersClass":
                Constant.tfCode = String.valueOf(ConstantsAccounting.invoice.getIdInvoice());
                Constant.entity = "InvoiceClass";

                break;
            default:
                break;
        }
    }
    private void differentiateBetweenEntities(boolean workerFound) {
        switch (ConstantsAccounting.entity){
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
            case "PartnersClass":
                if(!"Error".equals(labelNameShow.getText())){
                    //Crea la factura
                    InvoiceClass invoice = new InvoiceClass();
                    //invoice.setIdBill();
                    //invoice.setIdCustomer();
                    invoice.setIdPartners(Constant.partners.getIdentificationCard());
                    invoice.setBank(tfBold.getText());
                    invoice.setCash(tfCash.getText());
                    invoice.setTaxes("0");
                    invoice.setIndebtedness("0");
                    invoice.setTotal("0");
                    java.util.Date date = new Date();
                    invoice.setDate(String.valueOf(date));
                    invoice.setTotalBuy("0");
                    invoice.setUtilities("0");
                    invoice.setRUtilities("0");
                    invoice.setPartnersByIdPartners(Constant.partners);

                    //Cambia el saldo de la compañia y partner
                    int bank = Integer.valueOf(invoice.getBank());
                    int cash = Integer.valueOf(invoice.getCash());
                    int receivable = Integer.valueOf(invoice.getIndebtedness());
                    int total = bank + cash;
                    invoice.setTotal(String.valueOf(total));
                    final int payable = 0;
                    int rUtilities = Integer.valueOf(invoice.getRUtilities());
                    int utilities = Integer.valueOf(invoice.getUtilities());

                    ConstantsAccounting.invoice = invoice;

                    //El invoicetype dice que cuentas se veran afectadas
                    ConstantsPurchases.invoiceType = "socialContribution";

                    companyAccounting("PartnersClass",bank,cash,total,receivable,rUtilities, payable, utilities);
                    ConstantsAccounting.invoice.setIdCompany(Constant.company.getIdCompanyNIT());

                    partnerAccounting("PartnersClass",bank,cash,0,payable);
                    save.setDisable(false);
                }
                break;
            default:
                break;
        }
    }
    public void companyAccounting(String entity, int bank, int cash, int total, int receivable, int uReceivable, int payable, int utilities){
        Constant.entity = "CompanyClass";
        Constant.tfCode = "1";
        FoundHQL.workerFound();
        Constant.entity = entity;

        int actuallyBank = Integer.valueOf(Constant.company.getBank());
        int actuallyCash = Integer.valueOf(Constant.company.getCash());
        int actuallyTotal = Integer.valueOf(Constant.company.getTotal());
        int actuallyReceivable = Integer.valueOf(Constant.company.getReceivable());
        int actuallyUReceivable = Integer.valueOf(Constant.company.getUReceivable());
        int actuallyPayable = Integer.valueOf(Constant.company.getPayable());
        int actuallyUtilities = Integer.valueOf(Constant.company.getUtilities());

        int newBank = bank + actuallyBank;
        int newCash = cash + actuallyCash;
        int newTotal = total + actuallyTotal;
        int newReceivable = receivable + actuallyReceivable;
        int newUReceivable = uReceivable + actuallyUReceivable;
        int newPayable = payable + actuallyPayable;
        int newUtilities = utilities + actuallyUtilities;

        Constant.company.setBank(String.valueOf(newBank));
        Constant.company.setCash(String.valueOf(newCash));
        Constant.company.setTotal(String.valueOf(newTotal));
        Constant.company.setReceivable(String.valueOf(newReceivable));
        Constant.company.setUReceivable(String.valueOf(newUReceivable));
        Constant.company.setPayable(String.valueOf(newPayable));
        Constant.company.setUtilities(String.valueOf(newUtilities));

    }
    public void partnerAccounting(String entity, int bank, int cash, int receivable, int payable){

        int actuallyBank = Integer.valueOf(Constant.partners.getBank());
        int actuallyCash = Integer.valueOf(Constant.partners.getCash());
        int actuallyReceivable = Integer.valueOf(Constant.partners.getReceivable());
        int actuallyPayable = Integer.valueOf(Constant.partners.getPayable());

        int newBank = bank + actuallyBank;
        int newCash = cash + actuallyCash;
        int newReceivable = receivable + actuallyReceivable;
        int newPayable = payable + actuallyPayable;

        Constant.partners.setBank(String.valueOf(newBank));
        Constant.partners.setCash(String.valueOf(newCash));
        Constant.partners.setReceivable(String.valueOf(newReceivable));
        Constant.partners.setPayable(String.valueOf(newPayable));

    }
    @FXML
    public void buttonsOptions(ActionEvent event) {
        Button button = (Button) event.getSource();
        switch (button.getId()){
            case "buttonTaxes":
                ConstantsAccounting.entity = "TaxesClass";
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
                ConstantsAccounting.entity = "WarehouseClass";
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
                clean();
                ConstantsAccounting.entity = "Accounting";
                tfCode.setVisible(true);
                labelCode.setVisible(true);
                bSearchCode.setVisible(true);
                bCancelCode.setVisible(true);
                tableTwo.setVisible(true);
                labelNameShow.setVisible(true);
                labelPhone.setVisible(true);
                labelAddress.setVisible(true);

                labelIVA.setVisible(true);
                labelIVA.setText("TIPO");
                labelBold.setVisible(true);
                labelBold.setText("NOMBRE");

                comboBoxWare.setVisible(true);
                comboBoxProduct.setVisible(true);
                tableMain.setVisible(true);
                tfCode.setDisable(true);

                comboBoxLoad();

                break;
            case "buttonInput":
                //Se debe de porder hacer ingresos de dinero a la cuenta de la compañia
                clean();
                ConstantsAccounting.entity = "PartnersClass";

                labelIVA.setVisible(true);
                labelIVA.setText("Socio");
                comboBoxWare.setVisible(true);

                labelBold.setVisible(true);
                labelBold.setText("Banco");
                tfBold.setVisible(true);

                labelCash.setVisible(true);
                labelCash.setText("Efectivo");
                tfCash.setVisible(true);

                labelNameShow.setVisible(true);
                labelNameShow.setText(" Aporte a Capital Total: 0");

                comboBoxLoad();

                break;
            case "buttonReceivable":
                //Se debe de poder visualizar las deudas que tiene la empresa
                // y las deudas de los cleintes de la empresa
                clean();
                ConstantsAccounting.entity = "Debt";

                tfCode.setVisible(true);
                labelCode.setVisible(true);
                bSearchCode.setVisible(true);
                bCancelCode.setVisible(true);
                tableTwo.setVisible(true);
                labelNameShow.setVisible(true);
                labelPhone.setVisible(true);
                labelAddress.setVisible(true);

                labelIVA.setVisible(true);
                labelIVA.setText("TIPO");
                labelBold.setVisible(true);
                labelBold.setText("NOMBRE");

                comboBoxWare.setVisible(true);
                comboBoxProduct.setVisible(true);
                tableMain.setVisible(true);
                tfCode.setDisable(true);

                checkBoxDebt.setVisible(true);
                checkBoxPay.setVisible(true);
                checkBoxDebt.setSelected(true);
                checkBoxDebt.setText("Por Cobrar");
                checkBoxPay.setText("Por Pagar");

                comboBoxLoad();

                break;
            default:
                break;
        }


    }
    public void comboBoxLoad(){
     comboBoxWare.getItems().clear();
     comboBoxProduct.getItems().clear();
        ArrayList<String> listComboBox = new ArrayList<>();
        switch (ConstantsAccounting.entity){
            case "WarehouseClass":
                //Carga las Bodegas en el comboBox
                Constant.entity = "WarehouseClass";
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
                comboBoxProduct.getItems().addAll(listComboBox);
                break;
            case "Accounting":
                //Carga los tipos de cuentas que existen
                String [] typesAccounting = {"Compañia", "Cliente", "Socio", "Proveedor", "Trabajador"};
                comboBoxWare.getItems().addAll(typesAccounting);
                Constant.entity = "Accounting";
                break;
            case "PartnersClass":
                Constant.entity = "PartnersClass";
                SearchHQL.searchHQL();
                for(int i = 0; i< Constant.partnersList.length; i = i + 1){
                    String product = Constant.partnersList[i].getName();
                    if (product != null) {
                        listComboBox.add(product);
                    }
                }
                comboBoxWare.getItems().addAll(listComboBox);
                break;
            case "Debt":

                if(checkBoxDebt.isSelected()){
                    String [] forDebt = {"Cliente"};
                    comboBoxWare.getItems().addAll(forDebt);
                    comboBoxWare.setValue("Cliente");
                } else if (checkBoxPay.isSelected()) {
                    String [] forPay = {"Trabajador", "Proveedor"};
                    comboBoxWare.getItems().addAll(forPay);
                    comboBoxWare.setValue("Proveedor");
                }else{
                    System.out.println("ninguno");
                }
                break;
            default:
                break;
        }
    }
    public void comboBoxClick(ActionEvent event) {
        ComboBox comboBox = (ComboBox) event.getSource();

        String idComboBox = comboBox.getId();

        switch (ConstantsAccounting.entity){
            case "WarehouseClass":
                tableLoad(ConstantsAccounting.entity, idComboBox);
                break;
            case "Accounting":
                if(Objects.equals(idComboBox, "comboBoxWare")){
                    comboBoxProduct.getItems().clear();
                    ArrayList<String> listComboBoxName = new ArrayList<>();
                    switch (comboBoxWare.getValue()){
                        case "Compañia":
                            Constant.entity = "CompanyClass";
                            ConstantsAccounting.entity = "CompanyClass";
                            SearchHQL.searchHQL();
                            for (int i = 0; i < Constant.companiesList.length; i = i + 1) {
                                String product = Constant.companiesList[i].getName();
                                if (product != null) {
                                    listComboBoxName.add(product);
                                }
                            }
                            break;
                        case "Cliente":
                            Constant.entity = "CustomerClass";
                            ConstantsAccounting.entity = "CustomerClass";
                            SearchHQL.searchHQL();
                            for(int i = 0; i< Constant.customersList.length; i = i + 1){
                                String product = Constant.customersList[i].getName();
                                if (product != null) {
                                    listComboBoxName.add(product);
                                }
                            }
                            break;
                        case "Socio":
                            Constant.entity = "PartnersClass";
                            ConstantsAccounting.entity = "PartnersClass";
                            SearchHQL.searchHQL();
                            for(int i = 0; i< Constant.partnersList.length; i = i + 1){
                                String product = Constant.partnersList[i].getName();
                                if (product != null) {
                                    listComboBoxName.add(product);
                                }
                            }
                            break;
                        case "Proveedor":
                            Constant.entity = "ProvidersClass";
                            ConstantsAccounting.entity = "ProvidersClass";
                            SearchHQL.searchHQL();
                            for(int i = 0; i< Constant.providersList.length; i = i + 1){
                                String product = Constant.providersList[i].getName();
                                if (product != null) {
                                    listComboBoxName.add(product);
                                }
                            }
                            break;
                        case "Trabajador":
                            Constant.entity = "WorkersClass";
                            ConstantsAccounting.entity = "WorkersClass";
                            SearchHQL.searchHQL();
                            for(int i = 0; i< Constant.workersList.length; i = i + 1){
                                String product = Constant.workersList[i].getName();
                                if (product != null) {
                                    listComboBoxName.add(product);
                                }
                            }
                            break;
                        default:
                            break;
                    }
                    Constant.tfSalary = Constant.entity;
                    comboBoxProduct.getItems().addAll(listComboBoxName);
                    tfCode.setDisable(false);
                }
                if(Objects.equals(idComboBox, "comboBoxProduct") && comboBoxProduct.getValue() != null){
                    Constant.entity = Constant.tfSalary;
                    switch (Constant.entity){
                        case "CompanyClass":
                            for (int i = 0; i < Constant.companiesList.length; i = i + 1) {
                                String product = Constant.companiesList[i].getName();
                                if (product.equals(comboBoxProduct.getValue())) {
                                    tfCode.setText(Constant.companiesList[i].getIdCompanyNIT());
                                }
                            }
                            break;
                        case "CustomerClass":
                            for(int i = 0; i< Constant.customersList.length; i = i + 1){
                                String product = Constant.customersList[i].getName();
                                if (product.equals(comboBoxProduct.getValue())) {
                                    tfCode.setText(Constant.customersList[i].getIdCustomer());
                                }
                            }
                            break;
                        case "PartnersClass":
                            for(int i = 0; i< Constant.partnersList.length; i = i + 1){
                                String product = Constant.partnersList[i].getName();
                                if (product.equals(comboBoxProduct.getValue())) {
                                    tfCode.setText(Constant.partnersList[i].getIdentificationCard());
                                }
                            }
                            break;
                        case "ProvidersClass":
                            for(int i = 0; i< Constant.providersList.length; i = i + 1){
                                String product = Constant.providersList[i].getName();
                                if (product.equals(comboBoxProduct.getValue())) {
                                    tfCode.setText(Constant.providersList[i].getNit());
                                }
                            }
                            break;
                        case "WorkersClass":
                            for(int i = 0; i< Constant.workersList.length; i = i + 1){
                                String product = Constant.workersList[i].getName();
                                if (product.equals(comboBoxProduct.getValue())) {
                                    tfCode.setText(Constant.workersList[i].getIdentificationCard());
                                }
                            }
                            break;
                        default:
                            break;
                    }

                }
                tableMain.getItems().clear();
                tableTwo.getItems().clear();
                ConstantsAccounting.entity = "Accounting";
                break;
            case "PartnersClass":
                tfCash.setText("0");
                tfBold.setText("0");
                labelNameShow.setText(" Aporte Total: ");
                for(int i = 0; i< Constant.partnersList.length; i = i + 1){
                    String product = Constant.partnersList[i].getName();
                    if (product.equals(comboBoxWare.getValue())) {
                        Constant.partners = Constant.partnersList[i];
                    }
                }
                break;
            case "Debt":
                if(Objects.equals(idComboBox, "comboBoxWare")){
                    comboBoxProduct.getItems().clear();
                    ArrayList<String> listComboBoxName = new ArrayList<>();
                    switch (comboBoxWare.getValue()){
                        case "Cliente":
                            Constant.entity = "CustomerClass";
                            SearchHQL.searchHQL();
                            for(int i = 0; i< Constant.customersList.length; i = i + 1){
                                String product = Constant.customersList[i].getName();
                                if (product != null) {
                                    listComboBoxName.add(product);
                                }
                            }
                            break;
                        case "Socio":
                            Constant.entity = "PartnersClass";
                            SearchHQL.searchHQL();
                            for(int i = 0; i< Constant.partnersList.length; i = i + 1){
                                String product = Constant.partnersList[i].getName();
                                if (product != null) {
                                    listComboBoxName.add(product);
                                }
                            }
                            break;
                        case "Proveedor":
                            Constant.entity = "ProvidersClass";
                            SearchHQL.searchHQL();
                            for(int i = 0; i< Constant.providersList.length; i = i + 1){
                                String product = Constant.providersList[i].getName();
                                if (product != null) {
                                    listComboBoxName.add(product);
                                }
                            }
                            break;
                        case "Trabajador":
                            Constant.entity = "WorkersClass";
                            SearchHQL.searchHQL();
                            for(int i = 0; i< Constant.workersList.length; i = i + 1){
                                String product = Constant.workersList[i].getName();
                                if (product != null) {
                                    listComboBoxName.add(product);
                                }
                            }
                            break;
                        default:
                            break;
                    }
                    comboBoxProduct.getItems().addAll(listComboBoxName);
                    tfCode.setDisable(false);
                }
                if(Objects.equals(idComboBox, "comboBoxProduct") && comboBoxProduct.getValue() != null){
                    switch (Constant.entity){
                        case "CustomerClass":
                            for(int i = 0; i< Constant.customersList.length; i = i + 1){
                                String product = Constant.customersList[i].getName();
                                if (product.equals(comboBoxProduct.getValue())) {
                                    tfCode.setText(Constant.customersList[i].getIdCustomer());
                                }
                            }
                            break;
                        case "PartnersClass":
                            for(int i = 0; i< Constant.partnersList.length; i = i + 1){
                                String product = Constant.partnersList[i].getName();
                                if (product.equals(comboBoxProduct.getValue())) {
                                    tfCode.setText(Constant.partnersList[i].getIdentificationCard());
                                }
                            }
                            break;
                        case "ProvidersClass":
                            for(int i = 0; i< Constant.providersList.length; i = i + 1){
                                String product = Constant.providersList[i].getName();
                                if (product.equals(comboBoxProduct.getValue())) {
                                    tfCode.setText(Constant.providersList[i].getNit());
                                }
                            }
                            break;
                        case "WorkersClass":
                            for(int i = 0; i< Constant.workersList.length; i = i + 1){
                                String product = Constant.workersList[i].getName();
                                if (product.equals(comboBoxProduct.getValue())) {
                                    tfCode.setText(Constant.workersList[i].getIdentificationCard());
                                }
                            }
                            break;
                        default:
                            break;
                    }

                }
                break;
            default:
                break;
        }
    }
    public void tableLoad(String entity, String idComboBox){
        try{
            switch (entity){
                case "WarehouseClass":
                    tableMain.getItems().clear();
                    //carga todos los productos que hay en esa bodega
                    /*producto, cantidad, precio
                    * */
                    Constant.listTableShow.clear();
                    if(Objects.equals(idComboBox, "comboBoxWare")){
                        //obtiene la lista de productos en la bodega
                        ConstantsWare.wareProductList = null;
                        Constant.tfCode = comboBoxWare.getValue();
                        Constant.entity = "WarehouseClass";
                        FoundHQL.workerFound();
                        ConstantsWare.wareProductList = ConstantsWare.ware.getWareProductsByIdWarehouse().toArray(new WareProductClass[0]);
                        //Obtiene lista de productPrice
                        Constant.entity = "ProductpriceClass";
                        SearchHQL.searchHQL();
                        Constant.entity = "WarehouseClass";
                        for(WareProductClass wP: ConstantsWare.wareProductList){
                            for(ProductpriceClass pP: ConstantsWare.productPriceList){
                                if(wP.getIdWareProduct() == pP.getIdProductWare()){
                                    Constant.entity = "ProductClass";
                                    Constant.tfCode = wP.getIdProduct();
                                    FoundHQL.workerFound();
                                    Constant.entity = "WarehouseClass";

                                    TableShow tableShow = new TableShow();
                                    tableShow.setC1(ConstantsWare.product.getName());
                                    tableShow.setC2(String.valueOf(pP.getAmount()));
                                    tableShow.setC3(String.valueOf(pP.getPrice()));
                                    tableShow.setC4(ConstantsWare.product.getSalePrice());
                                    tableShow.setC5(wP.getIdWare());
                                    Constant.listTableShow.add(tableShow);
                                }
                            }
                        }
                    }else{
                        //obtiene la lista de productos en la bodega
                        ConstantsWare.wareProductList = null;
                        Constant.tfCode = comboBoxProduct.getValue();
                        Constant.entity = "ProductClass";
                        FoundHQL.wareFound();
                        ConstantsWare.wareProductList = ConstantsWare.product.getWareProductsByIdProduct().toArray(new WareProductClass[0]);
                        //Obtiene lista de productPrice
                        Constant.entity = "ProductpriceClass";
                        SearchHQL.searchHQL();
                        Constant.entity = "WarehouseClass";
                        for(WareProductClass wP: ConstantsWare.wareProductList){
                            for(ProductpriceClass pP: ConstantsWare.productPriceList){
                                if(wP.getIdWareProduct() == pP.getIdProductWare()){
                                    TableShow tableShow = new TableShow();
                                    tableShow.setC1(ConstantsWare.product.getName());
                                    tableShow.setC2(String.valueOf(pP.getAmount()));
                                    tableShow.setC3(String.valueOf(pP.getPrice()));
                                    tableShow.setC4(ConstantsWare.product.getSalePrice());
                                    tableShow.setC5(wP.getIdWare());
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
                    c5.setText("Bodega");
                    c5.setCellValueFactory(new PropertyValueFactory<>("c5"));
                    ObservableList<TableShow> datesTTT = FXCollections.observableArrayList(Constant.listTableShow);
                    tableMain.setItems(datesTTT);
                    break;
                case "Accounting":
                   if(idComboBox.equals("tableMain")){
                       tableMain.getItems().clear();
                       c1.setText("ID");
                       c1.setCellValueFactory(new PropertyValueFactory<>("c1"));
                       c2.setText("Fecha");
                       c2.setCellValueFactory(new PropertyValueFactory<>("c2"));
                       c3.setText("Total");
                       c3.setCellValueFactory(new PropertyValueFactory<>("c3"));
                       c4.setText("Deuda");
                       c4.setCellValueFactory(new PropertyValueFactory<>("c4"));
                       c5.setText("Utilidad");
                       c5.setCellValueFactory(new PropertyValueFactory<>("c5"));
                       ObservableList<TableShow> datesIv = FXCollections.observableArrayList(Constant.listTableShow);
                       tableMain.setItems(datesIv);
                   }
                   if(idComboBox.equals("tableTwo")){
                       c21.setText("Producto");
                       c21.setCellValueFactory(new PropertyValueFactory<>("c1"));
                       c22.setText("Cantidad");
                       c22.setCellValueFactory(new PropertyValueFactory<>("c2"));
                       c23.setText("P. Venta");
                       c23.setCellValueFactory(new PropertyValueFactory<>("c3"));
                       c24.setText("P. Compra");
                       c24.setCellValueFactory(new PropertyValueFactory<>("c4"));
                       c25.setText("ID factura");
                       c25.setCellValueFactory(new PropertyValueFactory<>("c5"));
                       ObservableList<TableShow> dateWIv = FXCollections.observableArrayList(Constant.listTableShow2);
                       tableTwo.setItems(dateWIv);
                   }
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

        switch (ConstantsAccounting.entity){
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

            case "PartnersClass":
                try{
                    int cash = Integer.valueOf(tfCash.getText());
                    int bank = Integer.valueOf(tfBold.getText());
                    int total = cash + bank;
                    String input = String.valueOf(total);
                    labelNameShow.setText(" Aporte a Capital Total: " + input);
                }catch (Exception i){
                    labelNameShow.setText("Error");
                }
                break;
            default:
                break;
        }
    }
    public void clean(){
        labelBold.setVisible(false);
        labelIVA.setVisible(false);
        labelCode.setVisible(false);
        labelNameShow.setVisible(false);
        labelPhone.setVisible(false);
        labelAddress.setVisible(false);
        labelCash.setVisible(false);

        tfBold.setVisible(false);
        tfIva.setVisible(false);
        tfCode.setVisible(false);
        tfCode.setDisable(true);
        tfCash.setVisible(false);

        tfBold.setText("");
        tfIva.setText("");
        tfCash.setText("");
        labelNameShow.setText(" NOMBRE: ");
        labelPhone.setText(" TELEFONO: " );
        labelAddress.setText(" DIRECCIÓN: ");
        tfCode.setText("");

        save.setDisable(true);
        bSearchCode.setVisible(false);
        bCancelCode.setVisible(false);

        comboBoxProduct.setVisible(false);
        comboBoxWare.setVisible(false);

        checkBoxPay.setVisible(false);
        checkBoxDebt.setVisible(false);
        checkBoxPay.setSelected(false);
        checkBoxDebt.setSelected(false);

        tableMain.setVisible(false);
        tableTwo.setVisible(false);
        tableMain.getItems().clear();
        tableTwo.getItems().clear();



    }
    @FXML
    public void search(ActionEvent event) {
        Constant.tfCode = tfCode.getText();
        FoundHQL.workerFound();
        switch (Constant.entity){
            case "CompanyClass":
                labelNameShow.setText(" NOMBRE: " + Constant.company.getName());
                labelPhone.setText(" TELEFONO: " + Constant.company.getPhoneNumber());
                labelAddress.setText(" DIRECCIÓN: "+ Constant.company.getAddres());
                ConstantsAccounting.invoiceList = Constant.company.getInvoicesByIdCompanyNit().toArray(new InvoiceClass[0]);
                tableNull();
                break;
            case "CustomerClass":
                labelNameShow.setText(" NOMBRE: " + Constant.customer.getName());
                labelPhone.setText(" TELEFONO: " + Constant.customer.getPhoneNumber());
                labelAddress.setText(" DIRECCIÓN: "+ Constant.customer.getAddres());
                ConstantsAccounting.invoiceList = Constant.customer.getInvoicesByIdCustomer().toArray(new InvoiceClass[0]);
                tableNull();
                break;
            case "PartnersClass":
                labelNameShow.setText(" NOMBRE: " + Constant.partners.getName());
                labelPhone.setText(" TELEFONO: " + Constant.partners.getPhoneNumber());
                labelAddress.setText(" DIRECCIÓN: "+ Constant.partners.getAddress());
                ConstantsAccounting.invoiceList = Constant.partners.getInvoicesByIdentificationCard().toArray(new InvoiceClass[0]);
                tableNull();
                break;
            case "ProvidersClass":
                labelNameShow.setText(" NOMBRE: " + Constant.provider.getName());
                labelPhone.setText(" TELEFONO: " + Constant.provider.getPhoneNumber());
                labelAddress.setText(" DIRECCIÓN: "+ Constant.provider.getAddress());
                ConstantsAccounting.invoiceList = Constant.provider.getInvoicesByNit().toArray(new InvoiceClass[0]);
                tableNull();
                break;
            case "WorkersClass":
                labelNameShow.setText(" NOMBRE: " + Constant.worker.getName());
                labelPhone.setText(" TELEFONO: " + Constant.worker.getPhoneNumber());
                labelAddress.setText(" DIRECCIÓN: "+ Constant.worker.getAddress());
                ConstantsAccounting.invoiceList = Constant.worker.getInvoicesByIdentificationCard().toArray(new InvoiceClass[0]);
                tableNull();
                break;
            default:
                break;
        }

    }
    public void tableSelection() {
        switch (ConstantsAccounting.entity){
            case "WarehouseClass":
                System.out.println("nada");
                break;
            case "Accounting":
                try{
                    if(ConstantsAccounting.invoiceList.length != 0) {

                        TableShow tableSelected = tableMain.getSelectionModel().getSelectedItem();

                        Constant.entity = "InvoiceClass";
                        Constant.tfCode = String.valueOf(tableSelected.getC1());
                        FoundHQL.workerFound();

                        Constant.entity = "WareinvoiceClass";
                        SearchHQL.searchHQL();

                        Constant.entity = "Accounting";
                        forInvoice();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println(e);
                }
                break;
            case "Debt":
                try{
                    if(ConstantsAccounting.invoiceList.length != 0) {
                        TableShow tableSelected1 = tableMain.getSelectionModel().getSelectedItem();
                        InvoiceClass invoice1 = new InvoiceClass();
                        invoice1.setIdInvoice(Integer.valueOf(tableSelected1.getC1()));
                        invoice1.setDate(tableSelected1.getC2());
                        invoice1.setTotal(tableSelected1.getC3());
                        invoice1.setIndebtedness(tableSelected1.getC4());
                        invoice1.setUtilities(tableSelected1.getC5());
                        Constant.entity = "InvoiceClass";
                        Constant.tfCode = String.valueOf(invoice1.getIdInvoice());
                        FoundHQL.workerFound();
                        ConstantsAccounting.debtList = ConstantsAccounting.invoice.getMoveinvoicesByIdInvoice().toArray(new MoveinvoiceClass[0]);
                        forInvoice();
                    }
                }catch (Exception e){
                    System.out.println(e);
                }

                break;
            default:
                break;
        }
    }
    public void forInvoice(){

        Constant.listTableShow2.clear();

        for(WareinvoiceClass wiv: ConstantsPurchases.wareInvoiceList){
            TableShow tableShow2 = new TableShow();
            tableShow2.setC1(wiv.getProductName());
            tableShow2.setC2(String.valueOf(wiv.getAmount()));
            tableShow2.setC3(wiv.getPriceSale());
            tableShow2.setC4(String.valueOf(wiv.getIdProductPrice()));
            tableShow2.setC5(String.valueOf(wiv.getIdInvoice()));
            Constant.listTableShow2.add(tableShow2);
        }

        tableLoad(ConstantsAccounting.entity, "tableTwo");
    }
    public void tableNull(){
        Constant.listTableShow.clear();
        Constant.listTableShow2.clear();
        System.out.println(ConstantsAccounting.invoiceList.length);
        if(ConstantsAccounting.invoiceList.length != 0) {
            for (InvoiceClass iv : ConstantsAccounting.invoiceList) {
                TableShow tableShow = new TableShow();
                tableShow.setC1(String.valueOf(iv.getIdInvoice()));
                tableShow.setC2(iv.getDate());
                tableShow.setC3(iv.getTotal());
                tableShow.setC4(iv.getIndebtedness());
                tableShow.setC5(iv.getUtilities());
                Constant.listTableShow.add(tableShow);
                if (Constant.listTableShow.size() == 1) {
                    ConstantsAccounting.wareInvoiceList = iv.getWareinvoicesByIdInvoice().toArray(new WareinvoiceClass[0]);
                    forInvoice();
                }
            }
        }else{
            TableShow tableShow = new TableShow();
            tableShow.setC1("null");
            tableShow.setC2("null");
            tableShow.setC3("null");
            tableShow.setC4("null");
            tableShow.setC5("null");
            Constant.listTableShow.add(tableShow);
            Constant.listTableShow2.add(tableShow);
            tableLoad(ConstantsAccounting.entity, "tableTwo");
        }
        tableLoad(ConstantsAccounting.entity, "tableMain");
    }
    public void changeMoney(ActionEvent event) {
    }
    public void checkBox(ActionEvent event) {
        CheckBox ch = (CheckBox) event.getSource();
        String idCh = ch.getId();

        if(idCh.equals("checkBoxDebt")){
            checkBoxPay.setSelected(false);
            checkBoxDebt.setSelected(true);
        }else{
            checkBoxPay.setSelected(true);
            checkBoxDebt.setSelected(false);
        }
        comboBoxLoad();
    }
    @FXML
    public void cancel(ActionEvent event) {
        labelNameShow.setText(" NOMBRE: ");
        labelPhone.setText(" TELEFONO: " );
        labelAddress.setText(" DIRECCIÓN: ");
        tfCode.setText("");
        tableTwo.getItems().clear();
        tableMain.getItems().clear();
    }
}
