package com.forpus.forpusinventory.domain.repository;
import com.forpus.forpusinventory.controller.WareController;
import com.forpus.forpusinventory.domain.services.Constant;
import com.forpus.forpusinventory.domain.services.ConstantsAccounting;
import com.forpus.forpusinventory.domain.services.ConstantsPurchases;
import com.forpus.forpusinventory.persistence.Session.SessionDB;
import com.forpus.forpusinventory.persistence.crud.FoundHQL;
import com.forpus.forpusinventory.persistence.entity.CompanyClass;
import com.forpus.forpusinventory.persistence.entity.CustomerClass;
import com.forpus.forpusinventory.persistence.entity.InvoiceClass;
import com.forpus.forpusinventory.persistence.entity.WareinvoiceClass;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.swing.JRViewer;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportGenerator {
    public static String os = System.getProperty("os.name").toLowerCase();
    public static void generateReport() {
        try {
            InvoiceClass invoice = ConstantsAccounting.invoice;
            CustomerClass customer = customerInvoice(invoice.getIdCustomer());
            CompanyClass company = companyFound();
            // Ruta al archivo del informe de JasperReports (.jASPERT)
            String reportPath = "src/main/resources/com/forpus/jasper_report/Factura.jrxml";
            String pathInstallWin = "C:\\Program Files (x86)\\forpus\\reports\\Factura.jrxml";
            String pathInstallLinux = "/opt/forpusInventory/reports/Factura.jrxml";
            if (os.contains("win")) {
                // Código para Windows
                reportPath = "Factura.jrxml";
            } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
                // Código para Linux o macOS
                reportPath = "/home/loro/MEGAsync/hubgit/forpusInventory/src/main/resources/com/forpus/jasper_report/Factura.jrxml";
            } else {
                throw new UnsupportedOperationException("Sistema operativo no compatible");
            }
            //String reportPath = "C:\\Users\\Teemo\\OneDrive\\java\\proyecto1\\forpus_inventory\\src\\main\\resources\\com\\forpus\\jasper_report\\Factura.jrxml";


            // Obtener los datos para la tabla (en este caso, una lista de objetos WareInvoice)
            List<WareinvoiceClass> invoiceData = obtenerDatosDeFactura();

            WareinvoiceClass wi = invoiceData.get(0);
            invoiceData.add(0, wi);

            for(WareinvoiceClass wInvoice: invoiceData){
                int subtotal = wInvoice.getAmount() * Integer.parseInt(wInvoice.getPriceSale());
                wInvoice.setPriceBuy(subtotal);
            }
            //calcula el subtotal
            int total = Integer.parseInt(invoice.getTotal());
            int taxes = Integer.parseInt(invoice.getTaxes());
            int subtotal = total - taxes;


            // Convertir la lista de datos en un origen de datos JRBeanCollectionDataSource
            JRBeanArrayDataSource sourceData = new JRBeanArrayDataSource(invoiceData.toArray());

            // Parámetros del informe
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("LOGO", "ruta/al/logo.png");
            parameters.put("ID_INVOICE", invoice.getIdInvoice());
            parameters.put("wareInvoice", sourceData);
            parameters.put("DATE", invoice.getDate());
            parameters.put("NAME_COMPANY", company.getName());
            parameters.put("NIT_EMPRESA", company.getIdCompanyNIT());
            parameters.put("PHONE_COMPANY", company.getPhoneNumber());
            parameters.put("CUSTOMER", customer.getName());
            parameters.put("NIT_CUSTOMER", invoice.getIdCustomer());
            parameters.put("PHONE_CUSTOMER", customer.getPhoneNumber());
            parameters.put("OBSERVATIONS", invoice.getObservations());
            parameters.put("SUBTOTAL", String.valueOf(subtotal));
            parameters.put("TOTAL", invoice.getTotal());
            parameters.put("IVA", invoice.getTaxes());

            // Compilar el informe
            JasperReport jasperReport = null;
            try{
               jasperReport = JasperCompileManager.compileReport(reportPath);
            }catch (JRException i){
                if (os.contains("win")) {
                    jasperReport = JasperCompileManager.compileReport(pathInstallWin);
                }else {
                    jasperReport = JasperCompileManager.compileReport(pathInstallLinux);
                }
            }

            // Llenar el informe con datos y parámetros
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, sourceData);

            // Crear la vista previa del informe
            JRViewer viewer = new JRViewer(jasperPrint);

            // Establecer las dimensiones deseadas
            viewer.setPreferredSize(new Dimension(1000, 800));

            // Crear un diálogo para mostrar la vista previa
            JDialog dialog = new JDialog();
            dialog.getContentPane().add(viewer);
            dialog.setTitle("Vista previa del informe");
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);

            SessionDB.sessionClose();

            // Exportar el informe a PDF u otro formato
           //JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Teemo\\OneDrive\\destino.pdf");
        } catch (Exception e) {
            WareController.alertSend("ERROR EL CARGAR EL REPORTE" );
        }
    }

    private static List<WareinvoiceClass> obtenerDatosDeFactura() {
        return (List<WareinvoiceClass>) ConstantsAccounting.invoice.getWareinvoicesByIdInvoice();
    }

    public static CompanyClass companyFound (){
       try {
           String entity = Constant.entity;
           Constant.entity = "CompanyClass";
           FoundHQL.workerFound();
           Constant.entity = entity;

       }catch (Exception i){
           WareController.alertSend("ERROR AL CARGAR LA COMPAÑIA");
       }
        return Constant.company;
    }

    public static CustomerClass customerInvoice(String idCustomer){
        try {
            String entity = Constant.entity;
            String code = Constant.tfCode;
            Constant.entity = "CustomerClass";
            Constant.tfCode = idCustomer;
            if (!FoundHQL.workerFound()) {
                CustomerClass customer = new CustomerClass();
                customer.setName("GENERICO");
                customer.setPhoneNumber("---------");
                customer.setIdCustomer(idCustomer);
                Constant.tfCode = code;
                Constant.entity = entity;
                return customer;
            }
            Constant.tfCode = code;
            Constant.entity = entity;
        }catch (Exception i){
            WareController.alertSend("ERROR AL CARGAR CLIENTE");
        }
        return Constant.customer;
    }

    public static void generadorCotizar(CompanyClass company, CustomerClass customer, ArrayList<WareinvoiceClass> wareInvoice, int subtotal, int iva){
        // Ruta al archivo del informe de JasperReports (.jASPERT)

        String reportPath;
        String pathInstallWin = "C:\\Program Files (x86)\\forpus\\reports\\Cotizar.jrxml";
        String pathInstallLinux = "/opt/forpusInventory/reports/Cotizar.jrxml";
        if (os.contains("win")) {
            // Código para Windows
            reportPath = "Cotizar.jrxml";
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            // Código para Linux o macOS
            reportPath = "/home/loro/MEGAsync/hubgit/forpusInventory/src/main/resources/com/forpus/jasper_report/Cotizar.jrxml";
        } else {
            throw new UnsupportedOperationException("Sistema operativo no compatible");
        }

        try {
            WareinvoiceClass wI = wareInvoice.get(0);
            wareInvoice.add(0, wI);

            for(WareinvoiceClass wInvoice: wareInvoice){
                int totalForItem = wInvoice.getAmount() * Integer.parseInt(wInvoice.getPriceSale());
                wInvoice.setPriceBuy(totalForItem);
            }

            // Convertir la lista de datos en un origen de datos JRBeanCollectionDataSource
            JRBeanArrayDataSource sourceData = new JRBeanArrayDataSource(wareInvoice.toArray());

            // Parámetros del informe
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("LOGO", "ruta/al/logo.png");
            parameters.put("wareInvoice", sourceData);
            parameters.put("DATE", ConstantsPurchases.dateActuallyGet());
            parameters.put("NAME_COMPANY", company.getName());
            parameters.put("NIT_EMPRESA", company.getIdCompanyNIT());
            parameters.put("PHONE_COMPANY", company.getPhoneNumber());
            parameters.put("CUSTOMER", customer.getName());
            parameters.put("NIT_CUSTOMER", customer.getIdCustomer());
            parameters.put("PHONE_CUSTOMER", customer.getPhoneNumber());
            parameters.put("OBSERVATIONS", "COTIZACIÓN VALIDA POR 1 MES");
            parameters.put("SUBTOTAL", String.valueOf(subtotal));
            parameters.put("TOTAL", String.valueOf(iva + subtotal));
            parameters.put("IVA", String.valueOf(iva));

            // Compilar el informe
            JasperReport jasperReport;
            try{
                jasperReport = JasperCompileManager.compileReport(reportPath);
            }catch (JRException i){
                if (os.contains("win")) {
                    jasperReport = JasperCompileManager.compileReport(pathInstallWin);
                }else {
                    jasperReport = JasperCompileManager.compileReport(pathInstallLinux);
                }
            }

            // Llenar el informe con datos y parámetros
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, sourceData);

            // Crear la vista previa del informe
            JRViewer viewer = new JRViewer(jasperPrint);

            // Establecer las dimensiones deseadas
            viewer.setPreferredSize(new Dimension(1000, 800));

            // Crear un diálogo para mostrar la vista previa
            JDialog dialog = new JDialog();
            dialog.getContentPane().add(viewer);
            dialog.setTitle("Vista previa del informe");
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);

            SessionDB.sessionClose();

        } catch (Exception e) {
            WareController.alertSend("ERROR EL CARGAR EL REPORTE");
        }

    }
}
