package pl.com.dragon.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class SimpleAppCont extends BorderPane {

    @FXML
    Label status;
    @FXML
    TextField nameField;
    @FXML TextField emailField;
    @FXML TextArea commentsField;

    public void onCancel(ActionEvent actionEvent) {
        status.setText("You cancelled");
    }

    public void onOK(ActionEvent actionEvent) {
        status.setText(computeStatus());

    }

    private String computeStatus() {
        return "Name: " + nameField.getText() + ", Email: " + emailField.getText() +
                ", Comments: " + commentsField.getText();
    }
}
