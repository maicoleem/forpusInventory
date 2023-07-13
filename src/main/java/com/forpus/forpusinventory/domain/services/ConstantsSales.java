package com.forpus.forpusinventory.domain.services;
import com.forpus.forpusinventory.persistence.entity.ProductClass;
import com.forpus.forpusinventory.persistence.entity.WareProductClass;
import javafx.collections.ObservableList;

import javax.swing.text.Utilities;

public class ConstantsSales {

    public static String salesOption = null;
    public static WareProductClass wareProduct = null;
    public static String iva = "";
    public static String bold = "";
    public static String rBuy = "0";
    public static String rUtilities = "0";
    public static ObservableList<ProductClass> seekProductList;
    public static Integer utilitiesPer (int sale, int buy){
        return ((sale - buy) * 100)/buy;
    }
    public static String taxes (String ivaI, String boldB){
        int iva = Integer.parseInt(ivaI);
        int bold = Integer.parseInt(boldB);

        if(!ConstantsPurchases.iva){
            iva = 0;
        }
        if(!ConstantsPurchases.boldP){
            bold = 0;
        }
        return String.valueOf(iva + bold);
    }
    public static String utilities (String totalSale, String totalBuy, String taxes, String debt, String total, String bold){
        //venta sin impuestos
        int sale = Integer.parseInt(totalSale);
        int buy = Integer.parseInt(totalBuy);
        int tx = Integer.parseInt(taxes);
        int d = Integer.parseInt(debt);
        int b = Integer.parseInt(bold);
        //venta con impuestos
        int tv = Integer.parseInt(total);

        //total pagado (Se reta de la venta con impuestos lo que se debe)
        int pay = tv - d;
        //utilidades
        int utilities = sale - buy;

        //si lo pagado es diferente a la venta con impuestos
        if(pay != tv){
            //si lo pagado es menor o igual al valor de los impuestos IVA
            if(pay <= tx){
                rBuy = String.valueOf(buy);
                rUtilities = String.valueOf(utilities);
            }else {
                //si lo pagado es menor  o igual a lo comprado

                if(pay <= buy ){
                 rUtilities = String.valueOf(utilities);
                 rBuy = String.valueOf(buy - (pay - tx));
                }else {
                    rBuy = "0";
                    rUtilities = String.valueOf(utilities-(pay -tx -buy));
                }
            }
        }else{
            rBuy = "0";
            rUtilities = "0";
        }
        return String.valueOf((sale - buy - b));
    }
    public static void saleCompany(String bank, String cash, String receivable, String uReceivable, String utilities){

        int bankChange = Integer.parseInt(bank);
        int cashChange = Integer.parseInt(cash);
        //is all lo que deben a la compañia
        int receivableChange = Integer.parseInt(receivable);
        //uReceivable son las utilidades pendientes de cobrar
        int uRChange = Integer.parseInt(uReceivable);
        int utilitiesChange = Integer.parseInt(utilities);

        int bankOld = Integer.parseInt(Constant.company.getBank());
        int cashOld = Integer.parseInt(Constant.company.getCash());
        int receivableOld = Integer.parseInt(Constant.company.getReceivable());
        int uRChangeOld = Integer.parseInt(Constant.company.getUReceivable());
        int utilitiesOld = Integer.parseInt(Constant.company.getUtilities());

        int bankNew = bankOld + bankChange;
        int cashNew = cashOld + cashChange;
        int receivableNew = receivableOld + receivableChange;
        int uRNew = uRChangeOld + uRChange;
        int utilitiesNew = utilitiesOld +  utilitiesChange;
        int totalNew = bankNew + cashNew;

        Constant.company.setBank(String.valueOf(bankNew));
        Constant.company.setCash(String.valueOf(cashNew));
        Constant.company.setReceivable(String.valueOf(receivableNew));
        Constant.company.setUReceivable(String.valueOf(uRNew));
        Constant.company.setUtilities(String.valueOf(utilitiesNew));
        Constant.company.setTotal(String.valueOf(totalNew));
    }
    public static void saleCustomer(String bank, String cash, String payable){

        int bankChange = Integer.parseInt(bank);
        int cashChange = Integer.parseInt(cash);
        int payableChange = Integer.parseInt(payable);

        int bankOld = Integer.parseInt(Constant.customer.getBank());
        int cashOld = Integer.parseInt(Constant.customer.getCash());
        int payableOld = Integer.parseInt(Constant.customer.getPayable());

        int bankNew = bankOld + bankChange;
        int cashNew = cashOld + cashChange;
        int payableNew = payableChange + payableOld;

        Constant.customer.setBank(String.valueOf(bankNew));
        Constant.customer.setCash(String.valueOf(cashNew));
        Constant.customer.setPayable(String.valueOf(payableNew));
    }

    public static void saleMoveInvoiceCompany(String bank, String cash){
        int bankChange = Integer.parseInt(bank);
        int cashChange = Integer.parseInt(cash);
        int bankOld = Integer.parseInt(Constant.company.getBank());
        int cashOld = Integer.parseInt(Constant.company.getCash());
        //is all lo que deben a la compañia
        int receivableOld = Integer.parseInt(Constant.company.getReceivable());
        //uReceivable son las utilidades pendientes de cobrar
        int uRecevivableOld = Integer.parseInt(Constant.company.getUReceivable());
        //utilidades netas
        int utilitiesOld = Integer.parseInt(Constant.company.getUtilities());
        int utilitiesChange = 0;

        int bankNew = bankOld + bankChange;
        int cashNew = cashOld + cashChange;
        int totalNew = bankNew + cashNew;

        int totalPay = cashChange + bankChange;
        int debt = receivableOld - totalPay;

        if(receivableOld > uRecevivableOld){
            if(debt <= uRecevivableOld){
                Constant.company.setReceivable(String.valueOf(debt));
                Constant.company.setUReceivable(String.valueOf(debt));
                utilitiesChange = uRecevivableOld - debt;
                Constant.company.setUtilities(String.valueOf(utilitiesOld + utilitiesChange));
            }else {
                Constant.company.setReceivable(String.valueOf(debt));
                Constant.company.setUReceivable(String.valueOf(uRecevivableOld));
                Constant.company.setUtilities(String.valueOf(utilitiesOld));
            }
        }else {
            Constant.company.setReceivable(String.valueOf(debt));
            Constant.company.setUReceivable(String.valueOf(debt));
            utilitiesChange = uRecevivableOld - debt;
            Constant.company.setUtilities(String.valueOf(utilitiesOld + utilitiesChange));
        }

        Constant.company.setBank(String.valueOf(bankNew));
        Constant.company.setCash(String.valueOf(cashNew));
        Constant.company.setTotal(String.valueOf(totalNew));

    }
    public static String resta (String valorA, String valorB){
        int a = Integer.parseInt(valorA);
        int b = Integer.parseInt(valorB);
        return String.valueOf(a-b);
    }


}
