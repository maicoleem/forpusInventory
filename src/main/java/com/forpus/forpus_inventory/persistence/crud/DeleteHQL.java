package com.forpus.forpus_inventory.persistence.crud;

import com.forpus.forpus_inventory.domain.services.Constant;
import com.forpus.forpus_inventory.persistence.Session.SessionDB;
import com.forpus.forpus_inventory.persistence.entity.*;
import org.hibernate.Session;

public class DeleteHQL {

    public static boolean workerDelete(){
        try{
            //check hibernate connection and database
            SessionDB.session();
            Session session = SessionDB.session().getSession();
            switch (Constant.entity){
                case "CompanyClass":
                    CompanyClass company = session.load(CompanyClass.class, Constant.tfCode);
                    session.delete(company);
                    session.getTransaction().commit();
                    session.close();
                    break;
                case "CustomerClass":
                    CustomerClass customer = session.load(CustomerClass.class, Constant.tfCode);
                    session.delete(customer);
                    session.getTransaction().commit();
                    session.close();

                    break;
                case "PartnersClass":
                    PartnersClass partner = session.load(PartnersClass.class, Constant.tfCode);
                    session.delete(partner);
                    session.getTransaction().commit();
                    session.close();

                    break;
                case "ProvidersClass":
                    ProvidersClass provider = session.load(ProvidersClass.class, Constant.tfCode);
                    session.delete(provider);
                    session.getTransaction().commit();
                    session.close();

                    break;
                case "WorkersClass":
                    WorkersClass worker = session.load(WorkersClass.class, Constant.tfCode);
                    session.delete(worker);
                    session.getTransaction().commit();
                    session.close();
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
