package com.forpus.forpus_inventory.persistence.crud;

import com.forpus.forpus_inventory.domain.services.Constant;
import com.forpus.forpus_inventory.persistence.Session.SessionDB;
import com.forpus.forpus_inventory.persistence.entity.*;
import org.hibernate.Query;
import org.hibernate.Session;

public class SaveHQL {
    public static boolean workerInsertUpdate(){
        if(searchWorker()){
            //cuando lo encuentre debe de actualizar falta eso
            Constant.messageSave = "Encontrado";
            insertWorker("update");
            return true;
        } else if (insertWorker("save")) {
            Constant.messageSave = "Creado";
            return true;
        } else{
            return false;
        }
    }

    public static boolean searchWorker(){
        SessionDB.session();
        Session session = SessionDB.session().getSession();
        //set the search query by name and get the password
        try{
            String q = "from "+ Constant.entity +" C where C.id in(?1)";
            Query query = session.createQuery(q);
            System.out.println(query);
            query.setParameter(1, Constant.tfCode);
            System.out.println(query);
            CompanyClass company = (CompanyClass) query.uniqueResult();
            if(company != null){
                return true;
            }

        }catch (Exception e){
            System.out.println(e);

            return false;
        }
        return false;
    }

    public static boolean insertWorker(String saveOrUpdate){
        try{
            //check hibernate connection and database
            SessionDB.session();
            Session session = SessionDB.session().getSession();
            switch (Constant.entity){
                case "CompanyClass":
                    CompanyClass company = new CompanyClass();
                    company.setIdCompanyNIT(Constant.tfCode);
                    company.setName(Constant.tfName);
                    company.setPhoneNumber(Constant.tfPhone);
                    company.setAddres(Constant.tfAddress);
                    company.setWeb(Constant.tfJob);
                    company.setPassword("789");
                    company.setSocial(Constant.tfSalary);

                    session.beginTransaction();
                   if(saveOrUpdate.equals("save")){
                        session.save(company);
                   }else{
                       session.update(company);
                   }
                    session.getTransaction().commit();
                    session.close();
                    System.out.println("Datos guardados");
                    break;
                case "CustomerClass":
                    CustomerClass customer = new CustomerClass();
                    customer.setIdCustomer(Constant.tfCode);
                    customer.setName(Constant.tfName);
                    customer.setPhoneNumber(Constant.tfPhone);
                    customer.setAddres(Constant.tfAddress);

                    session.beginTransaction();
                    if(saveOrUpdate.equals("save")){
                        session.save(customer);
                    }else{
                        session.update(customer);
                    }
                    session.getTransaction().commit();
                    session.close();
                    System.out.println("Cliente Creado");
                    break;
                case "PartnersClass":
                    PartnersClass partnert = new PartnersClass();
                    partnert.setIdentificationCard(Constant.tfCode);
                    partnert.setName(Constant.tfName);
                    partnert.setPhoneNumber(Constant.tfPhone);
                    partnert.setAddress(Constant.tfAddress);

                    session.beginTransaction();
                    if(saveOrUpdate.equals("save")){
                        session.save(partnert);
                    }else{
                        session.update(partnert);
                    }
                    session.getTransaction().commit();
                    session.close();
                    System.out.println("Socio Creada");

                    break;
                case "ProvidersClass":
                    ProvidersClass provider = new ProvidersClass();
                    provider.setNit(Constant.tfCode);
                    provider.setName(Constant.tfName);
                    provider.setPhoneNumber(Constant.tfPhone);
                    provider.setAddress(Constant.tfAddress);
                    provider.setEmail(Constant.tfJob);

                    session.beginTransaction();
                    if(saveOrUpdate.equals("save")){
                        session.save(provider);
                    }else{
                        session.update(provider);
                    }
                    session.getTransaction().commit();
                    session.close();
                    System.out.println("proveedor Creado");

                    break;
                case "WorkersClass":
                    WorkersClass worker = new WorkersClass();
                    worker.setIdentificationCard(Constant.tfCode);
                    worker.setName(Constant.tfName);
                    worker.setPhoneNumber(Constant.tfPhone);
                    worker.setAddress(Constant.tfAddress);
                    worker.setJob(Constant.tfJob);
                    worker.setWage(Constant.tfSalary);
                    worker.setPassword(Constant.tfPassword);

                    session.beginTransaction();
                    if(saveOrUpdate.equals("save")){
                        session.save(worker);
                    }else{
                        session.update(worker);
                    }
                    session.getTransaction().commit();
                    session.close();
                    System.out.println("Trabajador Creado");
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


