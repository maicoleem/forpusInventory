package com.forpus.forpus_inventory.domain.repository;
import com.forpus.forpus_inventory.domain.services.Constant;
import com.forpus.forpus_inventory.domain.services.ConstantsAccounting;
import com.forpus.forpus_inventory.persistence.crud.FoundHQL;
import com.forpus.forpus_inventory.persistence.entity.CompanyClass;
import com.forpus.forpus_inventory.persistence.entity.CustomerClass;
import com.forpus.forpus_inventory.persistence.entity.InvoiceClass;
import com.forpus.forpus_inventory.persistence.entity.WareinvoiceClass;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportGenerator {

    public static void generateReport() {
        InvoiceClass invoice = ConstantsAccounting.invoice;
        CustomerClass customer = customerInvoice(invoice.getIdCustomer());
        CompanyClass company = companyFound();
        // Ruta al archivo del informe de JasperReports (.jASPERT)
        String reportPath = "C:\\Users\\Teemo\\OneDrive\\java\\proyecto1\\forpus_inventory\\src\\main\\resources\\com\\forpus\\jasper_report\\Factura.jrxml";

        try {
            // Obtener los datos para la tabla (en este caso, una lista de objetos WareInvoice)
            List<WareinvoiceClass> invoiceData = obtenerDatosDeFactura();

            for(WareinvoiceClass w: invoiceData){
                System.out.println(w);
            }

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
            parameters.put("SUBTOTAL", "FFFFFFFF");
            parameters.put("TOTAL", invoice.getTotal());
            parameters.put("IVA", "FFFFF");

            // Compilar el informe
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);

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

            // Exportar el informe a PDF u otro formato
           //JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Teemo\\OneDrive\\destino.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<WareinvoiceClass> obtenerDatosDeFactura() {
        return (List<WareinvoiceClass>) ConstantsAccounting.invoice.getWareinvoicesByIdInvoice();
    }

    public static CompanyClass companyFound (){
        String entity = Constant.entity;
        Constant.entity = "CompanyClass";
        FoundHQL.workerFound();
        Constant.entity = entity;

        return Constant.company;
    }

    private static CustomerClass customerInvoice(String idCustomer){
        String entity = Constant.entity;
        String code = Constant.tfCode;
        Constant.entity = "CustomerClass";
        Constant.tfCode = idCustomer;
        if(!FoundHQL.workerFound()){
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
        return Constant.customer;
    }
}
