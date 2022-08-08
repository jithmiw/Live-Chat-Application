package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(this.getClass().getResource("/client/views/LoginForm.fxml"));
        Scene mainScene = new Scene(root);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Live-Chat");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
