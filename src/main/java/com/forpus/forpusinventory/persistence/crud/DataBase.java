package com.forpus.forpusinventory.persistence.crud;
import com.forpus.forpusinventory.controller.FinanceController;
import com.forpus.forpusinventory.controller.WareController;
import com.forpus.forpusinventory.domain.services.Constant;
import com.forpus.forpusinventory.domain.services.ConstantsPurchases;
import com.forpus.forpusinventory.domain.services.ConstantsWare;
import com.forpus.forpusinventory.persistence.Session.SessionDB;
import com.forpus.forpusinventory.persistence.entity.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Metamodel;
import org.hibernate.Session;
import org.hibernate.query.Query;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import java.io.*;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


public class DataBase {

    public static boolean backUp(String path) {

        Properties properties = properties();
        String pDB = properties.getProperty("clave");
        String pName = properties.getProperty("name");
        String date = ConstantsPurchases.dateDDMMAA();

        String os = System.getProperty("os.name").toLowerCase();

        String executeCmd = "";

        if (os.contains("win")) {
            // Código para Windows
            String file = "\\"+date+ "backup.sql";
            executeCmd = "mysqldump -u root -p"+ pDB +" " + pName + " -r " + path + file;
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            // Código para Linux o macOS
            String file = path + "/"+date+ "backup.sql";
            //executeCmd = "mysqldump --user=root" + " --password=" + pDB + " --databases " + pName + " -r " + file;
            executeCmd = "mysqldump -u root -p"+ pDB +" " + pName + " -r " + file;
        } else {
            throw new UnsupportedOperationException("Sistema operativo no compatible");
        }
        try {
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            // Captura la salida estándar (stdout)
            InputStream inputStream = runtimeProcess.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            // Muestra la salida estándar
            while ((line = reader.readLine()) != null) {
                System.out.println("stdout: " + line);
            }

            // Captura la salida de error (stderr)
            InputStream errorStream = runtimeProcess.getErrorStream();
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));
            // Muestra la salida de error
            while ((line = errorReader.readLine()) != null) {
                System.out.println("stderr: " + line);
            }
            int processComplete = runtimeProcess.waitFor();
            if (processComplete == 0) {
                System.out.println("La copia de seguridad de la base de datos se ha completado correctamente");
                return true;
            } else {
                System.out.println("Error al realizar la copia de seguridad de la base de datos");
                return false;
            }
        } catch (IOException | InterruptedException e) {
            WareController.alertSend("ERROR EN DESCARGAR EL BACKUP");
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

        int error = 1;
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
                    if(error == 1) {
                        WareController.alertSend("ERROR EN EL TIPO DE DATO");
                    }
                    error = error +1;
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
            WareController.alertSend("ERROR AL GUARDAR EL LIBRO");
        }
        return true;
    }
    public static boolean importTable(String path, String entity) {
        //borra los datos de la tabla a la que se le van a importar los datos
        deleteAllData(entity);
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
        //check hibernate connection and database
        try{
            if(SessionDB.sessionHibernate != null){
                SessionDB.sessionClose();
            }
        }catch (Exception i){
            WareController.alertSend("ERROR AL CONECTAR CON LA BASE DE DATOS");
        }
        SessionDB.session();
        Session session = SessionDB.sessionHibernate;
        Cell idCell;

        try (FileInputStream inputStream = new FileInputStream(path)) {

            int erroImport = 1;

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

            for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
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
                        try {
                        if (field.getType() == String.class) {
                            try {
                                field.set(entityObject, cell.getStringCellValue());
                            }catch (Exception e){
                                field.set(entityObject, String.valueOf(cell.getNumericCellValue()));
                            }
                        } else if (field.getType() == Integer.class) {
                            try {
                                field.set(entityObject, (int) cell.getNumericCellValue());
                            }catch (Exception numberFormat){
                                field.set(entityObject, Integer.parseInt( cell.getStringCellValue()));
                            }

                        } else if (field.getType() == Double.class) {
                            field.set(entityObject, cell.getNumericCellValue());
                        }
                        }catch (Exception f){
                            if(erroImport == 1){
                                WareController.alertSend("ERROR EN EL TIPO DE DATOS EN EL ARCHIVO " + f);
                                erroImport = erroImport + 1;
                            }
                        }
                    }
                }

                idCell = sheet.getRow(i).getCell(0);
                try {
                    idValue = idCell.getStringCellValue();
                }catch (Exception e){
                    idValue = String.valueOf(idCell.getNumericCellValue());
                }

                switch (entity) {
                    case "CustomerClass":
                        idField = entityClass.getDeclaredField("idCustomer");
                        break;
                    case "WorkersClass":
                        idField = entityClass.getDeclaredField("identificationCard");
                        break;
                    case "ProvidersClass":
                        idField = entityClass.getDeclaredField("nit");
                        break;
                    case "ProductClass":
                        idField = entityClass.getDeclaredField("idProduct");
                        break;
                    case "ServiceClass":
                        idField = entityClass.getDeclaredField("idService");
                        break;
                    default:
                        idField = entityClass.getDeclaredField("identificationCard");
                        break;
                }
                // Asignar el valor del identificador al objeto entityObject
                idField.setAccessible(true);
                idField.set(entityObject, idValue);

                entityObject = session.merge(entityObject);
            }
            session.getTransaction().commit();
            workbook.close();

            if(entity.equals("ProductClass")){
                warePrice();
            }
            return true;
        } catch (IOException | IllegalAccessException | InstantiationException | NoSuchFieldException e) {
            WareController.alertSend( e.toString() + " PRODUCTOS NO AGREGADOS");
            return false;
        }
    }
    private static void warePrice(){
        String entity = Constant.entity;

        try {
            //check hibernate connection and database
            if (SessionDB.sessionHibernate.isOpen()) {
                SessionDB.sessionClose();
            }
            SessionDB.session();
            Session session = SessionDB.session().getSession();

            Constant.entity = "ProductClass";
            SearchHQL.searchHQL();

            int i = 1;

            ConstantsPurchases.wareProductList.clear();
            ConstantsPurchases.pPriceUpdateList.clear();

            session.beginTransaction();

            for(ProductClass p:  ConstantsWare.productList ){
                //Crea la ware product
                WareProductClass wp = new WareProductClass();
                wp.setIdProduct(p.getIdProduct());
                wp.setProductByIdProduct(p);

                Constant.entity = "WarehouseClass";
                Constant.tfCode = "BD01";
                FoundHQL.workerFound();

                wp.setIdWareProduct(i);
                wp.setIdWare(ConstantsWare.ware.getIdWarehouse());
                wp.setWarehouseByIdWare(ConstantsWare.ware);
                session.save(wp);

                //Crea el product price
                ProductpriceClass price = new ProductpriceClass();
                price.setPrice(Integer.parseInt(p.getPurchasePrice()));
                price.setIdPrice(i);
                price.setAmount(p.getAmount());
                price.setIdProductWare(wp.getIdWareProduct());
                price.setWareProductByIdProductWare(wp);
                session.save(price);
            }
            session.getTransaction().commit();
        }catch (Exception i){
            WareController.alertSend("Error en creación de bodegas y precios de productos");
        }
        Constant.entity = entity;

    }
    public static boolean deleteAllData(String entity) {
        try {
            // Obtén una instancia de sesión de Hibernate
            //check hibernate connection and database
            try{
                if(SessionDB.sessionHibernate != null){
                    SessionDB.sessionClose();
                }
            }catch (Exception i){
                WareController.alertSend("ERROR AL CONECTAR CON LA BASE DE DATOS");
            }
            SessionDB.session();
            Session session = SessionDB.sessionHibernate;
            session.beginTransaction();

            // Crea y ejecuta las consultas de eliminación para cada tabla
            switch (entity) {
                case "CustomerClass":
                    session.createQuery("DELETE FROM CustomerClass").executeUpdate();
                    break;
                case "WorkersClass":
                    session.createQuery("DELETE FROM WorkersClass").executeUpdate();
                    break;
                case "ProvidersClass":
                    session.createQuery("DELETE FROM ProvidersClass").executeUpdate();
                    break;
                case "ProductClass":
                    session.createQuery("DELETE FROM ProductpriceClass").executeUpdate();
                    session.createQuery("DELETE FROM WareProductClass").executeUpdate();
                    session.createQuery("DELETE FROM ProductClass").executeUpdate();
                    break;
                case "ServiceClass":
                    session.createQuery("DELETE FROM ServiceClass").executeUpdate();
                    break;
                case "PartnersClass":
                    session.createQuery("DELETE FROM PartnersClass").executeUpdate();
                    break;
                case "todos":
                    session.createQuery("DELETE FROM MoveinvoiceClass").executeUpdate();
                    session.createQuery("DELETE FROM InvoiceClass").executeUpdate();
                    session.createQuery("DELETE FROM CustomerClass").executeUpdate();
                    session.createQuery("DELETE FROM WorkersClass").executeUpdate();
                    session.createQuery("DELETE FROM ProvidersClass").executeUpdate();

                    session.createQuery("DELETE FROM ProductpriceClass").executeUpdate();
                    session.createQuery("DELETE FROM WareProductClass").executeUpdate();
                    session.createQuery("DELETE FROM ProductClass").executeUpdate();

                    session.createQuery("DELETE FROM ServiceProductClass").executeUpdate();
                    session.createQuery("DELETE FROM ServiceClass").executeUpdate();
                    session.createQuery("DELETE FROM PartnersClass").executeUpdate();
                    session.createQuery("DELETE FROM CategoryoneClass").executeUpdate();
                    session.createQuery("DELETE FROM CategorytwoClass ").executeUpdate();
                    session.createQuery("DELETE FROM CategorythreeClass ").executeUpdate();

                    session.createQuery("DELETE FROM WareinvoiceClass").executeUpdate();

                    session.createQuery("DELETE FROM WarehouseClass").executeUpdate();
                    break;
                default:
                    break;
            }
            // Confirma la transacción
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            WareController.alertSend("ERROR AL BORRAR LOS DATOS");
            return false;

        }
    }
    public static boolean install() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("SQL Files", "*.sql")
            );

            File selectedFile = fileChooser.showOpenDialog(new Stage());

            String path = FinanceController.escaparCaracteres(selectedFile.getPath());

            //condicional para crear la base de datos
            if(createDatabase()){
                String os = System.getProperty("os.name").toLowerCase();

                //dependiendo del sistema operativo
                if (os.contains("win")) {
                    // Código para Windows
                    winDBRestore(path);
                } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
                    // Código para Linux o macOS
                    linuxDBRestore(path);
                } else {
                    throw new UnsupportedOperationException("Sistema operativo no compatible");
                }
                return true;
            }else {
                return false;
            }
        }catch (Exception i){
            WareController.alertSend("ERROR AL CARGAR BASE DE DATOS");
            return false;
        }
    }
    public static Properties properties(){
        Properties properties = new Properties();
        try {
            InputStream inputStream = DataBase.class.getClassLoader().getResourceAsStream("properties.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            WareController.alertSend(e.toString());
        }
        return properties;
    }
    public static boolean createDatabase() {
        Properties properties = properties();
        String username = properties.getProperty("user");
        String password = properties.getProperty("clave");
        String databaseName = properties.getProperty("name");
        String url = properties.getProperty("url");
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {
            // Crea la base de datos
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + databaseName);
            return true;
        } catch (SQLException e) {
            WareController.alertSend("Error createDataBase");
            return false;
        }
    }
    public static void winDBRestore(String path){
        Properties properties = properties();
        String executeCmd = "mysql -u " + properties.getProperty("user") + " -p" + properties.getProperty("clave") + " " +properties.getProperty("name");
        try {
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            OutputStream os = runtimeProcess.getOutputStream();
            FileInputStream fis = new FileInputStream(path);
            byte[] buffer = new byte[1000];
            int leido = fis.read(buffer);
            while (leido>0){
                os.write(buffer, 0, leido);
                leido = fis.read(buffer);
            }
            os.flush();
            os.close();
            fis.close();
        } catch (IOException e) {
            WareController.alertSend("Error Cargando Datos");
        }
    }
    public static void linuxDBRestore(String path){

        Properties properties = properties();
        String jdbcURL = properties.getProperty("url") + properties.getProperty("name");;
        String username = properties.getProperty("user");
        String password = properties.getProperty("clave");

        try {
            // Establecer la conexión a la base de datos
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            Statement statement = connection.createStatement();

            // Ruta al archivo SQL que deseas ejecutar
            String sqlFilePath = path;

            // Leer el archivo SQL
            BufferedReader br = new BufferedReader(new FileReader(sqlFilePath));
            String line;
            StringBuilder sqlCommands = new StringBuilder();

            while ((line = br.readLine()) != null) {
                sqlCommands.append(line).append("\n");
            }

            // Dividir los comandos SQL
            String[] commands = sqlCommands.toString().split(";");

            // Ejecutar cada comando SQL
            for (String command : commands) {
                statement.executeUpdate(command);
            }

            // Cerrar recursos
            br.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            WareController.alertSend("Error Cargando Datos");
        }
    }
}
