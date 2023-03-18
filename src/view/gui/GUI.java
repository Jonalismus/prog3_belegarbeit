package view.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;


public class GUI extends Application {

    public static void run(){
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getClassLoader().getResource("view/gui/scene/startWindow.fxml"))));
        primaryStage.setTitle("Kuchenautomat");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


}
