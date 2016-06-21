package ru.gum.ats.logzocfx.start;/**
 * Created by chupiraov on 27.04.2016.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    private static Stage primaryStage;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception{

        this.primaryStage = primaryStage;
        //initRootLayout();
        showLogZocManager();
    }

    public static Stage getPrimaryStage(){
        return primaryStage;
    }

    private void showLogZocManager() {
        try {
            // Load LogZocManager.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../viewfxml/logzocoverview.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();
            Scene scene = new Scene(anchorPane,600,400);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
