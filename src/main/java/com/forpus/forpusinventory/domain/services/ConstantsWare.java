package com.forpus.forpusinventory.domain.services;

import com.forpus.forpusinventory.persistence.crud.FoundHQL;
import com.forpus.forpusinventory.persistence.crud.SaveHQL;
import com.forpus.forpusinventory.persistence.entity.*;
import javafx.scene.control.Button;
import org.apache.poi.ss.formula.functions.Finance;

import java.util.ArrayList;

public class ConstantsWare {
    public static Button blueToWhite = null;
    public static String idOption = "";
    public static String nameCategory = "";
    public static CategoryoneClass one = null;
    public static CategorytwoClass two = null;
    public static CategorythreeClass three = null;
    public static WarehouseClass ware = null;
    public static ServiceClass service = null;
    public static ProductClass product = null;
    public static ServiceProductClass serviceProduct = null;
    public static ProductpriceClass productPrice = null;
    public static ProductpriceClass productPriceTransmute = null;
    public static WareProductClass wareProduct = null;
    public static CategoryoneClass[] categoryOneList = null;
    public static CategorytwoClass[] categoryTwoList = null;
    public static CategorythreeClass[] categoryThreeList = null;
    public static WarehouseClass[] wareList = null;
    public static ServiceClass[] serviceList = null;
    public static ProductClass[] productList = null;
    public static ServiceProductClass[] serviceProductList = null;
    public static ProductpriceClass[] productPriceList = null;
    public static WareProductClass[] wareProductList = null;
    public static ArrayList<ServiceProductClass> sPListArray = new ArrayList<>();
    public static ArrayList<ProductpriceClass> pPListArray = new ArrayList<>();
    public static String tfOne = null;
    public static String tfTwo = null;
    public static String tfThree = null;
    public static String tfWare = null;
    public static String tfBuy = null;
    public static String tfSale = null;
    public static String tfProfit = null;
    public static String tf10th = null;
    public static String tfCost = null;
    public static String tfConsumed = null;
    public static boolean saveTransmute(ProductpriceClass one, ArrayList<ProductpriceClass> many){
        try{//create invoice
        InvoiceClass invoice = ConstantsFinance.invoiceEmpty();
        invoice.setIdBill(666);
        invoice.setObservations("Transmute");
        invoice.setDate(ConstantsPurchases.dateActuallyGet());
        //save invoice
        SaveHQL.saveOrUpdateObject(invoice);
        //return id invoice
        Constant.tfCode = "666";
        Constant.entity = "InvoiceClass";
        FoundHQL.wareFound();
        //create and save wareInvoice and productPrices
        for(int i=0; i<many.size(); i++){
            ProductClass pt = many.get(i).getWareProductByIdProductWare().getProductByIdProduct();
            saveWareInvoice(ConstantsAccounting.invoice.getIdInvoice(),pt, i);
            SaveHQL.saveOrUpdateObject(many.get(i));
        }
        ProductClass productOne = one.getWareProductByIdProductWare().getProductByIdProduct();
        saveWareInvoice(ConstantsAccounting.invoice.getIdInvoice(),productOne, many.size());
        SaveHQL.saveOrUpdateObject(one);
        //Update invoice idBill = 0
        ConstantsAccounting.invoice.setIdBill(0);
        SaveHQL.saveOrUpdateObject(ConstantsAccounting.invoice);
        return true;
        }catch (Exception i){
            System.out.printf(i.getMessage());
            return false;
        }
    }
    public static void saveWareInvoice(int idInvoice, ProductClass product, int index){
        WareinvoiceClass wi = new WareinvoiceClass();
        wi.setIdInvoice(idInvoice);
        wi.setAmount(product.getAmount());
        wi.setIndexWare(index);
        wi.setPriceBuy(Integer.parseInt(product.getPurchasePrice()));
        wi.setPriceSale(product.getSalePrice());
        wi.setProductName(product.getName());
        wi.setIdProduct(product.getIdProduct());
        wi.setOffSale(product.getOffSale());

        SaveHQL.saveOrUpdateObject(wi);
    }
}
