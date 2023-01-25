package com.forpus.forpus_inventory.persistence.crud;

import com.forpus.forpus_inventory.domain.services.Constant;
import com.forpus.forpus_inventory.domain.services.ConstantsWare;
import com.forpus.forpus_inventory.persistence.Session.SessionDB;
import com.forpus.forpus_inventory.persistence.entity.*;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.Objects;

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

    //para update categorias
    //para insertar categorias
    public static boolean saveUpdateCate(String saveUpdate){
        try {
            //check hibernate connection and database
            SessionDB.session();
            //Session session = SessionDB.session().getSession();
            Session session = SessionDB.sessionHibernate;

            switch (Constant.entity) {
                case "CategoryoneClass":
                CategoryoneClass oneC = new CategoryoneClass();
                oneC.setCategoryOne(Constant.tfName);
                session.beginTransaction();
                if (Objects.equals(saveUpdate, "save")) {
                    session.save(oneC);
                } else {
                    oneC = session.load(CategoryoneClass.class, ConstantsWare.one.getIdOne());
                    oneC.setCategoryOne(Constant.tfName);
                    session.evict(oneC);
                    session.update(oneC);
                }
                ConstantsWare.one = oneC;
                break;

                case "CategorytwoClass":
                    CategorytwoClass twoC = new CategorytwoClass();
                    twoC.setCategoryTwo(Constant.tfName);
                    twoC.setIdCategoryOne(ConstantsWare.one.getIdOne());
                    session.beginTransaction();

                    if (Objects.equals(saveUpdate, "save")) {
                        twoC.setCategoryoneByIdCategoryOne(ConstantsWare.one);
                        session.save(twoC);
                    } else {
                        twoC = session.load(CategorytwoClass.class, ConstantsWare.two.getIdTwo());
                        twoC.setCategoryTwo(Constant.tfName);
                        session.update(twoC);
                    }
                    ConstantsWare.two = twoC;
                    break;
                case "CategorythreeClass":
                    CategorythreeClass threeC = new CategorythreeClass();
                    threeC.setCategoryThree(Constant.tfName);
                    threeC.setIdTwoThree(ConstantsWare.two.getIdTwo());
                    threeC.setCategorytwoByIdTwoThree(ConstantsWare.two);
                    session.beginTransaction();
                    if (Objects.equals(saveUpdate, "save")) {
                        session.save(threeC);
                    } else {
                        threeC = session.load(CategorythreeClass.class, ConstantsWare.three.getIdThree());
                        threeC.setCategoryThree(Constant.tfName);
                        session.update(threeC);
                    }
                    ConstantsWare.three = threeC;
                    break;
                default:
                    break;
            }
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            SessionDB.sessionClose();
            e.printStackTrace();
            System.out.println(e);
            return false;
        }
    }

}


