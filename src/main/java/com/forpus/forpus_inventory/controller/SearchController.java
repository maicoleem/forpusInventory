package com.forpus.forpus_inventory.controller;

import com.forpus.forpus_inventory.domain.services.Constant;
import com.forpus.forpus_inventory.persistence.crud.SearchHQL;
import com.forpus.forpus_inventory.persistence.entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

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
        c7.setText("Instagram");
        c7.setCellValueFactory(new PropertyValueFactory<>("Social"));

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
        c3.setCellValueFactory(new PropertyValueFactory<>("Phone"));
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
        c4.setCellValueFactory(new PropertyValueFactory<>("Addres"));
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


}
