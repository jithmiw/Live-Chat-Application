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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

public class ClientFormController {
    private final FileChooser fileChooser = new FileChooser();
    public JFXButton buttonSend;
    public ListView<HBox> listView;
    public JFXTextField textField;
    public HBox menuBar;
    public Font x3;
    public Color x4;
    public SplitPane splitPane;
    public HBox emojiRow;
    public Label nameLabel;
    public ImageView logo;
    public ImageView imageSelect;
    public ImageView btnEmojiRow;
    public Label lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8, lbl9, lbl10, lbl11, lbl12, lbl13, lbl14, lbl15,
            lbl16, lbl17, lbl18, lbl19, lbl20, lbl21, lbl22, lbl23, lbl24, lbl25, lbl26, lbl27, lbl28;

    Stage stage = new Stage();
    private String name;
    private DataOutputStream streamOut;

    public void initialize() {
        logo.setImage(new Image(Main.class.getResource("resources/img/messages.png").toString()));
        btnEmojiRow.setImage(new Image(Main.class.getResource("resources/img/emoji.png").toString()));
        imageSelect.setImage(new Image(Main.class.getResource("resources/img/camera.png").toString()));
        streamOut = ApplicationContext.getStreamConfiguration().getStreamOut();
        name = ApplicationContext.getStreamConfiguration().getName();
        nameLabel.setText(name);
        emojiRow.setVisible(false);
        emojiRow.setManaged(false);
        initFields();
        initEmojis();
        new ChatClientThread(this);
        ClientNotification("Establishing connection. Please wait...");
        ClientNotification("Welcome " + name);
    }

    private void initFields() {
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

    private void initEmojis() {
        Label[] labels = new Label[]{lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8, lbl9, lbl10,
                lbl11, lbl12, lbl13, lbl14, lbl15, lbl16, lbl17, lbl18,
                lbl19, lbl20, lbl21, lbl22, lbl23, lbl24, lbl25, lbl26, lbl27, lbl28};
        String[] unicode = new String[]{"\uD83D\uDE00", "\uD83D\uDE04", "\uD83D\uDE01", "\uD83D\uDE06", "\uD83D\uDE05", "\uD83D\uDE02",
                "\uD83E\uDD23", "\uD83D\uDE07", "\uD83D\uDE0D", "\uD83D\uDE11", "\uD83D\uDE36", "\uD83D\uDE0F", "\uD83D\uDE2E", "\uD83D\uDE44",
                "\uD83D\uDE34", "\uD83D\uDE2C", "\uD83D\uDE2D", "\uD83D\uDE31", "\uD83D\uDC40", "\uD83D\uDC4B", "\uD83D\uDC4D",
                "\uD83D\uDC4E", "\uD83D\uDC4F", "\uD83D\uDE4F", "\uD83D\uDCAA", "\uD83E\uDD19", "\uD83E\uDD1D", "\uD83D\uDC4A"};

        for (int i = 0; i < 28; i++) {
            labels[i].setText(unicode[i]);
        }
    }

    private void ClientNotification(String text) {
        ApplicationContext.getMessageConfiguration().getClientNotification(listView, text);
    }

    public void handle(String msg) {
        Platform.runLater(() -> ServerMessage(msg));
    }

    private void ServerMessage(String text) {
        ApplicationContext.getMessageConfiguration().getServerRespond(listView, text);
    }

    public void pressedSend(ActionEvent event) {
        if (textField.getText().startsWith("//")) {
            //There is no command support yet.
            ClientNotification("Unknown Command");
        } else if (textField.getText() != null && !textField.getText().trim().isEmpty()) { //Prevent sending empty messages.
            Send(textField.getText());
        }
        textField.clear();
    }

    private void Send(String message) {
        try {
            streamOut.writeUTF(name + ":- " + message);
            streamOut.flush();
            ClientMessage(message);
        } catch (IOException e) {
            ClientNotification("Sending error : " + e);
            ApplicationContext.getStreamConfiguration().stopStream();
            new Alert(Alert.AlertType.ERROR, "An error occurred.").show();
        } catch (Exception e) {
            ClientNotification("An error occurred. Please restart the client...");
            ApplicationContext.getStreamConfiguration().stopStream();
            new Alert(Alert.AlertType.ERROR, "An error occurred.").show();
        }
    }

    private void ClientMessage(String text) {
        ApplicationContext.getMessageConfiguration().getClientRespond(listView, text);
    }

    public void emojiOnAction(MouseEvent event) {
        Label label = (Label) event.getSource();
        textField.setText(textField.getText() + label.getText());
        textField.end();
    }

    public void imgChooseOnAction(MouseEvent event) {
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            Send(file.getPath());
        }
    }

    public void emojiRowOnAction(MouseEvent event) {
        if (emojiRow.isVisible()) {
            emojiRow.setVisible(false);
            emojiRow.setManaged(false);
        } else {
            emojiRow.setVisible(true);
            emojiRow.setManaged(true);
        }
    }
}
