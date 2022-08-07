module com.example.l3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.l3 to javafx.fxml;
    exports com.example.l3;
}