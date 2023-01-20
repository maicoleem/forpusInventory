package com.forpus.forpus_inventory;

import com.forpus.forpus_inventory.controller.SettingController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(SettingController.class.getResource("setting-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Forpus Inventory");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}