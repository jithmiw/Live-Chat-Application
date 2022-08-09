package client.controllers;

import client.ApplicationContext;
import client.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public TextField fieldIpAddress;
    public Button buttonEnter;
    public TextField fieldUserName;
    public TextField fieldPort;
    public VBox vbox;
    public AnchorPane anchor;
    public ImageView img;

    public void initialize() {
        img.setImage(new Image(Main.class.getResource("resources/img/messages.png").toString()));

        textFieldLimit(fieldUserName, 10);
        textFieldLimit(fieldIpAddress, 20);
        textFieldLimit(fieldPort, 10);

        Platform.runLater(() -> fieldUserName.requestFocus());

        vbox.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                buttonEnter.fire();
            }
        });
    }

    private void textFieldLimit(TextField textField, int limit) {
        textField.lengthProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() > oldValue.intValue()) {
                if (textField.getText().length() >= 10) {
                    textField.setText(textField.getText().substring(0, limit));
                }
            }
        });
    }

    public void pressedEnter(ActionEvent event) {
        String name;
        int port;
        String ip;
        try {
            name = fieldUserName.getText();
            port = Integer.parseInt(fieldPort.getText());
            ip = fieldIpAddress.getText();
        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION, "Invalid input. Please try again...").show();
            e.printStackTrace();
            return;
        }
        try {
            initStreamConfiguration(name, ip, port);
            Parent window = FXMLLoader.load(getClass().getResource("/client/views/ClientForm.fxml"));
            Scene newScene = new Scene(window);
            Stage mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            mainWindow.setTitle("Live-Chat");
            mainWindow.setScene(newScene);
            mainWindow.setMaximized(true);
        } catch (IOException ex) {
            new Alert(Alert.AlertType.ERROR, "An error occurred.").show();
            ex.printStackTrace();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred.").show();
            e.printStackTrace();
            System.exit(0);
        }
    }

    private void initStreamConfiguration(String name, String ip, int port) throws IOException {
        ApplicationContext.getStreamConfiguration().setName(name);
        ApplicationContext.getStreamConfiguration().setHostAddr(ip);
        ApplicationContext.getStreamConfiguration().setPortAddr(port);
        ApplicationContext.getStreamConfiguration().initSocket();
        ApplicationContext.getStreamConfiguration().initStream();
    }
}
