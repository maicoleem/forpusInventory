package com.forpus.forpus_inventory.persistence.crud;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
        String executeCmd = "mysqldump -u root -p"+ pDB +" inventoryaccounting -r " + path;
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
}
