package com.forpus.forpus_inventory.domain.services;

import com.forpus.forpus_inventory.persistence.entity.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ConstantsPurchases {

    public static String entity = null;
    public static String entityForInvoice = null;
    public static ArrayList<ProductClass> productTableList = new ArrayList<>();
    public static Boolean iva = false;
    public static Boolean boldP = false;
    public static Boolean checkin = true;
    public static ArrayList<ServiceClass> serviceTableList = new ArrayList<>();
    public static ArrayList<ProductpriceClass> pPriceUpdateList = new ArrayList<>();
    public static ArrayList<ProductClass> productNewList = new ArrayList<>();
    public static ArrayList<WareinvoiceClass> wareInvoiceList = new ArrayList<>();

    public static String invoiceType = null;

    //Este metodo permite calcular el subtotal  de cada producto
    public static Integer subtotalProduct(String amount, String price){
        int a = Integer.parseInt(amount);
        int p = Integer.parseInt(price);
        return a * p;
    }
    //Este metodo permite calcular el monto que costaron los productos.
    public static Integer totalBuyProduct(ArrayList<ProductClass> productList){
        int totalBuy = 0;
        for(ProductClass pt:productList){
            totalBuy = totalBuy + subtotalProduct(String.valueOf(pt.getAmount()), pt.getPurchasePrice());
        }
        return totalBuy;
    }
    //Este metodo permite calcular la cantidad de utilidades que aún no se han pagado
    public static Integer rUtilitiesProducts(ArrayList<ProductClass> productList, String indebtness, String total){
        //deuda que se tiene
        int debt = Integer.parseInt(indebtness);
        //utilidades que se debn
        int rUtilities;
        //utilidades netas
        int utilities;
        //inversión en compras que se debe
        int rBuy;
        //total de la venta
        int t = Integer.parseInt(total);
        //calculo de la utilidades
        utilities = t - totalBuyProduct(productList);
        //calculo de la cantidad que falta por pagar = totalBuy(fijo) - se ha pagado (variable)
        rBuy = (t - utilities) - (t - debt);

        if(rBuy == 0){
            rUtilities = utilities;
        } else if (rBuy < 0) {

            rUtilities = utilities - debt;

        } else {
            rUtilities = utilities + rBuy;
        }

        return rUtilities;
    }
    //Fecha
    public static String dateActually(){
        // Obteniendo la fecha actual del sistema.
        Date dateActually = new Date(Calendar.getInstance().getTimeInMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(dateActually);
    }
    public static void purchaseCompany(String bank, String cash, String payable){

        int bankChange = Integer.parseInt(bank);
        int cashChange = Integer.parseInt(cash);
        int payableChange = Integer.parseInt(payable);

        int bankOld = Integer.parseInt(Constant.company.getBank());
        int cashOld = Integer.parseInt(Constant.company.getCash());
        int payableOld = Integer.parseInt(Constant.company.getPayable());
        int totalOld = Integer.parseInt(Constant.company.getTotal());

        int bankNew = bankOld - bankChange;
        int cashNew = cashOld - cashChange;
        int payableNew = payableOld + payableChange;
        int totalNew = totalOld - bankNew - cashNew;

        Constant.company.setBank(String.valueOf(bankNew));
        Constant.company.setCash(String.valueOf(cashNew));
        Constant.company.setPayable(String.valueOf(payableNew));
        Constant.company.setTotal(String.valueOf(totalNew));
    }
    public static void purchaseProvider(String bank, String cash, String receivable){

        int bankChange = Integer.parseInt(bank);
        int cashChange = Integer.parseInt(cash);
        int receivableChange = Integer.parseInt(receivable);

        int bankOld = Integer.parseInt(Constant.provider.getBank());
        int cashOld = Integer.parseInt(Constant.provider.getCash());
        int receivableOld = Integer.parseInt(Constant.provider.getReceivable());

        int bankNew = bankOld + bankChange;
        int cashNew = cashOld + cashChange;
        int receivableNew = receivableOld + receivableChange;

        Constant.provider.setBank(String.valueOf(bankNew));
        Constant.provider.setCash(String.valueOf(cashNew));
        Constant.provider.setReceivable(String.valueOf(receivableNew));
    }
}
