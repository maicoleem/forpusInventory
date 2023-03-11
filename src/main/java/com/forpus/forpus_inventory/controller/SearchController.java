package com.forpus.forpus_inventory.controller;

import com.forpus.forpus_inventory.domain.services.Constant;
import com.forpus.forpus_inventory.domain.services.TableShow;
import com.forpus.forpus_inventory.persistence.crud.SearchHQL;
import com.forpus.forpus_inventory.persistence.entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.hibernate.jdbc.WorkExecutor;

import javax.persistence.Table;
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

    @FXML
    public void company(){
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
    }
    public void worker(){
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
    }
    public void partner(){
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
    }
    public void providers(){
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
    }
    public void customer(){

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
    }
    @FXML
    public void comboBoxLoad(){
        comboBoxSearch.getItems().clear();
        String [] typesAccounting = {"Compañia", "Cliente", "Socio", "Proveedor", "Trabajador"};
        comboBoxSearch.getItems().addAll(typesAccounting);
    }
    public void comboBoxClick(ActionEvent event) {
        ComboBox comboBox = (ComboBox) event.getSource();
        tableNull();
        switch (comboBox.getValue().toString()){
            case "Compañia":
                company();
                break;
            case "Cliente":
                customer();
                break;
            case "Socio":
                partner();
                break;
            case "Proveedor":
                providers();
                break;
            case "Trabajador":
                worker();
                break;
            default:
                break;
        }
    }
    public void tableSelect(MouseEvent mouseEvent) {
        Object tShow = tableView.getSelectionModel().getSelectedItem();

        String name = "Nombre:";
        String code = "Codigo";

        switch (tShow.getClass().toString()){
            case "class com.forpus.forpus_inventory.persistence.entity.CustomerClass" :
                CustomerClass customer = (CustomerClass) tShow;
                name = customer.getName();
                code = customer.getIdCustomer();
                break;
            case "class com.forpus.forpus_inventory.persistence.entity.CompanyClass":
                CompanyClass company = (CompanyClass) tShow;
                name = company.getName();
                code = company.getIdCompanyNIT();
                break;
            case "class com.forpus.forpus_inventory.persistence.entity.PartnersClass":
                PartnersClass partner = (PartnersClass) tShow;
                name = partner.getName();
                code = partner.getIdentificationCard();
                break;
            case "class com.forpus.forpus_inventory.persistence.entity.ProvidersClass":
                ProvidersClass provider = (ProvidersClass) tShow;
                name = provider.getName();
                code = provider.getNit();
                break;
            case "class com.forpus.forpus_inventory.persistence.entity.WorkersClass":
                WorkersClass worker = (WorkersClass) tShow;
                name = worker.getName();
                code = worker.getIdentificationCard();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + tShow.getClass());
        }

        labelCodeSearch2.setText(code);
        labelNameSearch2.setText(name);

    }
    public void tableNull(){
        c1.setCellValueFactory(null);
        c2.setCellValueFactory(null);
        c3.setCellValueFactory(null);
        c4.setCellValueFactory(null);
        c5.setCellValueFactory(null);
        c6.setCellValueFactory(null);
        c7.setCellValueFactory(null);
    }
    @FXML
    public void initialize() {
        comboBoxLoad();
    }


    public void cpString(ActionEvent event) {
        Button button = (Button) event.getSource();
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();

        if(Objects.equals(button.getId(), "buttonCopyCode")){
            content.putString(labelCodeSearch2.getText());
        }else{
            content.putString(labelNameSearch2.getText());
        }
        clipboard.setContent(content);
    }
}
