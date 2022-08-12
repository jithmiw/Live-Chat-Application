package client.controllers;

import client.ApplicationContext;
import client.ChatClientThread;
import client.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.DataOutputStream;
import java.io.IOException;

public class ClientFormController {
    public JFXButton buttonSend;
    public ListView<HBox> listView;
    public JFXTextField textField;
    public HBox menuBar;
    public Font x3;
    public Color x4;
    public SplitPane splitPane;
    public Label nameLabel;
    public ImageView logo;
    public ImageView imageSelect;

    private String name;
    private DataOutputStream streamOut;

    public void initialize() {
        logo.setImage(new Image(Main.class.getResource("resources/img/messages.png").toString()));
        imageSelect.setImage(new Image(Main.class.getResource("resources/img/camera.png").toString()));
        streamOut = ApplicationContext.getStreamConfiguration().getStreamOut();
        name = ApplicationContext.getStreamConfiguration().getName();
        nameLabel.setText(name);

        initFields();
        new ChatClientThread(this);
        ClientNotification("Establishing connection. Please wait...");
        ClientNotification("Welcome " + name);
    }

    private void initFields() {
        //Get focus except on control, shift, alt and capslock keys.
        listView.setOnKeyPressed(event -> {
            if (event.getCode() != KeyCode.CONTROL && event.getCode() != KeyCode.SHIFT && event.getCode() != KeyCode.ALT
                    && event.getCode() != KeyCode.CAPS)
                textField.requestFocus();

        });

        //Textfield limit is 240 characters.
        textField.lengthProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() > oldValue.intValue()) {
                if (textField.getText().length() >= 240) {
                    textField.setText(textField.getText().substring(0, 240));
                }
            }
        });

        textField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                buttonSend.fire();
            } else if (event.getCode() == KeyCode.BACK_SPACE) {
                return;
            }
        });
        Platform.runLater(() -> textField.requestFocus());
    }

    private void ClientNotification(String text) {
        ApplicationContext.getMessageConfiguration().getClientNotification(listView, text);
    }

    public void pressedSend(ActionEvent event) {
    }

    public void imgChooseOnAction(MouseEvent event) {
    }
}
