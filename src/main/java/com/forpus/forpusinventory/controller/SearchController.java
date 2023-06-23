package com.forpus.forpusinventory.controller;

import com.forpus.forpusinventory.domain.services.Constant;
import com.forpus.forpusinventory.domain.services.ConstantsSearch;
import com.forpus.forpusinventory.domain.services.ConstantsWare;
import com.forpus.forpusinventory.persistence.crud.SearchHQL;
import com.forpus.forpusinventory.persistence.entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import java.util.Objects;

public class SearchController {
    @FXML
    public Pane paneWhite;
    @FXML
    public TableView tableView;
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
    public Button button;
    public ComboBox<String> comboBoxSearch;
    public Label labelCodeSearch;
    public Label labelNameSearch;
    public Label labelCodeSearch2;
    public Label labelNameSearch2;
    public Button buttonCopyName;
    public Button buttonCopyCode;
    public TextField tfFiltrate1;
    public TextField tfFiltrate2;
    public TextField tfFiltrate3;
    public TextField tfFiltrate4;
    public TextField tfFiltrate5;
    public TextField tfFiltrate6;
    public TextField tfFiltrate7;
    @FXML
    public void company(){
        try {
            Constant.entity = "CompanyClass";
            SearchHQL.searchHQL();
            c1.setText("NIT");
            c1.setCellValueFactory(new PropertyValueFactory<>("idCompanyNIT"));
            c2.setText("Company");
            c2.setCellValueFactory(new PropertyValueFactory<>("Name"));
            c3.setText("Password");
            c3.setCellValueFactory(new PropertyValueFactory<>("Password"));
            c4.setText("Address");
            c4.setCellValueFactory(new PropertyValueFactory<>("Addres"));
            c5.setText("Phone");
            c5.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
            c6.setText("Web");
            c6.setCellValueFactory(new PropertyValueFactory<>("Web"));
            c7.setText("Instagram");
            c7.setCellValueFactory(new PropertyValueFactory<>("Social"));

            ObservableList<CompanyClass> dates = FXCollections.observableArrayList(Constant.companiesList);
            tableView.setItems(dates);
        }catch (Exception i){
            i.printStackTrace();
            WareController.alertSend("ERROR AL CARGAR LOS DATOS DE LA COMPAÑIA");
        }
    }
    public void worker(){
        try {
            Constant.entity = "WorkersClass";
            SearchHQL.searchHQL();
            c1.setText("Cedula");
            c1.setCellValueFactory(new PropertyValueFactory<>("IdentificationCard"));
            c2.setText("Nombre");
            c2.setCellValueFactory(new PropertyValueFactory<>("Name"));
            c3.setText("Cargo");
            c3.setCellValueFactory(new PropertyValueFactory<>("Job"));
            c4.setText("Salario");
            c4.setCellValueFactory(new PropertyValueFactory<>("Wage"));
            c5.setText("Phone");
            c5.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
            c6.setText("Dirección");
            c6.setCellValueFactory(new PropertyValueFactory<>("Address"));

            ObservableList<WorkersClass> dates = FXCollections.observableArrayList(Constant.workersList);
            tableView.setItems(dates);
        }catch (Exception i){
            i.printStackTrace();
            WareController.alertSend("ERROR AL CARGAR LOS DATOS DE LOS TRABAJADORES");
        }
    }
    public void partner(){
        try {
            Constant.entity = "PartnersClass";
            SearchHQL.searchHQL();
            c1.setText("Cedula");
            c1.setCellValueFactory(new PropertyValueFactory<>("IdentificationCard"));
            c2.setText("Nombre");
            c2.setCellValueFactory(new PropertyValueFactory<>("Name"));
            c3.setText("Telefono");
            c3.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
            c4.setText("Dirección");
            c4.setCellValueFactory(new PropertyValueFactory<>("Address"));
            c5.setText("C5");
            c6.setText("C6");
            c7.setText("C7");

            ObservableList<PartnersClass> dates = FXCollections.observableArrayList(Constant.partnersList);
            tableView.setItems(dates);
        }catch (Exception i){
            i.printStackTrace();
            WareController.alertSend("ERROR AL CARGAR LOS DATOS DE LOS SOCIOS");
        }
    }
    public void providers(){
        try {
            Constant.entity = "ProvidersClass";
            SearchHQL.searchHQL();
            c1.setText("NIT");
            c1.setCellValueFactory(new PropertyValueFactory<>("Nit"));
            c2.setText("Nombre");
            c2.setCellValueFactory(new PropertyValueFactory<>("Name"));
            c3.setText("Telefono");
            c3.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
            c4.setText("Dirección");
            c4.setCellValueFactory(new PropertyValueFactory<>("Address"));
            c5.setText("Email");
            c5.setCellValueFactory(new PropertyValueFactory<>("Email"));
            c6.setText("C6");
            c7.setText("C7");
            ObservableList<ProvidersClass> dates = FXCollections.observableArrayList(Constant.providersList);
            tableView.setItems(dates);
        }catch (Exception i){
            i.printStackTrace();
            WareController.alertSend("ERROR AL CARGAR LOS DATOS DE LOS PROVEEDORES");
        }
    }
    public void customer(){
        try {
        Constant.entity = "CustomerClass";
        SearchHQL.searchHQL();
        c1.setText("Cedula");
        c1.setCellValueFactory(new PropertyValueFactory<>("idCustomer"));
        c2.setText("Nombre");
        c2.setCellValueFactory(new PropertyValueFactory<>("Name"));
        c3.setText("Telefono");
        c3.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
        c4.setText("Dirección");
        c4.setCellValueFactory(new PropertyValueFactory<>("Addres"));
        c5.setText("C5");
        c6.setText("C6");
        c7.setText("C7");
        ObservableList<CustomerClass> dates = FXCollections.observableArrayList(Constant.customersList);
        tableView.setItems(dates);
        }catch (Exception i){
            i.printStackTrace();
            WareController.alertSend("ERROR AL CARGAR LOS DATOS DE LOS CLIENTES");
        }
    }
    public void product(){
        try {
            Constant.entity = "ProductClass";
            SearchHQL.searchHQL();
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
            c6.setText("P. Compra");
            c6.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));
            c7.setText("P. Venta");
            c7.setCellValueFactory(new PropertyValueFactory<>("salePrice"));
            ObservableList<ProductClass> datesProduct = FXCollections.observableArrayList(ConstantsWare.productList);
            tableView.setItems(datesProduct);
        }catch (Exception i){
            i.printStackTrace();
            WareController.alertSend("ERROR AL CARGAR LOS DATOS DE LOS PRODUCTOS");
        }
    }
    public void service(){
        try {
            Constant.entity = "ServiceClass";
            SearchHQL.searchHQL();
            c1.setText("Codigo");
            c1.setCellValueFactory(new PropertyValueFactory<>("idService"));
            c2.setText("Servicio");
            c2.setCellValueFactory(new PropertyValueFactory<>("name"));
            c3.setText("Precio");
            c3.setCellValueFactory(new PropertyValueFactory<>("cost"));
            c4.setText("Bodega");
            c4.setCellValueFactory(new PropertyValueFactory<>("idWare"));
            c5.setText("Costo Hora");
            c5.setCellValueFactory(new PropertyValueFactory<>("payForHour"));
            c6.setText("Horas");
            c6.setCellValueFactory(new PropertyValueFactory<>("hour"));
            c7.setText("Profit");
            c7.setCellValueFactory(new PropertyValueFactory<>("profit"));
            ObservableList<ServiceClass> datesService = FXCollections.observableArrayList(ConstantsWare.serviceList);
            tableView.setItems(datesService);
        }catch (Exception i){
            i.printStackTrace();
            WareController.alertSend("ERROR AL CARGAR LOS DATOS DE LOS SERVICIOS");
        }
    }
    @FXML
    public void comboBoxLoad(){
        try {
            comboBoxSearch.getItems().clear();
            String[] typesAccounting = {"Compañia", "Cliente", "Socio", "Proveedor", "Trabajador", "Producto", "Servicio"};
            comboBoxSearch.getItems().addAll(typesAccounting);
        }catch (Exception i){
            i.printStackTrace();
            WareController.alertSend("ERROR AL CARGAR DATOS EN LA LISTA");
        }
    }
    public void comboBoxClick(ActionEvent event) {
        try {
            ComboBox comboBox = (ComboBox) event.getSource();
            tableNull();
            switch (comboBox.getValue().toString()) {
                case "Compañia":
                    company();
                    ConstantsSearch.classTable = "CompanyClass";
                    break;
                case "Cliente":
                    customer();
                    ConstantsSearch.classTable = "CustomerClass";
                    break;
                case "Socio":
                    partner();
                    ConstantsSearch.classTable = "PartnersClass";
                    break;
                case "Proveedor":
                    providers();
                    ConstantsSearch.classTable = "ProvidersClass";
                    break;
                case "Trabajador":
                    worker();
                    ConstantsSearch.classTable = "WorkersClass";
                    break;
                case "Producto":
                    product();
                    ConstantsSearch.classTable = "ProductClass";
                    break;
                case "Servicio":
                    service();
                    ConstantsSearch.classTable = "ServiceClass";
                    break;
                default:
                    break;
            }
            ConstantsSearch.listTable = tableView.getItems();
        }catch (Exception i){
            i.printStackTrace();
            WareController.alertSend("ERROR AL CARGAR DATOS");
        }
    }
    public void tableSelect(MouseEvent mouseEvent) {
        try {
            Object tShow = tableView.getSelectionModel().getSelectedItem();
            String name = "Nombre:";
            String code = "Codigo";
            switch (tShow.getClass().toString()) {
                case "class com.forpus.forpusinventory.persistence.entity.CustomerClass":
                    CustomerClass customer = (CustomerClass) tShow;
                    name = customer.getName();
                    code = customer.getIdCustomer();
                    break;
                case "class com.forpus.forpusinventory.persistence.entity.CompanyClass":
                    CompanyClass company = (CompanyClass) tShow;
                    name = company.getName();
                    code = company.getIdCompanyNIT();
                    break;
                case "class com.forpus.forpusinventory.persistence.entity.PartnersClass":
                    PartnersClass partner = (PartnersClass) tShow;
                    name = partner.getName();
                    code = partner.getIdentificationCard();
                    break;
                case "class com.forpus.forpusinventory.persistence.entity.ProvidersClass":
                    ProvidersClass provider = (ProvidersClass) tShow;
                    name = provider.getName();
                    code = provider.getNit();
                    break;
                case "class com.forpus.forpusinventory.persistence.entity.WorkersClass":
                    WorkersClass worker = (WorkersClass) tShow;
                    name = worker.getName();
                    code = worker.getIdentificationCard();
                    break;
                case "class com.forpus.forpusinventory.persistence.entity.ProductClass":
                    ProductClass product = (ProductClass) tShow;
                    name = product.getName();
                    code = product.getIdProduct();
                    break;
                case "class com.forpus.forpusinventory.persistence.entity.ServiceClass":
                    ServiceClass service = (ServiceClass) tShow;
                    name = service.getName();
                    code = service.getIdService();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + tShow.getClass());
            }
            labelCodeSearch2.setText(code);
            labelNameSearch2.setText(name);
        }catch (Exception i){
            i.printStackTrace();
            WareController.alertSend("ERROR AL SELECCIONAR DATOS");
        }
    }
    public void tableNull(){
        try {
            c1.setCellValueFactory(null);
            c2.setCellValueFactory(null);
            c3.setCellValueFactory(null);
            c4.setCellValueFactory(null);
            c5.setCellValueFactory(null);
            c6.setCellValueFactory(null);
            c7.setCellValueFactory(null);
        }catch (Exception i){
            i.printStackTrace();
            WareController.alertSend("ERROR AL LIMPIAR LA TABLA");
        }
    }
    @FXML
    public void initialize() {
        comboBoxLoad();
        customer();
        ConstantsSearch.classTable = "CustomerClass";
    }
    public void cpString(ActionEvent event) {
        try {
            Button button = (Button) event.getSource();
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();

            if (Objects.equals(button.getId(), "buttonCopyCode")) {
                content.putString(labelCodeSearch2.getText());
            } else {
                content.putString(labelNameSearch2.getText());
            }
            clipboard.setContent(content);
        }catch (Exception i){
            i.printStackTrace();
            WareController.alertSend("ERROR AL COPIAR LOS DATOS");
        }
    }
    public void filterTable(KeyEvent keyEvent) {
        try {
            TextField tfID = (TextField) keyEvent.getSource();
            System.out.println(tfID.getId());
            switch (ConstantsSearch.classTable) {
                case "CustomerClass":
                    ObservableList<CustomerClass> listOne = ConstantsSearch.listTable;
                    FilteredList<CustomerClass> filteredCode = new FilteredList<CustomerClass>(listOne, s -> s.getIdCustomer().contains(tfFiltrate1.getText()) && s.getName().contains(tfFiltrate2.getText()) && s.getPhoneNumber().contains(tfFiltrate3.getText()) && s.getAddres().contains(tfFiltrate4.getText()));
                    tableView.setItems(filteredCode);
                    break;

                case "CompanyClass":
                    ObservableList<CompanyClass> listCompany = ConstantsSearch.listTable;
                    FilteredList<CompanyClass> filteredListCompany = new FilteredList<>(listCompany, s -> s.getIdCompanyNIT().contains(tfFiltrate1.getText()) && s.getName().contains(tfFiltrate2.getText()) && s.getPassword().contains(tfFiltrate3.getText()) && s.getAddres().contains(tfFiltrate4.getText()) && s.getPhoneNumber().contains(tfFiltrate5.getText()) && s.getWeb().contains(tfFiltrate6.getText()) && s.getSocial().contains(tfFiltrate7.getText()));
                    tableView.setItems(filteredListCompany);
                    break;
                case "PartnersClass":
                    ObservableList<PartnersClass> listPartners = ConstantsSearch.listTable;
                    FilteredList<PartnersClass> filteredListPartner = new FilteredList<PartnersClass>(listPartners, s -> s.getIdentificationCard().contains(tfFiltrate1.getText()) && s.getName().contains(tfFiltrate2.getText()) && s.getPhoneNumber().contains(tfFiltrate3.getText()) && s.getAddress().contains(tfFiltrate4.getText()));
                    tableView.setItems(filteredListPartner);
                    break;
                case "ProvidersClass":
                    ObservableList<ProvidersClass> listProviders = ConstantsSearch.listTable;
                    FilteredList<ProvidersClass> filteredListProviders = new FilteredList<>(listProviders, s -> s.getNit().contains(tfFiltrate1.getText()) && s.getName().contains(tfFiltrate2.getText()) && s.getPhoneNumber().contains(tfFiltrate3.getText()) & s.getAddress().contains(tfFiltrate4.getText()) && s.getEmail().contains(tfFiltrate5.getText()));
                    tableView.setItems(filteredListProviders);
                    break;
                case "WorkersClass":
                    ObservableList<WorkersClass> listWorkers = ConstantsSearch.listTable;
                    FilteredList<WorkersClass> filteredListWorkers = new FilteredList<>(listWorkers, s -> s.getIdentificationCard().contains(tfFiltrate1.getText()) && s.getName().contains(tfFiltrate2.getText()) && s.getJob().contains(tfFiltrate3.getText()) && s.getWage().contains(tfFiltrate4.getText()) && s.getPhoneNumber().contains(tfFiltrate5.getText()) && s.getAddress().contains(tfFiltrate7.getText()));
                    tableView.setItems(filteredListWorkers);
                    break;
                case "ProductClass":
                    ObservableList<ProductClass> listProduct = ConstantsSearch.listTable;
                    System.out.println(ConstantsSearch.listTable);
                    FilteredList<ProductClass> filteredListProduct = new FilteredList<ProductClass>(listProduct, s -> s.getIdProduct().contains(tfFiltrate1.getText()) && s.getName().contains(tfFiltrate2.getText()) && s.getPurchasePrice().contains(tfFiltrate6.getText()) && s.getSalePrice().contains(tfFiltrate7.getText()));
                    tableView.setItems(filteredListProduct);
                    break;
                case "ServiceClass":
                    ObservableList<ServiceClass> listService = ConstantsSearch.listTable;
                    System.out.println(ConstantsSearch.listTable);
                    FilteredList<ServiceClass> filteredListService = new FilteredList<ServiceClass>(listService, s -> s.getIdService().contains(tfFiltrate1.getText()) && s.getName().contains(tfFiltrate2.getText()) && s.getCost().contains(tfFiltrate3.getText()) && s.getIdWare().contains(tfFiltrate4.getText()) && s.getPayForHour().contains(tfFiltrate5.getText()) && s.getHour().contains(tfFiltrate6.getText()) && s.getProfit().contains(tfFiltrate7.getText()));
                    tableView.setItems(filteredListService);
                    break;
                default:
                    break;
            }
        }catch (Exception i){
            i.printStackTrace();
            WareController.alertSend("ERROR AL FILTRAR DATOS");
        }
    }
}
