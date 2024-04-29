package com.forpus.forpusinventory.domain.services;

import com.forpus.forpusinventory.persistence.crud.SearchHQL;
import com.forpus.forpusinventory.persistence.entity.InvoiceClass;

import javax.persistence.criteria.CriteriaBuilder;
import java.security.PublicKey;
import java.util.Date;

public class ConstantsFinance {
    public static Date dateBegin;
    public static Date dateFinish;
    public static String[] cuentas = {"Clientes", "Trabajadores", "Proveedores", "Socios", "Productos", "Servicios"};
    public static int contributions;
    public static int cash;
    public static int bank;
    public static int payable;
    public static  int taxes;
    public static int operation;

    public static void searchInvoice(Date begin, Date end){
        dateBegin = begin;
        dateFinish = end;
        if(SearchHQL.invoiceEntity("Date")){
            for (InvoiceClass invoice : ConstantsPurchases.invoiceList) {
                //Social Contributions
                if(!invoice.getIdPartners().isEmpty()){
                    contributions = contributions + Integer.parseInt(invoice.getTotal());
                    cash = cash + Integer.parseInt(invoice.getCash());
                    bank = bank + Integer.parseInt(invoice.getBank());
                }
                //Shopping
                if(!invoice.getIdProviders().isEmpty()){
                    cash = cash - Integer.parseInt(invoice.getCash());
                    bank = bank - Integer.parseInt(invoice.getBank());
                    operation = operation + Integer.parseInt(invoice.getTotal()) - Integer.parseInt(invoice.getTaxes()) - Integer.parseInt(invoice.getBold());
                    payable = payable + invoice.getIdBill();
                    taxes = taxes + Integer.parseInt(invoice.getTaxes()) + Integer.parseInt(invoice.getBold());
                }
            }
        }else {
            System.out.println("NULL");
        }
    }
}