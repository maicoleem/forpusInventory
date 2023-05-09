package com.forpus.forpus_inventory.controller;

import com.forpus.forpus_inventory.domain.services.Constant;
import com.forpus.forpus_inventory.domain.services.ConstantsPurchases;
import com.forpus.forpus_inventory.domain.services.ConstantsWare;
import com.forpus.forpus_inventory.persistence.crud.FoundHQL;
import com.forpus.forpus_inventory.persistence.crud.SearchHQL;
import com.forpus.forpus_inventory.persistence.entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.util.ArrayList;

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

    @FXML
    protected void buttonSlide(ActionEvent event) throws IOException {
        WareController.slide(event);
    }
    public void initialize() {
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
    public void buttonCRUD(ActionEvent event) {
    }
    public void buttonsOptions(ActionEvent event) {
    }
}
