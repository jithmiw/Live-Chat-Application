package client.controllers;

import client.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class LoginFormController {
    public TextField fieldIpAddress;
    public Button buttonEnter;
    public TextField fieldUserName;
    public TextField fieldPort;
    public VBox vbox;
    public AnchorPane anchor;
    public ImageView img;

    public void initialize()  {
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

    private void textFieldLimit(TextField textField, int limit){
        textField.lengthProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.intValue() > oldValue.intValue()){
                if(textField.getText().length() >= 10){
                    textField.setText(textField.getText().substring(0,limit));
                }
            }
        });
    }

    public void pressedEnter(ActionEvent event) {
    }
}
