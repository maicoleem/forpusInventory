package com.forpus.forpusinventory.persistence.Session;

import com.forpus.forpusinventory.controller.WareController;
import org.hibernate.Metamodel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Objects;

public class SessionDB {
    public static Session sessionHibernate = null;
    public static Session session(){
       try{
            if(sessionHibernate == null){
                    SessionFactory sF = new Configuration().configure().buildSessionFactory();
                    sessionHibernate = sF.openSession();
             }
       }catch (Exception e){
           WareController.alertSend(e.toString());
           System.out.println("Session cerado");
           System.out.println(e);
       }
        System.out.println("Session abierta");
       return sessionHibernate;
    }

    public static Session sessionClose(){
        try{
            sessionHibernate.close();
            return (sessionHibernate = null);
        }catch (Exception e){
            System.out.println("Error Al Cerrar Session"+ e);
        }
        System.out.println("Session cerrada");
        return (sessionHibernate = null);
    }

    public static EntityManager entityFactory(){
        try {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
            return entityManagerFactory.createEntityManager();
        }catch (Exception i){
            WareController.alertSend(i.toString());
            return null;
        }
    }

    public static Metamodel metamodel(){
        try {
            EntityManager eM = entityFactory();
            return (Metamodel) Objects.requireNonNull(eM).getMetamodel();
        }catch (Exception i){
            WareController.alertSend(i.toString());
            return null;
        }
    }

}
