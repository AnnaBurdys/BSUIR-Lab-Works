module com.example.l2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.l2 to javafx.fxml;
    exports com.example.l2;
}