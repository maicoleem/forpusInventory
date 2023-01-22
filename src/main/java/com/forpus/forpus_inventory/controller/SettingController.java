package com.forpus.forpus_inventory.controller;

import com.forpus.forpus_inventory.HelloApplication;
import com.forpus.forpus_inventory.domain.services.Constant;
import com.forpus.forpus_inventory.persistence.crud.DeleteHQL;
import com.forpus.forpus_inventory.persistence.crud.FoundHQL;
import com.forpus.forpus_inventory.persistence.crud.SaveHQL;
import com.forpus.forpus_inventory.persistence.crud.SearchHQL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;

public class SettingController implements Initializable {
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
    Pane paneWhite;
    @FXML
    Button buttonPartner;
    @FXML
    protected void buttonOptions(ActionEvent event){
        Button button = (Button) event.getSource();

        if(Constant.greyToBlue == null){
            Constant.greyToBlue = button;
            button.setStyle("-fx-background-color: #1BA1E2; ");
        } else if (Constant.greyToBlue == button) {
            button.setStyle("-fx-background-color: #1BA1E2; ");
        }else{
            Constant.greyToBlue.setStyle("-fx-background-color: #C2C2C2; ");
            button.setStyle("-fx-background-color: #1BA1E2; ");
            Constant.greyToBlue = button;
        }
        changeLabelsText(button.getId());
    }
    @FXML
    protected void buttonCRUD(ActionEvent event){
        Button buttonCRUD = (Button) event.getSource();

        if(Constant.blueToWhite == null){
            Constant.blueToWhite = buttonCRUD;
            buttonCRUD.setStyle("-fx-background-color: #F5F5F5; ");
        } else if (Constant.blueToWhite == buttonCRUD) {
            buttonCRUD.setStyle("-fx-background-color: #F5F5F5; ");
        }else{
            Constant.blueToWhite.setStyle("-fx-background-color: #1BA1E2; ");
            buttonCRUD.setStyle("-fx-background-color: #F5F5F5; ");
            Constant.blueToWhite = buttonCRUD;
        }
        crudEjecuted(buttonCRUD.getId());
    }

    @FXML
    protected void buttonSlide(ActionEvent event){
        Button buttonSlide = (Button) event.getSource();

        if(Constant.greyToBlueSlide == null){
            Constant.greyToBlueSlide = buttonSlide;
            buttonSlide.setStyle("-fx-background-color: #F5F5F5; ");
        } else if (Constant.greyToBlueSlide == buttonSlide) {
            buttonSlide.setStyle("-fx-background-color: #F5F5F5; ");
        }else{
            Constant.greyToBlueSlide.setStyle("-fx-background-color: #C2C2C2; ");
            buttonSlide.setStyle("-fx-background-color: #F5F5F5; ");
            Constant.greyToBlueSlide = buttonSlide;
        }

    }

    public void changeLabelsText(String idButton){
        Constant.buttonOptionsID = idButton;
        switch (idButton){
            case "buttonCompany":
                Constant.entity = "CompanyClass";
                //labels
                labelIdentificationCard.setText(Constant.lblNIT);
                labelIdentificationCard.setVisible(true);

                labelName.setText(Constant.lblCompany);
                labelName.setVisible(true);

                labelPhone.setText(Constant.lblPhone);
                labelPhone.setVisible(true);

                labelAddress.setText(Constant.lblAddress);
                labelAddress.setVisible(true);

                labelJob.setText(Constant.lblWeb);
                labelJob.setVisible(true);

                labelSalary.setText(Constant.lblInstagram);
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

                break;
            case "buttonPartners":
            case "buttonCustomers":
                if(idButton=="buttonPartners"){
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
                break;
            default:
                System.out.println("ERROR");
                break;
        }
    }

    public void crudEjecuted(String idButton){

        switch (idButton){
            case "save":
                Constant.tfCode = textFieldIdentificationCard.getText();
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
                break;
            case "search":
                if(SearchHQL.searchHQL()){
                    try{
                        FXMLLoader search = new FXMLLoader(HelloApplication.class.getResource("search-view.fxml"));
                        Parent root = search.load();
                        Scene sceneSearch = new Scene(root);
                        Stage stage = new Stage();

                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setScene(sceneSearch);

                        stage.showAndWait();


                    }catch (Exception e){
                        System.out.println(e);
                    }


                }else{

                }

                /*

                Constant.companiesList;*/


                break;
            case "deleted":
                Constant.tfCode = textFieldIdentificationCard.getText();

                Alert alertDelete = new Alert(Alert.AlertType.INFORMATION);

                alertDelete.setTitle("Freya Style--//--Forpus Company");

                if(DeleteHQL.workerDelete()){
                    alertDelete.setContentText("Datos Eliminados");
                }else {
                    alertDelete.setContentText("Error al eliminar datos");
                }
                alertDelete.show();

                break;
            case "cancel":
                //textFields
                textFieldIdentificationCard.setText("");
                textFieldName.setText("");
                textFieldPhone.setText("");
                textFieldAddress.setText("");
                textFieldJob.setText("");
                textFieldSalary.setText("");
                textFieldPassword.setText("");
                break;
            case "found":
                Constant.tfCode = textFieldIdentificationCard.getText();
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
                break;
            default:
                break;
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
