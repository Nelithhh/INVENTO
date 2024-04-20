module com.invento.invento {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.dlsc.formsfx;
    requires javax.mail.api;

    opens com.invento.invento to javafx.fxml;

    exports com.invento.invento;
    exports javaClass;
    opens javaClass to javafx.base, javafx.fxml;
}