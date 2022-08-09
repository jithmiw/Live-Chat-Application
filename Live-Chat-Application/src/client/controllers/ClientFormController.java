package client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ClientFormController {
    public JFXButton buttonSend;
    public ListView<HBox> listView;
    public ListView<HBox> listViewStatus;
    public JFXTextField textField;
    public Label label1;
    public HBox menuBar;
    public Font x3;
    public Color x4;
    public SplitPane splitPane;
    public Label nameLabel;
    public ImageView logo;

    public void pressedSend(ActionEvent event) {
    }
}
