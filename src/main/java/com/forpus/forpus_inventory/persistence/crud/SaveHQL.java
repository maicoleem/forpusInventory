package com.forpus.forpus_inventory.persistence.crud;

import com.forpus.forpus_inventory.controller.WareController;
import com.forpus.forpus_inventory.domain.services.Constant;
import com.forpus.forpus_inventory.domain.services.ConstantsAccounting;
import com.forpus.forpus_inventory.domain.services.ConstantsPurchases;
import com.forpus.forpus_inventory.domain.services.ConstantsWare;
import com.forpus.forpus_inventory.persistence.Session.SessionDB;
import com.forpus.forpus_inventory.persistence.entity.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.event.spi.SaveOrUpdateEvent;

import java.security.Provider;
import java.util.Objects;

public class SaveHQL {
    public static boolean workerInsertUpdate(){
        if(FoundHQL.workerFound()){
            //cuando lo encuentre debe de actualizar falta eso
            Constant.messageSave = "Encontrado";
            insertWorker("update");
            return true;
        } else if (insertWorker("save")) {
            Constant.messageSave = "Creado";
            return true;
        } else{
            Constant.messageSave = "Error al guardar";
            return false;
        }
    }

    public static boolean insertWorker(String saveOrUpdate){

        try{
            //check hibernate connection and database
            if(SessionDB.sessionHibernate.isOpen()){
                SessionDB.sessionClose();
            }
            SessionDB.session();
            Session session = SessionDB.session().getSession();

            switch (Constant.entity){
                case "CompanyClass":
                    Constant.company.setName(Constant.tfName);
                    Constant.company.setPhoneNumber(Constant.tfPhone);
                    Constant.company.setAddres(Constant.tfAddress);
                    Constant.company.setWeb(Constant.tfJob);
                    Constant.company.setSocial(Constant.tfSalary);
                    session.beginTransaction();
                    session.update(Constant.company);
                    session.getTransaction().commit();

                    System.out.println("Datos Actualizados Compañia");

                    break;
                case "CustomerClass":
                    CustomerClass customer = new CustomerClass();
                    customer.setIdCustomer(Constant.tfCode);
                    customer.setName(Constant.tfName);
                    customer.setPhoneNumber(Constant.tfPhone);
                    customer.setAddres(Constant.tfAddress);

                    session.beginTransaction();
                    if(saveOrUpdate.equals("save")){
                        customer.setBank("0");
                        customer.setCash("0");
                        customer.setPayable("0");
                        customer.setReceivable("0");
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

                        partnert.setBank("0");
                        partnert.setCash("0");
                        partnert.setPayable("0");
                        partnert.setReceivable("0");

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
                    System.out.println("ProvidersClass save or update");
                    provider.setNit(Constant.tfCode);
                    provider.setName(Constant.tfName);
                    provider.setPhoneNumber(Constant.tfPhone);
                    provider.setAddress(Constant.tfAddress);
                    provider.setEmail(Constant.tfJob);

                    session.beginTransaction();
                    if(saveOrUpdate.equals("save")){

                        provider.setBank("0");
                        provider.setCash("0");
                        provider.setPayable("0");
                        provider.setReceivable("0");

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

                        worker.setBank("0");
                        worker.setCash("0");
                        worker.setPayable("0");
                        worker.setReceivable("0");

                        session.save(worker);
                    }else{
                        session.update(worker);
                    }
                    session.getTransaction().commit();
                    System.out.println("Trabajador Creado");
                    break;
                case "WarehouseClass":
                    WarehouseClass ware = new WarehouseClass();
                    ware.setIdWarehouse(Constant.tfCode);
                    ware.setName(Constant.tfName);

                    session.beginTransaction();
                    if(saveOrUpdate.equals("save")){
                        session.save(ware);
                    }else{
                        session.update(ware);
                    }
                    session.getTransaction().commit();
                    System.out.println("Bodega Guardada");
                    break;
                case "ProductClass":

                    //guarda o actualiza el producto
                    ProductClass product = new ProductClass();
                    product.setIdProduct(Constant.tfCode);
                    product.setName(Constant.tfName);

                    if(ConstantsWare.tfOne != null){
                        product.setIdOne(Integer.valueOf(ConstantsWare.tfOne));
                    }
                    if(ConstantsWare.tfTwo != null) {
                        product.setIdTwo(Integer.valueOf(ConstantsWare.tfTwo));
                    }
                    if(ConstantsWare.tfThree != null) {
                        product.setIdThree(Integer.valueOf(ConstantsWare.tfThree));
                    }

                    if(ConstantsWare.tfWare == null){
                        ConstantsWare.tfWare = "Withou";
                        product.setIdWage(ConstantsWare.tfWare);
                    }else{
                        product.setIdWage(ConstantsWare.ware.getIdWarehouse());
                    }

                    product.setSalePrice(ConstantsWare.tfSale);
                    product.setProfit(ConstantsWare.tfProfit);

                    if(saveOrUpdate.equals("save")){
                        product.setPurchasePrice(ConstantsWare.tfBuy);
                        product.setAmount(Integer.valueOf(ConstantsWare.tfConsumed));
                    }

                    session.beginTransaction();
                    if(saveOrUpdate.equals("save")){
                        product.setPurchasePrice(ConstantsWare.tfBuy);
                        product.setAmount(Integer.valueOf(ConstantsWare.tfConsumed));
                        session.save(product);
                    }else{
                        session.update(product);
                    }
                    session.getTransaction().commit();
                    System.out.println("Producto Guardada");

                    //guarada el producto en bodega
                    if(saveOrUpdate.equals("save")){
                        WareProductClass wp = new WareProductClass();
                        wp.setIdProduct(product.getIdProduct());
                        wp.setProductByIdProduct(product);

                        Constant.entity = "WarehouseClass";
                        Constant.tfCode = ConstantsWare.tfWare;
                        FoundHQL.workerFound();
                        wp.setIdWare(ConstantsWare.ware.getIdWarehouse());
                        wp.setWarehouseByIdWare(ConstantsWare.ware);

                        session.beginTransaction();
                        session.saveOrUpdate(wp);
                        session.getTransaction().commit();

                        //guarda o actualiza el precio del producto
                        ProductpriceClass pP = new ProductpriceClass();
                        pP.setPrice(Integer.parseInt(ConstantsWare.tfBuy));
                        pP.setAmount(Integer.parseInt(ConstantsWare.tfConsumed));
                        pP.setIdProductWare(wp.getIdWareProduct());
                        pP.setWareProductByIdProductWare(wp);
                        session.beginTransaction();
                        session.saveOrUpdate(pP);

                    }
                    session.getTransaction().commit();

                    Constant.entity = "ProductClass";
                    break;
                case "ServiceClass":
                    ServiceClass service = new ServiceClass();

                    service.setIdService(Constant.tfCode);

                    System.out.println(Constant.tfCode);
                    service.setName(Constant.tfName);

                    service.setProfit(ConstantsWare.tfProfit);

                    service.setPayForHour(ConstantsWare.tfBuy);

                    service.setIdWare(ConstantsWare.tfWare);

                    service.setHour(ConstantsWare.tfThree);

                    service.setCost(ConstantsWare.tfCost);

                    System.out.println(ConstantsWare.tfCost);

                    session.beginTransaction();

                    if(saveOrUpdate.equals("save")){
                        session.save(service);
                    }else{
                        session.update(service);
                    }
                    session.getTransaction().commit();
                    System.out.println("Servicio Guardado");

                    session.beginTransaction();
                    for(ServiceProductClass sp: ConstantsWare.sPListArray){
                        sp.setServiceByIdService(service);
                        session.saveOrUpdate(sp);
                    }
                    session.getTransaction().commit();
                    break;
                case "Transmute":
                    session.beginTransaction();
                    session.update(ConstantsWare.productPriceTransmute);
                    session.getTransaction().commit();

                    session.beginTransaction();
                    for(ProductpriceClass p: ConstantsWare.pPListArray){
                        ProductpriceClass L = session.get(ProductpriceClass.class, p.getIdPrice());
                        L.setAmount(p.getAmount());
                        session.update(L);
                        session.getTransaction().commit();
                    }
                    break;
                case "TaxesClass":
                    session.beginTransaction();
                    for(TaxesClass tx: ConstantsAccounting.taxesList){
                        TaxesClass L = session.get(TaxesClass.class, tx.getIdTaxes());
                        L.setTaxes(tx.getTaxes());
                        session.update(L);
                    }
                    session.getTransaction().commit();
                    break;
                case "InvoiceClass":
                    //incia la transferencia de datos
                    session.beginTransaction();
                    //guarda los datos
                    session.save(ConstantsAccounting.invoice);
                    //realiza el envio a la base de datos
                    session.getTransaction().commit();

                    switch (ConstantsPurchases.invoiceType){
                        case "socialContribution":
                            session.beginTransaction();
                            session.update(Constant.company);
                            session.getTransaction().commit();

                            session.beginTransaction();
                            session.update(Constant.partners);
                            session.getTransaction().commit();
                            break;
                        case "purchaseFromSupplier":
                          //la idea es que esta opción detenga la ejecución
                            break;
                        default:
                            break;
                    }
                    break;
                case "MoveinvoiceClass":
                    //salva el movimiento
                    session.beginTransaction();
                    session.save(ConstantsPurchases.moveInv);
                    session.getTransaction().commit();
                    //actualiza la factura
                    session.beginTransaction();
                    ConstantsAccounting.invoice.setIdBill(ConstantsPurchases.moveInv.getSubtotal());
                    session.update(ConstantsAccounting.invoice);
                    session.getTransaction().commit();

                    switch (ConstantsPurchases.entityForInvoice){
                        case "ProvidersClass":
                            ConstantsPurchases.purchaseCompany(String.valueOf(ConstantsPurchases.moveInv.getPayBank()),
                                    String.valueOf(ConstantsPurchases.moveInv.getPayCash()),
                                    String.valueOf((-1 *(
                                            ConstantsPurchases.moveInv.getPayCash() +
                                            ConstantsPurchases.moveInv.getPayBank()))));
                            session.beginTransaction();
                            session.update(Constant.company);
                            session.getTransaction().commit();

                            ConstantsPurchases.purchaseProvider(String.valueOf(ConstantsPurchases.moveInv.getPayBank()),
                                    String.valueOf(ConstantsPurchases.moveInv.getPayCash()),
                                    String.valueOf((-1 *
                                            (ConstantsPurchases.moveInv.getPayCash() +
                                            ConstantsPurchases.moveInv.getPayBank()))));
                            session.beginTransaction();
                            session.update(Constant.provider);
                            session.getTransaction().commit();
                            break;
                        default:
                            break;
                    }

                    break;
                default:
                    break;
            }
            SessionDB.sessionClose();
            return true;
        }catch (Exception i){
            System.out.println("Error en SaveHQL");
            System.out.println(i);
            i.printStackTrace();
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
            SessionDB.sessionClose();
            return true;
        }catch (Exception e){
            SessionDB.sessionClose();
            e.printStackTrace();
            System.out.println(e);
            return false;
        }
    }

    /**here go to code for save the invoice
     * */
    public static boolean saveInvoice(){
        try{
            SessionDB.session();
            Session session = SessionDB.session().getSession();
            session.beginTransaction();
            //primero actualiza la invoice
            session.update(ConstantsAccounting.invoice);
            session.getTransaction().commit();
            //segundo salva los ware invoice

            session.beginTransaction();
            for(WareinvoiceClass wI: ConstantsPurchases.wareInvoiceList){
                session.save(wI);
            }
            session.getTransaction().commit();
            // tercero actualiza las cuentas
            session.beginTransaction();
            session.update(Constant.company);
            session.getTransaction().commit();

            session.beginTransaction();
            switch (ConstantsPurchases.entityForInvoice){
                case "ProvidersClass":
                    session.update(Constant.provider);
                    break;
                case "WorkersClass":
                    session.update(Constant.worker);
                    break;
                case "CustomerClass":
                    session.update(Constant.customer);
                    break;
                case "PartnersClass":
                    session.update(Constant.partners);
                    break;
                default:
                    System.out.println("error al actuañizar los datos");
                    break;
            }
            session.getTransaction().commit();

            switch (ConstantsPurchases.entity){
                case "Purchases":
                    //actualiza el inventario
                    if(!ConstantsPurchases.pPriceUpdateList.isEmpty()){
                        session.beginTransaction();
                        for(ProductpriceClass pp: ConstantsPurchases.pPriceUpdateList){
                            session.update(pp);
                        }
                        session.getTransaction().commit();
                    }
                    if(!ConstantsPurchases.productNewList.isEmpty()){
                       //salva el producto
                        for(ProductClass pt: ConstantsPurchases.productNewList){
                            session.beginTransaction();
                            session.save(pt);
                            session.getTransaction().commit();

                            WareProductClass wp = new WareProductClass();
                            wp.setIdProduct(pt.getIdProduct());
                            wp.setProductByIdProduct(pt);
                            Constant.entity = "WarehouseClass";
                            Constant.tfCode = pt.getIdWage();
                            FoundHQL.workerFound();
                            wp.setIdWare(ConstantsWare.ware.getIdWarehouse());
                            wp.setWarehouseByIdWare(ConstantsWare.ware);
                            session.beginTransaction();
                            session.save(wp);
                            session.getTransaction().commit();

                            ProductpriceClass pP = new ProductpriceClass();
                            pP.setPrice(Integer.parseInt(pt.getPurchasePrice()));
                            pP.setAmount(pt.getAmount());
                            pP.setIdProductWare(wp.getIdWareProduct());
                            pP.setWareProductByIdProductWare(wp);
                            session.beginTransaction();
                            session.save(pP);
                            session.getTransaction().commit();
                        }
                    }
                    moveInvoiceNew();
                    break;
                case "Service":
                    //Actualiza los servicios o los crea
                    session.beginTransaction();
                    for(ServiceClass s: ConstantsPurchases.serviceTableList){
                        session.saveOrUpdate(s);
                    }
                    session.getTransaction().commit();
                    moveInvoiceNew();
                    break;
                case "SaleProduct":
                    //para guardar datos de la venta de un producto
                    //actualiza el inventario
                    if(!ConstantsPurchases.pPriceUpdateList.isEmpty()){
                        session.beginTransaction();
                        for(ProductpriceClass pp: ConstantsPurchases.pPriceUpdateList){
                            session.update(pp);
                        }
                        session.getTransaction().commit();
                    }
                    moveInvoiceNew();
                    break;
                default:
                    break;
            }
            SessionDB.sessionClose();
            return true;
        }catch (Exception i){
            i.printStackTrace();
            return false;
        }
    }
     public static void moveInvoiceNew(){
     try{
         SessionDB.session();
         Session session = SessionDB.session().getSession();
     //guarda deuda (inicio)
     if(!Objects.equals(ConstantsAccounting.invoice.getIndebtedness(), "0")){
         MoveinvoiceClass mi = new MoveinvoiceClass();
         mi.setIdInvoice(ConstantsAccounting.invoice.getIdInvoice());
         mi.setDate(ConstantsAccounting.invoice.getDate());
         mi.setDebt(Integer.parseInt(ConstantsAccounting.invoice.getIndebtedness()));
         mi.setPayCash(0);
         mi.setPayBank(0);
         mi.setSubtotal(mi.getDebt());
         session.beginTransaction();
         session.save(mi);
         session.getTransaction().commit();
     }
     SessionDB.sessionClose();
     }catch (Exception i){
         i.printStackTrace();
         System.out.println("Error en MoveInvoiceNew- SAveHQL");
         System.out.println(i);
     }
 }
}


