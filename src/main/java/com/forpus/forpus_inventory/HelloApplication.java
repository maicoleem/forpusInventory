package com.forpus.forpus_inventory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**<h1>FORPUS INVENTORY POS</h1>
 * Forpus inventory POS is a program for manage the sales, the purchases, the inventory and accounting
 * of an one micro-enterprise, with the projection of reaching all SMEs as the development of the program progresses
 *,aiming to be able to manage a large company.
 * <p>
 * @autor Maicol Escobar
 * @version 1.0.1
 * @since 2023
 *<h2>HelloApplication</h2>
 *Start the scene hello-view.fxml where the user can sing in.
 * </p>
 * */
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

}