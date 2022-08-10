package client.util;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MessageConfiguration {
    public void getClientNotification(ListView<HBox> listView, String text) {
        HBox hBox = new HBox();
        Label l1 = new Label();

        l1.setText(text);
        l1.setFont(Font.font("System", FontWeight.NORMAL, 15));
        l1.setStyle("-fx-background-color: #1C3F3E; -fx-background-radius: 13px; -fx-padding: 10px");
        l1.setTextFill(Color.web("#ffffff"));
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(l1);
        listView.getItems().add(hBox);
        listView.scrollTo(listView.getItems().lastIndexOf(hBox));
    }
}
