/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Admon Lee
 */

package ucf.assignments;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application {

    //Load main GUI
    @Override
    public void start(Stage stage) throws Exception{

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("InventoryTrackerInterface.fxml")));

        Scene scene = new Scene(root);
        stage.setTitle("Inventory Tracker");
        stage.setScene(scene);
        stage.show();
    }

    //Start application
    public static void main(String[] args) {
        launch(args);
    }
}
