package com.example.l3;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class HelloController {

    @FXML
    private TextField coeffA;

    @FXML
    private TextField coeffB;

    @FXML
    private TextField coeffC;

    @FXML
    private TextField resultField;

    @FXML
    protected void onCalculateButtonClick() throws IOException {
        if (!coeffA.getText().equals("") &&
                !coeffB.getText().equals("") &&
                !coeffC.getText().equals("")) {

            DatagramSocket st = new DatagramSocket(12346);
            DatagramPacket dp;
            InetAddress loc = InetAddress.getByName("localhost"); // объект класса InetAddress для определения айпи адреса порта
            byte[] buf; // массив для пересылки данных серверу
            byte[] buf2 = new byte[100]; // массив для получения данных от сервера

            buf = coeffA.getText().getBytes();
            int k = coeffA.getText().getBytes().length;
            dp = new DatagramPacket(buf, k, loc, 12345);
            st.send(dp); // пересылка дейтаграммы серверу

            buf = coeffB.getText().getBytes();
            k = coeffB.getText().getBytes().length;
            dp = new DatagramPacket(buf, k, loc, 12345);
            st.send(dp);

            buf = coeffC.getText().getBytes();
            k = coeffC.getText().getBytes().length;
            dp = new DatagramPacket(buf, k, loc, 12345);
            st.send(dp);

            dp = new DatagramPacket(buf2, 100);
            st.receive(dp);
            resultField.setText(new String(dp.getData()));
            st.close();
            coeffA.setText("");
            coeffB.setText("");
            coeffC.setText("");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Check input data!");
            alert.showAndWait();}
    }

    @FXML
    protected void onClearButtonCLick() throws IOException {
        coeffA.setText("");
        coeffB.setText("");
        coeffC.setText("");
        resultField.setText("");
    }
}