package com.forpus.forpus_inventory.controller;

import com.forpus.forpus_inventory.HelloApplication;
import com.forpus.forpus_inventory.domain.services.Constant;
import com.forpus.forpus_inventory.domain.services.ConstantsAccounting;
import com.forpus.forpus_inventory.persistence.crud.DeleteHQL;
import com.forpus.forpus_inventory.persistence.crud.FoundHQL;
import com.forpus.forpus_inventory.persistence.crud.SaveHQL;
import com.forpus.forpus_inventory.persistence.crud.SearchHQL;
import com.forpus.forpus_inventory.persistence.entity.TaxesClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
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
    public TextField tfBold;
    public TextField tfIva;

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

        switch (Constant.entity){
            case "TaxesClass":
                for(TaxesClass tx: ConstantsAccounting.taxesList){
                    if(Objects.equals(tx.getIdTaxes(), "IVA")){
                        tx.setTaxes(tfIva.getText());
                    }
                    if(Objects.equals(tx.getIdTaxes(), "BOLD")){
                        tx.setTaxes(tfBold.getText());
                    }
                }
            default:
                break;
        }
    }

    private void differentiateBetweenEntities(boolean workerFound) {
        switch (Constant.entity){
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
            default:
                break;
        }
    }

    @FXML
    public void buttonsOptions(ActionEvent event) {
    }


    public void numeric(KeyEvent keyEvent) {

        switch (Constant.entity){
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
            default:
                break;
        }
    }
    public void clean(){
        tfBold.setText("");
        tfIva.setText("");
        save.setDisable(true);
    }
}
