package com.forpus.forpus_inventory.controller;

import com.forpus.forpus_inventory.HelloApplication;
import com.forpus.forpus_inventory.domain.services.Constant;
import com.forpus.forpus_inventory.domain.services.ConstantsWare;
import com.forpus.forpus_inventory.persistence.crud.DeleteHQL;
import com.forpus.forpus_inventory.persistence.crud.FoundHQL;
import com.forpus.forpus_inventory.persistence.crud.SaveHQL;
import com.forpus.forpus_inventory.persistence.crud.SearchHQL;
import com.forpus.forpus_inventory.persistence.entity.CategoryoneClass;
import com.forpus.forpus_inventory.persistence.entity.CategorythreeClass;
import com.forpus.forpus_inventory.persistence.entity.CategorytwoClass;
import com.forpus.forpus_inventory.persistence.entity.WarehouseClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class WareController {
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
    public TableColumn c8;
    @FXML
    public TableColumn c9;
    @FXML
    public Button found;
    @FXML
    public Button cancel;
    @FXML
    public Button save;
    @FXML
    public Button search;
    @FXML
    public Button remove;
    @FXML
    public Button buttonWarePB;
    @FXML
    public Button buttonDashPB;
    @FXML
    public Button buttonAccountingPB;
    @FXML
    public Button buttonBuyPB;
    @FXML
    public Button buttonSalePB;
    @FXML
    public Button buttonSettingsPB;
    @FXML
    public Button buttonCategory;
    @FXML
    public Button buttonWare;
    @FXML
    public Button buttonService;
    @FXML
    public Button buttonTransmute;
    @FXML
    public Label labelCode;
    @FXML
    public Label labelProduct;
    @FXML
    public Label labelOneCategory;
    @FXML
    public Label labelTwoCategory;
    @FXML
    public Label labelThreeCategory;
    @FXML
    public Label labelWage;
    @FXML
    public Label labelBuy;
    @FXML
    public Label labelSale;
    @FXML
    public Label labelProfit;
    @FXML
    public TextField tfCode;
    @FXML
    public TextField tfProduct;
    @FXML
    public TextField tfBuy;
    @FXML
    public TextField tfProfit;
    @FXML
    public TextField tfSale;
    @FXML
    public ComboBox comboBoxThree;
    @FXML
    public ComboBox comboBoxOne;
    @FXML
    public ComboBox comboBoxTwo;
    @FXML
    public ComboBox comboBoxWage;
    @FXML
    public Button buttonProducts;
    @FXML
    public TextField tfOneCategory;
    @FXML
    public TextField tfThreeCategory1;
    @FXML
    public Label labelCost;
    @FXML
    public TextField tfCost;
    @FXML
    public Label labelConsumed;
    @FXML
    public TextField tfConsumed;
    @FXML
    public TextField tfWare;
    @FXML
    public Button bOne;
    @FXML
    public Button bTwo;
    @FXML
    public Button bThree;
    @FXML
    public Button buttonUAM;
    @FXML
    public Button buttonMAU;
    @FXML
    public Button bThreeRemove;
    @FXML
    public Button bTwoRemove;
    @FXML
    public TableView tableWare;

    @FXML //botones del CRUD
    public void buttonCRUD(ActionEvent event) {

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
    public void crudEjecuted(String idButton){

        switch (idButton){
            case "save":
                Constant.tfCode = tfCode.getText();
                if(!Objects.equals(Constant.tfCode, "")){
                    Constant.tfName = tfOneCategory.getText();
                    if(SaveHQL.workerInsertUpdate()){
                        if(Objects.equals(Constant.messageSave, "Creado")){
                            alertSend("Se han guardado los datos");
                        }else{
                            alertSend("Se han actualizado los datos");
                        }
                    }else {
                        alertSend("Error en actualizar y/o insertar datos, por favor reiniciar");
                    }
                }else{
                    alertSend("Por favor digitar código");
                }

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
                    Alert alertSearch = new Alert(Alert.AlertType.INFORMATION);
                    alertSearch.setTitle("Freya Style--//--Forpus Company");
                    alertSearch.setContentText("Error al cargar los datos");
                    alertSearch.show();
                }
                break;
            case "remove":

                if(Constant.isEntityForeanKey(Constant.entity)){
                    DeleteHQL.deleteForean();
                }else{
    
                    Constant.tfCode = tfCode.getText();
                    if(DeleteHQL.workerDelete()){
                        alertSend("Datos Eliminados");
                    }else {
                        alertSend("Error al eliminar datos");
                    }
                }
                crudEjecuted("cancel");
                break;
            case "cancel":
                //textFields
                switch (Constant.entity){
                    case "CategorioneClass":
                    case "CategoritwoClass":
                    case "CategorythreeClass":
                        options("buttonCategory");
                    break;
                    case "buttonWare":
                        options("buttonWare");
                        break;
                    case "buttonService":
                        options("buttonService");
                        break;
                    case "buttonProducts":
                        options("buttonProducts");
                        break;
                    case "buttonTransmute":
                        options("buttonTransmute");
                        break;
                    default:
                }

                break;
            case "found":
                Constant.tfCode = tfCode.getText();

                if(!Objects.equals(Constant.tfCode, "")){
                    differentiateBetweenEntities(FoundHQL.workerFound());
                }else{
                    alertSend("Por favor digitar código");
                }
                break;
            default:
                break;
        }

    }

    //fucnion para que el crud haga diferentes cosas dependiendo de la entity
    public void differentiateBetweenEntities(Boolean found){
        switch (Constant.entity){
            case "CategoryoneClass":
                    bOne.setDisable(false);
                    SearchHQL.searchHQL();
                    c1.setText("Categoria Uno");
                    c1.setCellValueFactory(new PropertyValueFactory<>("categoryOne"));
                    ObservableList<CategoryoneClass> dates = FXCollections.observableArrayList(ConstantsWare.categoryOneList);
                    tableWare.getItems().clear();
                    tableWare.setItems(dates);
                break;

            case "WarehouseClass":
                //busca y pone datos en la tabla
                SearchHQL.searchHQL();
                tableWare.getItems().clear();
                c1.setText("Codigo");
                c1.setCellValueFactory(new PropertyValueFactory<>("idWarehouse"));
                c2.setText("Bodega");
                c2.setCellValueFactory(new PropertyValueFactory<>("name"));
                ObservableList<WarehouseClass> datesWare = FXCollections.observableArrayList(ConstantsWare.wareList);
                tableWare.setItems(datesWare);

                //busca el codigo de la warehouse y pone el nombre
                if(FoundHQL.workerFound()){
                    tfOneCategory.setText(ConstantsWare.ware.getName());
                    save.setDisable(false);
                    remove.setDisable(false);
                }else{
                    alertSend("La bodega no existe");
                    save.setDisable(false);
                }

                break;
            default:
                break;
        }
    }

    //botones check para categorias pricnipalmente
    public void buttonCheck(ActionEvent event) {
        Button buttonCheck = (Button) event.getSource();

        switch (buttonCheck.getId()){
            case "bOne":
                Constant.entity = "CategoryoneClass";
                Constant.tfName = tfCode.getText();
                ConstantsWare.two = null;
                ConstantsWare.three = null;

                if(ConstantsWare.one != null){
                    Constant.tfCode = String.valueOf(ConstantsWare.one.getIdOne());
                    if(SaveHQL.saveUpdateCate("update")){
                        bOne.setDisable(true);
                        bTwo.setDisable(false);
                        tfCode.setDisable(true);
                        tfOneCategory.setDisable(false);
                        remove.setDisable(false);
                    }
                }else{
                    if(SaveHQL.saveUpdateCate("save")){
                        bOne.setDisable(true);
                        bTwo.setDisable(false);
                        tfCode.setDisable(true);
                        tfOneCategory.setDisable(false);
                        remove.setDisable(false);
                    }
                }
                Constant.entity = "CategorytwoClass";
                SearchHQL.searchHQL();
                c1.setText("Categoria Dos");
                c1.setCellValueFactory(new PropertyValueFactory<>("categoryTwo"));
                ObservableList<CategorytwoClass> datesTwo = FXCollections.observableArrayList(ConstantsWare.categoryTwoList);
                tableWare.getItems().clear();
                tableWare.setItems(datesTwo);
                Constant.entity = "CategoryoneClass";

                break;
            case "bTwo":
                Constant.entity = "CategorytwoClass";
                Constant.tfName = tfOneCategory.getText();

                if(FoundHQL.workerFound() || ConstantsWare.two != null){

                    Constant.tfCode = String.valueOf(ConstantsWare.two.getIdTwo());

                    if(SaveHQL.saveUpdateCate("update")){
                        System.out.println("update");
                        bOne.setDisable(true);
                        bTwo.setDisable(false);
                        bThree.setDisable(false);

                        tfCode.setDisable(true);
                        tfOneCategory.setDisable(false);
                        tfThreeCategory1.setDisable(false);
                        bTwoRemove.setDisable(false);

                    }
                }else{
                    if(SaveHQL.saveUpdateCate("save")){
                        System.out.println("save");
                        bOne.setDisable(true);
                        bTwo.setDisable(false);
                        bThree.setDisable(false);

                        bThree.setDisable(false);
                        tfCode.setDisable(true);
                        tfOneCategory.setDisable(false);
                        tfThreeCategory1.setDisable(false);
                        bTwoRemove.setVisible(true);
                        bTwoRemove.setDisable(false);
                    }
                }
                Constant.entity = "CategorythreeClass";
                SearchHQL.searchHQL();
                c1.setText("Categoria Tres");
                c1.setCellValueFactory(new PropertyValueFactory<>("categoryThree"));
                ObservableList<CategorythreeClass> datesThree = FXCollections.observableArrayList(ConstantsWare.categoryThreeList);
                tableWare.getItems().clear();
                tableWare.setItems(datesThree);
                Constant.entity = "CategorytwoClass";
                break;
            case "bThree":
                Constant.entity = "CategorythreeClass";
                Constant.tfName = tfThreeCategory1.getText();
                if(FoundHQL.workerFound() || ConstantsWare.three != null){
                    Constant.tfCode = String.valueOf(ConstantsWare.three.getIdThree());
                    if(SaveHQL.saveUpdateCate("update")){
                        bThreeRemove.setVisible(true);
                        bThreeRemove.setDisable(false);
                    }
                }else{
                    if(SaveHQL.saveUpdateCate("save")){

                        bThreeRemove.setDisable(false);
                    }
                }
                break;
            default:
                break;
        }

    }
    @FXML //boton remover para las categorias
    public void buttonRemoveCate(ActionEvent event) {

        Button buttonRemove = (Button) event.getSource();

        switch (buttonRemove.getId()){
            case "bTwoRemove":
                Constant.entity = "CategorytwoClass";
                DeleteHQL.deleteForean();
                tfOneCategory.setText("");
                tfThreeCategory1.setText("");
                break;
            case "bThreeRemove":
                Constant.entity = "CategorythreeClass";
                tfThreeCategory1.setText("");
                DeleteHQL.deleteForean();
                break;
            default:
                break;
        }
    }
    
    @FXML //botones del lado derecho
    private void buttonSlide(ActionEvent event) throws IOException {
        slide(event);
    }
    @FXML//botones derechos
    static void slide(ActionEvent event) throws IOException {
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
        slideChange(buttonSlide.getId(), event);
    }
    @FXML //botones de lado derecho
    static void slideChange(String blue, ActionEvent event) throws IOException {
        switch (blue){
            case "bWare":
                FXMLLoader fxmlLoaderWare = new FXMLLoader(HelloApplication.class.getResource("ware-view.fxml"));
                Scene sceneWare = new Scene(fxmlLoaderWare.load());
                Stage stageWare = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stageWare.setTitle("Forpus Inventory");
                stageWare.setScene(sceneWare);
                stageWare.show();
                break;
            case "bDash":
                break;
            case "bAccounting":
                break;
            case "bBuy":
            case "bSale":
                break;
            case "bSettings":
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("settings-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Forpus Inventory");
                stage.setScene(scene);
                stage.show();
                break;
            default:
                break;
        }

    }

    @FXML //botones de arriba
    public void buttonsOptions(ActionEvent event) {
        Button buttonOption = (Button) event.getSource();

        if(ConstantsWare.blueToWhite == null){
            ConstantsWare.blueToWhite = buttonOption;
            buttonOption.setStyle("-fx-background-color: #F5F5F5; ");
        } else if (ConstantsWare.blueToWhite == buttonOption) {
            buttonOption.setStyle("-fx-background-color: #F5F5F5; ");
        }else{
            ConstantsWare.blueToWhite.setStyle("-fx-background-color: #1BA1E2; ");
            buttonOption.setStyle("-fx-background-color: #F5F5F5; ");
            ConstantsWare.blueToWhite = buttonOption;
        }

        ConstantsWare.idOption = buttonOption.getId();
        options(ConstantsWare.idOption);
    }
    //botones de arriba, para dar formato al view
    protected void options(String option){
        clean();
        switch (option){
            case "buttonCategory":

                Constant.entity = "CategoryoneClass";

                labelCode.setText("Categoria 1");
                labelOneCategory.setText("Categoria 2");
                labelThreeCategory.setText("Categoria 3");

                labelThreeCategory.setVisible(true);
                tfOneCategory.setVisible(true);
                tfThreeCategory1.setVisible(true);

                tfOneCategory.setDisable(true);
                tfThreeCategory1.setDisable(true);

                bOne.setVisible(true);
                bTwo.setVisible(true);
                bThree.setVisible(true);
                bTwoRemove.setVisible(true);
                bThreeRemove.setVisible(true);

                break;
            case "buttonWare":
                Constant.entity = "WarehouseClass";

                labelCode.setText(Constant.lblCode);
                labelOneCategory.setText("Bodega");
                tfOneCategory.setVisible(true);


                break;
            case "buttonService":
                labelCode.setText(Constant.lblCode);
                labelOneCategory.setText("Producto");
                labelTwoCategory.setText("Precio");
                labelThreeCategory.setText("Profit");
                labelBuy.setText("Salario");
                labelProfit.setText("Horas");
                labelProduct.setText("Servicio");
                labelSale.setText("Contenido");
                labelWage.setText("Bodega");


                comboBoxOne.setVisible(true);
                comboBoxWage.setVisible(true);
                comboBoxTwo.setVisible(true);

                tfThreeCategory1.setVisible(true);
                tfBuy.setVisible(true);
                tfProfit.setVisible(true);
                tfProduct.setVisible(true);
                tfSale.setVisible(true);
                tfCost.setVisible(true);
                tfConsumed.setVisible(true);

                labelBuy.setVisible(true);
                labelSale.setVisible(true);
                labelProfit.setVisible(true);
                labelProduct.setVisible(true);
                labelTwoCategory.setVisible(true);
                labelWage.setVisible(true);
                labelThreeCategory.setVisible(true);
                labelCost.setVisible(true);
                labelConsumed.setVisible(true);

                break;
            case "buttonProducts":

                labelCode.setText(Constant.lblCode);
                labelOneCategory.setText("Categoria 1");
                labelThreeCategory.setText("Categoria 3");

                labelTwoCategory.setText("Categoria 2");
                labelBuy.setText("Precio Compra");
                labelProfit.setText("Profit");
                labelProduct.setText("Producto");
                labelSale.setText("Precio Venta");
                labelWage.setText("Bodega");

                labelBuy.setVisible(true);
                labelSale.setVisible(true);
                labelProfit.setVisible(true);
                labelProduct.setVisible(true);
                labelTwoCategory.setVisible(true);
                labelWage.setVisible(true);
                labelThreeCategory.setVisible(true);

                comboBoxOne.setVisible(true);
                comboBoxTwo.setVisible(true);
                comboBoxThree.setVisible(true);
                comboBoxWage.setVisible(true);

                tfBuy.setVisible(true);
                tfProduct.setVisible(true);
                tfSale.setVisible(true);
                tfProfit.setVisible(true);

                break;
            case "buttonTransmute":

                labelCode.setText(Constant.lblCode);
                labelOneCategory.setText("Cantidad");
                labelThreeCategory.setText("Código");
                labelWage.setText("Producto");
                labelBuy.setText("Cantidad");
                labelProfit.setText("Profit");
                labelProduct.setText("Producto");
                labelConsumed.setText("Precio Venta");

                labelBuy.setVisible(true);
                labelProfit.setVisible(true);
                labelProduct.setVisible(true);
                labelWage.setVisible(true);
                labelThreeCategory.setVisible(true);
                labelConsumed.setVisible(true);
                labelCost.setVisible(true);

                comboBoxOne.setVisible(true);
                comboBoxThree.setVisible(true);

                tfBuy.setVisible(true);
                tfProduct.setVisible(true);
                tfProfit.setVisible(true);
                tfThreeCategory1.setVisible(true);
                tfConsumed.setVisible(true);
                tfCost.setVisible(true);
                tfWare.setVisible(true);

                bTwo.setVisible(true);
                buttonUAM.setVisible(true);
                buttonMAU.setVisible(true);

                break;
            default:
                break;
        };
    }

    //botones de arriba para dar formato al view
    protected void clean(){

        labelBuy.setVisible(false);
        labelSale.setVisible(false);
        labelProfit.setVisible(false);
        labelProduct.setVisible(false);
        labelTwoCategory.setVisible(false);
        labelWage.setVisible(false);
        labelThreeCategory.setVisible(false);
        labelCost.setVisible(false);
        labelConsumed.setVisible(false);

        comboBoxOne.setVisible(false);
        comboBoxTwo.setVisible(false);
        comboBoxThree.setVisible(false);
        comboBoxWage.setVisible(false);

        tfBuy.setVisible(false);
        tfProduct.setVisible(false);
        tfSale.setVisible(false);
        tfProfit.setVisible(false);
        tfOneCategory.setVisible(false);
        tfThreeCategory1.setVisible(false);
        tfCost.setVisible(false);
        tfConsumed.setVisible(false);
        tfWare.setVisible(false);

        tfCode.setDisable(false);
        tfOneCategory.setDisable(false);
        tfThreeCategory1.setDisable(false);

        tfCode.setText("");
        tfBuy.setText("");
        tfProduct.setText("");
        tfSale.setText("");
        tfProfit.setText("");
        tfWare.setText("");
        tfCost.setText("");
        tfConsumed.setText("");
        tfThreeCategory1.setText("");
        tfOneCategory.setText("");

        tableWare.getItems().clear();

        bOne.setVisible(false);
        bTwo.setVisible(false);
        bThree.setVisible(false);
        buttonUAM.setVisible(false);
        buttonMAU.setVisible(false);
        bTwoRemove.setVisible(false);

        bTwo.setDisable(true);
        bThree.setDisable(true);
        bTwoRemove.setDisable(true);
        bThreeRemove.setVisible(false);
        bThreeRemove.setDisable(true);
        remove.setDisable(true);

        c1.setText("C1");
    }
    public void alertSend(String massage){
        Alert alertMassage = new Alert(Alert.AlertType.INFORMATION);
        alertMassage.setTitle("Freya Style--//--Forpus Company");
        alertMassage.setContentText(massage);
        alertMassage.show();
    }
}
