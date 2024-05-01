package com.forpus.forpusinventory.domain.services;

import com.forpus.forpusinventory.controller.WareController;
import com.forpus.forpusinventory.persistence.crud.FoundHQL;
import com.forpus.forpusinventory.persistence.crud.SaveHQL;
import com.forpus.forpusinventory.persistence.crud.SearchHQL;
import com.forpus.forpusinventory.persistence.entity.CompanyClass;
import com.forpus.forpusinventory.persistence.entity.InvoiceClass;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ConstantsFinance {
    public static Date dateBegin;
    public static Date dateFinish;
    public static String[] cuentas = {"Clientes", "Trabajadores", "Proveedores", "Socios", "Productos", "Servicios"};
    public static String adjustments;
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
    public static int taxesBuy = 0;
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
        taxesBuy = 0;

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
                    taxesBuy = taxesBuy + Integer.parseInt(invoice.getTaxes()) + Integer.parseInt(invoice.getBold());
                }
                //SALES
                if(invoice.getIdCustomer() != null){
                    cash = cash + Integer.parseInt(invoice.getCash());
                    bank = bank + Integer.parseInt(invoice.getBank());
                    operation = operation - Integer.parseInt(invoice.getTotal()) + Integer.parseInt(invoice.getTaxes()) + Integer.parseInt(invoice.getBold()) + Integer.parseInt(invoice.getUtilities());
                    receivable = receivable + invoice.getIdBill();
                    taxes = taxes + Integer.parseInt(invoice.getBold());
                    others = others + Integer.parseInt(invoice.getTaxes());
                    utilities = utilities + Integer.parseInt(invoice.getUtilities());
                }
                //accounting adjustments
                if(invoice.getObservations() != null && invoice.getObservations().contains("adjustments") ){
                    //pay BOLD
                    taxes = taxes - Integer.parseInt(invoice.getBold());
                    //pay IVA
                    others = others - Integer.parseInt(invoice.getTaxes());
                    cash = cash - Integer.parseInt(invoice.getCash());
                    bank = bank - Integer.parseInt(invoice.getBank());
                    //adjustments cash and bank (- into invoice)
                    cash = cash + Integer.parseInt(invoice.getCash());
                    bank = bank + Integer.parseInt(invoice.getBank());
                }

                assets = receivable + cash + bank + operation + taxesBuy + taxes;
                liabilities = payable + taxes + others;
                equity = contributions + utilities;
            }
            return true;
        }else {
            System.out.println("NULL");
            return false;
        }
    }
    public static InvoiceClass invoiceEmpty(){
        InvoiceClass invoice = new InvoiceClass();
        invoice.setIdBill(0);
        invoice.setIdCompany("1");
        invoice.setBank("0");
        invoice.setCash("0");
        invoice.setTaxes("0");
        invoice.setBold("0");
        invoice.setIndebtedness("0");
        invoice.setTotal("0");
        invoice.setTotalBuy("0");
        invoice.setUtilities("0");
        invoice.setRUtilities("0");
        return invoice;
    }
    public static void saveAdjustment (InvoiceClass invoice){
        if(SaveHQL.saveOrUpdateObject(invoice)){
            WareController.alertSend("Ajuste realizado");
        }else {
            WareController.alertSend("Error al realizar ajuste");
        }
    }
    public static void updateAccountCompany(int bank, int cash){
        CompanyClass company = FoundHQL.companyFound();
        if(company != null){
            int afterBank = Integer.parseInt(company.getBank());
            int afterCash = Integer.parseInt(company.getCash());
            int newBank = afterBank + bank;
            int newCash = afterCash + cash;
            company.setBank(String.valueOf(newBank));
            company.setCash(String.valueOf(newCash));
            SaveHQL.saveOrUpdateObject(company);
        }
    }
    public static Date dateBalance(DatePicker date){
        LocalDate localDate;
        localDate = date.getValue();
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

}