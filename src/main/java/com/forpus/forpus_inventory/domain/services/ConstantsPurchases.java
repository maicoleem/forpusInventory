package com.forpus.forpus_inventory.domain.services;

import com.forpus.forpus_inventory.persistence.entity.InvoiceClass;
import com.forpus.forpus_inventory.persistence.entity.ProductClass;
import com.forpus.forpus_inventory.persistence.entity.WareinvoiceClass;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;

public class ConstantsPurchases {

    public static String entity = null;
    public static ArrayList<ProductClass> productTableList = new ArrayList<>();
    public static Boolean iva = false;
    public static Boolean boldP = false;
    public static Boolean checkin = true;
    public static InvoiceClass invoice = null;
    public static WareinvoiceClass wareInvoice = null;

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

}
