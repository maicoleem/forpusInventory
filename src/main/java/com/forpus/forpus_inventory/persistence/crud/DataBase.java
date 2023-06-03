package com.forpus.forpus_inventory.persistence.crud;

import com.forpus.forpus_inventory.controller.WareController;
import com.forpus.forpus_inventory.domain.services.ConstantsWare;
import com.forpus.forpus_inventory.persistence.Session.SessionDB;
import com.forpus.forpus_inventory.persistence.entity.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.*;
import org.apache.xmlbeans.soap.SOAPArrayType;
import org.hibernate.Metamodel;
import org.hibernate.Session;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.swing.plaf.basic.BasicEditorPaneUI;
import java.io.FileInputStream;
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
            case "ProductClass":
                queryStr = "FROM ProductClass";
                entityClass = ProductClass.class;
                break;
            case "ServiceClass":
                queryStr = "FROM ServiceClass";
                entityClass = ServiceClass.class;
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
    public static void importTable(String path, String entity) {
        SessionDB.session();
        Metamodel metamodel = SessionDB.metamodel();
        Session session = SessionDB.sessionHibernate;
        Cell idCell;

        try (FileInputStream inputStream = new FileInputStream(path)) {

            if(!session.beginTransaction().isActive()){
                session.beginTransaction();
            }
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            Row headerRow = sheet.getRow(0);
            int columnCount = headerRow.getPhysicalNumberOfCells();
            String[] columnNames = new String[columnCount];

            for (int i = 0; i < columnCount; i++) {
                Cell cell = headerRow.getCell(i);
                columnNames[i] = cell.getStringCellValue();
            }

            Class<?> entityClass;
            switch (entity) {
                case "CustomerClass":
                    entityClass = CustomerClass.class;
                    break;
                case "WorkersClass":
                    entityClass = WorkersClass.class;
                    break;
                case "ProvidersClass":
                    entityClass = ProvidersClass.class;
                    break;
                case "ProductClass":
                    entityClass = ProductClass.class;
                    break;
                case "ServiceClass":
                    entityClass = ServiceClass.class;
                    break;
                default:
                    entityClass = PartnersClass.class;
                    break;
            }

            for (int i = 1; i < sheet.getLastRowNum(); i++) {
                Row dataRow = sheet.getRow(i);
                Object entityObject = entityClass.newInstance();
                String idValue = null;
                Field idField = null;

                for (int j = 0; j < columnCount; j++) {
                    Cell cell = dataRow.getCell(j);
                    String propertyName = columnNames[j];

                    Field field = entityClass.getDeclaredField(propertyName);
                    field.setAccessible(true);

                    if (cell == null || cell.getCellType() == CellType.BLANK) {
                        // Si la celda está vacía, establecer el valor del campo como null
                        field.set(entityObject, null);
                    } else {
                        // Si la celda no está vacía, establecer el valor del campo según el tipo de dato
                        if (field.getType() == String.class) {
                            field.set(entityObject, cell.getStringCellValue());
                        } else if (field.getType() == Integer.class) {
                            field.set(entityObject, (int) cell.getNumericCellValue());
                        } else if (field.getType() == Double.class) {
                            field.set(entityObject, cell.getNumericCellValue());
                        }
                    }
                }

                switch (entity) {
                    case "CustomerClass":
                        // Obtener el valor del identificador en la fila 1, columna 3
                        idCell = sheet.getRow(i).getCell(4);
                        idValue = idCell.getStringCellValue();
                        idField = entityClass.getDeclaredField("idCustomer");
                        break;
                    case "WorkersClass":
                        // Obtener el valor del identificador en la fila 1, columna 3
                        idCell = sheet.getRow(i).getCell(8);
                        idValue = idCell.getStringCellValue();
                        idField = entityClass.getDeclaredField("identificationCard");
                        break;
                    case "ProvidersClass":
                        // Obtener el valor del identificador en la fila 1, columna 3
                        idCell = sheet.getRow(i).getCell(2);
                        idValue = idCell.getStringCellValue();
                        idField = entityClass.getDeclaredField("nit");
                        break;
                    case "ProductClass":
                        // Obtener el valor del identificador en la fila 1, columna 3
                        idCell = sheet.getRow(i).getCell(1);
                        idValue = idCell.getStringCellValue();
                        idField = entityClass.getDeclaredField("idProduct");
                        break;
                    case "ServiceClass":
                        // Obtener el valor del identificador en la fila 1, columna 3
                        idCell = sheet.getRow(i).getCell(0);
                        idValue = idCell.getStringCellValue();
                        idField = entityClass.getDeclaredField("idService");
                        break;
                    default:
                        // Obtener el valor del identificador en la fila 1, columna 3
                        idCell = sheet.getRow(i).getCell(5);
                        idValue = idCell.getStringCellValue();
                        idField = entityClass.getDeclaredField("identificationCard");
                        break;
                }
                // Asignar el valor del identificador al objeto entityObject
                System.out.println(idValue);
                idField.setAccessible(true);
                idField.set(entityObject, idValue);

                entityObject = session.merge(entityObject);

            }

            session.getTransaction().commit();
            System.out.println("274");
            workbook.close();
        } catch (IOException | IllegalAccessException | InstantiationException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

}
