package com.forpus.forpus_inventory.persistence.crud;

import com.forpus.forpus_inventory.domain.services.Constant;
import com.forpus.forpus_inventory.persistence.Session.SessionDB;
import com.forpus.forpus_inventory.persistence.entity.CompanyClass;
import com.forpus.forpus_inventory.persistence.entity.WorkersClass;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.metamodel.model.domain.CollectionDomainType;

/**
 * <h1>SingUP</h1>
 * search in data base the user and password
 * <p>
 * if the user and password math, login.
 * </p>
 * */

public class SingUp {
    /**
     * @param name is the username
     * @param password is the password the user
     * @return true if the name and password match with the database
     * */
    public static boolean companySingUP(String name, String password){
        try{
            SessionDB.session();
            Session session = SessionDB.sessionHibernate;
            Query query = session.createQuery("from CompanyClass C where C.name in(?1)");
            query.setParameter(1, name);
            Constant.company = (CompanyClass) query.uniqueResult();
            if(password.equals(Constant.company.getPassword())){
                Constant.isAdmin = true;
            }
            return password.equals(Constant.company.getPassword());
        }catch (Exception e){
            try{
                SessionDB.session();
                Session session = SessionDB.sessionHibernate;
                Query query = session.createQuery("from WorkersClass C where C.name in(?1)");
                query.setParameter(1, name);
                Constant.workerLogin = (WorkersClass) query.uniqueResult();
                if(password.equals(Constant.workerLogin.getPassword())){
                    Constant.isAdmin = Constant.workerLogin.getWage().equals("ADMIN");
                }
                return password.equals(Constant.workerLogin.getPassword());
            }catch (Exception i){
                System.out.println("Error ejecutando el query");
            }
        }
        return false;
    }

}
