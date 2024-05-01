package com.forpus.forpusinventory.persistence.crud;

import com.forpus.forpusinventory.domain.services.Constant;
import com.forpus.forpusinventory.domain.services.ConstantsAccounting;
import com.forpus.forpusinventory.domain.services.ConstantsWare;
import com.forpus.forpusinventory.persistence.Session.SessionDB;
import com.forpus.forpusinventory.persistence.entity.*;
import org.hibernate.Query;
import org.hibernate.Session;
import java.util.Objects;

public class FoundHQL {
    public static boolean workerFound(){
        try{
            //check hibernate connection and database
            SessionDB.session();
            Session session = SessionDB.sessionHibernate;
            switch (Constant.entity){
                case "CompanyClass":
                    Query queryCompany = session.createQuery("from CompanyClass");
                    Constant.company = (CompanyClass) queryCompany.uniqueResult();
                    break;
                case "CustomerClass":
                    CustomerClass customer = session.load(CustomerClass.class, Constant.tfCode);
                    Constant.customer = customer;
                    Constant.tfName = customer.getName();
                    Constant.tfPhone = customer.getPhoneNumber();
                    Constant.tfAddress = customer.getAddres();

                    break;
                case "PartnersClass":
                    PartnersClass partner = session.load(PartnersClass.class, Constant.tfCode);
                    Constant.partners = partner;
                    Constant.tfName = partner.getName();
                    Constant.tfPhone = partner.getPhoneNumber();
                    Constant.tfAddress = partner.getAddress();

                    break;
                case "ProvidersClass":
                    ProvidersClass provider = session.load(ProvidersClass.class, Constant.tfCode);
                    Constant.provider = provider;
                    Constant.tfName = provider.getName();
                    Constant.tfPhone = provider.getPhoneNumber();
                    Constant.tfAddress = provider.getAddress();
                    Constant.tfJob = provider.getEmail();

                    break;
                case "WorkersClass":
                    WorkersClass worker = session.load(WorkersClass.class, Constant.tfCode);
                    Constant.worker = worker;
                    Constant.tfName = worker.getName();
                    Constant.tfPhone = worker.getPhoneNumber();
                    Constant.tfAddress = worker.getAddress();
                    Constant.tfJob = worker.getJob();
                    Constant.tfSalary = worker.getWage();
                    Constant.tfPassword = worker.getPassword();
                    break;
                case "CategoryoneClass":

                    String q = "from "+ Constant.entity +" C where C.categoryOne in(?1)";
                    Query query = session.createQuery(q);
                    query.setParameter(1, Constant.tfCode);
                    CategoryoneClass one = (CategoryoneClass) query.uniqueResult();
                    ConstantsWare.one = one;
                    if(one == null){
                        return false;
                    }
                    break;
                case "CategorytwoClass":

                    String qTwo = "from "+ Constant.entity +" C where C.categoryTwo in(?1)";
                    Query queryTwo = session.createQuery(qTwo);
                    queryTwo.setParameter(1, Constant.tfName);
                    CategorytwoClass two = (CategorytwoClass) queryTwo.uniqueResult();

                    if(two == null){
                        return false;
                    }else{
                        ConstantsWare.two = two;
                    }
                    break;
                case "CategorythreeClass":

                    String qThree = "from "+ Constant.entity +" C where C.categoryThree in(?1)";
                    Query queryThree = session.createQuery(qThree);
                    queryThree.setParameter(1, Constant.tfName);
                    CategorythreeClass three = (CategorythreeClass) queryThree.uniqueResult();
                    if(three == null){
                        return false;
                    }else{
                        ConstantsWare.three = three;
                    }
                    break;
                case "WarehouseClass":
                    String qWare = "from "+ Constant.entity +" C where C.id in(?1)";
                    Query queryWare = session.createQuery(qWare);
                    queryWare.setParameter(1, Constant.tfCode);
                    WarehouseClass ware = (WarehouseClass) queryWare.uniqueResult();
                    if(ware == null){
                        return false;
                    }else{
                        ConstantsWare.ware = ware;
                    }
                    break;
                case "ServiceClass":
                    String qService = "from "+ Constant.entity +" C where C.id in(?1)";
                    Query queryService = session.createQuery(qService);
                    queryService.setParameter(1, Constant.tfCode);
                    ServiceClass service = (ServiceClass) queryService.uniqueResult();

                    if(service == null){
                        return false;
                    }else{
                        ConstantsWare.service = service;
                    }
                    break;
                case "ProductClass":
                    String qProduct = "from "+ Constant.entity +" C where C.id in(?1)";
                    Query queryProduct = session.createQuery(qProduct);
                    queryProduct.setParameter(1, Constant.tfCode);
                    ProductClass product = (ProductClass) queryProduct.uniqueResult();
                    if(product == null){
                        return false;
                    }else{
                        ConstantsWare.product = product;
                    }
                    break;
                case "ProductpriceClass":
                    String qPP = "from "+ Constant.entity +" C where C.price in(?1)";
                    Query queryPP = session.createQuery(qPP);
                    queryPP.setParameter(1, Constant.tfCode);
                    ProductpriceClass pP = (ProductpriceClass) queryPP.uniqueResult();
                    if(pP == null){
                        return false;
                    }else{
                        ConstantsWare.productPrice = pP;
                    }
                    break;
                case "TaxesClass":
                    SearchHQL.searchHQL();
                    break;
                case "InvoiceClass":
                    InvoiceClass invoice = session.load(InvoiceClass.class, Integer.valueOf(Constant.tfCode));
                    ConstantsAccounting.invoice = invoice;
                    break;
                default:
                    break;
            }
            return true;
        }catch (Exception i){
            i.printStackTrace();
            if(!Constant.entity.equals("InvoiceClass")) {
                System.out.println("Error en worker found");
            }
            return false;
        }
    }
    public static boolean wareFound() {
        try {
            //check hibernate connection and database
            SessionDB.session();
            Session session = SessionDB.sessionHibernate;

            switch (Constant.entity) {
                case "WarehouseClass":
                    String qWare = "from " + Constant.entity + " C where C.name in(?1)";
                    Query queryWare = session.createQuery(qWare);
                    queryWare.setParameter(1, Constant.tfCode);
                    WarehouseClass ware = (WarehouseClass) queryWare.uniqueResult();
                    if (ware == null) {
                        return false;
                    } else {
                        ConstantsWare.ware = ware;
                    }
                    break;
                case "ProductClass":
                    String qProduct = "from " + Constant.entity + " C where C.name in(?1)";
                    Query queryProduct = session.createQuery(qProduct);
                    queryProduct.setParameter(1, Constant.tfCode);
                    ProductClass product = (ProductClass) queryProduct.uniqueResult();
                    if (product == null) {
                        return false;
                    } else {
                        ConstantsWare.product = product;
                    }
                    break;
                case "ServiceProductClass":
                    String qSP = "from "+ Constant.entity +" C where C.idProduct in(?1) and C.idService in(?2)";
                    Query querySP = session.createQuery(qSP);
                    querySP.setParameter(1, Constant.tfCode);
                    querySP.setParameter(2, Constant.tfName);
                    ServiceProductClass pSP = (ServiceProductClass) querySP.uniqueResult();
                    if(pSP == null){
                        return false;
                    }else{
                        ConstantsWare.serviceProduct = pSP;
                    }
                    break;
                case "WareProductClass":
                    String qWP = "from "+ Constant.entity +" C where C.idProduct in(?1) and C.idWare in(?2)";
                    Query queryWP = session.createQuery(qWP);
                    queryWP.setParameter(1, Constant.tfCode);
                    queryWP.setParameter(2, Constant.tfName);
                    WareProductClass pWP = (WareProductClass) queryWP.uniqueResult();
                    if(pWP == null){
                        return false;
                    }else{
                        ConstantsWare.wareProduct = pWP;
                    }
                    break;
                case "ProductpriceClass":
                    String qPPT = "from "+ Constant.entity +" C where C.idProductWare in(?1) and C.price in(?2)";
                    Query queryPPT = session.createQuery(qPPT);
                    queryPPT.setParameter(1, Integer.valueOf(Constant.tfCode));
                    queryPPT.setParameter(2, Integer.valueOf(Constant.tfName));
                    ProductpriceClass pPT = (ProductpriceClass) queryPPT.uniqueResult();

                    if(pPT == null){
                        return false;
                    }else{
                        ConstantsWare.productPrice = pPT;
                        if(Objects.equals(Constant.tfSalary, "transmute")){
                            ConstantsWare.productPriceTransmute = pPT;
                        }
                    }
                    break;
                case "ProductpriceClassID":
                    Constant.entity = "ProductpriceClass";
                    String qPrice = "from "+ Constant.entity +" C where C.idPrice in(?1)";
                    Query queryPrice = session.createQuery(qPrice);
                    queryPrice.setParameter(1, Integer.valueOf(Constant.tfCode));
                    ProductpriceClass price = (ProductpriceClass) queryPrice.uniqueResult();
                    if(price == null){
                        return false;
                    }else{
                        ConstantsWare.productPrice = price;
                    }
                    break;
                case "InvoiceClass":
                    String qInv = "from "+ Constant.entity +" C where C.idBill in(?1)";
                    Query queryInv = session.createQuery(qInv);
                    queryInv.setParameter(1, Integer.valueOf(Constant.tfCode));
                    InvoiceClass inv = (InvoiceClass) queryInv.uniqueResult();
                    if(inv == null){
                        return false;
                    }else{
                        ConstantsAccounting.invoice = inv;
                    }
                    break;

                default:
                    break;
            }
            return true;
        } catch (Exception i){
            i.printStackTrace();
            return false;
        }
    }
    public static CompanyClass companyFound(){
        try{
            SessionDB.session();
            Session session = SessionDB.sessionHibernate;
            Query queryCompany = session.createQuery("from CompanyClass");
            return (CompanyClass) queryCompany.uniqueResult();
        }catch (Exception i){
            i.printStackTrace();
            return null;
        }
    }

}
