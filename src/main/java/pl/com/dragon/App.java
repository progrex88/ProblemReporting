package pl.com.dragon;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

   public Thread thread = Thread.currentThread();

    @Override
    public void start(Stage primaryStage) throws Exception {



        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/fxml/MainReportWindow.fxml"));
        BorderPane borderPane = loader.load();



        Scene scene = new Scene(borderPane,600,400);
        primaryStage.setScene(scene);

        primaryStage.setTitle("Pierwsza Scena");
        primaryStage.show();



    }

    public static void main(String[] args) {
        launch();
    }

}