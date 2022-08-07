package com.example.l4_final;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class DeleteController implements Initializable {

    @FXML
    private Label deleteText;

    @FXML
    private TextField idToDeleteField;

    @FXML
    private ImageView trashbin;

    private Socket socket;
    private String delete;
    private static DataOutputStream outputStream;

    @FXML
    public void onDeleteButtonClick() {
        try {
            socket = new Socket("127.0.0.1", 1234);
            outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        delete = idToDeleteField.getText();

        String output = "DELETE FROM books WHERE BookId = " + delete;

        try {
            outputStream.writeUTF(output);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        deleteText.setText("Deleted successfully");
    }

    @FXML
    public void onGoBackButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file1 = new File("/Users/annaburdys/IdeaProjects/L4_final/src/main/java/com/example/l4_final/Images/trashbin.png");
        Image image1 = new Image(file1.toURI().toString());
        trashbin.setImage(image1);
    }

}
