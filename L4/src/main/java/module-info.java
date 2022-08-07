module com.example.l4_final {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.l4_final to javafx.fxml;
    exports com.example.l4_final;
}