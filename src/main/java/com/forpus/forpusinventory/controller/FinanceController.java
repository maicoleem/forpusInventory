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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

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
    public Pane panelBalance;
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
    public Button buttonGenerate;
    public ComboBox<String> cbBoxCuentas;
    @FXML
    private LineChart<String , Number> chartSales;
    @FXML
    public LineChart<String , Number> chartPurchases;
    public BarChart<String, Number> chartProducts;
    public BarChart<String, Number> chartCustomers;
    public DatePicker dateInit;
    public DatePicker dateEnd;
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
        try {
            SearchHQL.invoiceEntity("CustomerClass");
            chartSales.getData().clear();

            List<XYChart.Series<String, Number>> seriesList = graphics();
            for (XYChart.Series<String, Number> series : seriesList) {
                chartSales.getData().add(series);
            }

            chartSales.setTitle("VENTAS");
        } catch (Exception i) {
            i.printStackTrace();
        }
    }
    public void graphicsPurchases() throws ParseException {
        try{
        SearchHQL.invoiceEntity("ProvidersClass");
        chartPurchases.getData().clear();

        List<XYChart.Series<String, Number>> seriesList = graphics();
        for (XYChart.Series<String, Number> series : seriesList) {
            chartPurchases.getData().add(series);
        }
        chartPurchases.setTitle("COMPRAS");
        }catch (Exception i){
            i.printStackTrace();
        }
    }
    public void graphicsProducts(){
        try{
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
        }catch (Exception i){
            i.printStackTrace();
        }
    }
    public void graphicsCustomers(){
        try{
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
        }catch (Exception i){
            i.printStackTrace();
        }
    }
    public List<XYChart.Series<String, Number>> graphics() throws ParseException {
        Map<Integer, Map<Integer, Integer>> yearMonthTotals = new HashMap<>();

        SimpleDateFormat format = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");
        for (InvoiceClass iv : ConstantsPurchases.invoiceList) {
            Date date = iv.getDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int total = Integer.parseInt(iv.getTotal()) - iv.getIdBill();

            // Verificar si ya existe un mapa de totales para el año actual
            Map<Integer, Integer> monthTotals = yearMonthTotals.computeIfAbsent(year, k -> new HashMap<>());

            // Obtener el total actual del mes y sumar el nuevo total
            int currentTotal = monthTotals.getOrDefault(month, 0);
            monthTotals.put(month, currentTotal + total);
        }

        List<XYChart.Series<String, Number>> seriesList = new ArrayList<>();

        for (int year : yearMonthTotals.keySet()) {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName(String.valueOf(year));

            Map<Integer, Integer> monthTotals = yearMonthTotals.get(year);
            series.getData().add(new XYChart.Data<>("ENE", getMonthTotal(monthTotals, 1)));
            series.getData().add(new XYChart.Data<>("FEB", getMonthTotal(monthTotals, 2)));
            series.getData().add(new XYChart.Data<>("MAR", getMonthTotal(monthTotals, 3)));
            series.getData().add(new XYChart.Data<>("ABR", getMonthTotal(monthTotals, 4)));
            series.getData().add(new XYChart.Data<>("MAY", getMonthTotal(monthTotals, 5)));
            series.getData().add(new XYChart.Data<>("JUN", getMonthTotal(monthTotals, 6)));
            series.getData().add(new XYChart.Data<>("JUL", getMonthTotal(monthTotals, 7)));
            series.getData().add(new XYChart.Data<>("AGO", getMonthTotal(monthTotals, 8)));
            series.getData().add(new XYChart.Data<>("SEP", getMonthTotal(monthTotals, 9)));
            series.getData().add(new XYChart.Data<>("OCT", getMonthTotal(monthTotals, 10)));
            series.getData().add(new XYChart.Data<>("NOV", getMonthTotal(monthTotals, 11)));
            series.getData().add(new XYChart.Data<>("DIC", getMonthTotal(monthTotals, 12)));

            seriesList.add(series);
        }

        return seriesList;
    }
    private int getMonthTotal(Map<Integer, Integer> monthTotals, int month) {
        Integer monthTotal = monthTotals.get(month);
        return (monthTotal != null) ? monthTotal : 0;
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
            case "buttonBalance":
                panelBalance.setVisible(a);
                break;
        }
    }
    public void clear(){
        try{
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
        panelBalance.setVisible(a);

        buttonFinances.setStyle("-fx-background-color: #1BA1E2;");
        buttonGraphics.setStyle("-fx-background-color: #1BA1E2;");
        buttonDB.setStyle("-fx-background-color: #1BA1E2;");
        }catch (Exception i){
            i.printStackTrace();
        }
    }
    public void export(ActionEvent event) {
        try{
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
        // Deshabilita el botón si el texto de labelRuta contiene '\' o '/'
        buttonBackUp.setDisable(!labelRuta.getText().contains("\\") && !labelRuta.getText().contains("/"));
        buttonDownload.setDisable(!labelRuta.getText().contains("\\") && !labelRuta.getText().contains("/"));

        }catch (Exception i){
            WareController.alertSend("ERROR AL EXPORTAR DATOS");
            i.printStackTrace();
        }
    }
    public void restore(ActionEvent event) {
        try{
        String entity1 = cbBoxCuentas.getValue();
        String entity2 =cbClass(entity1);
        if(DataBase.importTable(escaparCaracteres(labelRuta2.getText()),entity2)){
            WareController.alertSend("DATOS IMPORTADOS CON EXITO");
            SessionDB.sessionClose();
        }else {
            WareController.alertSend("ERROR AL IMPORTAR LOS DATOS");
        }
        }catch (Exception i){
            i.printStackTrace();
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
        try{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccione el archivo de Excel");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de Excel", "*.xlsx"));

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            String selectedFilePath = selectedFile.getAbsolutePath();
            labelRuta2.setText(selectedFilePath);
        }

        buttonRestore.setDisable(!labelRuta2.getText().contains("xlsx"));
        }catch (Exception i){
            i.printStackTrace();
        }
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

    public void balance(ActionEvent actionEvent) {

        SimpleDateFormat sdfQuery = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate dateStart;
        LocalDate dateFinished;
        dateStart =dateInit.getValue();
        dateFinished = dateEnd.getValue();
        Date init = Date.from(dateStart.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(dateFinished.atStartOfDay(ZoneId.systemDefault()).toInstant());
        ConstantsFinance.searchInvoice(init, end);
    }
}
