package com.forpus.forpusinventory.domain.services;

import com.forpus.forpusinventory.persistence.entity.InvoiceClass;
import com.forpus.forpusinventory.persistence.entity.MoveinvoiceClass;
import com.forpus.forpusinventory.persistence.entity.TaxesClass;
import com.forpus.forpusinventory.persistence.entity.WareinvoiceClass;

public class ConstantsAccounting {
    public static TaxesClass[] taxesList = null;

    public static String entity = null;
    public static InvoiceClass[] invoiceList = null;
    public static InvoiceClass invoice = null;
    public static WareinvoiceClass[] wareInvoiceList = null;
    public static MoveinvoiceClass[] debtList = null;
}
