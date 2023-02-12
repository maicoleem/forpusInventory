package com.forpus.forpus_inventory.persistence.crud;

import com.forpus.forpus_inventory.domain.services.Constant;
import com.forpus.forpus_inventory.domain.services.ConstantsAccounting;
import com.forpus.forpus_inventory.domain.services.ConstantsWare;
import com.forpus.forpus_inventory.persistence.Session.SessionDB;
import com.forpus.forpus_inventory.persistence.entity.*;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;

public class SearchHQL {

    public static boolean searchHQL(){

        try{
            //check hibernate connection and database
            SessionDB.session();
            Session session = SessionDB.sessionHibernate;

            switch (Constant.entity){
                case "CompanyClass":
                    Constant.companiesList = null;
                    String hql = "FROM "+ Constant.entity;
                    Query query = session.createQuery(hql);
                    List<CompanyClass> results = query.list();
                    Constant.companiesList = results.toArray(new CompanyClass[0]);
                    break;
                case "CustomerClass":
                    Constant.customersList = null;
                    String hql2 = "FROM "+ Constant.entity;
                    Query query2 = session.createQuery(hql2);
                    List<CustomerClass> results2 = query2.list();
                    Constant.customersList = results2.toArray(new CustomerClass[0]);

                    break;
                case "PartnersClass":
                    Constant.partnersList = null;
                    String hql3 = "FROM "+ Constant.entity;
                    Query query3 = session.createQuery(hql3);
                    List<PartnersClass> results3 = query3.list();
                    Constant.partnersList = results3.toArray(new PartnersClass[0]);
                    break;
                case "ProvidersClass":
                    Constant.providersList = null;
                    String hql4 = "FROM "+ Constant.entity;
                    Query query4 = session.createQuery(hql4);
                    List<ProvidersClass> results4 = query4.list();
                    Constant.providersList = results4.toArray(new ProvidersClass[0]);

                    break;
                case "WorkersClass":
                    Constant.workersList = null;
                    String hql5 = "FROM "+ Constant.entity;
                    Query query5 = session.createQuery(hql5);
                    List<WorkersClass> results5 = query5.list();
                    Constant.workersList = results5.toArray(new WorkersClass[0]);
                    break;
                case "CategoryoneClass":
                    ConstantsWare.categoryOneList = null;
                    String hql6 = "FROM "+ Constant.entity;
                    Query query6 = session.createQuery(hql6);
                    List<CategoryoneClass> results6 = query6.list();
                    ConstantsWare.categoryOneList = results6.toArray(new CategoryoneClass[0]);
                    break;
                case "CategorytwoClass":
                    ConstantsWare.categoryTwoList = null;
                    String hql7 = "FROM "+ Constant.entity;
                    Query query7 = session.createQuery(hql7);
                    List<CategorytwoClass> results7 = query7.list();
                    ConstantsWare.categoryTwoList = results7.toArray(new CategorytwoClass[0]);
                    break;
                case "CategorythreeClass":
                    ConstantsWare.categoryThreeList = null;
                    String hql8 = "FROM "+ Constant.entity;
                    Query query8 = session.createQuery(hql8);
                    List<CategorythreeClass> results8 = query8.list();
                    ConstantsWare.categoryThreeList = results8.toArray(new CategorythreeClass[0]);
                    break;
                case "WarehouseClass":
                    ConstantsWare.wareList = null;
                    String hql9 = "FROM "+ Constant.entity;
                    Query query9 = session.createQuery(hql9);
                    List<WarehouseClass> results9 = query9.list();
                    ConstantsWare.wareList = results9.toArray(new WarehouseClass[0]);
                    break;
                case "ServiceClass":
                    ConstantsWare.serviceList = null;
                    String hql10 = "FROM "+ Constant.entity;
                    Query query10 = session.createQuery(hql10);
                    List<ServiceClass> results10 = query10.list();
                    ConstantsWare.serviceList = results10.toArray(new ServiceClass[0]);
                    break;
                case "ProductClass":
                    ConstantsWare.productList = null;
                    String hql11 = "FROM "+ Constant.entity;
                    Query query11 = session.createQuery(hql11);
                    List<ProductClass> results11 = query11.list();
                    ConstantsWare.productList = results11.toArray(new ProductClass[0]);
                    break;
                case "ProductpriceClass":
                    ConstantsWare.productPriceList = null;
                    String hql12 = "FROM "+ Constant.entity;
                    Query query12 = session.createQuery(hql12);
                    List<ProductpriceClass> results12 = query12.list();
                    ConstantsWare.productPriceList = results12.toArray(new ProductpriceClass[0]);
                    break;
                case "WareProductClass":
                    ConstantsWare.wareProductList = null;
                    String hql13 = "FROM "+ Constant.entity;
                    Query query13 = session.createQuery(hql13);
                    List<WareProductClass> results13 = query13.list();
                    ConstantsWare.wareProductList = results13.toArray(new WareProductClass[0]);
                    break;
                case "TaxesClass":
                    ConstantsAccounting.taxesList = null;
                    String hql14 = "FROM "+ Constant.entity;
                    Query query14 = session.createQuery(hql14);
                    List<TaxesClass> results14 = query14.list();
                    ConstantsAccounting.taxesList = results14.toArray(new TaxesClass[0]);
                    break;
                default:
                    break;
            }
            return true;
        }catch (Exception i){
            //SessionDB.sessionClose();
            System.out.println(i);
            i.printStackTrace();
            return false;
        }
    }
}
