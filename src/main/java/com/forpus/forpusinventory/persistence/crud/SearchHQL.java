package com.forpus.forpusinventory.persistence.crud;

import com.forpus.forpusinventory.controller.WareController;
import com.forpus.forpusinventory.domain.services.*;
import com.forpus.forpusinventory.persistence.Session.SessionDB;
import com.forpus.forpusinventory.persistence.entity.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class SearchHQL {

    public static boolean searchHQL(){
        try{
            //check hibernate connection and database
            SessionDB.session();
            Session session = SessionDB.sessionHibernate;

            switch (Constant.entity){
                case "CompanyClass":
                    Constant.companiesList = null;
                    String hql = "FROM "+ Constant.entity;
                    Query query = session.createQuery(hql);
                    List<CompanyClass> results = query.list();
                    Constant.companiesList = results.toArray(new CompanyClass[0]);
                    break;
                case "CustomerClass":
                    Constant.customersList = null;
                    String hql2 = "FROM "+ Constant.entity;
                    Query query2 = session.createQuery(hql2);
                    List<CustomerClass> results2 = query2.list();
                    Constant.customersList = results2.toArray(new CustomerClass[0]);

                    break;
                case "PartnersClass":
                    Constant.partnersList = null;
                    String hql3 = "FROM "+ Constant.entity;
                    Query query3 = session.createQuery(hql3);
                    List<PartnersClass> results3 = query3.list();
                    Constant.partnersList = results3.toArray(new PartnersClass[0]);
                    break;
                case "ProvidersClass":
                    Constant.providersList = null;
                    String hql4 = "FROM "+ Constant.entity;
                    Query query4 = session.createQuery(hql4);
                    List<ProvidersClass> results4 = query4.list();
                    Constant.providersList = results4.toArray(new ProvidersClass[0]);

                    break;
                case "WorkersClass":
                    Constant.workersList = null;
                    String hql5 = "FROM "+ Constant.entity;
                    Query query5 = session.createQuery(hql5);
                    List<WorkersClass> results5 = query5.list();
                    Constant.workersList = results5.toArray(new WorkersClass[0]);
                    break;
                case "CategoryoneClass":
                    ConstantsWare.categoryOneList = null;
                    String hql6 = "FROM "+ Constant.entity;
                    Query query6 = session.createQuery(hql6);
                    List<CategoryoneClass> results6 = query6.list();
                    ConstantsWare.categoryOneList = results6.toArray(new CategoryoneClass[0]);
                    break;
                case "CategorytwoClass":
                    ConstantsWare.categoryTwoList = null;
                    String hql7 = "FROM "+ Constant.entity;
                    Query query7 = session.createQuery(hql7);
                    List<CategorytwoClass> results7 = query7.list();
                    ConstantsWare.categoryTwoList = results7.toArray(new CategorytwoClass[0]);
                    break;
                case "CategorythreeClass":
                    ConstantsWare.categoryThreeList = null;
                    String hql8 = "FROM "+ Constant.entity;
                    Query query8 = session.createQuery(hql8);
                    List<CategorythreeClass> results8 = query8.list();
                    ConstantsWare.categoryThreeList = results8.toArray(new CategorythreeClass[0]);
                    break;
                case "WarehouseClass":
                    ConstantsWare.wareList = null;
                    String hql9 = "FROM "+ Constant.entity;
                    Query query9 = session.createQuery(hql9);
                    List<WarehouseClass> results9 = query9.list();
                    ConstantsWare.wareList = results9.toArray(new WarehouseClass[0]);
                    break;
                case "ServiceClass":
                    ConstantsWare.serviceList = null;
                    String hql10 = "FROM "+ Constant.entity;
                    Query query10 = session.createQuery(hql10);
                    List<ServiceClass> results10 = query10.list();
                    ConstantsWare.serviceList = results10.toArray(new ServiceClass[0]);
                    break;
                case "ProductClass":
                    ConstantsWare.productList = null;
                    String hql11 = "FROM "+ Constant.entity;
                    Query query11 = session.createQuery(hql11);
                    List<ProductClass> results11 = query11.list();
                    ConstantsWare.productList = results11.toArray(new ProductClass[0]);
                    break;
                case "ProductpriceClass":
                    ConstantsWare.productPriceList = null;
                    String hql12 = "FROM "+ Constant.entity;
                    Query query12 = session.createQuery(hql12);
                    List<ProductpriceClass> results12 = query12.list();
                    ConstantsWare.productPriceList = results12.toArray(new ProductpriceClass[0]);
                    break;
                case "WareProductClass":
                    ConstantsWare.wareProductList = null;
                    String hql13 = "FROM "+ Constant.entity;
                    Query query13 = session.createQuery(hql13);
                    List<WareProductClass> results13 = query13.list();
                    ConstantsWare.wareProductList = results13.toArray(new WareProductClass[0]);
                    break;
                case "TaxesClass":
                    ConstantsAccounting.taxesList = null;
                    String hql14 = "FROM "+ Constant.entity;
                    Query query14 = session.createQuery(hql14);
                    List<TaxesClass> results14 = query14.list();
                    ConstantsAccounting.taxesList = results14.toArray(new TaxesClass[0]);
                    break;
                case "MoveinvoiceClass":
                    if(ConstantsPurchases.entityForInvoice.equals("ProvidersClass")){
                        ConstantsPurchases.moveInvoiceList.clear();
                        String hql15 = "FROM "+ Constant.entity +" C where C.idInvoice in(?1)";
                        Query query15 = session.createQuery(hql15);
                        query15.setParameter(1, Integer.valueOf(Constant.tfCode));
                        List<MoveinvoiceClass> results15 = query15.list();
                        ConstantsPurchases.moveInvoiceList = (ArrayList<MoveinvoiceClass>) results15;
                    }else {
                        ConstantsAccounting.debtList = null;
                        String hql15 = "FROM "+ Constant.entity;
                        Query query15 = session.createQuery(hql15);
                        List<MoveinvoiceClass> results15 = query15.list();
                        ConstantsAccounting.debtList = results15.toArray(new MoveinvoiceClass[0]);
                    }

                    break;
                case "InvoiceClass":
                    try {
                        ConstantsPurchases.invoiceList.clear();
                    }catch (Exception i){
                        System.out.println(i +" invoiceList vacio");
                    }

                    String hql16 = "FROM "+ Constant.entity;
                    Query query16 = session.createQuery(hql16);
                    List<InvoiceClass> results16 = query16.list();
                    ConstantsPurchases.invoiceList = (ArrayList<InvoiceClass>) results16;
                    break;
                case "WareinvoiceClass":
                    ConstantsPurchases.wareInvoiceList = null;
                    String hql17 = "FROM "+ Constant.entity +" C where C.idInvoice in(?1)";
                    Query query17 = session.createQuery(hql17);
                    query17.setParameter(1, Integer.valueOf(Constant.tfCode));
                    List<WareinvoiceClass> results17 = query17.list();
                    ConstantsPurchases.wareInvoiceList = (ArrayList<WareinvoiceClass>) results17;
                    break;

                default:
                    break;
            }
            return true;
        }catch (Exception i){
            WareController.alertSend("ERROR AL BUSCAR DATOS");
            i.printStackTrace();
            return false;
        }
    }
    public static boolean invoiceIdBill(String typeEntity){

        try{
            SessionDB.session();
            Session session = SessionDB.sessionHibernate;
            ConstantsPurchases.invoiceList = null;
            switch (typeEntity){
                case "ProvidersClass":
                    String hql16 = "from InvoiceClass C where C.idBill > 0 and C.idProviders is not null";
                    Query query16 = session.createQuery(hql16);
                    List<InvoiceClass> results16 = query16.list();
                    ConstantsPurchases.invoiceList = (ArrayList<InvoiceClass>) results16;
                    break;
                case "CustomerClass":
                    String hqlC = "from InvoiceClass C where C.idBill > 0 and C.idCustomer is not null";
                    Query queryC = session.createQuery(hqlC);
                    List<InvoiceClass> resultsC = queryC.list();
                    ConstantsPurchases.invoiceList = (ArrayList<InvoiceClass>) resultsC;
                    break;
                case "WorkersClass":
                    String hqlW = "from InvoiceClass C where C.idBill > 0 and C.idWorkers is not null";
                    Query queryW = session.createQuery(hqlW);
                    List<InvoiceClass> resultsW = queryW.list();
                    ConstantsPurchases.invoiceList = (ArrayList<InvoiceClass>) resultsW;
                    break;
            }
            return true;
        }catch (Exception i){
            i.printStackTrace();
            WareController.alertSend("ERROR AL BUSCAR DEUDAS O CREDITOS");
            return false;
        }
    }
    public static boolean invoiceEntity(String typeEntity){
        try{
            SessionDB.session();
            Session session = SessionDB.sessionHibernate;
            ConstantsPurchases.invoiceList = null;
            switch (typeEntity){
                case "ProvidersClass":
                    String hql16 = "from InvoiceClass C where C.idProviders is not null";
                    Query query16 = session.createQuery(hql16);
                    List<InvoiceClass> results16 = query16.list();
                    ConstantsPurchases.invoiceList = (ArrayList<InvoiceClass>) results16;
                    break;
                case "CustomerClass":
                    String hqlC = "from InvoiceClass C where C.idCustomer is not null";
                    Query queryC = session.createQuery(hqlC);
                    List<InvoiceClass> resultsC = queryC.list();
                    ConstantsPurchases.invoiceList = (ArrayList<InvoiceClass>) resultsC;
                    break;
                case "WorkersClass":
                    String hqlW = "from InvoiceClass C where C.idWorkers is not null";
                    Query queryW = session.createQuery(hqlW);
                    List<InvoiceClass> resultsW = queryW.list();
                    ConstantsPurchases.invoiceList = (ArrayList<InvoiceClass>) resultsW;
                    break;
                case "Date":
                    Query query = session.createQuery("FROM InvoiceClass C WHERE C.date BETWEEN :dateStart AND :dateEnd");
                    query.setParameter("dateStart", ConstantsFinance.dateBegin);
                    query.setParameter("dateEnd", ConstantsFinance.dateFinish);
                    List<InvoiceClass> result = query.list();
                    ConstantsPurchases.invoiceList = (ArrayList<InvoiceClass>) result;
                    break;
            }
            return true;
        }catch (Exception i){
            i.printStackTrace();
            WareController.alertSend("ERROR AL BUSCAR DATOS");
            return false;
        }
    }
    public static void wareInvoice(){
        try{
            SessionDB.session();
            Session session = SessionDB.sessionHibernate;
            ConstantsPurchases.invoiceList = null;

            // Crear un objeto CriteriaBuilder para construir la consulta
            CriteriaBuilder builder = session.getCriteriaBuilder();

            // Crear un objeto CriteriaQuery para definir la consulta y establecer la entidad de la tabla
            CriteriaQuery<WareinvoiceClass> query = builder.createQuery(WareinvoiceClass.class);
            Root<WareinvoiceClass> root = query.from(WareinvoiceClass.class);

            // Definir las expresiones de selección y agrupación de la consulta
            Expression<String> idProduct = root.get("idProduct");
            Expression<Long> amount = builder.sum(root.get("amount"));
            query.multiselect(idProduct, amount);
            query.groupBy(idProduct);

            // Filtrar por proveedor no nulo
            Join<WareinvoiceClass, InvoiceClass> join = root.join("invoiceByIdInvoice", JoinType.INNER);
            Predicate proveedorNotNull = builder.isNotNull(join.get("idCustomer"));
            query.where(proveedorNotNull);

            // Ejecutar la consulta y obtener los resultados
            List<WareinvoiceClass> resultados = session.createQuery(query).getResultList();
            ConstantsPurchases.wareInvoiceList = (ArrayList<WareinvoiceClass>) resultados;
        }catch (Exception i){
            WareController.alertSend("ERROR AL BUSCAR DATOS");
            i.printStackTrace();
        }
    }
    public static void customersFinance(){
        try{
            SessionDB.session();
            Session session = SessionDB.sessionHibernate;
            ConstantsPurchases.invoiceList = null;

            // Crear un objeto CriteriaBuilder para construir la consulta
            CriteriaBuilder builder = session.getCriteriaBuilder();

            // Crear un objeto CriteriaQuery para definir la consulta y establecer la entidad de la tabla
            CriteriaQuery<CustomerClass> query = builder.createQuery(CustomerClass.class);
            Root<CustomerClass> root = query.from(CustomerClass.class);

            // Definir las expresiones de selección y agrupación de la consulta
            query.select(root)
                    .orderBy(builder.desc(builder.sum(root.get("cash"), root.get("bank"))));

            // Ejecutar la consulta y obtener los resultados
            List<CustomerClass> resultados = session.createQuery(query).getResultList();
            Constant.customersList = resultados.toArray(new CustomerClass[0]);
        }catch (Exception i){
            WareController.alertSend("ERROR AL BUSCAR DATOS");
            i.printStackTrace();
        }
    }
}
