package com.forpus.forpusinventory.persistence.crud;

import com.forpus.forpusinventory.controller.WareController;
import com.forpus.forpusinventory.domain.services.Constant;
import com.forpus.forpusinventory.domain.services.ConstantsWare;
import com.forpus.forpusinventory.persistence.Session.SessionDB;
import com.forpus.forpusinventory.persistence.entity.*;
import org.hibernate.Query;
import org.hibernate.Session;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DeleteHQL {
    public static boolean workerDelete(){
        try{
            //check hibernate connection and database
            SessionDB.session();
            Session session = SessionDB.sessionHibernate;

            session.beginTransaction();

            switch (Constant.entity){
                case "CompanyClass":
                    CompanyClass company = session.load(CompanyClass.class, Constant.tfCode);
                    session.delete(company);
                    break;
                case "CustomerClass":
                    CustomerClass customer = session.load(CustomerClass.class, Constant.tfCode);
                    session.delete(customer);
                    break;
                case "PartnersClass":
                    PartnersClass partner = session.load(PartnersClass.class, Constant.tfCode);
                    session.delete(partner);
                    break;
                case "ProvidersClass":
                    ProvidersClass provider = session.load(ProvidersClass.class, Constant.tfCode);
                    session.delete(provider);
                    break;
                case "WorkersClass":
                    WorkersClass worker = session.load(WorkersClass.class, Constant.tfCode);
                    session.delete(worker);
                    break;
                case "CategoryoneClass":
                    String q = "delete from "+ Constant.entity +" C where C.idOne in(?1)";
                    Query query = session.createQuery(q);
                    query.setParameter(1, ConstantsWare.one.getIdOne());
                    query.executeUpdate();
                    break;
                case "CategorytwoClass":
                    CategorytwoClass two = session.load(CategorytwoClass.class, ConstantsWare.two.getIdTwo());
                    session.delete(two);
                    break;
                case "CategorythreeClass":
                    CategorythreeClass three = session.load(CategorythreeClass.class, ConstantsWare.three.getIdThree());
                    session.delete(three);
                    break;
                case "WarehouseClass":
                    WarehouseClass ware = session.load(WarehouseClass.class, Constant.tfCode);
                    session.delete(ware);
                    break;
                default:
                    break;
            }

            session.getTransaction().commit();
            SessionDB.sessionClose();

            return true;
        }catch (Exception i){
            SessionDB.sessionClose();
            WareController.alertSend("ERROR AL BORRAR DATO");
            i.printStackTrace();
            return false;
        }
    }
    public static void deleteForean(){
        try{
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            switch (Constant.entity) {
                case "CategoryoneClass":
                    CategoryoneClass one = entityManager.find(CategoryoneClass.class, ConstantsWare.one.getIdOne());
                    one.getCategorytwosByIdOne().remove(one);
                    entityManager.remove(one);
                    break;
                case "CategorytwoClass":
                    CategorytwoClass two = entityManager.find(CategorytwoClass.class, ConstantsWare.two.getIdTwo());
                    two.getCategorythreesByIdTwo().remove(two);
                    entityManager.remove(two);
                    break;
                case "CategorythreeClass":
                    CategorythreeClass three = entityManager.find(CategorythreeClass.class, ConstantsWare.three.getIdThree());
                    entityManager.remove(three);
                    break;
                case "WarehouseClass":
                    WarehouseClass ware = entityManager.find(WarehouseClass.class, ConstantsWare.ware.getIdWarehouse());
                    entityManager.remove(ware);
                    break;
                case "ProductClass":
                    ProductClass product = entityManager.find(ProductClass.class, ConstantsWare.product.getIdProduct());
                    entityManager.remove(product);
                    break;
                case "ServiceClass":
                    /*
                    if(!ConstantsWare.sPListArray.isEmpty()) {
                        for (ServiceProductClass p : ConstantsWare.sPListArray) {
                            ServiceProductClass sP = entityManager.find(ServiceProductClass.class, p.getId());
                            entityManager.remove(sP);
                        }
                    }*/

                    ServiceClass service = entityManager.find(ServiceClass.class, ConstantsWare.service.getIdService());
                    entityManager.remove(service);
                    break;
                case "ServiceProductClass":
                    ServiceProductClass SP = entityManager.find(ServiceProductClass.class, ConstantsWare.serviceProduct.getId());
                    entityManager.remove(SP);
                    break;
                default:
                    break;
            }
            entityManager.flush();
            entityManager.clear();
            entityManager.getTransaction().commit();
            SessionDB.sessionClose();
            System.out.println("borrado");

        }catch (Exception e){
            WareController.alertSend("ERROR AL BORRAR DATO");
            e.printStackTrace();
        }
    }

}
