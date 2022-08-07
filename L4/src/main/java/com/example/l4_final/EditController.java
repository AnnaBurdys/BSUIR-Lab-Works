package com.example.l4_final;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class EditController {
    @FXML
    TextField bookIdField;

    @FXML
    TextField nameField;

    @FXML
    TextField authorField;

    @FXML
    TextField yearField;

    @FXML
    TextField genreField;

    @FXML
    TextField priceField;

    @FXML
    TextField rateField;

    @FXML
    Label editLabel;

    private Socket socket;
    private static DataOutputStream outputStream;
    private String bookId;
    private String bookName;
    private String authorName;
    private String year;
    private String genre;
    private String price;
    private String rate;

    public void onEditButtonClick() {

        if (bookIdField.getText().equals("") || nameField.getText().equals("") ||
                authorField.getText().equals("") || yearField.getText().equals("") ||
                genreField.getText().equals("") || priceField.getText().equals("") ||
                rateField.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Connection successful");
            alert.setHeaderText(null);
            alert.setContentText("Wrong data. Try again!");
            alert.showAndWait();
        } else {
            try {
                socket = new Socket("127.0.0.1", 1234);
                outputStream = new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

            bookId = bookIdField.getText();
            bookName = nameField.getText();
            authorName = authorField.getText();
            year = yearField.getText();
            genre = genreField.getText();
            price = priceField.getText();
            rate = rateField.getText();

            String output = "UPDATE books SET Name = \'" + bookName + "\', Author = \'" + authorName
                    + "\', year = \'" + year + "\', Genre = \'"
                    + genre + "\', Price = \'" + price + "\', Rate = \'"
                    + rate + "\'  WHERE BookId = \'" + bookId + "\';";

            try {
                outputStream.writeUTF(output);
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            editLabel.setText("Edited successfully");
        }
    }

    public void onGoBackButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}