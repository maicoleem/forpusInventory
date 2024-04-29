package com.forpus.forpusinventory.domain.services;

import com.forpus.forpusinventory.persistence.crud.SearchHQL;
import com.forpus.forpusinventory.persistence.entity.InvoiceClass;
import java.util.Date;

public class ConstantsFinance {
    public static Date dateBegin;
    public static Date dateFinish;
    public static String[] cuentas = {"Clientes", "Trabajadores", "Proveedores", "Socios", "Productos", "Servicios"};
    public static int contributions = 0;
    public static int cash = 0;
    public static int bank = 0;
    public static int payable = 0;
    public static  int taxes = 0;
    public static int operation = 0;
    public static int receivable = 0;
    public static int utilities = 0;
    public static int others = 0;
    public static int assets = 0;
    public static int liabilities = 0;
    public static  int equity = 0;
    public static boolean searchInvoice(Date begin, Date end){
        dateBegin = begin;
        dateFinish = end;
        contributions = 0;
        cash = 0;
        bank = 0;
        payable = 0;
        taxes = 0;
        operation = 0;
        receivable = 0;
        utilities = 0;
        others = 0;
        assets = 0;
        liabilities = 0;
        equity = 0;

        if(SearchHQL.invoiceEntity("Date")){
            for (InvoiceClass invoice : ConstantsPurchases.invoiceList) {
                //Social Contributions
                if(invoice.getIdPartners() != null){
                    contributions = contributions + Integer.parseInt(invoice.getTotal());
                    cash = cash + Integer.parseInt(invoice.getCash());
                    bank = bank + Integer.parseInt(invoice.getBank());
                }
                //Shopping
                if(invoice.getIdProviders() != null){
                    cash = cash - Integer.parseInt(invoice.getCash());
                    bank = bank - Integer.parseInt(invoice.getBank());
                    operation = operation + Integer.parseInt(invoice.getTotal()) - Integer.parseInt(invoice.getTaxes()) - Integer.parseInt(invoice.getBold());
                    payable = payable + invoice.getIdBill();
                    taxes = taxes + Integer.parseInt(invoice.getTaxes()) + Integer.parseInt(invoice.getBold());
                }
                //SALES
                if(invoice.getIdCustomer() != null){
                    cash = cash + Integer.parseInt(invoice.getCash());
                    bank = bank + Integer.parseInt(invoice.getBank());

                    operation = operation - Integer.parseInt(invoice.getTotal()) + Integer.parseInt(invoice.getTaxes()) + Integer.parseInt(invoice.getBold()) + Integer.parseInt(invoice.getUtilities());
                    receivable = receivable + invoice.getIdBill();
                    taxes = taxes + Integer.parseInt(invoice.getBold());

                    others = others + Integer.parseInt(invoice.getTaxes());

                    utilities = utilities + Integer.parseInt(invoice.getUtilities()) - Integer.parseInt(invoice.getRUtilities());
                }
                assets = receivable + cash + bank + operation;
                liabilities = payable + taxes + others;
                equity = contributions + utilities;
            }
            return true;
        }else {
            System.out.println("NULL");
            return false;
        }
    }
}