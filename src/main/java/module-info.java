module com.example.tap_intellij {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tap_intellij to javafx.fxml;
    opens com.example.tap_intellij.Componentes to javafx.base;
    exports com.example.tap_intellij;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mariadb.java.client;
}