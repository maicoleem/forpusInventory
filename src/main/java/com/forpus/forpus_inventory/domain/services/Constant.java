package com.forpus.forpus_inventory.domain.services;

import com.forpus.forpus_inventory.persistence.entity.*;
import javafx.scene.control.Button;
import java.util.ArrayList;
import java.util.Objects;

public class Constant {
    public static Button greyToBlue = null;
    public static Button blueToWhite = null;
    public static Button greyToBlueSlide = null;
    public static String buttonOptionsID = null;

    public static String entity = null;
    public static Boolean isAdmin = false;
    public static String admin = "Admin";
    public static String title (){
        return "Forpus Inventory:// " + Constant.admin;
    }
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
    //Objectos de las entitys
    public static CompanyClass company = null;
    public static CompanyClass companyLogin = null;
    public static CustomerClass customer = null;
    public static PartnersClass partners = null;
    public static ProvidersClass provider = null;
    public static WorkersClass worker = null;
    public static WorkersClass workerLogin = null;
    public static String[] entityForean = {"CategoryoneClass", "WarehouseClass", "ServiceClass", "ProductClass"};
    public static ArrayList<TableShow> listTableShow = new ArrayList<>();
    public static ArrayList<TableShow> listTableShow2 = new ArrayList<>();

    public static Boolean isEntityForeanKey(String entity){

        for (String s : entityForean) {
            if (Objects.equals(s, entity)) {
                return true;
            }
        }
        return false;
    }

}
