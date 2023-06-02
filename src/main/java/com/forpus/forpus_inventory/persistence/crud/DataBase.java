package com.forpus.forpus_inventory.persistence.crud;

import com.forpus.forpus_inventory.controller.WareController;
import com.forpus.forpus_inventory.domain.services.ConstantsWare;
import com.forpus.forpus_inventory.persistence.Session.SessionDB;
import com.forpus.forpus_inventory.persistence.entity.CustomerClass;
import com.forpus.forpus_inventory.persistence.entity.PartnersClass;
import com.forpus.forpus_inventory.persistence.entity.ProvidersClass;
import com.forpus.forpus_inventory.persistence.entity.WorkersClass;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.*;
import org.hibernate.Metamodel;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.swing.plaf.basic.BasicEditorPaneUI;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;


public class DataBase {

    public static boolean backUp(String path){

        Properties properties = new Properties();
        try {
            InputStream inputStream = DataBase.class.getClassLoader().getResourceAsStream("properties.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String pDB = properties.getProperty("clave");
        String executeCmd = "mysqldump -u root -p"+ pDB +" inventoryaccounting -r " + path + "\\backup.sql";
        try {
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {
                System.out.println("La copia de seguridad de la base de datos se ha completado correctamente");
                return true;
            } else {
                System.out.println("Error al realizar la copia de seguridad de la base de datos");
                return false;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean downloadTable(String path, String entity) {
        SessionDB.session();
        Metamodel metamodel = SessionDB.metamodel();
        Session session = SessionDB.sessionHibernate;

        // Crear el libro de Excel y las hojas
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Datos");
        // Crear la primera fila para los encabezados
        Row headerRow = sheet.createRow(0);

        int index = 0;

        // Llenar los datos
        int rowNum = 1;

        String queryStr;
        Class<?> entityClass;
        switch (entity) {
            case "CustomerClass":
                queryStr = "FROM CustomerClass";
                entityClass = CustomerClass.class;
                break;
            case "WorkersClass":
                queryStr = "FROM WorkersClass";
                entityClass = WorkersClass.class;
                break;
            case "ProvidersClass":
                queryStr = "FROM ProvidersClass";
                entityClass = ProvidersClass.class;
                break;
            default:
                queryStr = "FROM PartnersClass";
                entityClass = PartnersClass.class;
                break;
        }

        Query<?> query = session.createQuery(queryStr, entityClass);
        List<?> resultList = query.getResultList();

        EntityType<?> entityType = Objects.requireNonNull(metamodel).entity(entityClass);
        Set<? extends Attribute<?, ?>> attributes = entityType.getDeclaredAttributes();

        String[] columnNames = new String[attributes.size()];

        for (Attribute<?, ?> attribute : attributes) {
            columnNames[index++] = attribute.getName();
        }

        for (int i = 0; i < columnNames.length; i++) {
            org.apache.poi.ss.usermodel.Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnNames[i]);
        }

        for (Object row : resultList) {
            Row dataRow = sheet.createRow(rowNum++);
            for (int i = 0; i < columnNames.length; i++) {
                Cell cell = dataRow.createCell(i);
                String propertyName = columnNames[i];
                try {
                    Field field = row.getClass().getDeclaredField(propertyName);
                    field.setAccessible(true);
                    Object value = field.get(row);
                    if (value instanceof String) {
                        cell.setCellValue((String) value);
                    } else if (value instanceof Integer) {
                        cell.setCellValue((Integer) value);
                    } else if (value instanceof Double) {
                        cell.setCellValue((Double) value);
                    }
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

// Ajustar el ancho de las columnas automáticamente
        for (int i = 0; i < columnNames.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Guardar el libro de Excel en un archivo
        try (FileOutputStream outputStream = new FileOutputStream(path + "\\" + entity + ".xlsx")) {
            workbook.write(outputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }/* finally {
            session.close();
        }*/
        return true;
    }
}
