package com.forpus.forpusinventory.controller;

import com.forpus.forpusinventory.domain.services.Constant;
import com.forpus.forpusinventory.domain.services.ConstantsFinance;
import com.forpus.forpusinventory.domain.services.ConstantsPurchases;
import com.forpus.forpusinventory.domain.services.ConstantsWare;
import com.forpus.forpusinventory.persistence.Session.SessionDB;
import com.forpus.forpusinventory.persistence.crud.DataBase;
import com.forpus.forpusinventory.persistence.crud.FoundHQL;
import com.forpus.forpusinventory.persistence.crud.SaveHQL;
import com.forpus.forpusinventory.persistence.crud.SearchHQL;
import com.forpus.forpusinventory.persistence.entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class FinanceController {

    public Button buttonWarePB;
    public Button buttonDashPB;
    public Button buttonAccountingPB;
    public Button buttonBuyPB;
    public Button buttonSalePB;
    public Button buttonSettingsPB;
    public Label bankCompany;
    public Label cashCompany;
    public Label totalCompany;
    public TableView<PartnersClass> tablePartners;
    public TableColumn<Object, Object> a1;
    public TableColumn<Object, Object> a2;
    public TableColumn<Object, Object> a3;
    public TableColumn<Object, Object> a4;
    public Label partnersTotal;
    public TableView<ProductClass> tableProducts;
    public TableColumn<Object, Object> p1;
    public TableColumn<Object, Object> p2;
    public TableColumn<Object, Object> p3;
    public TableColumn<Object, Object> p4;
    public Label productTotal;
    public TableView<InvoiceClass> tablePassives;
    public Label passivesTotal;
    public TableColumn<Object, Object> ps1;
    public TableColumn<Object, Object> ps2;
    public TableColumn<Object, Object> ps3;
    public TableView<InvoiceClass> tableReceivable;
    public TableColumn<Object, Object> r1;
    public TableColumn<Object, Object> r2;
    public TableColumn<Object, Object> r3;
    public Label totalReceivable;
    public Pane panelReceivable;
    public Button buttonFinances;
    public Button buttonGraphics;
    public Button buttonDB;
    public Pane panelProducts;
    public Pane panelPassives;
    public Pane panelPartners;
    public Pane panelFinance;
    public Button buttonExport;
    public Label labelRuta;
    public Pane panelDB;
    public Button buttonBackUp;
    public Button buttonRestore;
    public Button buttonCorte;
    public Label labelRuta2;
    public Button buttonRuta3;
    public Label labelRuta3;
    public Button buttonRuta2;
    public Button buttonDownload;
    public ComboBox<String> cbBoxCuentas;
    @FXML
    private LineChart<String , Number> chartSales;
    @FXML
    public LineChart<String , Number> chartPurchases;
    public BarChart<String, Number> chartProducts;
    public BarChart<String, Number> chartCustomers;
    @FXML
    protected void buttonSlide(ActionEvent event) throws IOException {
        WareController.slide(event);
    }
    public void initialize() {
        SessionDB.sessionClose();

        //CARGA LA LIQUIDEZ
        companyFinance();

        //CARGA LOS APORTES
        a1.setCellValueFactory(new PropertyValueFactory<>("Name"));
        a2.setCellValueFactory(new PropertyValueFactory<>("cash"));
        a3.setCellValueFactory(new PropertyValueFactory<>("bank"));
        a4.setCellValueFactory(new PropertyValueFactory<>("payable"));
        partnersFinance();

        p1.setCellValueFactory(new PropertyValueFactory<>("name"));
        p2.setCellValueFactory(new PropertyValueFactory<>("amount"));
        p3.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));
        p4.setCellValueFactory(new PropertyValueFactory<>("profit"));
        productFinance();

        ps1.setCellValueFactory(new PropertyValueFactory<>("idProviders"));
        ps2.setCellValueFactory(new PropertyValueFactory<>("date"));
        ps3.setCellValueFactory(new PropertyValueFactory<>("idBill"));
        passivesFinance();

        r1.setCellValueFactory(new PropertyValueFactory<>("idCustomer"));
        r2.setCellValueFactory(new PropertyValueFactory<>("date"));
        r3.setCellValueFactory(new PropertyValueFactory<>("idBill"));
        receivable();

        buttonFinances.setStyle("-fx-background-color: #F5F5F5;");

        cbBoxCuentas.getItems().addAll(ConstantsFinance.cuentas);
    }
    //FUNCION PARA CARGAR LOS SALDOS DE LA EMPRESA
    public void companyFinance(){
        Constant.entity = "CompanyClass";
        FoundHQL.workerFound();
        bankCompany.setText(Constant.company.getBank());
        cashCompany.setText(Constant.company.getCash());
        totalCompany.setText(Constant.company.getTotal());
    }
    //METODO PARA CARGAR APORTES
    public void partnersFinance(){
        Constant.entity = "PartnersClass";
        SearchHQL.searchHQL();
        tablePartners.getItems().clear();
        try{
            ArrayList<PartnersClass> partners = new ArrayList<>();
            int total = 0;
            for(PartnersClass p: Constant.partnersList){
                int totalCapital = Integer.parseInt(p.getBank())
                        + Integer.parseInt(p.getCash())
                        - Integer.parseInt(p.getReceivable());
                if(totalCapital > 1){
                    p.setPayable(String.valueOf(totalCapital));
                    total = total + totalCapital;
                    partners.add(p);
                }
            }
            ObservableList<PartnersClass> partnersObLi =
                    FXCollections.observableArrayList(partners);
            tablePartners.setItems(partnersObLi);
            partnersTotal.setText(String.valueOf(total));
        }catch (Exception i){
            i.printStackTrace();
            WareController.alertSend("Sin Aportes Sociales");
        }
    }
    //METODO PARA CARGAR LOS ACTIVOS(PRODUCTOS)
    public void productFinance(){
        Constant.entity ="ProductClass";
        SearchHQL.searchHQL();
        tableProducts.getItems().clear();
        try {
            ArrayList<ProductClass> products = new ArrayList<>();
            int total = 0;
            for (ProductClass p: ConstantsWare.productList){
                for(WareProductClass w: p.getWareProductsByIdProduct()){
                    for(ProductpriceClass t:w.getProductpricesByIdWareProduct()){
                        ProductClass pdt = new ProductClass();
                        pdt.setIdProduct(t.getIdPrice() + p.getIdProduct());
                        pdt.setSalePrice(p.getSalePrice());
                        pdt.setName(p.getName());
                        pdt.setPurchasePrice(String.valueOf(t.getPrice()));
                        pdt.setAmount(t.getAmount());
                        pdt.setProfit(String.valueOf(t.getAmount() * t.getPrice()));
                        products.add(pdt);
                        total = total + Integer.parseInt(pdt.getProfit());
                    }
                }
            }
            ObservableList<ProductClass> pdtObList = FXCollections.observableArrayList(products);
            tableProducts.setItems(pdtObList);
            productTotal.setText(String.valueOf(total));
        }catch (Exception e){
            e.printStackTrace();
            WareController.alertSend("Sin Productos");
        }

    }
    //METODO PARA CARGAR LOS PASIVOS (CUENTAS POR PAGAR)
    public void passivesFinance(){

        try {
            SearchHQL.invoiceIdBill("ProvidersClass");
            ConstantsPurchases.invoiceCredit = ConstantsPurchases.invoiceList;
            SearchHQL.invoiceIdBill("WorkersClass");
            ConstantsPurchases.invoiceList.addAll(ConstantsPurchases.invoiceCredit);
            ConstantsPurchases.invoiceCredit.clear();
            int total = 0;
            for(InvoiceClass iv: ConstantsPurchases.invoiceList){
                total = total + iv.getIdBill();
                if(iv.getIdProviders() == null){
                    iv.setIdProviders(iv.getIdWorkers());
                }
                ConstantsPurchases.invoiceCredit.add(iv);
            }

            tablePassives.getItems().clear();
            ObservableList<InvoiceClass> passives = FXCollections.observableArrayList(ConstantsPurchases.invoiceCredit);
            tablePassives.setItems(passives);

            passivesTotal.setText(String.valueOf(total));
        } catch (Exception i){
            i.printStackTrace();
            WareController.alertSend("Sin Deudas");
        }
    }
    //METODO PARA CARGAR LAS CUENTAS POR COBRAR
    public void receivable(){
        try {
            SearchHQL.invoiceIdBill("CustomerClass");
            int total = 0;

            if(!ConstantsPurchases.invoiceCredit.isEmpty()){
                ConstantsPurchases.invoiceCredit.clear();
            }

            for(InvoiceClass iv: ConstantsPurchases.invoiceList){
                total = total + iv.getIdBill();
                ConstantsPurchases.invoiceCredit.add(iv);
                totalReceivable.setText(String.valueOf(total));
            }

            tableReceivable.getItems().clear();
            ObservableList<InvoiceClass> receivable = FXCollections.observableArrayList(ConstantsPurchases.invoiceCredit);
            tableReceivable.setItems(receivable);

        }catch (Exception i){
            i.printStackTrace();
        }
    }
    //METODO PARA CARGAR LAS VENTAS EN EL GRAFICO
    public void graphicsSales() throws ParseException {
        SearchHQL.invoiceEntity("CustomerClass");
        chartSales.getData().clear();
        XYChart.Series<String, Number> series = graphics();
        series.setName("VENTAS");
        chartSales.setTitle("VENTAS");
        chartSales.getData().add(series);
    }
    public void graphicsPurchases() throws ParseException {
        SearchHQL.invoiceEntity("ProvidersClass");
        chartPurchases.getData().clear();
        chartPurchases.setTitle("COMPRAS");

        XYChart.Series<String, Number> series = graphics();
        series.setName("COMPRAS");
        chartPurchases.getData().add(series);
    }
    public void graphicsProducts(){
        SearchHQL.wareInvoice();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("PRODUCTOS");

        for (int i = 0; i < 10 && i < ConstantsPurchases.wareInvoiceList.size(); i++) {
            WareinvoiceClass result = ConstantsPurchases.wareInvoiceList.get(i);
            String idProduct = result.getIdProduct();
            int amount = result.getAmount();
            // Aquí puedes hacer lo que necesites con los valores de idProduct y amount
            series.getData().add(new XYChart.Data<>(idProduct, amount));
        }
        chartProducts.getData().clear();
        chartProducts.setTitle("PRODUCTOS");
        chartProducts.getData().add(series);
    }
    public void graphicsCustomers(){
        SearchHQL.customersFinance();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("CLIENTES");

        for (int i = 0; i < 10 && i < Constant.customersList.length; i++) {
            CustomerClass result = Constant.customersList[i];
            String nameCustomer = result.getName();
            int amount = Integer.parseInt(result.getBank()) + Integer.parseInt(result.getCash());
            // Aquí puedes hacer lo que necesites con los valores de idProduct y amount
            series.getData().add(new XYChart.Data<>(nameCustomer, amount));
        }
        chartCustomers.getData().clear();
        chartCustomers.setTitle("CLIENTES");
        chartCustomers.getData().add(series);
    }

    public XYChart.Series<String, Number> graphics() throws ParseException {

        int m1 = 0, m2 = 0, m3 = 0, m4 = 0, m5 = 0, m6 = 0
                , m7 = 0, m8 = 0, m9 = 0, m10 = 0, m11 = 0, m12 = 0;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");
        for(InvoiceClass iv:ConstantsPurchases.invoiceList){
            Date date = format.parse(iv.getDate());
            calendar.setTime(date);
            switch (calendar.get(Calendar.MONTH) + 1){
                case 1:
                    m1 = m1 - iv.getIdBill() + Integer.parseInt(iv.getTotal());
                    break;
                case 2:
                    m2 = m2 - iv.getIdBill() + Integer.parseInt(iv.getTotal());
                    break;
                case 3:
                    m3 = m3 - iv.getIdBill() + Integer.parseInt(iv.getTotal());
                    break;
                case 4:
                    m4 = m4 - iv.getIdBill() + Integer.parseInt(iv.getTotal());
                    break;
                case 5:
                    m5 = m5 - iv.getIdBill() + Integer.parseInt(iv.getTotal());
                    break;
                case 6:
                    m6 = m6 - iv.getIdBill() + Integer.parseInt(iv.getTotal());
                    break;
                case 7:
                    m7 = m7 - iv.getIdBill() + Integer.parseInt(iv.getTotal());
                    break;
                case 8:
                    m8 = m8 - iv.getIdBill() + Integer.parseInt(iv.getTotal());
                    break;
                case 9:
                    m9 = m9 - iv.getIdBill() + Integer.parseInt(iv.getTotal());
                    break;
                case 10:
                    m10 = m10 - iv.getIdBill() + Integer.parseInt(iv.getTotal());
                    break;
                case 11:
                    m11 = m11 - iv.getIdBill() + Integer.parseInt(iv.getTotal());
                    break;
                case 12:
                    m12 = m12 - iv.getIdBill() + Integer.parseInt(iv.getTotal());
                    break;

            }
        }
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("MONTH");
        series.getData().add(new XYChart.Data<>("ENE", m1));
        series.getData().add(new XYChart.Data<>("FEB", m2));
        series.getData().add(new XYChart.Data<>("MAR", m3));
        series.getData().add(new XYChart.Data<>("ABR", m4));
        series.getData().add(new XYChart.Data<>("MAY", m5));
        series.getData().add(new XYChart.Data<>("JUN", m6));
        series.getData().add(new XYChart.Data<>("JUL", m7));
        series.getData().add(new XYChart.Data<>("AGO", m8));
        series.getData().add(new XYChart.Data<>("SEP", m9));
        series.getData().add(new XYChart.Data<>("OCT", m10));
        series.getData().add(new XYChart.Data<>("NOV", m11));
        series.getData().add(new XYChart.Data<>("DIC", m12));

        return series;
    }
    public void buttonCRUD(ActionEvent event) {
    }
    public void buttonsOptions(ActionEvent event) {
        clear();
        Button button = (Button) event.getSource();
        final boolean a = true;
        switch (button.getId()){
            case "buttonFinances":
                buttonFinances.setStyle("-fx-background-color: #F5F5F5;");
                panelPartners.setVisible(a);
                panelPassives.setVisible(a);
                panelProducts.setVisible(a);
                panelReceivable.setVisible(a);
                panelFinance.setVisible(a);
                break;
            case "buttonGraphics":
                buttonGraphics.setStyle("-fx-background-color: #F5F5F5;");
                chartCustomers.setVisible(a);
                chartProducts.setVisible(a);
                chartPurchases.setVisible(a);
                chartSales.setVisible(a);

                try {
                    graphicsSales();
                    graphicsPurchases();
                    graphicsProducts();
                    graphicsCustomers();
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "buttonDB":
                panelDB.setVisible(a);
                buttonDB.setStyle("-fx-background-color: #F5F5F5;");
                break;
        }
    }

    public void clear(){
        SessionDB.sessionClose();
        final boolean a = false;
        panelPartners.setVisible(a);
        panelPassives.setVisible(a);
        panelProducts.setVisible(a);
        panelReceivable.setVisible(a);
        panelFinance.setVisible(a);
        chartCustomers.setVisible(a);
        chartProducts.setVisible(a);
        chartPurchases.setVisible(a);
        chartSales.setVisible(a);
        panelDB.setVisible(a);

        buttonFinances.setStyle("-fx-background-color: #1BA1E2;");
        buttonGraphics.setStyle("-fx-background-color: #1BA1E2;");
        buttonDB.setStyle("-fx-background-color: #1BA1E2;");
    }

    public void export(ActionEvent event) {

        Button button = (Button) event.getSource();

        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(null);

        if (selectedDirectory != null) {
            // Hacer algo con la ruta seleccionada
            switch (button.getId()){
                case "buttonExport":
                    labelRuta.setText(selectedDirectory.getAbsolutePath());
                    break;
                case "buttonRuta2":
                    labelRuta2.setText(selectedDirectory.getAbsolutePath());
                    break;
            }
        }

        buttonBackUp.setDisable(!labelRuta.getText().contains("\\"));
        buttonDownload.setDisable(!labelRuta.getText().contains("\\"));
    }

    public void restore(ActionEvent event) {
        String entity1 = cbBoxCuentas.getValue();
        String entity2 =cbClass(entity1);
        if(DataBase.importTable(escaparCaracteres(labelRuta2.getText()),entity2)){
            WareController.alertSend("DATOS IMPORTADOS CON EXITO");
            SessionDB.sessionClose();
        }else {
            WareController.alertSend("ERROR AL IMPORTAR LOS DATOS");
        }
    }

    public void corte(ActionEvent event) {
    }
    //DESCARGA EL SQL DE LA BASE DE DATOS
    public void backup(ActionEvent event) {
        if(DataBase.backUp(escaparCaracteres(labelRuta.getText()))){
            WareController.alertSend("SE HA CREADO UN ARCHIVO SQL");
        }else {
            WareController.alertSend("ERROR AL CREAR EL BACKUP");
        }
    }
    public static String escaparCaracteres(String cadena) {
        return cadena.replace("\\", "\\\\");
    }

    public void tablas(ActionEvent event) {
        String entity1 = cbBoxCuentas.getValue();
        String entity2 =cbClass(entity1);
        DataBase.downloadTable(escaparCaracteres(labelRuta.getText()),entity2);
    }
    public static String cbClass(String entity1){
        String entity2;
        switch (entity1){
            case "Clientes":
                entity2 = "CustomerClass";
                break;
            case "Trabajadores":
                entity2 = "WorkersClass";
                break;
            case  "Proveedores":
                entity2 = "ProvidersClass";
                break;
            case "Socios":
                entity2 = "PartnersClass";
                break;
            case "Productos":
                entity2 = "ProductClass";
                break;
            case "Servicios":
                entity2 = "ServiceClass";
                break;
            default:
                entity2 = "null";
                break;
        }
        return  entity2;
    }

    public void cbCuentas(ActionEvent event) {
    }
    public void importExcel(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccione el archivo de Excel");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de Excel", "*.xlsx"));

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            String selectedFilePath = selectedFile.getAbsolutePath();
            labelRuta2.setText(selectedFilePath);
        }

        buttonRestore.setDisable(!labelRuta2.getText().contains("xlsx"));

    }
    public void deletedDB(ActionEvent event) {
        if(DataBase.deleteAllData("todos")){
            String entity = Constant.entity;
            Constant.entity = "CompanyClass";
            if(FoundHQL.workerFound()) {

                Constant.tfName = Constant.company.getName();
                Constant.tfPhone = Constant.company.getPhoneNumber();
                Constant.tfAddress = Constant.company.getAddres();
                Constant.tfJob = Constant.company.getWeb();
                Constant.tfSalary = Constant.company.getSocial();
                Constant.company.setPayable("0");
                Constant.company.setUtilities("0");
                Constant.company.setCash("0");
                Constant.company.setBank("0");
                Constant.company.setReceivable("0");
                Constant.company.setUReceivable("0");
                Constant.company.setTotal("0");

                SaveHQL.insertWorker("update");

            }

            Constant.entity = "WarehouseClass";
            Constant.tfCode = "BD01";
            Constant.tfName = "BODEGA 01";
            SaveHQL.insertWorker("save");

            Constant.entity = entity;
            WareController.alertSend("DATOS BORRADOS CON EXITO");
        }else {
            WareController.alertSend("ERROR AL ELIMINAR LOS DATOS");
        }
    }
}
