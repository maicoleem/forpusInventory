package com.forpus.forpus_inventory.domain.services;

import com.forpus.forpus_inventory.persistence.entity.*;
import javafx.scene.control.Button;
import org.hibernate.hql.internal.ast.tree.BooleanLiteralNode;

import java.util.List;
import java.util.Objects;

public class Constant {
    public static Button greyToBlue = null;
    public static Button blueToWhite = null;
    public static Button greyToBlueSlide = null;
    public static String buttonOptionsID = null;

    public static String entity = null;
    public static final String lblCompany= "Empresa";
    public static final String lblName= "Nombre";
    public static final String lblCode = "Código";
    public static final String lblNIT = "NIT";
    public static final String lblIdentificationCard = "Cedula";
    public static final String lblAddress = "Dirección";
    public static final String lblPhone = "Telefono";
    public static final String lblWeb = "Página web";
    public static final String lblInstagram = "Instagram";
    public static final String lblPassword = "Password";
    public static final String lblSalary = "Salario";
    public static final String lblJob = "Cargo";
    public static final String lblEmail = "Email";

    //string para CRUD
    public static String tfCode = "";
    public static String tfName = "";
    public static String tfPhone = "";
    public static String tfAddress = "";
    public static String tfJob = "";
    public static String tfSalary = "";
    public static String tfPassword = "";

    //CRUD se salvaron los datos?????
    public static String messageSave = "";

    //lista de todos los datos
    public static CompanyClass[] companiesList = null;
    public static CustomerClass[] customersList = null;
    public static PartnersClass[] partnersList = null;
    public static ProvidersClass[] providersList = null;
    public static WorkersClass[] workersList = null;
    public static CompanyClass[] companyList = null;
    public static String[] entityForean= {"CategoryoneClass", "WarehouseClass", "ServiceClass", "ProductClass"};

    public static Boolean isEntityForeanKey(String entity){

        for(int i = 0; i < 2; i = i+1){

            if (Objects.equals(entityForean[i], entity)){
                return true;
            }
        }
        return false;
    }


}
