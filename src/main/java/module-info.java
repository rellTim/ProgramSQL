module com.example.test02 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.test02 to javafx.fxml;
    exports com.example.test02;
}