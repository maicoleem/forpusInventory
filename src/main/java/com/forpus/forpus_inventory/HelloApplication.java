package com.forpus.forpus_inventory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Forpus Inventory");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void hn(){
        try {
            SessionFactory sF = new Configuration().configure().buildSessionFactory();
            Session s = sF.openSession();
            if (s != null) {
                System.out.println("Success");
                sF.close();
            } else {
                System.out.println("No success");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}