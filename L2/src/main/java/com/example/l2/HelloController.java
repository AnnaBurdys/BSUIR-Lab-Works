package com.example.l2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private TextArea resultText;

    @FXML
    private TextField sendingData;

    @FXML
    private TextField ipAddress;

    @FXML
    private TextField port;

    @FXML
    private ImageView winx;

    @FXML
    private ImageView kiko;

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {
        File file1 = new File("/Users/burdysanna/IdeaProjects/L2/src/main/java/com/example/l2/Images/winx.png");
        Image image1 = new Image(file1.toURI().toString());
        winx.setImage(image1);
        File file2 = new File("/Users/burdysanna/IdeaProjects/L2/src/main/java/com/example/l2/Images/kiko.png");
        Image image2 = new Image(file2.toURI().toString());
        kiko.setImage(image2);
    }

    @FXML
    protected void onConnectButtonClick() throws IOException {
        if (ipAddress.getText().equals("127.0.0.1") && port.getText().equals("1777")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Connection established!");
            alert.setHeaderText(null);
            alert.setContentText("You are successfully connected to the server!");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Check IP address and port");
            alert.showAndWait();
        }
    }

    @FXML
    protected void onClearButtonCLick() throws IOException {
        ipAddress.setText("");
        port.setText("");
        sendingData.setText("");
        resultText.setText("");
    }

    @FXML
    protected void onHelloButtonClick() throws IOException {

        if (!sendingData.getText().equals("")  ) {
            int portNumber = 1777;
            String str;
            Socket socket = new Socket("127.0.0.1", portNumber);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader stroka = new BufferedReader(new InputStreamReader(System.in));
            str = sendingData.getText();
            pw.println(str);
            str = br.readLine();
            resultText.setText(str);
            br.close();
            pw.close();
            socket.close();

            ipAddress.setText("");
            port.setText("");
            sendingData.setText("");
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Check input data!");
            alert.showAndWait();
        }
    }
}