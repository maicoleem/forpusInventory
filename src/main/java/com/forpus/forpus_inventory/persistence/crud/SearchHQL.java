package com.forpus.forpus_inventory.persistence.crud;

import com.forpus.forpus_inventory.domain.services.Constant;
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
            Session session = SessionDB.session().getSession();
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
                default:
                    break;
            }
            return true;
        }catch (Exception i){
            System.out.println(i);
            return false;
        }
    }
}
