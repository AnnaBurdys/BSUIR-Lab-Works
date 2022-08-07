package com.example.l4_final;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConnectController implements Initializable {
    @FXML
    TextField ipAddress;

    @FXML
    TextField portField;

    @FXML
    ImageView connection;

    @FXML
    protected void onConnectButtonClick(ActionEvent event) throws IOException {
        if (!(ipAddress.getText().equals("127.0.0.1") && portField.getText().equals("1234"))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Connection unsuccessful");
            alert.setHeaderText(null);
            alert.setContentText("Wrong IP or port. Try again!");
            alert.showAndWait();
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file1 = new File("/Users/annaburdys/IdeaProjects/L4_final/src/main/java/com/example/l4_final/Images/connectionPic.png");
        Image image1 = new Image(file1.toURI().toString());
        connection.setImage(image1);
    }
}