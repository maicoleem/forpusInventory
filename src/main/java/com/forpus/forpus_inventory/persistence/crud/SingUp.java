package com.forpus.forpus_inventory.persistence.crud;

import com.forpus.forpus_inventory.persistence.Session.SessionDB;
import com.forpus.forpus_inventory.persistence.entity.CompanyClass;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.hibernate.Query;
import org.hibernate.Session;
public class SingUp {
    @FXML
    private Label lblSingUp;

    public static String name = "";
    public static String password = "";
    public static boolean companySingUP(){
        //check hibernate connection and database
        SessionDB.session();
        System.out.println("hello");
        System.out.println(SessionDB.session().getSession());
        Session session = SessionDB.session().getSession();
        //set the search query by name and get the password
        try{
            Query query = session.createQuery("from CompanyClass C where C.name in(?1)");
            query.setParameter(1, name);
            CompanyClass company = (CompanyClass) query.uniqueResult();
            return password.equals(company.getPassword());
        }catch (Exception e){
            System.out.println("Error ejecutando el query");
        }
        return false;
    }

}
