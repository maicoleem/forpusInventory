package com.forpus.forpusinventory.controller;

import com.forpus.forpusinventory.domain.services.Constant;
import com.forpus.forpusinventory.persistence.crud.DeleteHQL;
import com.forpus.forpusinventory.persistence.crud.FoundHQL;
import com.forpus.forpusinventory.persistence.crud.SaveHQL;
import com.forpus.forpusinventory.persistence.crud.SearchHQL;
import com.forpus.forpusinventory.persistence.entity.CompanyClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SettingController {
    public Button buttonPartners;
    public Button buttonWorkers;
    public Button buttonCompany;
    public Button buttonCustomers;
    public Button buttonProviders;
    public Button buttonAccount;
    @FXML
    public Label labelIdentificationCard;
    @FXML
    public Label labelName;
    @FXML
    public Label labelPhone;
    @FXML
    public Label labelAddress;
    @FXML
    public Label labelJob;
    @FXML
    public Label labelSalary;
    @FXML
    public Label labelPassword;
    @FXML
    public TextField textFieldIdentificationCard;
    @FXML
    public TextField textFieldName;
    @FXML
    public TextField textFieldPhone;
    @FXML
    public TextField textFieldSalary;
    @FXML
    public TextField textFieldAddress;
    @FXML
    public TextField textFieldJob;
    @FXML
    public TextField textFieldPassword;
    @FXML
    public Button save;
    @FXML
    public Button remove;
    @FXML
    public Button search;
    @FXML
    public Button cancel;
    @FXML
    public Button found;
    public Button buttonSingOut;
    public Button bWare;
    public Button bDash;
    public Button bAccounting;
    public Button bBuy;
    public Button bSale;
    public Button bSettings;
    @FXML
    Pane paneWhite;
    @FXML
    Button buttonPartner;
    @FXML
    protected void buttonOptions(ActionEvent event){
        changeLabelsText(Constant.changeOptions(event));
    }
    @FXML
    protected void buttonCRUD(ActionEvent event){
        String receiver = Constant.buttonCrudChangeColor(event);
        if(!receiver.equals("error")){
            crudEjecuted(receiver);
        }else {
            WareController.alertSend("Error Ejecutando Crud");
        }
    }
    @FXML
    protected void buttonSlide(ActionEvent event) throws IOException {
        WareController.slide(event);

    }
    public void changeLabelsText(String idButton){
        try{
        Constant.buttonOptionsID = idButton;
        changeOptions();
        switch (idButton){
            case "buttonCompany":
                Constant.entity = "CompanyClass";
                company("CompanyClass");

                textFieldIdentificationCard.setText(Constant.company.getIdCompanyNIT());
                textFieldIdentificationCard.setDisable(true);
                textFieldName.setText(Constant.company.getName());
                textFieldPhone.setText(Constant.company.getPhoneNumber());
                textFieldAddress.setText(Constant.company.getAddres());
                textFieldJob.setText(Constant.company.getWeb());
                textFieldSalary.setText(Constant.company.getSocial());

                //labels
                labelJob.setText(Constant.lblWeb);
                labelSalary.setText(Constant.lblInstagram);
                labelIdentificationCard.setText(Constant.lblNIT);

                labelIdentificationCard.setVisible(true);
                labelName.setVisible(true);
                labelPhone.setVisible(true);
                labelAddress.setVisible(true);
                labelJob.setVisible(true);
                labelSalary.setVisible(true);
                labelPassword.setText(Constant.lblPassword);
                labelPassword.setVisible(false);

                //textFields
                textFieldIdentificationCard.setVisible(true);
                textFieldName.setVisible(true);
                textFieldPhone.setVisible(true);
                textFieldAddress.setVisible(true);
                textFieldJob.setVisible(true);
                textFieldSalary.setVisible(true);
                textFieldPassword.setVisible(false);

                //buttons
                save.setVisible(Constant.isAdmin);
                save.setDisable(!Constant.isAdmin);
                search.setVisible(Constant.isAdmin);
                cancel.setVisible(Constant.isAdmin);

                found.setVisible(false);
                remove.setVisible(false);

                break;
            case "buttonPartners":
            case "buttonCustomers":
                if(idButton.equals("buttonPartners")){
                    Constant.entity ="PartnersClass";
                }else{
                    Constant.entity ="CustomerClass";
                }

                //labels
                labelIdentificationCard.setText(Constant.lblIdentificationCard);
                labelIdentificationCard.setVisible(true);

                labelName.setText(Constant.lblName);
                labelName.setVisible(true);

                labelPhone.setText(Constant.lblPhone);
                labelPhone.setVisible(true);

                labelAddress.setText(Constant.lblAddress);
                labelAddress.setVisible(true);

                labelJob.setText(Constant.lblWeb);
                labelJob.setVisible(false);

                labelSalary.setText(Constant.lblInstagram);
                labelSalary.setVisible(false);

                labelPassword.setText(Constant.lblPassword);
                labelPassword.setVisible(false);
                //textFields
                textFieldIdentificationCard.setVisible(true);
                textFieldName.setVisible(true);
                textFieldPhone.setVisible(true);
                textFieldAddress.setVisible(true);
                textFieldJob.setVisible(false);
                textFieldSalary.setVisible(false);
                textFieldPassword.setVisible(false);

                //butonsAdmin
                save.setVisible(true);
                save.setDisable(true);
                search.setVisible(true);
                found.setVisible(true);
                remove.setVisible(Constant.isAdmin);
                remove.setDisable(true);
                cancel.setVisible(true);

                break;
            case "buttonWorkers":
                Constant.entity ="WorkersClass";
                labelIdentificationCard.setText(Constant.lblIdentificationCard);
                labelIdentificationCard.setVisible(true);
                labelName.setText(Constant.lblName);
                labelName.setVisible(true);
                labelPhone.setText(Constant.lblPhone);
                labelPhone.setVisible(true);
                labelAddress.setText(Constant.lblAddress);
                labelAddress.setVisible(true);
                labelJob.setText(Constant.lblJob);
                labelJob.setVisible(true);
                labelSalary.setText(Constant.lblSalary);
                labelSalary.setVisible(true);
                labelPassword.setText(Constant.lblPassword);
                labelPassword.setVisible(true);

                textFieldIdentificationCard.setVisible(true);
                textFieldName.setVisible(true);
                textFieldPhone.setVisible(true);
                textFieldAddress.setVisible(true);
                textFieldJob.setVisible(true);
                textFieldSalary.setVisible(true);
                textFieldPassword.setVisible(true);

                //butonsAdmin
                save.setVisible(Constant.isAdmin);
                save.setDisable(true);
                search.setVisible(Constant.isAdmin);
                found.setVisible(Constant.isAdmin);
                remove.setVisible(Constant.isAdmin);
                remove.setDisable(true);
                cancel.setVisible(Constant.isAdmin);

                break;

            //textFields
            case "buttonProviders":
                Constant.entity ="ProvidersClass";
                //labels
                labelIdentificationCard.setText(Constant.lblNIT);
                labelIdentificationCard.setVisible(true);

                labelName.setText(Constant.lblName);
                labelName.setVisible(true);

                labelPhone.setText(Constant.lblPhone);
                labelPhone.setVisible(true);

                labelAddress.setText(Constant.lblAddress);
                labelAddress.setVisible(true);

                labelJob.setText(Constant.lblEmail);
                labelJob.setVisible(true);

                labelSalary.setText(Constant.lblInstagram);
                labelSalary.setVisible(false);

                labelPassword.setText(Constant.lblPassword);
                labelPassword.setVisible(false);
                //textFields
                textFieldIdentificationCard.setVisible(true);
                textFieldName.setVisible(true);
                textFieldPhone.setVisible(true);
                textFieldAddress.setVisible(true);
                textFieldJob.setVisible(true);
                textFieldSalary.setVisible(false);
                textFieldPassword.setVisible(false);

                //butonsAdmin
                save.setVisible(true);
                save.setDisable(true);
                search.setVisible(true);
                found.setVisible(true);
                remove.setVisible(Constant.isAdmin);
                remove.setDisable(true);
                cancel.setVisible(true);

                break;
            case "buttonAccount":

                //labels
                labelIdentificationCard.setText(Constant.lblIdentificationCard);
                labelIdentificationCard.setVisible(true);

                labelName.setText(Constant.lblName);
                labelName.setVisible(true);

                labelPhone.setText(Constant.lblPhone);
                labelPhone.setVisible(false);

                labelAddress.setText(Constant.lblAddress);
                labelAddress.setVisible(false);

                labelJob.setText(Constant.lblWeb);
                labelJob.setVisible(false);

                labelSalary.setText(Constant.lblInstagram);
                labelSalary.setVisible(false);

                labelPassword.setText(Constant.lblPassword);
                labelPassword.setVisible(false);

                //textFields
                textFieldIdentificationCard.setVisible(true);
                textFieldName.setVisible(true);
                textFieldPhone.setVisible(false);
                textFieldAddress.setVisible(false);
                textFieldJob.setVisible(false);
                textFieldSalary.setVisible(false);
                textFieldPassword.setVisible(false);

                if (Constant.workerLogin != null) {
                    textFieldIdentificationCard.setText(Constant.workerLogin.getIdentificationCard());
                    textFieldName.setText(Constant.workerLogin.getName());
                }else {
                    textFieldIdentificationCard.setText(Constant.company.getIdCompanyNIT());
                    textFieldName.setText(Constant.company.getName());
                }

                //buttons
                save.setVisible(false);
                remove.setVisible(false);
                found.setVisible(false);
                search.setVisible(false);
                cancel.setVisible(false);

                buttonSingOut.setVisible(true);

                break;
            default:
                System.out.println("ERROR");
                break;
        }
        }catch (Exception i){
            i.printStackTrace();
            WareController.alertSend("ERROR EN CARGAR DATOS");
        }
    }
    public void crudEjecuted(String idButton){
        try{
        switch (idButton){
            case "save":
                Constant.tfCode = textFieldIdentificationCard.getText();
                if(!Objects.equals(Constant.tfCode, "")){
                    Constant.tfName = textFieldName.getText();
                    Constant.tfPhone = textFieldPhone.getText();
                    Constant.tfAddress = textFieldAddress.getText();
                    Constant.tfJob = textFieldJob.getText();
                    Constant.tfSalary = textFieldSalary.getText();
                    Constant.tfPassword = textFieldPassword.getText();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Freya Style--//--Forpus Company");

                    if(SaveHQL.workerInsertUpdate()){
                        if(Objects.equals(Constant.messageSave, "Creado")){
                            alert.setContentText("Se han guardado los datos");
                        }else{
                            alert.setContentText("Se han actualizado los datos");
                        }
                    }else {
                        alert.setContentText("Error en actualizar y/o insertar datos, por favor reiniciar");
                    }
                    alert.show();
                }else{
                    alertSend("Por favor digitar código");
                }
                changeOptions();
                break;
            case "search":
                    SalesController.searchCrud();
                break;
            case "remove":

                Constant.tfCode = textFieldIdentificationCard.getText();

                Alert alertDelete = new Alert(Alert.AlertType.INFORMATION);

                alertDelete.setTitle("Freya Style--//--Forpus Company");

                if(DeleteHQL.workerDelete()){
                    alertDelete.setContentText("Datos Eliminados");
                }else {
                    alertDelete.setContentText("Error al eliminar datos");
                }
                alertDelete.show();
                changeOptions();
                break;
            case "cancel":
                //textFields
                changeOptions();
                break;
            case "found":
                Constant.tfCode = textFieldIdentificationCard.getText();
                    if(!Objects.equals(Constant.tfCode, "")){
                        Alert alertSearch = new Alert(Alert.AlertType.INFORMATION);
                        alertSearch.setTitle("Freya Style--//--Forpus Company");
                        if(FoundHQL.workerFound()){
                            textFieldName.setText(Constant.tfName);
                            textFieldPhone.setText(Constant.tfPhone);
                            textFieldAddress.setText(Constant.tfAddress);
                            textFieldJob.setText(Constant.tfJob);
                            textFieldSalary.setText(Constant.tfSalary);
                            textFieldPassword.setText(Constant.tfPassword);
                        }else{
                            alertSearch.setContentText("Daton no encontrados");
                            alertSearch.show();
                        }
                        save.setDisable(false);
                        remove.setDisable(false);
                        textFieldIdentificationCard.setDisable(true);
                    }else{
                        alertSend("Por favor digitar código");
                    }
                break;
            default:
                break;
        }
        }catch (Exception i){
            i.printStackTrace();
            WareController.alertSend("ERROR CRUD");
        }
    }
    public void changeOptions(){
        try{
        textFieldIdentificationCard.setDisable(false);
        remove.setVisible(Constant.isAdmin);
        remove.setDisable(true);
        remove.setStyle("-fx-background-color: #1BA1E2; ");
        save.setStyle("-fx-background-color: #1BA1E2; ");
        save.setDisable(true);
        found.setStyle("-fx-background-color: #1BA1E2; ");
        cancel.setStyle("-fx-background-color: #1BA1E2; ");
        search.setStyle("-fx-background-color: #1BA1E2; ");
        buttonSingOut.setVisible(false);
        textFieldIdentificationCard.setText("");
        textFieldName.setText("");
        textFieldPhone.setText("");
        textFieldAddress.setText("");
        textFieldJob.setText("");
        textFieldSalary.setText("");
        textFieldPassword.setText("");
        }catch (Exception i){
            i.printStackTrace();
            WareController.alertSend("ERROR AL LIMPIAR FORMATO");
        }
    }
    public void alertSend(String massage){
        Alert alertMassage = new Alert(Alert.AlertType.INFORMATION);
        alertMassage.setTitle("Freya Style--//--Forpus Company");
        alertMassage.setContentText(massage);
        alertMassage.show();
    }
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonWorkers.setVisible(Constant.isAdmin);
        buttonPartners.setVisible(Constant.isAdmin);

        bSettings.setDisable(false);
        bSale.setDisable(false);
        bBuy.setDisable(false);
        bAccounting.setDisable(!Constant.isAdmin);
        bDash.setDisable(!Constant.isAdmin);
        bWare.setDisable(!Constant.isAdmin);
    }
    //found the company
    public static CompanyClass company(String entityClassNotChange){
        try {
            Constant.entity = "CompanyClass";
            SearchHQL.searchHQL();
            Constant.company = Constant.companiesList[0];
        }catch (Exception i) {
            i.printStackTrace();
            WareController.alertSend("ERROR AL CARGAR DATOS DE LA EMPRESA");
        }
        return Constant.company;
    }

}
