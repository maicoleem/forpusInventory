package com.forpus.forpus_inventory.domain.services;

import com.forpus.forpus_inventory.persistence.entity.InvoiceClass;
import com.forpus.forpus_inventory.persistence.entity.MoveinvoiceClass;
import com.forpus.forpus_inventory.persistence.entity.TaxesClass;
import com.forpus.forpus_inventory.persistence.entity.WareinvoiceClass;

public class ConstantsAccounting {
    public static TaxesClass[] taxesList = null;

    public static String entity = null;
    public static InvoiceClass[] invoiceList = null;
    public static InvoiceClass invoice = null;
    public static WareinvoiceClass[] wareInvoiceList = null;
    public static MoveinvoiceClass[] debtList = null;
}
