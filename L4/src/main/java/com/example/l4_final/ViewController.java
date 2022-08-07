package com.example.l4_final;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class ViewController implements Initializable {

    @FXML
    public TableColumn <ModelTable, String> idColumn;

    @FXML
    public TableColumn <ModelTable, String> nameColumn;

    @FXML
    public TableColumn <ModelTable, String> authorColumn;

    @FXML
    public TableColumn <ModelTable, String> yearColumn;

    @FXML
    public TableColumn <ModelTable, String> genreColumn;

    @FXML
    public TableColumn <ModelTable, String> priceColumn;

    @FXML
    public TableColumn <ModelTable, String> rateColumn;

    @FXML
    public TableView<ModelTable> table;

    private DbConnection dc;
    private ObservableList <ModelTable> data;

    @FXML
    public void onViewButtonClick () throws SQLException, ClassNotFoundException {
        Connection conn = dc.Connect();
        data = FXCollections.observableArrayList();
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM books");
        while (rs.next()) {
            data.add (new ModelTable (rs.getString(1), rs.getString(2),
                    rs.getString(3), rs.getString(4), rs.getString(5),
                    rs.getString(6), rs.getString(7)) );
        }

        idColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("bookAuthor"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("bookYear"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("bookGenre"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("bookPrice"));
        rateColumn.setCellValueFactory(new PropertyValueFactory<>("bookRate"));

        table.setItems(null);
        table.setItems(data);
    }



    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        dc = new DbConnection();
    }



    @FXML
    public void onGoBackButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}