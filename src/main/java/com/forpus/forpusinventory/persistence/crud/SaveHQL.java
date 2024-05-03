package com.forpus.forpusinventory.persistence.crud;

import com.forpus.forpusinventory.controller.WareController;
import com.forpus.forpusinventory.domain.services.*;
import com.forpus.forpusinventory.persistence.Session.SessionDB;
import com.forpus.forpusinventory.persistence.entity.*;
import org.hibernate.Session;
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
            try{if(SessionDB.sessionHibernate.isOpen()){
                SessionDB.sessionClose();
            }}catch (Exception i){
                System.out.println(i +" INSERWORKER");
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
                    session.beginTransaction();
                    if(saveOrUpdate.equals("save")){
                        session.save(ConstantsWare.service);
                    }else{
                        session.update(ConstantsWare.service);
                    }
                    session.getTransaction().commit();

                    session.beginTransaction();
                    for(ServiceProductClass sp: ConstantsWare.sPListArray){
                        sp.setServiceByIdService(ConstantsWare.service);
                        session.saveOrUpdate(sp);
                    }
                    session.getTransaction().commit();
                    break;
                case "Transmute":
                    /*//One
                    session.beginTransaction();
                    session.update(ConstantsWare.productPriceTransmute);
                    session.getTransaction().commit();
                    //many
                    session.beginTransaction();
                    for(ProductpriceClass p: ConstantsWare.pPListArray){
                        ProductpriceClass L = session.get(ProductpriceClass.class, p.getIdPrice());
                        L.setAmount(p.getAmount());
                        session.update(L);
                        session.getTransaction().commit();
                    }*/
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

                    //actualiza deuda en la factura
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
                        case "CustomerClass":

                            session.beginTransaction();
                            session.update(Constant.company);
                            session.getTransaction().commit();

                            //actualiza cliente
                            ConstantsSales.saleCustomer(String.valueOf(ConstantsPurchases.moveInv.getPayBank()),
                                    String.valueOf(ConstantsPurchases.moveInv.getPayCash()),
                                    String.valueOf((-1 * (ConstantsPurchases.moveInv.getPayCash() +
                                                    ConstantsPurchases.moveInv.getPayBank()))));

                            session.beginTransaction();
                            session.update(Constant.customer);
                            session.getTransaction().commit();

                            //actualiza la cuenta de la compañia
                            ConstantsSales.saleMoveInvoiceCompany(
                                    String.valueOf(ConstantsPurchases.moveInv.getPayBank()),
                                    String.valueOf(ConstantsPurchases.moveInv.getPayCash()));
                            session.beginTransaction();
                            session.update(Constant.company);
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
            WareController.alertSend("ERROR AL SALVAR DATOS");
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
                    WareController.alertSend("ERROR AL SALVAR DATOS");
                    break;
            }
            session.getTransaction().commit();

            switch (ConstantsPurchases.entity){
                case "Purchases":
                    //update the products
                    if(!ConstantsPurchases.productTableList.isEmpty()){
                        session.beginTransaction();
                        for(ProductClass p: ConstantsPurchases.productTableList){
                            if(ConstantsPurchases.productNewList.contains(p)){
                                System.out.println("Producto nuevo para crear");
                            }else {
                                System.out.println("Actualizando: "+ p.getName());
                                session.update(p);
                            }
                        }
                        session.getTransaction().commit();
                    }
                    if(!ConstantsPurchases.productNewList.isEmpty()){
                        //salva el producto nuevo
                        for(ProductClass pt: ConstantsPurchases.productNewList){
                            System.out.println("Creando producto: "+ pt);
                            session.beginTransaction();
                            session.save(pt);
                            session.getTransaction().commit();
                            WareProductClass wp = saveWareProduct(pt);
                            saveProductPrice(pt, wp);
                        }
                    }
                    //actualiza el inventario
                    if(!ConstantsPurchases.pPriceUpdateList.isEmpty()){
                        session.beginTransaction();
                        for(ProductpriceClass pp: ConstantsPurchases.pPriceUpdateList){
                            System.out.println("Actualizando precio: "+ pp.getIdPrice());
                            session.update(pp);
                        }
                        session.getTransaction().commit();
                    }
                    if(!ConstantsPurchases.productNewWare.isEmpty()){
                        //salva el producto con bodega nueva
                        for(ProductClass pNewWare: ConstantsPurchases.productNewWare){
                            System.out.println("Creando bodega: "+ pNewWare);
                            WareProductClass wp = saveWareProduct(pNewWare);
                            saveProductPrice(pNewWare, wp);
                        }
                    }
                    if(!ConstantsPurchases.productNewPrice.isEmpty()){
                        //salva el producto con precio nuevo
                        for(ProductClass pNewPrice: ConstantsPurchases.productNewPrice){
                            for(WareProductClass wp: pNewPrice.getWareProductsByIdProduct()){
                                //Si la bodega corresponde a una registrada
                                System.out.println("Crando precio nuevo: "+ pNewPrice);
                                if(Objects.equals(wp.getIdWare(), pNewPrice.getIdWage())){
                                    saveProductPrice(pNewPrice, wp);
                                }
                            }
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
            WareController.alertSend("ERROR AL SALVAR DATOS");
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
         mi.setDate(String.valueOf(ConstantsAccounting.invoice.getDate()));
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
         WareController.alertSend("ERROR AL SALVAR DATOS");
     }
 }
    public static WareProductClass saveWareProduct(ProductClass product){

        try{
            SessionDB.session();
            Session session = SessionDB.session().getSession();

            WareProductClass wp = new WareProductClass();
            wp.setIdProduct(product.getIdProduct());
            wp.setProductByIdProduct(product);

            Constant.entity = "WarehouseClass";
            Constant.tfCode = product.getIdWage();
            FoundHQL.workerFound();

            wp.setIdWare(ConstantsWare.ware.getIdWarehouse());
            wp.setWarehouseByIdWare(ConstantsWare.ware);

            session.beginTransaction();
            session.save(wp);
            session.getTransaction().commit();

            return wp;
        }catch (Exception i) {
            WareController.alertSend("ERROR AL SALVAR DATOS");
            i.printStackTrace();
            return null;
        }
    }
    public static void saveProductPrice(ProductClass product, WareProductClass warehouse){
        try{
            SessionDB.session();
            Session session = SessionDB.session().getSession();
            ProductpriceClass pP = new ProductpriceClass();
            pP.setPrice(Integer.parseInt(product.getPurchasePrice()));
            pP.setAmount(product.getAmount());
            pP.setIdProductWare(warehouse.getIdWareProduct());
            pP.setWareProductByIdProductWare(warehouse);
            session.beginTransaction();
            session.save(pP);
            session.getTransaction().commit();
        }catch (Exception i){
            WareController.alertSend("ERROR AL SALVAR DATOS");
            i.printStackTrace();
        }
    }
    public static <T> boolean saveOrUpdateObject(T object) {
        try {
            SessionDB.session();
            Session session = SessionDB.session().getSession();
            session.beginTransaction();
            session.saveOrUpdate(object);
            session.getTransaction().commit();
            SessionDB.sessionClose();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}


