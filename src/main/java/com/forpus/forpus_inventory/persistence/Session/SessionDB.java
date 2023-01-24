package com.forpus.forpus_inventory.persistence.Session;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionDB {
    public static Session sessionHibernate = null;
    public static Session session(){
       try{
            if(sessionHibernate == null){
                    SessionFactory sF = new Configuration().configure().buildSessionFactory();
                    sessionHibernate = sF.openSession();
             }
       }catch (Exception e){
             e.printStackTrace();
           System.out.println(e);
       }
       return sessionHibernate;
    }

    public static Session sessionClose(){
        try{
            sessionHibernate.close();
            return (sessionHibernate = null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return (sessionHibernate = null);
    }

}
