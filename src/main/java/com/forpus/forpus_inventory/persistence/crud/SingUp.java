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
            //check hibernate connection and database
            if(SessionDB.sessionHibernate.isOpen()){
                SessionDB.sessionClose();
            }
            SessionDB.session();
            Session session = SessionDB.sessionHibernate;

            Query query = session.createQuery("from CompanyClass C where C.name in(?1)");
            query.setParameter(1, name);
            Constant.company = (CompanyClass) query.uniqueResult();
            //VARIABLE DONDE SE ALMACENA LA COMPAÑIA QUE INICIO SESION
            Constant.companyLogin = Constant.company;
            if(password.equals(Constant.company.getPassword())){
                Constant.isAdmin = true;
                Constant.admin = "Usuario: "+ Constant.company.getName() + " Tipo: Admin";
            }
            SessionDB.sessionClose();
            return password.equals(Constant.company.getPassword());
        }catch (Exception e){
            try{
                SessionDB.session();
                Session session = SessionDB.sessionHibernate;
                Query query = session.createQuery("from WorkersClass C where C.name in(?1)");
                query.setParameter(1, name);
                //VARIABLE DONDE SE ALMACENA LA COMPAÑIA QUE INICIO SESION
                Constant.workerLogin = (WorkersClass) query.uniqueResult();
                if(password.equals(Constant.workerLogin.getPassword())){
                    //VARIABLE QUE GUARDA SI EL USUARIO ES ADMIN
                    Constant.isAdmin = Constant.workerLogin.getJob().contains("ADMIN");
                    if(Constant.isAdmin){
                        Constant.admin = "Usuario: "+ Constant.workerLogin.getName() + " Tipo: Admin";
                    }else{
                        Constant.admin = "Usuario: "+ Constant.workerLogin.getName() + " Tipo: User";
                    }

                }
                SessionDB.sessionClose();
                return password.equals(Constant.workerLogin.getPassword());
            }catch (Exception i){
                System.out.println("Error ejecutando el query");
            }
        }
        return false;
    }

}
