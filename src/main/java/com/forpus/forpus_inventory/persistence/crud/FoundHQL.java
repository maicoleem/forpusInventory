package com.forpus.forpus_inventory.persistence.crud;

import com.forpus.forpus_inventory.domain.services.Constant;
import com.forpus.forpus_inventory.domain.services.ConstantsWare;
import com.forpus.forpus_inventory.persistence.Session.SessionDB;
import com.forpus.forpus_inventory.persistence.entity.*;
import org.hibernate.Query;
import org.hibernate.Session;

public class FoundHQL {
    public static boolean workerFound(){
        try{
            //check hibernate connection and database
            SessionDB.session();
            Session session = SessionDB.sessionHibernate;

            switch (Constant.entity){
                case "CompanyClass":
                    CompanyClass company = session.load(CompanyClass.class, Constant.tfCode);
                    Constant.tfName = company.getName();
                    Constant.tfPhone = company.getPhoneNumber();
                    Constant.tfAddress = company.getAddres();
                    Constant.tfJob = company.getWeb();
                    Constant.tfSalary = company.getSocial();
                    break;
                case "CustomerClass":
                    CustomerClass customer = session.load(CustomerClass.class, Constant.tfCode);
                    Constant.tfName = customer.getName();
                    Constant.tfPhone = customer.getPhoneNumber();
                    Constant.tfAddress = customer.getAddres();

                    break;
                case "PartnersClass":
                    PartnersClass partner = session.load(PartnersClass.class, Constant.tfCode);
                    Constant.tfName = partner.getName();
                    Constant.tfPhone = partner.getPhoneNumber();
                    Constant.tfAddress = partner.getAddress();

                    break;
                case "ProvidersClass":
                    ProvidersClass provider = session.load(ProvidersClass.class, Constant.tfCode);
                    System.out.println(Constant.tfCode);
                    Constant.tfName = provider.getName();
                    Constant.tfPhone = provider.getPhoneNumber();
                    Constant.tfAddress = provider.getAddress();
                    Constant.tfJob = provider.getEmail();

                    break;
                case "WorkersClass":
                    WorkersClass worker = session.load(WorkersClass.class, Constant.tfCode);
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
                default:
                    break;
            }
            return true;
        }catch (Exception i){
            //SessionDB.sessionClose();
            System.out.println(i);
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
                default:
                    break;
            }
            return true;
        } catch (Exception i){
            //SessionDB.sessionClose();
            System.out.println(i);
            return false;
        }
    }

}
